public class Doctor {
	private int id;
	private String username;
	private String password;

	private Customer[] customer;
	private int numOfCustomer;
	public static final int MAX_CUSTOMER = 1000;

	public Doctor(String username, String password, int numOfCustomer){
		this.username = username;
		this.password = password;
		customer = new Customer[MAX_CUSTOMER];
	}

	public Doctor(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Doctor(String name, String address, String phone, String diag, String presc) {

	}

	public Doctor(int id, String username, String password) {
		this.id=id;
		this.username=username;
		this.password=password;
	}

	public Doctor(int id) {
		this.id=id;
	}
	public int getId(){
		return id;
	}
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public void setId(int id){
		this.id=id;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public void setPassword(String password){
		this.password=password;
	}

	public void addCustomer(Customer c){
		if (numOfCustomer < MAX_CUSTOMER){
			customer[numOfCustomer++] = c;
			c.setDoctor(this);
		}
	}

	public Customer[] getAllCustomer(){
		return customer;
	}

	public int getNumOfCustomer(){
		return numOfCustomer;
	}
}
