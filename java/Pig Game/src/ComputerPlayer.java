
// Computer player is a child of the Player class and overrides 2 methods, in addition it keeps track of the human player's score
public class ComputerPlayer extends Player {
	// Allows the computer to view the human players score since the computer
	// can not see the public score board
	private int humanScore;

	// Default Constructor gets all attributes from parent class
	public ComputerPlayer() {}

	// Algorithm that a computer uses to decide to roll again
	@Override
	public boolean rollAgain() {
		// If the computer is winning by 35 points combined with human players
		// current score, it will pass and not risk rolling snake eyes
		if (score.getBank() > humanScore + 35) {
			return false;

			// If computer is leading, it will make less risky choices
		} else if (score.getBank() > humanScore) {
			// Gather 10 points then pass the turn
			if (score.getPoints() <= 15) {
				return true;
			}

			// if computer is losing, it will make more risky choice to catch up
		} else if (score.getBank() < humanScore) {
			if (score.getPoints() <= 30) {
				return true;
			}

			// If the computer and human are tied, then it will make a moderate
			// choice
		} else if (score.getBank() == humanScore) {
			if (score.getPoints() <= 20) {
				return true;
			}
		}
		return false;
	}

	// Checks to see what the current play has rolled and if the player can roll
	// again
	@Override
	public boolean rollPairOfDice() {
		// Player rolls both dice
		pairOfDice.roll();

		// Checks to see if a die rolled a one
		if (pairOfDice.isOneRolled()) {
			System.out.println(pairOfDice.toString() + score.toString() + "LOST TURN POINTS");
			score.resetRoundPoints();
			return false;

			// Checks to see if both dice rolled a one
		} else if (pairOfDice.isSnakeEyeRolled()) {
			System.out.println(pairOfDice.toString() + score.toString() + "LOST ALL POINTS");
			score.resetBankTotal();
			return false;
			// The dice should be 2-6
		} else {
			score.addRound(pairOfDice.total());

			// Checks to see if the player has reached the goal
			if (score.getPoints() + score.getBank() >= score.getGoal()) {
				System.out.println(pairOfDice.toString() + score.toString() + "Reached the goal.");
				return false;
			}
		}
		// The computer decides to roll again
		if (rollAgain()) {
			System.out.println(pairOfDice.toString() + score.toString() + "Rolling again.");
			// The computer decides to not roll again and pass
		} else {
			System.out.println(pairOfDice.toString() + score.toString() + "Not rolling again.");
		}
		return true;
	}

	// Get the human score
	public int getHumanScore() {
		return humanScore;
	}

	// Sets the human score
	public void setHumanScore(int humanScore) {
		this.humanScore = humanScore;
	}
}
