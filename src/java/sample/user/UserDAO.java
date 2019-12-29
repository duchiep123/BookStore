/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

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
public class UserDAO implements Serializable {

    Connection cnn = null;
    PreparedStatement pre = null;
    ResultSet rs = null;

    public UserDAO() {
    }

    public Vector<UserDTO> getAllUser() throws SQLException, NamingException {
        Vector<UserDTO> listUser = new Vector<>();
        try {
            String sql = "Select UserID, Fullname, Tel, Address from UserTable";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                UserDTO dto = new UserDTO();
                dto.setUserID(rs.getString("UserID"));
                dto.setFullname(rs.getString("Fullname"));
                dto.setTel(rs.getString("Tel"));
                dto.setAddress(rs.getString("Address"));
                listUser.add(dto);
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
        return listUser;
    }

    public UserDTO login(String userID, String password) throws SQLException, NamingException {
        UserDTO result = new UserDTO();
        try {
            String sql = "Select role, Fullname, UserID from UserTable where UserID=? and Password=?";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setString(1, userID);
            pre.setString(2, password);
            rs = pre.executeQuery();
            if (rs.next()) {
                result.setUserID(rs.getString("UserID"));
                result.setRole(rs.getInt("Role"));
                result.setFullname(rs.getString("Fullname"));
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

    public String getFullname(String userID) throws SQLException, NamingException {
        String result = "";
        try {
            String sql = "Select Fullname from UserTable where UserID=?";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setString(1, userID);
            rs = pre.executeQuery();
            if (rs.next()) {
                result = rs.getString("Fullname");
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

    public boolean createNewUser(UserDTO dto) throws SQLException, NamingException {
        boolean result = false;
        try {
            String sql = "insert into UserTable(UserID, Fullname, Tel, Address, Password, Role) values(?,?,?,?,?,?)";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setString(1, dto.getUserID());
            pre.setString(2, dto.getFullname());
            pre.setString(3, dto.getTel());
            pre.setString(4, dto.getAddress());
            pre.setString(5, dto.getPassword());
            pre.setInt(6, dto.getRole());
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

    public boolean checkDuplicate(String userID) throws SQLException, NamingException {
        boolean result = false;
        try {
            String sql = "Select UserID from UserTable where UserID=?";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setString(1, userID);
            rs = pre.executeQuery();
            if (rs.next()) {
                result = true;
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
