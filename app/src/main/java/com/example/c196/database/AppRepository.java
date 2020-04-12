package com.example.c196.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.c196.utilities.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static AppRepository ourInstance;

    public LiveData<List<TermEntity>> mTerms;
    public LiveData<List<CourseEntity>> mCourses;
    public LiveData<List<MentorEntity>> mMentors;
    public List<CourseEntity> mCoursesByTerm;
    public List<MentorEntity> mMentorsByCourse;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {
        mDb = AppDatabase.getInstance(context);
        mTerms = getAllTerms();
        mCourses = getAllCourses();
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.termDao().insertAll(SampleData.getTerms());
                mDb.courseDao().insertAll(SampleData.getCourses());
            }
        });
    }

    public void deleteAllTerms() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.termDao().deleteAll();
            }
        });
    }

    public TermEntity getTermById(int termId) {
        return mDb.termDao().getTermById(termId);
    }

    private LiveData<List<TermEntity>> getAllTerms() {
        return mDb.termDao().getAll();
    }

    public LiveData<List<CourseEntity>> getCoursesByTermId(int termId) {
        return mDb.courseDao().getCoursesByTermId(termId);
    }

    public LiveData<List<CourseEntity>> getAllCourses() {
        return mDb.courseDao().getAll();
    }

    public void deleteAllCourses() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.courseDao().deleteAll();
            }
        });
    }

    public CourseEntity getCourseById(int courseId) {
        return mDb.courseDao().getCourseById(courseId);
    }

    public void deleteSampleData() {
        deleteAllTerms();
        deleteAllCourses();
    }

    public void insertTerm(TermEntity term) {
        executor.execute(() -> mDb.termDao().insertTerm(term));
    }

    public void deleteTerm(TermEntity term) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.termDao().deleteTerm(term);
            }
        });
    }

    public LiveData<List<MentorEntity>> getMentorsByCourseId(int courseId) {
        return mDb.mentorDao().getMentorsByCourseId(courseId);
    }
}