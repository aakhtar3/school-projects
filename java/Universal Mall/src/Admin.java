public class Admin {
	//variable
	private Account account;
	//constructors
	public Admin(){}
	public Admin(Account account){
		this.account = account;
	}
	//get
	public Account getAccount(){
		return account;
	}
	//set
	public void setAccount(Account account){
		this.account = account;
	}
	//toString
	public String toString(){
		return account.toString();
	}
}
