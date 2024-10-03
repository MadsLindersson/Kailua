package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

// todo: ret til ift prompten - 1: yes - 2: no
// todo: prompten skal være på dansk
// todo: ændre så man ikke skal skrive sport
// todo: lav pænere formatering på listCostumers()
// todo: kig på ændre kontrakt metode - formatering af date objektet

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DB_Connection.getConnection();
        Scanner scan = new Scanner(System.in);
        Menu.menu(scan, connection);
    }
}