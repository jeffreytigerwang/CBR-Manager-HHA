/**
 * The following ListView Adapter is inspired by Android Beginner Tutorial #8 - Custom ListView Adapter For Displaying Multiple Columns.
 * URL: https://www.youtube.com/watch?v=E6vE8fqQPTE
 */

package com.example.cbr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.fragments.clientlist.ClientListFragment.ClientListFragmentInterface;
import com.example.cbr.models.ClientInfo;

import java.util.ArrayList;

public class ClientListAdapter extends RecyclerView.Adapter<ClientListAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<ClientInfo> clientInfoList;
    private ClientListFragmentInterface clientListFragmentInterface;

    public ClientListAdapter(Context context, ArrayList<ClientInfo> clientInfoList, ClientListFragmentInterface clientListFragmentInterface) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.clientInfoList = clientInfoList;
        this.clientListFragmentInterface = clientListFragmentInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_clientlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(clientInfoList.get(position));
    }

    @Override
    public int getItemCount() {
        return clientInfoList.size();
    }

    /**
     * Holds variables in a View
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView idText;
        TextView locationText;

        public ViewHolder(View itemView) {
            super(itemView);
            nameText =  itemView.findViewById(R.id.textView_clientlist_name);
            idText = itemView.findViewById(R.id.textview_clientlist_id);
            locationText = itemView.findViewById(R.id.textView_clientlist_location);
        }
        //ImageView photo;

        public void bind(final ClientInfo clientInfo) {
            nameText.setText(clientInfo.getFullName());
            idText.setText(clientInfo.getId());
            locationText.setText(clientInfo.getZoneLocation());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clientListFragmentInterface.swapToClientPage(clientInfo);
                }
            });
        }
    }
}
