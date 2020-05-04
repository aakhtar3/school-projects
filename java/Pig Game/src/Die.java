
// Die class generates a value from 1-6
public class Die {
	// Integer value for the roll
	private int roll;

	// Default Constructor
	public Die() {}

	// Get the roll value
	public int getRoll() {
		return roll;
	}

	// Roll method sets a random number from 1-6
	public void roll() {
		this.roll = (int) (Math.random() * 6) + 1;
	}
}
