package Test;

import com.example.dao.DatabaseConnection;
import com.example.dao.OrderDao;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.Orderline;
import com.example.entity.Product;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OrderDaoTest {
    static DatabaseConnection db;
    static Connection connect;

    @org.junit.jupiter.api.BeforeAll
    static void setup(){
        db = new DatabaseConnection("sa","","org.h2.Driver","jdbc:h2:mem:test");
        connect = db.connect();
        db.createDb(connect);
    }


    @org.junit.jupiter.api.Test
    void insertOrder() {
        Customer client = new Customer();
        client.setCustomerID("client1");
        Order command = new Order();
        command.setOrderNum(1);
        Product produit = new Product();
        produit.setProductID("produit1");
        Orderline ligne = new Orderline();
        ligne.setOrderedQte(1);
        ligne.setProduct(produit);
        List<Orderline> listproduit = new ArrayList<>();
        listproduit.add(ligne);
        command.setCustomer(client);
        command.setOrderDate("jeudi");
        command.setOrderlines(listproduit);
        OrderDao order = new OrderDao();
        order.setConn(connect);
        order.insertOrder(command);
        List<Orderline> result = order.getOrderDetails(1);
        assertEquals(result.get(0),ligne); //assertNull(result)
    }

    @org.junit.jupiter.api.Test
    void deleteOrder() {
        Customer client = new Customer();
        client.setCustomerID("client1");
        Order command = new Order();
        command.setOrderNum(1);
        Product produit = new Product();
        produit.setProductID("produit1");
        Orderline ligne = new Orderline();
        ligne.setOrderedQte(1);
        ligne.setProduct(produit);
        List<Orderline> listproduit = new ArrayList<>();
        listproduit.add(ligne);
        command.setCustomer(client);
        command.setOrderDate("jeudi");
        command.setOrderlines(listproduit);
        OrderDao order = new OrderDao();
        order.setConn(connect);
        order.insertOrder(command);
        order.deleteOrder(1);
        List<Orderline> result = order.getOrderDetails(1);
        assertNull(result);
    }
}