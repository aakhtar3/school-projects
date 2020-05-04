
import java.util.Scanner;

//Computer player is a child of Player and overrides 2 methods
public class HumanPlayer extends Player {

	// Default Constructor gets all attributes from parent class
	public HumanPlayer() {}

	// Asks the user if they want to roll again
	@Override
	public boolean rollAgain() {
		Scanner input = new Scanner(System.in);
		String answer = input.nextLine();
		while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
			System.out.print(pairOfDice.toString() + score.toString() + "Roll again? (y/n) ");
			answer = input.nextLine();
		}
		return (answer.equalsIgnoreCase("y")) ? true : false;
	}

	// Checks to see what the current play has rolled and if the player can roll
	// again
	@Override
	public boolean rollPairOfDice() {
		pairOfDice.roll();
		// Checks to see if there is a die with a one
		if (pairOfDice.isOneRolled()) {
			System.out.println(pairOfDice.toString() + score.toString() + "LOST TURN POINTS");
			score.resetRoundPoints();
			return false;

			// Checks to see if both dice have a one
		} else if (pairOfDice.isSnakeEyeRolled()) {
			System.out.println(pairOfDice.toString() + score.toString() + "LOST ALL POINT");
			score.resetBankTotal();
			return false;
			
			// The dice should be 2-6
		} else {
			score.addRound(pairOfDice.total());

			// Checks to see if the player has reached the goal
			if (score.getPoints() + score.getBank() >= score.getGoal()) {
				// Ask the user if they want to roll again
				System.out.println(pairOfDice.toString() + score.toString() + "You met the goal!");
				return false;
			}
		}
		
		// Ask player if they want to roll again
		System.out.print(pairOfDice.toString() + score.toString()
				+ "Roll again? (y/n) ");
		return true;
	}
}
