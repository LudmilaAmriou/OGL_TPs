package com.example.service;

import com.example.entity.Payment;

public interface IPayementManager {

    public void addPayement(Payment payment);

    public boolean isPaid(int orderNum);
}
