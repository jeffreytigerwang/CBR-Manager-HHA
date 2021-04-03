package com.example.cbr.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.cbr.R;

import java.lang.ref.WeakReference;

/**
 * Location service API to handle any location jobs.
 * User must grant permission for the App to use location services before using this API.
 * Client of this API should call {@link LocationUtil#stopUpdateService()} when this service
 * is no longer needed to prevent battery drainage.
 * From: https://stackoverflow.com/a/40653111
 * */

public class LocationUtil extends Service implements LocationListener {

    private final WeakReference<Context> context;

    private static final int MIN_TIME_BETWEEN_UPDATES_MS = 5000;
    private static final int MIN_DISTANCE_CHANGE_FOR_UPDATES_M = 10;


    private boolean isGPSEnabled;
    private boolean isNetworkEnabled;
    private Location currentLocation;

    private final LocationManager locationManager;

    public LocationUtil(@NonNull Context context)
            throws CustomExceptions.PermissionNotGranted, CustomExceptions.GPSNotEnabled {
        checkPermissions(context);
        this.context = new WeakReference<>(context);
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        startUpdateService();
    }

    @SuppressLint("MissingPermission")
    private void startUpdateService() throws CustomExceptions.GPSNotEnabled {
        setProvidersEnabled();

        if (!isGPSEnabled && !isNetworkEnabled) {
            showSettingsAlert();
            throw new CustomExceptions.GPSNotEnabled(context.get().getString(R.string.gps_not_enabled));
        }
        if (isNetworkEnabled) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    MIN_TIME_BETWEEN_UPDATES_MS, MIN_DISTANCE_CHANGE_FOR_UPDATES_M, this);
            currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (isGPSEnabled) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    MIN_TIME_BETWEEN_UPDATES_MS, MIN_DISTANCE_CHANGE_FOR_UPDATES_M, this);
            currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
    }

    private void setProvidersEnabled() {
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void checkPermissions(Context context)
            throws CustomExceptions.PermissionNotGranted {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            throw new CustomExceptions.PermissionNotGranted(
                    context.getString(R.string.location_permission_not_granted));
        }
    }

    public Location getCurrentLocation() {
        if (currentLocation != null) {
            return currentLocation;
        }
        throw new CustomExceptions.LocationNotFound(context.get().getString(R.string.location_not_found));
    }

    public double getLatitude() {
        if (currentLocation != null) {
            return currentLocation.getLatitude();
        }
        throw new CustomExceptions.LocationNotFound(context.get().getString(R.string.location_not_found));
    }

    public double getLongitude() {
        if (currentLocation != null) {
            return currentLocation.getLongitude();
        }
        throw new CustomExceptions.LocationNotFound(context.get().getString(R.string.location_not_found));
    }

    /**
     * Show GPS not enabled alert dialog
     * On pressing Settings button will launch Settings Options
     * Use when the user has not enabled GPS and network
     * */
    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context.get());
        alertDialog.setTitle(R.string.location_alert_title);
        alertDialog.setMessage(R.string.gps_alert_message);

        alertDialog.setPositiveButton(R.string.alert_settings_positive_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.get().startActivity(intent);
            }
        });

        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    /**
     * Stops the GPS service for the App
     * Use to reduce battery drainage
     * */
    public void stopUpdateService() {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        currentLocation = location;
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
