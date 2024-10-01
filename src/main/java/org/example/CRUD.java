package org.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class CRUD {

    public static void createCar(Scanner scan) throws SQLException {
        Connection connection = DB_Connection.getConnection();

        String brand = Input_Validation.validateStringInput(scan, "Hvilket brand er bilen?");
        String model = Input_Validation.validateStringInput(scan, "Hvilken model er bilen?");
        String color  = Input_Validation.validateStringInput(scan, "Hvilken farve er bilen?");
        int seats = Input_Validation.validateIntegerInput(scan, "Hvor mange sæder er der i bilen?");
        String leather_seats = Input_Validation.validateStringInput(scan, "Er der lædersæder i bilen? yes/no");
        String aircon = Input_Validation.validateStringInput(scan, "Har bilen aicon? yes/no");
        int gear_type_number;
        String gear_type = "";
        //Skal måske rettes - Mads
        boolean check = true;
        do {
            gear_type_number = Input_Validation.validateIntegerInput(scan, "Hvilken geartype har bilen 1: Manuel 2: Automatisk ");
            if (gear_type_number == 1) {
                gear_type = "manuel";
                check = false;
            } else if (gear_type_number == 2) {
                gear_type = "automatic";
                check = false;
            } else {
                System.out.println("Wrong input");
            }
        } while (check);

        String cruise_control = Input_Validation.validateStringInput(scan, "Er der cruise control? yes/no");
        String fuel_type = Input_Validation.validateStringInput(scan, "Hvilken benzintype bruger bilen?");
        int horse_power = Input_Validation.validateIntegerInput(scan,"Hvor mange hestekræfter har bilen?");
        String license_plate = Input_Validation.validateStringInput(scan, "Hvad er nummerpladen?");
        LocalDate reg_date = Input_Validation.validateDateInput(scan, "Hvornår er bilen registreret - (yyyy-MM-dd)");
        System.out.println(reg_date);
        int odometer = Input_Validation.validateIntegerInput(scan, "Hvor meget har bilen kørt?");
        String car_type = Input_Validation.validateStringInput(scan, "Hvilken type er bilen?");

        String query = "INSERT INTO cars (brand, model, color, seats, leather_seats, aircon, gear_type, " +
                "cruise_control, fuel_type, horse_power, license_plate, reg_date, odometer, car_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, brand);
        preparedStatement.setString(2, model);
        preparedStatement.setString(3, color);
        preparedStatement.setInt(4, seats);
        preparedStatement.setString(5, leather_seats);
        preparedStatement.setString(6, aircon);
        preparedStatement.setString(7, gear_type);
        preparedStatement.setString(8, cruise_control);
        preparedStatement.setString(9, fuel_type);
        preparedStatement.setInt(10, horse_power);
        preparedStatement.setString(11, license_plate);
        preparedStatement.setDate(12, Date.valueOf(reg_date));
        System.out.println(reg_date);
        preparedStatement.setInt(13, odometer);
        preparedStatement.setString(14, car_type);





        preparedStatement.executeUpdate();
        System.out.println("Bilen er tilføjet til databasen");

    }
}
