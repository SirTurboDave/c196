package com.example.c196;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.example.c196.utilities.DateFormatter;
import com.example.c196.viewmodel.CourseEditorViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.c196.utilities.Constants.COURSE_ID_KEY;
import static com.example.c196.utilities.Constants.TERM_ID_KEY;

public class CourseEditorActivity extends AppCompatActivity {

    private CourseEditorViewModel mViewModel;
    private GregorianCalendar cal;
    private int termId;
    private boolean mNewCourse;

    @BindView(R.id.course_name_edit)
    EditText mCourseName;

    @BindView(R.id.course_start_date_edit)
    EditText courseStartDateText;

    @BindView(R.id.course_end_date_edit)
    EditText courseEndDateText;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_check);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        initViewModel();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(CourseEditorViewModel.class);

        mViewModel.mLiveCourse.observe(this, (courseEntity -> {
            if (courseEntity != null) {
                mCourseName.setText(courseEntity.getCourseName());
                courseStartDateText.setText(DateFormatter.format(courseEntity.getCourseStartDate()));
                courseEndDateText.setText(DateFormatter.format(courseEntity.getCourseEndDate()));
            }
        }));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            setTitle("Edit Course");
            termId = extras.getInt(TERM_ID_KEY);
            int courseId = extras.getInt(COURSE_ID_KEY);
            mViewModel.loadCourseData(courseId);
        } else {
            setTitle("New Course");
            mNewCourse = true;
        }
    }

    @OnClick(R.id.course_edit_start_date_fab)
    public void courseStartDatePicker() {
        cal = new GregorianCalendar();
        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            courseStartDateText.setText((DateFormatter.format(cal.getTime())));
        };
        new DatePickerDialog(this, date, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick(R.id.course_edit_end_date_fab)
    public void courseEndDatePicker() {
        cal = new GregorianCalendar();
        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            courseEndDateText.setText(DateFormatter.format(cal.getTime()));
        };
        new DatePickerDialog(this, date, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveAndReturn();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveAndReturn() {
        try {
            Date courseStartDate = DateFormatter.parse(courseStartDateText.getText().toString());
            Date courseEndDate = DateFormatter.parse(courseEndDateText.getText().toString());

            mViewModel.saveCourse(termId, mCourseName.getText().toString(), courseStartDate,
                    courseEndDate);
            finish();
        } catch (Exception e) {
            Log.v("Exception", Objects.requireNonNull(e.getMessage()));
        }
        finish();
    }
}