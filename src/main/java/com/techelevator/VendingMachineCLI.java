package com.techelevator;

import com.techelevator.view.Menu;


public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String FEED_MONEY_OPTION = "Feed Money";
	private static final String SELECT_PRODUCT_OPTION = "Select Product";
	private static final String FINISH_TRANSACTION_OPTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {FEED_MONEY_OPTION, SELECT_PRODUCT_OPTION, FINISH_TRANSACTION_OPTION};

	private static final String ADD_ONE_DOLLAR = "Add $1";
	private static final String ADD_FIVE_DOLLARS = "Add $5";
	private static final String ADD_TEN_DOLLARS = "Add $10";
	private static final String ADD_OTHER_AMOUNT = "Add Other Amount";
	private static final String[] MONEY_FEED_OPTIONS = {ADD_ONE_DOLLAR, ADD_FIVE_DOLLARS, ADD_TEN_DOLLARS, ADD_OTHER_AMOUNT, FINISH_TRANSACTION_OPTION};

	private Menu menu;
	private double balance = 0.0;


	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	public void run() {
		while (true) {
			System.out.println("Current Money Provided: $"+balance);
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);


			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				displayItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				displayPurchaseMenu();
			}else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				System.out.println("Here is your change.");
				System.exit(-1);
			}
		}
	}

	private void displayPurchaseMenu(){
		System.out.println("Current Money Provided: $"+balance);
		String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

		switch (choice){
			case FEED_MONEY_OPTION:
				//feed money --> Salem
				displayFeedMenu();
				break;
			case SELECT_PRODUCT_OPTION:
				//select product --> Jennifer
				selectProduct();
				break;
			case FINISH_TRANSACTION_OPTION:
				System.out.println("Here is your change.");
				System.exit(-1);

		}
	}
	private void displayItems(){
		System.out.println("displaying new items"); //replace with call to Jennifer's methods
	}

	private void selectProduct(){
		System.out.println("Select from these products....");
	}

	private void displayFeedMenu(){
		System.out.println("Current Money Provided: $"+balance);
		String choice = (String) menu.getChoiceFromOptions(MONEY_FEED_OPTIONS);
		switch (choice){
			case ADD_ONE_DOLLAR:
				balance++;
				break;
			case ADD_FIVE_DOLLARS:
				balance += 5.00;
				break;
			case ADD_TEN_DOLLARS:
				balance += 10.00;
				break;
			case ADD_OTHER_AMOUNT:
				//add other amount
				System.out.println("Please enter an amount in whole dollars: ");
				int moneyFed = Integer.parseInt((menu.getInputFromUser()));
				balance += moneyFed;

				break;
			case FINISH_TRANSACTION_OPTION:
				//change is returned
				//machine balance reset to zero

				System.out.println("Here is your change.");
				System.exit(-1);
				break;

		}

	}




}
