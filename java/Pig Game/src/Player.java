
// Player class is abstract and has common functionality that is shared in it's subclasses
public abstract class Player {

	// A player needs a name, a pair of dice, score, and goal
	protected String name;
	protected PairOfDice pairOfDice;
	protected Score score;
	protected int goal;

	public Player() {
		this.pairOfDice = new PairOfDice();
		this.score = new Score();
	}

	// Abstract method that let the player decide to roll again
	// The code will be over written to meet the child's functionality
	public abstract boolean rollAgain();

	// Abstract method that rolls the players pair of dice
	public abstract boolean rollPairOfDice();

	// Set the players name
	public void setName(String name) {
		this.name = name;
	}

	// Get the player name
	public String getName() {
		return name;
	}

	// Ends the players turn, adds the points to the bank, and displays an
	// output
	public void endTurn() {
		score.addToBank();
		System.out.println("Passing. " + "\t\tPoints: " + score.getPoints() + "\t\tBank: " + getBankTotal());
		System.out.println("---------------------------------------------------------------------------------");
	}

	// Get the bank total score
	public int getBankTotal() {
		return score.getBank();
	}

	// Checks to see if the player is a winner
	public boolean isWinner() {
		return (score.getBank() >= score.getGoal()) ? true : false;
	}

	// ToString
	public String toString() {
		return name + "'s turn. \t\t" + score;
	}

}
