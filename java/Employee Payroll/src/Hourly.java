
import java.util.Random;

// Hourly employees get paid based on their hourly pay rate and the hours they worked. Hourly employees have an option to add hours to their paycheck. Once they get paid their hours set back to 0
public class Hourly extends Employee {
	// Hourly employees receive their paycheck by the hours they work
	private int hours;

	// Constructor to create a Hourly employee
	public Hourly(String firstName, String lastName, Long id, double payment) {
		super(firstName, lastName, id, payment);
		this.type = "Hourly";
		this.hours = 0;
	}

	// Function that adds a random hour for the employee
	private void addHours() {
		Random random = new Random();
		int minimum = 1;
		int maximum = 96;
		this.hours = (random.nextInt(maximum - minimum) + minimum);

		System.out.println("*********Adding " + hours + " HOURS*********");
	}

	// Hourly employee gets paid by adding hours that are then multiplied by
	// their hourly pay rate
	@Override
	public void pay() {
		setPayment(payment);
		
		addHours();

		System.out.println("**********PAYING HOURLY**********");
		payment = doubleFormat(payment * hours);
		System.out.println(toString());

		System.out.println("**********RESETING HOURS**********");
		payment = doubleFormat(payment / hours);
		hours = 0;
		System.out.println(toString());

	}

	@Override
	protected void setPayment(double payment) {
		this.payment = doubleFormat(payment);
	}


}
