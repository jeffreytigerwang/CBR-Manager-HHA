package com.example.cbr.fragments.newreferral;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;


import com.example.cbr.R;
import com.example.cbr.adapters.questioninfoadapters.QuestionsFragmentPagerAdapter;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.CheckBoxViewContainer;
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

        return binding.getRoot();
    }

    private void setupViewPager() {
        ViewPager2 viewPager2 = binding.questionsPageViewPager;
        viewPager2.setAdapter(new QuestionsFragmentPagerAdapter(getActivity(), generateViewPagerList()));
    }

    private List<QuestionsFragmentPagerAdapter.ViewPagerContainer> generateViewPagerList() {
        List<QuestionsFragmentPagerAdapter.ViewPagerContainer> viewPagerContainerList = new ArrayList<>();

        // main page
        List<QuestionDataContainer> firstPage = new ArrayList<>();
        firstPage.add(new SingleTextViewContainer(getString(R.string.service_requirements)));
        firstPage.add(new CheckBoxViewContainer(getString(R.string.physiotherapy)));
        firstPage.add(new CheckBoxViewContainer(getString(R.string.prosthetic)));
        firstPage.add(new CheckBoxViewContainer(getString(R.string.orthotic)));
        firstPage.add(new CheckBoxViewContainer(getString(R.string.wheelchair)));
        firstPage.add(new CheckBoxViewContainer(getString(R.string.other)));

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(firstPage, true));

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
