package com.example.cbr.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cbr.R;
import com.example.cbr.models.Constants;
import com.example.cbr.models.VisitRecord;

import org.w3c.dom.Text;

import static com.example.cbr.models.Constants.CANCELLED;
import static com.example.cbr.models.Constants.CONCLUDED;
import static com.example.cbr.models.Constants.EDUCATION_ADVICE_DESC;
import static com.example.cbr.models.Constants.EDUCATION_ADVOCACY_DESC;
import static com.example.cbr.models.Constants.EDUCATION_ENCOURAGEMENT_DESC;
import static com.example.cbr.models.Constants.EDUCATION_GOAL_STATUS;
import static com.example.cbr.models.Constants.EDUCATION_OUTCOME_DESC;
import static com.example.cbr.models.Constants.EDUCATION_REF_DESC;
import static com.example.cbr.models.Constants.IS_EDUCATION_ADVICE_CHECKED;
import static com.example.cbr.models.Constants.IS_EDUCATION_ADVOCACY_CHECKED;
import static com.example.cbr.models.Constants.IS_EDUCATION_ENCOURAGEMENT_CHECKED;
import static com.example.cbr.models.Constants.IS_EDUCATION_REF_CHECKED;
import static com.example.cbr.models.Constants.ONGOING;

public class VisitThirdQuestionSetFragment extends Fragment {

    private EditText editTextAdvice;
    private EditText editTextAdvocacy;
    private EditText editTextRef;
    private EditText editTextEncouragement;
    private EditText editTextEducationOutcome;

    private final VisitRecord visitRecord;
    private final Context context;
    private CheckBox checkBoxEncouragement;
    private CheckBox checkBoxAdvice;
    private CheckBox checkBoxAdvocacy;
    private CheckBox checkBoxRef;
    private RadioGroup goalStatus;
    private TextView question13;

    public VisitThirdQuestionSetFragment(VisitRecord visitRecord, Context context) {
        this.visitRecord = visitRecord;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_visit_third_question_set, container, false);

        preLoadViews(view);

        setupCheckBoxes(view);
        setupRadioGroup(view);

        return view;
    }

    private void preLoadViews(View view) {
        findViews(view);

        SharedPreferences sharedPref = context.getSharedPreferences(Constants.QUESTION_SET_3_PREF_NAME, Context.MODE_PRIVATE);

        boolean isAdviceChecked = sharedPref.getBoolean(IS_EDUCATION_ADVICE_CHECKED, false);
        boolean isAdvocacyChecked = sharedPref.getBoolean(IS_EDUCATION_ADVOCACY_CHECKED, false);
        boolean isRefChecked = sharedPref.getBoolean(IS_EDUCATION_REF_CHECKED, false);
        boolean isEncouragementChecked = sharedPref.getBoolean(IS_EDUCATION_ENCOURAGEMENT_CHECKED, false);
        String adviceDesc = sharedPref.getString(EDUCATION_ADVICE_DESC, "");
        String advocacyDesc = sharedPref.getString(EDUCATION_ADVOCACY_DESC, "");
        String refDesc = sharedPref.getString(EDUCATION_REF_DESC, "");
        String encouragementDesc = sharedPref.getString(EDUCATION_ENCOURAGEMENT_DESC, "");
        String educationOutcomeDesc = sharedPref.getString(EDUCATION_OUTCOME_DESC, "");
        String goalStatus = sharedPref.getString(EDUCATION_GOAL_STATUS, "");

        checkBoxAdvice.setChecked(isAdviceChecked);
        loadEditTextVisibility(isAdviceChecked, editTextAdvice);
        checkBoxAdvocacy.setChecked(isAdvocacyChecked);
        loadEditTextVisibility(isAdvocacyChecked, editTextAdvocacy);
        checkBoxRef.setChecked(isRefChecked);
        loadEditTextVisibility(isRefChecked, editTextRef);
        checkBoxEncouragement.setChecked(isEncouragementChecked);
        loadEditTextVisibility(isEncouragementChecked, editTextEncouragement);

        editTextAdvice.setText(adviceDesc);
        editTextAdvocacy.setText(advocacyDesc);
        editTextRef.setText(refDesc);
        editTextEncouragement.setText(encouragementDesc);
        editTextEducationOutcome.setText(educationOutcomeDesc);

        if (goalStatus.equalsIgnoreCase(CANCELLED)) {
            this.goalStatus.check(R.id.radioButtonEducationCancelled);
        } else if (goalStatus.equalsIgnoreCase(ONGOING)) {
            this.goalStatus.check(R.id.radioButtonEducationOngoing);
        } else if (goalStatus.equalsIgnoreCase(CONCLUDED)) {
            this.goalStatus.check(R.id.radioButtonEducationConcluded);
            question13.setVisibility(View.VISIBLE);
            editTextEducationOutcome.setVisibility(View.VISIBLE);
        }
    }

    private void loadEditTextVisibility(boolean isChecked, EditText editText) {
        if (isChecked) {
            editText.setVisibility(View.VISIBLE);
        }
    }

    private void findViews(View view) {
        editTextAdvice = view.findViewById(R.id.editTextEducationAdvice);
        editTextAdvocacy = view.findViewById(R.id.editTextEducationAdvocacy);
        editTextRef = view.findViewById(R.id.editTextEducationRef);
        editTextEncouragement = view.findViewById(R.id.editTextEducationEncouragement);
        editTextEducationOutcome = view.findViewById(R.id.editTextEducationOutcome);

        goalStatus = view.findViewById(R.id.radioGroupEducationGoalStatus);

        checkBoxAdvice = view.findViewById(R.id.checkBoxEducationAdvice);
        checkBoxAdvocacy = view.findViewById(R.id.checkBoxEducationAdvocacy);
        checkBoxRef = view.findViewById(R.id.checkBoxEducationRef);
        checkBoxEncouragement = view.findViewById(R.id.checkBoxEducationEncouragement);

        question13 = view.findViewById(R.id.textViewQ13);
    }

    private void setupRadioGroup(final View view) {
        goalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TextView question13 = view.findViewById(R.id.textViewQ13);
                if (checkedId == R.id.radioButtonEducationConcluded) {
                    visitRecord.setEducationGoalStatus(CONCLUDED);
                    question13.setVisibility(View.VISIBLE);
                    editTextEducationOutcome.setVisibility(View.VISIBLE);
                } else {
                    question13.setVisibility(View.GONE);
                    editTextEducationOutcome.setVisibility(View.GONE);
                }
                if (checkedId == R.id.radioButtonEducationCancelled) {
                    visitRecord.setEducationGoalStatus(CANCELLED);
                }
                if (checkedId == R.id.radioButtonEducationOngoing) {
                    visitRecord.setEducationGoalStatus(ONGOING);
                }
            }
        });
    }

    private void setupCheckBoxes(View view) {
        checkBoxAdvice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextAdvice.setVisibility(View.VISIBLE);
                    visitRecord.setEducationAdviceChecked(true);
                } else {
                    editTextAdvice.setVisibility(View.GONE);
                    visitRecord.setEducationAdviceChecked(false);
                }
            }
        });
        checkBoxAdvocacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextAdvocacy.setVisibility(View.VISIBLE);
                    visitRecord.setEducationAdvocacyChecked(true);
                } else {
                    editTextAdvocacy.setVisibility(View.GONE);
                    visitRecord.setEducationAdvocacyChecked(false);
                }
            }
        });
        checkBoxRef.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextRef.setVisibility(View.VISIBLE);
                    visitRecord.setEducationRefChecked(true);
                } else {
                    editTextRef.setVisibility(View.GONE);
                    visitRecord.setEducationRefChecked(false);
                }
            }
        });
        checkBoxEncouragement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextEncouragement.setVisibility(View.VISIBLE);
                    visitRecord.setEducationEncouragementChecked(true);
                } else {
                    editTextEncouragement.setVisibility(View.GONE);
                    visitRecord.setEducationEncouragementChecked(false);
                }
            }
        });
    }

    public EditText getEditTextAdvice() {
        return editTextAdvice;
    }

    public EditText getEditTextAdvocacy() {
        return editTextAdvocacy;
    }

    public EditText getEditTextRef() {
        return editTextRef;
    }

    public EditText getEditTextEncouragement() {
        return editTextEncouragement;
    }

    public EditText getEditTextEducationOutcome() {
        return editTextEducationOutcome;
    }
}