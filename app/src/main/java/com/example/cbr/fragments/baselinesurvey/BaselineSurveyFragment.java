package com.example.cbr.fragments.baselinesurvey;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cbr.R;
import com.example.cbr.adapters.questioninfoadapters.QuestionsFragmentPagerAdapter;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.CheckBoxViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.EditTextViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.HeaderViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.RadioGroupViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.SpinnerViewContainer;
import com.example.cbr.databinding.FragmentBaselinesurveyBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;
import com.example.cbr.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        generateHealth();
        generateEducation();
        generateSocial();
        generateLivelihood();
        generateFoodAndNutrition();
        generateEmpowerment();
        generateShelterAndCare();
    }

    private void generateMainPage() {
        final ArrayList<QuestionDataContainer> mainPageList = new ArrayList<>();
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.are_you_over_sixteen)));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.are_you_under_eighteen)));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof CheckBoxViewContainer) {
                    String questionText = ((CheckBoxViewContainer) questionDataContainer).getQuestionText();
                    boolean isChecked = ((CheckBoxViewContainer) questionDataContainer).isChecked();

                    if (questionText.equals(getString(R.string.are_you_over_sixteen))) {
                        setPageActive(PAGES.LIVELIHOOD.ordinal(), isChecked);
                    } else if (questionText.equals(getString(R.string.are_you_under_eighteen))) {
                        setPageActive(PAGES.EDUCATION.ordinal(), isChecked);
                    }
                }
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(mainPageList, true, onViewPagerChangedListener));
    }

    private void generateHealth() {

    }

    private void generateEducation() {
        final ArrayList<QuestionDataContainer> educationList = new ArrayList<>();
        educationList.add(new HeaderViewContainer(getString(R.string.education)));

        List<RadioGroupViewContainer.RadioGroupListItem> currentlyGoingToSchoolOptions = new ArrayList<>();
        currentlyGoingToSchoolOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        currentlyGoingToSchoolOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        educationList.add(new RadioGroupViewContainer(getString(R.string.do_you_go_to_school), true, currentlyGoingToSchoolOptions));

        educationList.add(new EditTextViewContainer(getString(R.string.if_yes_what_grade), Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, null, getString(R.string.grade), InputType.TYPE_CLASS_NUMBER));

        List<String> reasonsForNotGoingToSchoolOptions = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.reasons_for_not_going_to_school_array))
        );
        educationList.add(new SpinnerViewContainer(getString(R.string.if_no_why_do_you_not_go_to_school), Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, reasonsForNotGoingToSchoolOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> hasBeenToSchoolOptions = new ArrayList<>();
        hasBeenToSchoolOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        hasBeenToSchoolOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        educationList.add(new RadioGroupViewContainer(getString(R.string.if_no_have_you_ever_been_to_school_before), true, hasBeenToSchoolOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> wantToGoToSchoolOptions = new ArrayList<>();
        wantToGoToSchoolOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        wantToGoToSchoolOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        educationList.add(new RadioGroupViewContainer(getString(R.string.if_no_do_you_want_to_go_to_school), true, wantToGoToSchoolOptions));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {

            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(educationList, true, onViewPagerChangedListener));
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
        binding.baselineSurveyPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.baselineSurveyPageViewPager.getCurrentItem() == baselineSurveyFragmentAdapter.getItemCount() - 1) {
                    getActivity().getSupportFragmentManager().popBackStack();
                    // Database API call
                }
                binding.baselineSurveyPageViewPager.setCurrentItem(binding.baselineSurveyPageViewPager.getCurrentItem() + 1);
            }
        });

        binding.baselineSurveyPageNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.baselineSurveyPageViewPager.getCurrentItem() == PAGES.MAIN.ordinal()) {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
                binding.baselineSurveyPageViewPager.setCurrentItem(binding.baselineSurveyPageViewPager.getCurrentItem() - 1);
            }
        });

    }

    private void updateDisplayInfo(int currentPage) {
        int numTotalPage = baselineSurveyFragmentAdapter.getItemCount();
        binding.baselineSurveyPagePageNumberText.setText(getString(R.string.viewpager_page_number, currentPage + 1, numTotalPage));

        if (currentPage == PAGES.MAIN.ordinal()) {
            binding.baselineSurveyPageNegativeButton.setText(R.string.cancel);
        } else {
            binding.baselineSurveyPageNegativeButton.setText(R.string.back);
        }

        if (currentPage == numTotalPage - 1) {
            binding.baselineSurveyPositiveButton.setText(R.string.record);
        } else {
            binding.baselineSurveyPositiveButton.setText(R.string.next);
        }
    }

    private void setPageActive(int page, boolean isActive) {
        baselineSurveyFragmentAdapter.setPageActive(page, isActive);
        updateDisplayInfo(binding.baselineSurveyPageViewPager.getCurrentItem());
    }

    @Override
    public void setPresenter(BaselineSurveyContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public static String getFragmentTag() {
        return BaselineSurveyFragment.class.getSimpleName();
    }
}
