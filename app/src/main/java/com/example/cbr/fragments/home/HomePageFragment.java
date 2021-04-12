package com.example.cbr.fragments.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cbr.R;
import com.example.cbr.databinding.FragmentHomePageBinding;
import com.example.cbr.fragments.DashboardPageFragment;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.localdb.DBHelper;
import com.example.cbr.models.ClientDisability;
import com.example.cbr.models.ClientEducationAspect;
import com.example.cbr.models.ClientHealthAspect;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientSocialAspect;
import com.example.cbr.models.ReferralInfo;
import com.example.cbr.models.VisitEducationQuestionSetData;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.models.VisitHealthQuestionSetData;
import com.example.cbr.models.VisitSocialQuestionSetData;

import java.util.ArrayList;
import java.util.List;


public class HomePageFragment extends BaseFragment implements HomePageContract.View, View.OnClickListener{
    private HomePageContract.Presenter homePagePresenter;
    private FragmentHomePageBinding binding;
    private HomePageFragmentInterface homePageFragmentInterface;
    private DashboardPageFragment.DashboardFragmentInterface dashboardFragmentInterface;
    private DBHelper dbHelper;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            homePageFragmentInterface = (HomePageFragment.HomePageFragmentInterface) context;
            dashboardFragmentInterface = (DashboardPageFragment.DashboardFragmentInterface) context;
        } catch (ClassCastException e) {
            Log.e(getFragmentTag(), "Activity should implement HomePageFragmentInterface, " +
                    "TempHomeFragmentInterface.");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new HomePagePresenter(this));

        binding = FragmentHomePageBinding.inflate(inflater, container, false);

        binding.cardViewAllClients.setOnClickListener(this);
        binding.cardViewDashboard.setOnClickListener(this);
        binding.cardViewNewClient.setOnClickListener(this);

        dbHelper = new DBHelper(getContext());

        View view = binding.getRoot();
        return view;
    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        homePagePresenter = presenter;
    }


    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }

    public static String getFragmentTag() {
        return HomePageFragment.class.getSimpleName();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardViewAllClients:
                homePageFragmentInterface.swapToClientList();
                break;

            case R.id.cardViewDashboard:
                homePageFragmentInterface.swapToDashboard();
                break;

            case R.id.cardViewNewClient:
                dashboardFragmentInterface.swapToNewClient();
                break;

            case R.id.cardViewSync:
                Toast.makeText(getActivity(),getString(R.string.syncing_toast), Toast.LENGTH_LONG).show();
                syncData();
            default:
                break;
        }
    }

    //Syncing only supports when an entry has been added to database and not if entries where changed
    private void syncData() {
        uploadLocalChanges();





    }

    private void uploadLocalChanges() {
        List<ClientInfo> clients = dbHelper.getChangedAllClients();
        List<ClientDisability> disabilities = dbHelper.getChangedAllDisability();
        List<ReferralInfo> referrals = dbHelper.getChangedReferrals();
        List<ClientEducationAspect> educationAspects = dbHelper.getChangedAllClientEducationAspects();
        List<ClientSocialAspect> socialAspects = dbHelper.getAllClientSocialAspects();
        List<ClientHealthAspect> healthAspects = dbHelper.getChangedAllClientHealthAspects();
        List<VisitGeneralQuestionSetData> visitGeneralQuestionSetDataList = dbHelper.getChangedAllVisitGeneralQuestions();
        List<VisitHealthQuestionSetData> visitHealthQuestionSetDataList = dbHelper.getChangedAllVisitHealthQuestions();
        List<VisitEducationQuestionSetData> visitEducationQuestionSetDataList = dbHelper.getChangedAllVisitEducationQuestions();
        List<VisitSocialQuestionSetData> visitSocialQuestionSetDataList = dbHelper.getChangedAllVisitSocialQuestions();

        for(ClientInfo client : clients) {
            homePagePresenter.createClientInfo(client);
        }

        for(ClientDisability disability : disabilities) {
            homePagePresenter.createClientDisability(disability);
        }

        for(ReferralInfo referral : referrals) {
            homePagePresenter.createReferralInfo(referral);
        }

        for(ClientEducationAspect aspect : educationAspects) {
            homePagePresenter.createClientEducationAspect(aspect);
        }

        for(ClientSocialAspect aspect : socialAspects) {
            homePagePresenter.createClientSocialAspect(aspect);
        }

        for(ClientHealthAspect aspect : healthAspects) {
            homePagePresenter.createClientHealthAspect(aspect);
        }

        for(VisitEducationQuestionSetData progress :visitEducationQuestionSetDataList) {
            homePagePresenter.createVisitEducationQuestionSetData(progress);
        }

        for(VisitHealthQuestionSetData progress :visitHealthQuestionSetDataList) {
            homePagePresenter.createVisitHealthQuestionSetData(progress);
        }
        for(VisitGeneralQuestionSetData progress :visitGeneralQuestionSetDataList) {
            homePagePresenter.createVisitGeneralQuestionSetData(progress);
        }
        for(VisitSocialQuestionSetData progress :visitSocialQuestionSetDataList) {
            homePagePresenter.createVisitSocialQuestionSetData(progress);
        }
    }


    public interface HomePageFragmentInterface {
        void swapToClientList();
        void swapToDashboard();
    }
}