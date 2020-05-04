
// Score class sets the target goal and keeps track of the players points and bank score.
public class Score {

	// Variable that is needed for the points, bank, and goal
	private int points;
	private int bank;
	private int goal;

	public Score() {
		this.bank = 0;
		this.points = 0;
	}

	// Gets the target goal
	public int getGoal() {
		return goal;
	}

	// Sets the target goal
	public void setGoal(int goal) {
		this.goal = goal;
	}

	// Get the point value
	public int getPoints() {
		return points;
	}

	// Get the bank value
	public int getBank() {
		return bank;
	}

	// Adds points generate to the round
	public void addRound(int points) {
		this.points += points;
	}

	// Adds points to the bank
	public void addToBank() {
		bank += points;
		resetRoundPoints();
	}

	// Reset round points if a player rolls a one
	public void resetRoundPoints() {
		points = 0;
	}

	// Resets bank total if a player rolls two ones
	public void resetBankTotal() {
		bank = 0;
		resetRoundPoints();
	}

	// ToString method
	public String toString() {
		return "Points: " + points + "\t\tBank: " + bank + "\t\t";
	}

}
