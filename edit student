<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Student</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    
    <div class="container1">
        <h1>Edit Student</h1>
        
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        
        <form action="edit-student" method="post" enctype="multipart/form-data">
            <input type="hidden" name="studentId" value="${student.studentId}">
            
            <div class="form-group">
                <label>First Name:</label>
                <input type="text" name="firstName" value="${student.firstName}" required>
            </div>
            <div class="form-group">
                <label>Last Name:</label>
                <input type="text" name="lastName" value="${student.lastName}" required>
            </div>
            <div class="form-group">
                <label>Grade:</label>
                <select name="grade" required>
                    <option value="Grade 1" ${student.grade == 'Grade 1' ? 'selected' : ''}>Grade 1</option>
                    <option value="Grade 2" ${student.grade == 'Grade 2' ? 'selected' : ''}>Grade 2</option>
                    <option value="Grade 3" ${student.grade == 'Grade 3' ? 'selected' : ''}>Grade 3</option>
                    <option value="Grade 4" ${student.grade == 'Grade 4' ? 'selected' : ''}>Grade 4</option>
                    <option value="Grade 5" ${student.grade == 'Grade 5' ? 'selected' : ''}>Grade 5</option>
                    <option value="Grade 6" ${student.grade == 'Grade 6' ? 'selected' : ''}>Grade 6</option>
                </select>
            </div>
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" value="${student.email}" required>
            </div>
            <div class="form-group">
                <label>Status:</label>
                <select name="status" required>
                    <option value="Active" ${student.status == 'Active' ? 'selected' : ''}>Active</option>
                    <option value="Inactive" ${student.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
                </select>
            </div>
            <div class="form-group">
                <label>Current Photo:</label>
                <img src="${student.photoPath}" alt="Current Photo" class="current-photo">
            </div>
            <div class="form-group">
                <label>New Photo (leave blank to keep current):</label>
                <input type="file" name="photo" accept="image/*">
            </div>
            <div class="form-group">
                <button type="submit" class="btn-save">Update</button>
                <a href="students" class="btn-cancel">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>