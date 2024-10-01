package org.example;

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
    public static Date validateDateInput(Scanner scan, String prompt){
        while (true){
            System.out.println(prompt);
            try{
                return new Date(scan.nextLine());
            }catch (DateTimeParseException e){
                System.out.println("Indtast venligst en dato i format - YYYY-MM-DD");
            }
        }
    }
}
