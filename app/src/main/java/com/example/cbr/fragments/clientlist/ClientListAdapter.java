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
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;

import com.example.cbr.R;
import com.example.cbr.models.ClientInfo;

public class ClientListAdapter extends ArrayAdapter<ClientInfo> {

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
    public ClientListAdapter(Context context, int resource, ArrayList<ClientInfo> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the clients information
        boolean consentToInterView = getItem(position).isConsentToInterview();
        String gpsLocation = getItem(position).getGpsLocation();
        String location = getItem(position).getZoneLocation();
        String villageNumber = getItem(position).getVillageNumber();
        String date = getItem(position).getDateJoined();
        String firstName = getItem(position).getFirstName();
        String lastName = getItem(position).getLastName();
        Integer age = getItem(position).getAge();
        String contactNumber = getItem(position).getContactNumber();
        boolean caregiverPresentForInterview = getItem(position).isCaregiverPresentForInterview();
        String caregiverContactNumber = getItem(position).getCaregiverContactNumber();
        boolean amputeeDisability = getItem(position).isAmputeeDisability();
        boolean polioDisability = getItem(position).isPolioDisability();
        boolean spinalCordInjuryDisability = getItem(position).isSpinalCordInjuryDisability();
        boolean cerebralPalsyDisability = getItem(position).isCerebralPalsyDisability();
        boolean spinaBifidaDisability = getItem(position).isSpinaBifidaDisability();
        boolean hydrocephalusDisability = getItem(position).isHydrocephalusDisability();
        boolean visualImpairmentDisability = getItem(position).isVisualImpairmentDisability();
        boolean hearingImpairmentDisability = getItem(position).isHearingImpairmentDisability();
        boolean doNotKnowDisability = getItem(position).isDoNotKnowDisability();
        boolean otherDisability = getItem(position).isOtherDisability();
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
        ClientInfo clientInfo = new ClientInfo(consentToInterView, gpsLocation, location, villageNumber, date,
                firstName, lastName, age, contactNumber, caregiverPresentForInterview,
                caregiverContactNumber, amputeeDisability, polioDisability
                , spinalCordInjuryDisability, cerebralPalsyDisability, spinaBifidaDisability, hydrocephalusDisability,
                visualImpairmentDisability, hearingImpairmentDisability, doNotKnowDisability,
                otherDisability, rateHealth,
                describeHealth,
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
        holder.location.setText(clientInfo.getZoneLocation());
        //holder.photo.setImageResource(clientInfo.getPhoto());



        return convertView;
    }
}
