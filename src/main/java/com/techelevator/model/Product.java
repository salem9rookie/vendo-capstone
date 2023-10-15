package com.techelevator.model;

public class Product {

    private final String name;
    private final double price;
    private final String type;
    private int inventory;
    private final String slot;



    public Product(String slot, String name, double price, String type) {
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.type = type;
        this.inventory = 5;
    }

    public String getSlot() { return slot; }

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

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", inventory=" + inventory +
                ", slot='" + slot + '\'' +
                '}';
    }
}

