package com.example.c196;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.example.c196.viewmodel.CourseEditorViewModel;

import butterknife.BindView;

public class CourseEditorActivity extends AppCompatActivity {

    private CourseEditorViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(CourseEditorViewModel.class);
    }
}