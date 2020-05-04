/*
 * Ayyaz Akhtar
 * CS 5044 Programming Assignment #3
 * Program Employee Payroll
 * Prof John Lewis
 * 11-22-14
 */

// Abstract class that has a few attributes that all staff members must have
public abstract class StaffMember {

	// All staff members must have a name, id, and type
	protected String firstName;
	protected String lastName;
	protected Long id;
	protected String type;

	// Constructor that sets the attributes, for this project we will not need
	// setters for some of these attributes
	public StaffMember(String firstName, String lastName, Long id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}

	// Gets Id
	public Long getID() {
		return id;
	}

	// Abstract class that pays all staff members
	public abstract void pay();

	
	// Gets the Staff Members info
	public String toString(){
		return firstName + " " + lastName + " (" + type + ")";
	}


}
