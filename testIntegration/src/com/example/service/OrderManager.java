package com.example.service;

import com.example.dao.OrderDao;
import com.example.entity.Order;
import com.example.entity.Orderline;

import java.util.List;


public class OrderManager implements IOrderManager {

    private OrderDao orderDao;
    private IStockManager iStockManager;



    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public IStockManager getiStockManager() {
        return iStockManager;
    }



    public void setiStockManager(IStockManager iStockManager) {
        this.iStockManager = iStockManager;
    }


    @Override
    public boolean createOrder(Order order) {
        for(Orderline orderline:order.getOrderlines()) {
            int qteStock = iStockManager.getProductQte(orderline.getProduct());
            if(qteStock<orderline.getOrderedQte()) {
                return false;
            }
        }
        orderDao.insertOrder(order);
        List<Orderline> orderlines = order.getOrderlines();
        for (int i = 0; i < orderlines.size(); i++) {
            iStockManager.removeProductStock(orderlines.get(i).getProduct(), orderlines.get(i).getOrderedQte());
        }
        return true;

    }
    /*
        public void createOrder(Order order) {
             boolean existStock = true;
             for(Orderline orderline:order.getOrderlines()) {
                 int qteStock = iStockManager.getProductQte(orderline.getProduct());
                 if (qteStock < orderline.getOrderedQte()) {
                     existStock = false;
                     break;
                 }
             }
                    if(existStock) {
                     orderDao.insertOrder(order);
                     List<Orderline> orderlines = order.getOrderlines();
                     for (int i = 0; i < orderlines.size(); i++) {
                         iStockManager.removeProductStock(orderlines.get(i).getProduct(), orderlines.get(i).getOrderedQte());
                     }
                 }

    }

    */

    @Override
    public List<Orderline> getOrderDetails(int orderNum) {
        return orderDao.getOrderDetails(orderNum);
    }
}
