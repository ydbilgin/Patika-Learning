package Tourism.View;

import Tourism.Helper.Helper;
import Tourism.Model.*;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeGUI extends JFrame {
    private JTabbedPane tabbedPane1;
    private JButton btn_emp_logout;
    private JTable tbl_hotel_list;
    private JTextField getFld_welcome_employee;
    private JPanel wrapper;
    private JTable tbl_room_list;
    private JLabel lbl_welcome_empl;
    private JScrollPane pnl_hotel_list;
    private JFormattedTextField fld_start_date;
    private JFormattedTextField fld_end_date;
    private JTextField fld_hotel_name;
    private JTextField fld_guest_info;
    private JButton btn_emp_search;
    private JButton btn_refresh;
    private JTable tbl_reservation_list;
    private String fld_welcome_employee;
    private DefaultTableModel mdl_hotel_list;
    private DefaultTableModel mdl_room_list;
    private DefaultTableModel mdl_reservation_list;

    private Object[] row_hotel_list;
    private Object[] row_room_list;
    private Object[] row_reservation_list;
    private JPopupMenu hotelMenu;
    private JPopupMenu roomMenu;
    private JPopupMenu reservationMenu;
    private User user;
    private Term term;
    private boolean dontRunDisposeHandler = false;

    public EmployeeGUI(User user){
        add(wrapper);
        setSize(1000,500);
        setTitle("Acente Çalışanı ");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        int x = Helper.screenCenter("x", this.getSize());
        int y = Helper.screenCenter("y", this.getSize());
        setLocation(x, y);
        this.user = user;

        if (user == null){
            dispose();
        }
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                LoginGUI loginGUI = new LoginGUI();

            }
        });

        lbl_welcome_empl.setText("Hoşgeldiniz " + user.getName());


        //Hotel List
        mdl_hotel_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_hotel_list = {"ID", "Otel Adı","Şehir","Bölge","Otel Özellikleri", "Adres" ,  "Mail", "Telefon","Yıldız"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list = new Object[col_hotel_list.length];
        loadHotelModel();
        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.setComponentPopupMenu(hotelMenu);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);


        tbl_hotel_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int row = e.getPoint().y / tbl_hotel_list.getRowHeight();
                    tbl_hotel_list.setRowSelectionInterval(row,row);


                    int id = (int) tbl_hotel_list.getValueAt(row, 0);

                    JPopupMenu hotelMenu = new JPopupMenu();

                    JMenuItem hotelManagement = new JMenuItem("Otel Yönetimi");
                    JMenuItem addRoom = new JMenuItem("Oda Ekle");
                    JMenuItem deleteMenuItem = new JMenuItem("Sil");


                    hotelManagement.addActionListener(event2 ->{
                        AddHotelGUI addHotelGUI = new AddHotelGUI(id,true,user);
                        dispose();


                    });


                    deleteMenuItem.addActionListener(event4 -> {
                        Hotel.deleteHotel(id);
                        Helper.showMsg("Otel Başarıyla silindi!");
                        loadPriceTableModel();
                        loadHotelModel();
                        hotelMenu.setVisible(false);

                    });
                    addRoom.addActionListener(event5 ->{
                        AddRoomGUI addRoomGUI = new AddRoomGUI(id,false,user);
                        dispose();
                    });
                    hotelMenu.add(hotelManagement);
                    hotelMenu.add(addRoom);
                    hotelMenu.add(deleteMenuItem);
                    hotelMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        tbl_hotel_list.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    createAddHotelRightClick(e);

                }
            }
        });

        pnl_hotel_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    createAddHotelRightClick(e);

                }
            }
        });



        tbl_room_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Sağ tıklama gerçekleştiyse
                if (e.getButton() == MouseEvent.BUTTON3) {


                    int row = e.getPoint().y / tbl_room_list.getRowHeight();
                    tbl_room_list.setRowSelectionInterval(row,row);


                    int roomID = (int) tbl_room_list.getValueAt(row, 1);
                    int termID = (int) tbl_room_list.getValueAt(row, 0);

                    JPopupMenu roomMenu = new JPopupMenu();
                    JMenuItem reservationMenu = new JMenuItem("Rezervasyon Yap");
                    JMenuItem roomManagement = new JMenuItem("Oda Yönetimi");
                    JMenuItem deleteTerm = new JMenuItem("Fiyatı Sil");

                    roomManagement.addActionListener(event3 ->{
                        AddRoomGUI addRoomGUI = new AddRoomGUI(roomID,true,user);
                        dispose();



                    });

                    reservationMenu.addActionListener(event1 ->{
                        ReservationGUI reservationGUI = new ReservationGUI(termID,false,user);
                        dispose();


                    });
                    deleteTerm.addActionListener(event4 -> {
                        Term.deleteTerm(termID);
                        Helper.showMsg("Fiyat dönemi başarıyla silindi");
                        loadPriceTableModel();
                        loadReservationModel();



                        roomMenu.setVisible(false);

                        Helper.showMsg("Dönem aralığı başarıyla silindi.");
                    });
                    roomMenu.add(reservationMenu);
                    roomMenu.add(roomManagement);
                    roomMenu.add(deleteTerm);
                    roomMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        //Room List
        mdl_room_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_room_list = {"Term id ","ID", "Otel Adı","Şehir","Bölge", "Oda Tipi","Pansiyon Tipi","Başlangıç Tarihi","Bitiş Tarihi","Stok sayısı","Oda Özellikleri","Metrekare","Yatak Sayısı","Yetişkin Fiyatı","Çocuk Fiyatı"};
        mdl_room_list.setColumnIdentifiers(col_room_list);
        row_room_list = new Object[col_room_list.length];
        loadPriceTableModel();
        tbl_room_list.setModel(mdl_room_list);
        tbl_room_list.setComponentPopupMenu(roomMenu);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);


        tbl_room_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_room_list.rowAtPoint(point);
                tbl_room_list.setRowSelectionInterval(selected_row,selected_row);

            }
        });

        btn_emp_logout.addActionListener(e -> dispose());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        btn_emp_search.addActionListener(e -> {
            String place = fld_hotel_name.getText();
            String start = fld_start_date.getText();
            String end = fld_end_date.getText();
            String personNumberText = fld_guest_info.getText();
            int filledFieldCount = 0;

            Date startDate = null;
            Date endDate = null;

            if (!place.isEmpty()) {
                filledFieldCount++;
            }

            if (!start.isEmpty()) {
                try {
                    startDate = dateFormat.parse(start);
                    filledFieldCount++;
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if (!end.isEmpty()) {
                try {
                    endDate = dateFormat.parse(end);
                    filledFieldCount++;
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }

            Integer personNumber = null;

            if (!personNumberText.isEmpty()) {
                try {
                    personNumber = Integer.parseInt(personNumberText);
                    filledFieldCount++;
                } catch (NumberFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (filledFieldCount > 0) {
                java.sql.Date sqlStartDate = (startDate != null) ? new java.sql.Date(startDate.getTime()) : null;
                java.sql.Date sqlEndDate = (endDate != null) ? new java.sql.Date(endDate.getTime()) : null;

                if (personNumber == null) {
                    personNumber = 0; // personNumber null ise 0 olarak varsayalım
                }

                String query = Room.searchQuery(place, place, place, startDate, endDate, personNumber);
                ArrayList<Term> filter = Term.searchTermList(query);
                loadPriceTableModel(filter);

            }
            if (fld_hotel_name.getText().isEmpty() && fld_guest_info.getText().isEmpty() && fld_start_date.getText().isEmpty() && fld_end_date.getText().isEmpty()){
                loadPriceTableModel();
            }
        });

        btn_refresh.addActionListener(e -> {
            loadPriceTableModel();
            loadReservationModel();
            loadHotelModel();

        });
        mdl_reservation_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_reservation_list = {"ID", "Otel Adı","Adı ve Soyadı","Telefon NO","E-mail Adresi","Misafir Sayısı","Rezervasyon Başlangıç Tarihi","Rezervasyon Bitiş Tarihi","Fiyat"};
        mdl_reservation_list.setColumnIdentifiers(col_reservation_list);
        row_reservation_list = new Object[col_reservation_list.length];
        loadReservationModel();
        tbl_reservation_list.setModel(mdl_reservation_list);
        tbl_reservation_list.setComponentPopupMenu(reservationMenu);
        tbl_reservation_list.getTableHeader().setReorderingAllowed(false);

        tbl_reservation_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Sağ tıklama gerçekleşmesi
                if (e.getButton() == MouseEvent.BUTTON3) {


                    int row = e.getPoint().y / tbl_reservation_list.getRowHeight();
                    tbl_reservation_list.setRowSelectionInterval(row,row);

                    //reservation ID ' yi tıklanan yere atıyorum
                    int reservationID = (int) tbl_reservation_list.getValueAt(row, 0);

                    JPopupMenu reservationMenu = new JPopupMenu();
                    JMenuItem deleteReservation = new JMenuItem("Rezervasyonu Sil");


                    deleteReservation.addActionListener(event4 -> {
                        Reservation.deleteReservation(reservationID);
                        loadReservationModel();
                        loadPriceTableModel();

                        reservationMenu.setVisible(false);

                        Helper.showMsg("Rezervasyon başarıyla silindi.");
                    });

                    reservationMenu.add(deleteReservation);
                    reservationMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    private void loadReservationModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_reservation_list.getModel();
        clearModel.setRowCount(0);


        for (Reservation obj : Reservation.getListReservation()) {
            int i = 0;
            row_reservation_list[i++] = obj.getReservationID();
            Term term = Term.getTermBYTermID(obj.getTermID());
            Hotel hotel = Hotel.getFetch(term.getHotelID());
            row_reservation_list[i++] = hotel.getName();
            row_reservation_list[i++] = obj.getCustomerName();
            row_reservation_list[i++] = obj.getCustomerPhone();
            row_reservation_list[i++] = obj.getCustomerMail();
            row_reservation_list[i++] = obj.getCustomerNumber();
            row_reservation_list[i++] = obj.getReservationStart();
            row_reservation_list[i++] = obj.getReservationEnd();
            row_reservation_list[i++] = obj.getCustomerPrice();
            mdl_reservation_list.addRow(row_reservation_list);

        }
    }


    private void createAddHotelRightClick(MouseEvent e) {
        JPopupMenu hotelPanelMenu = new JPopupMenu();
        JMenuItem addMenuItem = new JMenuItem("Otel Ekle");
        addMenuItem.addActionListener(event3 ->{
            createAddHotelGUIPopup();
            dispose();
        });
        hotelPanelMenu.add(addMenuItem);
        hotelPanelMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    private void createAddHotelGUIPopup() {
        AddHotelGUI addHotelGUI = new AddHotelGUI(0,false,user);


    }
    public void loadRoomModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);

        for (Room obj : Room.getList()) {
            int i = 0;
            row_room_list[i++] = obj.getRoomID();
            Hotel hotel = Hotel.getFetch(obj.getHotelID());
            row_room_list[i++] = hotel.getName();
            row_room_list[i++] = obj.getRoomType();
            row_room_list[i++] = obj.getRoomStock();
            row_room_list[i++] = obj.getRoomFeature();
            row_room_list[i++] = obj.getRoomSize();
            row_room_list[i++] = obj.getRoomBedCount();
            mdl_room_list.addRow(row_room_list);
        }

    }

    public void loadPriceTableModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);


        for (Term obj : Term.getListTerm()) {
            Room room =Room.getRoom(obj.getRoomID());
            if (room.getRoomStock()>0){
                int i = 0;
                row_room_list[i++] = obj.getTermId();
                row_room_list[i++] = room.getRoomID();
                Hotel hotel = Hotel.getFetch(obj.getHotelID());
                row_room_list[i++] = hotel.getName();
                row_room_list[i++] = hotel.getCity();
                row_room_list[i++] = hotel.getDistrict();
                row_room_list[i++] = room.getRoomType();
                row_room_list[i++] = obj.getRoomBoardType();
                row_room_list[i++] = obj.getTermStartDate();
                row_room_list[i++] = obj.getTermEndDate();
                row_room_list[i++] = room.getRoomStock();
                row_room_list[i++] = room.getRoomFeature();
                row_room_list[i++] = room.getRoomSize();
                row_room_list[i++] = room.getRoomBedCount();
                row_room_list[i++] = obj.getAdultPrice();
                row_room_list[i++] = obj.getChildrenPrice();
                mdl_room_list.addRow(row_room_list);

            }

        }

    }
    public void loadPriceTableModel(ArrayList<Term> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);


        for (Term obj : list) {
            Room room =Room.getRoom(obj.getRoomID());
            if (room.getRoomStock()>0){
                int i = 0;
                row_room_list[i++] = obj.getTermId();
                row_room_list[i++] = room.getRoomID();
                Hotel hotel = Hotel.getFetch(obj.getHotelID());
                row_room_list[i++] = hotel.getName();
                row_room_list[i++] = hotel.getCity();
                row_room_list[i++] = hotel.getDistrict();
                row_room_list[i++] = room.getRoomType();
                row_room_list[i++] = obj.getRoomBoardType();
                row_room_list[i++] = obj.getTermStartDate();
                row_room_list[i++] = obj.getTermEndDate();
                row_room_list[i++] = room.getRoomStock();
                row_room_list[i++] = room.getRoomFeature();
                row_room_list[i++] = room.getRoomSize();
                row_room_list[i++] = room.getRoomBedCount();
                row_room_list[i++] = obj.getAdultPrice();
                row_room_list[i++] = obj.getChildrenPrice();
                mdl_room_list.addRow(row_room_list);

            }
        }
    }

    public void loadHotelModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);

        for (Hotel obj : Hotel.getList()) {
            int i = 0;
            row_hotel_list[i++] = obj.getId();
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getCity();
            row_hotel_list[i++] = obj.getDistrict();
            row_hotel_list[i++] = obj.getHotelFeatures();
            row_hotel_list[i++] = obj.getAddress();
            row_hotel_list[i++] = obj.getMail();
            row_hotel_list[i++] = obj.getPhone();
            row_hotel_list[i++] = obj.getStar();
            mdl_hotel_list.addRow(row_hotel_list);
        }

    }
}
