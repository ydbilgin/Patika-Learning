package Tourism.View;

import Tourism.Helper.DBConnector;
import Tourism.Helper.Helper;
import Tourism.Model.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ReservationGUI extends JFrame {
    private JPanel wrapper;
    private JPanel w_top;
    private JPanel w_bot;
    private JTextField fld_adult_number;
    private JTextField fld_children_number;
    private JFormattedTextField fld_start_date_r;
    private JFormattedTextField fld_end_date_r;
    private JTextField fld_name_surname;
    private JTextField fld_phone;
    private JTextField fld_email;
    private JButton btn_reservation;
    private JTextField fld_price;
    private JButton btn_update;
    private JTextField fld_info;
    private JLabel lbl_info;
    int termID;
    User user;

    ReservationGUI(int id, boolean isEdit, User user) {
        this.user = user;
        this.termID = id;
        this.add(wrapper);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Otel Yönetimi");
        this.setSize(600, 400);
        int x = Helper.screenCenter("x", this.getSize());
        int y = Helper.screenCenter("y", this.getSize());
        setLocation(x, y);
        this.setVisible(true);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                EmployeeGUI employeeGUI = new EmployeeGUI(user);

            }
        });
        if (isEdit){
            btn_reservation.setVisible(false);
            btn_update.setVisible(true);
            lbl_info.setVisible(false);
            fld_info.setVisible(false);

        }else {
            btn_update.setVisible(false);
            btn_reservation.setVisible(true);
            lbl_info.setVisible(true);
            fld_info.setVisible(true);
            setSize(800,600);

        }

        btn_reservation.addActionListener(e -> {
            addReservation();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date startDate = dateFormat.parse(fld_start_date_r.getText());
                Date endDate = dateFormat.parse(fld_end_date_r.getText());
                if (endDate.before(startDate)){
                    Helper.showMsg("Rezervasyon çıkış tarihi giriş tarihinden önce olamaz!");
                }
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
            EmployeeGUI employeeGUI = new EmployeeGUI(user);



        });

        Term term = Term.getTermBYTermID(termID);
        fld_info.setText(term.getTermStartDate().toString() + " / " + term.getTermEndDate().toString());


        fld_adult_number.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatePriceByNumber();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updatePriceByNumber();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updatePriceByNumber();
            }
        });

        fld_children_number.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatePriceByNumber();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updatePriceByNumber();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updatePriceByNumber();
            }
        });
        fld_start_date_r.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatePriceByDate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updatePriceByDate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updatePriceByDate();
            }
        });

        fld_end_date_r.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatePriceByDate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updatePriceByDate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updatePriceByDate();
            }
        });


    }



    public void addReservation() {
        try {
            if (Helper.isFieldEmpty(fld_adult_number)  || Helper.isFieldEmpty(fld_email) || Helper.isFieldEmpty(fld_phone) || Helper.isFieldEmpty(fld_name_surname) || Helper.isFieldEmpty(fld_start_date_r) || Helper.isFieldEmpty(fld_end_date_r)) {
                Helper.showMsg("fill");
                return;
            }

            int adultCount = 0;
            int childCount = 0;
            if (!fld_adult_number.getText().isEmpty()) {
                adultCount = Integer.parseInt(fld_adult_number.getText());
            }
            if (!fld_children_number.getText().isEmpty()) {
                childCount = Integer.parseInt(fld_children_number.getText());
            }
            if (Integer.parseInt(fld_adult_number.getText()) <=0){
                Helper.showMsg("Çocuklar rezervasyon yapamazzzzz :)))");
                return;
            }

            Term term = Term.getTermBYTermID(termID);

            if (!isDateFieldsValid()) {
                return;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(fld_start_date_r.getText());
            Date endDate = dateFormat.parse(fld_end_date_r.getText());

            if (endDate.before(startDate)) {
                Helper.showMsg("Bitiş tarihi, başlangıç tarihinden önce olamaz.");
                return;
            }

            long dayCount = calculateDayCount(startDate, endDate);

            double customerPrice = (adultCount * term.getAdultPrice()) + (childCount * term.getChildrenPrice());
            customerPrice *= dayCount;

            if (Reservation.add(fld_name_surname.getText(), fld_phone.getText(), fld_email.getText(), adultCount + childCount, new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()), customerPrice, termID)) {
                Helper.showMsg("done");
            }

            setVisible(false);
        } catch (ParseException ex) {
            Helper.showMsg("Tarih biçimi hatalı.");
        }
    }



    private void updatePriceByNumber() {
        try {
            int adultCount = 0;
            int childCount = 0;

            if (!fld_adult_number.getText().isEmpty()) {
                adultCount = Integer.parseInt(fld_adult_number.getText());
            }

            if (!fld_children_number.getText().isEmpty()) {
                childCount = Integer.parseInt(fld_children_number.getText());
            }

            Term term = Term.getTermBYTermID(termID);
            double customerPrice = (adultCount * term.getAdultPrice()) + (childCount * term.getChildrenPrice());
            String formattedPrice = String.format("%.2f", customerPrice);
            fld_price.setText(formattedPrice);
        } catch (NumberFormatException ex) {
            fld_price.setText("");
        }
    }

    private void updatePriceByDate() {
        try {
            if (!isDateFieldsValid()) {
                return;
            }

            int adultCount = 0;
            int childCount = 0;

            if (!fld_adult_number.getText().isEmpty()) {
                adultCount = Integer.parseInt(fld_adult_number.getText());
            }

            if (!fld_children_number.getText().isEmpty()) {
                childCount = Integer.parseInt(fld_children_number.getText());
            }

            Term term = Term.getTermBYTermID(termID);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(fld_start_date_r.getText());
            Date endDate = dateFormat.parse(fld_end_date_r.getText());

            long dayCount = calculateDayCount(startDate, endDate);

            double customerPrice = (adultCount * term.getAdultPrice()) + (childCount * term.getChildrenPrice());
            customerPrice *= dayCount;

            String formattedPrice = String.format("%.2f", customerPrice);
            fld_price.setText(formattedPrice);
        } catch (NumberFormatException | ParseException ex) {
            fld_price.setText("");
        }
    }



    private boolean isDateFieldsValid() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Başlangıç tarihini ve bitiş tarihini al
            String startDateStr = fld_start_date_r.getText();
            String endDateStr = fld_end_date_r.getText();

            if (startDateStr.isEmpty() || endDateStr.isEmpty()) {
                return false;
            }

            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            if (endDate.before(startDate)) {
                return false;
            }

            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public void loadReservationData(int reservationID) {
        String query = "SELECT customer_number, reservation_start_date, reservation_end_date, customer_name, customer_phone, customer_mail, customer_price FROM reservation WHERE reservation_id = " + reservationID;

        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int customerNumber = resultSet.getInt("customer_number");
                Date startDate = resultSet.getDate("reservation_start_date");
                Date endDate = resultSet.getDate("reservation_end_date");
                String customerName = resultSet.getString("customer_name");
                String customerPhone = resultSet.getString("customer_phone");
                String customerEmail = resultSet.getString("customer_mail");
                double customerPrice = resultSet.getDouble("customer_price");

                int adultCount = customerNumber;
                int childCount = 0;

                fld_adult_number.setText(String.valueOf(adultCount));
                fld_children_number.setText(String.valueOf(childCount));
                fld_start_date_r.setText(String.valueOf(startDate));
                fld_end_date_r.setText(String.valueOf(endDate));
                fld_name_surname.setText(customerName);
                fld_phone.setText(customerPhone);
                fld_email.setText(customerEmail);
                fld_price.setText(String.valueOf(customerPrice));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private long calculateDayCount(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
    }







}
