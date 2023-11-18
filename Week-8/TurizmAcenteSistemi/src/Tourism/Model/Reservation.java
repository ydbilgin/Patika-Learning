package Tourism.Model;

import Tourism.Helper.DBConnector;
import Tourism.Helper.Helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Reservation {
    private int reservationID;
    private String customerName;
    private String customerPhone;
    private String customerMail;
    private int customerNumber;
    private Date reservationStart;
    private Date reservationEnd;
    private double customerPrice;
    private int termID;

    public Reservation(int reservationID, String customerName, String customerPhone, String customerMail, int customerNumber,Date reservationStart,Date reservationEnd, double customerPrice, int termID) {
        this.reservationID = reservationID;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerMail = customerMail;
        this.customerNumber = customerNumber;
        this.reservationStart = reservationStart;
        this.reservationEnd = reservationEnd;
        this.customerPrice = customerPrice;
        this.termID = termID;
    }

    public Reservation(){

    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public double getCustomerPrice() {
        return customerPrice;
    }

    public void setCustomerPrice(double customerPrice) {
        this.customerPrice = customerPrice;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public Date getReservationStart() {
        return reservationStart;
    }

    public void setReservationStart(Date reservationStart) {
        this.reservationStart = reservationStart;
    }

    public Date getReservationEnd() {
        return reservationEnd;
    }

    public void setReservationEnd(Date reservationEnd) {
        this.reservationEnd = reservationEnd;
    }

    public static boolean add(String customerName, String customerPhone, String customerMail, int customerNumber, Date reservationStart, Date reservationEnd, double customerPrice, int termID) {
        String query = "INSERT INTO reservation (customer_name, customer_phone, customer_mail, customer_number, reservation_start_date, reservation_end_date, customer_price, term_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Term term = Term.getTermBYTermID(termID);
        Room room = Room.getRoom(term.getRoomID());

        if (room.getRoomStock() < 1) {
            Helper.showMsg("error");
            return false; // Eğer oda stokta yoksa işlemi devam ettirme
        }
        if (customerNumber> room.getRoomBedCount()){
            Helper.showMsg("Rezervasyon yaptıran kişi sayısı odanın yatak sayısından büyük olamaz!");
            return false;
        }
        if (reservationStart.after(reservationEnd)){
            Helper.showMsg("Çıkış tarihi giriş tarihinden önce olamaz!");
            return false;
        }
        if (reservationStart.before(term.getTermStartDate()) || reservationEnd.after(term.getTermEndDate())){
            Helper.showMsg("Girdiğiniz tarihler dönem tarihleri arasında olmalıdır!");
            return false;
        }


        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, customerName);
            pr.setString(2, customerPhone);
            pr.setString(3, customerMail);
            pr.setInt(4, customerNumber);
            pr.setDate(5, (java.sql.Date) reservationStart);
            pr.setDate(6, (java.sql.Date) reservationEnd);
            pr.setDouble(7, customerPrice);
            pr.setInt(8, termID);
            int response = pr.executeUpdate();

            if (response == -1) {
                Helper.showMsg("error");
                return false;
            }


            //Oda stoğunu rezervasyon eklenirse 1 azaltıyor.
            Room.updateRoomForRoomStock(room.getRoomID(), room.getRoomStock()-1,room.getRoomFeature(),room.getRoomSize(),room.getRoomBedCount() );

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static Reservation getReservationByReservationID(int reservationID) {
        Reservation obj = null;
        String query = "SELECT * FROM reservation WHERE reservation_id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, reservationID);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                obj = new Reservation();
                obj.setReservationID(rs.getInt("reservation_id"));
                obj.setCustomerName(rs.getString("customer_name"));
                obj.setCustomerPhone(rs.getString("customer_phone"));
                obj.setCustomerMail(rs.getString("customer_mail"));
                obj.setCustomerNumber(rs.getInt("customer_number"));
                obj.setReservationStart(rs.getDate("reservation_start_date"));
                obj.setReservationEnd(rs.getDate("reservation_end_date"));
                obj.setCustomerPrice(rs.getDouble("customer_price"));
                obj.setTermID(rs.getInt("term_id"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;

    }

    public static boolean deleteReservation(int id){
        Reservation reservation = Reservation.getReservationByReservationID(id);
        Term term = Term.getTermBYTermID(reservation.getTermID());
        Room room = Room.getRoom(term.getRoomID());

        Connection connection = DBConnector.getInstance();
        String sql = "DELETE FROM reservation WHERE reservation_id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        //Oda stoğunu rezervasyon silinirse 1 artırıyor.
        Room.updateRoomForRoomStock(room.getRoomID(), room.getRoomStock()+1,room.getRoomFeature(),room.getRoomSize(),room.getRoomBedCount() );


        return true;

    }
    public static ArrayList<Reservation> getReservationListBYTermID(int id) {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM reservation WHERE term_id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Reservation obj = new Reservation();
                obj.setReservationID(rs.getInt("reservation_id"));
                obj.setCustomerName(rs.getString("customer_name"));
                obj.setCustomerPhone(rs.getString("customer_phone"));
                obj.setCustomerMail(rs.getString("customer_mail"));
                obj.setCustomerNumber(rs.getInt("customer_number"));
                obj.setReservationStart(rs.getDate("reservation_start_date"));
                obj.setReservationEnd(rs.getDate("reservation_end_date"));
                obj.setCustomerPrice(rs.getDouble("customer_price"));
                obj.setTermID(rs.getInt("term_id"));
                reservationList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservationList;
    }

    public static ArrayList<Reservation> getListReservation() {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM reservation";
        Reservation obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                obj = new Reservation();
                obj.setReservationID(rs.getInt("reservation_id"));
                obj.setCustomerName(rs.getString("customer_name"));
                obj.setCustomerPhone(rs.getString("customer_phone"));
                obj.setCustomerMail(rs.getString("customer_mail"));
                obj.setCustomerNumber(rs.getInt("customer_number"));
                obj.setReservationStart(rs.getDate("reservation_start_date"));
                obj.setReservationEnd(rs.getDate("reservation_end_date"));
                obj.setCustomerPrice(rs.getDouble("customer_price"));
                obj.setTermID(rs.getInt("term_id"));


                reservationList.add(obj);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }


}
