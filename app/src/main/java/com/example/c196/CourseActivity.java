package com.example.c196;

import android.os.Bundle;

import com.example.c196.viewmodel.CourseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;

public class CourseActivity extends AppCompatActivity {

    //@BindView(R.id.course_name)
    //TextView mCourseName;

    //@BindView(R.id.course_start_date)
    //TextView mCourseStartDate;

    //@BindView(R.id.course_end_date)
    //TextView mCourseEndDate;

    //@BindView(R.id.course_mentor_list)
    //RecyclerView mRecyclerView;

    private CourseViewModel courseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

}
