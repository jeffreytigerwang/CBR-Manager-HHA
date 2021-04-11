package com.example.cbr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.cbr.R;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientInfoManager;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.io.IOException;

public class MapInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{
    private Context context;
    private final View window;
    private ClientInfoManager clientInfoManager;

    public MapInfoWindowAdapter(Context context) {
        this.context = context;
        window = LayoutInflater.from(context).inflate(R.layout.map_info_window, null);
        clientInfoManager = ClientInfoManager.getInstance();
    }

    private void renderWindow(Marker marker, View view) throws IOException {
        String markerTitle = marker.getTitle();
        System.out.println(markerTitle);

        if (clientInfoManager.findClientByName(markerTitle) != null){
            ClientInfo clientInfo = clientInfoManager.findClientByName(markerTitle);

            TextView name = view.findViewById(R.id.info_textView_clientName);
            name.setText(clientInfo.getFullName());

            TextView id = view.findViewById(R.id.info_textView_clientID);
            id.setText(clientInfo.getClientId().toString());

            TextView lastVisit = view.findViewById(R.id.info_textView_clientLastVisit);
            lastVisit.setText(clientInfoManager.getDateOfLastVisit(clientInfo));
        }
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        if (clientInfoManager.findClientByName(marker.getTitle()) != null){
            try {
                renderWindow(marker, window);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return window;
        }
        else{
            return null;
        }
    }
}
