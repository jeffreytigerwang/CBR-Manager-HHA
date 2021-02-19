/**
 * The following ListView Adapter is inspired by Android Beginner Tutorial #8 - Custom ListView Adapter For Displaying Multiple Columns.
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
import com.example.cbr.model.ClientInfo;

public class ClientListAdapter extends ArrayAdapter<com.example.cbr.model.ClientInfo> {

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
        //ImageView photo;
    }

    /**
     * Default constructor for the ClientListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public ClientListAdapter(Context context, int resource, ArrayList<com.example.cbr.model.ClientInfo> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the clients information
        String gpsLocation = getItem(position).getGpsLocation();
        String zoneLocation = getItem(position).getLocation();
        String villageNumber = getItem(position).getVillageNumber();
        String date = getItem(position).getDate();
        String firstName = getItem(position).getFirstName();
        String lastName = getItem(position).getLastName();
        String gender = getItem(position).getGender();
        Integer age = getItem(position).getAge();
        String contactNumber = getItem(position).getContactNumber();

        String caregiverContactNumber = getItem(position).getCaregiverContactNumber();

        String rateHealth = getItem(position).getRateHealth();
        String describeHealth = getItem(position).getDescribeHealth();
        String setGoalForHealth = getItem(position).getSetGoalForHealth();
        String rateEducation = getItem(position).getRateEducation();
        String describeEducation = getItem(position).getDescribeEducation();
        String setGoalForEducation = getItem(position).getSetGoalForEducation();
        String rateSocialStatus = getItem(position).getRateSocialStatus();
        String describeSocialStatus = getItem(position).getDescribeSocialStatus();
        String setGoalForSocialStatus = getItem(position).getSetGoalForSocialStatus();


        //Create the client object with the information
        ClientInfo clientInfo = new ClientInfo(true, gpsLocation, zoneLocation, villageNumber, date,
                firstName, lastName, gender, age, contactNumber, true, caregiverContactNumber, true, true
                , true, true, true, true, true, true, true, true, rateHealth, describeHealth,
                setGoalForHealth, rateEducation, describeEducation, setGoalForEducation,
                rateSocialStatus, describeSocialStatus, setGoalForSocialStatus);

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textView_clientlist_name);
            holder.location = (TextView) convertView.findViewById(R.id.textView_clientlist_location);
            holder.id = (TextView) convertView.findViewById(R.id.textview_clientlist_id);
            //holder.photo = (ImageView) convertView.findViewById(R.id.imageView_clientlist_Photo);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(clientInfo.getFirstName());
        holder.location.setText(clientInfo.getLocation());
        //holder.photo.setImageResource(clientInfo.getPhoto());



        return convertView;
    }
}
