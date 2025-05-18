<%@page import="com.authsystem.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Attendance Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="styles.css">
    <style>
        .table-container {
            margin-top: 2rem;
        }
        .summary-card {
            margin-top: 2rem;
        }
        h1{
            color:#cccccc;
        }
    </style>
</head>
<body>
    <% 
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("signin.jsp");
                return;
            }
        %>
        
        <div class="wrapper">
        <!-- Sidebar -->
        <nav id="sidebar" class="active">
            <div class="sidebar-header">
                <span class="ape"><h3>APE School</h3></span>
                <span class="ape"><h4>AP</h4></span>
            </div>
            <ul class="list-unstyled components">
                <li>
                    <a href="welcome.jsp">
                        <i class="bi bi-speedometer2"></i>
                        Dashboard
                    </a>
                </li>
                <li>
                    <a href="student-list.jsp">
                        <i class="bi bi-people-fill"></i>
                        Students
                    </a>
                    
                </li>
                <li>
                    <a href="courses">
                        <i class="bi bi-book"></i>
                        Courses
                    </a>
                </li>
                <li>
                    <a href="gradebook.jsp">
                        <i class="bi bi-journal-bookmark"></i>
                        Gradebook
                    </a>
                </li>
                <li class="active">
                    <a href="AttendanceServlet">
                        <i class="bi bi-calendar-check"></i>
                        Attendance
                    </a>
                </li>
                <li>
                    <a href="LogoutServlet">
                        <i class="bi bi-box-arrow-right"></i>
                            Logout
                    </a>
                </li>
            </ul>
        </nav>

        <!-- Page Content -->
        <div id="content">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <button type="button" id="sidebarCollapse" class="btn btn-info">
                        <i class="bi bi-list"></i>
                    </button>
                    <div class="ms-auto">
                        <i class="bi bi-bell-fill mx-3"></i>
                        <i class="bi bi-person-circle"></i>
                        <span class="ms-2"><%= user.getUsername() %></span>
                    </div>
                </div>
            </nav>
    <div class="container">
        <h1 class="my-4 text-center" color="red">Daily Attendance</h1>
        
        <div class="card mb-4">
            <div class="card-header">
                <h5>Select Date</h5>
            </div>
            <div class="card-body">
                <form method="get" action="AttendanceServlet">
                    <div class="row g-3 align-items-center">
                        <div class="col-md-3">
                            <input type="date" name="date" class="form-control" 
                                   value="<fmt:formatDate value="${selectedDate}" pattern="yyyy-MM-dd" />">
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-primary w-100">View Attendance</button>
                        </div>
                        <div class="col-md-7 text-end">
                            <a href="AttendanceServlet" class="btn btn-outline-secondary">Today</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        
        <div class="card mb-4">
            <div class="card-header">
                <h5>Record Attendance</h5>
            </div>
            <div class="card-body">
                <form action="AttendanceServlet" method="post">
                    <input type="hidden" name="action" value="record">
                    <input type="hidden" name="date" 
                           value="<fmt:formatDate value="${selectedDate}" pattern="yyyy-MM-dd" />">
                    <div class="row g-3">
                        <div class="col-md-3">
                            <select name="studentId" class="form-select" required>
                                <option value="">Select Student</option>
                                <c:forEach var="student" items="${students}">
                                    <option value="${student.id}">${student.firstName} ${student.lastName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <input type="time" name="timeIn" class="form-control" required>
                        </div>
                        <div class="col-md-2">
                            <input type="time" name="timeOut" class="form-control" required>
                        </div>
                        <div class="col-md-2">
                            <select name="status" class="form-select" required>
                                <option value="1.0">Present (1.0)</option>
                                <option value="0.75">Late (0.75)</option>
                                <option value="0.5">Half Day (0.5)</option>
                                <option value="0.0">Absent (0.0)</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-success w-100">Record</button>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-11">
                            <input type="text" name="notes" class="form-control" placeholder="Notes (optional)">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        
        <div class="table-container">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>Student</th>
                        <th>Status</th>
                        <th>Time In</th>
                        <th>Time Out</th>
                        <th>Notes</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="attendance" items="${attendanceList}">
                        <tr>
                            <td>${attendance.studentName}</td>
                            <td>${attendance.status}</td>
                            <td><fmt:formatDate value="${attendance.timeIn}" pattern="hh:mm a" /></td>
                            <td><fmt:formatDate value="${attendance.timeOut}" pattern="hh:mm a" /></td>
                            <td>${attendance.notes}</td>
                            <td>
                                <a href="AttendanceServlet?action=edit&id=${attendance.id}" 
                                   class="btn btn-sm btn-warning">Edit</a>
                                <a href="AttendanceServlet?action=delete&id=${attendance.id}" 
                                   class="btn btn-sm btn-danger"
                                   onclick="return confirm('Are you sure you want to delete this attendance record?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="script.js"></script>
</body>
</html>