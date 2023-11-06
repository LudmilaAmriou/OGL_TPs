package com.example.test;

import com.example.dao.DatabaseConnection;
import com.example.dao.OrderDao;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.Orderline;
import com.example.entity.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class OrderDaoUnitTest {

    static DatabaseConnection databaseConnection;
    static Connection connection;

    @Before
    public void init() {
        databaseConnection = new DatabaseConnection("sa","","org.h2.Driver","jdbc:h2:mem:test");
        connection = databaseConnection.connect();
        databaseConnection.createDb(connection);
    }

    @Test
    public void testInsertAndGetOrder() {
        OrderDao orderDao = new OrderDao();
        orderDao.setConn(connection);
        Product p1 = new Product();
        p1.setProductID("123ID");
        Product p2 = new Product();
        p2.setProductID("523ID");
        Customer c1 = new Customer();
        c1.setCustomerID("485");
        Orderline ord1 = new Orderline();
        Orderline ord2 = new Orderline();
        ord1.setProduct(p1);
        ord1.setOrderedQte(3);
        ord2.setProduct(p2);
        ord2.setOrderedQte(6);
        List<Orderline> orderlineList = new ArrayList<>();
        orderlineList.add(ord1);
        orderlineList.add(ord2);
        Order order = new Order();
        order.setOrderNum(1);
        order.setCustomer(c1);
        order.setOrderDate("12/01/2022");
        order.setOrderlines(orderlineList);
        orderDao.insertOrder(order);
        List<Orderline> result = orderDao.getOrderDetails(1);
        Assert.assertArrayEquals(orderlineList.toArray(),result.toArray());

    }

    @Test
    public void deleteOrder() {
        OrderDao orderDao = new OrderDao();
        orderDao.setConn(connection);
        Product p1 = new Product();
        p1.setProductID("123ID");
        Customer c1 = new Customer();
        c1.setCustomerID("485");
        Orderline ord1 = new Orderline();
        ord1.setProduct(p1);
        ord1.setOrderedQte(3);
        List<Orderline> orderlineList = new ArrayList<>();
        orderlineList.add(ord1);
        Order order = new Order();
        order.setOrderNum(1);
        order.setCustomer(c1);
        order.setOrderDate("12/01/2022");
        order.setOrderlines(orderlineList);
        orderDao.insertOrder(order);
        orderDao.deleteOrder(1);
        List<Orderline> result = orderDao.getOrderDetails(1);
        Assert.assertNull(result);

    }

    @After
    public void closeDb() {
      databaseConnection.disconnect(connection);
    }


}