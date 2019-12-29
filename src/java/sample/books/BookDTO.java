/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.books;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author mrhie
 */
public class BookDTO implements Serializable {

    String bookID;

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    String bookName;
    String image;
    String description;
    float price;
    String author;
    int category;
    int quantity;
    LocalDate importDate;
    int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDate getImportDate() {
        return importDate;
    }

    public void setImportDate(LocalDate importDate) {
        this.importDate = importDate;
    }

    public BookDTO() {
    }

    public BookDTO(String bookID, int status, String bookName, String image, String description, float price, String author, int category, int quantity, LocalDate importDate) {
        this.bookID = bookID;
        this.status = status;
        this.bookName = bookName;
        this.image = image;
        this.description = description;
        this.price = price;
        this.author = author;
        this.category = category;
        this.quantity = quantity;
        this.importDate = importDate;

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

}
