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
    private int assessmentType;
    private Date assessmentDate;


    public AssessmentEntity(int id, int courseId, String assessmentName, int assessmentType,
                            Date assessmentDate) {
        this.id = id;
        this.courseId = courseId;
        this.assessmentName = assessmentName;
        this.assessmentType = assessmentType;
        this.assessmentDate = assessmentDate;
    }

    @Ignore
    public AssessmentEntity(int courseId, String assessmentName, int assessmentType,
                            Date assessmentDate) {
        this.courseId = courseId;
        this.assessmentName = assessmentName;
        this.assessmentType = assessmentType;
        this.assessmentDate = assessmentDate;
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

    public int getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(int assessmentType) {
        this.assessmentType = assessmentType;
    }

    public Date getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(Date assessmentDate) {
        this.assessmentDate = assessmentDate;
    }
}
