package dao;

import beans.LectureSlide;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LectureSlideDAO {
    public void uploadLectureSlide(LectureSlide slide) throws SQLException {
        String sql = "INSERT INTO lecture_slides (course_id, title, description, file_name, file_path) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, slide.getCourseId());
            stmt.setString(2, slide.getTitle());
            stmt.setString(3, slide.getDescription());
            stmt.setString(4, slide.getFileName());
            stmt.setString(5, slide.getFilePath());
            stmt.executeUpdate();
        }
    }
    
    public List<LectureSlide> getSlidesByCourse(int courseId) throws SQLException {
        List<LectureSlide> slides = new ArrayList<>();
        String sql = "SELECT * FROM lecture_slides WHERE course_id = ? ORDER BY upload_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LectureSlide slide = new LectureSlide(
                        rs.getInt("slide_id"),
                        rs.getInt("course_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("file_name"),
                        rs.getString("file_path"),
                        rs.getTimestamp("upload_date")
                    );
                    slides.add(slide);
                }
            }
        }
        return slides;
    }
    
    
public boolean deleteSlide(int slideId) throws SQLException {
    String sql = "DELETE FROM lecture_slides WHERE slide_id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, slideId);
        return stmt.executeUpdate() > 0;
    }
}

public boolean updateSlide(LectureSlide slide) throws SQLException {
    String sql = "UPDATE lecture_slides SET title = ?, description = ? WHERE slide_id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, slide.getTitle());
        stmt.setString(2, slide.getDescription());
        stmt.setInt(3, slide.getSlideId());
        
        return stmt.executeUpdate() > 0;
    }
}
    
    public LectureSlide getSlideById(int slideId) throws SQLException {
        String sql = "SELECT * FROM lecture_slides WHERE slide_id = ?";
        LectureSlide slide = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, slideId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    slide = new LectureSlide(
                        rs.getInt("slide_id"),
                        rs.getInt("course_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("file_name"),
                        rs.getString("file_path"),
                        rs.getTimestamp("upload_date")
                    );
                }
            }
        }
        return slide;
    }
}