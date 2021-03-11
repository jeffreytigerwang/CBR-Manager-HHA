package com.example.cbr.fragments.newreferral;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;


import com.example.cbr.R;
import com.example.cbr.adapters.questioninfoadapters.QuestionsFragmentPagerAdapter;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.CheckBoxViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.EditTextViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.SingleTextViewContainer;
import com.example.cbr.databinding.FragmentQuestionspageBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.models.ClientInfo;

import java.util.ArrayList;
import java.util.List;

public class NewReferralFragment extends BaseFragment implements NewReferralContract.View {

    private FragmentQuestionspageBinding binding;
    private NewReferralContract.Presenter clientListPresenter;

    private ClientInfo clientInfo;

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
        ViewPager2 viewPager2 = binding.questionsPageViewPager;
        viewPager2.setAdapter(new QuestionsFragmentPagerAdapter(getActivity(), generateViewPagerList()));
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

    private ArrayList<QuestionsFragmentPagerAdapter.ViewPagerContainer> generateViewPagerList() {
        ArrayList<QuestionsFragmentPagerAdapter.ViewPagerContainer> viewPagerContainerList = new ArrayList<>();

        // main page
        ArrayList<QuestionDataContainer> mainPageList = new ArrayList<>();
        mainPageList.add(new SingleTextViewContainer(getString(R.string.service_requirements), 20));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.physiotherapy)));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.prosthetic)));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.orthotic)));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.wheelchair)));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.other)));

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(mainPageList, true));

        // physiotherapy
        ArrayList<QuestionDataContainer> physioTherapyList = new ArrayList<>();
        physioTherapyList.add(new SingleTextViewContainer(getString(R.string.patient_conditions), 20));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.amputee)));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.polio)));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.spinal_cord_injury)));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.cerebral_palsy)));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.spina_bifida)));        mainPageList.add(new CheckBoxViewContainer(getString(R.string.physiotherapy)));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.hydrocephalus)));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.visual_impairment)));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.hearing_impairment)));
        physioTherapyList.add(new CheckBoxViewContainer(getString(R.string.other)));
        physioTherapyList.add(new EditTextViewContainer(getString(R.string.other_option), getString(R.string.other), InputType.TYPE_CLASS_TEXT));

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(physioTherapyList, true));

        return viewPagerContainerList;
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
