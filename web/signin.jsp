<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sign In</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="styles1.css">
</head>
<body>
    <h4>APE School Management System Login <a href="signup.jsp"><i class="bi bi-r-circle"></i></a></h4>
    
    <div class="container">
        
        <h2>Sign In</h2>
        
        <% if (request.getParameter("registration") != null && request.getParameter("registration").equals("success")) { %>
            <div class="success">Registration successful! Please sign in.</div>
        <% } %>
        
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="error"><%= request.getAttribute("errorMessage") %></div>
        <% } %>
        
        <form action="SignInServlet" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button class="bttn1" type="submit">Sign In</button>
        </form>
        
    </div>
        
</body>
</html>