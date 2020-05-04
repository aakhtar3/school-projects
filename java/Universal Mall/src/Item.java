//added this class

public class Item {
	private String name;
	private String category;
	private int quantity;
	private double price;
	
	public Item(){
	}
	
	public Item(String category, String name, double price, int quantity){
		this.name=name;
		this.category=category;
		this.quantity=quantity;
		this.price=price;
	}
	public String getName() {
		return name;
	}
	
	public String getCategory(){
		return category;
	}
	
	public void setCategory(String category){
		this.category=category;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString(){
		return "Name: " + name + "\n" /*+ "Category: " + category + "\n" */+ "Price: " + price + "\nQuantity: " + quantity;
	}
}
