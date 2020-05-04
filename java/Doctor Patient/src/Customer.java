public class Customer {
	private int code;
	private String username;
	private String password;
	private String name;
	private String address;
	private String phone;
	private String diagnoses;
	private String prescription;
	private int doc;
	private Doctor doctor;

	public Customer(){}

	public Customer(int code, String username, String password, String name, String address, String phone, String diagnoses, String prescription, int doc, Doctor doctor) {
		this.code=code;
		this.username = username;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.diagnoses = diagnoses;
		this.prescription = prescription;
		this.doc = doc;
		this.doctor = doctor;
	}
	public Customer(int code, String username, String password, String name, String address, String phone, String diagnoses, String prescription, int doc) {
		this.code=code;
		this.username = username;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.diagnoses = diagnoses;
		this.prescription = prescription;
		this.doc = doc;
	}


	public Customer(String username, String name, String address, String phone, String diagnoses, String prescription, Doctor doctor){
		this.username = username;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.diagnoses = diagnoses;
		this.prescription = prescription;
		this.doctor = doctor;
	}

	public Customer(String name, String address, String phone, String diagnoses,String prescription, Doctor doctor) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.diagnoses = diagnoses;
		this.prescription = prescription;
		this.doctor = doctor;
	}

	public Customer(String username, String password, String name, String address, String phone) {
		this.username = username;
		this.password=password;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public Customer(int code, String name, String address, String phone, String diagnoses, String prescription, int doc) {
		this.code=code;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.diagnoses = diagnoses;
		this.prescription = prescription;
		this.doc=doc;
	}



	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public String getName(){
		return name;
	}
	public String getAddress(){
		return address;
	}
	public String getPhone(){
		return phone;
	}
	public String getDiagnoses(){
		return diagnoses;
	}
	public String getPrescription(){
		return prescription;
	}
	public int getCode(){
		return code;
	}
	public int getDoc(){
		return doc;
	}
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor newDoctor){
		if (doctor != newDoctor) {
			doctor = newDoctor;
			if (newDoctor != null)
				newDoctor.addCustomer(this);
		}
	}

	public void setUsername(String username){
		this.username=username;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public void setDiagnoses(String diagnoses){
		this.diagnoses=diagnoses;
	}
	public void setPrescription(String prescription){
		this.prescription=prescription;
	}
	public void setCode(int code){
		this.code=code;
	}
	public void setDoc(int doc){
		this.doc=doc;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}

}
