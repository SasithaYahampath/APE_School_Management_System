package com.authsystem.controller;

import com.authsystem.dao.StudentDAO;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete-student")
public class DeleteStudentServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String studentId = request.getParameter("id");
            StudentDAO studentDAO = new StudentDAO();
            
            if (studentDAO.deleteStudent(studentId)) {
                response.sendRedirect("students");
            } else {
                request.setAttribute("error", "Failed to delete student");
                request.getRequestDispatcher("student-list.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}