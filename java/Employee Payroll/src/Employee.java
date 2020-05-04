
import java.text.DecimalFormat;

// Abstract class that is a staff member and a Employee has a payment and a boss
public abstract class Employee extends StaffMember {

	// All employees have a payment and a boss
	protected double payment;
	protected Employee boss;

	// Constructor to create and Employee and it also formats the payment for
	// the employees paycheck
	public Employee(String firstName, String lastName, Long id, double payment) {
		super(firstName, lastName, id);

		// Sets the payment to the correct format and applies the correct
		// payment functionality for the type of employee
		this.payment = doubleFormat(payment);;
	}

	// Function to set a boss for this employee
	public void setBoss(Employee boss) {
		this.boss = boss;
	}

	// Gets payment after it has been formated
	protected double getPayment() {
		return payment;
	}

	// Abstract set payment method that an employee will override to meet its
	// functionality
	protected abstract void setPayment(double payment);

	// This method is used through out all employee types to format a double to
	// have two decimal places
	protected double doubleFormat(double doubleAdjusment) {
		DecimalFormat decimalFormat = new DecimalFormat("##.00");
		String formatString = (decimalFormat.format(doubleAdjusment));
		return Double.parseDouble(formatString);
	}

	// toString to display employee's attribute
	public String toString() {
		return super.toString() + " $" + payment;
	}

}
