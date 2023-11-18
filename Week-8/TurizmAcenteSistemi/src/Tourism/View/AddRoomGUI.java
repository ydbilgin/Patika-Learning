package Tourism.View;

import Tourism.Helper.DBConnector;
import Tourism.Helper.Helper;
import Tourism.Model.Hotel;
import Tourism.Model.Room;
import Tourism.Model.Term;
import Tourism.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddRoomGUI extends JFrame {
    private JPanel wrapper;
    private JCheckBox chck_box_tv;
    private JCheckBox chck_box_mini;
    private JCheckBox chck_box_gpad;
    private JCheckBox chck_box_vault;
    private JCheckBox chck_box_projector;
    private JTextField fld_room_adult_price;
    private JTextField fld_room_children_price;
    private JButton btn_update_room_p;
    private JButton btn_add_room_p;
    private JComboBox cmb_room_type;
    private JTextField fld_room_stock;
    private JTextField fld_room_size;
    private JTextField fld_bed_count;
    private JTable tbl_term_list_byroom;
    private JButton btn_price_add;
    private JScrollPane pnl_tbl_term_list;
    private JPopupMenu roomMenu;

    private JCheckBox[] checkBoxList;
    private DefaultTableModel mdl_room_list_byroom;
    private Object[] row_room_list_byroom;
    Room room;
    double childRatio = 0.8;
    int hotelID;
    int roomID;
    User user;

    Connection connection;

    public AddRoomGUI(int id, Boolean isEdit, User user) {
        this.user = user;
        hotelID = id;
        roomID = id;
        this.add(wrapper);
        setSize(1200, 600);
        setTitle("Oda Yönetimi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        int x = Helper.screenCenter("x", this.getSize());
        int y = Helper.screenCenter("y", this.getSize());
        setLocation(x, y);
        checkBoxList = new JCheckBox[]{chck_box_tv, chck_box_mini, chck_box_gpad, chck_box_vault, chck_box_projector};
        this.connection = DBConnector.getInstance();
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                EmployeeGUI employeeGUI = new EmployeeGUI(user);

            }
        });
        if (isEdit) {
            btn_add_room_p.setVisible(false);
            btn_update_room_p.setVisible(true);
            tbl_term_list_byroom.setVisible(true);



            room = Room.getRoom(roomID);
            List<String> featureList = Arrays.stream(room.getRoomFeature().split(",")).toList();
            for (String feature : featureList) {
                for (JCheckBox checkBox : checkBoxList) {
                    if (feature.equals(checkBox.getText())) {
                        checkBox.setSelected(true);
                    }
                }
            }
            loadRoomData(id);


        } else {
            btn_add_room_p.setVisible(true);
            btn_update_room_p.setVisible(false);
            tbl_term_list_byroom.setVisible(false);
            pnl_tbl_term_list.setVisible(false);
            btn_price_add.setVisible(false);


        }
        mdl_room_list_byroom = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_room_list = {"ID", "Oda ID", "Oda Tipi","Pansiyon tipi","Dönem Başlangıç","Dönem Bitiş", "Stok sayısı", "Oda Özellikleri", "Metrekare", "Yatak Sayısı","Yetişkin Fiyat","Çocuk Fiyat"};
        mdl_room_list_byroom.setColumnIdentifiers(col_room_list);
        row_room_list_byroom = new Object[col_room_list.length];
        loadTermModelRoomPage(roomID);
        tbl_term_list_byroom.setModel(mdl_room_list_byroom);
        tbl_term_list_byroom.setComponentPopupMenu(roomMenu);
        tbl_term_list_byroom.getTableHeader().setReorderingAllowed(false);
        tbl_term_list_byroom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int row = e.getPoint().y / tbl_term_list_byroom.getRowHeight();
                    tbl_term_list_byroom.setRowSelectionInterval(row,row);


                    int termID = (int) tbl_term_list_byroom.getValueAt(row, 0);

                    JPopupMenu hotelMenu = new JPopupMenu();

                    JMenuItem termManagement = new JMenuItem("Fiyat güncelle");
                    JMenuItem deleteMenuItem = new JMenuItem("Sil");

                    termManagement.addActionListener(event5 ->{
                        PriceSetGUI priceSetGUI = new PriceSetGUI(termID,true,user);
                        dispose();
                    });
                    deleteMenuItem.addActionListener(event5 ->{
                        Term.deleteTerm(termID);
                        dispose();
                    });

                    hotelMenu.add(termManagement);
                    hotelMenu.add(deleteMenuItem);
                    hotelMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        btn_add_room_p.addActionListener(e -> {
            addRoom();
            dispose();
            AddHotelGUI addHotelGUI = new AddHotelGUI(hotelID, true,user);


        });
        btn_price_add.addActionListener(e -> {
            PriceSetGUI priceSetGUI = new PriceSetGUI(roomID,false,user);
            dispose();

        });
        btn_price_add.addActionListener(e -> {

        });
        btn_update_room_p.addActionListener(e -> {
            updateRoom(roomID);
            dispose();
            EmployeeGUI employeeGUI = new EmployeeGUI(user);


        });
    }

    public void loadRoomData(int id) {
        String query = "SELECT room_stock, room_size, room_bed_count, room_type FROM room WHERE room_id = " + id;

        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int roomStock = resultSet.getInt("room_stock");
                int roomSize = resultSet.getInt("room_size");
                int roomBedCount = resultSet.getInt("room_bed_count");
                String roomType = resultSet.getString("room_type");

                fld_room_stock.setText(String.valueOf(roomStock));
                fld_room_size.setText(String.valueOf(roomSize));
                fld_bed_count.setText(String.valueOf(roomBedCount));
                cmb_room_type.setSelectedItem(roomType);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void loadTermModelRoomPage(int roomID) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_term_list_byroom.getModel();
        clearModel.setRowCount(0);

        for (Term obj : Term.getTermListByRoomID(roomID)) {
            int i = 0;
            Room room = Room.getRoom(obj.getRoomID());
            row_room_list_byroom[i++] = obj.getTermId();
            row_room_list_byroom[i++] = room.getRoomID();
            row_room_list_byroom[i++] = room.getRoomType();
            row_room_list_byroom[i++] = obj.getRoomBoardType();
            row_room_list_byroom[i++] = obj.getTermStartDate();
            row_room_list_byroom[i++] = obj.getTermEndDate();
            row_room_list_byroom[i++] = room.getRoomStock();
            row_room_list_byroom[i++] = room.getRoomFeature();
            row_room_list_byroom[i++] = room.getRoomSize();
            row_room_list_byroom[i++] = room.getRoomBedCount();
            row_room_list_byroom[i++] = obj.getAdultPrice();
            row_room_list_byroom[i++] = obj.getChildrenPrice();
            mdl_room_list_byroom.addRow(row_room_list_byroom);
        }

    }

    public void loadRoomModel(ArrayList<Room> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_term_list_byroom.getModel();
        clearModel.setRowCount(0);


        for (Room obj : list) {
            int i = 0;
            row_room_list_byroom[i++] = obj.getRoomID();
            Hotel hotel = Hotel.getFetch(obj.getHotelID());
            row_room_list_byroom[i++] = hotel.getName();
            row_room_list_byroom[i++] = obj.getRoomType();
            row_room_list_byroom[i++] = obj.getRoomStock();
            row_room_list_byroom[i++] = obj.getRoomFeature();
            row_room_list_byroom[i++] = obj.getRoomSize();
            row_room_list_byroom[i++] = obj.getRoomBedCount();
            mdl_room_list_byroom.addRow(row_room_list_byroom);
        }
    }

    /*public void loadPrices(int id) {
        String query = "SELECT adult_price FROM term WHERE room_id = " + id;

        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int adultPrice = resultSet.getInt("adult_price");
                fld_room_adult_price.setText(String.valueOf(adultPrice));

                // Child price'ı hesapla ve göster
                double childPrice = adultPrice * childRatio;
                fld_room_children_price.setText(String.valueOf(childPrice));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/


    public String getCurrentFeatures() {
        String features = "";
        for (JCheckBox checkbox : checkBoxList) {
            if (checkbox.isSelected()) {
                features += checkbox.getText() + ",";
            }
        }
        return features;
    }

    public String getSelectedFeatures() {
        String selectedFeatures = "";
        for (JCheckBox checkbox : checkBoxList) {
            if (checkbox.isSelected()) {
                selectedFeatures += checkbox.getText() + ",";
            }
        }
        if (!selectedFeatures.isEmpty()) {
            // Son virgülü kaldırın
            selectedFeatures = selectedFeatures.substring(0, selectedFeatures.length() - 1);
        }
        return selectedFeatures;
    }


    public void addRoom() {

        try {
            String roomType = cmb_room_type.getSelectedItem().toString();
            int roomStock = Integer.parseInt(fld_room_stock.getText());
            String roomFeature = getSelectedFeatures();
            int roomSize = Integer.parseInt(fld_room_size.getText());
            int roomBedCount = Integer.parseInt(fld_bed_count.getText());

            if (Room.add(hotelID, roomType, roomStock, roomFeature, roomSize, roomBedCount)) {
                Helper.showMsg("done");
            }

        }catch (Exception e){
            Helper.showMsg("Sayı girilmesi gereken yere yazı yazamazsınız !");

        }




        setVisible(false);
    }

    public void updateRoom(int roomID) {


        try {
            int roomSize = Integer.parseInt(fld_room_size.getText());
            int roomBedCount = Integer.parseInt(fld_bed_count.getText());
            int roomStock = Integer.parseInt(fld_room_stock.getText());
            String roomType = cmb_room_type.getSelectedItem().toString();
            String roomFeature = getSelectedFeatures();

            if (Room.updateRoom(roomID, roomType,roomStock, roomFeature, roomSize, roomBedCount)) {
                Helper.showMsg("Oda başarıyla güncellendi.");
            }

        }catch (Exception e){
            Helper.showMsg("Sayı girilmesi gereken yere yazı yazamazsınız !");

        }




        setVisible(false);
    }

}





