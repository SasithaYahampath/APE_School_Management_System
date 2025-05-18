package com.dao;

import com.model.Student;
import com.util.DBUtil;
import com.model.Student;
import com.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM students");
            
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setEmail(rs.getString("email"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return students;
    }
    
    public boolean addStudent(Student student) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO students (first_name, last_name, email) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getEmail());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn, stmt, null);
        }
    }
    
    public boolean updateStudent(Student student) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE students SET first_name=?, last_name=?, email=? WHERE student_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getEmail());
            stmt.setInt(4, student.getId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn, stmt, null);
        }
    }
    
    public boolean deleteStudent(int studentId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM students WHERE student_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn, stmt, null);
        }
    }
    
    public Student getStudentById(int studentId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Student student = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM students WHERE student_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return student;
    }
}