package com.controller;

import com.dao.StudentDAO;
import com.model.Student;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "StudentServlet", urlPatterns = {"/StudentServlet"})
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDao = new StudentDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            request.setAttribute("students", studentDao.getAllStudents());
            request.getRequestDispatcher("students.jsp").forward(request, response);
            return;
        }
        
        try {
            switch (action) {
                case "add":
                    addStudent(request, response);
                    break;
                case "edit":
                    editStudent(request, response);
                    break;
                case "update":
                    updateStudent(request, response);
                    break;
                case "delete":
                    deleteStudent(request, response);
                    break;
                default:
                    request.setAttribute("students", studentDao.getAllStudents());
                    request.getRequestDispatcher("students.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("students.jsp");
        }
    }
    
    private void addStudent(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Student student = new Student();
        student.setFirstName(request.getParameter("firstName"));
        student.setLastName(request.getParameter("lastName"));
        student.setEmail(request.getParameter("email"));
        
        studentDao.addStudent(student);
        response.sendRedirect("StudentServlet");
    }
    
    private void editStudent(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        Student student = studentDao.getStudentById(studentId);
        request.setAttribute("student", student);
        request.getRequestDispatcher("editStudent.jsp").forward(request, response);
    }
    
    private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Student student = new Student();
        student.setId(Integer.parseInt(request.getParameter("id")));
        student.setFirstName(request.getParameter("firstName"));
        student.setLastName(request.getParameter("lastName"));
        student.setEmail(request.getParameter("email"));
        
        studentDao.updateStudent(student);
        response.sendRedirect("StudentServlet");
    }
    
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        studentDao.deleteStudent(studentId);
        response.sendRedirect("StudentServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}