package com.example.c196;

import android.content.Intent;
import android.os.Bundle;

import com.example.c196.database.CourseEntity;
import com.example.c196.database.TermEntity;
import com.example.c196.ui.CoursesAdapter;
import com.example.c196.utilities.DateFormatter;
import com.example.c196.utilities.SampleData;
import com.example.c196.viewmodel.TermViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
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
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.c196.utilities.Constants.TERM_ID_KEY;

public class TermActivity extends AppCompatActivity {

    @BindView(R.id.term_start_date)
    TextView mTermStartDate;

    @BindView(R.id.term_end_date)
    TextView mTermEndDate;

    @BindView(R.id.add_course_fab)
    FloatingActionButton mFab;

    @OnClick(R.id.add_course_fab)
    void fabClickHandler() {
        Intent intent = new Intent(this, CourseEditorActivity.class);
        intent.putExtra(TERM_ID_KEY, termId);
        startActivity(intent);
    }

    @BindView(R.id.term_course_list)
    RecyclerView mRecyclerView;

    private TermViewModel mViewModel;
    private int termId;

    private List<CourseEntity> coursesData = new ArrayList<>();
    private CoursesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);
        Toolbar toolbar = findViewById(R.id.term_toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initRecyclerView();
        initViewModel();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(TermViewModel.class);

        mViewModel.mLiveTerm.observe(this, termEntity -> {
            Objects.requireNonNull(getSupportActionBar()).setTitle(termEntity.getTermName());
            mTermStartDate.setText(DateFormatter.format(termEntity.getTermStartDate()));
            mTermEndDate.setText(DateFormatter.format(termEntity.getTermEndDate()));
        });

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            termId = extras.getInt(TERM_ID_KEY);
            mViewModel.loadData(termId);
        }

        final Observer<List<CourseEntity>> coursesObserver = courseEntities -> {
            coursesData.clear();
            coursesData.addAll(courseEntities);

            if (mAdapter == null) {
                mAdapter = new CoursesAdapter(termId, coursesData, TermActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            } else {
                mAdapter.notifyDataSetChanged();
            }
        };

        mViewModel.getCoursesByTermId(termId).observe(this, coursesObserver);
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_term, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            Intent intent = new Intent(this, TermEditorActivity.class);
            intent.putExtra(TERM_ID_KEY, termId);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}