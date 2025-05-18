package beans;

import java.util.Date;

public class LectureSlide {
    private int slideId;
    private int courseId;
    private String title;
    private String description;
    private String fileName;
    private String filePath;
    private Date uploadDate;
    
    // Constructors, getters, and setters
    public LectureSlide() {}
    
    public LectureSlide(int slideId, int courseId, String title, String description, 
                       String fileName, String filePath, Date uploadDate) {
        this.slideId = slideId;
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.fileName = fileName;
        this.filePath = filePath;
        this.uploadDate = uploadDate;
    }
    
    // Getters and Setters
    public int getSlideId() { return slideId; }
    public void setSlideId(int slideId) { this.slideId = slideId; }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public Date getUploadDate() { return uploadDate; }
    public void setUploadDate(Date uploadDate) { this.uploadDate = uploadDate; }
}