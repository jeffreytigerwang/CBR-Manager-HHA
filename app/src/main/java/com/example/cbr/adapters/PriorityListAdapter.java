package com.example.cbr.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.model.ClientInfo;

import java.util.ArrayList;

public class PriorityListAdapter extends RecyclerView.Adapter<PriorityListAdapter.ViewHolder>{


    //TODO: Possible other parameters like interface or visits info
    public PriorityListAdapter(Context context, ArrayList<ClientInfo> priorityList){

    }

    @NonNull
    @Override
    public PriorityListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PriorityListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameText;
        TextView riskText;
        TextView locationText;
        TextView dateText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.dashboard_priorityListName);
            riskText = itemView.findViewById(R.id.dashboard_priorityListRisk);
            locationText = itemView.findViewById(R.id.dashboard_outstandingListLocation);
            dateText = itemView.findViewById(R.id.dashboard_priorityListDate);
        }

        //TODO: find way to show most critical risk level and last visit date
        public void bind(final ClientInfo clientInfo){
            nameText.setText(clientInfo.getFullName());
            riskText.setText(clientInfo.getRateHealth() + "Level");
            locationText.setText(clientInfo.getGpsLocation());
            dateText.setText("February 10, 2021");



        }
    }
}
