package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class Input_Validation {

    public static String validateStringInput(Scanner scan, String prompt){
        System.out.println(prompt);
        return scan.nextLine();
    }

    public static int validateIntegerInput(Scanner scan, String prompt){
        while (true){
            System.out.println(prompt);
            try {
                return Integer.parseInt(scan.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Indtast venligst et heltal");
            }
        }
    }
    public static double validateDoubleInput(Scanner scan, String prompt){
        while (true){
            System.out.println(prompt);
            try{
                return Double.parseDouble(scan.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Indtast venligst et decimaltal");
            }
        }
    }
    public static LocalDate validateDateInput(Scanner scan, String prompt) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.println(prompt);
            try {
                return LocalDate.parse(scan.nextLine(), dateTimeFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Indtast venligst en dato i format - YYYY-MM-DD");
            }
        }
    }
}
