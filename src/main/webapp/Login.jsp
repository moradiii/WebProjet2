<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<%
String url = "jdbc:mysql://localhost:3306/courrierbd";
String username = "root";
String password = "";

Connection conn = null;
PreparedStatement statement = null;
ResultSet resultSet = null;

try {
    Class.forName("com.mysql.jdbc.Driver");
    conn = DriverManager.getConnection(url, username, password);

    String email = request.getParameter("Email");
    String pwd = request.getParameter("Password");

    if (email != null && pwd != null) {
        String sql = "SELECT id FROM user WHERE Email=? AND PWD=?";
        statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, pwd);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            // Redirect based on user type
            if (id == 1) {
                response.sendRedirect("Table.jsp");
            } else if (id > 1) {
                response.sendRedirect("tbl_util.jsp");
            }
            return; // Exit after redirect
        }
    }
} catch (ClassNotFoundException | SQLException e) {
    e.printStackTrace(); // Handle exceptions appropriately
} finally {
    try {
        if (resultSet != null) resultSet.close();
        if (statement != null) statement.close();
        if (conn != null) conn.close();
    } catch (SQLException e) {
        e.printStackTrace(); // Log or handle exceptions
    }
}
%>

<!DOCTYPE html>
<html>
<head>
    <title>Log-in</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="img/Logo.png" type="image/png">
    <style>
body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: #ebe3db;
            /* Add the background image and positioning */
            background-image: url("img/background3.jpg");
            background-size: cover;
    		background-repeat: no-repeat;
            background-position: center top;
            /* You can adjust the position as needed */
        }

.container {
    max-width: 400px; /* Limit the width of the form */
    margin: 0 auto; /* Center the form horizontally */
    padding: 20px; /* Add some padding */
    padding-top: 0px;
    border: 1px solid #ccc; /* Add a border */
    border-radius: 8px; /* Add some border radius for rounded corners */
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);
    background-color: whitesmoke;
}

input.loginn[type=text], input.loginn[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

button.log-in {
    background-color: #333;
    color: rgb(2, 157, 224);
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
    border-radius: 30px;
}

button.log-in:hover {
    opacity: 0.8;
    background-color: rgb(2, 157, 224);
    color: black;
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);
}

.imgcontainer {
    text-align: center;
    
}

img.avatar {
    width: 30%;
    border-radius: 50%;
}

span.psw {
    float: right;
    padding-top: 16px;
}

#content {
    margin-top: 60px;
}

label, h2 {
    color: rgb(2, 157, 224);
    padding: 14px;
}

.loginn:hover {
box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);
}
</style>
</head>
<body>
    <!-- Your HTML content here -->
    <jsp:include page="Nav.jsp" />
    <div id="content" style="margin-top: 150px;">
        <div class="container">
            <form method="post">
                <h2><center>Se connecter</h2>
                <div class="imgcontainer">
                    <img src="img/loginn.png" alt="Avatar" class="avatar">
                </div>
                <label for="uname"><b>Email</b></label>
                <input class="loginn" type="text" placeholder="Enter Email" name="Email" required>
                <label for="psw"><b>Password</b></label>
                <input class="loginn" type="password" placeholder="Enter Password" name="Password" required>
                <button type="submit" class="log-in">Login</button>
            </form>
        </div>
    </div>
</body>
</html>
