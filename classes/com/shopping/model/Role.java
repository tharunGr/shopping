package com.shopping.model;

import javax.persistence.*;
import java.time.LocalDateTime; 

/**
 * <p> 
 * It has fields of role for performing various operatons.   
 * </p>
 *
 * @author Tharun
 * @date   21/06/2019
 */
@Entity
@Table(name = "ROLE")
public class Role {

    private int id;
    private String name;
    private String description;
    private Boolean status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
  
    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="NAME")
    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
 
    @Column(name="STATUS")
    public Boolean getStatus() {
        return status;
    } 

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name="CREATED_DATE")
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }   

    @Column(name="UPDATED_DATE")
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public String toString() {  
        return id + "" + name + "" + description + "" + status + "" + createdDate
               + "" + updatedDate;
    } 
}










