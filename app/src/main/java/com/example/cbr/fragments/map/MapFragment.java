package com.example.cbr.fragments.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cbr.R;
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
    private  MapContract.Presenter mapContractPresenter;
    private FragmentMapBinding binding;
    private GoogleMap mMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setPresenter(new MapPresenter(this));

        binding = FragmentMapBinding.inflate(inflater, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(-34, 151);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }
        });

        View view = binding.getRoot();
        return view;
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    public static String getFragmentTag() {
        return MapFragment.class.getSimpleName();
    }

    @Override
    public void setPresenter(MapContract.Presenter presenter) {
        mapContractPresenter = presenter;
    }
}
