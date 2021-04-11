package com.example.cbr.fragments.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cbr.R;
import com.example.cbr.databinding.FragmentHomePageBinding;
import com.example.cbr.fragments.DashboardPageFragment;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.localdb.DBHelper;


public class HomePageFragment extends BaseFragment implements HomePageContract.View, View.OnClickListener{
    private HomePageContract.Presenter homePagePresenter;
    private FragmentHomePageBinding binding;
    private HomePageFragmentInterface homePageFragmentInterface;
    private DashboardPageFragment.DashboardFragmentInterface dashboardFragmentInterface;
    private DBHelper dbHelper;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            homePageFragmentInterface = (HomePageFragment.HomePageFragmentInterface) context;
            dashboardFragmentInterface = (DashboardPageFragment.DashboardFragmentInterface) context;
        } catch (ClassCastException e) {
            Log.e(getFragmentTag(), "Activity should implement HomePageFragmentInterface, " +
                    "TempHomeFragmentInterface.");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new HomePagePresenter(this));

        binding = FragmentHomePageBinding.inflate(inflater, container, false);

        binding.cardViewAllClients.setOnClickListener(this);
        binding.cardViewDashboard.setOnClickListener(this);
        binding.cardViewNewClient.setOnClickListener(this);

        dbHelper = new DBHelper(getContext());

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardViewAllClients:
                homePageFragmentInterface.swapToClientList();
                break;

            case R.id.cardViewDashboard:
                homePageFragmentInterface.swapToDashboard();
                break;

            case R.id.cardViewNewClient:
                dashboardFragmentInterface.swapToNewClient();
                break;

            case R.id.cardViewSync:
                Toast.makeText(getActivity(),getString(R.string.syncing_toast), Toast.LENGTH_LONG).show();
                syncData();
            default:
                break;
        }
    }

    private void syncData() {

    }


    public interface HomePageFragmentInterface {
        void swapToClientList();
        void swapToDashboard();
    }
}