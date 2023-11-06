package com.example.test;

import com.example.dao.DatabaseConnection;
import com.example.dao.OrderDao;
import com.example.dao.StockDao;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.Orderline;
import com.example.entity.Product;
import com.example.service.OrderManager;
import com.example.service.StockManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OrderManagerIntegrationTest {

    static DatabaseConnection databaseConnection;
    static Connection connection;
    static OrderDao orderDao;
    static StockDao stockDao;
    static StockManager stockManager;
    static OrderManager orderManager;

    @BeforeClass
    public static void init() {
        databaseConnection = new DatabaseConnection("sa","","org.h2.Driver","jdbc:h2:mem:test");
        connection = databaseConnection.connect();
        databaseConnection.createDb(connection);
        orderDao = new OrderDao();
        orderDao.setConn(connection);
        stockDao = new StockDao();
        stockDao.setConn(connection);
        stockManager = new StockManager();
        stockManager.setStockDao(stockDao);
        orderManager = new OrderManager();
        orderManager.setOrderDao(orderDao);
        orderManager.setiStockManager(stockManager);
    }

    @Test
    public void testCreateOrder() {
        // create Products
        Product p1 = new Product();
        p1.setProductID("123ID");
        p1.setProductName("product 1");
        p1.setProductPrice(120);
        p1.setProductQte(20);
        stockManager.addProduct(p1);

        Product p2 = new Product();
        p2.setProductID("523ID");
        p2.setProductName("Product 2");
        p2.setProductPrice(150);
        p2.setProductQte(15);
        stockManager.addProduct(p2);

        // order data
        Customer c1 = new Customer();
        c1.setCustomerID("485");
        Orderline ord1 = new Orderline();
        Orderline ord2 = new Orderline();
        Product p3 = new Product();
        p3.setProductID("123ID");

        Product p4 = new Product();
        p4.setProductID("523ID");
        ord1.setProduct(p3);
        ord1.setOrderedQte(3);
        ord2.setProduct(p4);
        ord2.setOrderedQte(5);
        List<Orderline> orderlineList = new ArrayList<>();
        orderlineList.add(ord1);
        orderlineList.add(ord2);
        Order order = new Order();
        order.setOrderNum(1);
        order.setCustomer(c1);
        order.setOrderDate("2022-12-01");
        order.setOrderlines(orderlineList);
        orderManager.createOrder(order);
        int qte1 = stockManager.getProductQte(p1);
        int qte2 = stockManager.getProductQte(p2);
        List<Orderline> result = orderManager.getOrderDetails(1);
        Assert.assertArrayEquals(orderlineList.toArray(),result.toArray());
        Assert.assertEquals(17,qte1);
        Assert.assertEquals(10,qte2);

    }


    @AfterClass
    public static  void closeDb() {
        databaseConnection.disconnect(connection);
    }
}
