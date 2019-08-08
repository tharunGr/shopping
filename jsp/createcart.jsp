<%@ page contentType="text/html" pageEncoding="UTF-8" %>  
<%@ page import="com.shopping.model.Product" %> 
<!DOCTYPE html> 
<html>
<head>
    <link rel="stylesheet" type="text/css" href="shopstyle.css">
</head>
<body class="background">
     <ul>         
        <li style="float:right">
            <a href="<%= request.getContextPath() %>/display-cart">Cancel create</a>
        </li>
    </ul>
    <%int productId =  (int)request.getAttribute("productId");%> 
     <center>
	<h1>Create Cart</h1>
    <form method="POST" action="cart">
            <br>
            <input name="quantity" placeholder="Enter Quantity"/>
            <input type ="hidden" name = "productId" value =<%=productId%>>
            <br> 
            <br>  
            <input type="submit" value="CREATE" /> 
        </form>
    </center>
</body>
</html>
