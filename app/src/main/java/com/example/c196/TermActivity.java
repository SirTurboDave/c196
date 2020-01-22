package com.example.c196;

import android.os.Bundle;

import com.example.c196.database.CourseEntity;
import com.example.c196.database.TermEntity;
import com.example.c196.ui.CoursesAdapter;
import com.example.c196.utilities.SampleData;
import com.example.c196.viewmodel.TermViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.c196.utilities.Constants.TERM_ID_KEY;

public class TermActivity extends AppCompatActivity {

    @BindView(R.id.term_name)
    TextView mTermName;

    @BindView(R.id.term_start_date)
    TextView mTermStartDate;

    @BindView(R.id.term_end_date)
    TextView mTermEndDate;

    @BindView(R.id.term_course_list)
    RecyclerView mRecyclerView;

    private TermViewModel termViewModel;
    private int termId;
    private boolean mNewTerm;

    private List<CourseEntity> coursesData = new ArrayList<>();
    private CoursesAdapter mAdapter;

    private String datePattern = "MMM-dd-yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern, Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initRecyclerView();
        initViewModel();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initViewModel() {
        termViewModel = ViewModelProviders.of(this)
                .get(TermViewModel.class);

        termViewModel.mLiveTerm.observe(this, new Observer<TermEntity>() {
            @Override
            public void onChanged(TermEntity termEntity) {
                if (termEntity != null) {
                    mTermName.setText(termEntity.getTermName());
                    mTermStartDate.setText(simpleDateFormat.format(termEntity.getTermStartDate()));
                    mTermEndDate.setText(simpleDateFormat.format(termEntity.getTermEndDate()));
                }
            }
        });

        final Observer<List<CourseEntity>> coursesObserver = new Observer<List<CourseEntity>>() {
            @Override
            public void onChanged(List<CourseEntity> courseEntities) {
                coursesData.clear();
                coursesData.addAll(courseEntities);

                if (mAdapter == null) {
                    mAdapter = new CoursesAdapter(coursesData, TermActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    mAdapter.notifyDataSetChanged();
                }
            }
        };

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            termId = extras.getInt(TERM_ID_KEY);
            termViewModel.getCoursesByTermId(termId).observe(this, coursesObserver);
            termViewModel.loadData(termId);
        } else {
            setTitle("New Term");
            mNewTerm = true;
        }
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }
}