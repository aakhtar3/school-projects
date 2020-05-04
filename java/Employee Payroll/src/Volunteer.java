/*
 * Ayyaz Akhtar
 * CS 5044 Programming Assignment #3
 * Program Employee Payroll
 * Prof John Lewis
 * 11-22-14
 */

// Volunteer class is a staff member with no new functionality 
public class Volunteer extends StaffMember {

	// Constructor that sets all attributes, no need for setters
	public Volunteer(String firstName, String lastName, Long id) {
		super(firstName, lastName, id);
		this.type = "Volunteer";
	}

	// Volunteer do not get paid
	@Override
	public void pay() {
		System.out.println("*************VOLUNTEER*************");
	}

}
