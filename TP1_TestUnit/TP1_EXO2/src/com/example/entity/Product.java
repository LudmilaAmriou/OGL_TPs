package com.example.entity;

public class Product {

    private String productID;
    private String productName;
    private float productPrice;
    private int productQte;



    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQte() {
        return productQte;
    }

    public void setProductQte(int productQte) {
        this.productQte = productQte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product  = (Product) o;
        return productID.equals(product.productID) &&
               productName==product.productName  &&
               productPrice == product.productPrice &&
               productQte == product.productQte;
    }
}
