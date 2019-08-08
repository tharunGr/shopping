<!DOCTYPE HTML>
<html lang="en">
    <head>
        <style type="text/css">
            <%@ include file="loginstyle.css" %>
        </style>   
    </head>

    <body class="background">
    <center>
    <h1 class="glow">Online Shopping</h1>
    <button class="open-button" onclick="openForm()">Lets Shop!</button>
    <div class="form-popup" id="myForm">
        <form action="shop" method="POST" class="form-container">
            <h1>Login</h1>

            <label for="userName"><b>User Name</b></label>
            <input type="text" placeholder="User Name" name="userName" required>

            <label for="password"><b>Password</b></label>
            <input type="password" placeholder="User Password" name="password" required>

            <button type="submit" class="btn">Login</button>
            <button type="button" class="btn cancel" onclick="closeForm()">Cancel</button>
        </form>
    </div>
   </center>
    <script>
        function openForm() {
          document.getElementById("myForm").style.display = "block";
        }

        function closeForm() {
          document.getElementById("myForm").style.display = "none";
        }
    </script>
    
    </body>
</html>
