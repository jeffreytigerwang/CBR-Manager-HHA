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
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;

import com.example.cbr.R;

public class ClientListAdapter extends ArrayAdapter<ClientListClientInfo> {

    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    private int mResource;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView name;
        TextView id;
        TextView location;
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
        //get the persons information
        String name = getItem(position).getName();
        String id = getItem(position).getId();
        String location = getItem(position).getLocation();

        //Create the person object with the information
        ClientListClientInfo clientListClientInfo = new ClientListClientInfo(name,id,location);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textView_clientlist_name);
            holder.location = (TextView) convertView.findViewById(R.id.textView_clientlist_location);
            holder.id = (TextView) convertView.findViewById(R.id.textview_clientlist_id);

//            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
//            result = convertView;
        }


//        Animation animation = AnimationUtils.loadAnimation(mContext,
//                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
//        result.startAnimation(animation);
//        lastPosition = position;

        holder.name.setText(clientListClientInfo.getName());
        holder.id.setText(clientListClientInfo.getId());
        holder.location.setText(clientListClientInfo.getLocation());


        return convertView;
    }
}
