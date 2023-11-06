package com.example.test;

import com.example.dao.DatabaseConnection;
import com.example.dao.StockDao;
import com.example.entity.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;

public class StockDaoUnitTest {

    static DatabaseConnection databaseConnection;
    static Connection connection;

    @Before
    public void init() {
        databaseConnection = new DatabaseConnection("sa","","org.h2.Driver","jdbc:h2:mem:test");
        connection = databaseConnection.connect();
        databaseConnection.createDb(connection);
    }

    @Test
    public void testInsertAndGetProductQte() {
        StockDao stockDao = new StockDao();
        stockDao.setConn(connection);
        Product p1 = new Product();
        p1.setProductID("123ID");
        p1.setProductName("Gel");
        p1.setProductPrice(240);
        p1.setProductQte(20);
        stockDao.insertProduct(p1);
        int qte = stockDao.getQte(p1);
        Assert.assertEquals(20,qte);
    }

    @Test
    public void testDeleteProductQte() {
        StockDao stockDao = new StockDao();
        stockDao.setConn(connection);
        Product p1 = new Product();
        p1.setProductID("123ID");
        p1.setProductName("Gel");
        p1.setProductPrice(240);
        p1.setProductQte(20);
        stockDao.insertProduct(p1);
        stockDao.deleteProductStock(p1,5);
        int qte = stockDao.getQte(p1);
        Assert.assertEquals(15,qte);
    }

    @After
    public void closeDb() {
        databaseConnection.disconnect(connection);
    }

}
