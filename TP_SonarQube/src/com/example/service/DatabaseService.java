package com.example.service;

import com.example.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseService {

    public static final Logger logger = Logger.getAnonymousLogger(); //fixe
    private Connection connection;
    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public  void addCity(City city) {
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO City " + "VALUES (?,?,?,?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, city.getIdCity());
            pstmt.setString(2, city.getName());
            pstmt.setInt(3, city.getTouristNumber());
            pstmt.setString(4, city.getDescription());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException se) {
            logger.log(Level.SEVERE, "erreurSQL ptstmt1", se);
        } finally {
            try {
                if (pstmt != null) {pstmt.close();}
            } catch (SQLException se2) {
                logger.log(Level.SEVERE, "erreurSQL pstmt2", se2);
            }
        }
    }


   public City getCity(int idCity) {
       List<City> cities = this.getCities();
       int i = 0;
       City city= null;
       boolean find = false;
       while (i < cities.size() && !find){
           if (cities.get(i).getIdCity() == idCity){
               find = true;
               city = cities.get(i);
           }
           i= i+1;
       }
       return  city;
    }


    public  List<City> getCities() {
        Statement stmt = null;
        List<City> cities = new ArrayList<>();
        try {

            String sql = "SELECT * FROM City";
            stmt = connection.createStatement();
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    City city = new City();
                    city.setIdCity(rs.getInt("idCity"));
                    city.setName(rs.getString("name"));
                    city.setTouristNumber(rs.getInt("touristNumber"));
                    city.setDescription(rs.getString("description"));
                    cities.add(city);
                }
            } finally {
                if (rs != null){ rs.close();}
                stmt.close();
            }
        } catch (SQLException se) {
            logger.log(Level.SEVERE, "context", se);
        } finally {
            try {
                if (stmt != null) {stmt.close();}
            } catch (SQLException se2) {
                logger.log(Level.SEVERE, "SQLException2", se2);
            }
        }
        return  cities;
    }
}
