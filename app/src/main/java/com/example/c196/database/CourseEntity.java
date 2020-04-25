package com.example.c196.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "courses")
public class CourseEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int termId;
    private String courseName;
    private Date courseStartDate;
    private Date courseEndDate;
    private String courseStatus;
    private String courseNote;
    private int courseAssessmentId;
    private int courseMentorId;


    @Ignore
    public CourseEntity() {
    }


    public CourseEntity(int id, int termId, String courseName, Date courseStartDate,
                        Date courseEndDate, String courseStatus, String courseNote) {
        this.id = id;
        this.termId = termId;
        this.courseName = courseName;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseStatus = courseStatus;
        this.courseNote = courseNote;
    }

    @Ignore
    public CourseEntity(int termId, String courseName, Date courseStartDate, Date courseEndDate,
                        String courseStatus, String courseNote) {
        this.termId = termId;
        this.courseName = courseName;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseStatus = courseStatus;
        this.courseNote = courseNote;
    }

    @Ignore
    public CourseEntity(String courseName, Date courseStartDate, Date courseEndDate) {
        this.courseName = courseName;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId (int termId) {
        this.termId = termId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(Date courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public Date getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(Date courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }

    public int getCourseAssessmentId() {
        return courseAssessmentId;
    }

    public void setCourseAssessmentId(int courseAssessmentId) {
        this.courseAssessmentId = courseAssessmentId;
    }

    public int getCourseMentorId() {
        return courseMentorId;
    }

    public void setCourseMentorId(int courseMentorId) {
        this.courseMentorId = courseMentorId;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "id=" + id +
                ", termId=" + termId +
                ", courseName='" + courseName + '\'' +
                ", courseStartDate=" + courseStartDate +
                ", courseEndDate=" + courseEndDate +
                ", courseStatus='" + courseStatus + '\'' +
                ", courseNote='" + courseNote + '\'' +
                '}';
    }
}
