
import java.util.Scanner;

// Application class that runs the program called PigGame
public class PigGame {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// String objects to help display text
		String line = "-----------------------------------"
				+ "----------------------------------------------";
		String star = "********************************* ";

		// Creates a reference point for the human score
		// A computer will use this value to determine its decision making process
		int humanScore = 0;

		// FirstRoll checks to see if the player can roll after switching to
		// their turn and keepRolling checks to see if the player wants to keep
		// rolling
		boolean firstRoll = false, keepRolling = false;

		// Create human and computer players
		Player humanPlayer = new HumanPlayer(), computerPlayer = new ComputerPlayer();

		// Start the game with the current player as a human
		Player currentPlayer = humanPlayer;

		// Introduction to the Game
		System.out.println("Welcome to the Pig Game!");

		// Ask human player for their name
		System.out.print("What is your name? ");
		humanPlayer.setName(input.nextLine());
		computerPlayer.setName("CPU");

		// Ask human player the target goal
		System.out.print("What is your target goal? ");
		int goal = input.nextInt();
		humanPlayer.score.setGoal(goal);
		computerPlayer.score.setGoal(goal);
		System.out.println(line);

		// Keep looping until there is a winner
		while (!humanPlayer.isWinner() && !computerPlayer.isWinner()) {
			// Prints Name's turn. Points: XX Bank: XX
			// Checks to see if the first roll is valid
			System.out.println(currentPlayer.toString());
			
			//Checks to see if the player can roll again
			firstRoll = currentPlayer.rollPairOfDice();

			// If the first roll is valid then the player decides to roll again
			if (firstRoll) {
				keepRolling = currentPlayer.rollAgain();
			} else {
				keepRolling = false;
			}

			// Keeps letting the player decide if they want to roll again
			while (keepRolling && !currentPlayer.isWinner()) {
				keepRolling = currentPlayer.rollPairOfDice();
				if (keepRolling) {
					keepRolling = currentPlayer.rollAgain();
				} else {
					keepRolling = false;
				}
			}
			// Ends the current players turn
			currentPlayer.endTurn();

			// Switch the current player to the next player
			if (currentPlayer == humanPlayer) {
				// Get human score
				humanScore = currentPlayer.getBankTotal();

				// Switch player to computer player
				currentPlayer = computerPlayer;

				// Set human score so the computer can determine its next roll
				if (currentPlayer instanceof ComputerPlayer) {
					((ComputerPlayer) currentPlayer).setHumanScore(humanScore);
				}
			} else if (currentPlayer == computerPlayer) {
				currentPlayer = humanPlayer;
			}

		}

		// Display Winner
		if (humanPlayer.isWinner()) {
			System.out.println(star + humanPlayer.getName() + " wins! " + star);
		} else if (computerPlayer.isWinner()) {
			System.out.println(star + computerPlayer.getName() + " wins! "
					+ star);
		}
	}
}
