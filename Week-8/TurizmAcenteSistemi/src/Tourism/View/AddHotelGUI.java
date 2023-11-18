package Tourism.View;

import Tourism.Helper.DBConnector;
import Tourism.Helper.Helper;
import Tourism.Model.Hotel;
import Tourism.Model.Room;
import Tourism.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Arrays;
import java.util.List;


public class AddHotelGUI extends JFrame {
    private JPanel wrapper;
    private JPanel w_top;
    private int hotelID;
    private JButton btn_hotel_add;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_district;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_mail;
    private JTextField fld_hotel_phone;
    private JTextField fld_hotel_star;
    private JButton btn_hotel_update;
    private JTable tbl_room_list;
    private JButton btn_add_room;
    private JScrollPane tbl_scr_pane;
    private JCheckBox chck_box_park;
    private JCheckBox chck_box_wifi;
    private JCheckBox chck_box_pool;
    private JCheckBox chck_box_fitness;
    private JCheckBox chck_box_hotelcon;
    private JCheckBox chck_box_SPA;
    private JCheckBox chck_box_roomservice;
    private DefaultTableModel mdl_room_list;
    private Object[] row_room_list;
    int roomID;
    User user;
    private JCheckBox[] checkBoxList;

    private boolean dontRunDisposeHandler = false;




    public AddHotelGUI(int id,boolean isEdit,User user){
        this.user =user ;
        this.roomID = id;
        this.hotelID = id;
        this.add(wrapper);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Otel Yönetimi");
        this.setSize(1200,800);
        int x = Helper.screenCenter("x", this.getSize());
        int y = Helper.screenCenter("y", this.getSize());
        setLocation(x, y);
        checkBoxList = new JCheckBox[]{chck_box_park,chck_box_wifi,chck_box_pool,chck_box_fitness,chck_box_hotelcon,chck_box_SPA,chck_box_roomservice};
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                EmployeeGUI employeeGUI = new EmployeeGUI(user);

            }
        });


        this.setVisible(true);
        if(isEdit){
            btn_hotel_update.setVisible(true);
            btn_hotel_add.setVisible(false);
            Hotel hotel = Hotel.getFetch(hotelID);
            tbl_room_list.setVisible(true);
            btn_add_room.setVisible(true);
            loadHotelData(hotelID);
            List<String> featureList = Arrays.stream(hotel.getHotelFeatures().split(",")).toList();
            for (String feature : featureList) {
                for (JCheckBox checkBox : checkBoxList) {
                    if (feature.equals(checkBox.getText())) {
                        checkBox.setSelected(true);
                    }
                }
            }




        }else {
            btn_hotel_update.setVisible(false);
            btn_hotel_add.setVisible(true);
            tbl_room_list.setVisible(false);
            tbl_room_list.getTableHeader().setVisible(false);
            btn_add_room.setVisible(false);
            setSize(400,400);
            x = Helper.screenCenter("x", this.getSize());
            y = Helper.screenCenter("y", this.getSize());
            setLocation(x, y);
        }
        btn_hotel_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_name) || Helper.isFieldEmpty(fld_hotel_city) || Helper.isFieldEmpty(fld_hotel_district)  || Helper.isFieldEmpty(fld_hotel_address) ||  Helper.isFieldEmpty(fld_hotel_mail) || Helper.isFieldEmpty(fld_hotel_phone)||  Helper.isFieldEmpty(fld_hotel_star)) {
                Helper.showMsg("fill");
            } else{
                String name = fld_hotel_name.getText();
                String city = fld_hotel_city.getText();
                String district = fld_hotel_district.getText();
                String hotelFeatures = getSelectedFeatures();
                String address = fld_hotel_address.getText();
                String mail = fld_hotel_mail.getText();
                String phone = fld_hotel_phone.getText();
                String star = fld_hotel_star.getText();

                if (Hotel.add(name, city, district, hotelFeatures, address, mail, phone, Integer.valueOf(star))) {
                    Helper.showMsg("done");

                }

                setVisible(false);
                createAddEmployeeGUI();

            }
        });
        mdl_room_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_room_list = {"Room ID", "Oda Tipi","Stok sayısı","Oda Özellikleri","Metrekare","Yatak Sayısı"};
        mdl_room_list.setColumnIdentifiers(col_room_list);
        row_room_list = new Object[col_room_list.length];
        loadRoomModelHotelPage();
        tbl_room_list.setModel(mdl_room_list);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);

        tbl_room_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Sağ tıklama gerçekleştiyse
                if (e.getButton() == MouseEvent.BUTTON3) {


                    int row = e.getPoint().y / tbl_room_list.getRowHeight();
                    tbl_room_list.setRowSelectionInterval(row,row);


                    int clickedID = (int) tbl_room_list.getValueAt(row, 0);

                    JPopupMenu roomMenu = new JPopupMenu();
                    JMenuItem roomManagement = new JMenuItem("Oda Yönetimi");
                    JMenuItem deleteRoom = new JMenuItem("Oda Sil");

                    roomManagement.addActionListener(event3 ->{
                        AddRoomGUI addRoomGUI = new AddRoomGUI(clickedID,true,user);
                        dispose();



                    });
                    deleteRoom.addActionListener(event4 -> {
                        Room.deleteRoom(clickedID);
                        loadRoomModelHotelPage();
                        roomMenu.setVisible(false);
                        Helper.showMsg("Oda başarıyla silindi.");

                    });


                    roomMenu.add(roomManagement);
                    roomMenu.add(deleteRoom);

                    roomMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });


        btn_add_room.addActionListener(e -> {
            AddRoomGUI addRoomGUI = new AddRoomGUI(id,false,user);
            dispose();


        });
        btn_hotel_update.addActionListener(e -> {
            createAddEmployeeGUI();

        });
        btn_hotel_update.addActionListener(e -> {
            updateHotel(hotelID);
            dispose();

        });
    }
    public void loadHotelData(int id) {
        String query = "SELECT hotel_name, hotel_city, hotel_district, hotel_address,hotel_mail,hotel_phone,hotel_star FROM hotel WHERE hotel_id = " + id;

        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String hotelName = resultSet.getString("hotel_name");
                String hotelCity = resultSet.getString("hotel_city");
                String hotelDistrict = resultSet.getString("hotel_district");
                String hotelAddress = resultSet.getString("hotel_address");
                String hotelMail = resultSet.getString("hotel_mail");
                String hotelPhone = resultSet.getString("hotel_phone");
                int hotelStar = resultSet.getInt("hotel_star");


                fld_hotel_name.setText(hotelName);
                fld_hotel_city.setText(hotelCity);
                fld_hotel_district.setText(hotelDistrict);
                fld_hotel_address.setText(hotelAddress);
                fld_hotel_mail.setText(hotelMail);
                fld_hotel_phone.setText(hotelPhone);
                fld_hotel_star.setText(String.valueOf(hotelStar));

            } else {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateHotel(int hotelId) {
        if (Helper.isFieldEmpty(fld_hotel_name) || Helper.isFieldEmpty(fld_hotel_city) || Helper.isFieldEmpty(fld_hotel_district) || Helper.isFieldEmpty(fld_hotel_address) || Helper.isFieldEmpty(fld_hotel_mail) || Helper.isFieldEmpty(fld_hotel_phone) || Helper.isFieldEmpty(fld_hotel_star)) {
            Helper.showMsg("fill");
        } else {
            String name = fld_hotel_name.getText();
            String city = fld_hotel_city.getText();
            String district = fld_hotel_district.getText();
            String hotelFeatures = getSelectedFeatures();
            String address = fld_hotel_address.getText();
            String mail = fld_hotel_mail.getText();
            String phone = fld_hotel_phone.getText();
            String star = fld_hotel_star.getText();

            if (Hotel.updateHotelById(hotelId, name, city, district , hotelFeatures , address, mail, phone, Integer.valueOf(star))) {

            }

            setVisible(false);
        }
    }

    public void loadRoomModelHotelPage() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);

        for (Room obj : Room.getListRoomBYHotelID(hotelID)) {
            int i = 0;
            row_room_list[i++] = obj.getRoomID();
            row_room_list[i++] = obj.getRoomType();
            row_room_list[i++] = obj.getRoomStock();
            row_room_list[i++] = obj.getRoomFeature();
            row_room_list[i++] = obj.getRoomSize();
            row_room_list[i++] = obj.getRoomBedCount();
            mdl_room_list.addRow(row_room_list);
        }

    }



    private void createAddEmployeeGUI() {
        EmployeeGUI employeeGUI = new EmployeeGUI(user);
        dispose();

    }
    public String getSelectedFeatures() {
        String selectedFeatures = "";
        for (JCheckBox checkbox : checkBoxList) {
            if (checkbox.isSelected()) {
                selectedFeatures += checkbox.getText() + ",";
            }
        }
        if (!selectedFeatures.isEmpty()) {
            selectedFeatures = selectedFeatures.substring(0, selectedFeatures.length() - 1);
        }
        return selectedFeatures;
    }


}
