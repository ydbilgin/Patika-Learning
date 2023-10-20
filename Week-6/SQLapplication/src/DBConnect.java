import java.sql.*;

public class DBConnect {
    public static final String DB_URL = "jdbc:mysql://localhost/employee";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "mysql";
    public static void main(String[] args) {
        Connection connect = null;
        String sql = "SELECT * FROM employee";
        try {
            connect = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            Statement st = connect.createStatement();
            ResultSet data = st.executeQuery(sql);
            System.out.println("Employee List");

            System.out.println();

            while (data.next()){
                System.out.println("ID : "+ data.getInt("employee_id"));
                System.out.println("Name : " + data.getString("employee_name"));
                System.out.println("Position : " + data.getString("employee_position"));
                System.out.println("Salary : " + data.getDouble("employee_salary"));
                System.out.println("#############################");
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());

        }

    }
}
