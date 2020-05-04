import java.text.DecimalFormat;

public class Store {
	private static DecimalFormat twoD = new DecimalFormat("#.##");

	//variable
	private static double totalProfit;
	//constructor
	public Store(){
	}
	public static void setTotalProfit(double tp){
		totalProfit = totalProfit + tp;
	}
	public static double getTotalProfit() {
		return Double.valueOf(twoD.format(totalProfit));
	}
	//toString
	public String toString(){
		return "\nTotalProfit: " + totalProfit;
	}
}
