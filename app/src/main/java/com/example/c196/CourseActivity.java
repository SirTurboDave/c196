package com.example.c196;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import com.example.c196.database.AssessmentEntity;
import com.example.c196.database.CourseEntity;
import com.example.c196.database.MentorEntity;
import com.example.c196.ui.AssessmentsAdapter;
import com.example.c196.ui.CoursesAdapter;
import com.example.c196.utilities.DateFormatter;
import com.example.c196.viewmodel.CourseViewModel;
import com.example.c196.viewmodel.TermViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.c196.utilities.Constants.COURSE_ID_KEY;
import static com.example.c196.utilities.Constants.TERM_ID_KEY;

public class CourseActivity extends AppCompatActivity {

    @BindView(R.id.course_start_date)
    TextView mCourseStartDate;

    @BindView(R.id.course_end_date)
    TextView mCourseEndDate;

    @BindView(R.id.course_status)
    TextView mCourseStatus;

    @BindView(R.id.course_note)
    TextView mCourseNote;

    @BindView(R.id.course_assessment_add)
    ImageButton mAssessmentAdd;

    @OnClick(R.id.course_assessment_add)
    void assessmentAddClickHandler() {
        Intent intent = new Intent(this, AssessmentEditorActivity.class);
        intent.putExtra(COURSE_ID_KEY, courseId);
        startActivity(intent);
    }

    @BindView(R.id.course_assessment_list)
    RecyclerView mAssessmentRecyclerView;

    @BindView(R.id.course_mentor_add)
    ImageButton mMentorAdd;

    @OnClick(R.id.course_mentor_add)
    void mentorAddClickHandler() {
        Intent intent = new Intent(this, MentorEditorActivity.class);
        intent.putExtra(COURSE_ID_KEY, courseId);
        startActivity(intent);
    }

    @BindView(R.id.course_mentor_list)
    RecyclerView mMentorRecyclerView;

    @BindView(R.id.edit_course_fab)
    FloatingActionButton mFab;

    @OnClick(R.id.edit_course_fab)
    void fabClickHandler() {
        Intent intent = new Intent(this, CourseEditorActivity.class);
        intent.putExtra(TERM_ID_KEY, termId);
        intent.putExtra(COURSE_ID_KEY, courseId);
        startActivity(intent);
    }

    private int termId;
    private int courseId;
    private boolean mNewCourse;

    private List<AssessmentEntity> assessmentsData = new ArrayList<>();
    private AssessmentsAdapter mAssessmentsAdapter;
    
    private List<MentorEntity> mentorsData = new ArrayList<>();
    //private MentorsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initRecyclerView();
        initViewModel();
    }

    private void initRecyclerView() {
        mAssessmentRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAssessmentRecyclerView.setLayoutManager(layoutManager);

        mAssessmentsAdapter = new AssessmentsAdapter(courseId, assessmentsData, this);
        mAssessmentRecyclerView.setAdapter(mAssessmentsAdapter);
    }

    private void initViewModel() {
        CourseViewModel mViewModel = ViewModelProviders.of(this)
                .get(CourseViewModel.class);

        mViewModel.mLiveCourse.observe(this, courseEntity -> {
            Objects.requireNonNull(getSupportActionBar()).setTitle(courseEntity.getCourseName());
            mCourseStartDate.setText(DateFormatter.format(courseEntity.getCourseStartDate()));
            mCourseEndDate.setText(DateFormatter.format(courseEntity.getCourseEndDate()));
            mCourseStatus.setText(courseEntity.getCourseStatus());
            mCourseNote.setText(courseEntity.getCourseNote());
        });

        final Observer<List<AssessmentEntity>> assessmentsObserver = assessmentEntities -> {
            assessmentsData.clear();
            assessmentsData.addAll(assessmentEntities);

            if (mAssessmentsAdapter == null) {
                mAssessmentsAdapter = new AssessmentsAdapter(courseId, assessmentsData,
                        CourseActivity.this);
                mAssessmentRecyclerView.setAdapter(mAssessmentsAdapter);
            } else {
                mAssessmentsAdapter.notifyDataSetChanged();
            }
        };

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            termId = extras.getInt(TERM_ID_KEY);
            courseId = extras.getInt(COURSE_ID_KEY);
            mViewModel.getAssessmentsByCourseid(courseId).observe(this, assessmentsObserver);
            mViewModel.loadData(courseId);
        } else {
            setTitle("New Course");
            mNewCourse = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNewCourse) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_course, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }
}
