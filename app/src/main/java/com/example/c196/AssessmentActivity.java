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

import com.example.c196.utilities.DateFormatter;
import com.example.c196.viewmodel.AssessmentViewModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.c196.utilities.Constants.ASSESSMENT_ID_KEY;
import static com.example.c196.utilities.Constants.COURSE_ID_KEY;
import static com.example.c196.utilities.Constants.TERM_ID_KEY;

public class AssessmentActivity extends AppCompatActivity {

    @BindView(R.id.assessment_name)
    TextView mAssessmentName;

    @BindView(R.id.assessment_type)
    TextView mAssessmentType;

    @BindView(R.id.assessment_date)
    TextView mAssessmentDate;

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
        switch(item.getItemId()) {
            case R.id.action_edit:
                Intent intent = new Intent(this, AssessmentEditorActivity.class);
                intent.putExtra(COURSE_ID_KEY, courseId);
                intent.putExtra(ASSESSMENT_ID_KEY, assessmentId);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
