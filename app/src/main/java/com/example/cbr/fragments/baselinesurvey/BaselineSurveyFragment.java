package com.example.cbr.fragments.baselinesurvey;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cbr.adapters.questioninfoadapters.QuestionsFragmentPagerAdapter;
import com.example.cbr.databinding.FragmentBaselinesurveyBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class BaselineSurveyFragment extends BaseFragment implements BaselineSurveyContract.View {
    private FragmentBaselinesurveyBinding binding;
    private BaselineSurveyContract.Presenter presenter;

    private QuestionsFragmentPagerAdapter baselineSurveyFragmentAdapter;
    private ArrayList<QuestionsFragmentPagerAdapter.ViewPagerContainer> viewPagerContainerList = new ArrayList<>();

    private enum PAGES {
        MAIN,
        HEALTH,
        EDUCATION,
        SOCIAL,
        LIVELIHOOD,
        FOOD_AND_NUTRITION,
        EMPOWERMENT,
        SHELTER_AND_CARE
    }
    
    private static final String BASELINE_SURVEY_PAGE_BUNDLE = "baselineSurveyPageBundle";
    
    // Instantiate BaselineSurvey class here
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new BaselineSurveyPresenter(this, getContext()));
        binding = FragmentBaselinesurveyBinding.inflate(inflater, container, false);
        
        setupViewPager();
        setupButtons();
        
        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        
        return binding.getRoot();
    }
    
    private void setupViewPager() {
        generateViewPagerList();
        final ViewPager2 viewPager2 = binding.baselineSurveyPageViewPager;
        baselineSurveyFragmentAdapter = new QuestionsFragmentPagerAdapter(this, viewPagerContainerList);
        viewPager2.setAdapter(baselineSurveyFragmentAdapter);
        viewPager2.setOffscreenPageLimit(10);
        
        updateDisplayInfo(PAGES.MAIN.ordinal());
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateDisplayInfo(position);
            }
        });
    }

    private void generateViewPagerList() {
        generateMainPage();
        generateEducation();
        generateSocial();
        generateLivelihood();
        generateFoodAndNutrition();
        generateEmpowerment();
        generateShelterAndCare();
    }

    private void generateMainPage() {

    }

    private void generateEducation() {

    }

    private void generateSocial() {

    }

    private void generateLivelihood() {

    }

    private void generateFoodAndNutrition() {

    }

    private void generateEmpowerment() {

    }

    private void generateShelterAndCare() {

    }

    private void setupButtons() {
    }

    private void updateDisplayInfo(int currentPage) {

    }

    @Override
    public void setPresenter(BaselineSurveyContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public static String getFragmentTag() {
        return BaselineSurveyFragment.class.getSimpleName();
    }
}
