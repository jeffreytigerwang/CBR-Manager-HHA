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
import com.example.cbr.models.ClientInfoManager;

import java.io.IOException;
import java.util.List;

public class OutstandingListAdapter extends RecyclerView.Adapter<OutstandingListAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private Context context;
    private List<ClientInfo> outstandingList;
    private DashboardFragmentInterface dashboardFragmentInterface;


    public OutstandingListAdapter(Context context, List<ClientInfo> outstandingList, DashboardFragmentInterface dashboardFragmentInterface){
        this.context = context;
        this.outstandingList = outstandingList;
        this.dashboardFragmentInterface = dashboardFragmentInterface;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public OutstandingListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_outstanding_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OutstandingListAdapter.ViewHolder holder, int position) {
        holder.bind(outstandingList.get(position));
    }

    @Override
    public int getItemCount() {
        return outstandingList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameText;
        TextView referralText;
        TextView locationText;
        TextView dateText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.dashboard_outstandingListName);
            referralText = itemView.findViewById(R.id.dashboard_outstandingListReferral);
            locationText = itemView.findViewById(R.id.dashboard_outstandingListLocation);
            dateText = itemView.findViewById(R.id.dashboard_outstandingListDate);
        }

        public void bind(final ClientInfo clientInfo){

            ClientInfoManager manager = ClientInfoManager.getInstance();

            nameText.setText(clientInfo.getFullName());
            referralText.setText(R.string.outstanding_referrals);
            locationText.setText(clientInfo.getZoneLocation());

            try {
                dateText.setText(manager.getDateOfLastVisit(clientInfo));
            } catch (IOException e) {
                e.printStackTrace();
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dashboardFragmentInterface.swapToClientPage(clientInfo);
                }
            });

        }
    }
}
