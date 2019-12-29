/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.orderdetails;

import java.io.Serializable;

/**
 *
 * @author mrhie
 */
public class OrderDetailDTO implements Serializable {

    int orderID;
    String bookID;
    int quantity;
    float unitPrice;
    String bookName;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderID, String bookID, int quantity, float unitPrice, String bookName) {
        this.orderID = orderID;
        this.bookID = bookID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.bookName = bookName;

    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

}
