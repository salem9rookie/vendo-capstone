package com.techelevator;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class VendLog {

    public static void main(String[] args) {
// Example transactions
        Transaction[] transactions = {
                new Transaction("FEED MONEY", 5.00, 5.00),
                new Transaction("FEED MONEY", 5.00, 10.00),
                new Transaction("Crunchie", 1.75, 8.25),
                new Transaction("Cowtales", 1.50, 6.75),
                new Transaction("GIVE CHANGE", 6.75, 0.00)
        };


     try {
        FileWriter writer = new FileWriter("Log.txt");
        for (Transaction transaction : transactions) {
            String logLine = generateLogLine(transaction.getAction(), transaction.getAmountDeposited(), transaction.getNewBalance());
            writer.write(logLine + "\n");
        }
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    public static String generateLogLine(String action, double amountDeposited, double newBalance) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        String timestamp = dateFormat.format(new Date());
        return String.format("%s %s: $%.2f $%.2f", timestamp, action, amountDeposited, newBalance);
    }







}
