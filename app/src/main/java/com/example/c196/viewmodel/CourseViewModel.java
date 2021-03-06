package com.example.c196.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.c196.database.AppRepository;
import com.example.c196.database.AssessmentEntity;
import com.example.c196.database.CourseEntity;
import com.example.c196.database.MentorEntity;
import com.example.c196.database.TermEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CourseViewModel extends AndroidViewModel {

    public MutableLiveData<List<CourseEntity>> mCourses =
            new MutableLiveData<>();
    public MutableLiveData<CourseEntity> mLiveCourse =
            new MutableLiveData<>();
    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public CourseViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());
    }

    public void loadData(final int courseId) {
        executor.execute(() -> {
            CourseEntity course = mRepository.getCourseById(courseId);
            mLiveCourse.postValue(course);
        });
    }

    public LiveData<List<MentorEntity>> getMentorsByCourseId(final int courseId) {
        return mRepository.getMentorsByCourseId(courseId);
    }

    public LiveData<List<AssessmentEntity>> getAssessmentsByCourseid(int courseId) {
        return mRepository.getAssessmentsByCourseId(courseId);
    }
}