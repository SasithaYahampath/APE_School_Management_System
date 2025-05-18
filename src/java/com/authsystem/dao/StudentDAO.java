package com.authsystem.dao;

import com.authsystem.model.Student;
import com.authsystem.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    // Get paginated list of students
    public List<Student> getAllStudents(int page, int recordsPerPage) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students LIMIT ?, ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, (page - 1) * recordsPerPage);
            stmt.setInt(2, recordsPerPage);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    students.add(createStudentFromResultSet(rs));
                }
            }
        }
        return students;
    }
    
    // Get total number of students
    public int getTotalStudents() throws SQLException {
        String sql = "SELECT COUNT(*) FROM students";
        
        try (Connection conn =DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
    
    // Search students
    public List<Student> searchStudents(String query) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");
            stmt.setString(3, "%" + query + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    students.add(createStudentFromResultSet(rs));
                }
            }
        }
        return students;
    }
    
    // Add new student
    public boolean addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getLastName());
            stmt.setString(4, student.getGrade());
            stmt.setString(5, student.getEmail());
            stmt.setString(6, student.getStatus());
            stmt.setString(7, student.getPhotoPath());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Update student
    public boolean updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET first_name=?, last_name=?, grade=?, email=?, status=?, photo_path=? WHERE student_id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getGrade());
            stmt.setString(4, student.getEmail());
            stmt.setString(5, student.getStatus());
            stmt.setString(6, student.getPhotoPath());
            stmt.setString(7, student.getStudentId());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Delete student
    public boolean deleteStudent(String studentId) throws SQLException {
        String sql = "DELETE FROM students WHERE student_id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, studentId);
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Get student by ID
    public Student getStudentById(String studentId) throws SQLException {
        String sql = "SELECT * FROM students WHERE student_id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, studentId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return createStudentFromResultSet(rs);
                }
            }
        }
        return null;
    }
    
    // Helper method to create Student object from ResultSet
    private Student createStudentFromResultSet(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setStudentId(rs.getString("student_id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        student.setGrade(rs.getString("grade"));
        student.setEmail(rs.getString("email"));
        student.setStatus(rs.getString("status"));
        student.setPhotoPath(rs.getString("photo_path"));
        return student;
    }
}