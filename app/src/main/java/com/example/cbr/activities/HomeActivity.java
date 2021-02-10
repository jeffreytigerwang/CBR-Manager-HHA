package com.example.cbr.activities;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.cbr.R;
import com.example.cbr.fragments.TempHomeFragment;
import com.example.cbr.fragments.base.BaseActivity;
import com.example.cbr.fragments.clientlist.ClientListFragment;
import com.example.cbr.fragments.discussion.DiscussionFragment;
import com.example.cbr.fragments.notification.NotificationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends BaseActivity
        implements TempHomeFragment.TempHomeFragmentInterface
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.homeFragmentContainer, new TempHomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.fragmentClientList:
                            selectedFragment = new ClientListFragment();
                            break;

                        case R.id.fragmentHome:
                            selectedFragment = new TempHomeFragment();
                            break;

                        case R.id.fragmentDiscussion:
                            selectedFragment = new DiscussionFragment();
                            break;

                        case R.id.fragmentNotification:
                            selectedFragment = new NotificationFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.homeFragmentContainer, selectedFragment).commit();
                    return true;
                }
            };



    @Override
    public void swapToClientList() {
        ClientListFragment clientListFragment = ClientListFragment.newInstance();
        replaceFragment(R.id.homeFragmentContainer, clientListFragment, ClientListFragment.getFragmentTag());

    }


    public static Intent makeIntent(Context context){
        return new Intent(context, HomeActivity.class);
    }

}