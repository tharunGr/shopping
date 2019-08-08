package com.shopping.model;

import javax.persistence.*;
import java.time.LocalDateTime; 

/**
 * <p> 
 * It has fields of product details for performing various operatons.   
 * </p>
 *
 * @author Tharun
 * @date   20/06/2019
 */
@Entity
@Table(name = "PRODUCT")
public class Product {

    private int id;
    private String name;
    private String description;
    private int price;
    private Boolean status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private User user;

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

    public void setPrice(int price) {
        this.price = price;
    }

    @Column(name="PRICE")
    public int getPrice() {
        return price;
    }

    @Column(name="STATUS")  
    public Boolean getStatus() {
        return status;
    }
  
    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name="CREATED_DATE")
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
 
    public void setUpdatedDate(LocalDateTime updateDate) {
        this.updatedDate = updateDate;
    }

    @Column(name="UPDATED_DATE")
     public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="USER_ID")
    public User getUser() {
        return user;
    }

     public String toString() 
    { 
        return id + "" + name + "" + description + ""
               + price + "" + createdDate + "" + updatedDate + ""
               + status + "" + user; 
    } 
}
 




















    
