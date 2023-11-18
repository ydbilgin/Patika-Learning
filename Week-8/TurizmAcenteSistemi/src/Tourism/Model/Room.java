package Tourism.Model;

import Tourism.Helper.DBConnector;
import Tourism.Helper.Helper;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Room {
    private int roomID;
    private int hotelID;

    private int roomStock;

    private String roomType;
    private String roomFeature;
    private int roomSize;
    private int roomBedCount;


    public Room(int roomID, int hotelID, int roomStock, String roomType, String roomFeature, int roomSize, int roomBedCount) {
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.roomStock = roomStock;
        this.roomType = roomType;
        this.roomFeature = roomFeature;
        this.roomSize = roomSize;
        this.roomBedCount = roomBedCount;

    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }



    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomFeature() {
        return roomFeature;
    }

    public void setRoomFeature(String roomFeature) {
        this.roomFeature = roomFeature;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    public int getRoomBedCount() {
        return roomBedCount;
    }

    public void setRoomBedCount(int roomBedCount) {
        this.roomBedCount = roomBedCount;
    }

    public int getRoomStock() {
        return roomStock;
    }

    public void setRoomStock(int roomStock) {
        this.roomStock = roomStock;
    }

    public Room() {

    }


    public static ArrayList<Room> getList() {
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM room";
        Room obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                obj = new Room();
                obj.setRoomID(rs.getInt("room_id"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setRoomStock(rs.getInt("room_stock"));
                obj.setRoomFeature(rs.getString("room_feature"));
                obj.setRoomSize(rs.getInt("room_size"));
                obj.setRoomBedCount(rs.getInt("room_bed_count"));


                roomList.add(obj);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public static boolean deleteRoom(int id){
        ArrayList<Term> termList = Term.getTermListByRoomID(id);

        for (Term t : termList){
            Term.deleteTerm(t.getTermId());
        }
        Connection connection = DBConnector.getInstance();
        String sql = "DELETE FROM room WHERE room_id = ?";
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



    public static String searchQuery(String city, String district, String hotelName, Date checkinDate, Date checkoutDate, int bedCount) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String formattedCheckinDate = (checkinDate != null) ? dateFormat.format(checkinDate) : "";
        String formattedCheckoutDate = (checkoutDate != null) ? dateFormat.format(checkoutDate) : "";


        String query = "SELECT r.room_id, r.room_type, r.room_stock, r.hotel_id, r.room_feature, t.term_start_date, t.term_end_date, r.room_bed_count, r.room_size,t.term_id,t.room_board_type,t.adult_price,t.children_price " +
                "FROM Room r " +
                "JOIN Term t ON r.room_id = t.room_id " +
                "JOIN Hotel h ON r.hotel_id = h.hotel_id " +
                "WHERE (h.hotel_city LIKE '%{{city}}%' OR h.hotel_district LIKE '%{{district}}%' OR h.hotel_name LIKE '%{{hotelName}}%')";

        query = query.replace("{{city}}", city);
        query = query.replace("{{district}}", district);
        query = query.replace("{{hotelName}}", hotelName);

        if (bedCount > 0) {
            query += " AND r.room_bed_count >= " + bedCount;
        }

        if (!formattedCheckinDate.isEmpty() && !formattedCheckoutDate.isEmpty()) {
            // Giriş tarihi çıkış tarihinden küçük veya eşit ve çıkış tarihi giriş tarihinden büyük veya eşitse
            query += " AND (t.term_start_date <= '{{checkinDate}}' AND t.term_end_date >= '{{checkoutDate}}')";
            query = query.replace("{{checkinDate}}", formattedCheckinDate);
            query = query.replace("{{checkoutDate}}", formattedCheckoutDate);
        } else if (!formattedCheckinDate.isEmpty()) {
            // Sadece giriş tarihi verildiyse ve tarih aralığı kontrolü ekleyin
            query += " AND (t.term_start_date <= '{{checkinDate}}')";
            query = query.replace("{{checkinDate}}", formattedCheckinDate);
        } else if (!formattedCheckoutDate.isEmpty()) {
            // Sadece çıkış tarihi verildiyse ve tarih aralığı kontrolü ekleyin
            query += " AND (t.term_end_date >= '{{checkoutDate}}')";
            query = query.replace("{{checkoutDate}}", formattedCheckoutDate);
        }

        return query;
    }
    public static boolean add(int hotelID,String roomType, int roomStock, String roomFeature,int roomSize,int roomBedCount) {
        String query = "INSERT INTO room (hotel_id,room_type,room_stock,room_feature,room_size,room_bed_count) VALUES (?,?,?,?,?,?)";
        Room room = Room.getRoomBYHotelAndRoomType(hotelID,roomType);
        if (room != null){
            Helper.showMsg("Bu otele ait bu tipte bir oda bulunmaktadır. Lütfen varolan odayı güncelleyiniz!");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotelID);
            pr.setString(2, roomType);
            pr.setInt(3, roomStock);
            pr.setString(4, roomFeature);
            pr.setInt(5, roomSize);
            pr.setInt(6, roomBedCount);
            int response = pr.executeUpdate();

            if (response == -1) {
                Helper.showMsg("error");
            }

            return response != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;

    }
    public static boolean updateRoom(int roomID,String roomType, int roomStock, String roomFeature, int roomSize, int roomBedCount) {
        String query = "UPDATE room SET room_type = ?,room_stock = ?, room_feature = ?, room_size = ?, room_bed_count = ?  WHERE room_id = ?";
        Room r = Room.getRoom(roomID);
        Room room = Room.getRoomBYHotelAndRoomType(r.getHotelID(),roomType);
        if (room != null){
            Helper.showMsg("Bu otele ait bu tipte bir oda bulunmaktadır. Lütfen varolan odayı güncelleyiniz!");
            return false;
        }

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, roomType);
            pr.setInt(2, roomStock);
            pr.setString(3, roomFeature);
            pr.setInt(4, roomSize);
            pr.setInt(5, roomBedCount);
            pr.setInt(6, roomID);


            int response = pr.executeUpdate();
            if (response == 1) {
                return true;
            } else {
                Helper.showMsg("Oda güncelleme sırasında bir hata oluştu.");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean updateRoomForRoomStock(int roomID, int roomStock, String roomFeature, int roomSize, int roomBedCount) {
        String query = "UPDATE room SET room_stock = ?, room_feature = ?, room_size = ?, room_bed_count = ?  WHERE room_id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, roomStock);
            pr.setString(2, roomFeature);
            pr.setInt(3, roomSize);
            pr.setInt(4, roomBedCount);
            pr.setInt(5, roomID);


            int response = pr.executeUpdate();
            if (response == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public static ArrayList<Room> searchRoomList(String query) {
        ArrayList<Room> roomList = new ArrayList<>();
        Room obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                obj = new Room();
                obj.setRoomID(rs.getInt("room_id"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setRoomStock(rs.getInt("room_stock"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setRoomFeature(rs.getString("room_feature"));
                obj.setRoomBedCount(rs.getInt("room_bed_count"));
                obj.setRoomSize(rs.getInt("room_size"));
                roomList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }



    public static Room getRoom(int id) {
        Room obj = null;
        String query = "SELECT * FROM room WHERE room_id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                obj = new Room();
                obj.setRoomID(rs.getInt("room_id"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setRoomStock(rs.getInt("room_stock"));
                obj.setRoomFeature(rs.getString("room_feature"));
                obj.setRoomSize(rs.getInt("room_size"));
                obj.setRoomBedCount(rs.getInt("room_bed_count"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;

    }
    public static Room getRoomBYHotelAndRoomType(int hotel_id, String room_type) {
        Room obj = null;
        String query = "SELECT * FROM room WHERE hotel_id = ? and room_type = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setString(2, room_type);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                obj = new Room();
                obj.setRoomID(rs.getInt("room_id"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setRoomStock(rs.getInt("room_stock"));
                obj.setRoomFeature(rs.getString("room_feature"));
                obj.setRoomSize(rs.getInt("room_size"));
                obj.setRoomBedCount(rs.getInt("room_bed_count"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;

    }
    public static Room getRoomByRoomIDandRoomType(int room_id, String room_type) {
        Room obj = null;
        String query = "SELECT * FROM room WHERE room_id = ? and room_type = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, room_id);
            pr.setString(2, room_type);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                obj = new Room();
                obj.setRoomID(rs.getInt("room_id"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setRoomStock(rs.getInt("room_stock"));
                obj.setRoomFeature(rs.getString("room_feature"));
                obj.setRoomSize(rs.getInt("room_size"));
                obj.setRoomBedCount(rs.getInt("room_bed_count"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;

    }

    public static ArrayList<Room> getListRoomBYHotelID(int id) {
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM room WHERE hotel_id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Room obj = new Room();
                obj.setRoomID(rs.getInt("room_id"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setRoomStock(rs.getInt("room_stock"));
                obj.setRoomFeature(rs.getString("room_feature"));
                obj.setRoomSize(rs.getInt("room_size"));
                obj.setRoomBedCount(rs.getInt("room_bed_count"));
                roomList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomList;
    }

}
