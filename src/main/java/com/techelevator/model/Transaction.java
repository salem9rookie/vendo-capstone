package com.techelevator.model;

public class Transaction {

    private final String action;
    private final double amountDeposited;
    private final double newBalance;

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

