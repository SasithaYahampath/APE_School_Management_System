<%@page import="com.authsystem.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>School Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="css/style.css">
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
                <li >
                    <a href="welcome.jsp">
                        <i class="bi bi-speedometer2"></i>
                        Dashboard
                    </a>
                </li>
                <li class="active">
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
                <li>
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
        <div class="header">
            <h1>Student Directory</h1>
            <a href="add-student" class="btn-add">+ Add Student</a>
        </div>
        
        <div class="search-box">
            <form action="students" method="get">
                <input type="text" name="search" placeholder="Search students..." 
                       value="${param.search}">
                <button type="submit">Search</button>
                <c:if test="${not empty param.search}">
                    <a href="students" class="btn-cancel">Clear</a>
                </c:if>
            </form>
        </div>
        
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Photo</th>
                    <th>Name</th>
                    <th>Grade</th>
                    <th>Contact</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.studentId}</td>
                        <td>
                            <img src="${student.photoPath}" alt="${student.fullName}" class="student-photo">
                        </td>
                        <td>${student.fullName}</td>
                        <td>${student.grade}</td>
                        <td>${student.email}</td>
                        <td>
                            <span class="status ${student.status.toLowerCase()}">${student.status}</span>
                        </td>
                        <td>
                            <a href="edit-student?id=${student.studentId}" class="action-btn edit">Edit</a>
                            <a href="delete-student?id=${student.studentId}" 
                               class="action-btn delete" 
                               onclick="return confirm('Are you sure you want to delete this student?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="students?page=${currentPage - 1}<c:if test="${not empty param.search}">&search=${param.search}</c:if>">Previous</a>
            </c:if>
            
            <c:forEach begin="1" end="${totalPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage == i}">
                        <span class="current">${i}</span>
                    </c:when>
                    <c:otherwise>
                        <a href="students?page=${i}<c:if test="${not empty param.search}">&search=${param.search}</c:if>">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            
            <c:if test="${currentPage < totalPages}">
                <a href="students?page=${currentPage + 1}<c:if test="${not empty param.search}">&search=${param.search}</c:if>">Next</a>
            </c:if>
        </div>
    </div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="script.js"></script>
</body>
</html>