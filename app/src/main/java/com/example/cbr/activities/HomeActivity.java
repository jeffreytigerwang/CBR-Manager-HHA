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
import com.example.cbr.model.ClientInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.cbr.fragments.clientpage.ClientPageFragment;
import com.example.cbr.fragments.newclient.NewClientFragment;

public class HomeActivity extends BaseActivity implements
        TempHomeFragment.TempHomeFragmentInterface,
        ClientListFragment.ClientListFragmentInterface
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        swapToHomeFragment();
    }

    public void swapToHomeFragment() {
        TempHomeFragment tempHomeFragment = TempHomeFragment.newInstance();
        addFragment(R.id.homeFragmentContainer, tempHomeFragment, TempHomeFragment.getFragmentTag());
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.fragmentClientList:
                            selectedFragment = ClientListFragment.newInstance();
                            break;

                        case R.id.fragmentHome:
                            selectedFragment = TempHomeFragment.newInstance();
                            break;

                        case R.id.fragmentDiscussion:
                            selectedFragment = DiscussionFragment.newInstance();
                            break;

                        case R.id.fragmentNotification:
                            selectedFragment = NotificationFragment.newInstance();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.homeFragmentContainer, selectedFragment).commit();
                    return true;
                }
            };

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