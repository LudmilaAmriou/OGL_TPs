package com.example.service;

import com.example.entity.Product;

public interface IStockManager {



    public int  getProductQte(Product product) ;

    public void removeProductStock(Product product, int qte) ;

    public void addProductStock(Product product, int qte) ;


}
