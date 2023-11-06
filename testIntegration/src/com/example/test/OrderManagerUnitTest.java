package com.example.test;

import com.example.dao.OrderDao;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.Orderline;
import com.example.entity.Product;
import com.example.service.IStockManager;;
import com.example.service.OrderManager;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderManagerUnitTest {

    @Test
    public void testCreateOrder() {
        // Data
        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = new Product();
        p1.setProductID("123ID");
        p2.setProductID("323ID");
        p3.setProductID("423ID");
        Customer c1 = new Customer();
        c1.setCustomerID("485");
        Orderline ord1 = new Orderline();
        Orderline ord2 = new Orderline();
        Orderline ord3 = new Orderline();
        ord1.setProduct(p1);
        ord1.setOrderedQte(3);
        ord2.setProduct(p2);
        ord2.setOrderedQte(6);
        ord3.setProduct(p3);
        ord3.setOrderedQte(2);
        List<Orderline> orderlineList = new ArrayList<>();
        orderlineList.add(ord1);
        orderlineList.add(ord2);
        orderlineList.add(ord3);
        Order order = new Order();
        order.setOrderNum(1);
        order.setCustomer(c1);
        order.setOrderDate("12/01/2022");
        order.setOrderlines(orderlineList);
        // Create mocks
        OrderDao orderDaoMock = Mockito.mock(OrderDao.class);
        IStockManager stockManagerMock = Mockito.mock(IStockManager.class);
        when(stockManagerMock.getProductQte(p1)).thenReturn(15);
        when(stockManagerMock.getProductQte(p2)).thenReturn(10);
        when(stockManagerMock.getProductQte(p3)).thenReturn(2);
        // create OrderManager
        OrderManager orderManager = new OrderManager();
        orderManager.setiStockManager(stockManagerMock);
        orderManager.setOrderDao(orderDaoMock);
        orderManager.createOrder(order);
        // verify calls
        verify(orderDaoMock).insertOrder(order);
        verify(stockManagerMock).removeProductStock(p1,3);
        verify(stockManagerMock).removeProductStock(p2,6);
        verify(stockManagerMock).removeProductStock(p3,2);

    };
}
