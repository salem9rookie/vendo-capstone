package com.techelevator;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class VendLog {

    private static FileWriter writer;
//    public static void log(String message){
//        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File("Log.txt"), true));){
//            pw.println(generateLogLine());
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
    public static void generateLogLine(String action, double amountDeposited, double newBalance) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        String timestamp = dateFormat.format(new Date());
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File("Log.txt"), true))){
            pw.println(String.format("%s %s: $%.2f $%.2f", timestamp, action, amountDeposited, newBalance));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }}













