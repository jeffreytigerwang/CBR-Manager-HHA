package com.example.cbr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cbr.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupAddNewClientButton();
        setupAddNewVisitButton();
        setupDashboardButton();
        setupAddNewReferralButton();
        setupViewAllClientsButton();
        setupSyncButton();
    }

    private void setupSyncButton() {
        Button button = findViewById(R.id.button_sync);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setupViewAllClientsButton() {
        Button button = findViewById(R.id.button_client_list);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setupAddNewReferralButton() {
        Button button = findViewById(R.id.button_new_referral);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setupDashboardButton() {
        Button button = findViewById(R.id.button_dashboard);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setupAddNewVisitButton() {
        Button button = findViewById(R.id.button_new_visit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setupAddNewClientButton() {

        Button button = findViewById(R.id.button_new_client);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, HomeActivity.class);
    }

}