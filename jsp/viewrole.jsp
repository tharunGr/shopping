<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>  
<%@ page import="com.shopping.model.Role" %> 
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
        <li style="float:right">
                <a href="<%= request.getContextPath() %>/createrole.jsp">Create New Role</a>
        </li>
    </ul>
        <center>
            <h1>List of Roles</h1>
        <table border=1 cellpadding=3>
        <tr id="roletable">
            <th>Role Id</th>
            <th>Role Name</th>
            <th>Role Description</th>
            <th>Status</th>
            <th>Created Date</th>
            <th>Updated Date</th>
            <th>Delete</th>
            <th>Update</th>  
        </tr>
        <%List<Role> roles =  (List<Role>)request.getAttribute("view"); 
                for(Role role : roles){%>
            <tr>
                <td><%=role.getId()%></td>
                <td><%=role.getName()%></td>
                <td><%=role.getDescription()%></td>
                <td><%=role.getStatus()%></td>
                <td><%=role.getCreatedDate()%></td>
                <td><%=role.getUpdatedDate()%></td>
                <td>
                    <form action="delete-role" method="GET">
                        <input type ="hidden" name = "roleId" value =<%=role.getId()%>>
                        <input type = "submit" value = "remove">
                    </form>
                </td>
                <td>
                     <form action="show-update-role-form" method="GET">
                        <input type ="hidden" name = "roleId" value =<%=role.getId()%>>
                        <input type = "submit" value = "edit">
                    </form>
                </td>
            </tr>
        <%}%>
        </table>
    </center>  
    </body> 
</html> 

