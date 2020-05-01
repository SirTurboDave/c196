package com.example.c196;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.c196.database.CourseEntity;
import com.example.c196.ui.CoursesAdapter;
import com.example.c196.utilities.DateFormatter;
import com.example.c196.viewmodel.TermEditorViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.c196.utilities.Constants.TERM_ID_KEY;

public class TermEditorActivity extends AppCompatActivity {

    private TermEditorViewModel mViewModel;
    private GregorianCalendar cal;
    private int termId;
    private boolean mNewTerm;

    private List<CourseEntity> coursesData = new ArrayList<>();
    private CoursesAdapter mAdapter;

    @BindView(R.id.term_name_edit)
    EditText mTermName;

    @BindView(R.id.term_start_date_edit)
    EditText termStartDateText;

    @BindView(R.id.term_end_date_edit)
    EditText termEndDateText;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_check);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        initViewModel();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
            .get(TermEditorViewModel.class);

        mViewModel.mLiveTerm.observe(this, termEntity -> {
            if (termEntity != null) {
                mTermName.setText(termEntity.getTermName());
                termStartDateText.setText(DateFormatter.format(termEntity.getTermStartDate()));
                termEndDateText.setText(DateFormatter.format(termEntity.getTermEndDate()));
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle("New Term");
            mNewTerm = true;
        } else {
            setTitle("Edit Term");
            termId = extras.getInt(TERM_ID_KEY);
            mViewModel.loadData(termId);
        }

        final Observer<List<CourseEntity>> coursesObserver = courseEntities -> {
            coursesData.clear();
            coursesData.addAll(courseEntities);
        };

        mViewModel.getCoursesByTermId(termId).observe(this, coursesObserver);
    }

    @OnClick(R.id.term_edit_start_date_fab)
    public void termStartDatePicker() {
        cal = new GregorianCalendar();
        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            termStartDateText.setText(DateFormatter.format(cal.getTime()));
        };
        new DatePickerDialog(this, date, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick(R.id.term_edit_end_date_fab)
    public void termEndDatePicker() {
        cal = new GregorianCalendar();
        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            termEndDateText.setText(DateFormatter.format(cal.getTime()));
        };
        new DatePickerDialog(this, date, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNewTerm) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_term_editor, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                saveAndReturn();
                return true;
            case R.id.action_delete:
                if (coursesData != null && coursesData.size() != 0) {
                    Toast.makeText(this, "Courses must be removed from term first!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    mViewModel.deleteTerm();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        saveAndReturn();
    }

    private void saveAndReturn() {
        try {
            Date termStartDate = DateFormatter.parse(termStartDateText.getText().toString());
            Date termEndDate = DateFormatter.parse(termEndDateText.getText().toString());

            mViewModel.saveTerm(mTermName.getText().toString(), termStartDate, termEndDate);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.v("Exception", Objects.requireNonNull(e.getMessage()));
        }
        finish();
    }
}