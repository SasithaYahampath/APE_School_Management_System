<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.authsystem.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gradebook</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="styles.css">
    <style>
        h6{
            margin-left: 500px;
            color:whitesmoke;
            font-size:30px;
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
                <li>
                    <a href="courses">
                        <i class="bi bi-book"></i>
                        Courses
                    </a>
                </li>
                <li class="active">
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
          <div class="container-fluid">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h6>Gradebook</h6>
        <div>
            <select class="form-select me-2" style="width: auto; display: inline-block;">
                <option>All Classes</option>
                <option>Math 101</option>
                <option>Science 201</option>
                <option>Math 301</option>
                <option>Science 101</option>
                <option>Math 201</option>
                <option>Science 301</option>
            </select>
            <button class="btn btn-primary">
                <i class="bi bi-download"></i> Export
            </button>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header">
            <ul class="nav nav-tabs card-header-tabs">
                <li class="nav-item">
                    <a class="nav-link" href="gradebook.jsp">Gradebook</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="assignment.jsp">Assignments</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="report.jsp">Reports</a>
                </li>
            </ul>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <thead class="table-light">
                        <tr>
                            <th rowspan="2">Courses</th>
                            <th colspan="3" class="text-center">Assignments</th>
                        </tr>
                        <tr>
                            <th>Assignment 1</th>
                            <th>Assignment 2</th>
                            <th>Assignment 3</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Math 101</td>
                            <td>Length & Height Quiz</td>
                            <td>Area Assignment</td>
                            <td>Volume In-Class Test</td>
                        </tr>
                        <tr>
                            <td>Math 201</td>
                            <td>Circles In-Class Test</td>
                            <td>Radius & Dimeter Quiz</td>
                            <td>Relationship With Radius and Dimeter Assignment</td>
                        </tr>
                        <tr>
                            <td>Science 101</td>
                            <td>Electronic Quiz</td>
                            <td>Transistors In-Class Test</td>
                            <td>Relationship With Transistors And Daiods Assignment</td>
                        </tr>
                        <tr>
                            <td>Science 201</td>
                            <td>Parameters Assignment</td>
                            <td>Velocity In-Class Test</td>
                            <td>Lights & Sounds Quiz</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="script.js"></script>
</body>
</html>