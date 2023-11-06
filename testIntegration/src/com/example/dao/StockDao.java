package com.example.dao;

import com.example.entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockDao {

    private Connection conn;

    public void insertProduct(Product product) {
        PreparedStatement pstmt1 = null;;
        try {

            String sql1 = "INSERT INTO products (productID,productName,productPrice,productQte) " + "VALUES (?,?,?,?); " ;
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setString(1, product.getProductID());
            pstmt1.setString(2, product.getProductName());
            pstmt1.setDouble(3, product.getProductPrice());
            pstmt1.setInt(4, product.getProductQte());
            pstmt1.executeUpdate();
            conn.commit();
            pstmt1.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }  finally {
            try {
                if (pstmt1 != null) pstmt1.close();
            } catch (SQLException se2) {

            }
        }

    }


    public void  insertProductStock(Product product,int productQte) {

    }

    public int  getQte(Product product) {
        PreparedStatement pstmt = null;
        int qte = 0;
        int i = -1;
        try {
            String sql = "SELECT productQte FROM products WHERE productID=? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,product.getProductID());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                qte = rs.getInt("productQte");
            }


        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException se2) {
            }
        }
        return qte;
    }


    public void deleteProductStock(Product product,int qte) {

        PreparedStatement pstmt1 = null;;
        try {
            String sql1 = "UPDATE products SET productQte=productQte-? WHERE productID=?;" ;
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setInt(1, qte);
            pstmt1.setString(2, product.getProductID());
            pstmt1.executeUpdate();
            conn.commit();
            pstmt1.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }  finally {
            try {
                if (pstmt1 != null) pstmt1.close();
            } catch (SQLException se2) {

            }
        }
    }


    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }



}
