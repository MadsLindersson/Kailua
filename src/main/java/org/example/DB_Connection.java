package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java. sql.*;
import java.util.Scanner;

public class DB_Connection {
    static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("src/main/java/org/example/TXT_Files/DB_Pass"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String URL = "jdbc:mysql://localhost:3306/kailua";
    private static final String USERNAME = "root";
    private static final String PASSWORD = scanner.next();

    public static Connection getConnection() {
        try{
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("Der opstod en fejl ved oprettelsen til databasen: " + e.getMessage());
            return null;
        }
    }
}
