<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>  
<%@ page import="com.shopping.model.User" %> 
<%@ page import="com.shopping.model.Product" %>
<%@ page import="com.shopping.model.Cart" %>
<!DOCTYPE html> 
<html>  
    <head>
        <link rel="stylesheet" type="text/css" href="viewrole.css">
    </head>	
    <body class="background">
     <ul>         
        <li>
            <a href="<%= request.getContextPath() %>/shop.jsp"">Home</a>
        </li>
    </ul> 
	<center>
        <h1>List of Carts</h1> 
        <form action="order" method="POST">
        <table border=1 cellpadding=3> 
            <tr id="roletable"> 
                <th><b>Id</b></th> 
                <th><b>Quantity</b></th> 
                <th><b>Created Date</b></th>
                <th><b>Updated Date</b></th>
                <th><b>User Id</b></th>
                <th><b>Product Id</b></th>
                <th><b>Update</b></th>
                <th><b>Delete</b></th>
                <th><b>Choose to order</b></th>
            </tr>  
            <%List<Cart> carts =  (List<Cart>)request.getAttribute("view"); 
                for(Cart cart : carts){%>  
            <tr> 
                <td><%=cart.getId()%></td> 
                <td><%=cart.getQuantity()%></td> 
                <td><%=cart.getCreatedDate()%></td>
                <td><%=cart.getUpdatedDate()%></td>
                <td><%=cart.getUser().getId()%></td>
                <td><%=cart.getProduct().getId()%></td>
                <form></form>
                <td>
                    <form action="show-update-cart-form" method="GET">
                        <input type ="hidden" name = "cartId" value =<%=cart.getId()%>>
                        <input type = "submit" value = "edit">
                    </form>
                </td>
                <td>
                   <form action="delete-cart" method="GET">
                        <input type ="hidden" name = "cartId" value =<%=cart.getId()%>>
                        <input type = "submit" value = "remove">
                    </form>
                </td>                   
                <td>
                    <input type="checkbox" name="orderDetails" value="<%=cart.getId()%>"><br>     
                </td>
            </tr> 
            <%}%> 
        </table>  
        <br>
            <input type = "submit" value = "Order">
        </form> 
    </center>
    </body> 
</html> 












