package com.example.test;

import com.example.dao.DatabaseConnection;
import com.example.dao.OrderDao;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.Orderline;
import com.example.entity.Product;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderDaoIntegrationTest {

    static DatabaseConnection databaseConnection;
    static Connection connection;
    static OrderDao orderDao;

    @BeforeClass
    public static void init() {
        databaseConnection = new DatabaseConnection("root","root","com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/dbogl");
        connection = databaseConnection.connect();
         orderDao = new OrderDao();
        orderDao.setConn(connection);
    }

    @Test
    public void testAddAndGetOrder() {

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
        order.setOrderDate("01/12/2021");
        order.setOrderlines(orderlineList);
        orderDao.insertOrder(order);
        List<Orderline> result = orderDao.getOrderDetails(1);
        Assert.assertArrayEquals(orderlineList.toArray(),result.toArray());

    }

    @Test
    public void testDeleteOrder() {
        orderDao.deleteOrder(1);
        List<Orderline> result = orderDao.getOrderDetails(1);
        Assert.assertNull(result);

    }

    @AfterClass
    public static  void closeDb() {
        databaseConnection.disconnect(connection);
    }
}
