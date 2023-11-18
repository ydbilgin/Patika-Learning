package Tourism.Dao;

import Tourism.Helper.DBConnector;
import Tourism.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private Connection con;

    public UserDao() {
        this.con = DBConnector.getInstance();
    }

    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM turizm.user";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User findByLogin(String username, String password) {
        User obj = null;
        String query = "SELECT * FROM turizm.user WHERE user_uname = ? AND user_pass = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public User match(ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setId(rs.getInt("user_id"));
        obj.setName(rs.getString("user_name"));
        obj.setUname(rs.getString("user_uname"));
        obj.setPass(rs.getString("user_pass"));
        obj.setType(rs.getString("user_type"));

        return obj;

    }
}
