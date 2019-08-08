<html>
<head>
    <link rel="stylesheet" type="text/css" href="shopstyle.css">
</head>
<body class="background">
     <ul>         
        <li style="float:right">
            <a href="<%= request.getContextPath() %>/display-role">Cancel create</a>
        </li>
    </ul>
    <center>
	<h1>Create Role</h1>
    <form method="POST" action="role">
	    <input name="name" placeholder="Role Name"/> 
        <br>
        <br>
	    <input name="description" placeholder="Role Description"/>
        <br>
        <br>
	    <input type="submit" value="CREATE" /> 
	</form>
    </center>
</body>
</html>
