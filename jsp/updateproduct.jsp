<%@ page contentType="text/html" pageEncoding="UTF-8" %>  
<%@ page import="com.shopping.model.Product" %> 
<%@ page import="com.shopping.model.User" %>
<!DOCTYPE html> 
<html> 
    <head>
       <link rel="stylesheet" type="text/css" href="shopstyle.css">
    </head> 
    <body class="background"> 
	     <ul>         
        <li style="float:right">
            <a href="<%= request.getContextPath() %>/display-product">Cancel Update</a>
        </li>
        </ul>
	    <center>
        <h1>Update Product Detail</h1> 
            <%Product product =  (Product)request.getAttribute("update");%>  
            <form method="GET" action="update-product">
                <br>
                <input type ="hidden" name = "productId" value =<%=product.getId()%>>
                Product Name :
                <input name="name" value="<%=product.getName()%>"/>
                <br>
                <br>
                Product Description : 
                <input name="description" value="<%=product.getDescription()%>"/>
                <br>
                <br>
                Product Price : 
                <input name="price" value="<%=product.getPrice()%>"/>
                <br>
                <br>
                <input type ="hidden" name = "status" value =<%=product.getStatus()%>>
                <input type ="hidden" name = "createdDate" value =<%=product.getCreatedDate()%>>
                <input type ="hidden" name = "updatedDate" value =<%=product.getUpdatedDate()%>>
                <input type ="hidden" name = "userId" value =<%=product.getUser().getId()%>>
            <input type="submit" value="UPDATE" /> 
        </form>
        </center>
    </body> 
</html> 
