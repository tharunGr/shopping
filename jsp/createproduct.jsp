<!DOCTYPE html> 
<html>
<head>
    <link rel="stylesheet" type="text/css" href="shopstyle.css">
</head>
<body class="background">
     <ul>         
        <li style="float:right">
            <a href="<%= request.getContextPath() %>/display-product">Cancel create</a>
        </li>
    </ul>
    <center>
	<h1>Create Product</h1>  
        <form method="POST" action="product">
            <br>
            <input name="name" placeholder="Product Name"/>
            <br> 
            <br> 
            <input name="description" placeholder="Product Description"/>
            <br>
            <br> 
            <input name="price" placeholder="Product Price"/>
            <br>
            <br> 
            <input type="submit" value="CREATE" /> 
        </form>
    </center>
</body>
</html>
