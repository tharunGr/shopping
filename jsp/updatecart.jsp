<%@ page contentType="text/html" pageEncoding="UTF-8" %>  
<%@ page import="com.shopping.model.Product" %> 
<%@ page import="com.shopping.model.User" %>
<%@ page import="com.shopping.model.Cart" %>
<!DOCTYPE html> 
<html> 
    <head>
       <link rel="stylesheet" type="text/css" href="shopstyle.css">
    </head> 
    <body class="background"> 
	    <ul>         
        <li style="float:right">
            <a href="<%= request.getContextPath() %>/display-cart">Cancel update</a>
        </li>
        </ul>
	    <center>
        <h1>Update Cart Quantity</h1> 
            <%Cart cart =  (Cart)request.getAttribute("update");%>  
            <form method="GET" action="update-cart">
                <br>
                <br>
                <input type ="hidden" name = "cartId" value =<%=cart.getId()%>>
                Product Quantity : 
                <input name="quantity" value="<%=cart.getQuantity()%>"/>
                <br>
                <br>
                <input type ="hidden" name = "createdDate" value =<%=cart.getCreatedDate()%>>
                <input type ="hidden" name = "updatedDate" value =<%=cart.getUpdatedDate()%>>
                <input type ="hidden" name = "userId" value =<%=cart.getUser().getId()%>>
                <input type ="hidden" name = "productId" value =<%=cart.getProduct().getId()%>>
            <input type="submit" value="UPDATE" /> 
        </form>
        </center>
    </body> 
</html> 
