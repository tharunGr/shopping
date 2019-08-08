<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>  
<%@ page import="com.shopping.model.User" %> 
<%@ page import="com.shopping.model.Product" %>
<!DOCTYPE html> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>  
    <head>
        <link rel="stylesheet" type="text/css" href="viewrole.css">
    </head>	
    <body class="background">
    <c:set var = "role" value = "${roles}"/>
     <ul>         
        <li>
            <a href="<%= request.getContextPath() %>/shop.jsp"">Home</a>
        </li>
        <c:if test="${role == 'admin'}">
        <li style="float:right">
                <a href="<%= request.getContextPath() %>/createproduct.jsp">Create Product</a>
        </li>
         </c:if>
    </ul> 
                <center>
        <h1>List of Products</h1> 
        </center>
        <%List<Product> products =  (List<Product>)request.getAttribute("view"); 
                for(Product product : products){%>  
        <div class="card">
            <h1><%=product.getName()%></h1>
            <p class="price">$<%=product.getPrice()%></p>
             <c:if test="${role == 'admin'}">
            <form action="delete-product" method="GET">
                <input type ="hidden" name = "productId" value =<%=product.getId()%>>
                <p><button>Delete</button></p>
            </form>
            <form action="show-update-product-form" method="GET">
                <input type ="hidden" name = "productId" value =<%=product.getId()%>>
                <p><button>Update</button></p>
            </form>
            </c:if>
            <form action="create-cart" method="GET">
                <input type ="hidden" name = "productId" value =<%=product.getId()%>>
                 <p><button>Add to Cart</button></p>
            </form>           
            <br>
        </div> 
      <%}%> 
    </body> 
</html> 

