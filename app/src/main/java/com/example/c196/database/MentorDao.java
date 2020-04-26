package com.example.c196.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MentorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMentor(MentorEntity mentorEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MentorEntity> mentors);

    @Delete
    void deleteMentor(MentorEntity mentorEntity);

    @Query("select * from mentors where id = :id")
    MentorEntity getMentorById(int id);

    @Query("select * from mentors order by mentorName")
    LiveData<List<MentorEntity>> getAll();

    @Query("select * from mentors where courseId = :courseId")
    LiveData<List<MentorEntity>> getMentorsByCourseId(int courseId);

    @Query("delete from mentors")
    void deleteAll();
}