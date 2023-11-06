package com.example.service;

import com.example.entity.Order;
import com.example.entity.Orderline;

import java.util.List;

public interface IOrderManager {

    public boolean createOrder(Order order);

    public List<Orderline> getOrderDetails(int orderNum);

}
