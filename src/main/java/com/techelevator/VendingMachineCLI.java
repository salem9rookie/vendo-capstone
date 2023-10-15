package com.techelevator;

import com.techelevator.interfaces.Constants;
import com.techelevator.service.VendLog;
import com.techelevator.service.VendingMachine;
import com.techelevator.view.Menu;


public class VendingMachineCLI implements Constants {


    private Menu menu;
    private VendingMachine vendingMachine;
    private static VendingMachineCLI vendingMachineCLI;

    //constructor

    public VendingMachineCLI(Menu menu, VendingMachineCLI vendingMachineCLI) {
        this.menu = menu;
        this.vendingMachineCLI = vendingMachineCLI;
        this.vendingMachine = new VendingMachine(menu);
    }

    //main method

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu, vendingMachineCLI);
        cli.run();
    }

    //methods

    public void run() {
        vendingMachine.loadProducts("vendingmachine.csv");
        vendingMachine.displayBanner();

        while (true) {

            System.out.printf("Current Money Provided: $%.2f", vendingMachine.getBalance());
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            switch (choice) {
                case MAIN_MENU_OPTION_DISPLAY_ITEMS:
                    displayProducts();
                    break;
                case MAIN_MENU_OPTION_PURCHASE:
                    displayPurchaseMenu();
                    break;
                case MAIN_MENU_OPTION_EXIT:
                    System.out.println("Here is your change.");
                    VendingMachine.giveChange(vendingMachine.getBalance(), 0.00);
                    VendLog.generateLogLine("GIVE CHANGE", vendingMachine.getBalance(), 0.00);
                    System.exit(-1);
            }
        }
    }

    private void displayPurchaseMenu() {
        vendingMachine.displayPurchaseMenu();
    }

    public void displayProducts() {
        vendingMachine.displayProducts();
    }

}




