package com.techelevator.interfaces;

public interface Constants {
     String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
     String MAIN_MENU_OPTION_PURCHASE = "Purchase";
     String MAIN_MENU_OPTION_EXIT = "Exit";
     String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
     String FEED_MONEY_OPTION = "Feed Money";
     String SELECT_PRODUCT_OPTION = "Select Product";
     String FINISH_TRANSACTION_OPTION = "Finish Transaction";
     String[] PURCHASE_MENU_OPTIONS = {FEED_MONEY_OPTION, SELECT_PRODUCT_OPTION, FINISH_TRANSACTION_OPTION};
     String ADD_ONE_DOLLAR = "Add $1";
     String ADD_FIVE_DOLLARS = "Add $5";
     String ADD_TEN_DOLLARS = "Add $10";
     String[] MONEY_FEED_OPTIONS = {ADD_ONE_DOLLAR, ADD_FIVE_DOLLARS, ADD_TEN_DOLLARS, FINISH_TRANSACTION_OPTION};
}

