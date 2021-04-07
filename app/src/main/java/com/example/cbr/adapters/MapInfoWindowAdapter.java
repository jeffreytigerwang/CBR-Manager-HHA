package com.example.cbr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.cbr.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class MapInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{
    private Context context;
    private final View window;

    public MapInfoWindowAdapter(Context context) {
        this.context = context;
        window = LayoutInflater.from(context).inflate(R.layout.map_info_window, null);
    }

    private void renderWindow(){

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
