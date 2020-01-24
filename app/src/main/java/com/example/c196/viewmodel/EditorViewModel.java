package com.example.c196.viewmodel;

import android.app.Application;

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

public class EditorViewModel extends AndroidViewModel {

    public MutableLiveData<TermEntity> liveTerm = new MutableLiveData<>();
    public LiveData<List<TermEntity>> terms;

    private AppRepository mRepository;

    public EditorViewModel(@NonNull Application application) {
        super(application);
    }

    public void saveTerm(String termName, Date termStartDate, Date termEndDate) {
        TermEntity term = liveTerm.getValue();

        if (term == null) {
            term = new TermEntity(termName.trim(), termStartDate, termEndDate);
        } else {
            term.setTermName(termName.trim());
            term.setTermStartDate(termStartDate);
            term.setTermEndDate(termEndDate);
        }
        mRepository.insertTerm(term);
    }
}