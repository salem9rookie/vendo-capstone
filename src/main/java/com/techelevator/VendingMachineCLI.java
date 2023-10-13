package com.techelevator;

import com.techelevator.view.Menu;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

	private static final String FEED_MONEY_OPTION = "Feed Money";
	private static final String SELECT_PRODUCT_OPTION = "Select Product";
	private static final String FINISH_TRANSACTION_OPTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {FEED_MONEY_OPTION, SELECT_PRODUCT_OPTION, FINISH_TRANSACTION_OPTION};

	private static final String ADD_ONE_DOLLAR = "Add $1";
	private static final String ADD_FIVE_DOLLARS = "Add $5";
	private static final String ADD_TEN_DOLLARS = "Add $10";
	//	private static final String ADD_OTHER_AMOUNT = "Add Other Amount";
	private static final String[] MONEY_FEED_OPTIONS = {ADD_ONE_DOLLAR, ADD_FIVE_DOLLARS, ADD_TEN_DOLLARS, FINISH_TRANSACTION_OPTION};

	private final Menu menu;
	private double balance = 0.0;
	private final Map<String, Product> products = new TreeMap<>();
	private static VendingMachineCLI vendingMachineCLI; //new thing -> created and updated to static? may need to change

	//constructor

	public VendingMachineCLI(Menu menu, VendingMachineCLI vendingMachineCLI) {
		this.menu = menu;
		VendingMachineCLI.vendingMachineCLI = vendingMachineCLI;
	}

	//main method

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu, vendingMachineCLI);
		cli.run();
	}

	//methods
	public void run() {
		loadProducts("vendingmachine.csv");
		String filePath = "path/to/your/file.txt";
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("ASCIIvendo.txt"));
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + filePath);
			System.exit(1);
		}
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			System.out.println(line);
		}
		scanner.close();


		while (true){

			System.out.printf("Current Money Provided: $%.2f", balance);
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);


            switch (choice) {
                case MAIN_MENU_OPTION_DISPLAY_ITEMS:
                    displayItems();
                    break;
                case MAIN_MENU_OPTION_PURCHASE:
                    displayPurchaseMenu();
                    break;
                case MAIN_MENU_OPTION_EXIT:
                    System.out.println("Here is your change.");
                    giveChange(balance, 0.00);
                    VendLog.generateLogLine("GIVE CHANGE", balance, 0.00);
                    System.exit(-1);
            }
		}
	}

	private void displayPurchaseMenu() {
		System.out.printf("Current Money Provided: $%.2f", balance);
		String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

		switch (choice) {
			case FEED_MONEY_OPTION:
				displayFeedMenu();
				break;
			case SELECT_PRODUCT_OPTION:
				displayItems();
				selectProduct();
				break;
			case FINISH_TRANSACTION_OPTION:
				System.out.println("Here is your change.");
				giveChange(balance, 0.00);
				VendLog.generateLogLine("GIVE CHANGE", balance, 0.00);
				System.exit(-1);

		}
	}


	private void displayItems() {

		displayProducts();
	}


	private void displayFeedMenu() {
		System.out.printf("Current Money Provided: $%.2f", balance);
		String choice = (String) menu.getChoiceFromOptions(MONEY_FEED_OPTIONS);
		switch (choice) {
			case ADD_ONE_DOLLAR:
				double amountDeposited = 1.00;
				balance++;
				VendLog.generateLogLine("FEED MONEY", amountDeposited, balance );
				break;
			case ADD_FIVE_DOLLARS:
				amountDeposited = 5.00;
				balance += 5.00;
				VendLog.generateLogLine("FEED MONEY", amountDeposited, balance );
				break;
			case ADD_TEN_DOLLARS:
				amountDeposited = 10.00;
				balance += 10.00;
				VendLog.generateLogLine("FEED MONEY", amountDeposited, balance );
				break;
			case FINISH_TRANSACTION_OPTION:
				System.out.println("Here is your change.");
				giveChange(balance, 0.00);
				balance = 0.0;
				System.exit(-1);
				break;

		}

	}



	public void displayProducts() {
		System.out.println("Vending Machine Items:\n");
		for (Map.Entry<String, Product> entry : products.entrySet()) {
			String slot = entry.getKey();
			Product product = entry.getValue();
			System.out.printf("%s - %s ($%.2f) - %s - %d\n", product.getSlot(), product.getName(), product.getPrice(), product.getType(), product.getInventory());
		}
	}

	//-------- leave for Ahmad's optional office hours on 10-12
	// also possibly move to Transaction file? since its considered a Transaction?

	public void selectProduct() { //select item from the menu, matching slot to ultimately purchase the item in question.
		System.out.println("Please enter in product code: ");
		String selectedProduct = menu.getInputFromUser().toUpperCase();
		Product product = findProductBySlotNumber(selectedProduct);
		if(product == null){
			System.out.println("Product is null");
//        System.exit(-2);
			return;
		}
		if (selectedProduct.equalsIgnoreCase(product.getSlot())) {
			if(balance <= 0){
				System.out.println("Please insert money to purchase product.");
				return;
			}

			//System.out.println(product.getName());
			if(balance > product.getPrice()){
				if(product.getInventory()>0) {
					balance -= product.getPrice();
				}try {
					if (product.getInventory() <= 0){
						throw new RuntimeException("SOLD OUT. Choose another snack. Or try some pizza from Freddy's Fazbear's Pizzeria! Pizza so good, it's to DIE for!");
					}
				} catch (RuntimeException re) {
					System.out.println(re.getMessage());
					return;
				}
				//try catch block. if inventory is less than 0, update to SOLD OUT
				product.setInventory(product.getInventory()-1);
				switch (product.getType()) {
					case "Chip":
						System.out.println("Crunch, crunch Yum!");
						break;
					case "Drink":
						System.out.println("Glug, glug Yum!");
						break;
					case "Candy":
						System.out.println("Munch, munch Yum!");
						break;
					case "Gum":
						System.out.println("Chew, chew Yum!");
						break;
					// Add more cases for other product types if needed
					default:
						System.out.println("Unknown product type");
				}
			}
			VendLog.generateLogLine((product.getName()+ " "+product.getSlot()), product.getPrice(), balance);
		} else {
			System.out.println("oops....");
		}
	}

	public void loadProducts(String filename) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|");
				if (parts.length == 4) {
					String slot = parts[0];
					String name = parts[1];
					double price = Double.parseDouble(parts[2]);
					String type = parts[3];
					Product product = new Product(slot, name, price, type);
					products.put(slot, product);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public static void giveChange(double userBalance, double paidAmount){
		double returnedBalance = userBalance - paidAmount;
		int quarters = (int) (returnedBalance / 0.25);
		returnedBalance -= quarters * 0.25;

		int dimes = (int) (returnedBalance / 0.10);
		returnedBalance -= dimes * 0.10;

		int nickels = (int) (returnedBalance / 0.05);
		returnedBalance -= nickels * 0.05;

		int pennies = (int) (returnedBalance / 0.01);

		System.out.println("Your change is: ");
		System.out.println(quarters+" quarter(s)");
		System.out.println(dimes+ " dime(s)");
		System.out.println(nickels +" nickel(s)");
		System.out.println(pennies + " penn(ies)");
	}

	private Product findProductBySlotNumber(String slot){
		for (Map.Entry<String, Product> entry : products.entrySet()) {
			if(entry.getValue().getSlot().equalsIgnoreCase(slot)){
				return entry.getValue();
			}

		}return null;
	}
}




