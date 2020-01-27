package com.example.c196.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.c196.database.AppDatabase;
import com.example.c196.database.AppRepository;
import com.example.c196.database.TermDao;
import com.example.c196.database.TermEntity;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditorViewModel extends AndroidViewModel {

    public MutableLiveData<TermEntity> mLiveTerm = new MutableLiveData<>();

    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public EditorViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());
    }

    public void saveTerm(String termName, Date termStartDate, Date termEndDate) {
        TermEntity term = mLiveTerm.getValue();

        if (term == null) {
            if (TextUtils.isEmpty(termName.trim())) {
                return;
            }
            term = new TermEntity(termName.trim(), termStartDate, termEndDate);
        } else {
            term.setTermName(termName.trim());
            term.setTermStartDate(termStartDate);
            term.setTermEndDate(termEndDate);
        }
        mRepository.insertTerm(term);
    }

    public void loadData(int termId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                TermEntity term = mRepository.getTermById(termId);
                mLiveTerm.postValue(term);
            }
        });
    }
}