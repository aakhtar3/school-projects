import java.util.Scanner;

public class DecibelLevels {
	public static void main(String[] args) {
		// command line
		Scanner input = new Scanner(System.in);

		// Initialize values
		String description = "";
		int choice = 0, decibel = 0, value = 0;
		double pascals = 0;

		// Sentinel value is -1 to end program
		while (choice != -1) {
			// Prompt user to choose between entering dB, Pa, or quit program
			System.out.println("Enter 1 for a decibel (dB) vaule"
					+ "\nEnter 2 for a Pascals (Pa) value"
					+ "\nEnter -1 to end program");
			choice = input.nextInt();

			// Prompts the user to enter the value of their choice
			if (choice == 1) {
				System.out.println("Enter your decibel (dB) value");
				decibel = input.nextInt();
				value = decibel;
			} else if (choice == 2) {
				System.out.println("Enter your Pascals (Pa) value");
				pascals = input.nextDouble();
				// calculates the sound level value
				value = (int) (20 * Math.log10(pascals / 2e-5));
			}

			// Rounding 130 decibel value
			if (value >= 125 && value <= 130) {
				description = "Threshold of pain";
			}
			// Rounding 100 - 120 decibel value
			else if (value >= 115 && value <= 125) {
				description = "Possible hearing damage";
			}
			// Rounding 90 - 100 decibel value
			else if (value >= 95 && value <= 115) {
				description = "Traffic on a busy roadway at 10 m";
			}
			// Rounding 60 - 90 decibel value
			else if (value >= 75 && value <= 95) {
				description = "Normal converstaion";
			}
			// Rounding 30 - 60 decibel value
			else if (value >= 45 && value <= 75) {
				description = "Calm library";
			}
			// Rounding 0 - 30 decibel value
			else if (value >= 0 && value <= 15) {
				description = "Light leaf rustling";
			}
			// Inputs are not in the range of the description
			else {
				description = "** Error Your input is not in the decibel (dB) range of 0 - 130 **";
			}

			// Prints out description of the sound level
			if (choice == 1 || choice == 2) {
				System.out.println("Sound level is near: " + "\"" + description + "\""
						+ " with a dB value of: " + value + "\n");
			}
		}

	}
}
