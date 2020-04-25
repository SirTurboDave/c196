package com.example.c196.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AssessmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAssessment(AssessmentEntity note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<AssessmentEntity> assessments);

    @Delete
    void deleteAssesssment(AssessmentEntity assessmentEntity);

    @Query("select * from assessments where id = :id")
    AssessmentEntity getAssessmentById(int id);

    @Query("select * from assessments where courseId = :courseId")
    LiveData<List<AssessmentEntity>> getAssessmentsByCourseId(int courseId);

    @Query("select * from assessments order by id")
    LiveData<List<AssessmentEntity>> getAll();

    @Query("delete from assessments")
    int deleteAll();

    @Query("select count(id) from assessments")
    int getCount();
}
