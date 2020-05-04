/*
	Class that defines a Coin
 */
public class Coin {
	// Heads value
	private static final int HEADS = 0;
	// Private variables for name and face value
	private String name;
	private int face;

	// Default constructor
	public Coin() {}

	// Get name
	public String getName() {
		return name;
	}

	// Set name
	public void setName(String name) {
		this.name = name;
	}

	// Flips the coins for a 50/50 chance of getting either a heads or tails
	public void flipCoin() {
		face = (int) (Math.random() * 2);
	}

	// Sets the heads value
	public boolean isHeads() {
		return (face == HEADS);
	}

	// ToString for printing out results
	// If face is heads it will print out the name and heads
	// Else it will print out name an tails
	public String toString() {
		return (face == HEADS)
				? name + "'s Coin: " + "Heads"
				: name + "'s Coin: " + "Tails";
	}
}
