/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.category;

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
public class CategoryDAO implements Serializable {

    Connection cnn = null;
    PreparedStatement pre = null;
    ResultSet rs = null;

    public CategoryDAO() {
    }

    public Vector<CategoryDTO> getAllCategory() throws SQLException, NamingException {
        Vector<CategoryDTO> result = new Vector<>();
        try {
            String sql = "Select Category, Description from CategoryTable";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                CategoryDTO dto = new CategoryDTO(rs.getInt("Category"), rs.getString("Description"));
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
