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
    public LiveData<List<AssessmentEntity>> mAssessments;
    public LiveData<List<MentorEntity>> mMentors;
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
        mAssessments = getAllAssessments();
        mMentors = getAllMentors();
    }

    public void addSampleData() {
        executor.execute(() -> {
            mDb.termDao().insertAll(SampleData.getTerms());
            mDb.courseDao().insertAll(SampleData.getCourses());
            mDb.assessmentDao().insertAll(SampleData.getAssessments());
            mDb.mentorDao().insertAll(SampleData.getMentors());
        });
    }

    public void deleteAllTerms() {
        executor.execute(() -> mDb.termDao().deleteAll());
    }

    public TermEntity getTermById(int termId) {
        return mDb.termDao().getTermById(termId);
    }

    private LiveData<List<TermEntity>> getAllTerms() {
        return mDb.termDao().getAll();
    }

    public LiveData<List<CourseEntity>> getCoursesByTermId(final int termId) {
        return mDb.courseDao().getCoursesByTermId(termId);
    }

    public LiveData<List<CourseEntity>> getAllCourses() {
        return mDb.courseDao().getAll();
    }

    public void deleteAllCourses() {
        executor.execute(() -> mDb.courseDao().deleteAll());
    }

    public CourseEntity getCourseById(int courseId) {
        return mDb.courseDao().getCourseById(courseId);
    }

    public void deleteSampleData() {
        deleteAllTerms();
        deleteAllCourses();
        deleteAllAssessments();
        deleteAllMentors();
    }

    public void insertTerm(TermEntity term) {
        executor.execute(() -> mDb.termDao().insertTerm(term));
    }

    public void deleteTerm(TermEntity term) {
        executor.execute(() -> mDb.termDao().deleteTerm(term));
    }

    public LiveData<List<MentorEntity>> getMentorsByCourseId(int courseId) {
        return mDb.mentorDao().getMentorsByCourseId(courseId);
    }

    public MentorEntity getMentorById(int mentorId) {
        return mDb.mentorDao().getMentorById(mentorId);
    }

    public void insertCourse(CourseEntity course) {
        executor.execute(() -> mDb.courseDao().insertCourse(course));
    }

    public LiveData<List<AssessmentEntity>> getAllAssessments() {
        return mDb.assessmentDao().getAll();
    }

    public LiveData<List<AssessmentEntity>> getAssessmentsByCourseId(int courseId) {
        return mDb.assessmentDao().getAssessmentsByCourseId(courseId);
    }

    private void deleteAllAssessments() {
        executor.execute(() -> mDb.assessmentDao().deleteAll());
    }

    public void deleteCourse(CourseEntity course) {
        executor.execute(() -> mDb.courseDao().deleteCourse(course));
    }

    public void insertMentor(MentorEntity mentor) {
        executor.execute(() -> mDb.mentorDao().insertMentor(mentor));
    }

    public void deleteMentor(MentorEntity mentor) {
        executor.execute(() -> mDb.mentorDao().deleteMentor(mentor));
    }

    public LiveData<List<MentorEntity>> getAllMentors() {
        return mDb.mentorDao().getAll();
    }

    private void deleteAllMentors() {
        executor.execute(() -> mDb.mentorDao().deleteAll());
    }

    public AssessmentEntity getAssessmentById(int assessmentId) {
        return mDb.assessmentDao().getAssessmentById(assessmentId);
    }

    public void insertAssessment(AssessmentEntity assessment) {
        executor.execute(() -> mDb.assessmentDao().insertAssessment(assessment));
    }

    public void deleteAssessment(AssessmentEntity assessment) {
        executor.execute(() -> mDb.assessmentDao().deleteAssesssment(assessment));
    }
}