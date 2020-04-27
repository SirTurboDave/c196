package com.example.c196;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.example.c196.database.CourseEntity;
import com.example.c196.utilities.DateFormatter;
import com.example.c196.viewmodel.CourseEditorViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

import static com.example.c196.utilities.Constants.COURSE_ID_KEY;
import static com.example.c196.utilities.Constants.TERM_ID_KEY;

public class CourseEditorActivity extends AppCompatActivity {

    private CourseEditorViewModel mViewModel;
    private GregorianCalendar cal;
    private int termId;
    private int courseStatus;
    private boolean mNewCourse;
    private ArrayAdapter<CharSequence> adapter;
    private List<CharSequence> items;
    private String[] courseStatusArray;

    @BindView(R.id.course_name_edit)
    EditText mCourseName;

    @BindView(R.id.course_start_date_edit)
    EditText courseStartDateText;

    @BindView(R.id.course_end_date_edit)
    EditText courseEndDateText;

    @BindView(R.id.course_status_spinner)
    Spinner courseStatusSpinner;

    @BindView(R.id.course_note_edit)
    EditText mCourseNote;

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
        initSpinner();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(CourseEditorViewModel.class);

        mViewModel.mLiveCourse.observe(this, (courseEntity -> {
            if (courseEntity != null) {
                mCourseName.setText(courseEntity.getCourseName());
                courseStartDateText.setText(DateFormatter.format(courseEntity.getCourseStartDate()));
                courseEndDateText.setText(DateFormatter.format(courseEntity.getCourseEndDate()));
                courseStatus = courseEntity.getCourseStatus();
                courseStatusSpinner.setSelection(courseStatus);
                mCourseNote.setText(courseEntity.getCourseNote());
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

    private void initSpinner() {
        courseStatusArray = getResources().getStringArray(R.array.course_status_array);
        items = new ArrayList<>(Arrays.asList(courseStatusArray));
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseStatusSpinner.setAdapter(adapter);
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

    @OnItemSelected(R.id.course_status_spinner)
    void onItemSelected(int position) {
        courseStatus = position;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNewCourse) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_course_editor, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveAndReturn();
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            mViewModel.deleteCourse();
            Intent intent = new Intent(this, TermActivity.class);
            intent.putExtra(TERM_ID_KEY, termId);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveAndReturn() {
        try {
            Date courseStartDate = DateFormatter.parse(courseStartDateText.getText().toString());
            Date courseEndDate = DateFormatter.parse(courseEndDateText.getText().toString());


            mViewModel.saveCourse(termId, mCourseName.getText().toString(), courseStartDate,
                    courseEndDate, courseStatus, mCourseNote.getText().toString());
            finish();
        } catch (Exception e) {
            Log.v("Exception", Objects.requireNonNull(e.getMessage()));
        }
        finish();
    }
}