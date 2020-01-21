package com.example.c196.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TermDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(TermEntity termEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TermEntity> terms);

    @Delete
    void deleteTerm(TermEntity termEntity);

    @Query("select * from terms where id = :id")
    TermEntity getTermById(int id);

    @Query("select * from terms order by termStartDate")
    LiveData<List<TermEntity>> getAll();

    @Query("delete from terms")
    int deleteAll();

    @Query("select count(*) from terms")
    int getCount();
}
