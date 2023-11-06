package com.test;

import com.example.model.City;
import com.example.repository.CityManager;
import com.example.service.DatabaseConnection;
import com.example.service.DatabaseService;
import org.junit.*;
import java.sql.Connection;
import java.util.List;


public class CityManagerTest {
    Connection conn;
    DatabaseConnection db;
    public static final String USER = "sa"; //add final
    public static final String PASS = "";
    public static final String JDBC_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:mem:test";

    @Before
    public  void setUp(){
        db = new DatabaseConnection(USER,PASS,JDBC_DRIVER,DB_URL);
        conn = db.connect();
        db.createDb(conn);
    }



   @Test
    public void testAddAndGetCity(){
        City city = new City(1,"Alger",300000000,"Belle ville");
        DatabaseService databaseService = new DatabaseService();
        databaseService.setConnection(conn);
        CityManager cityManager = new CityManager();
        cityManager.setDatabaseService(databaseService);
        cityManager.addCity(city);
        City city2 = cityManager.getCity(1);
        Assert.assertEquals(city2,city);
    }


    @Test
    public void testGetCities(){
        City city1 = new City(1,"Alger",300000000,"Belle ville");
        City city2 = new City(2,"Oran",300000000,"Belle ville");
        City city3 = new City(3,"Annaba",300000000,"Belle ville");
        City[] expectedCities = {city1,city2,city3};
        DatabaseService databaseService = new DatabaseService();
        databaseService.setConnection(conn);
        CityManager cityManager = new CityManager();
        cityManager.setDatabaseService(databaseService);
        cityManager.addCity(city1);
        cityManager.addCity(city2);
        cityManager.addCity(city3);
        List<City> cities =  cityManager.getCities();
        Assert.assertArrayEquals(cities.toArray(),expectedCities);
    }

    @After
    public  void tearDown(){
        db.disconnect(conn);
    }

}