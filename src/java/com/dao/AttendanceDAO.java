package com.dao;

import com.model.Attendance;
import com.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    public boolean recordAttendance(Attendance attendance) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO attendance (student_id, date, time_in, time_out, status, notes) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, attendance.getStudentId());
            stmt.setDate(2, attendance.getDate());
            stmt.setTime(3, attendance.getTimeIn());
            stmt.setTime(4, attendance.getTimeOut());
            stmt.setDouble(5, attendance.getStatus());
            stmt.setString(6, attendance.getNotes());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn, stmt, null);
        }
    }
    
    public boolean updateAttendance(Attendance attendance) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE attendance SET student_id=?, date=?, time_in=?, time_out=?, status=?, notes=? WHERE attendance_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, attendance.getStudentId());
            stmt.setDate(2, attendance.getDate());
            stmt.setTime(3, attendance.getTimeIn());
            stmt.setTime(4, attendance.getTimeOut());
            stmt.setDouble(5, attendance.getStatus());
            stmt.setString(6, attendance.getNotes());
            stmt.setInt(7, attendance.getId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn, stmt, null);
        }
    }
    
    public List<Attendance> getDailyAttendance(Date date) {
        List<Attendance> attendanceList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT a.*, CONCAT(s.first_name, ' ', s.last_name) as student_name " +
                         "FROM attendance a JOIN students s ON a.student_id = s.student_id " +
                         "WHERE a.date = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, date);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setId(rs.getInt("attendance_id"));
                attendance.setStudentId(rs.getInt("student_id"));
                attendance.setStudentName(rs.getString("student_name"));
                attendance.setDate(rs.getDate("date"));
                attendance.setTimeIn(rs.getTime("time_in"));
                attendance.setTimeOut(rs.getTime("time_out"));
                attendance.setStatus(rs.getDouble("status"));
                attendance.setNotes(rs.getString("notes"));
                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return attendanceList;
    }
    
    public Attendance getAttendanceById(int attendanceId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Attendance attendance = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT a.*, CONCAT(s.first_name, ' ', s.last_name) as student_name " +
                         "FROM attendance a JOIN students s ON a.student_id = s.student_id " +
                         "WHERE a.attendance_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, attendanceId);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                attendance = new Attendance();
                attendance.setId(rs.getInt("attendance_id"));
                attendance.setStudentId(rs.getInt("student_id"));
                attendance.setStudentName(rs.getString("student_name"));
                attendance.setDate(rs.getDate("date"));
                attendance.setTimeIn(rs.getTime("time_in"));
                attendance.setTimeOut(rs.getTime("time_out"));
                attendance.setStatus(rs.getDouble("status"));
                attendance.setNotes(rs.getString("notes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return attendance;
    }
    
    public boolean deleteAttendance(int attendanceId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM attendance WHERE attendance_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, attendanceId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn, stmt, null);
        }
    }
    
    public List<Attendance> getAttendanceByStudent(int studentId) {
        List<Attendance> attendanceList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT a.*, CONCAT(s.first_name, ' ', s.last_name) as student_name " +
                         "FROM attendance a JOIN students s ON a.student_id = s.student_id " +
                         "WHERE a.student_id = ? ORDER BY a.date DESC";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setId(rs.getInt("attendance_id"));
                attendance.setStudentId(rs.getInt("student_id"));
                attendance.setStudentName(rs.getString("student_name"));
                attendance.setDate(rs.getDate("date"));
                attendance.setTimeIn(rs.getTime("time_in"));
                attendance.setTimeOut(rs.getTime("time_out"));
                attendance.setStatus(rs.getDouble("status"));
                attendance.setNotes(rs.getString("notes"));
                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return attendanceList;
    }
}