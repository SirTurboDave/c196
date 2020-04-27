package com.example.c196.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import com.example.c196.database.AppRepository;
import com.example.c196.database.CourseEntity;
import com.example.c196.database.MentorEntity;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CourseEditorViewModel extends AndroidViewModel {

    public MutableLiveData<CourseEntity> mLiveCourse = new MutableLiveData<>();

    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public CourseEditorViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());
    }

    public void loadCourseData(int courseId) {
        executor.execute(() -> {
            CourseEntity course = mRepository.getCourseById(courseId);
            mLiveCourse.postValue(course);
        });
    }

    public void saveCourse(int termId, String courseName, Date courseStartDate, Date courseEndDate,
                           int courseStatus, String courseNote) {
        CourseEntity course = mLiveCourse.getValue();

        if (course == null) {
            if (TextUtils.isEmpty(courseName.trim())) {
                return;
            }
            course = new CourseEntity(termId, courseName.trim(), courseStartDate, courseEndDate,
                    courseStatus, courseNote.trim());
        } else {
            course.setCourseName(courseName.trim());
            course.setCourseStartDate(courseStartDate);
            course.setCourseEndDate(courseEndDate);
            course.setCourseStatus(courseStatus);
            course.setCourseNote(courseNote.trim());
        }
        mRepository.insertCourse(course);
    }

    public void deleteCourse() {
        mRepository.deleteCourse(mLiveCourse.getValue());
    }
}
