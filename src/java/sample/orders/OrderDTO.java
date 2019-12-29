/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.orders;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author mrhie
 */
public class OrderDTO implements Serializable {

    int orderID;
    float TotalPrice;
    String customerID;
    LocalDateTime createDate;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, float TotalPrice, String customerID, LocalDateTime createDate) {
        this.orderID = orderID;
      
        this.TotalPrice = TotalPrice;
        this.customerID = customerID;
        this.createDate = createDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }


    public float getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(float TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

}
