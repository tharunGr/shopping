package com.shopping.model;

import java.util.Set;
import java.util.HashSet;
import javax.persistence.*;
import java.time.LocalDateTime; 

/**
 * <p> 
 * It has fields of user details for performing various operations.   
 * </p>
 *
 * @author Tharun
 * @date   19/06/2019
 */
@Entity
@Table(name = "USER")
public class User {

    private int id;
    private String name;
    private String userName;
    private String password;
    private String email;
    private String address;
    private String mobileNumber;
    private Boolean status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Set<Role> userRoles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public int getId() {
        return id;
    }
  
    public void setId(int id) {
        this.id = id;
    }

    @Column(name="NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="USERNAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name="PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
 
    @Column(name="EMAIL")
    public String getEmail() {
        return email;
    }
  
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="ADDRESS")
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }
 
    @Column(name="MOBILE_NUMBER")
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Column(name="STATUS")
    public Boolean getStatus() {
        return status;
    }
  
    public void setStatus(Boolean status) {
        this.status = status;
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

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }
 
    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, 
    inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public String toString() { 
        return id + "" + name + "" + userName + "" + password + "" + mobileNumber + ""
               + email + "" + address + "" + status + "" + createdDate
               + "" + updatedDate + "" + userRoles; 
    } 

}





















 
