package com.controller;

import com.dao.AttendanceDAO;
import com.dao.StudentDAO;
import com.model.Attendance;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AttendanceServlet", urlPatterns = {"/AttendanceServlet"})
public class AttendanceServlet extends HttpServlet {
    private AttendanceDAO attendanceDao = new AttendanceDAO();
    private StudentDAO studentDao = new StudentDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            String dateParam = request.getParameter("date");
            Date date = dateParam != null ? Date.valueOf(dateParam) : new Date(System.currentTimeMillis());
            request.setAttribute("attendanceList", attendanceDao.getDailyAttendance(date));
            request.setAttribute("students", studentDao.getAllStudents());
            request.setAttribute("selectedDate", date);
            request.getRequestDispatcher("attendance.jsp").forward(request, response);
            return;
        }
        
        try {
            switch (action) {
                case "record":
                    recordAttendance(request, response);
                    break;
                case "edit":
                    editAttendance(request, response);
                    break;
                case "update":
                    updateAttendance(request, response);
                    break;
                case "delete":
                    deleteAttendance(request, response);
                    break;
                default:
                    response.sendRedirect("AttendanceServlet");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("AttendanceServlet");
        }
    }
    
    private void recordAttendance(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Attendance attendance = new Attendance();
        attendance.setStudentId(Integer.parseInt(request.getParameter("studentId")));
        attendance.setDate(Date.valueOf(request.getParameter("date")));
        attendance.setTimeIn(Time.valueOf(request.getParameter("timeIn") + ":00"));
        attendance.setTimeOut(Time.valueOf(request.getParameter("timeOut") + ":00"));
        attendance.setStatus(Double.parseDouble(request.getParameter("status")));
        attendance.setNotes(request.getParameter("notes"));
        
        attendanceDao.recordAttendance(attendance);
        response.sendRedirect("AttendanceServlet?date=" + request.getParameter("date"));
    }
    
    private void editAttendance(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int attendanceId = Integer.parseInt(request.getParameter("id"));
        Attendance attendance = attendanceDao.getAttendanceById(attendanceId);
        request.setAttribute("attendance", attendance);
        request.setAttribute("students", studentDao.getAllStudents());
        request.getRequestDispatcher("editAttendance.jsp").forward(request, response);
    }
    
    private void updateAttendance(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Attendance attendance = new Attendance();
        attendance.setId(Integer.parseInt(request.getParameter("id")));
        attendance.setStudentId(Integer.parseInt(request.getParameter("studentId")));
        attendance.setDate(Date.valueOf(request.getParameter("date")));
        attendance.setTimeIn(Time.valueOf(request.getParameter("timeIn") + ":00"));
        attendance.setTimeOut(Time.valueOf(request.getParameter("timeOut") + ":00"));
        attendance.setStatus(Double.parseDouble(request.getParameter("status")));
        attendance.setNotes(request.getParameter("notes"));
        
        attendanceDao.updateAttendance(attendance);
        response.sendRedirect("AttendanceServlet?date=" + request.getParameter("date"));
    }
    
    private void deleteAttendance(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int attendanceId = Integer.parseInt(request.getParameter("id"));
        attendanceDao.deleteAttendance(attendanceId);
        response.sendRedirect("AttendanceServlet");
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