<%@page import="com.authsystem.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lecture Slides - ${course.courseCode}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
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
                <li>
                    <a href="student-list.jsp">
                        <i class="bi bi-people-fill"></i>
                        Students
                    </a>
                    
                </li>
                <li class="active">
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
        <h1>Lecture Slides for ${course.courseCode} - ${course.courseName}</h1>
        
        <div class="actions">
            <a href="lectures?action=upload&courseId=${course.courseId}" class="btn">Upload New Slide</a>
            <a href="courses?action=manage&id=${course.courseId}" class="btn">Back to Course</a>
        </div>
        
        <c:if test="${empty slides}">
            <p>No lecture slides available for this course.</p>
        </c:if>
        
        <c:if test="${not empty slides}">
            <table>
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Upload Date</th>
                        <th>Actions</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="slide" items="${slides}">
                        <tr>
                            <td>${slide.title}</td>
                            <td>${slide.description}</td>
                            <td>${slide.uploadDate}</td>
                            <td>
                                <a href="lectures?action=download&slideId=${slide.slideId}" class="btn">Download</a>
                            </td>
                            <td>
                                <a href="lectures?action=edit&slideId=${slide.slideId}" class="btn">Edit</a>
                            </td>
                            <td><a href="lectures?action=delete&slideId=${slide.slideId}&courseId=${course.courseId}" class="btn delete" onclick="return confirm('Are you sure you want to delete this slide?')">Delete</a>
                </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="script.js"></script>
</body>
</html>