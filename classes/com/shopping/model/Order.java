package com.shopping.model;

import java.util.HashSet;
import java.util.Set;
import com.shopping.model.OrderDetail;
import com.shopping.model.User;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * <p> 
 * It has fields of Total order.   
 * </p>
 *
 * @author Tharun
 * @date   21/06/2019
 */
@Entity
@Table(name = "USERORDER")
public class Order {
 
    private int id;
    private int totalPrice;
    private Boolean status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private User user;
    private Set<OrderDetail> orderItem;

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public int getId() {
        return id;
    }

    @Column(name="TOTALPRICE")
    public int getTotalPrice() {
        return totalPrice;
    }
 
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }
 
    @Column(name="STATUS")
    public Boolean getStatus() {
        return status;
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
  
    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="USER_ID")
    public User getUser() {
        return user;
    }

    public void setOrderItem(Set<OrderDetail> orderItem) {
        this.orderItem = orderItem;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    public Set<OrderDetail> getOrderItem() {
        return orderItem;
    }

    public String toString() 
    { 
        return id + "" + orderItem + "" + totalPrice + "" + user + "" + status
               + "" + updatedDate + "" + createdDate; 
    } 
}
