<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Attendance</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h4>Edit Attendance Record</h4>
                    </div>
                    <div class="card-body">
                        <form action="AttendanceServlet" method="post">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="id" value="${attendance.id}">
                            <input type="hidden" name="date" 
                                   value="<fmt:formatDate value="${attendance.date}" pattern="yyyy-MM-dd" />">
                            
                            <div class="mb-3">
                                <label for="studentId" class="form-label">Student</label>
                                <select class="form-select" id="studentId" name="studentId" required>
                                    <c:forEach var="student" items="${students}">
                                        <option value="${student.id}" 
                                                ${student.id == attendance.studentId ? 'selected' : ''}>
                                            ${student.firstName} ${student.lastName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="timeIn" class="form-label">Time In</label>
                                    <input type="time" class="form-control" id="timeIn" name="timeIn" 
                                           value="<fmt:formatDate value="${attendance.timeIn}" pattern="HH:mm" />" required>
                                </div>
                                <div class="col-md-6">
                                    <label for="timeOut" class="form-label">Time Out</label>
                                    <input type="time" class="form-control" id="timeOut" name="timeOut" 
                                           value="<fmt:formatDate value="${attendance.timeOut}" pattern="HH:mm" />" required>
                                </div>
                            </div>
                            
                            <div class="mb-3">
                                <label for="status" class="form-label">Status</label>
                                <select class="form-select" id="status" name="status" required>
                                    <option value="1.0" ${attendance.status == 1.0 ? 'selected' : ''}>Present (1.0)</option>
                                    <option value="0.75" ${attendance.status == 0.75 ? 'selected' : ''}>Late (0.75)</option>
                                    <option value="0.5" ${attendance.status == 0.5 ? 'selected' : ''}>Half Day (0.5)</option>
                                    <option value="0.0" ${attendance.status == 0.0 ? 'selected' : ''}>Absent (0.0)</option>
                                </select>
                            </div>
                            
                            <div class="mb-3">
                                <label for="notes" class="form-label">Notes</label>
                                <textarea class="form-control" id="notes" name="notes">${attendance.notes}</textarea>
                            </div>
                            
                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary">Update</button>
                                <a href="AttendanceServlet" class="btn btn-secondary">Cancel</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>