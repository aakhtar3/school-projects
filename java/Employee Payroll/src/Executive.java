
import java.util.Random;

// Executives have an option have add a bonus to their salary only once.
// I added the function to check to see if a Executive is able to receive 
// a bonus, if their are able to receive a bonus then another function 
// to selects a random bonus value and reset the bonus back to zero 
public class Executive extends Salaried {
	// Executives have an option to receive a bonus
	private double bonus;

	// Bonus checker checks to see if the Executive is able to receive a bonus
	private int bonusChecker = 0;
	// Bonus count is a constant that lets the Executive obtain only one bonus
	static final int BONUS_LIMIT = 1;

	// Constructor that create a Executive employee
	public Executive(String firstName, String lastName, Long id, double payment) {
		super(firstName, lastName, id, payment);
		this.type = "Executive";
		this.bonus = 0;
	}

	// Function to add a random bonus to the Executive staff member.
	private void addBonus() {
		Random random = new Random();
		double minimum = 10000;
		double maximum = 100000;
		this.bonus = doubleFormat(((minimum + (maximum - minimum) * random.nextDouble())));

		System.out.println("******SETTING BONUS OF " + bonus + "******");
	}

	// Executive have a one time shot to receive a bonus, there is a condition
	// to check if the Executive is able to receive a bonus. If the Executive is
	// able, then it adds the bonus to the paycheck
	@Override
	public void pay() {
		super.setPayment(payment);
		// If the executive has not used their bonus, then they can add a bonus
		// to their paycheck
		if (bonusChecker < BONUS_LIMIT) {
			// Calls the add add bonus function
			addBonus();

			// Pay with bonus
			System.out.println("*****PAYING EXECUTIVE WITH BONUS*****");
			payment = doubleFormat(payment + bonus);
			System.out.println(toString());

			// Resets the bonus
			System.out.println("***********RESETING BONUS***********");
			payment = doubleFormat(payment - bonus);
			bonus = 0;
			System.out.println(toString());

			// Executive has now used their one time shot to receive a bonus
			bonusChecker++;

			// Just pay the Executive with their salary
		} else {
			System.out.println("******PAYING EXECUTIVE EMPLOYEE******");
			System.out.println(toString());
		}

	}

}
