package Tourism.Model;

import Tourism.Helper.DBConnector;
import Tourism.Helper.Helper;

import java.sql.*;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private String city;
    private String district;
    private String hotelFeatures;
    private String address;
    private String phone;
    private String mail;
    private int star;


    public Hotel() {
    }

    public Hotel(int id, String name,String city,String district,String hotelFeatures, String address, String phone, String mail, int star) {
        this.name = name;
        this.city = city;
        this.district = district;
        this.hotelFeatures = hotelFeatures;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.id = id;
        this.star = star;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getHotelFeatures() {
        return hotelFeatures;
    }

    public void setHotelFeatures(String hotelFeatures) {
        this.hotelFeatures = hotelFeatures;
    }

    public static ArrayList<Hotel> getList() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotel";
        Hotel obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                obj = new Hotel();
                obj.setId(rs.getInt("hotel_id"));
                obj.setName(rs.getString("hotel_name"));
                obj.setCity(rs.getString("hotel_city"));
                obj.setDistrict(rs.getString("hotel_district"));
                obj.setHotelFeatures(rs.getString("hotel_features"));
                obj.setAddress(rs.getString("hotel_address"));
                obj.setMail(rs.getString("hotel_mail"));
                obj.setPhone(rs.getString("hotel_phone"));
                obj.setStar(rs.getInt("hotel_star"));

                hotelList.add(obj);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }
    public static boolean add(String name, String city, String district,String hotelFeatures , String address, String mail, String phone, Integer star) {
        String query = "INSERT INTO hotel (hotel_name, hotel_city, hotel_district , hotel_features, hotel_address, hotel_mail, hotel_phone, hotel_star) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Hotel findHotel = getFetch(mail);
        if (findHotel != null) {
            Helper.showMsg("Bu otel daha önce eklenmiş.");
            return false;
        }

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, city);
            pr.setString(3, district);
            pr.setString(4, hotelFeatures);
            pr.setString(5, address);
            pr.setString(6, mail);
            pr.setString(7, phone);
            pr.setString(8, String.valueOf(star));

            int response = pr.executeUpdate();
            if (response == -1) {
                Helper.showMsg("error");
            }

            return response != -1;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public static Hotel getHotelByRoomID(int room_id) {
        Hotel obj = null;
        String query = "SELECT hotel_id FROM hotel WHERE hotel_id IN (SELECT hotel_id FROM room WHERE room_id = ?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, room_id);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                obj = new Hotel();
                obj.setId(rs.getInt("hotel_id"));
                obj.setName(rs.getString("hotel_name"));
                obj.setCity(rs.getString("hotel_city"));
                obj.setDistrict(rs.getString("hotel_district"));
                obj.setHotelFeatures(rs.getString("hotel_features"));
                obj.setAddress(rs.getString("hotel_address"));
                obj.setMail(rs.getString("hotel_mail"));
                obj.setPhone(rs.getString("hotel_phone"));
                obj.setStar(rs.getInt("hotel_star"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;

    }



    public static boolean updateHotelById(int hotelId, String name, String city, String district, String hotelFeatures, String address, String mail, String phone, Integer star) {
        String query = "UPDATE hotel SET hotel_name = ?, hotel_city = ?, hotel_district = ?, hotel_features = ?, hotel_address = ?, hotel_mail = ?, hotel_phone = ?, hotel_star = ? WHERE hotel_id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, city);
            pr.setString(3, district);
            pr.setString(4, hotelFeatures);
            pr.setString(5, address);
            pr.setString(6, mail);
            pr.setString(7, phone);
            pr.setString(8, String.valueOf(star));
            pr.setInt(9, hotelId); // Set the ninth parameter

            int response = pr.executeUpdate();
            if (response == 1) {
                Helper.showMsg("Otel başarıyla güncellendi.");
                return true;
            } else {
                Helper.showMsg("Otel güncelleme sırasında bir hata oluştu.");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Hotel getFetch(String mail) {
        Hotel obj = null;
        String query = "SELECT * FROM hotel WHERE hotel_mail = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, mail);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                obj = new Hotel();
                obj.setId(rs.getInt("hotel_id"));
                obj.setName(rs.getString("hotel_name"));
                obj.setCity(rs.getString("hotel_city"));
                obj.setDistrict(rs.getString("hotel_district"));
                obj.setHotelFeatures(rs.getString("hotel_features"));
                obj.setAddress(rs.getString("hotel_address"));
                obj.setMail(rs.getString("hotel_mail"));
                obj.setPhone(rs.getString("hotel_phone"));
                obj.setStar(rs.getInt("hotel_star"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;

    }
    public static boolean deleteHotel(int id){
        ArrayList<Room> roomList = Room.getListRoomBYHotelID(id);

        for (Room r : roomList){
            Room.deleteRoom(r.getRoomID());
        }
        Connection connection = DBConnector.getInstance();
        String sql = "DELETE FROM hotel WHERE hotel_id = ?";
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

    public static Hotel getFetch(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM hotel WHERE hotel_id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                obj = new Hotel();
                obj.setId(rs.getInt("hotel_id"));
                obj.setName(rs.getString("hotel_name"));
                obj.setCity(rs.getString("hotel_city"));
                obj.setDistrict(rs.getString("hotel_district"));
                obj.setHotelFeatures(rs.getString("hotel_features"));
                obj.setAddress(rs.getString("hotel_address"));
                obj.setMail(rs.getString("hotel_mail"));
                obj.setPhone(rs.getString("hotel_phone"));
                obj.setStar(rs.getInt("hotel_star"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;

    }

}