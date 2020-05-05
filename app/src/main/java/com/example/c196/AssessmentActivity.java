package com.example.c196;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.example.c196.utilities.DateFormatter;
import com.example.c196.utilities.MyReceiver;
import com.example.c196.viewmodel.AssessmentViewModel;

import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.c196.utilities.Constants.ASSESSMENT_ID_KEY;
import static com.example.c196.utilities.Constants.COURSE_ID_KEY;
import static com.example.c196.utilities.Constants.NOTIFICATION_TEXT;
import static com.example.c196.utilities.Constants.NOTIFICATION_TITLE;
import static com.example.c196.utilities.Constants.TERM_ID_KEY;

public class AssessmentActivity extends AppCompatActivity {
    private Date assessmentDate;

    @BindView(R.id.assessment_name)
    TextView mAssessmentName;

    @BindView(R.id.assessment_type)
    TextView mAssessmentType;

    @BindView(R.id.assessment_date)
    TextView mAssessmentDate;

    @OnClick(R.id.assessment_date_alert_fab)
    public void setAssessmentDateAlarm(View view) {
        try {
            assessmentDate = DateFormatter.parse(mAssessmentDate.getText().toString());
        } catch (Exception e) {
            Log.v("Exception", Objects.requireNonNull(e.getMessage()));
        }

        Intent intent = new Intent(this, MyReceiver.class);
        intent.putExtra(NOTIFICATION_TITLE, "Assessment Goal");
        intent.putExtra(NOTIFICATION_TEXT, "An assessment goal is today!");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        long date = assessmentDate.getTime();
        assert alarmManager != null;
        alarmManager.set(AlarmManager.RTC_WAKEUP, date, pendingIntent);
    }

    private int courseId;
    private int assessmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);

        ButterKnife.bind(this);
        initViewModel();
    }

    private void initViewModel() {
        AssessmentViewModel mViewModel = ViewModelProviders.of(this)
                .get(AssessmentViewModel.class);

        mViewModel.mLiveAssessment.observe(this, assessmentEntity -> {
            Objects.requireNonNull(getSupportActionBar()).setTitle(assessmentEntity
                    .getAssessmentName());
            mAssessmentName.setText(assessmentEntity.getAssessmentName());
            switch(assessmentEntity.getAssessmentType()) {
                case 0:
                    mAssessmentType.setText(R.string.objective);
                    break;
                case 1:
                    mAssessmentType.setText(R.string.performance);
                    break;
            }
            mAssessmentDate.setText(DateFormatter.format(assessmentEntity.getAssessmentDate()));
        });

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            courseId = extras.getInt(COURSE_ID_KEY);
            assessmentId = extras.getInt(ASSESSMENT_ID_KEY);
            mViewModel.loadData(assessmentId);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_assessment, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            Intent intent = new Intent(this, AssessmentEditorActivity.class);
            intent.putExtra(COURSE_ID_KEY, courseId);
            intent.putExtra(ASSESSMENT_ID_KEY, assessmentId);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
