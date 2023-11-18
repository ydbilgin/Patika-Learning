package Tourism.View;

import Tourism.Helper.DBConnector;
import Tourism.Helper.Helper;
import Tourism.Model.Room;
import Tourism.Model.Term;
import Tourism.Model.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PriceSetGUI extends JFrame {
    private JPanel wrapper;
    private JPanel w_top;
    private JTextField fld_adult_price;
    private JTextField fld_children_price;
    private JFormattedTextField fld_start_date;
    private JFormattedTextField fld_end_date;
    private JComboBox cmb_room_type;
    private JButton btn_add_price;
    private JButton btn_update_price;
    double childPriceRatio = 0.8;
    int roomID;
    int termID;
    Double adultPrice;
    Double childrenPrice;
    User user;

    public PriceSetGUI(int id, boolean isEdit, User user) {
        this.user = user;
        this.roomID = id;
        this.termID = id;
        this.add(wrapper);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Oda Fiyatlandırma");
        this.setSize(1200, 800);
        int x = Helper.screenCenter("x", this.getSize());
        int y = Helper.screenCenter("y", this.getSize());
        setLocation(x, y);
        this.setVisible(true);
        addPriceListeners();
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                EmployeeGUI employeeGUI = new EmployeeGUI(user);

            }
        });
        if (isEdit) {
            btn_add_price.setVisible(false);
            btn_update_price.setVisible(true);
            loadTermData(termID);



        } else {
            btn_add_price.setVisible(true);
            btn_update_price.setVisible(false);


        }
        btn_add_price.addActionListener(e -> {
            addPrice();
            dispose();
            AddRoomGUI addRoomGUI = new AddRoomGUI(roomID,true,user);


        });
        btn_update_price.addActionListener(e -> {
            updateTerm(termID);
            dispose();
            Term term = Term.getTermBYTermID(termID);
            AddHotelGUI addHotelGUI = new AddHotelGUI(term.getHotelID(),true,user);
        });
    }

    public void addPrice() {
        String roomBoardType = cmb_room_type.getSelectedItem().toString();
        String adultPriceStr = fld_adult_price.getText();
        String childrenPriceStr = fld_children_price.getText();
        String startDateStr = fld_start_date.getText();
        String endDateStr = fld_end_date.getText();

        // Boş değerleri kontrol et
        if (roomBoardType.isEmpty() || adultPriceStr.isEmpty() || childrenPriceStr.isEmpty() || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            Helper.showMsg("Tüm alanları doldurmalısınız.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        Date endDate;
        try {
            startDate =  dateFormat.parse(startDateStr);
            endDate =  dateFormat.parse(endDateStr);
        } catch (ParseException e) {
            Helper.showMsg("Tarih biçimi hatalı.");
            return;
        }
        if (endDate.before(startDate)){
            Helper.showMsg("Dönem bitiş tarihi, dönem başlangıç tarihinden önce olamaz!");
            return;
        }

        int roomID = this.roomID;

        // Tarihleri java.sql.Date türüne çevir
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

        if (Term.addTermToDatabase(roomBoardType, sqlStartDate, sqlEndDate, Double.parseDouble(adultPriceStr), Double.parseDouble(childrenPriceStr), roomID)) {
            Helper.showMsg("done");
        }
        setVisible(false);
    }


    private void addPriceListeners() {
        fld_adult_price.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateChildPrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateChildPrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateChildPrice();
            }

            private void updateChildPrice() {
                try {
                    String adultPriceText = fld_adult_price.getText();
                    // Virgülü noktaya çevir
                    double adultPrice = Double.parseDouble(adultPriceText);
                    double childPrice = adultPrice * childPriceRatio;
                    String formattedChildPrice = String.format("%.2f", childPrice);
                    formattedChildPrice = formattedChildPrice.replace(',', '.');
                    fld_children_price.setText(formattedChildPrice);


                } catch (NumberFormatException ex) {
                    fld_children_price.setText("");
                }
            }
        });
    }
    public void loadTermData(int termID) {
        String query = "SELECT room_board_type, term_start_date, term_end_date, adult_price, children_price FROM term WHERE term_id = " + termID;

        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String roomBoardType = resultSet.getString("room_board_type");
                Date startDate = resultSet.getDate("term_start_date");
                Date endDate = resultSet.getDate("term_end_date");
                double adultPrice = resultSet.getDouble("adult_price");
                double childrenPrice = resultSet.getDouble("children_price");

                fld_adult_price.setText(String.valueOf(adultPrice));
                fld_children_price.setText(String.valueOf(childrenPrice));
                fld_start_date.setText(String.valueOf(startDate));
                fld_end_date.setText(String.valueOf(endDate));
                cmb_room_type.setSelectedItem(roomBoardType);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTerm(int termID) {
        String roomBoardType = cmb_room_type.getSelectedItem().toString();
        Date startDate = parseDate(fld_start_date.getText());
        Date endDate = parseDate(fld_end_date.getText());
        double adultPrice = Double.parseDouble(fld_adult_price.getText());
        double childrenPrice = Double.parseDouble(fld_children_price.getText());

        if (Term.updateTermInDatabase(termID, roomBoardType, startDate, endDate, adultPrice, childrenPrice)) {
            Helper.showMsg("Fiyat başarıyla güncellendi.");
        } else {
            Helper.showMsg("Fiyat güncelleme sırasında bir hata oluştu.");
        }

        setVisible(false);
    }

    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }




}


