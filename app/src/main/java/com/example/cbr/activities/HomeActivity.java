package com.example.cbr.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
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
import com.example.cbr.fragments.newvisit.NewVisitFragment;
import com.example.cbr.fragments.visitpage.VisitPageFragment;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.Users;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.util.Constants;
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
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        restoreUsersInstance(savedInstanceState);
        setupBottomNav();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(Constants.USERS_PARCEL_KEY, Users.getInstance());
    }

    private void restoreUsersInstance(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Users savedUsersInstance = savedInstanceState.getParcelable(Constants.USERS_PARCEL_KEY);
            Users users = Users.getInstance();

            users.setFirstName(savedUsersInstance.getFirstName());
            users.setLastName(savedUsersInstance.getLastName());
            users.setPassword(savedUsersInstance.getPassword());
            users.setPhoneNumber(savedUsersInstance.getPhoneNumber());
            users.setUserType(savedUsersInstance.getUserType());
            users.setId(savedUsersInstance.getId());
            users.setZones(savedUsersInstance.getZones());
        }
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
                    case HomeFragmentPagerAdapter.MAP_POSITION:
                        bottomNavigationView.getMenu().findItem(R.id.bottomMenuMap).setChecked(true);
                        break;
                    case HomeFragmentPagerAdapter.DISCUSSION_POSITION:
                        bottomNavigationView.getMenu().findItem(R.id.bottomMenuChat).setChecked(true);
                        break;
                }

                viewPager.setUserInputEnabled(position != HomeFragmentPagerAdapter.MAP_POSITION);
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

                            case R.id.bottomMenuMap:
                                currentTabPosition = HomeFragmentPagerAdapter.MAP_POSITION;
                                break;

                            case R.id.bottomMenuChat:
                                currentTabPosition = HomeFragmentPagerAdapter.DISCUSSION_POSITION;
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
        addFragmentWithAnimation(R.id.homeFragmentContainer, clientPageFragment,
                ClientPageFragment.getFragmentTag());
    }

    @Override
    public void swapToNewVisitPage(ClientInfo clientInfo) {
        NewVisitFragment newVisitFragment = NewVisitFragment.newInstance(clientInfo);
        addFragmentWithAnimation(R.id.homeFragmentContainer, newVisitFragment,
                NewVisitFragment.getFragmentTag()
        );
    }

    @Override
    public void swapToVisitPage(VisitGeneralQuestionSetData visitGeneralQuestionSetData) {
        VisitPageFragment visitPageFragment = VisitPageFragment.newInstance(visitGeneralQuestionSetData);
        addFragmentWithAnimation(R.id.homeFragmentContainer, visitPageFragment,
                VisitPageFragment.getFragmentTag());
    }

    @Override
    public void swapToReferralPage(ClientInfo clientInfo) {
        NewReferralFragment newReferralFragment = NewReferralFragment.newInstance(clientInfo);
        addFragmentWithAnimation(R.id.homeFragmentContainer, newReferralFragment,
                NewReferralFragment.getFragmentTag());
    }

    @Override
    public void swapToNewClient() {
        NewClientFragment newClientFragment = NewClientFragment.newInstance();
        addFragmentWithAnimation(R.id.homeFragmentContainer, newClientFragment,
                NewClientFragment.getFragmentTag());
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