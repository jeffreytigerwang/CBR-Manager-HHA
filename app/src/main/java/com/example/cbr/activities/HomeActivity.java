package com.example.cbr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.cbr.R;
import com.example.cbr.fragments.TempHomeFragment;
import com.example.cbr.fragments.base.BaseActivity;
import com.example.cbr.fragments.clientlist.ClientListFragment;
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

        swapToHomeFragment();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.fragment_clientlist:
                            selectedFragment = new ClientListFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.homeFragmentContainer, selectedFragment).commit();
                    return true;
                }
            };

    public void swapToHomeFragment() {
        TempHomeFragment tempHomeFragment = TempHomeFragment.newInstance();
        addFragment(R.id.homeFragmentContainer, tempHomeFragment, TempHomeFragment.getFragmentTag());
    }

    @Override
    public void swapToClientList() {
        ClientListFragment clientListFragment = ClientListFragment.newInstance();
        replaceFragment(R.id.homeFragmentContainer, clientListFragment, ClientListFragment.getFragmentTag());

    }


    public static Intent makeIntent(Context context){
        return new Intent(context, HomeActivity.class);
    }

}