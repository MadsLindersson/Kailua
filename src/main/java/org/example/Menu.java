package org.example;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public static void menu(Scanner scan) throws SQLException {
        boolean exitSoftware = false;
        do {
            System.out.println("""
                    >> MENU <<
                    
                    1 -  Biler
                    
                    2 -  Kunder
                    
                    3 -  Kontrakter
                    
                    4 -  Quit
                    
                    """);

            String answer = scan.next();

            switch (answer) {
                case "1":
                    carMenu(scan);
                    break;
                case "2":
                    customerMenu(scan);
                    break;
                case "3":
                    rentalsMenu(scan);
                    break;
                case "4":
                    exitSoftware = true;
                    break;
                default:
                    System.out.print("I did not get your answer. Try again.");
                    break;
            }
        } while (!exitSoftware);
    }

    private static void carMenu(Scanner scan) throws SQLException {
        boolean exitMenu = false;
        do {
            System.out.println("---> CARS <---");
            // Kald på metoden listCars
            System.out.println("""
                    >> FUNCTIONS<<
                    
                    1 -  Add car
                    
                    2 -  Change car
                    
                    3 -  Delete car
                    
                    4 -  Exit menu
                    
                    """);
            String answer = scan.next();

            switch (answer) {
                case "1":
                    CRUD.createCar(scan);
                    break;
                case "2":
                    //changeCar(scan);
                    break;
                case "3":
                    //deleteCar(scan);
                    break;
                case "4":
                    exitMenu = true;
                    break;
                default:
                    System.out.print("I did not get your answer. Try again.");
                    break;
            }
        } while (!exitMenu);
    }


    private static void rentalsMenu(Scanner scan) {
        boolean exitMenu = false;
        do {
            System.out.println("---> CONTRACTS <---");
            // Kald til metode i CRUD- listRentals.
            System.out.println("""
                    >> FUNCTIONS<<
                    
                    1 -  Add contract
                    
                    2 -  Change contract
                    
                    3 -  Delete contract
                    
                    4 -  Exit menu
                    
                    """);
            String answer = scan.next();

            switch (answer) {
                case "1":
                    //addContract(scan);
                    break;
                case "2":
                    //changeContract(scan);
                    break;
                case "3":
                    //deleteContract(scan);
                    break;
                case "4":
                    exitMenu = true;
                    break;
                default:
                    System.out.print("I did not get your answer. Try again.");
                    break;
            }
        } while (!exitMenu);

    }

    private static void customerMenu(Scanner scan) {
        boolean exitMenu = false;
        do {
            System.out.println("---> Customer <---");
            // Kald til metode i CRUD- list Customers.
            System.out.println("""
                    >> FUNCTIONS<<
                    
                    1 -  Add customer
                    
                    2 -  Change customer
                    
                    3 -  Delete customer
                    
                    4 -  Exit menu
                    
                    """);
            String answer = scan.next();

            switch (answer) {
                case "1":
                    //addCustomer(scan);
                    break;
                case "2":
                    //changeCustomer(scan);
                    break;
                case "3":
                    //deleteCustomer(scan);
                    break;
                case "4":
                    exitMenu = true;
                    break;
                default:
                    System.out.print("I did not get your answer. Try again.");
                    break;
            }
        } while (!exitMenu);
    }
}

