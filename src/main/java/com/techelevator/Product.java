package com.techelevator;

public class Product {

    private String name;
    private double price;
    private String type;
    private int inventory;



    public Product(String name, double price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.inventory = 5;  // Start with an inventory of 5 for each product
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getInventory() {
        return inventory;
    }

    public void decreaseInventory() {
       inventory--;
    }
}

