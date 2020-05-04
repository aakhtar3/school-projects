

// PairOfDice class manages two pair of dice
public class PairOfDice {
	// Two dice objects
	private Die redDie;
	private Die blueDie;

	// Constructor with new two separate dice objects
	public PairOfDice() {
		this.redDie = new Die();
		this.blueDie = new Die();
	}

	// Roll method generates two separate dice values from 1-6
	// Dice objects are calling the roll method from the Die class
	public void roll() {
		this.redDie.roll();
		this.blueDie.roll();
	}

	// Gets the roll value for the red die from the Die class
	public int getRedDieRoll() {
		return redDie.getRoll();
	}

	// Gets the roll value for the blue die from the Die class
	public int getBlueDieRoll() {
		return blueDie.getRoll();
	}

	// Gets the total value of both dice
	public int total() {
		return getRedDieRoll() + getBlueDieRoll();
	}

	// Method that checks to see if one of the die has a one value
	public boolean isOneRolled() {
		return (getRedDieRoll() == 1 || getBlueDieRoll() == 1) ? true : false;
	}

	// Method that checks to see if both of the dice has a one value
	public boolean isSnakeEyeRolled() {
		return (getRedDieRoll() == 1 && getBlueDieRoll() == 1) ? true : false;
	}

	// To String method
	public String toString() {
		return "Roll: " + getRedDieRoll() + "+" + getBlueDieRoll() + "=" + total() + "\t\t";
	}
}
