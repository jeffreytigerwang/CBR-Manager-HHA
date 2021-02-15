package com.example.cbr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.cbr.R;
import com.example.cbr.fragments.TempHomeFragment;
import com.example.cbr.fragments.base.BaseActivity;
import com.example.cbr.fragments.clientlist.ClientListFragment;
import com.example.cbr.fragments.clientpage.ClientPageFragment;

public class HomeActivity extends BaseActivity
        implements TempHomeFragment.TempHomeFragmentInterface
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        swapToHomeFragment();
    }

    public void swapToHomeFragment() {
        TempHomeFragment tempHomeFragment = TempHomeFragment.newInstance();
        addFragment(R.id.homeFragmentContainer, tempHomeFragment, TempHomeFragment.getFragmentTag());
    }

    @Override
    public void swapToClientList() {
        ClientListFragment clientListFragment = ClientListFragment.newInstance();
        replaceFragment(R.id.homeFragmentContainer, clientListFragment, ClientListFragment.getFragmentTag());
    }

    @Override
    public void swapToClientPage() {
        ClientPageFragment clientPageFragment = ClientPageFragment.newInstance();
        replaceFragment(R.id.homeFragmentContainer, clientPageFragment, ClientPageFragment.getFragmentTag());
    }


    public static Intent makeIntent(Context context){
        return new Intent(context, HomeActivity.class);
    }

}