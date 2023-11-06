package com.example.service;

import com.example.dao.StockDao;
import com.example.entity.Product;

public class StockManager implements IStockManager {

    private StockDao stockDao;

    @Override
    public int getProductQte(Product product) {
        return stockDao.getQte(product);
    }

    @Override
    public void removeProductStock(Product product, int qte) {
        if(qte>0) {
            stockDao.deleteProductStock(product, qte);
        }
        }

    @Override
    public void addProduct(Product product) {
        stockDao.insertProduct(product);
    }


    public StockDao getStockDao() {
        return stockDao;
    }

    public void setStockDao(StockDao stockDao) {
        this.stockDao = stockDao;
    }
}
