package com.example.c196.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "assessments")
public class AssessmentEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int courseId;
    private String assessmentName;
    private String assessmentType;
    private Date assessmentDueDate;


    public AssessmentEntity(int id, int courseId, String assessmentName, String assessmentType,
                            Date assessmentDueDate) {
        this.id = id;
        this.courseId = courseId;
        this.assessmentName = assessmentName;
        this.assessmentType = assessmentType;
        this.assessmentDueDate = assessmentDueDate;
    }

    @Ignore
    public AssessmentEntity(int courseId, String assessmentName, String assessmentType,
                            Date assessmentDueDate) {
        this.courseId = courseId;
        this.assessmentName = assessmentName;
        this.assessmentType = assessmentType;
        this.assessmentDueDate = assessmentDueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public Date getAssessmentDueDate() {
        return assessmentDueDate;
    }

    public void setAssessmentDueDate(Date assessmentDueDate) {
        this.assessmentDueDate = assessmentDueDate;
    }
}
