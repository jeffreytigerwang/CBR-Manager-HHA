package com.example.cbr.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.cbr.R;
import com.example.cbr.fragments.TempHomeFragment;
import com.example.cbr.fragments.base.BaseActivity;
import com.example.cbr.fragments.clientlist.ClientListFragment;
import com.example.cbr.fragments.clientpage.ClientPageFragment;
import com.example.cbr.fragments.discussion.DiscussionFragment;
import com.example.cbr.fragments.home.HomePageFragment;
import com.example.cbr.fragments.newclient.NewClientFragment;
import com.example.cbr.fragments.notification.NotificationFragment;
import com.example.cbr.fragments.visitpage.VisitPageFragment;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends BaseActivity implements
        TempHomeFragment.TempHomeFragmentInterface,
        ClientListFragment.ClientListFragmentInterface,
        ClientPageFragment.ClientPageFragmentInterface,
        HomePageFragment.HomePageFragmentInterface
{

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupBottomNav();
        //swapToHomeFragment();
        swapToHomePageFragment();

// Potentially another method to call the home page fragment.
//        HomePageFragment homePageFragment= HomePageFragment.newInstance();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        fragmentManager.beginTransaction().add(R.id.homeFragmentContainer, homePageFragment,
//                HomePageFragment.getFragmentTag()).commit();
    }



    @Override
    public void onBackPressed() {
        String currentFragment = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else if (currentFragment.equals(NotificationFragment.getFragmentTag())) {
            getSupportFragmentManager().popBackStack(currentFragment, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            bottomNavigationView.getMenu().getItem(4).setChecked(true);
        }
        else if (currentFragment.equals(ClientListFragment.getFragmentTag())){
            getSupportFragmentManager().popBackStack(currentFragment, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            bottomNavigationView.getMenu().getItem(2).setChecked(true);
        }
        else if (currentFragment.equals(DiscussionFragment.getFragmentTag())){
            getSupportFragmentManager().popBackStack(currentFragment, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            bottomNavigationView.getMenu().getItem(3).setChecked(true);
        }
        else if (currentFragment.equals(HomePageFragment.getFragmentTag())){
            getSupportFragmentManager().popBackStack(currentFragment, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            bottomNavigationView.getMenu().getItem(0).setChecked(true);
        }
        else if (currentFragment.equals(TempHomeFragment.getFragmentTag())){
            getSupportFragmentManager().popBackStack(currentFragment, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            bottomNavigationView.getMenu().getItem(1).setChecked(true);
        }
        else {
            super.onBackPressed();
        }
    }

    private void setupBottomNav() {
        BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.bottomMenuHome:
                                replaceFragment(R.id.homeFragmentContainer, HomePageFragment.newInstance(),
                                        HomePageFragment.getFragmentTag());
                                break;

                            case R.id.bottomMenuDashboard:
                                replaceFragment(R.id.homeFragmentContainer, TempHomeFragment.newInstance(), TempHomeFragment.getFragmentTag());
                                break;

                            case R.id.bottomMenuClientList:
                                replaceFragment(R.id.homeFragmentContainer, ClientListFragment.newInstance(), ClientListFragment.getFragmentTag());
                                break;

                            case R.id.bottomMenuDiscussion:
                                replaceFragment(R.id.homeFragmentContainer, DiscussionFragment.newInstance(), DiscussionFragment.getFragmentTag());
                                break;

                            case R.id.bottomMenuNotification:
                                replaceFragment(R.id.homeFragmentContainer, NotificationFragment.newInstance(), NotificationFragment.getFragmentTag());
                                break;
                        }
                        return true;
                    }
                };

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }

    public void swapToHomeFragment() {
        TempHomeFragment tempHomeFragment = TempHomeFragment.newInstance();
        addFragment(R.id.homeFragmentContainer, tempHomeFragment, TempHomeFragment.getFragmentTag());
    }

    @Override
    public void swapToClientPage(ClientInfo clientInfo) {
        ClientPageFragment clientPageFragment = ClientPageFragment.newInstance(clientInfo);
        replaceFragment(R.id.homeFragmentContainer, clientPageFragment, ClientPageFragment.getFragmentTag());
    }

    @Override
    public void swapToVisitPage(VisitGeneralQuestionSetData visitGeneralQuestionSetData) {
        VisitPageFragment visitPageFragment = VisitPageFragment.newInstance(visitGeneralQuestionSetData);
        replaceFragment(R.id.homeFragmentContainer, visitPageFragment, VisitPageFragment.getFragmentTag());
    }

    @Override
    public void swapToNewClient() {
        NewClientFragment newClientFragment = NewClientFragment.newInstance();
        replaceFragment(R.id.homeFragmentContainer, newClientFragment, NewClientFragment.getFragmentTag());
    }

    @Override
    public void swapToHomePageFragment() {
        HomePageFragment homePageFragment = HomePageFragment.newInstance();
        addFragment(R.id.homeFragmentContainer, homePageFragment,
                HomePageFragment.getFragmentTag());
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, HomeActivity.class);
    }


}