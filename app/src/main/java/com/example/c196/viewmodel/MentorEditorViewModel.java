package com.example.c196.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.c196.database.AppRepository;
import com.example.c196.database.MentorEntity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MentorEditorViewModel extends AndroidViewModel {

    public MutableLiveData<MentorEntity> mLiveMentor = new MutableLiveData<>();

    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public MentorEditorViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());
    }

    public void loadMentorData(int mentorId) {
        executor.execute(() -> {
            MentorEntity mentor = mRepository.getMentorById(mentorId);
            mLiveMentor.postValue(mentor);
        });
    }

    public void saveMentor(int courseId, String mentorName, String mentorPhone, String mentorEmail) {
        MentorEntity mentor = mLiveMentor.getValue();

        if (mentor == null) {
            if (TextUtils.isEmpty(mentorName.trim())) {
                return;
            }
            mentor = new MentorEntity(courseId, mentorName.trim(), mentorPhone.trim(),
                    mentorEmail.trim());
        } else {
            mentor.setMentorName(mentorName.trim());
            mentor.setMentorPhone(mentorPhone.trim());
            mentor.setMentorEmail(mentorEmail.trim());
        }
        mRepository.insertMentor(mentor);
    }

    public void deleteMentor() {
        mRepository.deleteMentor(mLiveMentor.getValue());
    }
}
