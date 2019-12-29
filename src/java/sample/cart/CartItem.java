/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.cart;

import java.util.Vector;

/**
 *
 * @author mrhie
 */
public class CartItem {

    String customerID;
    Vector<Item> cart;

    public CartItem() {
    }

    public Vector<Item> getCart() {
        return cart;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public boolean addToCart(Item i) {
        boolean result = false;
        if (cart == null) {
            
            cart = new Vector<>();
            cart.add(i);
            result = true;
        } else {
            cart.add(i);
        }
        return result;
    }

    public boolean removeBookInCart(String bookID) {
        boolean result = false;
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getBook().getBookID().equals(bookID)) {
                cart.remove(i);
                result = true;
                break;
            }
        }
        if (cart.isEmpty()) {
            cart = null;
        }
        return result;
    }

    public boolean updateQuantity(String bookID, int quantity) {
        boolean result = false;
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getBook().getBookID().equals(bookID)) {
                int currQuantity = cart.get(i).getQuantity();
                cart.get(i).setQuantity(currQuantity + quantity);
                result = true;
                break;
            }
        }
        return result;
    }

}
