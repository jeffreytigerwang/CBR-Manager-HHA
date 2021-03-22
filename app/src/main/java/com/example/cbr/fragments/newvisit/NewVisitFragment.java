package com.example.cbr.fragments.newvisit;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cbr.R;
import com.example.cbr.adapters.questioninfoadapters.QuestionsFragmentPagerAdapter;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.CheckBoxViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.EditTextViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.HeaderViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.RadioGroupViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.SingleTextViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.SpinnerViewContainer;
import com.example.cbr.databinding.FragmentQuestionspageBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.VisitEducationQuestionSetData;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.models.VisitHealthQuestionSetData;
import com.example.cbr.models.VisitSocialQuestionSetData;
import com.example.cbr.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

;

public class NewVisitFragment extends BaseFragment implements NewVisitContract.View {

    private FragmentQuestionspageBinding binding;
    private NewVisitContract.Presenter presenter;

    private ClientInfo clientInfo;
    private QuestionsFragmentPagerAdapter questionsFragmentPagerAdapter;
    private final ArrayList<QuestionsFragmentPagerAdapter.ViewPagerContainer> viewPagerContainerList = new ArrayList<>();

    VisitGeneralQuestionSetData generalQuestionSetData = new VisitGeneralQuestionSetData();
    VisitHealthQuestionSetData healthQuestionSetData = new VisitHealthQuestionSetData();
    VisitEducationQuestionSetData educationQuestionSetData = new VisitEducationQuestionSetData();
    VisitSocialQuestionSetData socialQuestionSetData = new VisitSocialQuestionSetData();

    private enum PAGES {
        GENERAL,
        HEALTH,
        EDUCATION,
        SOCIAL
    }

    private static final String NEW_VISIT_PAGE_BUNDLE = "newVisitPageBundle";

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new NewVisitPresenter(this, getContext()));
        binding = FragmentQuestionspageBinding.inflate(inflater, container, false);

        if (getArguments() != null) {
            clientInfo = (ClientInfo) getArguments().getSerializable(NEW_VISIT_PAGE_BUNDLE);
        } else {
            Toast.makeText(getContext(), getString(R.string.unable_to_retrieve_client_info),
                    Toast.LENGTH_SHORT).show();
        }

        setupViewPager();
        setupButtons();

        return binding.getRoot();
    }



    private void setupViewPager() {
        generateViewPagerList();
        final ViewPager2 viewPager2 = binding.questionsPageViewPager;
        questionsFragmentPagerAdapter = new QuestionsFragmentPagerAdapter(getActivity(), viewPagerContainerList);
        viewPager2.setAdapter(questionsFragmentPagerAdapter);
        viewPager2.setOffscreenPageLimit(10);

        updateDisplayInfo(PAGES.GENERAL.ordinal());
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateDisplayInfo(position);
            }
        });
    }

    private void updateDisplayInfo(int currentPage) {
        int numTotalPage = questionsFragmentPagerAdapter.getItemCount();
        binding.questionsPagePageNumberText.setText(getString(R.string.viewpager_page_number, currentPage + 1, numTotalPage));

        if (currentPage == PAGES.GENERAL.ordinal()) {
            binding.questionsPageNegativeButton.setText(R.string.cancel);
        } else {
            binding.questionsPageNegativeButton.setText(R.string.back);
        }

        if (currentPage == numTotalPage - 1) {
            binding.questionsPagePositiveButton.setText(R.string.record);
        } else {
            binding.questionsPagePositiveButton.setText(R.string.next);
        }
    }

    private void setPageActive(int page, boolean isActive) {
        questionsFragmentPagerAdapter.setPageActive(page, isActive);
        updateDisplayInfo(binding.questionsPageViewPager.getCurrentItem());
    }

    private void setupButtons() {
        binding.questionsPagePositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.questionsPageViewPager.getCurrentItem() == questionsFragmentPagerAdapter.getItemCount() - 1) {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
                binding.questionsPageViewPager.setCurrentItem(binding.questionsPageViewPager.getCurrentItem() + 1);
            }
        });

        binding.questionsPageNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.questionsPageViewPager.getCurrentItem() == PAGES.GENERAL.ordinal()) {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
                binding.questionsPageViewPager.setCurrentItem(binding.questionsPageViewPager.getCurrentItem() - 1);
            }
        });
    }

    private void generateViewPagerList() {
        generateGeneralPage();
        generateHealthPage();
        generateEducationPage();
        generateSocialPage();
    }

    private void generateGeneralPage() {
        final ArrayList<QuestionDataContainer> generalPageViews = new ArrayList<>();
        generalPageViews.add(new HeaderViewContainer(getString(R.string.general_questions)));

        List<RadioGroupViewContainer.RadioGroupListItem> radioGroupListItems = new ArrayList<>();
        radioGroupListItems.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.cbr),
                false, View.generateViewId()));
        radioGroupListItems.add(new RadioGroupViewContainer.RadioGroupListItem(
                getString(R.string.disability_centre_referral), false, View.generateViewId()));
        radioGroupListItems.add(new RadioGroupViewContainer.RadioGroupListItem(
                getString(R.string.disability_centre_referral_follow_up),
                false, View.generateViewId()));
        generalPageViews.add(new RadioGroupViewContainer(getString(R.string.purpose_of_visit),
                true, radioGroupListItems));

        generalPageViews.add(new SingleTextViewContainer(getString(R.string.new_visit_question_2),
                Constants.PRIMARY_QUESTION_TEXT_SIZE_SP));
        generalPageViews.add(new CheckBoxViewContainer(getString(R.string.health)));
        generalPageViews.add(new CheckBoxViewContainer(getString(R.string.education)));
        generalPageViews.add(new CheckBoxViewContainer(getString(R.string.social)));

        generalPageViews.add(new EditTextViewContainer(getString(R.string.date_of_visit),
                getString(R.string.yyyy_mm_dd), InputType.TYPE_DATETIME_VARIATION_DATE));

        generalPageViews.add(new EditTextViewContainer(getString(R.string.name_of_cbr_worker),
                getString(R.string.first_and_last_name), InputType.TYPE_TEXT_VARIATION_PERSON_NAME));

        generalPageViews.add(new EditTextViewContainer(getString(R.string.location_of_visit),
                getString(R.string.gps_location), InputType.TYPE_CLASS_TEXT));

        List<String> siteLocations = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.zone_locations_array)));
        generalPageViews.add(new SpinnerViewContainer(getString(R.string.site_location), siteLocations));

        generalPageViews.add(new EditTextViewContainer(getString(R.string.village_no),
                getString(R.string.e_g_5), InputType.TYPE_CLASS_NUMBER));

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(
                generalPageViews,
                true,
                new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof RadioGroupViewContainer) {
                    RadioGroupViewContainer.RadioGroupListItem listItem =
                            ((RadioGroupViewContainer) questionDataContainer).getCheckedItem();
                    String purposeOfVisit = listItem.getDescription();
                    if (purposeOfVisit.equals(getString(R.string.cbr))) {
                        generalQuestionSetData.setPurposeOfVisit(Constants.CBR);
                    } else if (purposeOfVisit.equals(getString(R.string.disability_centre_referral))) {
                        generalQuestionSetData.setPurposeOfVisit(Constants.DCR);
                    } else {
                        generalQuestionSetData.setPurposeOfVisit(Constants.DCRFU);
                    }
                }
                if (questionDataContainer instanceof CheckBoxViewContainer) {
                    String aspectSelected = ((CheckBoxViewContainer) questionDataContainer)
                            .getQuestionText();
                    boolean isChecked = ((CheckBoxViewContainer) questionDataContainer).isChecked();
                    if (aspectSelected.equals(getString(R.string.health))) {
                        generalQuestionSetData.setHealthChecked(isChecked);
                        setPageActive(PAGES.HEALTH.ordinal(), isChecked);
                    } else if (aspectSelected.equals(getString(R.string.education))) {
                        generalQuestionSetData.setEducationChecked(isChecked);
                        setPageActive(PAGES.EDUCATION.ordinal(), isChecked);
                    } else if (aspectSelected.equals(getString(R.string.social))) {
                        generalQuestionSetData.setSocialChecked(isChecked);
                        setPageActive(PAGES.SOCIAL.ordinal(), isChecked);
                    }
                }
                if (questionDataContainer instanceof EditTextViewContainer) {
                    String userInput = ((EditTextViewContainer) generalPageViews
                            .get(positionChanged)).getUserInput();
                    String questionText = ((EditTextViewContainer) generalPageViews
                            .get(positionChanged)).getQuestionText();
                    if (questionText.equals(getString(R.string.name_of_cbr_worker))) {
                        generalQuestionSetData.setWorkerName(userInput);
                    } else if (questionText.equals(getString(R.string.location_of_visit))) {
                        generalQuestionSetData.setVisitGpsLocation(userInput);
                    } else if (questionText.equals(getString(R.string.village_no))) {
                        generalQuestionSetData.setVillageNumber(userInput);
                    }
                }
                if (questionDataContainer instanceof SpinnerViewContainer) {
                    String itemSelected = ((SpinnerViewContainer) questionDataContainer)
                            .getSelectedItem();
                    generalQuestionSetData.setVisitZoneLocation(itemSelected);
                }
            }
        }));
    }

    private void generateHealthPage() {
        ArrayList<QuestionDataContainer> healthPageViews = new ArrayList<>();

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(
                healthPageViews,
                false,
                new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {

            }
        }));
    }

    private void generateEducationPage() {
        ArrayList<QuestionDataContainer> educationPageViews = new ArrayList<>();



        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(
                educationPageViews,
                false,
                new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {

            }
        }));
    }

    private void generateSocialPage() {
        ArrayList<QuestionDataContainer> socialPageViews = new ArrayList<>();

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(
                socialPageViews,
                false,
                new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {

            }
        }));
    }

    public static NewVisitFragment newInstance(ClientInfo clientInfo) {
        NewVisitFragment newVisitFragment = new NewVisitFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(NEW_VISIT_PAGE_BUNDLE, clientInfo);
        newVisitFragment.setArguments(bundle);

        return newVisitFragment;
    }

    @Override
    public void setPresenter(NewVisitContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public static String getFragmentTag() {
        return NewVisitFragment.class.getSimpleName();
    }
}
