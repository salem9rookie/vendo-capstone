package com.techelevator.service;

import com.techelevator.interfaces.Constants;
import com.techelevator.model.Product;
import com.techelevator.view.Menu;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingMachine implements Constants {

    private double balance;
    private double amountDeposited;
    private Map<String, Product> products;
    private Menu menu;

    //constructor
    public VendingMachine(Menu menu) {
        this.balance = 0.0;
        this.amountDeposited = 0;
        this.products = new TreeMap<>();
        loadProducts("vendingmachine.csv");
        this.menu = menu;
    }

    //methods
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
    public void selectProduct() {
        System.out.println("Please enter in product code: ");
        String selectedProduct = menu.getInputFromUser().toUpperCase();
        Product product = findProductBySlotNumber(selectedProduct);
        if(product == null){
            System.out.println("Product is null");
            return;
        }
        if (selectedProduct.equalsIgnoreCase(product.getSlot())) {
            if(balance <= 0){
                System.out.println("Please insert money to purchase product.");
                return;
            }

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
                    default:
                        System.out.println("Unknown product type");
                }
            }
            VendLog.generateLogLine((product.getName()+ " "+product.getSlot()), product.getPrice(), balance);
        } else {
            System.out.println("oops....");
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
    private void displayFeedMenu() {
        System.out.printf("Current Money Provided: $%.2f", balance);
        String choice = (String) menu.getChoiceFromOptions(MONEY_FEED_OPTIONS);
        switch (choice) {
            case ADD_ONE_DOLLAR:
                amountDeposited = 1.00;
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
    public void displayBanner(){
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
    }
    public void displayPurchaseMenu() {
        System.out.printf("Current Money Provided: $%.2f", balance);
        String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

        switch (choice) {
            case FEED_MONEY_OPTION:
                displayFeedMenu();
                break;
            case SELECT_PRODUCT_OPTION:
                displayProducts();
                selectProduct();
                break;
            case FINISH_TRANSACTION_OPTION:
                System.out.println("Here is your change.");
                giveChange(balance, 0.00);
                VendLog.generateLogLine("GIVE CHANGE", balance, 0.00);
                System.exit(-1);

        }
    }
    private Product findProductBySlotNumber(String slot){
        for (Map.Entry<String, Product> entry : products.entrySet()) {
            if(entry.getValue().getSlot().equalsIgnoreCase(slot)){
                return entry.getValue();
            }

        }return null;
    }

    //getters
    public double getBalance() {
        return balance;
    }

    public double getAmountDeposited() {
        return amountDeposited;
    }

    public Map<String, Product> getProducts() {
        return products;
    }
}
