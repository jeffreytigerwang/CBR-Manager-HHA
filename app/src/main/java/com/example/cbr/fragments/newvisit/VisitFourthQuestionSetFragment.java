package com.example.cbr.fragments.newvisit;

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
import com.example.cbr.databinding.FragmentVisitFourthQuestionSetBinding;
import com.example.cbr.models.Constants;
import com.example.cbr.models.VisitCheckContainer;

import static com.example.cbr.models.Constants.CANCELLED;
import static com.example.cbr.models.Constants.CONCLUDED;
import static com.example.cbr.models.Constants.IS_SOCIAL_ADVICE_CHECKED;
import static com.example.cbr.models.Constants.IS_SOCIAL_ADVOCACY_CHECKED;
import static com.example.cbr.models.Constants.IS_SOCIAL_ENCOURAGEMENT_CHECKED;
import static com.example.cbr.models.Constants.IS_SOCIAL_REF_CHECKED;
import static com.example.cbr.models.Constants.ONGOING;
import static com.example.cbr.models.Constants.SOCIAL_ADVICE_DESC;
import static com.example.cbr.models.Constants.SOCIAL_ADVOCACY_DESC;
import static com.example.cbr.models.Constants.SOCIAL_ENCOURAGEMENT_DESC;
import static com.example.cbr.models.Constants.SOCIAL_GOAL_STATUS;
import static com.example.cbr.models.Constants.SOCIAL_OUTCOME_DESC;
import static com.example.cbr.models.Constants.SOCIAL_REF_DESC;

public class VisitFourthQuestionSetFragment extends Fragment {

    private FragmentVisitFourthQuestionSetBinding binding;

    private final VisitCheckContainer visitCheckContainer;
    private final Context context;

    private EditText editTextAdvice;
    private EditText editTextAdvocacy;
    private EditText editTextRef;
    private EditText editTextEncouragement;
    private EditText editTextSocialOutcome;

    private RadioGroup goalStatus;
    private CheckBox checkBoxAdvice;
    private CheckBox checkBoxAdvocacy;
    private CheckBox checkBoxRef;
    private CheckBox checkBoxEncouragement;
    private TextView question16;

    public VisitFourthQuestionSetFragment(VisitCheckContainer visitCheckContainer, Context context) {
        this.visitCheckContainer = visitCheckContainer;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVisitFourthQuestionSetBinding.inflate(inflater, container, false);

        preLoadViews();

        setupCheckBoxes();
        setupRadioGroup();

        return binding.getRoot();
    }

    private void preLoadViews() {
        findViews();

        SharedPreferences sharedPref = context.getSharedPreferences(Constants.QUESTION_SET_4_PREF_NAME, Context.MODE_PRIVATE);

        boolean isAdviceChecked = sharedPref.getBoolean(IS_SOCIAL_ADVICE_CHECKED, false);
        boolean isAdvocacyChecked = sharedPref.getBoolean(IS_SOCIAL_ADVOCACY_CHECKED, false);
        boolean isRefChecked = sharedPref.getBoolean(IS_SOCIAL_REF_CHECKED, false);
        boolean isEncouragementChecked = sharedPref.getBoolean(IS_SOCIAL_ENCOURAGEMENT_CHECKED, false);
        String adviceDesc = sharedPref.getString(SOCIAL_ADVICE_DESC, "");
        String advocacyDesc = sharedPref.getString(SOCIAL_ADVOCACY_DESC, "");
        String refDesc = sharedPref.getString(SOCIAL_REF_DESC, "");
        String encouragementDesc = sharedPref.getString(SOCIAL_ENCOURAGEMENT_DESC, "");
        String educationOutcomeDesc = sharedPref.getString(SOCIAL_OUTCOME_DESC, "");
        String goalStatus = sharedPref.getString(SOCIAL_GOAL_STATUS, "");

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
        editTextSocialOutcome.setText(educationOutcomeDesc);

        if (goalStatus.equalsIgnoreCase(CANCELLED)) {
            this.goalStatus.check(R.id.newVisit_socialCancelledRadioButton);
        } else if (goalStatus.equalsIgnoreCase(ONGOING)) {
            this.goalStatus.check(R.id.newVisit_socialOngoingRadioButton);
        } else if (goalStatus.equalsIgnoreCase(CONCLUDED)) {
            this.goalStatus.check(R.id.newVisit_socialConcludedRadioButton);
            question16.setVisibility(View.VISIBLE);
            editTextSocialOutcome.setVisibility(View.VISIBLE);
        }
    }

    private void loadEditTextVisibility(boolean isChecked, EditText editText) {
        if (isChecked) {
            editText.setVisibility(View.VISIBLE);
        }
    }

    private void findViews() {
        editTextAdvice = binding.newVisitSocialAdviceEditText;
        editTextAdvocacy = binding.newVisitSocialAdvocacyEditText;
        editTextRef = binding.newVisitSocialRefEditText;
        editTextEncouragement = binding.newVisitSocialEncouragementEditText;
        editTextSocialOutcome = binding.newVisitSocialOutcomeEditText;

        goalStatus = binding.newVisitSocialGoalStatusRadioGroup;

        checkBoxAdvice = binding.newVisitSocialAdviceCheckBox;
        checkBoxAdvocacy = binding.newVisitSocialAdvocacyCheckBox;
        checkBoxRef = binding.newVisitSocialRefCheckBox;
        checkBoxEncouragement = binding.newVisitSocialEncouragementCheckBox;

        question16 = binding.newVisitQ16TextView;
    }

    private void setupRadioGroup() {

        goalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.newVisit_socialConcludedRadioButton) {
                    visitCheckContainer.setSocialGoalStatus(CONCLUDED);
                    question16.setVisibility(View.VISIBLE);
                    editTextSocialOutcome.setVisibility(View.VISIBLE);
                } else {
                    question16.setVisibility(View.GONE);
                    editTextSocialOutcome.setVisibility(View.GONE);
                }
                if (checkedId == R.id.newVisit_socialCancelledRadioButton) {
                    visitCheckContainer.setSocialGoalStatus(CANCELLED);
                }
                if (checkedId == R.id.newVisit_socialOngoingRadioButton) {
                    visitCheckContainer.setSocialGoalStatus(ONGOING);
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
                    visitCheckContainer.setSocialAdviceChecked(true);
                } else {
                    editTextAdvice.setVisibility(View.GONE);
                    visitCheckContainer.setSocialAdviceChecked(false);
                }
            }
        });
        checkBoxAdvocacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextAdvocacy.setVisibility(View.VISIBLE);
                    visitCheckContainer.setSocialAdvocacyChecked(true);
                } else {
                    editTextAdvocacy.setVisibility(View.GONE);
                    visitCheckContainer.setSocialAdvocacyChecked(false);
                }
            }
        });
        checkBoxRef.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextRef.setVisibility(View.VISIBLE);
                    visitCheckContainer.setSocialRefChecked(true);
                } else {
                    editTextRef.setVisibility(View.GONE);
                    visitCheckContainer.setSocialRefChecked(false);
                }
            }
        });
        checkBoxEncouragement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextEncouragement.setVisibility(View.VISIBLE);
                    visitCheckContainer.setSocialEncouragementChecked(true);
                } else {
                    editTextEncouragement.setVisibility(View.GONE);
                    visitCheckContainer.setSocialEncouragementChecked(false);
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

    public EditText getEditTextSocialOutcome() {
        return editTextSocialOutcome;
    }
}