package com.example.cbr.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cbr.R;
import com.example.cbr.fragments.VisitFirstQuestionSetFragment;
import com.example.cbr.fragments.VisitFourthQuestionSetFragment;
import com.example.cbr.fragments.VisitSecondQuestionSetFragment;
import com.example.cbr.fragments.VisitThirdQuestionSetFragment;
import com.example.cbr.models.VisitRecord;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class NewVisitActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "clientID";
    private static final String LOG_TAG = "NewVisitActivity";

    private Fragment currentFragment;
    private VisitRecord visitRecord;
    private Queue<Fragment> nextFragments;
    private Stack<Fragment> prevFragments;
    private Button buttonBack;
    private Button buttonRecord;
    private Button buttonNext;
    private byte totalFragments;
    private byte pageNum;

    public static Intent makeLaunchIntent(Context context, final long clientID) {
        Intent intent = new Intent(context, NewVisitActivity.class);
        intent.putExtra(CLIENT_ID, clientID);
        return intent;
    }

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

        currentFragment = new VisitFirstQuestionSetFragment();
        manageFragment(currentFragment);
        totalFragments += 1;
        pageNum = 1;

        visitRecord = VisitRecord.getInstance();

        nextFragments = new LinkedList<>();
        prevFragments = new Stack<>();

        setupNextButton();
        setupBackButton();
        setupRecordButton();
    }

    private void setupRecordButton() {
        buttonRecord = findViewById(R.id.buttonVisitRecord);

        // TODO: 2021-02-11 save data to db
    }

    private void setupBackButton() {
        buttonBack = findViewById(R.id.buttonVisitBack);
        // TODO: 2021-02-09 save data
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageNum-1 == 1) {
                    Log.d(LOG_TAG, "pagNum-1: " + (pageNum-1));
                    buttonBack.setVisibility(View.GONE);
                }

                nextFragments.add(currentFragment);
                currentFragment = prevFragments.pop();
                manageFragment(currentFragment);
                pageNum -= 1;

                if (pageNum < totalFragments) {
                    Log.d(LOG_TAG, "pagNum: " + pageNum + " totalFragments: " + totalFragments);
                    buttonRecord.setVisibility(View.GONE);
                    buttonNext.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void setupNextButton() {
        buttonNext = findViewById(R.id.buttonVisitNext);
        // TODO: 2021-02-09 save values as needed
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageNum == 1) {
                    prevFragments.clear();
                    nextFragments.clear();

                    totalFragments = 1;

                    if (visitRecord.isHealthChecked()) {
                        nextFragments.offer(new VisitSecondQuestionSetFragment());
                        totalFragments += 1;
                    }
                    if (visitRecord.isEducationChecked()) {
                        nextFragments.offer(new VisitThirdQuestionSetFragment());
                        totalFragments += 1;
                    }
                    if (visitRecord.isSocialChecked()) {
                        nextFragments.offer(new VisitFourthQuestionSetFragment());
                        totalFragments += 1;
                    }
                }

                if (nextFragments.peek() != null) {
                    prevFragments.add(currentFragment);
                    currentFragment = nextFragments.remove();
                    manageFragment(currentFragment);
                    pageNum += 1;
                }

                Log.d(LOG_TAG, "pageNum: " + pageNum);
                Log.d(LOG_TAG, "Is health: " + visitRecord.isHealthChecked()
                        + " is education: " + visitRecord.isEducationChecked()
                        + " is social: " + visitRecord.isSocialChecked());

                if (pageNum > 1) {
                    buttonBack.setVisibility(View.VISIBLE);
                }
                if (pageNum == totalFragments && pageNum != 1) {
                    buttonRecord.setVisibility(View.VISIBLE);
                    buttonNext.setVisibility(View.GONE);
                }
            }
        });
    }

    private void manageFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameVisitQuestion, fragment);
        fragmentTransaction.commit();
    }
}