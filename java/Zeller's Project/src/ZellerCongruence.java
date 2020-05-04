import java.util.Scanner;

public class ZellerCongruence {
	public static void main(String[] args) {
		// Command prompt
		Scanner input = new Scanner(System.in);
		// Initialize variables
		int month = 0, day = 0, year = 0, zValue = 0;
		String nextDay = "", dayString = "";

		// Prompt user to determine the day of week and Ask user if they want a
		// new day of week value
		do {
			// Determine Month
			System.out.println("Enter the month (1-12)");
			month = input.nextInt();
			// Validate the range
			while (month < 1 || month > 12) {
				System.out.println("Error!! You must enter between 1 and 12"
						+ "\nRe-enter the month (1-12)");
				month = input.nextInt();
			}

			// Determine Day
			System.out.println("Enter the day (1-31)");
			day = input.nextInt();
			// Validate the range
			while (day < 1 || day > 31) {
				System.out.println("Error!! You must enter between 1 and 31"
						+ "\nRe-enter the day (1-31)");
				day = input.nextInt();
			}

			// Determine Year
			System.out.println("Enter the year (YYYY)");
			year = input.nextInt();
			// Validate the range
			while (year < 1000 || year > 3000) {
				System.out.println("Error!! You must enter between 1000 and 3000"
						+ "\nRe-enter the year (YYYY)");
				year = input.nextInt();
			}

			// Adjust January and February
			if (month == 1) {
				month = 13;
				year -= 1;
			} else if (month == 2) {
				month = 14;
				year -= 1;
			}

			// Calculation for ZValue
			zValue = ((((day + (int) ((26 * (month + 1)) / 10.0) + year
					+ (int) (year / 4.0) + 6 * (int) (year / 100.0) + (int) (year / 400.0)) + 5) % 7) + 1);

			// Determines day of the week
			switch (zValue) {
			case 1:
				dayString = "Monday";
				break;
			case 2:
				dayString = "Tuesday";
				break;
			case 3:
				dayString = "Wednesday";
				break;
			case 4:
				dayString = "Thursday";
				break;
			case 5:
				dayString = "Friday";
				break;
			case 6:
				dayString = "Saturday";
				break;
			case 7:
				dayString = "Sundays";
				break;
			}

			// Display day of week
			System.out.println("Day of the week is: " + dayString);

			// Ask user if they want to enter a new date
			System.out.println("\nDo you want to enter a new date? (y/n)");
			nextDay = input.next();

			// If the the next day is "y" it will keep looping
		} while (nextDay.equalsIgnoreCase("y"));
	}
}
