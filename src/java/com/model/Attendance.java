package com.model;

import java.sql.Date;
import java.sql.Time;

public class Attendance {
    private int id;
    private int studentId;
    private String studentName;
    private Date date;
    private Time timeIn;
    private Time timeOut;
    private double status;
    private String notes;

    public Attendance() {}
    
    public Attendance(int id, int studentId, String studentName, Date date, Time timeIn, Time timeOut, double status, String notes) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.status = status;
        this.notes = notes;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public Time getTimeIn() { return timeIn; }
    public void setTimeIn(Time timeIn) { this.timeIn = timeIn; }
    public Time getTimeOut() { return timeOut; }
    public void setTimeOut(Time timeOut) { this.timeOut = timeOut; }
    public double getStatus() { return status; }
    public void setStatus(double status) { this.status = status; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}