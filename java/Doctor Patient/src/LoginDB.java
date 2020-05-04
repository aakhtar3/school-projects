import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LoginDB {
	private Connection getConnection() {
        Connection connection = null;
        try {
        	String driverName = "com.mysql.jdbc.Driver"; // Driver Name depended on the DB
        	Class.forName(driverName);
            String url = "jdbc:mysql://localhost/test"; // url depended on the DB
            String username = "root"; // Username
            String password = ""; // Userpasword
            connection = DriverManager.getConnection(url, username, password);
			return connection;
        }
        catch(Exception e) {
            System.err.println(e);
			return null;
        }
    }
	public ArrayList<Login> getDoctors() {
        String sql = "SELECT doctor_id, doctor_name, password "
                   + "FROM doctors";        
        ArrayList<Login> logins = new ArrayList<>();
        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
        	while(rs.next()) {
        		int id = rs.getInt("doctor_id");
                String name = rs.getString("doctor_name");
                String pass = rs.getString("password");

                Login l = new Login(id, name, pass);
                logins.add(l);
            }
            connection.close();
            return logins;
        }
        catch(SQLException e) {
            System.err.println(e);
            return null;
        }
    }
	public ArrayList<Login> getPatients() {
        String sql = "SELECT patient_id, user_name, password "
                   + "FROM patients";        
        ArrayList<Login> logins = new ArrayList<>();
        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
        	while(rs.next()) {
        		int id = rs.getInt("patient_id");
                String name = rs.getString("user_name");
                String pass = rs.getString("password");

                Login l = new Login(id, name, pass);
                logins.add(l);
            }
            connection.close();
            return logins;
        }
        catch(SQLException e) {
            System.err.println(e);
            return null;
        }
    }
}
