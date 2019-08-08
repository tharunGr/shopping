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
        <link rel="stylesheet" type="text/css" href="viewrole.css">
    </head>	
    <body class="background">
     <ul>         
        <li>
            <a href="<%= request.getContextPath() %>/shop.jsp"">Home</a>
        </li>
    </ul>
        <center>
        <h1>Your Order History</h1> 
        <table border=1 cellpadding=3> 
            <tr> 
                <th><b>Id</b></th> 
                <th><b>Total Price</b></th> 
                <th><b>Status</b></th> 
                <th><b>Created Date</b></th>
                <th><b>Updated Date</b></th>
                <th><b>User Id</b></th>
                <th><b>Delete</b></th>
            </tr>  
            <%List<Order> orders =  (List<Order>)request.getAttribute("view"); 
                for(Order order : orders){%>  
            <tr> 
                <td><%=order.getId()%></td> 
                <td><%=order.getTotalPrice()%></td> 
                <td><%=order.getStatus()%></td> 
                <td><%=order.getCreatedDate()%></td>
                <td><%=order.getUpdatedDate()%></td>
                <td><%=order.getUser().getId()%></td>
                <td>
                    <form action="delete-order" method="GET">
                        <input type ="hidden" name = "orderId" value =<%=order.getId()%>>
                        <input type = "submit" value = "remove">
                    </form>
                </td>
            </tr> 
            <%}%> 
        </table>   
    </center>
    </body> 
</html> 

