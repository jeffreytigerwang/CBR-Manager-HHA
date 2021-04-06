package com.example.cbr.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
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
import com.example.cbr.fragments.newvisit.NewVisitFragment;
import com.example.cbr.fragments.visitpage.VisitPageFragment;
import com.example.cbr.models.ClientInfo;
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

        setupBottomNav();
        requestLocationPermissions();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            bottomNavigationView.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);
        }
        super.onBackPressed();
    }

    private void requestLocationPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, Constants.LOCATION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, getString(R.string.automatic_fill_location),
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, R.string.location_permission_not_granted,
                        Toast.LENGTH_SHORT).show();
            }
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
                        bottomNavigationView.getMenu().findItem(R.id.bottomMenuDiscussion).setChecked(true);
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

                            case R.id.bottomMenuDiscussion:
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


    @Override
    protected void addFragment(int containerViewId, Fragment fragment, String fragmentTag) {
        super.addFragment(containerViewId, fragment, fragmentTag);

        viewPager.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    protected void addFragmentWithAnimation(int containerViewId, Fragment fragment,
                                            String fragmentTag) {
        super.addFragmentWithAnimation(containerViewId, fragment, fragmentTag);

        viewPager.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    protected void replaceFragment(int containerViewId, Fragment fragment, String fragmentTag) {
        super.replaceFragment(containerViewId, fragment, fragmentTag);

        viewPager.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.GONE);
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, HomeActivity.class);
    }
}