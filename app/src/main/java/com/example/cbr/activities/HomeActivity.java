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
import com.example.cbr.fragments.newclient.NewClientFragment;
import com.example.cbr.fragments.notification.NotificationFragment;
import com.example.cbr.models.ClientInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends BaseActivity implements
        TempHomeFragment.TempHomeFragmentInterface,
        ClientListFragment.ClientListFragmentInterface
{

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupBottomNav();
        swapToHomeFragment();
    }

    @Override
    public void onBackPressed() {
        String currentFragment = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else if (currentFragment.equals(ClientListFragment.getFragmentTag()) ||
                currentFragment.equals(DiscussionFragment.getFragmentTag()) ||
                currentFragment.equals(NotificationFragment.getFragmentTag())) {
            getSupportFragmentManager().popBackStack(currentFragment, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            bottomNavigationView.getMenu().getItem(0).setChecked(true);
        } else {
            super.onBackPressed();
        }
    }

    private void setupBottomNav() {
        BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.fragmentHome:
                                replaceFragment(R.id.homeFragmentContainer, TempHomeFragment.newInstance(), TempHomeFragment.getFragmentTag());
                                break;

                            case R.id.fragmentClientList:
                                replaceFragment(R.id.homeFragmentContainer, ClientListFragment.newInstance(), ClientListFragment.getFragmentTag());
                                break;

                            case R.id.fragmentDiscussion:
                                replaceFragment(R.id.homeFragmentContainer, DiscussionFragment.newInstance(), DiscussionFragment.getFragmentTag());
                                break;

                            case R.id.fragmentNotification:
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
    public void swapToNewClient() {
        NewClientFragment newClientFragment = NewClientFragment.newInstance();
        replaceFragment(R.id.homeFragmentContainer, newClientFragment, NewClientFragment.getFragmentTag());
    }


    public static Intent makeIntent(Context context){
        return new Intent(context, HomeActivity.class);
    }

}