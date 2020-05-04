public class Account {
	//variables
	private String username;
	private String password;
	//constructors
	public Account(){}
	public Account(String u, String p){
		username = u;
		password = p;
	}
	//get
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	//set
	public void setUsername(String username){
		this.username = username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	//toString
	public String toString(){
		return "Username: " + username + "\nPassword: " + password;
	}
}
