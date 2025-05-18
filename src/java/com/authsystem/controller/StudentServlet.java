package com.authsystem.controller;

import com.authsystem.dao.StudentDAO;
import com.authsystem.dao.UserDAO;
import com.authsystem.model.Student;
import com.authsystem.model.User;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private static final int RECORDS_PER_PAGE = 5;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int page = 1;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            
            
            StudentDAO studentDAO = new StudentDAO();
            
            List<Student> students = studentDAO.getAllStudents(page, RECORDS_PER_PAGE);
            int totalStudents = studentDAO.getTotalStudents();
            int totalPages = (int) Math.ceil((double) totalStudents / RECORDS_PER_PAGE);
            
            request.setAttribute("students", students);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            
            request.getRequestDispatcher("student-list.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}