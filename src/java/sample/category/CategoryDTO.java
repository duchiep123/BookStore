/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.category;

import java.io.Serializable;

/**
 *
 * @author mrhie
 */
public class CategoryDTO implements Serializable{
    int category;
    String description;

    public CategoryDTO() {
    }

    public CategoryDTO(int category, String description) {
        this.category = category;
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
