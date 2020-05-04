import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DepartmentStore extends Store {
	private List<Item> items;
	private static double departmentStoreProfit;
	private static DecimalFormat twoD = new DecimalFormat("#.##");

	public DepartmentStore(){
		items=new ArrayList<Item>();
	}
	
	public static void setDepartmentStoreProfit(double profit){
		departmentStoreProfit=departmentStoreProfit+profit;
		Store.setTotalProfit(departmentStoreProfit);
	}
	
	public static double getDepartmentStoreProfit() {
		return Double.valueOf(twoD.format(departmentStoreProfit));
	}

	public void addItems(Item i){
		items.add(i);
	}
	
	public void decrementStock(Item i){
		
	}
	public String listItems(){
		String itemsList ="";
		for(int i=0; i< items.size();i++){
			itemsList+="\n"+(i+1)+"."+ items.get(i).getName();
		}
		return itemsList;
	}
	public List<Item> getItems(){
		return items;
	}
}
