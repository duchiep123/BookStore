/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.orders;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Vector;
import javax.naming.NamingException;
import sample.db.MyConnection;

/**
 *
 * @author mrhie
 */
public class OrderDAO implements Serializable {

    Connection cnn = null;
    PreparedStatement pre = null;
    ResultSet rs = null;

    public OrderDAO() {
    }

    public int getNewestOrderID() throws SQLException, NamingException {
        int orderId = -1;
        try {
            cnn = MyConnection.makeConnection();
            String sql = "Select MAX(OrderID) as OrderID from OrderTable";

            pre = cnn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                orderId = rs.getInt("OrderID");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return orderId;
    }

    public boolean createNewOrder(OrderDTO dto) throws SQLException, NamingException {
        boolean result = false;

        try {
            String sql = "insert into OrderTable(TotalPrice, CustomerID, CreateDate) values(?,?,?)";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setFloat(1, dto.getTotalPrice());
            pre.setString(2, dto.getCustomerID());
            pre.setObject(3, dto.getCreateDate());
            result = pre.executeUpdate() > 0;

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return result;
    }

    public Vector<OrderDTO> searchOrderByDate(String cusID) throws SQLException, NamingException {
        Vector<OrderDTO> result = new Vector<>();
        try {
            String sql = "Select OrderID, TotalPrice, CreateDate from OrderTable where CustomerID=?";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setString(1, cusID);
            rs = pre.executeQuery();
            while (rs.next()) {
                OrderDTO dto = new OrderDTO();
                dto.setOrderID(rs.getInt("OrderID"));
                dto.setTotalPrice(rs.getFloat("TotalPrice"));
                String date = rs.getObject("CreateDate").toString();
                date = date.replace(" ", "T");
                dto.setCreateDate(LocalDateTime.parse(date));
                result.add(dto);
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return result;
    }

    public Vector<OrderDTO> showHistory(String customerID) throws SQLException, NamingException {
        Vector<OrderDTO> result = new Vector<>();
        try {
            String sql = "select OrderID, TotalPrice, CreateDate from OrderTable where CustomerID=?";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setString(1, customerID);
            rs = pre.executeQuery();
            while (rs.next()) {

                OrderDTO dto = new OrderDTO();
                dto.setOrderID(rs.getInt("OrderID"));
                dto.setTotalPrice(rs.getFloat("TotalPrice"));
                String date = rs.getObject("CreateDate").toString();
                date = date.replace(" ", "T");
                dto.setCreateDate(LocalDateTime.parse(date));
                dto.setTotalPrice(rs.getFloat("TotalPrice"));
                result.add(dto);
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return result;
    }

}
