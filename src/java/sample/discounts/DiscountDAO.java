/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.discounts;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import sample.db.MyConnection;

/**
 *
 * @author mrhie
 */
public class DiscountDAO implements Serializable {

    Connection cnn = null;
    PreparedStatement pre = null;
    ResultSet rs = null;

    public DiscountDAO() {
    }

    public DiscountDTO checkDiscount(String discountCode) throws SQLException, NamingException {
        DiscountDTO discount = null;
        try {
            String sql = "Select DiscountCode,Value from DiscountTable where DiscountCode=? and Status=1";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setString(1, discountCode);
            rs = pre.executeQuery();
            if (rs.next()) {
                discount = new DiscountDTO();
                discount.setDiscountCode(rs.getString("DiscountCode"));
                discount.setValue(rs.getFloat("Value"));
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
        return discount;
    }

    public boolean useDiscount(String userID, String disCode) throws SQLException, NamingException {
        boolean result = false;
        try {
            String sql = "update DiscountTable set UserID=?, Status =0 where DiscountCode=?";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setString(1, userID);
            pre.setString(2, disCode);
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
