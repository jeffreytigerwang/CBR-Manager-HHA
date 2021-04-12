package com.example.cbr.fragments.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HomePageFragment extends BaseFragment implements HomePageContract.View, View.OnClickListener{
    private HomePageContract.Presenter homePagePresenter;
    private FragmentHomePageBinding binding;
    private HomePageFragmentInterface homePageFragmentInterface;
    private DashboardPageFragment.DashboardFragmentInterface dashboardFragmentInterface;
    private boolean setSwitch = true;
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
        binding.cardViewSync.setOnClickListener(this);

        dbHelper = new DBHelper(getContext());

        setHasOptionsMenu(true);

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
    //For future: add column in remote database and query from API to get changed row rather than
        // iterating through all rows

    private void syncData() {
        uploadLocalChanges();
        downloadRemoteChanges();
    }

    private void downloadRemoteChanges() {
        List<ClientInfo> clientsLocal = dbHelper.getAllClients();
        List<ClientDisability> disabilitiesLocal = dbHelper.getAllDisability();
        List<ReferralInfo> referralsLocal = dbHelper.getAllReferrals();
        List<ClientEducationAspect> educationAspectsLocal = dbHelper.getAllClientEducationAspects();
        List<ClientSocialAspect> socialAspectsLocal = dbHelper.getAllClientSocialAspects();
        List<ClientHealthAspect> healthAspectsLocal = dbHelper.getAllClientHealthAspects();
        List<VisitGeneralQuestionSetData> visitGeneralQuestionSetDataListLocal = dbHelper.getAllVisitGeneralQuestions();
        List<VisitHealthQuestionSetData> visitHealthQuestionSetDataListLocal = dbHelper.getAllVisitHealthQuestions();
        List<VisitEducationQuestionSetData> visitEducationQuestionSetDataListLocal = dbHelper.getAllVisitEducationQuestions();
        List<VisitSocialQuestionSetData> visitSocialQuestionSetDataListLocal = dbHelper.getAllVisitSocialQuestions();

        List<ClientInfo> clientsRemote = new ArrayList<>();
        List<ClientDisability> disabilitiesRemote = new ArrayList<>();
        List<ReferralInfo> referralsRemote = new ArrayList<>();
        List<ClientEducationAspect> educationAspectsRemote = new ArrayList<>();
        List<ClientSocialAspect> socialAspectsRemote = new ArrayList<>();
        List<ClientHealthAspect> healthAspectsRemote = new ArrayList<>();
        List<VisitGeneralQuestionSetData> visitGeneralQuestionSetDataListRemote = new ArrayList<>();
        List<VisitHealthQuestionSetData> visitHealthQuestionSetDataListRemote = new ArrayList<>();
        List<VisitEducationQuestionSetData> visitEducationQuestionSetDataListRemote = new ArrayList<>();
        List<VisitSocialQuestionSetData> visitSocialQuestionSetDataListRemote = new ArrayList<>();

        try {
            clientsRemote = homePagePresenter.getAllClients();
            disabilitiesRemote = homePagePresenter.getAllClientDisabilities();
            referralsRemote = homePagePresenter.getAllReferrals();
            educationAspectsRemote = homePagePresenter.getAllClientEducationAspect();
            socialAspectsRemote = homePagePresenter.getAllClientSocialAspect();
            healthAspectsRemote = homePagePresenter.getAllClientHealthAspect();
            visitGeneralQuestionSetDataListRemote = homePagePresenter.getAllVisitGeneralQuestions();
            visitHealthQuestionSetDataListRemote = homePagePresenter.getAllVisitHealthQuestions();
            visitEducationQuestionSetDataListRemote = homePagePresenter.getAllVisitEducationQuestions();
            visitSocialQuestionSetDataListRemote = homePagePresenter.getAllVisitSocialQuestions();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(ClientInfo client : clientsRemote) {
            if(!clientsLocal.contains(client)) {
                dbHelper.addClient(client, false);
            }
        }

        for(ClientDisability disability : disabilitiesRemote) {
            if(!disabilitiesLocal.contains(disability)) {
                dbHelper.addDisability(disability, false);
            }
        }

        for(ReferralInfo referral : referralsRemote) {
            if(!referralsLocal.contains(referral)) {
                dbHelper.addReferral(referral, false);
            }
        }

        for(ClientEducationAspect aspect : educationAspectsRemote) {
            if(!educationAspectsLocal.contains(aspect)) {
                dbHelper.addEducationAspect(aspect, false);
            }
        }

        for(ClientSocialAspect aspect : socialAspectsRemote) {
            if(!socialAspectsLocal.contains(aspect)) {
                dbHelper.addSocialAspect(aspect, false);
            }
        }

        for(ClientHealthAspect aspect : healthAspectsRemote) {
            if(!healthAspectsLocal.contains(aspect)) {
                dbHelper.addHealthAspect(aspect, false);
            }
        }

        for(VisitEducationQuestionSetData progress :visitEducationQuestionSetDataListRemote) {
            if(!visitEducationQuestionSetDataListLocal.contains(progress)) {
                dbHelper.addEducationProgress(progress, false);
            }
        }

        for(VisitHealthQuestionSetData progress :visitHealthQuestionSetDataListRemote) {
            if(!visitHealthQuestionSetDataListLocal.contains(progress)) {
                dbHelper.addHealthProgress(progress, false);
            }
        }
        for(VisitGeneralQuestionSetData progress :visitGeneralQuestionSetDataListRemote) {
            if(!visitGeneralQuestionSetDataListLocal.contains(progress)) {
                dbHelper.addVisit(progress, false);
            }
        }
        for(VisitSocialQuestionSetData progress :visitSocialQuestionSetDataListRemote) {
            if(!visitSocialQuestionSetDataListLocal.contains(progress)) {
                dbHelper.addSocialProgress(progress, false);
            }
        }
    }

    private void uploadLocalChanges() {
        List<ClientInfo> clients = dbHelper.getChangedAllClients();
        List<ClientDisability> disabilities = dbHelper.getChangedAllDisability();
        List<ReferralInfo> referrals = dbHelper.getChangedReferrals();
        List<ClientEducationAspect> educationAspects = dbHelper.getChangedAllClientEducationAspects();
        List<ClientSocialAspect> socialAspects = dbHelper.getChangedAllClientSocialAspects();
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



    public void onPrepareOptionsMenu(Menu menu) {
        if(setSwitch){
            menu.findItem(R.id.clientListSearch).setVisible(false);
            menu.findItem(R.id.clientListSort).setVisible(false);
            setSwitch = false;
        }
        super.onPrepareOptionsMenu(menu);
    }


    public interface HomePageFragmentInterface {
        void swapToClientList();
        void swapToDashboard();
    }
}