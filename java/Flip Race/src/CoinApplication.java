
public class CoinApplication {
	public static void main(String[] args) {
		// Set the goal for the heads in a row
		final int GOAL = 3;

		// Set the counter for coins and total number of flips
		int countA = 0, countB = 0, count = 0;

		// Create two coins
		Coin coinA = new Coin(), coinB = new Coin();
		// Set the names of output
		coinA.setName("Rob");
		coinB.setName("Mark");

		// Flip both coins until three heads in a row
		while ((countA < GOAL) && (countB < GOAL)) {
			// Flip both coins and get the face values
			coinA.flipCoin();
			coinB.flipCoin();
			// If coin is heads it will increase the coin count by 1
			// Else three heads in a row will be reset
			countA = ((coinA.isHeads()) ? countA + 1 : 0);
			countB = ((coinB.isHeads()) ? countB + 1 : 0);

			// Print out Coin results
			System.out.println(coinA.toString() + "\t" + coinB.toString());

			// Total flip counter
			count++;

		}
		// Display winner and total number of flips
		if (countA < GOAL) {
			System.out.println(coinB.getName() + "'s coin Wins after " + count + " flips!");
		} else {
			if (countB < GOAL) {
				System.out.println(coinA.getName() + "'s coin Wins after " + count + " flips!");
			} else {
				System.out.println("It's a TIE after " + count + " flips!");
			}
		}
	}
}
