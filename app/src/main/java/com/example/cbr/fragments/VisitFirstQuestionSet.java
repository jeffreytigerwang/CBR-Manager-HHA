package com.example.cbr.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cbr.R;

public class VisitFirstQuestionSet extends Fragment {

    private static final String LOG_TAG = "FirstQuestionSetFragment";

    public VisitFirstQuestionSet() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_visit_first_question_set, container, false);
        
        setupRadioGroup(view);

        return view;
    }

    private void setupRadioGroup(final View view) {
        RadioGroup questionOne = view.findViewById(R.id.radioGroupPurpose);

        questionOne.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TextView question2 = view.findViewById(R.id.textViewQ2);

                if (checkedId == R.id.radioButtonCBR) {
                    Log.d(LOG_TAG, "onCheckedChanged: checked CBR");
                    question2.setTextColor(Color.parseColor("#000000"));
                    // TODO: 2021-02-09 set question 2 clickable
                } else if (checkedId == R.id.radioButtonDCR) {
                    Log.d(LOG_TAG, "onCheckedChanged: checked DCR");
                    question2.setTextColor(Color.parseColor("#808080"));
                } else if (checkedId == R.id.radioButtonDCRFU) {
                    Log.d(LOG_TAG, "onCheckedChanged: checked DCRFU");
                    question2.setTextColor(Color.parseColor("#808080"));
                }
            }
        });
    }
}