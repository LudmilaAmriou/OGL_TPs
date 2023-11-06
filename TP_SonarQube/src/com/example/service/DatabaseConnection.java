package com.example.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseConnection {

    private final String user;
    private final String pass;
    private final String jdbcDriver;
    private final String dbUrl;

    public DatabaseConnection(String user, String pass, String jdbcDriver, String dbUrl) {
        this.user = user;
        this.pass = pass;
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
    }

    public Connection connect() {
        Logger logger = Logger.getAnonymousLogger();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, user, pass);
            Class.forName(jdbcDriver);
        } catch (SQLException|ClassNotFoundException e ) {
            logger.log(Level.SEVERE, "Deux exceptions SQL et class introuvable", e);
        }
        return conn;
    }

    public void disconnect(Connection conn) {
        Logger logger = Logger.getAnonymousLogger();
        try {
            conn.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "ExceptionSQL disconnect", e);
        }
    }

    public void createDb(Connection conn) {
        Logger logger = Logger.getAnonymousLogger();
        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE   City " +
                    "(idCity INTEGER not NULL, " +
                    " name VARCHAR(255), " +
                    " touristNumber INTEGER, " +
                    " description VARCHAR(255), " +
                    " PRIMARY KEY ( idCity ))";
            stmt.executeUpdate(sql);

        } catch (SQLException se) {
            logger.log(Level.SEVERE, "ExceptionSQL createDb", se);
        }
    }
}
