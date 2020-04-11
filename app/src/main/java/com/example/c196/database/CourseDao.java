package com.example.c196.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourse(CourseEntity courseEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CourseEntity> courses);

    @Delete
    void deleteCourse(CourseEntity courseEntity);

    @Query("select * from courses where id = :id")
    CourseEntity getCourseById(int id);

    @Query("select * from courses where termId = :termId")
    LiveData<List<CourseEntity>> getCoursesByTermId(int termId);

    @Query("select * from courses order by courseStartDate")
    LiveData<List<CourseEntity>> getAll();

    @Query("delete from courses")
    int deleteAll();

    @Query("select count(id) from courses")
    int getCount();

    //LiveData<List<CourseEntity>> getMentorsByCourseId(int courseId);
}