package com.example.cbr.fragments.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.cbr.R;
import com.example.cbr.databinding.FragmentHomePageBinding;
import com.example.cbr.fragments.TempHomeFragment;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.fragments.clientlist.ClientListFragment;
import com.example.cbr.models.ClientInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomePageFragment extends BaseFragment implements HomePageContract.View, View.OnClickListener{
    private HomePageContract.Presenter homePagePresenter;
    private FragmentHomePageBinding binding;
    private BottomNavigationView bottomNavigationView;
    private HomePageFragmentInterface homePageFragmentInterface;
    private TempHomeFragment.TempHomeFragmentInterface tempHomeFragmentInterface;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            homePageFragmentInterface = (HomePageFragment.HomePageFragmentInterface) context;
        } catch (ClassCastException e) {
            Log.e(getFragmentTag(), "Activity should implement HomePageFragmentInterface");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new HomePagePresenter(this));

        binding = FragmentHomePageBinding.inflate(inflater, container, false);
        //setCardViewOnClickListener();

        CardView cardViewAllClients = binding.cardViewAllClients;
        cardViewAllClients.setOnClickListener(this);

        CardView cardViewDashboard = binding.cardViewDashboard;
        cardViewDashboard.setOnClickListener(this);

        View view = binding.getRoot();
        return view;
    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        homePagePresenter = presenter;
    }


    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }

    public static String getFragmentTag() {
        return HomePageFragment.class.getSimpleName();
    }



//    private void setCardViewOnClickListener() {
//        CardView allClients = binding.cardViewAllClients;
//        allClients.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                homePageFragmentInterface.swapToClientList();
//            }
//        });
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardViewAllClients:
                homePageFragmentInterface.swapToClientList();
                break;

            case R.id.cardViewDashboard:
                homePageFragmentInterface.swapToDashboard();
            default:
                break;
        }
    }


    public interface HomePageFragmentInterface {
        void swapToClientList();
        void swapToDashboard();
    }
}