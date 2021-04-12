package com.example.cbr.fragments.baselinesurvey;

import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

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
import com.example.cbr.models.BaselineEducationSurveyData;
import com.example.cbr.models.BaselineHealthSurveyData;
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

    BaselineHealthSurveyData baselineHealthSurveyData = new BaselineHealthSurveyData();
    BaselineEducationSurveyData baselineEducationSurveyData = new BaselineEducationSurveyData();
    private int clientId;
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

                    setPageActive(PAGES.MAIN.ordinal(), true);
                    setPageActive(PAGES.HEALTH.ordinal(), true);
                    setPageActive(PAGES.SOCIAL.ordinal(), true);
                    setPageActive(PAGES.FOOD_AND_NUTRITION.ordinal(), true);
                    setPageActive(PAGES.EMPOWERMENT.ordinal(), true);
                    setPageActive(PAGES.SHELTER_AND_CARE.ordinal(), true);

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
        final ArrayList<QuestionDataContainer> healthList = new ArrayList<>();
        healthList.add(new HeaderViewContainer(getString(R.string.health)));

        List<String> rateGeneralHealthOptions = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.satisfactions_array))
        );
        healthList.add(new SpinnerViewContainer(getString(R.string.rate_your_general_health), Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, rateGeneralHealthOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> haveAccessToRehabilitationServicesOptions = new ArrayList<>();
        haveAccessToRehabilitationServicesOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        haveAccessToRehabilitationServicesOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        healthList.add(new RadioGroupViewContainer(getString(R.string.do_you_have_access_to_rehabilitation_services), true, haveAccessToRehabilitationServicesOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> needAccessToRehabilitationOptions = new ArrayList<>();
        needAccessToRehabilitationOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        needAccessToRehabilitationOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        healthList.add(new RadioGroupViewContainer(getString(R.string.do_you_need_access_to_rehabilitation_services), true, needAccessToRehabilitationOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> haveAssistiveDeviceOptions = new ArrayList<>();
        haveAssistiveDeviceOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        haveAssistiveDeviceOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        healthList.add(new RadioGroupViewContainer(getString(R.string.do_you_have_an_assistive_device), true, haveAssistiveDeviceOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> isAssistiveDeviceWorkingWellOptions = new ArrayList<>();
        isAssistiveDeviceWorkingWellOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        isAssistiveDeviceWorkingWellOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        healthList.add(new RadioGroupViewContainer(getString(R.string.is_your_assistive_device_working_well), true, isAssistiveDeviceWorkingWellOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> needAssistiveDeviceOptions = new ArrayList<>();
        needAssistiveDeviceOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        needAssistiveDeviceOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        healthList.add(new RadioGroupViewContainer(getString(R.string.do_you_need_an_assistive_device), true, needAssistiveDeviceOptions));

        List<String> assistiveDeviceOptions = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.assistive_devices_array))
        );
        healthList.add(new SpinnerViewContainer(getString(R.string.what_assistive_device_do_you_need), Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, assistiveDeviceOptions));

        List<String> rateSatisfactionOptions = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.satisfactions_array))
        );
        healthList.add(new SpinnerViewContainer(getString(R.string.are_you_satisfied_with_the_health_services_you_receive), Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, rateSatisfactionOptions));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof SpinnerViewContainer) {
                    String questionText = ((SpinnerViewContainer) questionDataContainer).getQuestionText();
                    String selectedItem = ((SpinnerViewContainer) questionDataContainer).getSelectedItem();

                    if (questionText.equals(getString(R.string.rate_your_general_health))) {
                        baselineHealthSurveyData.setRateGeneralHealth(selectedItem);
                    } else if (questionText.equals(getString(R.string.what_assistive_device_do_you_need))) {
                        baselineHealthSurveyData.setAssistiveDeviceNeeded(selectedItem);
                    } else if (questionText.equals(getString(R.string.are_you_satisfied_with_the_health_services_you_receive))) {
                        baselineHealthSurveyData.setHealthServiceSatisfaction(selectedItem);
                    }
                }

                if (questionDataContainer instanceof RadioGroupViewContainer) {
                    String questionText = ((RadioGroupViewContainer) questionDataContainer).getQuestionText();
                    String selectedItem = ((RadioGroupViewContainer) questionDataContainer).getCheckedItem().getDescription();

                    if (questionText.equals(getString(R.string.do_you_have_access_to_rehabilitation_services))) {
                        baselineHealthSurveyData.setHasAccessToRehabilitationServices(selectedItem.equals(getString(R.string.yes)));
                    } else if (questionText.equals(getString(R.string.do_you_need_access_to_rehabilitation_services))) {
                        baselineHealthSurveyData.setNeedsAccessToRehabilitationServices(selectedItem.equals(getString(R.string.yes)));
                    } else if (questionText.equals(getString(R.string.do_you_have_an_assistive_device))) {
                        baselineHealthSurveyData.setHasAssistiveDevice(selectedItem.equals(getString(R.string.yes)));
                    } else if (questionText.equals(getString(R.string.is_your_assistive_device_working_well))) {
                        baselineHealthSurveyData.setAssistiveDeviceWorkingWell(selectedItem.equals(getString(R.string.yes)));
                    } else if (questionText.equals(getString(R.string.do_you_need_an_assistive_device))) {
                        baselineHealthSurveyData.setNeedsAssistiveDevice(selectedItem.equals(getString(R.string.yes)));
                    }
                }
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(healthList, true, onViewPagerChangedListener));
    }

    private void generateEducation() {
        final ArrayList<QuestionDataContainer> educationList = new ArrayList<>();
        educationList.add(new HeaderViewContainer(getString(R.string.education)));

        List<RadioGroupViewContainer.RadioGroupListItem> currentlyGoingToSchoolOptions = new ArrayList<>();
        currentlyGoingToSchoolOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        currentlyGoingToSchoolOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        educationList.add(new RadioGroupViewContainer(getString(R.string.do_you_go_to_school), true, currentlyGoingToSchoolOptions));

        educationList.add(new EditTextViewContainer(getString(R.string.if_yes_what_grade), Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, null, getString(R.string.if_yes_what_grade), InputType.TYPE_CLASS_NUMBER));

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
                if (questionDataContainer instanceof RadioGroupViewContainer) {
                    String questionText = ((RadioGroupViewContainer) questionDataContainer).getQuestionText();
                    String selectedItem = ((RadioGroupViewContainer) questionDataContainer).getCheckedItem().getDescription();

                    if (questionText.equals(getString(R.string.do_you_go_to_school))) {
                        baselineEducationSurveyData.setAttendingSchool(selectedItem.equals(getString(R.string.yes)));
                    } else if (questionText.equals(getString(R.string.if_no_have_you_ever_been_to_school_before))) {
                        baselineEducationSurveyData.setHasBeenToSchool(selectedItem.equals(getString(R.string.yes)));
                    } else if (questionText.equals(getString(R.string.if_no_do_you_want_to_go_to_school))) {
                        baselineEducationSurveyData.setWantsToAttendSchool(selectedItem.equals(getString(R.string.yes)));
                    }
                }

                if (questionDataContainer instanceof EditTextViewContainer) {
                    String questionText = ((EditTextViewContainer) questionDataContainer).getQuestionText();
                    String userInput = ((EditTextViewContainer) questionDataContainer).getUserInput();

                    if (questionText.equals(getString(R.string.if_yes_what_grade))) {
                        baselineEducationSurveyData.setGrade(userInput);
                    }
                }

                if (questionDataContainer instanceof SpinnerViewContainer) {
                    String questionText = ((SpinnerViewContainer) questionDataContainer).getQuestionText();
                    String selectedItem = ((SpinnerViewContainer) questionDataContainer).getSelectedItem();

                    if (questionText.equals(getString(R.string.if_no_why_do_you_not_go_to_school))) {
                        baselineEducationSurveyData.setReasonForNotAttendingSchool(selectedItem);
                    }
                }

            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(educationList, true, onViewPagerChangedListener));
    }

    private void generateSocial() {
        final ArrayList<QuestionDataContainer> socialList = new ArrayList<>();
        socialList.add(new HeaderViewContainer(getString(R.string.social)));

        List<RadioGroupViewContainer.RadioGroupListItem> valuedMemberOfCommunityOptions = new ArrayList<>();
        valuedMemberOfCommunityOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        valuedMemberOfCommunityOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        socialList.add(new RadioGroupViewContainer(getString(R.string.do_you_feel_valued_as_a_member_of_your_community), true, valuedMemberOfCommunityOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> feelIndependentOptions = new ArrayList<>();
        feelIndependentOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        feelIndependentOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        socialList.add(new RadioGroupViewContainer(getString(R.string.do_you_feel_independent), true, feelIndependentOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> participateInEventsOptions = new ArrayList<>();
        participateInEventsOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        participateInEventsOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        socialList.add(new RadioGroupViewContainer(getString(R.string.are_you_able_to_participate_in_events), true, participateInEventsOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> disabilityAffectInteractionOptions = new ArrayList<>();
        disabilityAffectInteractionOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        disabilityAffectInteractionOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        socialList.add(new RadioGroupViewContainer(getString(R.string.does_your_disability_affect_your_ability_to_interact_socially), true, disabilityAffectInteractionOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> experiencedDiscriminationOptions = new ArrayList<>();
        experiencedDiscriminationOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        experiencedDiscriminationOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        socialList.add(new RadioGroupViewContainer(getString(R.string.have_you_experienced_discrimination_because_of_your_disability), true, experiencedDiscriminationOptions));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {

            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(socialList, true, onViewPagerChangedListener));
    }

    private void generateLivelihood() {
        final ArrayList<QuestionDataContainer> livelihoodList = new ArrayList<>();
        livelihoodList.add(new HeaderViewContainer(getString(R.string.livelihood)));

        List<RadioGroupViewContainer.RadioGroupListItem> workingOptions = new ArrayList<>();
        workingOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        workingOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        livelihoodList.add(new RadioGroupViewContainer(getString(R.string.are_you_working), true, workingOptions));

        livelihoodList.add(new EditTextViewContainer(getString(R.string.if_yes_what_do_you_do), Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, null, getString(R.string.if_yes_what_do_you_do), InputType.TYPE_CLASS_TEXT));

        List<String> employedOptions = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.employment_array))
        );
        livelihoodList.add(new SpinnerViewContainer(getString(R.string.are_you_employed_or_self_employed), Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, employedOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> meetFinancialNeedsOptions = new ArrayList<>();
        meetFinancialNeedsOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        meetFinancialNeedsOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        livelihoodList.add(new RadioGroupViewContainer(getString(R.string.does_this_meet_your_financial_needs), true, meetFinancialNeedsOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> disabilityAffectWorkOptions = new ArrayList<>();
        disabilityAffectWorkOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        disabilityAffectWorkOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        livelihoodList.add(new RadioGroupViewContainer(getString(R.string.does_your_disability_affect_your_ability_to_go_to_work), true, disabilityAffectWorkOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> wantToWorkOptions = new ArrayList<>();
        wantToWorkOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        wantToWorkOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        livelihoodList.add(new RadioGroupViewContainer(getString(R.string.do_you_want_to_work), true, wantToWorkOptions));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {

            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(livelihoodList, true, onViewPagerChangedListener));
    }

    private void generateFoodAndNutrition() {
        final ArrayList<QuestionDataContainer> foodAndNutritionList = new ArrayList<>();
        foodAndNutritionList.add(new HeaderViewContainer(getString(R.string.food_and_nutrition)));

        List<String> rateFoodSecurityOptions = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.satisfactions_array))
        );
        foodAndNutritionList.add(new SpinnerViewContainer(getString(R.string.what_do_you_think_of_your_food_security), Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, rateFoodSecurityOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> haveEnoughFoodOptions = new ArrayList<>();
        haveEnoughFoodOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        haveEnoughFoodOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        foodAndNutritionList.add(new RadioGroupViewContainer(getString(R.string.do_you_have_enough_food_every_month), true, haveEnoughFoodOptions));

        List<String> rateNutritionOptions = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.nutrition_array))
        );
        foodAndNutritionList.add(new SpinnerViewContainer(getString(R.string.if_child_is_this_child), Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, rateNutritionOptions));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {

            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(foodAndNutritionList, true, onViewPagerChangedListener));
    }

    private void generateEmpowerment() {
        final ArrayList<QuestionDataContainer> empowermentList = new ArrayList<>();
        empowermentList.add(new HeaderViewContainer(getString(R.string.empowerment)));

        List<RadioGroupViewContainer.RadioGroupListItem> memberOfOrganizationsOptions = new ArrayList<>();
        memberOfOrganizationsOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        memberOfOrganizationsOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        empowermentList.add(new RadioGroupViewContainer(getString(R.string.are_you_a_member_of_any_organizations_which_assist_people_with_disabilities), true, memberOfOrganizationsOptions));

        empowermentList.add(new EditTextViewContainer(getString(R.string.if_yes_which_organizations), Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, null, getString(R.string.if_yes_which_organizations), InputType.TYPE_CLASS_TEXT));

        List<RadioGroupViewContainer.RadioGroupListItem> awareOfRightsOptions = new ArrayList<>();
        awareOfRightsOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        awareOfRightsOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        empowermentList.add(new RadioGroupViewContainer(getString(R.string.are_you_aware_of_your_rights_as_a_citizen_living_with_disabilities), true, awareOfRightsOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> ableToInfluencePeopleOptions = new ArrayList<>();
        ableToInfluencePeopleOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        ableToInfluencePeopleOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        empowermentList.add(new RadioGroupViewContainer(getString(R.string.do_you_feel_like_you_are_able_to_influence_people_around_you), true, ableToInfluencePeopleOptions));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {

            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(empowermentList, true, onViewPagerChangedListener));
    }

    private void generateShelterAndCare() {
        final ArrayList<QuestionDataContainer> shelterAndCareList = new ArrayList<>();
        shelterAndCareList.add(new HeaderViewContainer(getString(R.string.shelter_and_care)));

        List<RadioGroupViewContainer.RadioGroupListItem> adequateShelterOptions = new ArrayList<>();
        adequateShelterOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        adequateShelterOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        shelterAndCareList.add(new RadioGroupViewContainer(getString(R.string.do_you_have_adequate_shelter), true, adequateShelterOptions));

        List<RadioGroupViewContainer.RadioGroupListItem> accessToEssentialItemsOptions = new ArrayList<>();
        accessToEssentialItemsOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.yes), false, View.generateViewId()));
        accessToEssentialItemsOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.no), false, View.generateViewId()));
        shelterAndCareList.add(new RadioGroupViewContainer(getString(R.string.do_you_have_access_to_essential_items_for_your_household), true, accessToEssentialItemsOptions));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {

            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(shelterAndCareList, true, onViewPagerChangedListener));
    }

    private void setupButtons() {
        binding.baselineSurveyPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.baselineSurveyPageViewPager.getCurrentItem() == baselineSurveyFragmentAdapter.getItemCount() - 1) {
                    // Check if fields are filled and make database API call
                    getActivity().getSupportFragmentManager().popBackStack();
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
