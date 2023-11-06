package com.example.dao;

import com.example.entity.Order;
import com.example.entity.Orderline;
import com.example.entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private Connection conn;



    public void insertOrder(Order order) {

        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            // disable autocommit
           conn.setAutoCommit(false);
            // insert order
           String sql1 = "INSERT INTO orders (orderNum,customerID) " + "VALUES (?,?); " ;
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setInt(1, order.getOrderNum());
            pstmt1.setString(2, order.getCustomer().getCustomerID());
            pstmt1.executeUpdate();
            // Insert orderlines
            String sql2 = " INSERT INTO orderlines (orderNum,productID,orderedQte) " + "VALUES (?,?,?) ; " ;
            pstmt2 = conn.prepareStatement(sql2);
            for (Orderline orderline : order.getOrderlines()) {
                pstmt2.setInt(1,order.getOrderNum());
                pstmt2.setString(2,orderline.getProduct().getProductID());
                pstmt2.setInt(3,orderline.getOrderedQte());
                pstmt2.addBatch();
            }
            pstmt2.executeBatch();

            conn.commit();
            pstmt1.close();
            pstmt2.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }  finally {
            try {
                if (pstmt1 != null) pstmt1.close();
                if (pstmt2 != null) pstmt2.close();
            } catch (SQLException se2) {

            }
        }

    }



    public List<Orderline> getOrderDetails(int orderNum) {
        PreparedStatement pstmt = null;
        List<Orderline> orderlines = null;
        int i = -1;
        try {
            String sql = "SELECT * FROM orderlines WHERE orderNum=? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,orderNum);
            ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    if(orderlines==null) {
                       orderlines = new ArrayList();
                    }
                    Orderline orderline = new Orderline();
                    Product product = new Product();
                    product.setProductID(rs.getString("productID"));
                    orderline.setProduct(product);
                    orderline.setOrderedQte(rs.getInt("orderedQte"));
                    orderlines.add(orderline);
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
        return orderlines;
    }




    public void deleteOrder(int orderNum) {
        int i =0;
        int j =0;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            // disable autocommit
            conn.setAutoCommit(false);
            // remove order
            String sql1 = "DELETE FROM orders where orderNum=?" ;
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setInt(1,orderNum);
            pstmt1.executeUpdate();
            // remove orderlines
            String sql2 = " DELETE FROM orderlines WHERE orderNum=?" ;
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setInt(1,orderNum);
            pstmt2.executeUpdate();
            conn.commit();
        } catch (SQLException se) {
            se.printStackTrace();
        }  finally {
            try {
                if (pstmt1 != null) pstmt1.close();
                if (pstmt2 != null) pstmt2.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
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
