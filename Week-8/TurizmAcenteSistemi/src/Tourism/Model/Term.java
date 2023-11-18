package Tourism.Model;

import Tourism.Helper.DBConnector;
import Tourism.Helper.Helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Term {
    private int termId;
    private String termName;
    private int hotelID;
    private int roomID;
    private String roomBoardType;
    private Date termStartDate;
    private Date termEndDate;
    private int adultPrice;
    private int childrenPrice;

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public Date getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(Date termStartDate) {
        this.termStartDate = termStartDate;
    }

    public Date getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(Date termEndDate) {
        this.termEndDate = termEndDate;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public int getChildrenPrice() {
        return childrenPrice;
    }

    public void setChildrenPrice(int childrenPrice) {
        this.childrenPrice = childrenPrice;
    }

    public String getRoomBoardType() {
        return roomBoardType;
    }

    public void setRoomBoardType(String roomBoardType) {
        this.roomBoardType = roomBoardType;
    }

    public Term() {
    }


    public static Term getTerm(int id) {
        Term obj = null;
        String query = "SELECT * FROM term WHERE term_id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                obj = new Term();
                obj.setTermId(rs.getInt("term_id"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setRoomID(rs.getInt("room_id"));
                obj.setRoomBoardType(rs.getString("room_board_type"));
                obj.setTermStartDate(rs.getDate("term_start_date"));
                obj.setTermEndDate(rs.getDate("term_end_date"));
                obj.setAdultPrice(rs.getInt("adult_price"));
                obj.setChildrenPrice(rs.getInt("children_price"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;

    }
    public static Term getTermByRoomID(int roomID) {
        Term obj = null;
        String query = "SELECT * FROM term WHERE room_id = ?";
        Hotel hotel = Hotel.getHotelByRoomID(roomID);
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, roomID);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                obj = new Term();
                obj.setTermId(rs.getInt("term_id"));
                hotel.setId(rs.getInt("hotel_id"));
                obj.setRoomID(rs.getInt("room_id"));
                obj.setRoomBoardType(rs.getString("room_board_type"));
                obj.setTermStartDate(rs.getDate("term_start_date"));
                obj.setTermEndDate(rs.getDate("term_end_date"));
                obj.setAdultPrice(rs.getInt("adult_price"));
                obj.setChildrenPrice(rs.getInt("children_price"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;

    }

    public static boolean deleteTerm(int id){
        ArrayList<Reservation> reservationList = Reservation.getReservationListBYTermID(id);

        for (Reservation r : reservationList){
            Reservation.deleteReservation(r.getReservationID());
        }
        Connection connection = DBConnector.getInstance();
        String sql = "DELETE FROM term WHERE term_id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }

    public static Term getTermBYTermID(int termId) {
        Term obj = null;
        String query = "SELECT * FROM term WHERE term_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, termId);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                obj = new Term();
                obj.setTermId(rs.getInt("term_id"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setRoomID(rs.getInt("room_id"));
                obj.setRoomBoardType(rs.getString("room_board_type"));
                obj.setTermStartDate(rs.getDate("term_start_date"));
                obj.setTermEndDate(rs.getDate("term_end_date"));
                obj.setAdultPrice(rs.getInt("adult_price"));
                obj.setChildrenPrice(rs.getInt("children_price"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;

    }

    public static int[] getRoomAndHotelID(int roomID) {


        int[] ids = new int[2]; // room_id ve hotel_id için dizi
        String query = "SELECT room_id, hotel_id FROM room WHERE room_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, roomID);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                ids[0] = rs.getInt("room_id");
                ids[1] = rs.getInt("hotel_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Hata işleme kodu ekleyin
        }

        return ids;
    }

    public static boolean addTermToDatabase(String boardType, Date startDate, Date endDate, double adultPrice, double childPrice, int selectedRoomID) {
        int[] roomAndHotelID = getRoomAndHotelID(selectedRoomID);
        int roomID = roomAndHotelID[0];
        int hotelID = roomAndHotelID[1];

        Connection connection = null;
        PreparedStatement pr = null;
        String insertSQL = "INSERT INTO term (hotel_id, room_id, room_board_type, term_start_date, term_end_date, adult_price, children_price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = DBConnector.getInstance();

            pr = connection.prepareStatement(insertSQL);

            pr.setInt(1, hotelID);
            pr.setInt(2, roomID);
            pr.setString(3,  boardType);
            pr.setDate(4,  (java.sql.Date) startDate);
            pr.setDate(5,  (java.sql.Date) endDate);
            pr.setDouble(6, adultPrice);
            pr.setDouble(7, childPrice);
            int rowsAffected = pr.executeUpdate();

            return rowsAffected > 0; // Eklemenin başarılı olup olmadığını döndür

        } catch (SQLException ex) {
            ex.printStackTrace();
            // Hata işleme kodu ekleyin
            return false;
        }
    }
    public static boolean updateTermInDatabase(int termID, String boardType, Date startDate, Date endDate, double adultPrice, double childPrice) {
        Connection connection = null;
        PreparedStatement pr = null;

        String updateSQL = "UPDATE term SET room_board_type = ?, term_start_date = ?, term_end_date = ?, adult_price = ?, children_price = ? WHERE term_id = ?";

        try {
            connection = DBConnector.getInstance();
            pr = connection.prepareStatement(updateSQL);

            pr.setString(1, boardType);
            pr.setDate(2, new java.sql.Date(startDate.getTime()));
            pr.setDate(3, new java.sql.Date(endDate.getTime()));
            pr.setDouble(4, adultPrice);
            pr.setDouble(5, childPrice);
            pr.setInt(6, termID);

            int rowsAffected = pr.executeUpdate();

            return rowsAffected > 0; // Return true if the update was successful

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (pr != null) {
                    pr.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public static ArrayList<Term> getListTerm() {
        ArrayList<Term> termList = new ArrayList<>();
        String query = "SELECT * FROM term";
        Term obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                obj = new Term();
                obj.setTermId(rs.getInt("term_id"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setRoomID(rs.getInt("room_id"));
                obj.setRoomBoardType(rs.getString("room_board_type"));
                obj.setTermStartDate(rs.getDate("term_start_date"));
                obj.setTermEndDate(rs.getDate("term_end_date"));
                obj.setAdultPrice(rs.getInt("adult_price"));
                obj.setChildrenPrice(rs.getInt("children_price"));


                termList.add(obj);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return termList;
    }

    public static ArrayList<Term> getTermList(int room, int hotel) {
        ArrayList<Term> termList = new ArrayList<>();
        String query = "SELECT * FROM term WHERE room_id = "+room+" and hotel_id = "+hotel;
        Term obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                obj = new Term();
                obj.setTermId(rs.getInt("term_id"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setRoomID(rs.getInt("room_id"));
                obj.setRoomBoardType(rs.getString("room_board_type"));
                obj.setTermStartDate(rs.getDate("term_start_date"));
                obj.setTermEndDate(rs.getDate("term_end_date"));
                obj.setAdultPrice(rs.getInt("adult_price"));
                obj.setChildrenPrice(rs.getInt("children_price"));
                termList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return termList;
    }
    public static ArrayList<Term> getTermListByRoomID(int room) {
        ArrayList<Term> termList = new ArrayList<>();
        String query = "SELECT * FROM term WHERE room_id = "+room;
        Term obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                obj = new Term();
                obj.setTermId(rs.getInt("term_id"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setRoomID(rs.getInt("room_id"));
                obj.setRoomBoardType(rs.getString("room_board_type"));
                obj.setTermStartDate(rs.getDate("term_start_date"));
                obj.setTermEndDate(rs.getDate("term_end_date"));
                obj.setAdultPrice(rs.getInt("adult_price"));
                obj.setChildrenPrice(rs.getInt("children_price"));
                termList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return termList;
    }

    public static ArrayList<Term> searchTermList(String query) {
        ArrayList<Term> termList = new ArrayList<>();
        Term obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                obj = new Term();
                obj.setTermId(rs.getInt("term_id"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setRoomID(rs.getInt("room_id"));
                obj.setRoomBoardType(rs.getString("room_board_type"));
                obj.setTermStartDate(rs.getDate("term_start_date"));
                obj.setTermEndDate(rs.getDate("term_end_date"));
                obj.setAdultPrice(rs.getInt("adult_price"));
                obj.setChildrenPrice(rs.getInt("children_price"));
                termList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return termList;
    }

}
