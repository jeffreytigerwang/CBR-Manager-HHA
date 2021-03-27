package com.example.cbr.adapters.questioninfoadapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.ViewHolder> {

    Context context;
    private ArrayList<String> firstName;
    private ArrayList<String> lastName;
    private ArrayList<String> message;

    public DiscussionAdapter(Context ct, ArrayList<String> firstName,
                             ArrayList<String> lastName, ArrayList<String> message) {
        this.context = ct;
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = message;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
