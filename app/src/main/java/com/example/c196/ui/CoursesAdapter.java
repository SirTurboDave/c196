package com.example.c196.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.CourseActivity;
import com.example.c196.R;
import com.example.c196.TermActivity;
import com.example.c196.TermEditorActivity;
import com.example.c196.database.CourseEntity;
import com.example.c196.database.TermEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.c196.utilities.Constants.COURSE_ID_KEY;
import static com.example.c196.utilities.Constants.TERM_ID_KEY;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {

    private int termId;
    private final List<CourseEntity> mCourses;
    private final Context mContext;

    public CoursesAdapter(int termId, List<CourseEntity> mCourses, Context mContext) {
        this.termId = termId;
        this.mCourses = mCourses;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.course_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CourseEntity course = mCourses.get(position);
        holder.mTextView.setText(course.getCourseName());

        holder.mTextView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, CourseActivity.class);
            intent.putExtra(TERM_ID_KEY, course.getTermId());
            intent.putExtra(COURSE_ID_KEY, course.getId());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.course_text)
        TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
