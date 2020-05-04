public class Customer {
	//missing the random mall card in the customer object
	//variables
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private Account account;
	private MallCard mallCard;
	private int cusID;
	private static int totalCustomer;
	//constructors
	public Customer(){}
	public Customer(String firstName, String lastName, String phoneNumber, String email, Account account, MallCard mallCard) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.account = account;
		this.mallCard = mallCard;
		this.cusID = totalCustomer;
		totalCustomer++;
	}
	//get
	public String getFirstName(){
		return firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}
	public String getEmail(){
		return email;
	}
	public Account getAccount(){
		return account;
	}
	public int getCusID(){
		return cusID;
	}
	public MallCard getMallCard(){
		return mallCard;
	}
	public static int getTotalCustomers(){
		return totalCustomer;
	}
	//set
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public void setAccount(Account account){
		this.account = account;
	}
	public void setID(int cusID){
		this.cusID = cusID;
	}
	public void setMallCard(MallCard mallCard){
		this.mallCard = mallCard;
	}
	public void setTotalCustomer(int totalCustomer){
		this.totalCustomer = totalCustomer;
	}
	//toString
	public String toString(){
		return "ID: " + cusID + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nPhone Number: " + phoneNumber + "\nEmail: " + email  + "\n" +account.toString() + "\n" + mallCard.toString();
	}
}
