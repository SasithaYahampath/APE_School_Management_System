package com.authsystem.controller;

import com.authsystem.dao.StudentDAO;
import com.authsystem.model.Student;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/add-student")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddStudentServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("add-student.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            Student student = new Student();
            student.setStudentId(request.getParameter("studentId"));
            student.setFirstName(request.getParameter("firstName"));
            student.setLastName(request.getParameter("lastName"));
            student.setGrade(request.getParameter("grade"));
            student.setEmail(request.getParameter("email"));
            student.setStatus(request.getParameter("status"));
            
            // Handle file upload
            Part filePart = request.getPart("photo");
            String fileName = getFileName(filePart);
            
            if (fileName != null && !fileName.isEmpty()) {
                String uploadPath = getServletContext().getRealPath("") + File.separator + "images";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();
                
                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);
                student.setPhotoPath("images/" + fileName);
            } else {
                student.setPhotoPath("images/default.png");
            }
            
            StudentDAO studentDAO = new StudentDAO();
            if (studentDAO.addStudent(student)) {
                response.sendRedirect("students");
            } else {
                request.setAttribute("error", "Failed to add student");
                request.getRequestDispatcher("add-student.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }
}