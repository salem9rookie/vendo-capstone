package com.techelevator.view;
import com.techelevator.Product;
import com.techelevator.view.Menu;


public class MakeChange {

    public static void main(String[] args) {
        giveChange(5.59, 1.55);
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

//    public void purchaseItem(){
//        //user selects item
//
//        //depending on type, the item says something.
//        switch (type){
//            case CHIPS:
//
//                System.out.println("Crunch, Crunch, Yum!");
//                break;
//            case CANDY:
//                System.out.println("Munch, Munch, Yum!");
//                break;
//            case DRINK:
//                System.out.println("Glug, Glug, Yum!");
//                break;
//            case GUM:
//                System.out.println("Chew, Chew, Yum!");
//                break;
//        }
//        //user balance decreases
//        decreaseInventory();
//
//    }

//    for(
//    Product product: products.values()) {
//        if (selectedProduct.toUpperCase() == null) {
//            System.out.println("Invalid entry");
//        } else if ((selectedProduct.toUpperCase().equals(products.containsValue(true)))) {
//            System.out.println("Im over here");
//
//        } else {
//            System.out.println("No, over here!");
//        }
//    }
//


}
