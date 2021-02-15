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
import com.example.cbr.databinding.FragmentVisitThirdQuestionSetBinding;
import com.example.cbr.models.Constants;
import com.example.cbr.models.VisitCheckContainer;

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

    private FragmentVisitThirdQuestionSetBinding binding;

    private final VisitCheckContainer visitCheckContainer;
    private final Context context;

    private EditText editTextAdvice;
    private EditText editTextAdvocacy;
    private EditText editTextRef;
    private EditText editTextEncouragement;
    private EditText editTextEducationOutcome;

    private CheckBox checkBoxEncouragement;
    private CheckBox checkBoxAdvice;
    private CheckBox checkBoxAdvocacy;
    private CheckBox checkBoxRef;
    private RadioGroup goalStatus;
    private TextView question13;

    public VisitThirdQuestionSetFragment(VisitCheckContainer visitCheckContainer, Context context) {
        this.visitCheckContainer = visitCheckContainer;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVisitThirdQuestionSetBinding.inflate(inflater, container, false);

        preLoadViews();

        setupCheckBoxes();
        setupRadioGroup();

        return binding.getRoot();
    }

    private void preLoadViews() {
        findViews();

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

    private void findViews() {
        editTextAdvice = binding.editTextEducationAdvice;
        editTextAdvocacy = binding.editTextEducationAdvocacy;
        editTextRef = binding.editTextEducationRef;
        editTextEncouragement = binding.editTextEducationEncouragement;
        editTextEducationOutcome = binding.editTextEducationOutcome;

        goalStatus = binding.radioGroupEducationGoalStatus;

        checkBoxAdvice = binding.checkBoxEducationAdvice;
        checkBoxAdvocacy = binding.checkBoxEducationAdvocacy;
        checkBoxRef = binding.checkBoxEducationRef;
        checkBoxEncouragement = binding.checkBoxEducationEncouragement;

        question13 = binding.textViewQ13;
    }

    private void setupRadioGroup() {
        goalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radioButtonEducationConcluded) {
                    visitCheckContainer.setEducationGoalStatus(CONCLUDED);
                    question13.setVisibility(View.VISIBLE);
                    editTextEducationOutcome.setVisibility(View.VISIBLE);
                } else {
                    question13.setVisibility(View.GONE);
                    editTextEducationOutcome.setVisibility(View.GONE);
                }
                if (checkedId == R.id.radioButtonEducationCancelled) {
                    visitCheckContainer.setEducationGoalStatus(CANCELLED);
                }
                if (checkedId == R.id.radioButtonEducationOngoing) {
                    visitCheckContainer.setEducationGoalStatus(ONGOING);
                }
            }
        });
    }

    private void setupCheckBoxes() {
        checkBoxAdvice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextAdvice.setVisibility(View.VISIBLE);
                    visitCheckContainer.setEducationAdviceChecked(true);
                } else {
                    editTextAdvice.setVisibility(View.GONE);
                    visitCheckContainer.setEducationAdviceChecked(false);
                }
            }
        });
        checkBoxAdvocacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextAdvocacy.setVisibility(View.VISIBLE);
                    visitCheckContainer.setEducationAdvocacyChecked(true);
                } else {
                    editTextAdvocacy.setVisibility(View.GONE);
                    visitCheckContainer.setEducationAdvocacyChecked(false);
                }
            }
        });
        checkBoxRef.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextRef.setVisibility(View.VISIBLE);
                    visitCheckContainer.setEducationRefChecked(true);
                } else {
                    editTextRef.setVisibility(View.GONE);
                    visitCheckContainer.setEducationRefChecked(false);
                }
            }
        });
        checkBoxEncouragement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextEncouragement.setVisibility(View.VISIBLE);
                    visitCheckContainer.setEducationEncouragementChecked(true);
                } else {
                    editTextEncouragement.setVisibility(View.GONE);
                    visitCheckContainer.setEducationEncouragementChecked(false);
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