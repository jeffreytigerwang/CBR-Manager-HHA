package com.example.cbr.fragments.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.cbr.R;
import com.example.cbr.activities.HomeActivity;
import com.example.cbr.databinding.FragmentClientlistBinding;
import com.example.cbr.databinding.FragmentMapBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.fragments.clientlist.ClientListContract;
import com.example.cbr.fragments.clientlist.ClientListPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends BaseFragment implements MapContract.View {
    private static  final String TAG = "MapFragment";
    private  MapContract.Presenter mapContractPresenter;
    private FragmentMapBinding binding;
    private GoogleMap mMap;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private Boolean mLocationPermissionsGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setPresenter(new MapPresenter(this));

        binding = FragmentMapBinding.inflate(inflater, container, false);

//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
//                .findFragmentById(R.id.map);
//
//        mapFragment.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(GoogleMap googleMap) {
//                mMap = googleMap;
//                // Add a marker in Uganda and move the camera
//                LatLng uganda = new LatLng(1.3733, 32.2903);
//                mMap.addMarker(new MarkerOptions().position(uganda).title(getString(R.string.marker_in_Uganda)));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(uganda));
//            }
//        });

        getLocationPermission();

        View view = binding.getRoot();
        return view;
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    public static String getFragmentTag() {
        return MapFragment.class.getSimpleName();
    }

    private void initMap(){
        Log.d(TAG, "Map is ready.");
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                // Add a marker in Uganda and move the camera
                LatLng uganda = new LatLng(1.3733, 32.2903);
                mMap.addMarker(new MarkerOptions().position(uganda).title(getString(R.string.marker_in_Uganda)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(uganda));
            }
        });
    }

    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions.");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(getContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if (ContextCompat.checkSelfPermission(getContext(),
                    COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }
            else{
                requestPermissions(permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
        else{
            requestPermissions(permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionResult: called.");
        mLocationPermissionsGranted = false;

        switch (requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0){
                    for (int i = 0; i < grantResults.length; i++){
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionResult: permission failed.");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionResult: permission granted.");
                    mLocationPermissionsGranted = true;
                    initMap();
                }
        }
    }

    @Override
    public void setPresenter(MapContract.Presenter presenter) {
        mapContractPresenter = presenter;
    }
}
