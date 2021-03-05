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

public class OutstandingListAdapter extends RecyclerView.Adapter<OutstandingListAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private Context context;
    private List<ClientInfo> outstandingList;
    private TempHomeFragmentInterface tempHomeFragmentInterface;


    public OutstandingListAdapter(Context context, ArrayList<ClientInfo> outstandingList, TempHomeFragmentInterface tempHomeFragmentInterface){
        this.context = context;
        this.outstandingList = outstandingList;
        this.tempHomeFragmentInterface = tempHomeFragmentInterface;
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

        //TODO: find way to show most critical risk level and last visit date
        public void bind(final ClientInfo clientInfo){
            nameText.setText(clientInfo.getFullName());
            referralText.setText("Outstanding Referral");
            locationText.setText(clientInfo.getZoneLocation());
            dateText.setText("Last Visit: February 9, 2021");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tempHomeFragmentInterface.swapToClientPage(clientInfo);
                }
            });

        }
    }
}
