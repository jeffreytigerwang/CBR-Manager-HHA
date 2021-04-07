package com.example.cbr.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cbr.R;
import com.example.cbr.adapters.HomeFragmentPagerAdapter;
import com.example.cbr.fragments.DashboardPageFragment;
import com.example.cbr.fragments.base.BaseActivity;
import com.example.cbr.fragments.clientlist.ClientListFragment;
import com.example.cbr.fragments.clientpage.ClientPageFragment;
import com.example.cbr.fragments.home.HomePageFragment;
import com.example.cbr.fragments.newclient.NewClientFragment;
import com.example.cbr.fragments.newreferral.NewReferralFragment;
import com.example.cbr.fragments.visitpage.VisitPageFragment;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends BaseActivity implements
        DashboardPageFragment.DashboardFragmentInterface,
        ClientListFragment.ClientListFragmentInterface,
        ClientPageFragment.ClientPageFragmentInterface,
        HomePageFragment.HomePageFragmentInterface
{

    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager;
    private HomeFragmentPagerAdapter homeFragmentPagerAdapter;
    private int currentTabPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupBottomNav();
    }

    private void setupBottomNav() {
        viewPager = findViewById(R.id.homeViewPager);
        homeFragmentPagerAdapter = new HomeFragmentPagerAdapter(this);
        viewPager.setAdapter(homeFragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(5);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case HomeFragmentPagerAdapter.HOME_POSITION:
                        bottomNavigationView.getMenu().findItem(R.id.bottomMenuHome).setChecked(true);
                        break;
                    case HomeFragmentPagerAdapter.DASHBOARD_POSITION:
                        bottomNavigationView.getMenu().findItem(R.id.bottomMenuDashboard).setChecked(true);
                        break;
                    case HomeFragmentPagerAdapter.LIST_POSITION:
                        bottomNavigationView.getMenu().findItem(R.id.bottomMenuClientList).setChecked(true);
                        break;
                    case HomeFragmentPagerAdapter.DISCUSSION_POSITION:
                        bottomNavigationView.getMenu().findItem(R.id.bottomMenuDiscussion).setChecked(true);
                        break;
                    case HomeFragmentPagerAdapter.NOTIFICATION_POSITION:
                        bottomNavigationView.getMenu().findItem(R.id.bottomMenuNotification).setChecked(true);
                        break;
                }
            }
        });

        BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.bottomMenuHome:
                                currentTabPosition = HomeFragmentPagerAdapter.HOME_POSITION;
                                break;
                            case R.id.bottomMenuDashboard:
                                currentTabPosition = HomeFragmentPagerAdapter.DASHBOARD_POSITION;
                                break;

                            case R.id.bottomMenuClientList:
                                currentTabPosition = HomeFragmentPagerAdapter.LIST_POSITION;
                                break;

                            case R.id.bottomMenuDiscussion:
                                currentTabPosition = HomeFragmentPagerAdapter.DISCUSSION_POSITION;
                                break;

                            case R.id.bottomMenuNotification:
                                currentTabPosition = HomeFragmentPagerAdapter.NOTIFICATION_POSITION;
                                break;
                        }

                        viewPager.setCurrentItem(currentTabPosition);
                        return true;
                    }
                };

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }

    @Override
    public void swapToClientPage(ClientInfo clientInfo) {
        ClientPageFragment clientPageFragment = ClientPageFragment.newInstance(clientInfo);
        addFragment(R.id.homeFragmentContainer, clientPageFragment, ClientPageFragment.getFragmentTag());
    }

    @Override
    public void swapToVisitPage(VisitGeneralQuestionSetData visitGeneralQuestionSetData) {
        VisitPageFragment visitPageFragment = VisitPageFragment.newInstance(visitGeneralQuestionSetData);
        addFragment(R.id.homeFragmentContainer, visitPageFragment, VisitPageFragment.getFragmentTag());
    }

    @Override
    public void swapToReferralPage(ClientInfo clientInfo) {
        NewReferralFragment newReferralFragment = NewReferralFragment.newInstance(clientInfo);
        addFragment(R.id.homeFragmentContainer, newReferralFragment, NewReferralFragment.getFragmentTag());
    }

    @Override
    public void swapToNewClient() {
        NewClientFragment newClientFragment = NewClientFragment.newInstance();
        addFragment(R.id.homeFragmentContainer, newClientFragment, NewClientFragment.getFragmentTag());
    }

    @Override
    public void swapToClientList() {
        currentTabPosition = HomeFragmentPagerAdapter.LIST_POSITION;
        viewPager.setCurrentItem(HomeFragmentPagerAdapter.LIST_POSITION);
    }

    @Override
    public void swapToDashboard() {
        currentTabPosition = HomeFragmentPagerAdapter.DASHBOARD_POSITION;
        viewPager.setCurrentItem(HomeFragmentPagerAdapter.DASHBOARD_POSITION);
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, HomeActivity.class);
    }
}