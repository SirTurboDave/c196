package com.example.c196.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.c196.database.AppRepository;
import com.example.c196.database.AssessmentEntity;
import com.example.c196.database.MentorEntity;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AssessmentEditorViewModel extends AndroidViewModel {

    public MutableLiveData<AssessmentEntity> mLiveAssessment = new MutableLiveData<>();

    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public AssessmentEditorViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());
    }

    public void loadAssessmentData(int assessmentId) {
        executor.execute(() -> {
            AssessmentEntity assessment = mRepository.getAssessmentById(assessmentId);
            mLiveAssessment.postValue(assessment);
        });
    }

    public void saveAssessment(int courseId, String assessmentName, int assessmentType,
                               Date assessmentDate) {
        AssessmentEntity assessment = mLiveAssessment.getValue();

        if (assessment == null) {
            if (TextUtils.isEmpty(assessmentName.trim())) {
                return;
            }
            assessment = new AssessmentEntity(courseId, assessmentName.trim(), assessmentType,
                    assessmentDate);
        } else {
            assessment.setAssessmentName(assessmentName.trim());
            assessment.setAssessmentType(assessmentType);
            assessment.setAssessmentDate(assessmentDate);
        }
        mRepository.insertAssessment(assessment);
    }

    public void deleteAssessment() {
        mRepository.deleteAssessment(mLiveAssessment.getValue());
    }
}
