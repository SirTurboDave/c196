package com.example.c196;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.example.c196.viewmodel.MentorViewModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.c196.utilities.Constants.COURSE_ID_KEY;
import static com.example.c196.utilities.Constants.MENTOR_ID_KEY;

public class MentorActivity extends AppCompatActivity {

    @BindView(R.id.mentor_name)
    TextView mMentorName;

    @BindView(R.id.mentor_phone)
    TextView mMentorPhone;

    @BindView(R.id.mentor_email)
    TextView mMentorEmail;

    private int courseId;
    private int mentorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initViewModel();
    }

    private void initViewModel() {
        MentorViewModel mViewModel = ViewModelProviders.of(this)
                .get(MentorViewModel.class);

        mViewModel.mLiveMentor.observe(this, mentorEntity -> {
            Objects.requireNonNull(getSupportActionBar()).setTitle(mentorEntity.getMentorName());
            mMentorName.setText(mentorEntity.getMentorName());
            mMentorPhone.setText(mentorEntity.getMentorPhone());
            mMentorEmail.setText(mentorEntity.getMentorEmail());
        });

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            courseId = extras.getInt(COURSE_ID_KEY);
            mentorId = extras.getInt(MENTOR_ID_KEY);
            mViewModel.loadData(mentorId);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mentor, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            Intent intent = new Intent(this, MentorEditorActivity.class);
            intent.putExtra(COURSE_ID_KEY, courseId);
            intent.putExtra(MENTOR_ID_KEY, mentorId);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
