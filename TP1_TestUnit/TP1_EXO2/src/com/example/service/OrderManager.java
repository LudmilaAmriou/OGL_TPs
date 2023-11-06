package com.example.service;

import com.example.dao.OrderDao;
import com.example.entity.Order;
import com.example.entity.Orderline;

import java.util.List;


public class OrderManager {

    private OrderDao orderDao;
    private IStockManager iStockManager;
    private IPayementManager iPayementManager;





    public IPayementManager getiPayementManager() {
        return iPayementManager;
    }

    public void setiPayementManager(IPayementManager iPayementManager) {
        this.iPayementManager = iPayementManager;
    }

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




    public boolean cancelOrder(int orderNum) {
        boolean isPaid = iPayementManager.isPaid(orderNum);
        if(isPaid) {
            return false;
        }
        List<Orderline> orderlines = orderDao.getOrderDetails(orderNum);
        orderDao.deleteOrder(orderNum);
        for (int i = 0; i < orderlines.size(); i++) {
            iStockManager.addProductStock(orderlines.get(i).getProduct(), orderlines.get(i).getOrderedQte());
        }
        return true;

    }








}
