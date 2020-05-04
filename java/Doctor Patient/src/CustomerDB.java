import java.util.*;
import java.sql.*;
public class CustomerDB {
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

	public ArrayList<Customer> getCustomers() {
        String sql = "SELECT patient_id, user_name, password, name, address, phone_number, current_diagnoses, current_prescription, current_doctor"
                   + "FROM patients ORDER BY patient_id ASC";        
        ArrayList<Customer> customers = new ArrayList<>();
        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
        	while(rs.next()) {
                int code = rs.getInt("patient_id");
                String user = rs.getString("user_name");
                String pass = rs.getString("password");
                String name = rs.getString("name");
                String addess = rs.getString("address");
                String phone = rs.getString("phone_number");
                String diag = rs.getString("current_diagnoses");
                String pres = rs.getString("current_prescription");
                int doc = rs.getInt("current_doctor");

                Customer c = new Customer(code, user, pass, name, addess, phone, diag, pres, doc);
                customers.add(c);
            }
            connection.close();
            return customers;
        }
        catch(SQLException e) {
            System.err.println(e);
            return null;
        }
    }

	public ArrayList<Customer> getCustomers1(int id) {
		String sql = "SELECT Distinct patients.patient_id, patients.name, patients.address, patients.phone_number, patients.current_diagnoses, patients.current_prescription, current_doctor FROM patients, doctors WHERE patients.current_doctor = doctors.doctor_id AND doctors.doctor_id = " + id;
        ArrayList<Customer> customers = new ArrayList<>();
        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
        	while(rs.next()) {
                int code = rs.getInt("patient_id");
                String name = rs.getString("name");
                String addess = rs.getString("address");
                String phone = rs.getString("phone_number");
                String diag = rs.getString("current_diagnoses");
                String pres = rs.getString("current_prescription");
                int doc = rs.getInt("current_doctor");
                Customer c = new Customer(code, name, addess, phone, diag, pres, doc);
                customers.add(c);
            }
            connection.close();
            return customers;
        }
        catch(SQLException e) {
            System.err.println(e);
            return null;
        }
    }
	
	public Customer getCustomer(int id) {
        String sql =
            "SELECT patient_id, name, address, phone_number, current_diagnoses, current_prescription, current_doctor FROM patients WHERE patient_id = " + id;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {           
            //ps.setInt(1, custID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	int id1 = rs.getInt("patient_id");
                String name = rs.getString("name");
                String addess = rs.getString("address");
                String phone = rs.getString("phone_number");
                String diag = rs.getString("current_diagnoses");
                String pres = rs.getString("current_prescription");
                int doc = rs.getInt("current_doctor");
                Customer c = new Customer(id1, name, addess, phone, diag, pres, doc);
                rs.close();
                connection.close();
                return c;
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
	
	public boolean addCustomer1(Customer c, int id) {
        String sql =
            "INSERT INTO patients (user_name, password, name, address, phone_number, current_doctor) " +
            "VALUES ( ?, ?, ?, ?, ?,"+ id+")";
    	
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, c.getUsername());
            ps.setString(2, c.getPassword());
            ps.setString(3, c.getName());
            ps.setString(4, c.getAddress());
            ps.setString(5, c.getPhone());
            ps.executeUpdate();
            connection.close();
            return true;
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }

	public boolean addCustomer12(Customer c) {
        String sql =
            "INSERT INTO patients (user_name, password, name, address, phone_number) " +
            "VALUES ( ?, ?, ?, ?, ?)";
    	
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, c.getUsername());
            ps.setString(2, c.getPassword());
            ps.setString(3, c.getName());
            ps.setString(4, c.getAddress());
            ps.setString(5, c.getPhone());
            ps.executeUpdate();
            connection.close();
            return true;
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }
	
	public boolean updateCustomer(Customer c) {
        String sql = "UPDATE patients SET name = ?, address = ?, phone_number = ? WHERE patient_id = ?";        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {            
            ps.setString(1, c.getName());
            ps.setString(2, c.getAddress());
            ps.setString(3, c.getPhone());
            ps.setInt(4, c.getCode());
            ps.executeUpdate();
            connection.close();
            return true;
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
	}

	public boolean updateDiagnosis(Customer c) {
        String sql = "UPDATE patients SET current_diagnoses = ? WHERE patient_id = ?";        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {            
            ps.setString(1, c.getDiagnoses());
            ps.setInt(2, c.getCode());
            ps.executeUpdate();
            connection.close();
            return true;
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }

	public boolean updatePrescription(Customer c) {
        String sql = "UPDATE patients SET current_prescription = ? WHERE patient_id = ?";        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {            
            ps.setString(1, c.getPrescription());
            ps.setInt(2, c.getCode());
            ps.executeUpdate();
            connection.close();
            return true;
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }

	public boolean deleteDiagnosis(Customer c) {
        String sql = "UPDATE patients SET current_diagnoses = ? WHERE patient_id = ?";        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {            
            ps.setString(1, "");
            ps.setInt(2, c.getCode());
            ps.executeUpdate();
            connection.close();
            return true;
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }

	public boolean deletePrescription(Customer c) {
        String sql = "UPDATE patients SET current_prescription = ? WHERE patient_id = ?";        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {            
            ps.setString(1, "");
            ps.setInt(2, c.getCode());
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