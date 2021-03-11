package com.example.cbr.fragments.newreferral;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;


import com.example.cbr.R;
import com.example.cbr.adapters.questioninfoadapters.QuestionsFragmentPagerAdapter;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.CheckBoxViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.EditTextViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.RadioGroupViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.SingleTextViewContainer;
import com.example.cbr.databinding.FragmentQuestionspageBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ReferralInfo;

import java.util.ArrayList;
import java.util.List;

public class NewReferralFragment extends BaseFragment implements NewReferralContract.View {

    private FragmentQuestionspageBinding binding;
    private NewReferralContract.Presenter clientListPresenter;

    private ClientInfo clientInfo;
    ArrayList<QuestionsFragmentPagerAdapter.ViewPagerContainer> viewPagerContainerList = new ArrayList<>();
    ReferralInfo referralInfo = new ReferralInfo();

    private static final String NEW_REFERRAL_PAGE_BUNDLE = "newReferralPageBundle";

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new NewReferralPresenter(this));
        binding = FragmentQuestionspageBinding.inflate(inflater, container, false);

        clientInfo = (ClientInfo) getArguments().getSerializable(NEW_REFERRAL_PAGE_BUNDLE);

        setupViewPager();
        setupButtons();

        return binding.getRoot();
    }

    private void setupViewPager() {
        generateViewPagerList();
        ViewPager2 viewPager2 = binding.questionsPageViewPager;
        viewPager2.setAdapter(new QuestionsFragmentPagerAdapter(getActivity(), viewPagerContainerList));
    }

    private void setupButtons() {
        binding.questionsPagePositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.questionsPageViewPager.setCurrentItem(binding.questionsPageViewPager.getCurrentItem() + 1);
            }
        });

        binding.questionsPageNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.questionsPageViewPager.setCurrentItem(binding.questionsPageViewPager.getCurrentItem() - 1);
            }
        });
    }

    private void generateViewPagerList() {
        generateMainPage();
        generatePhysiotherapy();
        generateProsthetic();
        generateOrthotic();
        generateWheelchair();
    }

    private void generateMainPage() {
        final ArrayList<QuestionDataContainer> mainPageList = new ArrayList<>();
        mainPageList.add(new SingleTextViewContainer(getString(R.string.service_requirements_question), 20));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.physiotherapy)));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.prosthetic)));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.orthotic)));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.wheelchair)));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.other)));
        mainPageList.add(new EditTextViewContainer(getString(R.string.other_option), getString(R.string.other), InputType.TYPE_CLASS_TEXT));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged) {
                if (mainPageList.get(positionChanged) instanceof CheckBoxViewContainer) {
                    String serviceRequired = ((CheckBoxViewContainer) mainPageList.get(positionChanged)).getQuestionText();
                    boolean isChecked = ((CheckBoxViewContainer) mainPageList.get(positionChanged)).isChecked();

                    if (serviceRequired.equals(getString(R.string.physiotherapy))) {
                        referralInfo.setRequirePhysiotherapy(isChecked);
                    } else if (serviceRequired.equals(getString(R.string.prosthetic))) {
                        referralInfo.setRequireProsthetic(isChecked);
                    } else if (serviceRequired.equals(getString(R.string.orthotic))) {
                        referralInfo.setRequireOrthotic(isChecked);
                    } else if (serviceRequired.equals(getString(R.string.wheelchair))) {
                        referralInfo.setRequireWheelchair(isChecked);
                    } else if (serviceRequired.equals(getString(R.string.other))) {
                        referralInfo.setRequireOther(isChecked);
                    }
                }

                if (mainPageList.get(positionChanged) instanceof EditTextViewContainer) {
                    String userInput = ((EditTextViewContainer) mainPageList.get(positionChanged)).getUserInput();
                    String option = ((EditTextViewContainer) mainPageList.get(positionChanged)).getQuestionText();

                    if (option.equals(getString(R.string.other_option))) {
                        referralInfo.setOtherDescription(userInput);
                    }
                }
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(mainPageList, true, onViewPagerChangedListener));
    }

    private void generatePhysiotherapy() {
        ArrayList<QuestionDataContainer> physioTherapyList = new ArrayList<>();
        physioTherapyList.add(new SingleTextViewContainer(getString(R.string.patient_conditions_question), 20));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.amputee)));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.polio)));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.spinal_cord_injury)));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.cerebral_palsy)));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.spina_bifida)));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.hydrocephalus)));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.visual_impairment)));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.hearing_impairment)));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.other)));
        physioTherapyList.add(new EditTextViewContainer(getString(R.string.other_option), getString(R.string.other), InputType.TYPE_CLASS_TEXT));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged) {
                Log.d("testing", "physio");
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(physioTherapyList, true, onViewPagerChangedListener));
    }

    private void generateProsthetic() {
        ArrayList<QuestionDataContainer> prostheticList = new ArrayList<>();

        List<RadioGroupViewContainer.RadioGroupListItem> prostheticOptions = new ArrayList<>();
        prostheticOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.above_knee_question), false, View.generateViewId()));
        prostheticOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.below_knee_question), false, View.generateViewId()));
        prostheticList.add(new RadioGroupViewContainer(getString(R.string.prosthetic_question), true, prostheticOptions));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged) {

            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(prostheticList, true, onViewPagerChangedListener));
    }

    private void generateOrthotic() {
        ArrayList<QuestionDataContainer> orthoticList = new ArrayList<>();

        List<RadioGroupViewContainer.RadioGroupListItem> orthoticOptions = new ArrayList<>();
        orthoticOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.above_elbow_question), false, View.generateViewId()));
        orthoticOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.below_elbow_question), false, View.generateViewId()));
        orthoticList.add(new RadioGroupViewContainer(getString(R.string.orthotic_question), true, orthoticOptions));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged) {

            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(orthoticList, true, onViewPagerChangedListener));
    }

    private void generateWheelchair() {
        ArrayList<QuestionDataContainer> wheelchair = new ArrayList<>();

        List<RadioGroupViewContainer.RadioGroupListItem> basicIntermediate = new ArrayList<>();
        basicIntermediate.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        basicIntermediate.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        wheelchair.add(new RadioGroupViewContainer(getString(R.string.wheelchair_expertise_question), true, basicIntermediate));

        List<RadioGroupViewContainer.RadioGroupListItem> existingWheelchair = new ArrayList<>();
        existingWheelchair.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        existingWheelchair.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        wheelchair.add(new RadioGroupViewContainer(getString(R.string.existing_wheelchair_question), true, existingWheelchair));

        List<RadioGroupViewContainer.RadioGroupListItem> wheelchairRepair = new ArrayList<>();
        wheelchairRepair.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        wheelchairRepair.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        wheelchair.add(new RadioGroupViewContainer(getString(R.string.wheelchair_repair_question), true, wheelchairRepair));
        wheelchair.add(new SingleTextViewContainer(getString(R.string.wheelchair_centre), 15));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged) {

            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(wheelchair, true, onViewPagerChangedListener));
    }

    @Override
    public void setPresenter(NewReferralContract.Presenter presenter) {
        clientListPresenter = presenter;
    }


    public static NewReferralFragment newInstance(ClientInfo clientInfo) {
        NewReferralFragment newReferralFragment = new NewReferralFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(NEW_REFERRAL_PAGE_BUNDLE, clientInfo);
        newReferralFragment.setArguments(bundle);

        return newReferralFragment;
    }

    public static String getFragmentTag() {
        return NewReferralFragment.class.getSimpleName();
    }
}
