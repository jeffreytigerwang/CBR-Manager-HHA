package com.example.cbr.adapters.questioninfoadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.models.Messages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.ViewHolder> {

    Context context;

    private ArrayList<Messages> messagesArrayList;

    public DiscussionAdapter(Context ct, ArrayList<Messages> messagesArrayList) {
        this.context = ct;
        this.messagesArrayList = messagesArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_message_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(messagesArrayList.get(position).getPostDate());
        holder.date.setText(formattedDate);

        holder.picImageView.setImageResource(messagesArrayList.get(position).getImg());
        holder.firstName.setText(messagesArrayList.get(position).getFirstName());
        holder.lastName.setText(messagesArrayList.get(position).getLastName());
        holder.message.setText(messagesArrayList.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return messagesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView picImageView;
        TextView firstName, lastName, message, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            picImageView = itemView.findViewById(R.id.discussion_imageView);
            firstName = itemView.findViewById(R.id.discussion_firstName);
            lastName = itemView.findViewById(R.id.discussion_lastName);
            message = itemView.findViewById(R.id.discussion_message);
            date = itemView.findViewById(R.id.discussion_date);
        }
    }
}
