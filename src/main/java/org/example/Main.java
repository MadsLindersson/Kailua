package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DB_Connection.getConnection();
        Scanner scan = new Scanner(System.in);
        Menu.menu(scan, connection);
    }
}