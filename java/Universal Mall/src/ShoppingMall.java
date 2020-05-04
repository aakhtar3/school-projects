import java.util.*;
public class ShoppingMall {
	//variables
	private Admin admin;
	private List<Customer> customers;
	private List<Store> stores;
	//constructor
	public ShoppingMall() {
		admin = new Admin();
		customers = new ArrayList<Customer>();
		stores = new ArrayList<Store>();
	}
	//Admin get and set
	public Admin getAdmin(){
		return admin;
	}
	public void setAdmin(Admin admin){
		this.admin = admin;
	}
	//Store hash set
	public List<Store> getStores(){
		return stores; 
	}
	public void addStore(Store iStore){
		stores.add(iStore);
	}
	public void dropStore(Store iStore){
		stores.remove(iStore);
	}
	public boolean findStore(Store iStore){
        boolean found = false;
        while(stores.iterator().hasNext() && !found){
            if(stores.contains(iStore)){
                found = true;
            }
        }
        return found;
    }
	//Customer hash set
	public List<Customer> getCustomers(){
		return customers; 
	}
	public void addCustomer(Customer iCustomer){
		customers.add(iCustomer);
	}
	public void dropCustomer(Customer iCustomer){
		customers.remove(iCustomer);
	}
	public String listCustomers(){
		String all="";
		for(int i=0; i< customers.size();i++){
			all+="\n"+(i+1)+"."+ customers.get(i).getFirstName()+" "+customers.get(i).getLastName();
		}
		return all;
	}
	public boolean findCustomer(Customer iCustomer){
        boolean found = false;
        while(customers.iterator().hasNext() && !found){
            if(customers.contains(iCustomer)){
                found = true;
            }
        }
        return found;
    }
}
