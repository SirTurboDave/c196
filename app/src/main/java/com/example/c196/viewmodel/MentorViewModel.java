package com.example.c196.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.c196.database.AppRepository;
import com.example.c196.database.MentorEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MentorViewModel extends AndroidViewModel {
    public MutableLiveData<List<MentorEntity>> mMentors =
            new MutableLiveData<>();
    public MutableLiveData<MentorEntity> mLiveMentor =
            new MutableLiveData<>();
    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public MentorViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());
    }

    public void loadData(final int mentorId) {
        executor.execute(() -> {
            MentorEntity mentor = mRepository.getMentorById(mentorId);
            mLiveMentor.postValue(mentor);
        });
    }
}
