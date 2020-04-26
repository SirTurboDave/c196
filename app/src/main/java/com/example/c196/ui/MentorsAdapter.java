package com.example.c196.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.MentorActivity;
import com.example.c196.R;
import com.example.c196.database.MentorEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.c196.utilities.Constants.COURSE_ID_KEY;
import static com.example.c196.utilities.Constants.MENTOR_ID_KEY;

public class MentorsAdapter extends RecyclerView.Adapter<MentorsAdapter.ViewHolder> {

    private int courseId;
    private final List<MentorEntity> mMentors;
    private final Context mContext;

    public MentorsAdapter(int courseId, List<MentorEntity> mMentors, Context mContext) {
        this.courseId = courseId;
        this.mMentors = mMentors;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.mentor_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MentorEntity mentor = mMentors.get(position);
        holder.mTextView.setText(mentor.getMentorName());

        holder.mTextView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, MentorActivity.class);
            intent.putExtra(COURSE_ID_KEY, mentor.getCourseId());
            intent.putExtra(MENTOR_ID_KEY, mentor.getId());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mMentors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mentor_text)
        TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
