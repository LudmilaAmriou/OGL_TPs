package com.example.entity;

public class Orderline {

    private Product product ;
    private int orderedQte;




    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getOrderedQte() {
        return orderedQte;
    }

    public void setOrderedQte(int orderedQte) {
        this.orderedQte = orderedQte;
    }

       @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Orderline orderline = (Orderline) o;
        return product.equals(orderline.product) &&
               orderedQte == orderline.orderedQte;
    }
}
