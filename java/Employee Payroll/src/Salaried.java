
// Salaried employees have a payment receive their payments in portions over a course of a year
public class Salaried extends Employee {

	// Constructor to create a Salaried employee
	public Salaried(String firstName, String lastName, Long id, double payment) {
		super(firstName, lastName, id, payment);
		this.type = "Salaried";
	}

	// Payment divides their salary by the 12 months in a year
	@Override
	protected void setPayment(double payment) {
		this.payment = doubleFormat(payment / 12);
	}

	// Pay the salaried employee with their payment
	@Override
	public void pay() {
		setPayment(payment);
		System.out.println("*****PAYING SALRIED EMPLOYEE*****");
		System.out.println(toString());
	}

}
