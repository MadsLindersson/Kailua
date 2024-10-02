package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class CRUD {


    public static void createCar(Scanner scan, Connection connection) throws SQLException {

        String brand = Input_Validation.validateStringInput(scan, "Hvilket brand er bilen?");
        String model = Input_Validation.validateStringInput(scan, "Hvilken model er bilen?");
        String color = Input_Validation.validateStringInput(scan, "Hvilken farve er bilen?");
        int seats = Input_Validation.validateIntegerInput(scan, "Hvor mange sæder er der i bilen?");
        String leather_seats = Input_Validation.validateYesNoInput(scan, "Er der lædersæder i bilen? yes/no");
        String aircon = Input_Validation.validateYesNoInput(scan, "Har bilen aicon? yes/no");
        int gear_type_number;
        String gear_type = "";
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

        String cruise_control = Input_Validation.validateYesNoInput(scan, "Er der cruise control? yes/no");
        String fuel_type = Input_Validation.validateStringInput(scan, "Hvilken benzintype bruger bilen?");
        int horse_power = Input_Validation.validateIntegerInput(scan, "Hvor mange hestekræfter har bilen?");
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
        preparedStatement.setInt(13, odometer);
        preparedStatement.setString(14, car_type);

        preparedStatement.executeUpdate();
        System.out.println("Bilen er tilføjet til databasen");

    }

    public static void listCars(Connection connection) {

        try {
            String query = "SELECT * FROM cars";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                String color = resultSet.getString("color");
                int seats = resultSet.getInt("seats");
                String leather_seats = resultSet.getString("leather_seats");
                String aircon = resultSet.getString("aircon");
                String gear_type = resultSet.getString("gear_type");
                String cruise_control = resultSet.getString("cruise_control");
                String fuel_type = resultSet.getString("fuel_type");
                int horse_power = resultSet.getInt("horse_power");
                String license_plate = resultSet.getString("license_plate");
                LocalDate reg_date = resultSet.getDate("reg_date").toLocalDate();
                int odometer = resultSet.getInt("odometer");
                String car_type = resultSet.getString("car_type");

                System.out.println("Brand: " + brand);
                System.out.println("Model: " + model);
                System.out.println("Farve: " + color);
                System.out.println("Antal sæder: " + seats);
                System.out.println("Læder Sæder: " + leather_seats);
                System.out.println("Aircon: " + aircon);
                System.out.println("Gear Type: " + gear_type);
                System.out.println("Cruise Control: " + cruise_control);
                System.out.println("Benzin Type: " + fuel_type);
                System.out.println("Hæste Kræfter: " + horse_power);
                System.out.println("Nummer Plade: " + license_plate);
                System.out.println("Registrerings Dato: " + reg_date);
                System.out.println("Odometer: " + odometer);
                System.out.println("Bil Type: " + car_type);
                System.out.println("--------------------------------");
            }
        } catch (SQLException e) {
        }
    }

    public static void deleteCar(Scanner scan, Connection connection) {
        String license_plate = Input_Validation.validateStringInput(scan, "Hvilken bil vil du gerne slette fra databasen? (Brug nummerplade)");
        String deleteQuery = "DELETE FROM cars WHERE license_plate = ?";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, license_plate);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Bilen med nummerpladen: " + license_plate + " Er blevet slettet fra databasen.");
            } else {
                System.out.println("Ingen bil fundet med nummerpladen: " + license_plate + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCar(Scanner scan, Connection connection) {
        String license_plate = Input_Validation.validateStringInput(scan, "Hvilken bil vil du gerne ændre noget på");
        String column = null;

        List<String> validColumns = Arrays.asList("brand", "model", "color", "seats", "leather_seats", "aircon", "gear_type", "cruise_control", "fuel_type", "horse_power", "license_plate", "reg_date", "odometer", "car_type");
        while (true) {
            try {
                column = Input_Validation.validateStringInput(scan, "Hvilken bil attribut vil du gerne ændre noget i?");
                if (!validColumns.contains(column)) {
                    throw new IllegalArgumentException("Ugyldigt kolonnenavn: " + column);
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Fejl i input: " + e.getMessage() + ". Prøv igen.");
            } catch (Exception e) {
                System.out.println("En fejl opstod under inputvalideringen: " + e.getMessage() + ". Prøv igen.");
            }
        }
        String value = Input_Validation.validateStringInput(scan, "Hvad vil du gerne ændre attributten til?");
        String updateQuery = "UPDATE cars SET " + column + " = ? WHERE license_plate = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            if (Arrays.asList("seats", "horse_power", "odometer").contains(column)) {
                preparedStatement.setInt(1, Integer.parseInt(value));
            } else {
                preparedStatement.setString(1, value);
            }
            preparedStatement.setString(2, license_plate);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Bilen med nummerplade " + license_plate + " er blevet opdateret.");
            } else {
                System.out.println("Ingen bil fundet med nummerplade " + license_plate + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("En fejl opstod under opdateringen af bilen.");
        } catch (NumberFormatException e) {
            System.out.println("Ugyldig værdi for numerisk attribut: " + e.getMessage());
        }
    }

    public static void createCustomer(Scanner scan, Connection connection) {
        //driver_license_number er primary key
        try {
            String driver_license_number = Input_Validation.validateStringInput(scan, "Hvad er kørekort nr.");
            LocalDate driver_since_date = Input_Validation.validateDateInput(scan, "Hvornår er kørekortet erhvervet");
            String first_name = Input_Validation.validateStringInput(scan, "Hvad er fornavn");
            String last_name = Input_Validation.validateStringInput(scan, "Hvad er efternavn");
            String address = Input_Validation.validateStringInput(scan, "Hvad er addressen");
            String zip_code = Input_Validation.validateStringInput(scan, "Hvad er postnummer");
            String city = Input_Validation.validateStringInput(scan, "Hvad er byen");
            String phone_number = Input_Validation.validateStringInput(scan, "Hvad er telefon nummer");
            String email = Input_Validation.validateStringInput(scan, "Hvad er email");

            String query = "INSERT INTO customers (driver_license_number, driver_since_date, first_name, last_name," +
                    "address, zip_code, city, phone_number, email)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, driver_license_number);
            preparedStatement.setDate(2, Date.valueOf(driver_since_date));
            preparedStatement.setString(3, first_name);
            preparedStatement.setString(4, last_name);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, zip_code);
            preparedStatement.setString(7, city);
            preparedStatement.setString(8, phone_number);
            preparedStatement.setString(9, email);

            preparedStatement.executeUpdate();
            System.out.println("Kunde er oprettet");
        } catch (SQLException e) {
            System.out.println("Something went wrong");
            throw new RuntimeException(e);
        }
    }

    public static void listCustomer(Connection connection) {

        try {
            String query = "SELECT * FROM customers";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                System.out.printf("""
                                Navn: %s
                                Addresse: %s
                                Telefon nr.: %s
                                Email: %s
                                Driver license number: %s
                                Dato for kørerkort erhvervet: %s
                                ---------------------------------
                                """, rs.getString("first_name") + " " + rs.getString("last_name"),
                        rs.getString("address") + " " + rs.getString("zip_code") + " " + rs.getString("city"),
                        rs.getString("phone_number"), rs.getString("email"), rs.getString("driver_license_number"),
                        rs.getDate("driver_since_date").toLocalDate().toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changeCustomer(Scanner scan, Connection connection) {
        String driver_license_number = Input_Validation.validateStringInput(scan, "Hvad er kørekort nr. på kunden der skal ændres i?");

        boolean exit = true;
        try {
            String whatToChange = "";
            do {
                System.out.println("""
                        Hvad skal der ændres?
                        1. Addresse
                        2. Zip-code
                        3. By
                        4. Telefon nr.
                        5. Email""");
                int answer = scan.nextInt();
                scan.nextLine();
                switch (answer) {
                    case 1:
                        whatToChange = "address";
                        exit = false;
                        break;
                    case 2:
                        whatToChange = "zip-code";
                        exit = false;
                        break;
                    case 3:
                        whatToChange = "city";
                        exit = false;
                        break;
                    case 4:
                        whatToChange = "phone_number";
                        exit = false;
                        break;
                    case 5:
                        whatToChange = "email";
                        exit = false;
                        break;
                    default:
                        System.out.println("Something went wrong");
                }
            } while (exit);

            String changeTo = Input_Validation.validateStringInput(scan, "Hvad skal det ændres til?");

            String query = "UPDATE customers SET " + whatToChange + " = ? WHERE driver_license_number = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, changeTo);
            preparedStatement.setString(2, driver_license_number);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteCustomer(Scanner scan, Connection connection) {
        String driver_license_number = Input_Validation.validateStringInput(scan, "Hvilken kunde vil du gerne slette fra databasen? (Brug Kørekortnummer)");
        String deleteQuery = "DELETE FROM customers WHERE driver_license_number = ?";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, driver_license_number);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Kunden med kørekort nummer: " + driver_license_number + " Er blevet slettet fra databasen.");
            } else {
                System.out.println("Ingen kunde med kørekort nummer: " + driver_license_number + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listContracts(Connection connection) {

        String query = "SELECT contracts.*, cars.odometer, CONCAT(customers.first_name, ' ', customers.last_name) AS full_name, customers.address, customers.zip_code, customers.city, customers.email, customers.phone_number " +
                "FROM contracts " +
                "JOIN cars USING (license_plate) " +
                "JOIN customers USING (driver_license_number)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.printf("""
                                KONTRAKT ID: %d
                                Navn: %s
                                Adresse: %s
                                Postnummer: %s
                                By: %s
                                Tlf: %s
                                Email: %s
                                Kørekort nummer: %s
                                ---> UDLEJNINGSBIL <---
                                Nummerplade: %s
                                Start dato for udlejning: %s
                                Slut dato for udlejning: %s
                                Max km: %d
                                Start Odometer: %d
                                -----------------------------
                                """, rs.getInt("contract_id"), rs.getString("full_name"), rs.getString("address"), rs.getString("zip_code"), rs.getString("city")
                        , rs.getString("phone_number"), rs.getString("email"), rs.getString("driver_license_number")
                        , rs.getString("license_plate"), rs.getDate("start_time").toLocalDate().toString(), rs.getDate("end_time").toLocalDate().toString(), rs.getInt("max_km")
                        , rs.getInt("odometer"));

            }


        } catch (SQLException e) {
            System.out.println("Kontrakt liste kan ikke vises");
        }
    }


    public static void createContract(Scanner scan, Connection connection) {
        // contract_id er auto increment
        String license_plate = Input_Validation.validateStringInput(scan, "Hvad er bilens nummerpladen?");
        String driver_license_number = Input_Validation.validateStringInput(scan, "Hvad er kunden kørekort nummer?");
        LocalDate start_time = Input_Validation.validateDateInput(scan, "Hvad er startdatoen for udlejningen? - (yyyy-MM-dd)");
        LocalDate end_time = Input_Validation.validateDateInput(scan, "Hvad er slutdatoen for udlejningen? - (yyyy-MM-dd)");
        int max_km = Input_Validation.validateIntegerInput(scan, "Angiv max km");

        String query = "INSERT INTO contracts (license_plate, driver_license_number, start_time, end_time, max_km) " +
                "VALUES (?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, license_plate);
            preparedStatement.setString(2, driver_license_number);
            preparedStatement.setDate(3, Date.valueOf(start_time));
            preparedStatement.setDate(4, Date.valueOf(end_time));
            preparedStatement.setInt(5, max_km);

            preparedStatement.executeUpdate();
            System.out.println("Kontrakten er tilføjet til tabellen contracts i databasen");
        } catch (SQLException e) {
            System.out.println("Der opstod en fejl ved oprettelse af kontrakt: " + e.getMessage());
        }
    }

    public static void changeContract(Scanner scan, Connection connection) {

        String column = null;
        int contract_id = Input_Validation.validateIntegerInput(scan, "Angiv Kontrakt id på kontrakten du ønsker at ændre");
        int choice = 0;
        String value = "";
        boolean exitWhile = true;
        do {
            choice = Input_Validation.validateIntegerInput(scan, """
                    Skriv tallet udfra den information du ønsker at ændre i kontrakten:
                    1 - Start dato for udlejning
                    2 - Slut dato for udlejning 
                    3 - Max km. 
                    """);
            switch (choice) {
                case 1:
                    column = "start_date";
                    value = Input_Validation.validateStringInput(scan, "Hvad vil du gerne ændre start datoen til? (format: yyyy-mm-dd)");
                    exitWhile = false;
                    break;
                case 2:
                    column = "end_date";
                    value = Input_Validation.validateStringInput(scan, "Hvad vil du gerne ændre slut datoen til? (format: yyyy-mm-dd)");
                    exitWhile = false;
                    break;
                case 3:
                    column = "max_km";
                    value = Input_Validation.validateStringInput(scan, "Hvad vil du gerne ændre max km. til?");
                    exitWhile = false;
                    break;
                default:
                    System.out.println("Du kan kun taste 1, 2 eller 3.");
            }
        } while (exitWhile);

        String updateQuery = "UPDATE contracts SET " + column + " = ? WHERE contract_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, value);
            preparedStatement.setInt(2, contract_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Der er går noget galt i updaten");
        }
    }

    public static void deleteContract(Scanner scan, Connection connection) {

        int contract_id = Input_Validation.validateIntegerInput(scan, "Hvilken kontrakt vil du gerne slette? (brug kontrakt ID)");
        String deleteQuery = "DELETE FROM contracts WHERE contract_id = ?";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, contract_id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("kontrakten med ID'et: " + contract_id + " Er blevet slettet fra databasen.");
            } else {
                System.out.println("Ingen Kontrakts med ID'et: " + contract_id + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
