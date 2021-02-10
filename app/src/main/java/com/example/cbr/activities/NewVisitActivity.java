package com.example.cbr.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.cbr.R;
import com.example.cbr.fragments.VisitFirstQuestionSet;

public class NewVisitActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "clientID";
    private static final String LOG_TAG = "NewVisitActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visit);

        Intent intent = getIntent();
        final long clientID = intent.getLongExtra(CLIENT_ID, -1);

        if (clientID != -1) {
            // TODO: 2021-02-09 get client info from DB
        } else {
            Log.d(LOG_TAG, "onCreate: failed to get client ID");
        }

        Fragment firstQuestionSet = new VisitFirstQuestionSet();
        manageFragment(firstQuestionSet);

        Button buttonNext = findViewById(R.id.buttonVisitNext);
        Button buttonBack = findViewById(R.id.buttonVisitBack);
        setupNextButton(buttonNext);
        setupBackButton(buttonBack);
    }

    private void setupBackButton(Button buttonBack) {
        // TODO: 2021-02-09 go back to prev frag
    }

    private void setupNextButton(Button buttonNext) {
        // TODO: 2021-02-09 next frag
    }

    private void manageFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameVisitQuestion, fragment);
        fragmentTransaction.commit();
    }
}