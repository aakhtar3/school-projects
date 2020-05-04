
public class Dish {
	private String name;
	private int rating;
	private double price;
	
	public Dish(String name,  int rating, double price){
		this.name=name;
		this.rating=rating;
		this.price=price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString(){
		return "\nDish: " + name + "\n" +
					 "Rating: " + rating + 
					 "\nPrice: $" + price;
	}
}
