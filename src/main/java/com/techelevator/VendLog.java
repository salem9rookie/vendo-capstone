package com.techelevator;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class VendLog {

    private FileWriter writer;

    public VendLog() {
        try {
            writer = new FileWriter("Log.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logTransaction(Transaction transaction) {
        try {
            String logLine = generateLogLine(transaction.getAction(), transaction.getAmountDeposited(), transaction.getNewBalance());
            writer.write(logLine + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateLogLine(String action, double amountDeposited, double newBalance) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        String timestamp = dateFormat.format(new Date());
        return String.format("%s %s: $%.2f $%.2f", timestamp, action, amountDeposited, newBalance);
    }

    public void closeLogger() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Create a new instance of VendLog
        VendLog vendLog = new VendLog();

        // Log each transaction
        Transaction[] transactions = new Transaction[0];
        for (Transaction transaction : transactions) {
            vendLog.logTransaction(transaction);
        }

        // Close the logger
        vendLog.closeLogger();
    }
}











