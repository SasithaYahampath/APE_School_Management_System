package dao;

import beans.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Course course = new Course(
                    rs.getInt("course_id"),
                    rs.getString("course_code"),
                    rs.getString("course_name"),
                    rs.getString("department"),
                    rs.getInt("credits")
                );
                courses.add(course);
            }
        }
        return courses;
    }
    
    public void addCourse(Course course) throws SQLException {
        String sql = "INSERT INTO courses (course_code, course_name, department, credits) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getCourseName());
            stmt.setString(3, course.getDepartment());
            stmt.setInt(4, course.getCredits());
            stmt.executeUpdate();
        }
    }
 
public boolean deleteCourse(int courseId) throws SQLException {
    String sql = "DELETE FROM courses WHERE course_id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, courseId);
        return stmt.executeUpdate() > 0;
    }
}

public boolean updateCourse(Course course) throws SQLException {
    String sql = "UPDATE courses SET course_code = ?, course_name = ?, department = ?, credits = ? WHERE course_id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, course.getCourseCode());
        stmt.setString(2, course.getCourseName());
        stmt.setString(3, course.getDepartment());
        stmt.setInt(4, course.getCredits());
        stmt.setInt(5, course.getCourseId());
        
        return stmt.executeUpdate() > 0;
    }
}
    
    public Course getCourseById(int courseId) throws SQLException {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        Course course = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    course = new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_code"),
                        rs.getString("course_name"),
                        rs.getString("department"),
                        rs.getInt("credits")
                    );
                }
            }
        }
        return course;
    }
}