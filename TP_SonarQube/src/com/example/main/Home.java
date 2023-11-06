package com.example.main;

import com.example.model.City;
import com.example.repository.CityManager;
import com.example.service.DatabaseConnection;
import com.example.service.DatabaseService;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Home {
    public static final Logger logger = Logger.getAnonymousLogger(); //fixe
    private static final String USER = getEncryptedUser();
    private static final String PASSWORD = getEncryptedUser();
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/citiesdb";

    public static String getEncryptedUser(){
        return "root";
    }

    public static void main(String[] args) { //fixe
        DatabaseConnection db = new DatabaseConnection(USER, PASSWORD, JDBC_DRIVER, DB_URL);
        Connection conn = db.connect();
        try {
            if (args.length > 0) {
                int id = Integer.parseInt(args[0]);
                String name = args[1];
                int numTourist = Integer.parseInt(args[2]);
                String desc = args[3];
                City city1 = new City(id, name, numTourist, desc);
                DatabaseService databaseService = new DatabaseService();
                databaseService.setConnection(conn);
                CityManager cityManager = new CityManager();
                cityManager.setDatabaseService(databaseService);
                cityManager.addCity(city1);
            }
        }
        catch (NumberFormatException e) {
            logger.log(Level.SEVERE,"Arguments" + args[0] + args[2] + " must be an integer.");
            System.exit(1);
        }
    }
}
