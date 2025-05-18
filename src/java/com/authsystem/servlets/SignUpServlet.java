package com.authsystem.servlets;

import com.authsystem.dao.UserDAO;
import com.authsystem.model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        UserDAO userDao = new UserDAO();
        
        // Validate inputs
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }
        
        if (userDao.isUsernameTaken(username)) {
            request.setAttribute("errorMessage", "Username already taken");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }
        
        if (userDao.isEmailTaken(email)) {
            request.setAttribute("errorMessage", "Email already registered");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }
        
        // Create new user
        User newUser = new User(username, email, password);
        boolean success = userDao.registerUser(newUser);
        
        if (success) {
            response.sendRedirect("signin.jsp?registration=success");
        } else {
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }
}