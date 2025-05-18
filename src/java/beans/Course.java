package beans;

public class Course {
    private int courseId;
    private String courseCode;
    private String courseName;
    private String department;
    private int credits;
    
    // Constructors, getters, and setters
    public Course() {}
    
    public Course(int courseId, String courseCode, String courseName, String department, int credits) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.department = department;
        this.credits = credits;
    }
    
    // Getters and Setters
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
}