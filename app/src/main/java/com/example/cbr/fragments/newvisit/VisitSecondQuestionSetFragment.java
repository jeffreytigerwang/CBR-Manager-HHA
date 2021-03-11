package com.example.cbr.fragments.newvisit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cbr.R;
import com.example.cbr.databinding.FragmentVisitSecondQuestionSetBinding;
import com.example.cbr.models.ClientHealthAspect;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.VisitHealthQuestionSetData;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;
import com.example.cbr.util.Constants;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/*
* Fragment class is displayed if CBR is checked in question 1. and health is checked for question 2.
* */

public class VisitSecondQuestionSetFragment extends Fragment {

    private static final String LOG_TAG = "VisitSecondQuestionSetFragment";
    private FragmentVisitSecondQuestionSetBinding binding;

    private final VisitHealthQuestionSetData dataContainer;
    private final ClientInfo clientInfo;

    private EditText editTextWheelChair;
    private EditText editTextProsthetic;
    private EditText editTextOrthotic;
    private EditText editTextWR;
    private EditText editTextReferralToHC;
    private EditText editTextAdvice;
    private EditText editTextAdvocacy;
    private EditText editTextEncouragement;
    private EditText editTextHealthOutcome;

    private RadioGroup goalStatus;
    private CheckBox checkBoxWheelchair;
    private CheckBox checkBoxProsthetic;
    private CheckBox checkBoxOrthotic;
    private CheckBox checkBoxWR;
    private CheckBox checkBoxReferralToHC;
    private CheckBox checkBoxAdvice;
    private CheckBox checkBoxAdvocacy;
    private CheckBox checkBoxEncouragement;
    private TextView initialGoal;

    public VisitSecondQuestionSetFragment(VisitHealthQuestionSetData dataContainer,
                                          ClientInfo clientInfo) {
        this.dataContainer = dataContainer;
        this.clientInfo = clientInfo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVisitSecondQuestionSetBinding.inflate(inflater, container, false);

        preLoadViews();

        setupCheckBoxes();
        setupRadioGroup();

        return binding.getRoot();
    }

    private void preLoadViews() {
        findViews();

        checkBoxWheelchair.setChecked(dataContainer.isWheelChairChecked());
        toggleEditTextVisibility(dataContainer.isWheelChairChecked(), editTextWheelChair);
        checkBoxProsthetic.setChecked(dataContainer.isProstheticChecked());
        toggleEditTextVisibility(dataContainer.isProstheticChecked(), editTextProsthetic);
        checkBoxOrthotic.setChecked(dataContainer.isOrthoticChecked());
        toggleEditTextVisibility(dataContainer.isOrthoticChecked(), editTextOrthotic);
        checkBoxWR.setChecked(dataContainer.isWheelChairRepairChecked());
        toggleEditTextVisibility(dataContainer.isWheelChairRepairChecked(), editTextWR);
        checkBoxReferralToHC.setChecked(dataContainer.isReferralToHCChecked());
        toggleEditTextVisibility(dataContainer.isReferralToHCChecked(), editTextReferralToHC);
        checkBoxAdvice.setChecked(dataContainer.isReferralToHCChecked());
        toggleEditTextVisibility(dataContainer.isHealthAdviceChecked(), editTextAdvice);
        checkBoxAdvocacy.setChecked(dataContainer.isHealthAdvocacyChecked());
        toggleEditTextVisibility(dataContainer.isHealthAdvocacyChecked(), editTextAdvocacy);
        checkBoxEncouragement.setChecked(dataContainer.isHealthEncouragementChecked());
        toggleEditTextVisibility(dataContainer.isHealthEncouragementChecked(), editTextEncouragement);

        editTextWheelChair.setText(dataContainer.getWheelChairDesc());
        editTextProsthetic.setText(dataContainer.getProstheticDesc());
        editTextOrthotic.setText(dataContainer.getOrthoticDesc());
        editTextWR.setText(dataContainer.getWheelChairRepairDesc());
        editTextReferralToHC.setText(dataContainer.getReferralToHCDesc());
        editTextAdvice.setText(dataContainer.getHealthAdviceDesc());
        editTextAdvocacy.setText(dataContainer.getHealthAdvocacyDesc());
        editTextEncouragement.setText(dataContainer.getHealthEncouragementDesc());
        editTextHealthOutcome.setText(dataContainer.getHealthOutcomeDesc());

        String goal = clientInfo.getSetGoalForHealth();
        try {
            if (!goal.isEmpty()) {
                initialGoal.setText(goal);
            } else {
                initialGoal.setText(getResources().getString(R.string.na));
            }
        } catch (NullPointerException e) {
            initialGoal.setText(getResources().getString(R.string.na));
        }

        String goalStatus = dataContainer.getHealthGoalStatus();
        if (goalStatus.equalsIgnoreCase(Constants.CANCELLED)) {
            this.goalStatus.check(R.id.newVisit_healthCancelledRadioButton);
        } else if (goalStatus.equalsIgnoreCase(Constants.ONGOING)) {
            this.goalStatus.check(R.id.newVisit_healthOngoingRadioButton);
        } else if (goalStatus.equalsIgnoreCase(Constants.CONCLUDED)) {
            this.goalStatus.check(R.id.newVisit_healthConcludedRadioButton);
        }
    }

    private void findViews() {
        editTextWheelChair = binding.newVisitHealthWheelchairEditText;
        editTextProsthetic = binding.newVisitHealthProstheticEditText;
        editTextOrthotic = binding.newVisitHealthOrthoticEditText;
        editTextWR = binding.newVisitHealthWREditText;
        editTextReferralToHC = binding.newVisitHealthReferralToHCEditText;
        editTextAdvice = binding.newVisitHealthAdviceEditText;
        editTextAdvocacy = binding.newVisitHealthAdvocacyEditText;
        editTextEncouragement = binding.newVisitHealthEncouragementEditText;
        editTextHealthOutcome = binding.newVisitHealthOutcomeEditText;

        goalStatus = binding.newVisitHealthGoalStatusRadioGroup;

        checkBoxWheelchair = binding.newVisitHealthWheelchairCheckBox;
        checkBoxProsthetic = binding.newVisitHealthProstheticCheckBox;
        checkBoxOrthotic = binding.newVisitHealthOrthoticCheckBox;
        checkBoxWR = binding.newVisitHealthWRCheckBox;
        checkBoxReferralToHC = binding.newVisitHealthReferralToHCCheckBox;
        checkBoxAdvice = binding.newVisitHealthAdviceCheckBox;
        checkBoxAdvocacy = binding.newVisitHealthAdvocacyCheckBox;
        checkBoxEncouragement = binding.newVisitHealthEncouragementCheckBox;

        initialGoal = binding.newVisitHealthInitialGoalBoxTextView;
    }

    private void setupRadioGroup() {


        goalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.newVisit_healthConcludedRadioButton) {
                    dataContainer.setHealthGoalStatus(Constants.CONCLUDED);

                } else if (checkedId == R.id.newVisit_healthOngoingRadioButton) {
                    dataContainer.setHealthGoalStatus(Constants.ONGOING);

                } else if (checkedId == R.id.newVisit_educationCancelledRadioButton) {
                    dataContainer.setHealthGoalStatus(Constants.CANCELLED);
                }
            }
        });
    }


    private void setupCheckBoxes() {

        checkBoxWheelchair.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setWheelChairChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextWheelChair);
            }
        });
        checkBoxProsthetic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setProstheticChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextProsthetic);
            }
        });
        checkBoxOrthotic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setOrthoticChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextOrthotic);
            }
        });
        checkBoxWR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setWheelChairRepairChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextWR);
            }
        });
        checkBoxReferralToHC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setReferralToHCChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextReferralToHC);
            }
        });
        checkBoxAdvice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setHealthAdviceChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextAdvice);
            }
        });
        checkBoxAdvocacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setHealthAdvocacyChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextAdvocacy);
            }
        });
        checkBoxEncouragement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setHealthEncouragementChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextEncouragement);
            }
        });
    }

    private void toggleEditTextVisibility(boolean isVisible, EditText editText) {
        if (isVisible) {
            editText.setVisibility(View.VISIBLE);
        } else {
            editText.setVisibility(View.GONE);
        }
    }

    public EditText getEditTextWheelChair() {
        return editTextWheelChair;
    }

    public EditText getEditTextProsthetic() {
        return editTextProsthetic;
    }

    public EditText getEditTextOrthotic() {
        return editTextOrthotic;
    }

    public EditText getEditTextWR() {
        return editTextWR;
    }

    public EditText getEditTextReferralToHC() {
        return editTextReferralToHC;
    }

    public EditText getEditTextAdvice() {
        return editTextAdvice;
    }

    public EditText getEditTextAdvocacy() {
        return editTextAdvocacy;
    }

    public EditText getEditTextEncouragement() {
        return editTextEncouragement;
    }

    public EditText getEditTextHealthOutcome() {
        return editTextHealthOutcome;
    }
}