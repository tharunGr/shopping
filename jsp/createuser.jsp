<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>  
<%@ page import="com.shopping.model.Role" %>
<!DOCTYPE html> 
<html>
<head>
    <link rel="stylesheet" type="text/css" href="shopstyle.css">
</head>
<body class="background">
     <ul>         
        <li style="float:right">
            <a href="<%= request.getContextPath() %>/display-user">Cancel create</a>
        </li>
    </ul>
    <center>
    <h1>Create User</h1>
    <form method="POST" action="user">
        <input name="name" placeholder="Name"/>
        <br>
        <br>
        <input name="userName" placeholder="User Name"/>
        <br>
        <br>
        <input name="password" placeholder="Password"/>
        <br>
        <br>
        <input name="email" placeholder="Email Id"/>
        <br> 
        <br>
        <input name="mobilenumber" placeholder="Mobile Number"/>
        <br>
        <br>
        <input name="address" placeholder="Address"/>
        <br>
        <br>
        <p>Select roles for User</p>
        <%List<Role> roles =  (List<Role>)request.getAttribute("viewroles"); 
        for(Role role : roles){%>
        <input type="checkbox" name="roleDetails" value="<%=role.getId()%>"><%=role.getName()%>
        <%}%>
        <input type="submit" value="CREATE" /> 
    </form>
    </center>
</body>
</html>
