package com.company;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hallo my dear user!");
        try(BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            //IP - validation pattern
            Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
            boolean exit = true;
            while (exit) {
                System.out.println("Enter IP address or 'quit' to exit");
                String ip = userInput.readLine();
                Matcher matcher = pattern.matcher(ip);
                if (ip.equals("quit")) {
                    exit = false;
                }else if (matcher.find()) {
                    //Checking blacklist file
                    if (getBlackList().contains(ip)) {
                        System.out.println("Access disallowed");
                    } else if (!getBlackList().contains(ip)){
                        System.out.println("Access allowed");
                    }
                    //Validation IP
                }else if (!matcher.find()) {
                    System.out.println("Invalid IP Address");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Reading the blacklist.txt
    public static List<String> getBlackList() {
        List<String> blacklist = new ArrayList<>();
        try (BufferedReader fileContent = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\blacklist.txt"))){
            while (fileContent.ready()){
                blacklist.add(fileContent.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blacklist;
    }
}
