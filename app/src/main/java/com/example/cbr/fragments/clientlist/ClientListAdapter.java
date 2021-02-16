/**
 * The following ListView Adapter is inspired Android Beginner Tutorial #8 - Custom ListView Adapter For Displaying Multiple Columns.
 * URL: https://www.youtube.com/watch?v=E6vE8fqQPTE
 */

package com.example.cbr.fragments.clientlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;

import com.example.cbr.R;

public class ClientListAdapter extends ArrayAdapter<ClientListClientInfo> {

    private static final String TAG = "ClientListAdapter";

    private Context mContext;
    private int mResource;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView name;
        TextView id;
        TextView location;
        ImageView photo;
    }

    /**
     * Default constructor for the ClientListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public ClientListAdapter(Context context, int resource, ArrayList<ClientListClientInfo> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the clients information
        String name = getItem(position).getName();
        String id = getItem(position).getId();
        String location = getItem(position).getLocation();

        //Create the client object with the information
        ClientListClientInfo clientListClientInfo = new ClientListClientInfo(name,id,location);

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textView_clientlist_name);
            holder.location = (TextView) convertView.findViewById(R.id.textView_clientlist_location);
            holder.id = (TextView) convertView.findViewById(R.id.textview_clientlist_id);
            holder.photo = (ImageView) convertView.findViewById(R.id.imageView_clientlist_Photo);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(clientListClientInfo.getName());
        holder.id.setText(clientListClientInfo.getId());
        holder.location.setText(clientListClientInfo.getLocation());
        holder.photo.setImageResource(clientListClientInfo.getPhoto());



        return convertView;
    }
}
