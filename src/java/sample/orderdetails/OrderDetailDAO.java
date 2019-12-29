/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.orderdetails;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;
import sample.db.MyConnection;

/**
 *
 * @author mrhie
 */
public class OrderDetailDAO implements Serializable {

    Connection cnn = null;
    PreparedStatement pre = null;
    ResultSet rs = null;

    public OrderDetailDAO() {
    }

    public Vector<OrderDetailDTO> getOrderDetailByOrderID(String orderID) throws SQLException, NamingException {
        Vector<OrderDetailDTO> result = new Vector<>();
        try {
            String sql = "Select BookName, Quantity, UnitPrice from OrderDetailTable where OrderID=?";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setString(1, orderID);
            rs=pre.executeQuery();
            while(rs.next()){
                OrderDetailDTO dto=new OrderDetailDTO();
                dto.setBookName(rs.getString("BookName"));
                dto.setQuantity(rs.getInt("Quantity"));
                dto.setUnitPrice(rs.getFloat("UnitPrice"));
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

    public boolean createNewOrderDetail(OrderDetailDTO dto) throws SQLException, NamingException {
        boolean result = false;
        try {
            String sql = "insert into OrderDetailTable(OrderID, BookID, Quantity, UnitPrice,BookName) values(?,?,?,?,?)";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setInt(1, dto.getOrderID());
            pre.setString(2, dto.getBookID());
            pre.setInt(3, dto.getQuantity());
            pre.setFloat(4, dto.getUnitPrice());
            pre.setString(5, dto.getBookName());
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

}
