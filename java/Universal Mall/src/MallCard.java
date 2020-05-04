import java.text.DecimalFormat;
import java.util.Random;

public class MallCard {
	//variables
	private int cardNumber;
	
	//since this is an instance variable, default balance will be 0, no need to initialize
	private double balance;
	private static Random rand= new Random();
	//constructors
	public MallCard(){
		//generate a random sequence of 9-digit numbers
		cardNumber= (rand.nextInt(900000000) + 100000000);
	}

	//get
	public int getCardNumber(){
		return cardNumber;
	}
	public double getBalance(){
		DecimalFormat twoD = new DecimalFormat("#.##");
		return Double.valueOf(twoD.format(balance));
	}
	//set
	public void setCardNumber(int cardNumber){
		this.cardNumber = cardNumber;
	}
	public void setBalance(double balance){
		//continue summing the balance
		this.balance = this.balance + balance;
	}
	//toString
	public String toString(){
		return "Card Number: " + cardNumber + "\nBalance: $" + balance;
	}
}
