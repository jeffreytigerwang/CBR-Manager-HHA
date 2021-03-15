package com.example.cbr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.fragments.DashboardPageFragment.TempHomeFragmentInterface;
import com.example.cbr.models.ClientInfo;

import java.util.ArrayList;
import java.util.List;

public class PriorityListAdapter extends RecyclerView.Adapter<PriorityListAdapter.ViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    private List<ClientInfo> priorityList;
    private TempHomeFragmentInterface tempHomeFragmentInterface;

    //TODO: Possible other parameters like interface or visits info
    public PriorityListAdapter(Context context, List<ClientInfo> priorityList, TempHomeFragmentInterface tempHomeFragmentInterface){
        this.context = context;
        this.priorityList = priorityList;
        this.tempHomeFragmentInterface = tempHomeFragmentInterface;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PriorityListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_priority_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriorityListAdapter.ViewHolder holder, int position) {
        holder.bind(priorityList.get(position));
    }

    @Override
    public int getItemCount() {
        return priorityList.size();
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
            locationText = itemView.findViewById(R.id.dashboard_priorityListLocation);
            dateText = itemView.findViewById(R.id.dashboard_priorityListDate);
        }

        //TODO: find way to show most critical risk level and last visit date
        public void bind(final ClientInfo clientInfo){
            nameText.setText(clientInfo.getFullName());
            riskText.setText(clientInfo.getRateHealth());
            locationText.setText(clientInfo.getZoneLocation());
            dateText.setText(R.string.dummy_data_last_vist2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tempHomeFragmentInterface.swapToClientPage(clientInfo);
                }
            });

        }
    }
}
