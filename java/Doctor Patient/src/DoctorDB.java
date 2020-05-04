import java.util.*;
import java.sql.*;
public class DoctorDB {
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

	public ArrayList<Doctor> getPatients(int id) {       
		ArrayList<Doctor> doctors = new ArrayList<>();
 
				Doctor doctor = new Doctor(id);
	
        String sql1 = "SELECT name, address, phone_number, current_diagnoses, current_prescription "
        		+ "FROM patients, doctors"
        		+ "where patients.current_doctor = doctors.doctor_id and doctors.doctor_id = " + id;        
        
        try (Connection connection = getConnection();
                PreparedStatement ps1 = connection.prepareStatement(sql1);
                ResultSet rs1 = ps1.executeQuery()) {
           	while(rs1.next()) {
           		String name = rs1.getString("name");
                String address = rs1.getString("address");
                String phone = rs1.getString("phone_number");
                String diag = rs1.getString("current_diagnoses");
                String presc = rs1.getString("current_prescription");

                   doctor.addCustomer(new Customer(name, address, phone, diag, presc, doctor));
                   doctors.add(doctor);
               }
               connection.close();
               return doctors;
           }
           catch(SQLException e) {
               System.err.println(e);
               return null;
           }
	}

	public ArrayList<Doctor> getDoctors() {
        String sql = "SELECT doctor_id, doctor_name, password "
                   + "FROM doctors ORDER BY doctor_id ASC";        
        ArrayList<Doctor> doctors = new ArrayList<>();
        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
        	while(rs.next()) {
                int code = rs.getInt("doctor_id");
                String name = rs.getString("doctor_name");
                String pass = rs.getString("password");

                Doctor doctor = new Doctor(code, name,pass);
                
                doctors.add(doctor);
            }
            connection.close();
            return doctors;
        }
        catch(SQLException e) {
            System.err.println(e);
            return null;
        }
    }

	public Doctor getDoctor(int id) {
        String sql =
            "SELECT doctor_id, user_name, password" +
            "FROM doctor " +
            "WHERE doctor_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {           
            //ps.setInt(1, custID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("user_name");
                String pass = rs.getString("password");

                Doctor d = new Doctor(name, pass);
                rs.close();
                connection.close();
                return d;
            }
            else {
                rs.close();
                connection.close();
                return null;
            }
        }
        catch(SQLException e) {
            System.err.println(e);
            return null;
        }
    }
	
	public boolean addDoctor(Doctor d) {
        String sql =
            "INSERT INTO doctors (doctor_name, password) " +
            "VALUES ( ?, ?)";
    	
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, d.getUsername());
            ps.setString(2, d.getPassword());
            ps.executeUpdate();
            connection.close();
            return true;
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }
}