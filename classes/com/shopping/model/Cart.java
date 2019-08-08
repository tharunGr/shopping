package com.shopping.model;

import javax.persistence.*;
import com.shopping.model.User;
import com.shopping.model.Product;
import java.time.LocalDateTime; 

/**
 * <p> 
 * It has fields of cart for performing various operatons.   
 * </p>
 *
 * @author Tharun
 * @date   21/06/2019
 */
@Entity
@Table(name = "CART")
public class Cart {

    private int id;
    private int quantity;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Product product;
    private User user;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="QUANTITY")
    public int getQuantity() {
        return quantity;
    }
          
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name="CREATED_DATE")  
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }  
 
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name="UPDATED_DATE")
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="PRODUCT_ID")
    public Product getProduct() {
        return product;
    }
   
    public void setProduct(Product product) {
        this.product = product;
    } 
   
    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="USER_ID")
    public User getUser() {
        return user;
    }

    public String toString() {  
        return id + "" + quantity + "" + createdDate + "" + updatedDate + "" 
               + product + "" + user; 
    } 
}









