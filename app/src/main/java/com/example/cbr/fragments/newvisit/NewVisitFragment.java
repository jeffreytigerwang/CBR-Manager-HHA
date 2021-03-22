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
        final ArrayList<QuestionDataContainer> generalPageList = new ArrayList<>();
        generalPageList.add(new HeaderViewContainer(getString(R.string.general_questions)));

        List<RadioGroupViewContainer.RadioGroupListItem> radioGroupListItems = new ArrayList<>();
        radioGroupListItems.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.cbr),
                false, View.generateViewId()));
        radioGroupListItems.add(new RadioGroupViewContainer.RadioGroupListItem(
                getString(R.string.disability_centre_referral), false, View.generateViewId()));
        radioGroupListItems.add(new RadioGroupViewContainer.RadioGroupListItem(
                getString(R.string.disability_centre_referral_follow_up),
                false, View.generateViewId()));
        generalPageList.add(new RadioGroupViewContainer(getString(R.string.purpose_of_visit),
                true, radioGroupListItems));

        generalPageList.add(new SingleTextViewContainer(getString(R.string.new_visit_question_2),
                Constants.PRIMARY_QUESTION_TEXT_SIZE_SP));
        generalPageList.add(new CheckBoxViewContainer(getString(R.string.health)));
        generalPageList.add(new CheckBoxViewContainer(getString(R.string.education)));
        generalPageList.add(new CheckBoxViewContainer(getString(R.string.social)));

        generalPageList.add(new EditTextViewContainer(getString(R.string.date_of_visit),
                getString(R.string.yyyy_mm_dd), InputType.TYPE_CLASS_DATETIME));

        generalPageList.add(new EditTextViewContainer(getString(R.string.name_of_cbr_worker),
                getString(R.string.first_and_last_name), InputType.TYPE_TEXT_VARIATION_PERSON_NAME));

        generalPageList.add(new EditTextViewContainer(getString(R.string.location_of_visit),
                getString(R.string.gps_location), InputType.TYPE_CLASS_TEXT));

        List<String> siteLocations = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.zone_locations_array)));
        generalPageList.add(new SpinnerViewContainer(getString(R.string.site_location), siteLocations));

        generalPageList.add(new EditTextViewContainer(getString(R.string.village_no),
                getString(R.string.e_g_5), InputType.TYPE_CLASS_NUMBER));

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(
                generalPageList,
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
                    String userInput = ((EditTextViewContainer) generalPageList
                            .get(positionChanged)).getUserInput();
                    String questionText = ((EditTextViewContainer) generalPageList
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
        ArrayList<QuestionDataContainer> physioTherapyList = new ArrayList<>();
        physioTherapyList.add(new HeaderViewContainer(getString(R.string.physiotherapy)));
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
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(physioTherapyList, false, onViewPagerChangedListener));
    }

    private void generateEducationPage() {
        ArrayList<QuestionDataContainer> prostheticList = new ArrayList<>();

        prostheticList.add(new HeaderViewContainer(getString(R.string.prosthetic)));
        List<RadioGroupViewContainer.RadioGroupListItem> prostheticOptions = new ArrayList<>();
        prostheticOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.above_knee_question), false, View.generateViewId()));
        prostheticOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.below_knee_question), false, View.generateViewId()));
        prostheticList.add(new RadioGroupViewContainer(getString(R.string.prosthetic_question), true, prostheticOptions));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {

            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(prostheticList, false, onViewPagerChangedListener));
    }

    private void generateSocialPage() {
        ArrayList<QuestionDataContainer> orthoticList = new ArrayList<>();

        orthoticList.add(new HeaderViewContainer(getString(R.string.orthotic)));
        List<RadioGroupViewContainer.RadioGroupListItem> orthoticOptions = new ArrayList<>();
        orthoticOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.above_elbow_question), false, View.generateViewId()));
        orthoticOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.below_elbow_question), false, View.generateViewId()));
        orthoticList.add(new RadioGroupViewContainer(getString(R.string.orthotic_question), true, orthoticOptions));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {

            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(orthoticList, false, onViewPagerChangedListener));
    }

    private void generateWheelchair() {
        ArrayList<QuestionDataContainer> wheelchair = new ArrayList<>();

        wheelchair.add(new HeaderViewContainer(getString(R.string.wheelchair)));
        List<RadioGroupViewContainer.RadioGroupListItem> basicIntermediate = new ArrayList<>();
        basicIntermediate.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.basic_user), false, View.generateViewId()));
        basicIntermediate.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.intermediate_user), false, View.generateViewId()));
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
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {

            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(wheelchair, false, onViewPagerChangedListener));
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
