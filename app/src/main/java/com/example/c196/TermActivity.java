package com.example.c196;

import android.os.Bundle;

import com.example.c196.database.TermEntity;
import com.example.c196.viewmodel.TermViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.widget.TextView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.c196.utilities.Constants.TERM_ID_KEY;

public class TermActivity extends AppCompatActivity {

    @BindView(R.id.term_name)
    TextView mTermName;

    private TermViewModel termViewModel;
    private boolean mNewTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ButterKnife.bind(this);

        initViewModel();
    }

    private void initViewModel() {
        termViewModel = ViewModelProviders.of(this)
                .get(TermViewModel.class);

        termViewModel.mLiveTerm.observe(this, new Observer<TermEntity>() {
            @Override
            public void onChanged(TermEntity termEntity) {
                if (termEntity != null) {
                    mTermName.setText(termEntity.getTermName());

                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle("New Term");
            mNewTerm = true;
        } else {
            int termId = extras.getInt(TERM_ID_KEY);
            termViewModel.loadData(termId);
        }
    }
}