import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Application {
	
	//we only need 1 mall for this application, a global variable seems fitting
	public static ShoppingMall mall = new ShoppingMall();
	
	public static void main(String[] args){

		//test account, use for testing
		mall.addCustomer(new Customer("test","test","703-703-7033","test@test.com",new Account("test","test"),new MallCard()));
		
		//make an admin account and add to mall
		Account ad = new Account("Admin", "Admin");
		Admin admin = new Admin (ad);
		mall.setAdmin(admin);
		
		//variable to test whether the login credentials matched the admin's
		boolean isAdmin = false;
		//load up and store the stores into the shopping mall
		loadStores();
		int menuChoice=-1;
		String menu ="University Mall Version 2.0\n\nWelcome! Lets do some shopping!\n\n1)Log in\n2)Create account\n3)Exit";
		do {
			menuChoice = checkInt(1,3,menu);
			switch(menuChoice){
				case 1:
					customerLogIn();
					break;
				case 2:
					createAccount();
					break;
			}
		} while(menuChoice!=3);
		JOptionPane.showMessageDialog(null,"Thanks for stopping by!", "University Mall", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void loadStores(){
		String line = "";
		String filePath;
		//this will store a single row in the text file
		String[] record = new String[4];
		DepartmentStore ds= new DepartmentStore();
		filePath="DepartmentStores.txt";
		
		
		try {
			Scanner readIt = new Scanner(new FileInputStream(filePath));
			readIt.useDelimiter("#");
			while(readIt.hasNext()){
				line=readIt.nextLine();
				record=line.split(",");
				for(int i=0; i<record.length; i++){
					//remove white spaces from String
					record[i]=record[i].replaceAll("\\s","");
				}
				//remove delimiter
				record[3]=record[3].replace("#", "");
				ds.addItems(new Item(record[0], record[1], Double.parseDouble(record[2]), Integer.parseInt(record[3])));
			}
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		//add the department to the mall
		mall.addStore(ds);
		
		//load the Dining options from file
		Dining d=null;
		filePath="dining.txt";
		try{
			Scanner readIt = new Scanner(new FileInputStream(filePath));
			readIt.useDelimiter("#");
			while(readIt.hasNext()){
				line=readIt.nextLine();
				record=line.split(",");
				for(int i=0; i<record.length; i++){
					record[i]=record[i].replaceAll("\\s","");
				}
				record[3]=record[3].replace("#", "");
				d=new Dining(record[0]);
				d.addDish(new Dish(record[1], Integer.parseInt(record[2]), Double.parseDouble(record[3])));
				//add each diner to the mall
				mall.addStore(d);
			}
		}catch(FileNotFoundException e){
				e.printStackTrace();
		}
		
		//load the Entertainment from files
		Entertainment e=null;
		filePath="entertainment.txt";
		try{
			Scanner readIt = new Scanner(new FileInputStream(filePath));
			readIt.useDelimiter("#");
			while(readIt.hasNext()){
				line=readIt.nextLine();
				record=line.split(",");
				for(int i=0; i<record.length; i++){
					record[i]=record[i].replaceAll("\\s","");
				}
				record[3]=record[3].replace("#", "");
				//add each new entertainment event to the mall
				e=new Entertainment(record[0], record[1], record[2], Double.parseDouble(record[3]));
				mall.addStore(e);
			}
		}catch(FileNotFoundException ee){
				ee.printStackTrace();
		}
		
	}
	
	public static void createAccount(){
		String username;
		String password;
		String firstName;
		String lastName;
		String email="";
		String phoneNumber;
		final String EMAIL_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Customer c;
		
		try{
			do{
				username=JOptionPane.showInputDialog(null,"Please enter your desired username","University Mall", JOptionPane.QUESTION_MESSAGE);
				if(username.isEmpty())
					JOptionPane.showMessageDialog(null,"Invalid username detected!","University Mall",JOptionPane.ERROR_MESSAGE);
			}while(username.isEmpty() || username.trim().isEmpty() || username==null);
			do{
				password=JOptionPane.showInputDialog(null,"Please enter your desired password","University Mall", JOptionPane.QUESTION_MESSAGE);
				if(password.isEmpty())
					JOptionPane.showMessageDialog(null, "Invalid password detected!", "University Mall", JOptionPane.ERROR_MESSAGE);
			}while(password.isEmpty() || password.trim().isEmpty() || password==null);
			do{
				firstName=JOptionPane.showInputDialog(null,"Enter your first name","University Mall", JOptionPane.QUESTION_MESSAGE);
				if(firstName.isEmpty())
					JOptionPane.showMessageDialog(null, "First name cannot be empty!", "University Mall", JOptionPane.ERROR_MESSAGE);
			}while(firstName.isEmpty() || firstName.trim().isEmpty() || firstName==null);
			do{
				lastName=JOptionPane.showInputDialog(null,"Enter your last name","University Mall", JOptionPane.QUESTION_MESSAGE);
				if(lastName.isEmpty())
					JOptionPane.showMessageDialog(null, "Last name cannot be empty!", "University Mall", JOptionPane.ERROR_MESSAGE);
			}while(lastName.isEmpty() || lastName.trim().isEmpty() || lastName==null);
			do{
				email=JOptionPane.showInputDialog(null,"Please enter your email address","University Mall", JOptionPane.QUESTION_MESSAGE);
				if(email.isEmpty() || !email.matches(EMAIL_PATTERN))
					JOptionPane.showMessageDialog(null, "Invalid email detected! Please use the following format xxxx@xxx.com/net/org/edu/gov", "University Mall", JOptionPane.ERROR_MESSAGE);
			}while(email.isEmpty() || !email.matches(EMAIL_PATTERN) || email==null || email.trim().isEmpty());
			do{
				phoneNumber=JOptionPane.showInputDialog(null,"Please enter your phone number","University Mall", JOptionPane.QUESTION_MESSAGE);
				if(phoneNumber.isEmpty() || phoneNumber.length()!=12)
					JOptionPane.showMessageDialog(null,"Invalid phone number detected. Please use the following format XXX-XXX-XXXX", "University Mall", JOptionPane.ERROR_MESSAGE);
			}while(phoneNumber.isEmpty() || phoneNumber.length()!=12 || phoneNumber==null || phoneNumber.trim().isEmpty());
			c=new Customer(firstName,lastName,phoneNumber,email,new Account(username,password),new MallCard());
			JOptionPane.showMessageDialog(null, "Congratulations! You have just created an account. Below is your account information \n\n" + c.toString(),"University Mall", JOptionPane.INFORMATION_MESSAGE);
			mall.addCustomer(c);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Hmm, something went wrong. Try again!", "University Mall", JOptionPane.ERROR_MESSAGE);
		}
	}
		
	
	public static void customerLogIn(){
		String username=null;
		String password=null;
		Iterator<Customer> itr=mall.getCustomers().iterator();
		Customer c=null;
		boolean match=false;
		boolean isAdmin = false;
		
		username=JOptionPane.showInputDialog(null, "Enter your username", "University Mall", JOptionPane.INFORMATION_MESSAGE);
		password=JOptionPane.showInputDialog(null, "Enter your password", "University Mall", JOptionPane.INFORMATION_MESSAGE);
		
		if(username.equalsIgnoreCase("Admin") && password.equalsIgnoreCase("Admin")){
			showAdminMenu();
			isAdmin = true;
		}
			
		
		//seach through the customer credentials stored in the customer list, if credentials match, send the customer to the customer menu
		if (isAdmin == false) {
			while (itr.hasNext()) {
				c = itr.next();
				if (c.getAccount().getUsername().equalsIgnoreCase(username)
						&& c.getAccount().getPassword()
								.equalsIgnoreCase(password)) {
					match = true;
					showCustomerMenu(c);
				}
			}

			if (match == false) {
				JOptionPane.showMessageDialog(null,
						"Sorry, your username and/or password was incorrect!",
						"University Mall", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public static void showAdminMenu(){
		int menuChoice=-1;
		int cusID = -1;
		boolean validSelection=false;
		Customer customer = null;
		boolean found = false;
		String allCustomers ="";
		String menu = "Hello Admin! Choose your option:\n\n1.Generate Report \n2.Update Store Information" +
				"\n3.Edit Customer Information\n4.Log out \n5.Exit";
		
		menuChoice = checkInt(1,5,menu);
		while(menuChoice!=4 && menuChoice !=5){

			switch(menuChoice){
				case 1:
					generateReport();;
				break;
				case 2:
					updateStoreInfo();
				break;
				case 3: adminUpdateCustomerInfo();			
				break;
			}
			menuChoice = checkInt(1,5,menu);
		}
		
		if(menuChoice == 5){
			JOptionPane.showMessageDialog(null,"Thanks for stopping by!", "University Mall", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}	
	}

	public static void generateReport(){
		double earned = Store.getTotalProfit();
		int totalCust = Customer.getTotalCustomers();
		
		JOptionPane.showMessageDialog(null, "Mall has earned $"+earned+ 
		"\nEntertainment has earned: $"+Entertainment.getEntTotalProfit() + 
		"\nDining has earned: $"+ Dining.getDinTotalProfit()+"\nDepartment has earned" +
				"$"+DepartmentStore.getDepartmentStoreProfit()+"\n"+  totalCust +" Customers", "University Mall", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void updateStoreInfo(){
		//menu to change data on store
		int menuChoice = -1;
		int location =-1;
		String itemsList = "";
		int itemChoice = -1;
		String choose ="Hello Admin! Choose Service:\n\n1.Department Store" +
				" \n2.Dining\n3.Entertainment\n4.Go back";
		menuChoice = checkInt(1,4,choose); 
		
		
		switch(menuChoice){
		case 1: //read d object and removed it from mall stores
				
				for(int i=0;i<mall.getStores().size();i++){
					if(mall.getStores().get(i) instanceof DepartmentStore){
						location = i;
						}
					}
				DepartmentStore dept = (DepartmentStore) mall.getStores().get(location);
				mall.getStores().remove(location);
				
				//read items list from d to work on
				itemsList = dept.listItems();
				String itemMessage = "Choose your item to update."+ itemsList;
				itemChoice = checkInt(1,dept.getItems().size(),itemMessage);
				itemChoice= itemChoice -1;
				//the items list I am working on are the ones that are in object d
				Item e=(dept.getItems().get(itemChoice));
				dept.getItems().remove(itemChoice);
				
				//updates the change, adds the item into dept list same spot it was initially
				dept.getItems().add(itemChoice, showItemChangeMenu(e));
				
				//adds dept back into mall
				mall.addStore(dept);
				
				
				
		break;
		case 2: //read dining object and remove it from mall stores
				List<Dining> dinings = new ArrayList<Dining>();
			for(int i=0;i<mall.getStores().size();i++){
				if(mall.getStores().get(i) instanceof Dining){
					dinings.add((Dining) mall.getStores().get(i));
					}
				}
				
				//finds the dining object to be updated and its location
				Dining d= locateDining(dinings);
				for(int i=0;i<mall.getStores().size();i++){
					if(mall.getStores().get(i) instanceof Dining ){
						if(((Dining) mall.getStores().get(i)).getFlavor().equals(d.getFlavor()))
							location = i;
					}
				}
			
			//removes dining object from mall, will put it back in after it is updated
				mall.getStores().remove(d);
			
				//adds dining store back into mall
			mall.getStores().add(location, showDiningChangeMenu(d));
		break;		
		case 3: //reads the entertainment object from mall
			List<Entertainment> entertain = new ArrayList<Entertainment>();
			for(int i=0;i<mall.getStores().size();i++){
				if(mall.getStores().get(i) instanceof Entertainment){
					location = i;
					entertain.add((Entertainment) mall.getStores().get(i));
					}
				}
			
			Entertainment en = locateEntertainment(entertain);
			for(int i=0;i<mall.getStores().size();i++){
				if(mall.getStores().get(i) instanceof Entertainment ){
					if(((Entertainment) mall.getStores().get(i)).getEventName().equals(en.getEventName()))
						location = i;
				}
			}
			//removes Entertainment object from mall, will put it back in after it is updated
			//Entertainment ent = (Entertainment) mall.getStores().get(location);
			mall.getStores().remove(en);
			
			//adds entertainment object back into mall
			//mall.addStore(showEntertainmentChangeMenu(en));
			mall.getStores().add(location, showEntertainmentChangeMenu(en));
			
		break;
		}
		
	}
	public static Entertainment showEntertainmentChangeMenu(Entertainment ent){
		int menuChoice = -1;
		String itemsList = ent.toString()+"\n\n1.Timing \n2.Category \n3.Event Name\n4.Price\n5.Go Back";
		String input ="";
		menuChoice = checkInt(1,5,itemsList);
		double price;

		
		if(menuChoice ==1){
			input = checkString("Enter updated Timing:");
			ent.setTiming(input);
		}else if(menuChoice ==2){
			input = checkString("Enter updated Category:");
			ent.setCategory(input);
		}
		else if(menuChoice ==3){
			input = checkString("Enter updated Event Name:");
			ent.setEventName(input);
		}
		else if(menuChoice ==4){
			price = checkDouble(0,9999999,"Enter updated Price:");
			ent.setPrice(price);
		}else
			return ent;
		JOptionPane.showMessageDialog(null, ent.toString());
		return ent;
	}	
	public static Dining showDiningChangeMenu(Dining dining){
		int menuChoice = -1;
		String itemsList = dining.toString()+"\n\n1.Flavor \n2.Rating \n3.Price\n4.Go Back";
		String input = "";
		double price;
		int rating;
		menuChoice= checkInt(1,4,itemsList);
		
		if(menuChoice ==1){
			input = checkString("Enter updated Flavor:");
			dining.setFlavor(input);			
		}else if(menuChoice ==2){
			Dish dish = dining.getDish();
			rating = checkInt(0,5,"Enter updated Rating:");
			dish.setRating(rating);
			dining.addDish(dish);
		}
		else if(menuChoice ==3){
			Dish dish = dining.getDish();
			price = checkDouble(0.01,9999999,"Enter updated Price:");
			dish.setPrice(price);
			dining.addDish(dish);
		}else
			return dining;
		JOptionPane.showMessageDialog(null, dining.toString());
		return dining;
		
	}
	public static Item showItemChangeMenu(Item item){
		int menuChoice = -1;
		String menu ="Updating Department Store \n"+item.toString()+
				"\n\n1.Name \n2.Category \n3.Quantity \n4.Price\n5.Go Back";
		menuChoice = checkInt(1,5,menu);
		
		String input = "";
		double price;
		int quantity;
	
			switch(menuChoice){
			case 1:input = checkString("Enter updated Name:");
				item.setName(input);
			break;
			case 2: input = checkString("Enter updated Category:");
				item.setCategory(input);
			break;
			case 3:quantity = checkInt(0,9999999,"Enter updated Quantity:");
				item.setQuantity(quantity);
			break;
			case 4:price = checkDouble(0,9999999,"Enter updated Price:") ;
				item.setPrice(price);
			break;
			case 5: return item;
			}
			JOptionPane.showMessageDialog(null, item.toString());
		return item;
			
	}
	public static void adminUpdateCustomerInfo(){
		String allCustomers ="", old, change;
		double oldBalance, newBalance;
		int choice = -1;
		int menuChoice = -1;
		final String EMAIL_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		String menu = "\nWhat would you like to change?\n" +
				"\n1)First Name\n2)Last Name\n3)Phone Number\n4)Email\n5)Balance\n6)Username" +
				"\n7)Password\n8)Save and Exit";
		//gets the customer id and the list of customers
		allCustomers= mall.listCustomers();
		choice = checkInt(1,Customer.getTotalCustomers(),"Please choose your Customer:"+allCustomers);
		choice = choice -1;
			
		mall.getCustomers().get(choice).toString();
		do{
		
			menuChoice=checkInt(1,8,mall.getCustomers().get(choice).toString()+
					"\n"+menu);
		switch(menuChoice){
		case 1:
			old=mall.getCustomers().get(choice).getFirstName();
			change=checkString("Enter the new first name");
			mall.getCustomers().get(choice).setFirstName(change);
			JOptionPane.showMessageDialog(null,"First name changed from " + old + " to " + change);
		break;
		case 2:
			old=mall.getCustomers().get(choice).getLastName();
			change=checkString("Enter the new last name");
			mall.getCustomers().get(choice).setFirstName(change);
			JOptionPane.showMessageDialog(null,"Last name changed from " + old + " to " + change);
		break;
		case 3:
			old=mall.getCustomers().get(choice).getPhoneNumber();
			change=checkPhone("Enter the new phone number\nValid type is (where x is the digit): xxx-xxx-xxxx");
			mall.getCustomers().get(choice).setPhoneNumber(change);
			JOptionPane.showMessageDialog(null,"Phone number changed from " + old + " to " + change);
		break;
		case 4:
			old=mall.getCustomers().get(choice).getEmail();
			change=checkString("Enter your new email");
			while(!change.matches(EMAIL_PATTERN)){
				JOptionPane.showMessageDialog(null, "Invalid email detected! " +
					"Please use the following format xxxx@xxx.com/net/org/edu/gov", "University Mall", JOptionPane.ERROR_MESSAGE);
				change=checkString("Enter your new email");
			}
			mall.getCustomers().get(choice).setEmail(change);
			JOptionPane.showMessageDialog(null,"Email changed from " + old + " to " + change);
		break;
		case 5:
			oldBalance=mall.getCustomers().get(choice).getMallCard().getBalance();
			newBalance = checkDouble(0,1000000.0,"Enter updated Balance:");
			mall.getCustomers().get(choice).getMallCard().setBalance(newBalance);
			JOptionPane.showMessageDialog(null,"Balance changed from " + oldBalance + " to " + newBalance);
		break;
		case 6: 
			old=mall.getCustomers().get(choice).getAccount().getUsername();
			change=checkString("Enter the new Username");
			mall.getCustomers().get(choice).getAccount().setUsername(change);
			JOptionPane.showMessageDialog(null,"Username changed from " + old + " to " + change);
		break;
		case 7:
			old=mall.getCustomers().get(choice).getAccount().getPassword();
			change= checkString("Enter the new Username");
			mall.getCustomers().get(choice).getAccount().setPassword(change);
			JOptionPane.showMessageDialog(null,"Password changed from " + old + " to " + change);
		break;
		}
	}while(menuChoice!=8);
		
	}
	public static Dining locateDining(List<Dining> dinings){
		//locates particular dining object to be updated and sends it back
		
		Dining d;
		int menuChoice = -1;
		String message = "Please Choose one:";
		for(int i=0;i<dinings.size();i++){
			message+= "\n"+(i+1)+"."+dinings.get(i).getFlavor();
		}
		menuChoice = checkInt(1,(dinings.size()+1),message);
		
		menuChoice = menuChoice -1;
		d= dinings.get(menuChoice);
		
		
		return d;
	}
	public static Entertainment locateEntertainment(List<Entertainment> entertain){
		Entertainment en;
		int menuChoice = -1;
		String message = "Please Choose one:";
		for(int i=0;i<entertain.size();i++){
			message+= "\n"+(i+1)+"."+entertain.get(i).getEventName();
		}
		menuChoice = checkInt(1,(entertain.size()+1),message);
		menuChoice = menuChoice -1;
		en = entertain.get(menuChoice);
		return en;
	}
	public static void showCustomerMenu(Customer c){
		int menuChoice=-1;
		boolean validSelection=false;
		
		do{
			try{
				menuChoice=Integer.parseInt(JOptionPane.showInputDialog(null, "Welcome back " + c.getFirstName() + "! What would you like to do?" + "\n\n1.Buy Products/Service(s)\n2.Update Information\n3.Check account balance\n4.Log out", "University Mall", JOptionPane.INFORMATION_MESSAGE));
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Not a valid choice!", "University Mall", JOptionPane.ERROR_MESSAGE);
			}
			switch(menuChoice){
				case 1:
					displayServiceMenu(c);
				break;
				case 2:
					updateCustomerInfo(c);
				break;
				case 3:
					JOptionPane.showMessageDialog(null,"Your account balance is currently $" +c.getMallCard().getBalance(),"University Mall", JOptionPane.WARNING_MESSAGE);
				break;
			}
		}while(menuChoice!=4);
	}
	

	public static void displayServiceMenu(Customer c){
		int menuChoice=-1;
		do{
			try{
				menuChoice=Integer.parseInt(JOptionPane.showInputDialog(null, "Which category would you like to browse?"+ "\n1)Shopping\n2)Dining\n3)Entertainment\n4)Exit", "University Mall", JOptionPane.INFORMATION_MESSAGE));
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Not a valid selection!", "University Mall", JOptionPane.ERROR_MESSAGE);
			}
			switch(menuChoice){
				case 1:
					displayShoppingMenu(c);
				break;
				
				case 2:
					displayDiningMenu(c);
				break;
	
				case 3:
					displayEntertainmentMenu(c);
				break;
			}
		}while(menuChoice!=4);
	}
	
	/**
	 * @throws FileNotFoundException if the file does not exist
	 * */
	public static void displayShoppingMenu (Customer c){
		String purchaseRequest="";
		int quantityRequest=-1;
		DepartmentStore ds=null;
		int continueShopping=-1;
		String itemList="";
		//we know the first store-element is the department store
		//cast it to a department store in order to access its methods/members
		ds=(DepartmentStore)mall.getStores().get(0);
		Iterator<Item> itr= ds.getItems().iterator();
		boolean foundItem=false;
		boolean availableQuantity=false;
		Item searchItem=null;
		int indexOfItem=0;
		
		while(itr.hasNext()){
			itemList+=itr.next().toString() + "\n\n";
		}
		
		try{
			do{
				foundItem=false;
				availableQuantity=false;
				indexOfItem=0;
				purchaseRequest=JOptionPane.showInputDialog(null,itemList + "Enter the name of item that you would like to purchase (type exit to quit)","University Mall", JOptionPane.QUESTION_MESSAGE).trim();
				//re-assign the iterator, since its last value was null
				itr=ds.getItems().iterator();
				while(itr.hasNext()){
					searchItem=itr.next();
					if(searchItem.getName().equalsIgnoreCase(purchaseRequest)){
						foundItem=true;
						if(searchItem.getQuantity()>0)
							availableQuantity=true;
						break;
					}
					indexOfItem++;
				}
				if(foundItem && availableQuantity){
					quantityRequest=Integer.parseInt(JOptionPane.showInputDialog("How many " + purchaseRequest + " would you like to purchase?"));
					if(quantityRequest>0 && ds.getItems().get(indexOfItem).getQuantity()-quantityRequest>=0){
						//decrement the item
						ds.getItems().get(indexOfItem).setQuantity(ds.getItems().get(indexOfItem).getQuantity()-quantityRequest);
						//rebuild the menu
						itemList="";
						itr=ds.getItems().iterator();
						while(itr.hasNext()){
							itemList+=itr.next().toString() + "\n\n";
						}
						//charge the customer
						c.getMallCard().setBalance(ds.getItems().get(indexOfItem).getPrice()*quantityRequest);
						
						//update the profits
						DepartmentStore.setDepartmentStoreProfit(ds.getItems().get(indexOfItem).getPrice()*quantityRequest);
					}
					else{
						JOptionPane.showMessageDialog(null,"You can't buy that many!");
						continue;
					}
					continueShopping=JOptionPane.showConfirmDialog(null, "Would you like to purchase more items?", null, JOptionPane.YES_NO_OPTION);
				}
				else{
					if(!purchaseRequest.equalsIgnoreCase("exit"))
						JOptionPane.showMessageDialog(null,"Sorry, that item doesn't exist or is out of stock =(. Try again", null, JOptionPane.ERROR_MESSAGE);
				}
			}while(continueShopping!=JOptionPane.NO_OPTION && !purchaseRequest.equalsIgnoreCase("exit"));
		}catch(IllegalArgumentException e){
			JOptionPane.showMessageDialog(null,e.toString(),null, JOptionPane.ERROR_MESSAGE);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"An error occured. Please try again.");
		}
	}//end method
	
	
	public static void displayDiningMenu(Customer c){
		int continueEating=-1;
		String requestedDish=null;
		int numDishes=-1;
		int dinerCount=1;
		boolean validDish;
		String diningOptions="";
		Dining d=null;
		
		//we know that the mall has diners in slots 1-5 of the Stores list
		for(int i=1; i<6; i++){
			diningOptions+=mall.getStores().get(i).toString() + "\n";
		}
		try{
		do{
			validDish=false;
			requestedDish=JOptionPane.showInputDialog(null, diningOptions +  "\nWhich dish would you like to eat? (enter 'exit' if you're full)", "University Mall", JOptionPane.INFORMATION_MESSAGE);
			dinerCount=1;
			for(int i=1; i<6; i++){
					d=(Dining)mall.getStores().get(i);
					if(d.getDish().getName().equalsIgnoreCase(requestedDish)){
						validDish=true;
						dinerCount=i;
						break;
					}
			}
			if(validDish && !requestedDish.equalsIgnoreCase("exit")){
				do{
					validDish=false;
					try{
						numDishes=Integer.parseInt(JOptionPane.showInputDialog(null, "How many orders of " + requestedDish + " would you like to place?", "University Mall", JOptionPane.QUESTION_MESSAGE));
						validDish=true;
					}catch(Exception e){
						validDish=false;
						JOptionPane.showMessageDialog(null, "Hey, you can't do that!Try again", "University Mall", JOptionPane.ERROR_MESSAGE);
					}
				}while(numDishes<0 || !validDish);
				if(numDishes>0){
					//charge the customer
					c.getMallCard().setBalance(d.getDish().getPrice()*numDishes);
					//increment the profit
					Dining.setDinTotalProfit(d.getDish().getPrice()*numDishes);
					continueEating=JOptionPane.showConfirmDialog(null, "Would you like to eat more?", "University Mall", JOptionPane.YES_NO_OPTION);
				}
				else{
					JOptionPane.showMessageDialog(null, "Oops! You cant do that!", "University Mall", JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				if(!requestedDish.equalsIgnoreCase("exit"))
					JOptionPane.showMessageDialog(null,"Sorry! We don't have that dish at the moment. Try ordering something we do have!", "University Mall", JOptionPane.ERROR_MESSAGE);
			}
		}while(continueEating!=JOptionPane.NO_OPTION && !requestedDish.equalsIgnoreCase("exit") || requestedDish.isEmpty() || requestedDish.equals(null) || requestedDish.trim().isEmpty());
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Oops, something went wrong. Try again!", "University Mall", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void displayEntertainmentMenu(Customer c){
		//slots 6,7,8,9 are entertainment events
		String requestedEvent="";
		int buyMoreTickets=-1;
		String eventList="";
		Entertainment e=null;
		boolean validRequest;
		int numTickets;
		
		//get the list of events
		for(int i=6; i<10; i++){
			e=(Entertainment)mall.getStores().get(i);
			eventList+=e.toString() + "\n";
		}
		try{
			do{
				validRequest=false;
				requestedEvent=JOptionPane.showInputDialog(null,eventList + "Which event would you like tickets for? (type exit to go back to browse)", "University Mall", JOptionPane.QUESTION_MESSAGE);
				for(int i=6; i<10; i++){
					e=(Entertainment)mall.getStores().get(i);
					if(e.getEventName().equalsIgnoreCase(requestedEvent)){
						validRequest=true;
						break;
					}
				}
					if(validRequest){
						do{
							try{
							validRequest=false;
							numTickets=Integer.parseInt(JOptionPane.showInputDialog(null, "How many tickets would you like to purchase for " + requestedEvent + " ?","University Mall", JOptionPane.QUESTION_MESSAGE));
							if(numTickets>0){
								validRequest=true;
								c.getMallCard().setBalance(e.getPrice()*numTickets);
								Entertainment.setEntTotalProfit(e.getPrice()*numTickets);
								buyMoreTickets=JOptionPane.showConfirmDialog(null, "Would you like to buy more tickets?", "University Mall", JOptionPane.YES_NO_OPTION);
							}
							else{
								JOptionPane.showMessageDialog(null,"Hey, you can't do that!","University Mall", JOptionPane.ERROR_MESSAGE);
							}
							}catch(Exception ex){
								validRequest=false;
							}
						}while(!validRequest);
					}
					else{
						if(!requestedEvent.equalsIgnoreCase("exit"))
							JOptionPane.showMessageDialog(null,"Sorry, that's not a valid request! Please enter the name of the event to purchase tickets", "University Mall", JOptionPane.ERROR_MESSAGE);
					}
			}while(buyMoreTickets!=JOptionPane.NO_OPTION && !requestedEvent.equals("exit"));
		}catch(Exception ee){/*when the user hits cancel just catch the exception and go back to the previous menu*/}

	}
	
	public static void updateCustomerInfo(Customer c){
		int menuChoice=-1;
		
		//hold the requested change here
		String change;
		String old;
		final String EMAIL_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		do{
			try{
				menuChoice=Integer.parseInt(JOptionPane.showInputDialog(null,"What would you like to change?\n\n1)First Name\n2)Last Name\n3)Phone Number\n4)Email\n5)Save and Exit","University Mall", JOptionPane.QUESTION_MESSAGE));
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Hey, stick to the menu!", "University Mall", JOptionPane.ERROR_MESSAGE);
			}
			switch(menuChoice){
			case 1:
				do{
					old=c.getFirstName();
					change=JOptionPane.showInputDialog("Enter your new first name");
				}while(change.equals(null) || change.isEmpty() || change.trim().isEmpty());
				c.setFirstName(change);
				JOptionPane.showMessageDialog(null,"First name changed from " + old + " to " + change);
			break;
			case 2:
				do{
					old=c.getLastName();
					change=JOptionPane.showInputDialog("Enter your new last name");
				}while(change.equals(null) || change.isEmpty() || change.trim().isEmpty());
				c.setLastName(change);
				JOptionPane.showMessageDialog(null,"Last name changed from " + old + " to " + change,"University Mall", JOptionPane.INFORMATION_MESSAGE);
			break;
			case 3:
				do{
					old=c.getPhoneNumber();
					change=JOptionPane.showInputDialog("Enter your new phone number");
					if(change.length()!=12)
						JOptionPane.showMessageDialog(null, "Invalid phone number detected. Please use the following format XXX-XXX-XXXX", "University Mall", JOptionPane.ERROR_MESSAGE);
				}while(change.equals(null) || change.isEmpty() || change.trim().isEmpty() || change.length()!=12);
				c.setPhoneNumber(change);
				JOptionPane.showMessageDialog(null,"Phone number changed from " + old + " to " + change,"University Mall", JOptionPane.INFORMATION_MESSAGE);
			break;
			case 4:
				do{
					old=c.getEmail();
					change=JOptionPane.showInputDialog("Enter your new email");
					if(!change.matches(EMAIL_PATTERN))
						JOptionPane.showMessageDialog(null, "Invalid email detected! Please use the following format xxxx@xxx.com/net/org/edu/gov", "University Mall", JOptionPane.ERROR_MESSAGE);
				}while(change.equals(null) || change.isEmpty() || change.trim().isEmpty() || !change.matches(EMAIL_PATTERN));
				c.setEmail(change);
				JOptionPane.showMessageDialog(null,"Email changed from " + old + " to " + change);
			break;
			}
		}while(menuChoice!=5);
	}


	private static int checkInt(int mini, int max,String message) {
		// This method checks to make sure no errors happen when the user enters
		// a number
		int goodToGO = -1;
		boolean isValid = false;
		while (isValid == false) {
			try {
				goodToGO = Integer.parseInt(JOptionPane.showInputDialog(null,message, "University Mall", JOptionPane.INFORMATION_MESSAGE));
				if (goodToGO < mini || goodToGO > max)
					throw new MenuOutofBounds();
				else
					isValid = true;
			} catch (MenuOutofBounds e) {
				JOptionPane.showMessageDialog(null,"You must enter a number between " + mini
						+ " and " + max + "\nRe-enter >", "University Mall", JOptionPane.ERROR_MESSAGE);
			}catch (InputMismatchException e) {
				JOptionPane.showMessageDialog(null,"Sorry, you must enter a number. " +
						"The number cannot have a decimal.\nPlease Re-enter ", "University Mall", JOptionPane.ERROR_MESSAGE);
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null,"You cannot click Cancel or put in nothing.");
			}
			catch(NullPointerException e){
				JOptionPane.showMessageDialog(null,"You cannot click Cancel or put in nothing.");
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null,"An error occured. Please Try again.", "University Mall", JOptionPane.ERROR_MESSAGE);
			}
		}
		return goodToGO;

	}

	private static double checkDouble(double mini, double max, String message) 
	{
		//This method validates and makes sure that the double is okay before proceeding with the program
		
		boolean isValid = false;
		double number = 0.0;
		while (isValid==false) 
		{
			try 
			{
				number = Double.parseDouble(JOptionPane.showInputDialog(null,message));
				if (number < mini || number > max)
					throw new MenuOutofBounds();
				else
					isValid = true;
	
			} 
			catch (InputMismatchException e)
			{
				JOptionPane.showMessageDialog(null,"Sorry, you must enter a number. ");
				
			} catch (MenuOutofBounds e) {
				JOptionPane.showMessageDialog(null,"You must enter a number between " + mini
						+ " and " + max + "\nRe-enter >", "University Mall", JOptionPane.ERROR_MESSAGE);
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null,"You cannot click Cancel or put in nothing.");
			}
			catch(NullPointerException e){
				JOptionPane.showMessageDialog(null,"You cannot click Cancel or put in nothing.");
			}catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null,"An error occured. Please Try again.");
				e.printStackTrace();
			}
		}
		return number;
	}
	private static String checkString(String message){
		boolean isValid = false;
		String st ="";
			while(isValid == false){
				try{
					st = JOptionPane.showInputDialog(message);
					if(st.isEmpty()){
						JOptionPane.showMessageDialog(null,"You cannot enter an empty value. Please re-enter.");
					}
					else
						isValid = true;
				}catch(Exception e){}
			}
		
		
		return st;
	}
	private static String checkPhone(String message){
			//validates phone number
			boolean datavalid = true;
			char first, second;
			int position, pos, format;
			String error ="";
			String number="";
			
		do{
			 number= JOptionPane.showInputDialog(message);
			datavalid = true;
			if(number.length()!=12)
			{
				datavalid=false;
				error += "The number has too many or too little digits.\n"; 
			}
			if(datavalid ==true )
			{
				position=number.indexOf('-');
				if(position== -1)
				{
					datavalid =false;
					error+= "Incorrect format.\n"; 
				}
				else{
					pos = number.indexOf('-', (++position));
					if(pos == -1)
					{
						datavalid =false;
						error+= "Incorrect format, need to use two '-'.\n";
					}
				}
			}
			if(datavalid == false){
				JOptionPane.showMessageDialog(null,error);
				error = "";
			}
				
		}while(datavalid == false);
		
		return number;
}
}
