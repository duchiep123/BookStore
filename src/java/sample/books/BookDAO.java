/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.books;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
public class BookDAO implements Serializable {

    Connection cnn = null;
    PreparedStatement pre = null;
    ResultSet rs = null;

    public BookDAO() {
    }

    public Vector<BookDTO> searchBookByLikeName(String searchValue) throws SQLException, NamingException {
        Vector<BookDTO> result = new Vector<>();
        try {
            String sql = "Select BookID, BookName, Description, Price, Author, Category, Image, Quantity, Status, ImportDate from BookTable where BookName LIKE ? and Quantity >0 and Status=1";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setString(1, "%" + searchValue + "%");
            rs = pre.executeQuery();
            while (rs.next()) {
                BookDTO dto = new BookDTO();
                dto.setBookID(rs.getString("BookID"));
                dto.setBookName(rs.getString("BookName"));
                dto.setDescription(rs.getString("Description"));
                dto.setPrice(rs.getFloat("Price"));
                dto.setAuthor(rs.getString("Author"));
                dto.setCategory(rs.getInt("Category"));
                dto.setImage(rs.getString("Image"));
                dto.setQuantity(rs.getInt("Quantity"));
                dto.setStatus(rs.getInt("Status"));
                dto.setImportDate(rs.getDate("ImportDate").toLocalDate());
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

    public Vector<BookDTO> searchBookByCategory(int searchValue) throws SQLException, NamingException {
        Vector<BookDTO> result = new Vector<>();
        try {
            String sql = "Select BookID, BookName, Description, Price, Author, Category, Image, Quantity, Status, ImportDate  from BookTable where Category=? and Quantity >0 and Status=1";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setInt(1, searchValue);
            rs = pre.executeQuery();
            while (rs.next()) {
                BookDTO dto = new BookDTO();
                dto.setBookID(rs.getString("BookID"));
                dto.setBookName(rs.getString("BookName"));
                dto.setDescription(rs.getString("Description"));
                dto.setPrice(rs.getFloat("Price"));
                dto.setAuthor(rs.getString("Author"));
                dto.setCategory(rs.getInt("Category"));
                dto.setImage(rs.getString("Image"));
                dto.setQuantity(rs.getInt("Quantity"));
                dto.setStatus(rs.getInt("Status"));
                dto.setImportDate(rs.getDate("ImportDate").toLocalDate());
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

    public Vector<BookDTO> searchBookByPrice(float minPrice, float maxPrice) throws SQLException, NamingException {
        Vector<BookDTO> result = new Vector<>();
        try {
            String sql = "Select BookID, BookName, Description, Price, Author, Category, Image, Quantity, Status, ImportDate from BookTable where Price >= ? and Price <= ? and Quantity >0 and Status=1";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setFloat(1, minPrice);
            pre.setFloat(2, maxPrice);
            rs = pre.executeQuery();
            while (rs.next()) {
                BookDTO dto = new BookDTO();
                dto.setBookID(rs.getString("BookID"));
                dto.setBookName(rs.getString("BookName"));
                dto.setDescription(rs.getString("Description"));
                dto.setPrice(rs.getFloat("Price"));
                dto.setAuthor(rs.getString("Author"));
                dto.setCategory(rs.getInt("Category"));
                dto.setImage(rs.getString("Image"));
                dto.setQuantity(rs.getInt("Quantity"));
                dto.setStatus(rs.getInt("Status"));
                dto.setImportDate(rs.getDate("ImportDate").toLocalDate());
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

    public boolean removeBook(String bookID) throws SQLException, NamingException {
        boolean result = false;
        try {

            String sql = "Update BookTable set Status =2 where BookID= ?";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setString(1, bookID);
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

    public boolean updateBook(BookDTO dto) throws SQLException, NamingException {
        boolean result = false;
        try {
            String sql = "Update BookTable set BookName=?, Price=?, Author=?, Category=?, Quantity=? where BookID=?";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setString(1, dto.getBookName());
            pre.setFloat(2, dto.getPrice());
            pre.setString(3, dto.getAuthor());
            pre.setInt(4, dto.getCategory());
            pre.setInt(5, dto.getQuantity());
            pre.setString(6, dto.getBookID());
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

    public boolean insertNewBook(BookDTO newBook) throws SQLException, NamingException {
        boolean result = false;
        try {
            String sql = "insert into BookTable(BookID, BookName, Description, Category, Price, Status, Author, Image, Quantity, ImportDate) values(?,?,?,?,?,?,?,?,?,?)";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setString(1, newBook.getBookID());
            pre.setString(2, newBook.getBookName());
            pre.setString(3, newBook.getDescription());
            pre.setInt(4, newBook.getCategory());
            pre.setFloat(5, newBook.getPrice());
            pre.setInt(6, newBook.getStatus());
            pre.setString(7, newBook.getAuthor());
            pre.setString(8, newBook.getImage());
            pre.setInt(9, newBook.getQuantity());
            pre.setDate(10, Date.valueOf(newBook.getImportDate()));
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

    public BookDTO getBookByID(String bookID) throws SQLException, NamingException {
        BookDTO dto = null;
        try {
            String sql = "Select BookID, BookName, Description, Price, Author, Category, Image, Quantity, Status, ImportDate from BookTable where BookID=?";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setString(1, bookID);
            rs = pre.executeQuery();
            if (rs.next()) {
                dto = new BookDTO();
                dto.setBookID(rs.getString("BookID"));
                dto.setBookName(rs.getString("BookName"));
                dto.setDescription(rs.getString("Description"));
                dto.setPrice(rs.getFloat("Price"));
                dto.setAuthor(rs.getString("Author"));
                dto.setCategory(rs.getInt("Category"));
                dto.setImage(rs.getString("Image"));
                dto.setQuantity(rs.getInt("Quantity"));
                dto.setStatus(rs.getInt("Status"));
                dto.setImportDate(rs.getDate("ImportDate").toLocalDate());
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
        return dto;
    }

    public int getMaxQuantity(String bookID) throws SQLException, NamingException {
        int result = -1;
        try {
            String sql = "Select Quantity from BookTable where BookID=?";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setString(1, bookID);
            rs = pre.executeQuery();
            if (rs.next()) {
                result = rs.getInt("Quantity");
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

    public boolean updateQuantityAfterShopping(String bookID, int quantity) throws SQLException, NamingException {
        boolean result = false;
        try {
            String sql = "update BookTable set Quantity =Quantity - ? where BookID=? ";
            cnn = MyConnection.makeConnection();
            pre = cnn.prepareStatement(sql);
            pre.setInt(1, quantity);
            pre.setString(2, bookID);
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
