<%@ page contentType="text/html" pageEncoding="UTF-8" %>  
<%@ page import="com.shopping.model.User" %> 
<!DOCTYPE html> 
<html> 
    <head>
       <link rel="stylesheet" type="text/css" href="shopstyle.css">
    </head> 
    <body class="background"> 
	    <ul>         
        <li style="float:right">
            <a href="<%= request.getContextPath() %>/display-user">Cancel update</a>
        </li>
        </ul>
            <%User user =  (User)request.getAttribute("update");%> 
        <center>
         <h1>Update User </h1>  
            <form method="GET" action="update-user">
                <br>
                <input type ="hidden" name = "userId" value =<%=user.getId()%>>
                User Name :
                <input name="name" value="<%=user.getName()%>"/>
                <br>
                User Password : 
                <input name="password" value="<%=user.getPassword()%>"/>
                <br>
                User Email : 
                <input name="email" value="<%=user.getEmail()%>"/>
                <br>
                User Address : 
                <input name="address" value="<%=user.getAddress()%>"/>
                <br>
                User Mobile : 
                <input name="mobilenumber" value="<%=user.getMobileNumber()%>"/>
                <input type ="hidden" name = "status" value =<%=user.getStatus()%>>
                <input type ="hidden" name = "createdDate" value =<%=user.getCreatedDate()%>>
                <input type ="hidden" name = "updatedDate" value =<%=user.getUpdatedDate()%>>
            <br>
            <br>
            <input type="submit" value="UPDATE" /> 
        </form>
        </center>
    </body> 
</html> 
