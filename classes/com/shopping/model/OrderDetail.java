package com.shopping.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import com.shopping.model.Order;

/**
 * <p> 
 * It has fields of order details.   
 * </p>
 *
 * @author Tharun
 * @date   21/06/2019
 */
@Entity
@Table(name = "ORDERDETAIL")
public class OrderDetail {

    private int id;
    private int quantity;
    private Product product;
    private Order order;

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

    public void setProduct(Product product) {
        this.product = product;
    }    
 
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="PRODUCT_ID")
    public Product getProduct() {
        return product;
    }

    @ManyToOne
    @JoinColumn(name = "ORDER_ID") 
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String toString() {  
        return id + "" + quantity + "" + product + "" + order;            
    } 
}









