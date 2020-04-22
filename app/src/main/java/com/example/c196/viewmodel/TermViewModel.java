package com.example.c196.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.c196.database.AppRepository;
import com.example.c196.database.CourseEntity;
import com.example.c196.database.TermEntity;
import com.example.c196.utilities.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TermViewModel extends AndroidViewModel {

    public MutableLiveData<List<CourseEntity>> mCourses =
            new MutableLiveData<>();
    public MutableLiveData<TermEntity> mLiveTerm =
            new MutableLiveData<>();
    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public TermViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());
    }

    public void loadData(final int termId) {
        executor.execute(() -> {
            TermEntity term = mRepository.getTermById(termId);
            mLiveTerm.postValue(term);
        });
    }

    public LiveData<List<CourseEntity>> getCoursesByTermId(final int termId) {
        return mRepository.getCoursesByTermId(termId);
    }

    public void deleteTerm() {
        mRepository.deleteTerm(mLiveTerm.getValue());
    }
}