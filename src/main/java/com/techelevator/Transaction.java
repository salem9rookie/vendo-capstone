package com.techelevator;

public class Transaction {

    private String action;
    private double amountDeposited;
    private double newBalance;

    public Transaction(String action, double amountDeposited, double newBalance) {
        this.action = action;
        this.amountDeposited = amountDeposited;
        this.newBalance = newBalance;
    }

    public String getAction() {
        return action;
    }

    public double getAmountDeposited() {
        return amountDeposited;
    }

    public double getNewBalance() {
        return newBalance;
    }

}
