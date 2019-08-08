<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
    <head>
        <link rel="stylesheet" type="text/css" href="shopstyle.css">
	<link rel="stylesheet" type="text/css" href="loginstyle.css">
    </head>
    <body class="background"> 
    <c:set var = "role" value = "${roles}"/>
        <ul>         
	        <li>  
                <a href="#">Home</a>
            </li>
            <li style="float:right">
                <a href="<%= request.getContextPath() %>/logout">Logout</a>
            </li>         
        </ul>
        <h1 class="glow">Home Page</h1> 
        <c:if test="${role == 'admin'}">   
        <div class="container">
            <a href="<%= request.getContextPath() %>/display-role">
            <img src="roleimage.png" alt="Avatar" class="image" style="width:100">   
            </a>          
            <div class="middle">
            <div class="text">Role</div>
            </div>
        </div>
        <div class="container">
            <a href="<%= request.getContextPath() %>/display-user">
            <img src="userimage.png" alt="Avatar" class="image" style="width:100">   
            </a>          
            <div class="middle">
            <div class="text">User</div>
            </div>
        </div>
        </c:if>
        <div class="container">
            <a href="<%= request.getContextPath() %>/display-product">
            <img src="productimage.png" alt="Avatar" class="image" style="width:100">   
            </a>          
            <div class="middle">
            <div class="text">Product</div>
            </div>
        </div>
        <div class="container">
            <a href="<%= request.getContextPath() %>/display-cart">
            <img src="cartimage.png" alt="Avatar" class="image" style="width:100">   
            </a>          
            <div class="middle">
            <div class="text">Cart</div>
            </div>
        </div>
        <div class="container">
            <a href="<%= request.getContextPath() %>/display-order">
            <img src="orderimage.png" alt="Avatar" class="image" style="width:100">   
            </a>          
            <div class="middle">
            <div class="text">Order</div>
            </div>
        </div>
        <!--<div class="gallery">
            <a href="<%= request.getContextPath() %>/display-role">
                 <img src="roleimage.png" alt="No image" width="700" height="800">
            </a>
            <div class="desc">Role</div>
        </div>
        <div class="gallery">
            <a href="<%= request.getContextPath() %>/display-user">
                 <img src="userimage.png" alt="No image" width="700" height="800">
            </a>
            <div class="desc">User</div>
        </div>
      
        <div class="gallery">
            <a href="<%= request.getContextPath() %>/display-product">
                 <img src="productimage.png" alt="No image" width="700" height="800">
            </a>
            <div class="desc">Product</div>
        </div>
        <div class="gallery">
            <a href="<%= request.getContextPath() %>/display-cart">
                 <img src="cartimage.png" alt="No image" width="700" height="800">
            </a>
            <div class="desc">Cart</div>
        </div>
        <div class="gallery">
            <a href="<%= request.getContextPath() %>/display-order">
                 <img src="orderimage.png" alt="No image" width="700" height="800">
            </a>
            <div class="desc">Order</div>
        </div>-->
    </body>
</html>




















