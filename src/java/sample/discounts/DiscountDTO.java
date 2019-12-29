/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.discounts;

import java.io.Serializable;

/**
 *
 * @author mrhie
 */
public class DiscountDTO implements Serializable{
    String discountCode;
    float value;
    boolean status;
    String userID;

    public DiscountDTO() {
    }

    public DiscountDTO(String discountCode, float value, boolean status, String userID) {
        this.discountCode = discountCode;
        this.value = value;
        this.status = status;
        this.userID = userID;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    
    
    
}
