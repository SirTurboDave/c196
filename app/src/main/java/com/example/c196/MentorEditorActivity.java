package com.example.c196;

import android.content.Intent;
import android.os.Bundle;

import com.example.c196.utilities.DateFormatter;
import com.example.c196.utilities.SampleData;
import com.example.c196.viewmodel.MentorEditorViewModel;
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
import android.widget.EditText;

import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.c196.utilities.Constants.COURSE_ID_KEY;
import static com.example.c196.utilities.Constants.MENTOR_ID_KEY;

public class MentorEditorActivity extends AppCompatActivity {

    private MentorEditorViewModel mViewModel;
    private int courseId;
    private int mentorId;
    private boolean mNewMentor;

    @BindView(R.id.mentor_name_edit)
    EditText mMentorName;

    @BindView(R.id.mentor_phone_edit)
    EditText mMentorPhone;

    @BindView(R.id.mentor_email_edit)
    EditText mMentorEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_check);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        initViewModel();

    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(MentorEditorViewModel.class);

        mViewModel.mLiveMentor.observe(this, (mentorEntity -> {
            if (mentorEntity != null) {
                mMentorName.setText(mentorEntity.getMentorName());
                mMentorPhone.setText(mentorEntity.getMentorPhone());
                mMentorEmail.setText(mentorEntity.getMentorEmail());
            }
        }));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            setTitle("Edit Mentor");
            courseId = extras.getInt(COURSE_ID_KEY);
            System.out.println(courseId);
            mentorId = extras.getInt(MENTOR_ID_KEY);
            mViewModel.loadMentorData(mentorId);
        } else {
            setTitle("New Mentor");
            mNewMentor = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNewMentor) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_mentor_editor, menu);
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
                mViewModel.deleteMentor();
                Intent intent = new Intent(this, CourseActivity.class);
                intent.putExtra(COURSE_ID_KEY, courseId);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MentorActivity.class);
        intent.putExtra(MENTOR_ID_KEY, mentorId);
        startActivity(intent);
    }

    private void saveAndReturn() {
        try {
            mViewModel.saveMentor(courseId, mMentorName.getText().toString(),
                    mMentorPhone.getText().toString(), mMentorEmail.getText().toString());
            Intent intent = new Intent(this, CourseActivity.class);
            intent.putExtra(COURSE_ID_KEY, courseId);
            startActivity(intent);
        } catch (Exception e) {
            Log.v("Exception", Objects.requireNonNull(e.getMessage()));
        }
        finish();
    }
}
