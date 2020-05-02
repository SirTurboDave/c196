package com.example.c196;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.c196.utilities.DateFormatter;
import com.example.c196.viewmodel.AssessmentEditorViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

import static com.example.c196.utilities.Constants.ASSESSMENT_ID_KEY;
import static com.example.c196.utilities.Constants.COURSE_ID_KEY;
import static com.example.c196.utilities.Constants.MENTOR_ID_KEY;

public class AssessmentEditorActivity extends AppCompatActivity {

    private AssessmentEditorViewModel mViewModel;
    private GregorianCalendar cal;
    private int courseId;
    private int assessmentId;
    private int mAssessmentType;
    private boolean mNewAssessment;

    @BindView(R.id.assessment_name_edit)
    EditText mAssessmentName;

    @BindView(R.id.assessment_date_edit)
    EditText mAssessmentDateText;

    @BindView(R.id.assessment_edit_date_fab)
    FloatingActionButton mAssessmentDateTextFab;

    @BindView(R.id.assessment_type_spinner)
    Spinner mAssessmentTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_check);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        initViewModel();
        initSpinner();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(AssessmentEditorViewModel.class);

        mViewModel.mLiveAssessment.observe(this, (assessmentEntity -> {
            if (assessmentEntity != null) {
                mAssessmentName.setText(assessmentEntity.getAssessmentName());
                mAssessmentDateText.setText(DateFormatter.format(assessmentEntity.getAssessmentDate()));
                mAssessmentType = assessmentEntity.getAssessmentType();
                mAssessmentTypeSpinner.setSelection(mAssessmentType);
            }
        }));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            setTitle("Edit Assessment");
            courseId = extras.getInt(COURSE_ID_KEY);
            assessmentId = extras.getInt(ASSESSMENT_ID_KEY);
            mViewModel.loadAssessmentData(assessmentId);
        } else {
            setTitle("New Assessment");
            mNewAssessment = true;
        }
    }

    private void initSpinner() {
        String[] assessmentStatusArray = getResources()
                .getStringArray(R.array.assessment_type_array);
        List<CharSequence> items = new ArrayList<>(Arrays.asList(assessmentStatusArray));
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAssessmentTypeSpinner.setAdapter(adapter);
    }

    @OnClick(R.id.assessment_edit_date_fab)
    public void assessmentDatePicker() {
        cal = new GregorianCalendar();
        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            mAssessmentDateText.setText(DateFormatter.format(cal.getTime()));
        };
        new DatePickerDialog(this, date, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnItemSelected(R.id.assessment_type_spinner)
    void onItemSelected(int position) {
        mAssessmentType = position;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNewAssessment) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_assessment_editor, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveAndReturn();
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            mViewModel.deleteAssessment();
            Intent intent = new Intent(this, CourseActivity.class);
            intent.putExtra(COURSE_ID_KEY, courseId);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, AssessmentActivity.class);
        intent.putExtra(ASSESSMENT_ID_KEY, assessmentId);
        startActivity(intent);
    }

    private void saveAndReturn() {
        try {
            Date mAssessmentDate = DateFormatter.parse(mAssessmentDateText.getText().toString());
            mViewModel.saveAssessment(courseId, mAssessmentName.getText().toString(),
                    mAssessmentType, mAssessmentDate);
            Intent intent = new Intent(this, CourseActivity.class);
            intent.putExtra(COURSE_ID_KEY, courseId);
            startActivity(intent);
        } catch (Exception e) {
            Log.v("Exception", Objects.requireNonNull(e.getMessage()));
        }
        finish();
    }
}