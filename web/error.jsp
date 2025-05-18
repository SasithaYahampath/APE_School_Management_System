<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error Page</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>Error Occurred</h1>
        <div class="error">
            <p>Sorry, an error occurred while processing your request.</p>
            <p>Error details: ${pageContext.exception.message}</p>
        </div>
        <a href="students" class="btn-cancel">Back to Student Directory</a>
    </div>
</body>
</html>