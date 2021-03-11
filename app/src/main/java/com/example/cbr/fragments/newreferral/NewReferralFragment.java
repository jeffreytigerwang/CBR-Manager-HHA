package com.example.cbr.fragments.newreferral;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;


import com.example.cbr.adapters.questioninfoadapters.QuestionsFragmentPagerAdapter;
import com.example.cbr.databinding.FragmentQuestionspageBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.models.ClientInfo;

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
//        binding.questionsPageViewPager.setAdapter(new QuestionsFragmentPagerAdapter(getActivity()));

        return binding.getRoot();
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
