<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>  
<%@ page import="com.shopping.model.User" %> 
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
                <a href="<%= request.getContextPath() %>/create-user">Create New User</a>
        </li>
    </ul> 
    <center>    
    <h1>List of Users</h1> 
    </center>
     <%List<User> users =  (List<User>)request.getAttribute("view"); 
            for(User user : users){%>
    <div class="flip-card">
        <div class="flip-card-inner">
            <div class="flip-card-front">
                <img src="userviewimage.png" alt="Avatar" style="width:200px;height:200px;">
                  <div class="container">
                      <h4><center><b><%=user.getName()%></b></center></h4>  
                 </div>
            </div>
            <div class="flip-card-back">
                <p><b>User name : <b><%=user.getUserName()%></p> 
                <p><b>Email : <b><%=user.getEmail()%></p> 
                <p><b>Number : <b><%=user.getMobileNumber()%></p>
                <p><b>Status : <b><%=user.getStatus()%></p>
                <p><b>Created date : <b><%=user.getCreatedDate()%></p>
                <p><b>Updated Date : <b><%=user.getUpdatedDate()%></p>
                <form action="show-update-user-form" method="GET">
                    <input type ="hidden" name = "userId" value =<%=user.getId()%>>
                     <p><button>edit</button></p>
                </form>
                <form action="delete-user" method="GET">
                    <input type ="hidden" name = "userId" value =<%=user.getId()%>>
                     <p><button>remove</button></p>
                </form>
            </div>
        </div>
    </div>
    <%}%>
    </body> 
</html> 

