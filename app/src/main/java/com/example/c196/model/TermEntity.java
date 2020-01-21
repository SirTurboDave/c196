package com.example.c196.model;

import androidx.annotation.NonNull;

import java.util.Date;

public class TermEntity {
    private int id;
    private String termName;
    private Date termStartDate;
    private Date termEndDate;

    public TermEntity() {
    }

    public TermEntity(int id, String termName, Date termStartDate, Date termEndDate) {
        this.id = id;
        this.termName = termName;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
    }

    public TermEntity(String termName, Date termStartDate, Date termEndDate) {
        this.termName = termName;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
    }

    @NonNull
    public String toString() {
        return "TermEntity{" +
                "id=" + id +
                ", termName='" + termName + '\'' +
                ", termStartDate=" + termStartDate +
                ", termEndDate=" + termEndDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public Date getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(Date termStartDate) {
        this.termStartDate = termStartDate;
    }

    public Date getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(Date termEndDate) {
        this.termEndDate = termEndDate;
    }
}