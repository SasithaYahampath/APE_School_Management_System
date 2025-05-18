<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add New Student</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <button class="back">
                    <a href="students">
                        <i class="bi-arrow-left-circle"></i>
                    </a>
    </button>
    <div class="container1">
        <h1>Add New Student</h1>
        
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        
        <form action="add-student" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label>Student ID:</label>
                <input type="text" name="studentId" required>
            </div>
            <div class="form-group">
                <label>First Name:</label>
                <input type="text" name="firstName" required>
            </div>
            <div class="form-group">
                <label>Last Name:</label>
                <input type="text" name="lastName" required>
            </div>
            <div class="form-group">
                <label>Grade:</label>
                <select name="grade" required>
                    <option value="Grade 1">Grade 1</option>
                    <option value="Grade 2">Grade 2</option>
                    <option value="Grade 3">Grade 3</option>
                    <option value="Grade 4">Grade 4</option>
                    <option value="Grade 5">Grade 5</option>
                    <option value="Grade 6">Grade 6</option>
                </select>
            </div>
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" required>
            </div>
            <div class="form-group">
                <label>Status:</label>
                <select name="status" required>
                    <option value="Active">Active</option>
                    <option value="Inactive">Inactive</option>
                </select>
            </div>
            <div class="form-group">
                <label>Photo:</label>
                <input type="file" name="photo" accept="image/*">
            </div>
            <div class="form-group">
                <button type="submit" class="btn-save">Save</button>
                <a href="students" class="btn-cancel">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>