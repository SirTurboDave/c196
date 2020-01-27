package com.example.c196;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.c196.database.TermEntity;
import com.example.c196.utilities.DateFormatter;
import com.example.c196.viewmodel.EditorViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.c196.utilities.Constants.TERM_ID_KEY;

public class TermEditorActivity extends AppCompatActivity {

    private EditorViewModel mViewModel;
    private GregorianCalendar cal;

    @BindView(R.id.term_name_edit)
    EditText mTermName;

    @BindView(R.id.term_start_date_edit)
    EditText termStartDateText;

    @BindView(R.id.term_end_date_edit)
    EditText termEndDateText;
    private boolean mNewTerm;

    @OnClick(R.id.save_term_fab)
    void fabClickHandler() {
        saveAndReturn();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_check);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        initViewModel();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
            .get(EditorViewModel.class);

        mViewModel.mLiveTerm.observe(this, (new Observer<TermEntity>() {
            @Override
            public void onChanged(TermEntity termEntity) {
                if (termEntity != null) {
                    mTermName.setText(termEntity.getTermName());
                }
            }
        }));

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle("New Term");
            mNewTerm = true;
        } else {
            setTitle("Edit Term");
            int termId = extras.getInt(TERM_ID_KEY);
            mViewModel.loadData(termId);
        }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveAndReturn();
            return true;
        } else {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        saveAndReturn();
    }

    private void saveAndReturn() {
        try {
            Date termStartDate = DateFormatter.parse(termStartDateText.toString());
            Date termEndDate = DateFormatter.parse(termEndDateText.toString());

            mViewModel.saveTerm(mTermName.getText().toString(), termStartDate, termEndDate);
            finish();
        } catch (Exception e) {
            Log.v("Exception", Objects.requireNonNull(e.getMessage()));
        }
        finish();
    }
}