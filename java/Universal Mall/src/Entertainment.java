import java.text.DecimalFormat;

public class Entertainment extends Store{
	//variables
	private static DecimalFormat twoD = new DecimalFormat("#.##");
	private static double entTotalProfit;
	private String timing;
	private String category;
	private String eventName;
	private double price;
	//constructors
	public Entertainment(String category, String eventName, String timing, double price) {
		this.category=category;
		this.eventName=eventName;
		this.timing=timing;
		this.price=price;
	}

	//get
	public static double getEntTotalProfit(){
		return Double.valueOf(twoD.format(entTotalProfit));
	}
	public String getTiming(){
		return timing;
	}
	//set
	public static void setEntTotalProfit(double entTotalProfit){
		//increment the profit
		Entertainment.entTotalProfit = Entertainment.entTotalProfit + entTotalProfit;
		//increment the mall profit
		Store.setTotalProfit(Entertainment.getEntTotalProfit());
	}
	public void setTiming(String timing){
		this.timing = timing;
	}
	//toString
	public String toString(){
		return "Category: " + category + "\n" + "Event: " + eventName  + "\nTime: " + timing + "\nTicket Price: $" + price + "\n";
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
