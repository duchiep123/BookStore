/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.status;

import java.io.Serializable;

/**
 *
 * @author mrhie
 */
public class StatusDTO implements Serializable{
    int status;
    String description;

    public StatusDTO() {
    }

    public StatusDTO(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
