package com.example.cbr.fragments.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.cbr.R;
import com.example.cbr.databinding.FragmentHomePageBinding;
import com.example.cbr.fragments.TempHomeFragment;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.fragments.clientlist.ClientListFragment;


public class HomePageFragment extends BaseFragment implements HomePageContract.View{
    private HomePageContract.Presenter homePagePresenter;
    private FragmentHomePageBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new HomePagePresenter(this));

        binding = FragmentHomePageBinding.inflate(inflater, container, false);
        setCardViewOnClickListener();


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

    public interface HomePageFragmentInterface {
        void swapToHomePageFragment();
    }

    private void setCardViewOnClickListener(){
        CardView allClients = binding.cardViewAllClients;
        allClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientListFragment clientListFragment = new ClientListFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeFragmentContainer, clientListFragment, ClientListFragment.getFragmentTag()).addToBackStack(null).commit();
            }
        });

        CardView dashboard = binding.cardViewDashboard;
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TempHomeFragment tempHomeFragment = new TempHomeFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeFragmentContainer, tempHomeFragment, TempHomeFragment.getFragmentTag()).addToBackStack(null).commit();
            }
        });
    }
}
