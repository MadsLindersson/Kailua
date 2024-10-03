package org.example;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public static void menu(Scanner scan, Connection connection) throws SQLException {
        boolean exitSoftware = false;
        do {
            System.out.println("""
                    >> MENU <<
                    
                    1 -  Biler
                    
                    2 -  Kunder
                    
                    3 -  Kontrakter
                    
                    4 -  Sluk
                    
                    """);

            String answer = scan.next();

            switch (answer) {
                case "1":
                    carMenu(scan, connection);
                    break;
                case "2":
                    customerMenu(scan, connection);
                    break;
                case "3":
                    contractMenu(scan,connection);
                    break;
                case "4":
                    exitSoftware = true;
                    break;
                default:
                    System.out.print("Forkert input, prøv igen");
                    break;
            }
        } while (!exitSoftware);
    }

    private static void carMenu(Scanner scan, Connection connection) throws SQLException {
        boolean exitMenu = false;
        do {
            System.out.println("---> CARS <---");
            CRUD.listCars(connection);
            System.out.println("""
                    >> FUNCTIONS<<
                    
                    1 -  Opret bil
                    
                    2 -  Ændr bil
                    
                    3 -  Slet bil
                    
                    4 -  Gå tilbage
                    
                    """);
            String answer = scan.next();
            scan.nextLine();

            switch (answer) {
                case "1":
                    CRUD.createCar(scan, connection);
                    break;
                case "2":
                    CRUD.updateCar(scan, connection);
                    break;
                case "3":
                    CRUD.deleteCar(scan, connection);
                    break;
                case "4":
                    exitMenu = true;
                    break;
                default:
                    System.out.print("Forkert input, prøv igen");
                    break;
            }
        } while (!exitMenu);
    }


    private static void contractMenu(Scanner scan, Connection connection) throws SQLException {
        boolean exitMenu = false;
        do {
            System.out.println("---> CONTRACTS <---");
            CRUD.listContracts(connection);
            System.out.println("""
                    >> FUNCTIONS<<
                    
                    1 -  Opret kontrakt
                    
                    2 -  Ændr kontrakt
                    
                    3 -  Slet kontrakt
                    
                    4 -  Gå tilbage
                    
                    """);
            String answer = scan.next();
            scan.nextLine();

            switch (answer) {
                case "1":
                    CRUD.createContract(scan,connection);
                    break;
                case "2":
                    CRUD.changeContract(scan, connection);
                    break;
                case "3":
                    CRUD.deleteContract(scan, connection);
                    break;
                case "4":
                    exitMenu = true;
                    break;
                default:
                    System.out.print("Forkert input, prøv igen");
                    break;
            }
        } while (!exitMenu);

    }

    private static void customerMenu(Scanner scan, Connection connection) throws SQLException {
        boolean exitMenu = false;
        do {
            System.out.println("---> Customer <---");
            CRUD.listCustomer(connection);
            System.out.println("""
                    >> FUNCTIONS<<
                    
                    1 -  Opret kunde
                    
                    2 -  Ændr kunde
                    
                    3 -  Slet kunde
                    
                    4 -  Gå tilbage
                    
                    """);
            String answer = scan.next();
            scan.nextLine();
            switch (answer) {
                case "1":
                    CRUD.createCustomer(scan,connection);
                    break;
                case "2":
                    CRUD.changeCustomer(scan, connection);
                    break;
                case "3":
                    CRUD.deleteCustomer(scan, connection);
                    break;
                case "4":
                    exitMenu = true;
                    break;
                default:
                    System.out.print("Forkert input, prøv igen");
                    break;
            }
        } while (!exitMenu);
    }
}

