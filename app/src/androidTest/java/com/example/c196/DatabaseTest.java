package com.example.c196;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.c196.database.AppDatabase;
import com.example.c196.database.CourseDao;
import com.example.c196.database.CourseEntity;
import com.example.c196.database.TermDao;
import com.example.c196.database.TermEntity;
import com.example.c196.utilities.SampleData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    public static final String TAG = "Junit";
    private AppDatabase mDb;
    private TermDao termDao;
    private CourseDao courseDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        termDao = mDb.termDao();
        courseDao = mDb.courseDao();
        Log.i(TAG, "createDb");
    }

    @After
    public void closeDb() {
        mDb.close();
        Log.i(TAG, "closeDb");
    }

    @Test
    public void createAndRetrieveTerms() {
        termDao.insertAll(SampleData.getTerms());
        int count = termDao.getCount();
        Log.i(TAG, "createAndRetrieveTerms: count=" + count);
        Log.i(TAG, "createAndRetrieveTerms: " + termDao.toString());
        assertEquals(SampleData.getTerms().size(), count);
    }

    @Test
    public void compareStrings() {
        termDao.insertAll(SampleData.getTerms());
        TermEntity original = SampleData.getTerms().get(0);
        TermEntity fromDb = termDao.getTermById(1);
        Log.i(TAG, "compareStrings: " + fromDb.toString());
        assertEquals(original.getTermName(), fromDb.getTermName());
    }

    @Test
    public void createAndRetrieveCourses() {
        courseDao.insertAll(SampleData.getCourses());
        int count = courseDao.getCount();
        Log.i(TAG, "createAndRetrieveCourses: count=" + count);
        assertEquals(SampleData.getCourses().size(), count);
    }

    @Test
    public void createAndRetrieveCoursesByTermId() {
        courseDao.insertAll(SampleData.getCourses());
        LiveData<List<CourseEntity>> fromDb = courseDao.getCoursesByTermId(1);
        Log.i(TAG, "createAndRetrieveCoursesByTermId: " + fromDb.toString());
    }
}
