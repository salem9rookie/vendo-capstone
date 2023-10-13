package com.techelevator;

import com.techelevator.view.Menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class JennifersCode {

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
    //  private static final String ADD_OTHER_AMOUNT = "Add Other Amount";
    private static final String[] MONEY_FEED_OPTIONS = {ADD_ONE_DOLLAR, ADD_FIVE_DOLLARS, ADD_TEN_DOLLARS, FINISH_TRANSACTION_OPTION};

    private Menu menu;
    private double balance = 0.0;
    private Map<String, Product> products = new LinkedHashMap<>();
    private static VendingMachineCLI vendingMachineCLI; //new thing -> created and updated to static? may need to change

    //constructor

    public JennifersCode(Menu menu, VendingMachineCLI vendingMachineCLI) {
        this.menu = menu;
        this.vendingMachineCLI = vendingMachineCLI;
    }

    //main method

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu, vendingMachineCLI);

        cli.run();
    }

    //methods
    public void run() {
        while (true) {
            System.out.println("Current Money Provided: $" + balance);
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);


            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                // display vending machine items
                displayItems();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                // do purchase
                displayPurchaseMenu();
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                System.out.println("Here is your change.");
                giveChange(balance, 0.00);
                System.exit(-1);
            }
        }
    }

    private void displayPurchaseMenu() {
        System.out.println("Current Money Provided: $" + balance);
        String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

        switch (choice) {
            case FEED_MONEY_OPTION:
                //feed money --> Salem
                displayFeedMenu();
                break;
            case SELECT_PRODUCT_OPTION:
                //select product --> Jennifer
//                selectProduct();
                break;
            case FINISH_TRANSACTION_OPTION:
                System.out.println("Here is your change.");
                giveChange(balance, 0.00);
                System.exit(-1);

        }
    }


    private void displayItems() {
        loadProducts("vendingmachine.csv"); //added by Salem
        displayProducts(); //added by Salem
    }


    private void displayFeedMenu() {
        System.out.println("Current Money Provided: $" + balance);
        String choice = (String) menu.getChoiceFromOptions(MONEY_FEED_OPTIONS);
        switch (choice) {
            case ADD_ONE_DOLLAR:
                balance++;
                break;
            case ADD_FIVE_DOLLARS:
                balance += 5.00;
                break;
            case ADD_TEN_DOLLARS:
                balance += 10.00;
                break;
//        case ADD_OTHER_AMOUNT:
//           //add other amount
//           System.out.println("Please enter an amount in whole dollars: ");
//           int moneyFed = Integer.parseInt((menu.getInputFromUser()));
//           balance += moneyFed;
//
//           break;
            case FINISH_TRANSACTION_OPTION:
                //change is returned
                //machine balance reset to zero

                System.out.println("Here is your change.");
                giveChange(balance, 0.00);
                System.exit(-1);
                break;

        }

    }



    public void displayProducts() {
        System.out.println("Vending Machine Items:\n");
        for (Map.Entry<String, Product> entry : products.entrySet()) {
            String slot = entry.getKey();
            Product product = entry.getValue();
            System.out.printf("%s - %s ($%.2f) - %s\n", product.getSlot(), product.getName(), product.getPrice(), product.getType());
        }
    }

//    public void selectProduct() {
//        while (true) {
//            System.out.println("Current money provided: $" + balance);
//            System.out.println("(1) Feed Money");
//            System.out.println("(2) Select Your Product");
//            System.out.println("(3) End Transaction");
//            System.out.println("Choose option");
//            Scanner scanner = new Scanner(System.in);
//            String input = scanner.nextLine();
//            Product selectedItem = null;
//            if (input.equals("1")) {
//                System.out.println("Please enter a whole dollar amount");
//                double amount = scanner.nextDouble();
//                balance += amount;
//            } else if (input.equals("2")) {
//                System.out.println("Please enter the slot location of your desired product");
//                String slot = scanner.nextLine();
//                selectedItem = null;
//                for (Product product : products.values()) {
//                    if (product.getSlot().equals(slot)) {
//                        selectedItem = product;
//                        System.out.println(selectedItem);
//                        break;
//                    }
//                }
//                if (selectedItem == null) {
//                    System.out.println("Invalid slot location. Please, try again");
//                } else if (selectedItem.getInventory(selectedItem.decreaseInventory() - 1) == 0) {
//                    System.out.println("Item is SOLD OUT. Please, select another");
//                } else if (selectedItem.getPrice() > balance)
//                    System.out.println("Not enough money. Please, add more. Feed me, Seymour.");
//            } else {
//                balance -= selectedItem.getPrice();
//                selectedItem.getInventory(selectedItem.decreaseInventory() - 1);
//                System.out.println(selectedItem.getType() + "dispensed." + selectedItem.getMessage);
//                System.out.println("Balance remaining: $" + balance);
//            }
//        }
//    }
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
}