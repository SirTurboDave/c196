package com.example.c196;

import android.content.Intent;
import android.os.Bundle;

import com.example.c196.database.TermEntity;
import com.example.c196.ui.TermsAdapter;
import com.example.c196.utilities.SampleData;
import com.example.c196.viewmodel.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @OnClick(R.id.fab_new_term)
    void fabClickHandler() {
        Intent intent = new Intent(this, TermEditorActivity.class);
        startActivity(intent);
    }

    private List<TermEntity> termsData = new ArrayList<>();
    private TermsAdapter mAdapter;
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initRecyclerView();
        initViewModel();
    }

    private void initViewModel() {

        final Observer<List<TermEntity>> termsObserver = termEntities -> {
            termsData.clear();
            termsData.addAll(termEntities);

            if (mAdapter == null) {
                mAdapter = new TermsAdapter(termsData, MainActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            } else {
                mAdapter.notifyDataSetChanged();
            }
        };

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.mTerms.observe(this, termsObserver);
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_sample_terms) {
            addSampleTerms();
            return true;
        } else if (id == R.id.action_delete_all_terms) {
            deleteAllTerms();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllTerms() {
        mViewModel.deleteSampleData();
    }

    private void addSampleTerms() {
        mViewModel.addSampleData();
    }
}