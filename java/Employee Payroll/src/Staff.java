
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

// Staff is supposed to create all the staff members and map them to map by their ID's
// This class can also view all employees attributes, use their functionalities, and pay the employees.
public class Staff {

	// Staff map references
	private Map<Long, StaffMember> staffMap;

	// Constructor to create maps all the staff members
	public Staff() {

		// Create two Volunteer staff members
		Volunteer v1 = new Volunteer("Rob", "Bob", randomID());
		Volunteer v2 = new Volunteer("Tom", "Jobs", randomID());

		// Create two Executive staff members
		Executive e1 = new Executive("Steve", "Jobs", randomID(), randomSalary());
		Executive e2 = new Executive("Bill", "Gates", randomID(), randomSalary());
		e2.setBoss(e1);
		e1.setBoss(e2);

		// Create three Hourly staff members
		Hourly h1 = new Hourly("Alex", "Simpson", randomID(), randomHours());
		h1.setBoss(e1);
		Hourly h2 = new Hourly("Carol", "Fisherman", randomID(), randomHours());
		h2.setBoss(e1);
		Hourly h3 = new Hourly("Mindy", "Jackson", randomID(), randomHours());
		h3.setBoss(e1);

		// Create three Salaried staff members
		Salaried s1 = new Salaried("Wendy", "Gordon", randomID(), randomSalary());
		s1.setBoss(e2);
		Salaried s2 = new Salaried("Helen", "Carter", randomID(), randomSalary());
		s2.setBoss(e2);
		Salaried s3 = new Salaried("Samuel", "Masher", randomID(), randomSalary());
		s3.setBoss(e2);

		// Maps all objects by their ID's
		staffMap = new HashMap<Long, StaffMember>();
		staffMap.put(v1.getID(), v1);
		staffMap.put(v2.getID(), v2);
		staffMap.put(e1.getID(), e1);
		staffMap.put(e2.getID(), e2);
		staffMap.put(h1.getID(), h1);
		staffMap.put(h2.getID(), h2);
		staffMap.put(h3.getID(), h3);
		staffMap.put(s1.getID(), s1);
		staffMap.put(s2.getID(), s2);
		staffMap.put(s3.getID(), s3);

	}

	// Generates a random ID for a Staff member
	private Long randomID() {
		Random random = new Random();
		return random.nextLong();
	}

	// Generates a random salary for a Salaried employee
	private double randomSalary() {
		Random random = new Random();
		double minimum = 40000;
		double maximum = 500000;
		return (minimum + (maximum - minimum) * random.nextDouble());
	}

	// Generate a random hour for a hourly employee
	private double randomHours() {
		Random random = new Random();
		double minimum = 10;
		double maximum = 100;
		return (minimum + (maximum - minimum) * random.nextDouble());
	}

	// Pays all staff members
	public void payAll() {

		// Creates a keySet from the staff map
		Set<Long> keySet = staffMap.keySet();

		// Iterates through all the keys
		for (Long key : keySet) {

			// Gets the staff member designates from the current key
			StaffMember value = staffMap.get(key);

			// Displays staff member
			System.out.println("-----------NEXT EMPLOYEE-----------");
			System.out.println(value.toString());

			// Pays the staff member
			value.pay();
			System.out.println("--------EMPLOYEE PROCESSED---------");

			System.out.println();
			System.out.println();
		}
	}

}
