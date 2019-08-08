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
            <a href="<%= request.getContextPath() %>/display-role">Cancel update</a>
        </li>
        </ul>
        <%Role role =  (Role)request.getAttribute("update");%>
	    <center>
        <h1>Update Role</h1>  
            <form method="GET" action="update-role">
                <input type ="hidden" name = "roleId" value =<%=role.getId()%>>
                Role Name :
                <input name="name" title="Role Name" value="<%=role.getName()%>"/>
                <br>
                <br>
                Role Description : 
                <input name="description" title="Role Description" value="<%=role.getDescription()%>"/>
                <input type ="hidden" name = "status" value =<%=role.getStatus()%>>
                <input type ="hidden" name = "createdDate" value =<%=role.getCreatedDate()%>>
                <input type ="hidden" name = "updateDate" value =<%=role.getUpdatedDate()%>>
            <br>
            <br>
            <input type="submit" value="UPDATE" /> 
        </form>
        </center>
    </body> 
</html> 
