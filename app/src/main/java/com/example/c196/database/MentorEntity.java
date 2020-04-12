package com.example.c196.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "mentors")
public class MentorEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int courseId;
    private String mentorName;
    private String mentorPhone;
    private String mentorEmail;

    @Ignore
    public MentorEntity() {
    }

    public MentorEntity(int id, int courseId, String mentorName, String mentorPhone,
                        String mentorEmail) {
        this.id = id;
        this.courseId = courseId;
        this.mentorName = mentorName;
        this.mentorPhone = mentorPhone;
        this.mentorEmail = mentorEmail;
    }

    @Ignore
    public MentorEntity(int courseId, String mentorName, String mentorPhone,
                        String mentorEmail) {
        this.courseId = courseId;
        this.mentorName = mentorName;
        this.mentorPhone = mentorPhone;
        this.mentorEmail = mentorEmail;
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

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMentorPhone() {
        return mentorPhone;
    }

    public void setMentorPhone(String mentorPhone) {
        this.mentorPhone = mentorPhone;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }
}