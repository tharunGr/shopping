<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>  
<%@ page import="com.shopping.model.User" %> 
<%@ page import="com.shopping.model.Cart" %>
<%@ page import="com.shopping.model.Order" %>
<%@ page import="com.shopping.model.Product" %>
<!DOCTYPE html>  
<html>  
    <head>
        <link rel="stylesheet" type="text/css" href="shopstyle.css">
    </head>	
    <body class="background">
     <ul>         
        <li style="float:right">
            <a href="<%= request.getContextPath() %>/display-order">Cancel create</a>
        </li>
    </ul>
    <center>
         <h1>Create Order</h1>  
        <form method="POST" action="order">
            <br>
            <h2>Choose carts to add make new order<h2>
            <%List<Cart> carts =  (List<Cart>)request.getAttribute("viewcarts"); 
                for(Cart cart : carts){%>  
             <input type="checkbox" name="orderDetails" value="<%=cart.getId()%>"><%=cart.getProduct().getName()%><br>
            <%}%>
            <br>
            <br>
            <input type="submit" value="ORDER NOW" /> 
        </form>
    <center>
