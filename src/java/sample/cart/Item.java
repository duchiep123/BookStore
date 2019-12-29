/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.cart;

import sample.books.BookDTO;

/**
 *
 * @author mrhie
 */
public class Item {
    BookDTO book;
    int quantity;

    public Item() {
    }

    public Item(BookDTO book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
    
}
