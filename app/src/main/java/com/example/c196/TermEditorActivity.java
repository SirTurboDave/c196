package com.example.c196;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.c196.database.DateConverter;
import com.example.c196.utilities.DateFormatter;
import com.example.c196.viewmodel.EditorViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TermEditorActivity extends AppCompatActivity {

    private EditorViewModel mViewModel;
    private GregorianCalendar cal;

    @BindView(R.id.term_name_edit)
    EditText termName;

    @BindView(R.id.term_start_date_edit)
    EditText termStartDateText;

    @BindView(R.id.term_end_date_edit)
    EditText termEndDateText;

    @OnClick(R.id.save_term_fab)
    void fabClickHandler() {
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

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
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

    private void saveAndReturn() {
        try {
            Date termStartDate = new Date();
            Date termEndDate = new Date();
            mViewModel.saveTerm(termName.getText().toString(), termStartDate, termEndDate);

        } catch (Exception e) {
            Log.v("Exception", Objects.requireNonNull(e.getMessage()));
        }
        finish();
    }
}