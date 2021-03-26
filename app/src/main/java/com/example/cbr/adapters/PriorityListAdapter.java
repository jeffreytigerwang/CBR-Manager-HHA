package com.example.cbr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.fragments.DashboardPageFragment.DashboardFragmentInterface;
import com.example.cbr.models.ClientInfo;

import java.util.List;

public class PriorityListAdapter extends RecyclerView.Adapter<PriorityListAdapter.ViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    private List<ClientInfo> priorityList;
    private DashboardFragmentInterface dashboardFragmentInterface;
    private List<String> dateOfLastVisits;

    public PriorityListAdapter(Context context, List<ClientInfo> priorityList,
                               DashboardFragmentInterface dashboardFragmentInterface,
                               List<String> datesOfLastVisits){
        this.context = context;
        this.priorityList = priorityList;
        this.dashboardFragmentInterface = dashboardFragmentInterface;
        this.inflater = LayoutInflater.from(context);
        this.dateOfLastVisits = datesOfLastVisits;
    }

    @NonNull
    @Override
    public PriorityListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_priority_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriorityListAdapter.ViewHolder holder, int position) {
        holder.bind(priorityList.get(position), dateOfLastVisits.get(position));
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
        public void bind(final ClientInfo clientInfo, String dateOfLastVisit){
            nameText.setText(clientInfo.getFullName());

            String risk = "Education " + clientInfo.getRateEducation() + ", Health " +
                    clientInfo.getRateHealth() + ", Social Status " + clientInfo.getRateSocialStatus();

            riskText.setText(risk);
            locationText.setText(clientInfo.getZoneLocation());
            dateText.setText(dateOfLastVisit);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dashboardFragmentInterface.swapToClientPage(clientInfo);
                }
            });

        }
    }
}
