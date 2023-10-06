package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class PurchaseMenu {


    private PrintWriter out;
    private Scanner in;
    public double userMoneyFed;
    private static final double MAX_AMOUNT_USER_CAN_FEED_MACHINE = 100.00;
    private static final String FEED_MONEY_OPTION = "Feed Money";
    private static final String SELECT_PRODUCT_OPTION = "Select Product";
    private static final String FINISH_TRANSACTION_OPTION = "Finish Transaction";

    private static final Object[] PURCHASE_MENU_OPTIONS = { FEED_MONEY_OPTION, SELECT_PRODUCT_OPTION, FINISH_TRANSACTION_OPTION };
    private static final String ADD_ONE_DOLLAR = "Add $1";
    private static final String ADD_FIVE_DOLLARS = "Add $5";
    private static final String ADD_TEN_DOLLARS = "Add $10";

    private static final Object[] FEED_MONEY_OPTIONS = { ADD_ONE_DOLLAR, ADD_FIVE_DOLLARS, ADD_TEN_DOLLARS };

    PurchaseMenu purchaseMenu;

    public PurchaseMenu(InputStream input, OutputStream output){
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
        this.userMoneyFed = 0.0;
    }

    public Object getChoiceFromOptions(Object[] options){
        Object choice = null;
        while(choice == null){
            displayPurchaseMenuOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;
    }

    private Object getChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        try{
            int selectedOption = Integer.valueOf(userInput);
            if(selectedOption > 0 && selectedOption <= options.length){
                choice = options[selectedOption - 1];
            }
        }catch(NumberFormatException e){

        }
        if(choice == null){
            out.println(System.lineSeparator()+"*** "+ " is not a valid option ***" + System.lineSeparator());

        }
        return choice;
    }

    private void displayPurchaseMenuOptions(Object[] options) {
        out.println();
        for (int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            out.println("(" + optionNum + ") " + options[i]);
        }

        out.print(System.lineSeparator() + "Money inserted: " + userMoneyFed + System.lineSeparator() + "Please choose an option >>> ");
        out.flush();

        // Wait for user input
        in.nextLine();
    }

    private void feedMoney() {
        displayPurchaseMenuOptions(FEED_MONEY_OPTIONS); // Display feed money options
        Object feedMoneyChoice = getChoiceFromOptions(FEED_MONEY_OPTIONS);
        // Update userMoneyFed based on the chosen feed money option
        if (feedMoneyChoice.equals(ADD_ONE_DOLLAR)) {
            userMoneyFed += 1.00;
        } else if (feedMoneyChoice.equals(ADD_FIVE_DOLLARS)) {
            userMoneyFed += 5.00;
        } else if (feedMoneyChoice.equals(ADD_TEN_DOLLARS)) {
            userMoneyFed += 10.00;
        }

        // Limit the amount to the maximum allowed
        limitToMaxAmount();

        displayPurchaseMenuOptions(PURCHASE_MENU_OPTIONS);
    }

    private void limitToMaxAmount() {
        if (userMoneyFed > MAX_AMOUNT_USER_CAN_FEED_MACHINE) {
            out.println("You have reached the maximum allowed amount of $100.");
            out.println("No more money can be added.");
            userMoneyFed = MAX_AMOUNT_USER_CAN_FEED_MACHINE;
        }
    }


    public void run() {
        while(true) {
            String choice = (String) this.getChoiceFromOptions(PURCHASE_MENU_OPTIONS); // Use 'this' to call the method

            if (choice.equals(FEED_MONEY_OPTION)) {
                feedMoney();

            } else if (choice.equals(SELECT_PRODUCT_OPTION)) {

            } else if (choice.equals(FINISH_TRANSACTION_OPTION)) {
                // Finish transaction, refund money
            }
        }
    }


    public static void main(String[] args) {
        // Example usage
        PurchaseMenu purchaseMenu = new PurchaseMenu(System.in, System.out);

        purchaseMenu.displayPurchaseMenuOptions(PURCHASE_MENU_OPTIONS);
    }
}
