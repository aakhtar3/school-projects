import java.text.DecimalFormat;

public class Dining extends Store{
	//variables
	private static DecimalFormat twoD = new DecimalFormat("#.##");
	private static double dinTotalProfit;
	private String flavor;
	private Dish dish;
	//constructors
	public Dining() {
		
	}
	public Dining(String flavor) {
		this.flavor = flavor;
	}
	//get
	public static double getDinTotalProfit(){
		return Double.valueOf(twoD.format(dinTotalProfit));
	}
	public String getFlavor(){
		return flavor;
	}
	//set
	public static void setDinTotalProfit(double d){
		//increment the profit
		Dining.dinTotalProfit=Dining.dinTotalProfit + d;
		Store.setTotalProfit(dinTotalProfit);
	}
	public void setFlavor(String flavor){
		this.flavor = flavor;
	}
	//toString
	public String toString(){
				return "Flavor: " + flavor
				+ dish.toString() + "\n";
	}
	
	public void addDish(Dish dish){
		this.dish=dish;
	}
	
	public Dish getDish(){
		return dish;
	}
}
