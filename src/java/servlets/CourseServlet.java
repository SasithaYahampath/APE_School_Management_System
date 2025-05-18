package servlets;

import beans.Course;
import dao.CourseDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {
    private CourseDAO courseDAO;
    
    @Override
    public void init() {
        courseDAO = new CourseDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            
            if (action == null) {
                action = "list";
            }
            
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "add":
                    addCourse(request, response);
                    break;
                case "manage":
                    showManagePage(request, response);
                    break;
                case "delete":
                    deleteCourse(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateCourse(request, response);
                    break;
                case "list":
                default:
                    listCourses(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        String action = request.getParameter("action");
        
        if (action == null) {
            response.sendRedirect("courses");
            return;
        }
        
        switch (action) {
            case "add":
                addCourse(request, response);
                break;
            case "update":
                updateCourse(request, response);
                break;
            default:
                response.sendRedirect("courses");
                break;
        }
    } catch (SQLException ex) {
        throw new ServletException(ex);
    }
}
    
    private void listCourses(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Course> courses = courseDAO.getAllCourses();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/courses/list.jsp").forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/courses/add.jsp").forward(request, response);
    }
    
    private void showManagePage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("id"));
        Course course = courseDAO.getCourseById(courseId);
        request.setAttribute("course", course);
        request.getRequestDispatcher("/courses/manage.jsp").forward(request, response);
    }
    
    private void addCourse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String courseCode = request.getParameter("courseCode");
        String courseName = request.getParameter("courseName");
        String department = request.getParameter("department");
        int credits = Integer.parseInt(request.getParameter("credits"));
        
        Course newCourse = new Course();
        newCourse.setCourseCode(courseCode);
        newCourse.setCourseName(courseName);
        newCourse.setDepartment(department);
        newCourse.setCredits(credits);
        
        courseDAO.addCourse(newCourse);
        response.sendRedirect("courses");
    }
    private void deleteCourse(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int courseId = Integer.parseInt(request.getParameter("id"));
    courseDAO.deleteCourse(courseId);
    response.sendRedirect("courses");
}
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    int courseId = Integer.parseInt(request.getParameter("id"));
    Course existingCourse = courseDAO.getCourseById(courseId);
    request.setAttribute("course", existingCourse);
    request.getRequestDispatcher("/courses/edit.jsp").forward(request, response);
}
    
    private void updateCourse(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int courseId = Integer.parseInt(request.getParameter("courseId"));
    String courseCode = request.getParameter("courseCode");
    String courseName = request.getParameter("courseName");
    String department = request.getParameter("department");
    int credits = Integer.parseInt(request.getParameter("credits"));
    
    Course course = new Course(courseId, courseCode, courseName, department, credits);
    courseDAO.updateCourse(course);
    response.sendRedirect("courses");
}
}