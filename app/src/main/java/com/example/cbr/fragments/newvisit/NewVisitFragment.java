package com.example.cbr.fragments.newvisit;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cbr.R;
import com.example.cbr.adapters.questioninfoadapters.QuestionsFragmentPagerAdapter;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.CheckBoxViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.CheckBoxWithDescriptionViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.DividerViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.DoubleTextViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.EditTextViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.HeaderViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.RadioGroupViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.SingleTextViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.SpinnerViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.UnchangeableEditTextViewContainer;
import com.example.cbr.databinding.FragmentQuestionspageBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.Users;
import com.example.cbr.models.VisitEducationQuestionSetData;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.models.VisitHealthQuestionSetData;
import com.example.cbr.models.VisitSocialQuestionSetData;
import com.example.cbr.util.Constants;
import com.example.cbr.util.CustomExceptions;
import com.example.cbr.util.LocationUtil;
import com.example.cbr.util.StringsUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Fragment for the CBR worker to fill out information on a client for a new visit.
 * */

public class NewVisitFragment extends BaseFragment implements NewVisitContract.View {

    private FragmentQuestionspageBinding binding;
    private NewVisitContract.Presenter presenter;

    private ClientInfo clientInfo;
    private Integer clientId;
    private QuestionsFragmentPagerAdapter questionsFragmentPagerAdapter;
    private final ArrayList<QuestionsFragmentPagerAdapter.ViewPagerContainer> viewPagerContainerList = new ArrayList<>();
    private LocationUtil locationUtil;
    private String latLongLocation;

    private final Users users = Users.getInstance();
    private final VisitGeneralQuestionSetData generalQuestionSetData = new VisitGeneralQuestionSetData();
    private final VisitHealthQuestionSetData healthQuestionSetData = new VisitHealthQuestionSetData();
    private final VisitEducationQuestionSetData educationQuestionSetData = new VisitEducationQuestionSetData();
    private final VisitSocialQuestionSetData socialQuestionSetData = new VisitSocialQuestionSetData();


    private enum PAGES {
        GENERAL,
        HEALTH,
        EDUCATION,
        SOCIAL
    }

    private Menu menu;

    private static final String NEW_VISIT_PAGE_BUNDLE = "newVisitPageBundle";
    private static final String LOG_TAG = "NewVisitFragment";

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setPresenter(new NewVisitPresenter(this, getContext()));
        binding = FragmentQuestionspageBinding.inflate(inflater, container, false);
        getActivity().setTitle(getString(R.string.new_visit_title));
        setHasOptionsMenu(true);
        requestLocationPermissions();

        if (getArguments() != null) {
            clientInfo = (ClientInfo) getArguments().getSerializable(NEW_VISIT_PAGE_BUNDLE);
            binding.questionsPageTitle.setText(clientInfo.getFullName());
            try {
                clientId = Integer.parseInt(clientInfo.getId());
            } catch (NullPointerException e) {
                Log.i(LOG_TAG, "onCreateView: clientId=" + clientInfo.getId());
                Toast.makeText(getContext(), getResources().getString(R.string.failed_to_get_client_id),
                        Toast.LENGTH_SHORT).show();
                finish();
            }
            setVisitClientId();
        } else {
            Toast.makeText(getContext(), getString(R.string.unable_to_retrieve_client_info),
                    Toast.LENGTH_SHORT).show();
            finish();
        }

        setupViewPager();
        setupButtons();

        return binding.getRoot();
    }

    private void requestLocationPermissions() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
                &&
                ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, Constants.LOCATION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Constants.LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), getString(R.string.automatic_fill_location),
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), R.string.location_permission_not_granted,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getActivity().setTitle(getString(R.string.app_name));
        menu.clear();
        setHasOptionsMenu(false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        this.menu = menu;
        this.menu.clear();
        // inflate action bar items here
        inflater.inflate(R.menu.action_bar_record, menu);
        setupMenuItem(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * For menu items using {@code app:actionLayout}, it is important to setup
     * on click listeners. Otherwise,
     * {@link androidx.fragment.app.Fragment#onOptionsItemSelected(MenuItem)
     * onOptionsItemSelected(MenuItem)}
     * will not be called.
     * <p>Also, make sure that all views on the action layouts are set to
     * {@code android:clickable="false"}. Otherwise, the listener on those
     * views will steal the result.
     * */
    private void setupMenuItem(@NonNull Menu menu) {
        final MenuItem item = menu.findItem(R.id.newVisit_recordButton);
        View actionView = item.getActionView();
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(item);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar item clicks here
        int itemId = item.getItemId();

        if (itemId == R.id.newVisit_recordButton) {
            handleRecord();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void finish() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    private void handleRecord() {
        final List<String> emptyGeneralQuestions = generalQuestionSetData.getEmptyQuestions();
        final List<String> emptyHealthQuestions = healthQuestionSetData.getEmptyQuestions();
        final List<String> emptyEducationQuestions = educationQuestionSetData.getEmptyQuestions();
        final List<String> emptySocialQuestions = socialQuestionSetData.getEmptyQuestions();

        boolean isAllFilled = isAllRequiredQuestionsFilled(emptyGeneralQuestions,
                emptyHealthQuestions, emptyEducationQuestions, emptySocialQuestions);
        if (isAllFilled) {
            recordAndFinish();
        } else {
            List<String> emptyQuestions = new ArrayList<>(emptyGeneralQuestions);
            if (generalQuestionSetData.isHealthChecked()) {
                emptyQuestions.addAll(emptyHealthQuestions);
            }
            if (generalQuestionSetData.isEducationChecked()) {
                emptyQuestions.addAll(emptyEducationQuestions);
            }
            if (generalQuestionSetData.isSocialChecked()) {
                emptyQuestions.addAll(emptySocialQuestions);
            }
            displayNumberEmpty(emptyQuestions);
            showErrorDialog(getString(R.string.record_error), null);
        }
    }

    private void recordAndFinish() {
        presenter.createVisitGeneralQuestionSetData(generalQuestionSetData);
        if (generalQuestionSetData.isHealthChecked()) {
            presenter.createVisitHealthQuestionSetData(healthQuestionSetData);
        }
        if (generalQuestionSetData.isEducationChecked()) {
            presenter.createVisitEducationQuestionSetData(educationQuestionSetData);
        }
        if (generalQuestionSetData.isSocialChecked()) {
            presenter.createVisitSocialQuestionSetData(socialQuestionSetData);
        }
        finish();
    }

    private boolean isAllRequiredQuestionsFilled(List<String> emptyGeneralQuestions,
                                                 List<String> emptyHealthQuestions,
                                                 List<String> emptyEducationQuestions,
                                                 List<String> emptySocialQuestions) {

        final boolean isHealthChecked = generalQuestionSetData.isHealthChecked();
        final boolean isEducationChecked = generalQuestionSetData.isEducationChecked();
        final boolean isSocialChecked = generalQuestionSetData.isSocialChecked();

        // This one-liner may obscure readability, but it is necessary to remove
        // 'if' statements (improve performance).
        // All is filled under the condition that the empty question lists are empty,
        // it can depend on whether health, education, or social is considered required.
        return emptyGeneralQuestions.isEmpty()
                && (!isHealthChecked || emptyHealthQuestions.isEmpty())
                && (!isEducationChecked || emptyEducationQuestions.isEmpty())
                && (!isSocialChecked || emptySocialQuestions.isEmpty());
    }

    private void displayNumberEmpty(List<String> emptyQuestions) {
        TextView textViewMissedRequiredQuestions = binding.questionsPageMissedRequiredQuestionsTextView;
        TextView textViewQuestionNumbers = binding.questionsPageQuestionNumbersTextView;

        textViewMissedRequiredQuestions.setVisibility(View.VISIBLE);
        textViewQuestionNumbers.setVisibility(View.VISIBLE);

        StringBuilder questionNumbers = new StringBuilder();
        for (int i = 0; i < emptyQuestions.size(); i++) {
            questionNumbers.append(emptyQuestions.get(i)).append(" ");
        }
        textViewQuestionNumbers.setText(questionNumbers.toString());
    }

    private void setLatLongLocation() {
        try {
            locationUtil = new LocationUtil(getContext());
            latLongLocation = getString(R.string.lat_long_location,
                    locationUtil.getLatitude(), locationUtil.getLongitude());
            generalQuestionSetData.setVisitGpsLocation(latLongLocation);
            locationUtil.stopUpdateService();
        } catch (CustomExceptions.PermissionNotGranted permissionNotGranted) {
            showOkDialog(getString(R.string.permission_change),
                    getString(R.string.permission_change_message),
                    null);
        } catch (CustomExceptions.GPSNotEnabled ignored) {
        } catch (CustomExceptions.LocationNotFound locationNotFound) {
            locationUtil.stopUpdateService();
            Toast.makeText(getContext(),
                    getString(R.string.location_not_found),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void setVisitClientId() {
        int visitId = ThreadLocalRandom.current().nextInt(100000000, 999999999);

        generalQuestionSetData.setClientId(clientId);
        generalQuestionSetData.setVisitId(visitId);

        healthQuestionSetData.setClientId(clientId);
        healthQuestionSetData.setVisitId(visitId);

        educationQuestionSetData.setClientId(clientId);
        educationQuestionSetData.setVisitId(visitId);

        socialQuestionSetData.setClientId(clientId);
        socialQuestionSetData.setVisitId(visitId);
    }

    private void setupViewPager() {
        generateViewPagerList();
        final ViewPager2 viewPager2 = binding.questionsPageViewPager;
        questionsFragmentPagerAdapter = new QuestionsFragmentPagerAdapter(this, viewPagerContainerList);
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
            setButtonActive(View.INVISIBLE, false);
        } else {
            setButtonActive(View.VISIBLE, true);
        }
    }

    private void setButtonActive(int visibility, boolean isActive) {
        binding.questionsPagePositiveButton.setVisibility(visibility);
        binding.questionsPagePositiveButton.setClickable(isActive);
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
                    finish();
                }
                binding.questionsPageViewPager.setCurrentItem(binding.questionsPageViewPager.getCurrentItem() + 1);
            }
        });

        binding.questionsPageNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.questionsPageViewPager.getCurrentItem() == PAGES.GENERAL.ordinal()) {
                    finish();
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

        generalPageViews.add(new UnchangeableEditTextViewContainer(getString(R.string.date_of_visit),
                Constants.PRIMARY_QUESTION_TEXT_SIZE_SP,
                StringsUtil.dateToUKFormat(generalQuestionSetData.getDateOfVisit())));

        final String userName = users.getFirstName() + " " + users.getLastName();
        generalQuestionSetData.setWorkerName(userName);
        generalPageViews.add(new UnchangeableEditTextViewContainer(
                getString(R.string.name_of_cbr_worker), Constants.PRIMARY_QUESTION_TEXT_SIZE_SP,
                userName));

        setLatLongLocation();
        generalPageViews.add(new EditTextViewContainer(getString(R.string.location_of_visit),
                Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, latLongLocation,
                getString(R.string.gps_location), InputType.TYPE_CLASS_TEXT));

        List<String> siteLocations = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.zone_locations_array)));
        generalQuestionSetData.setVisitZoneLocation(siteLocations.get(0));
        generalPageViews.add(new SpinnerViewContainer(getString(R.string.new_vist_location),
                Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, siteLocations));

        generalPageViews.add(new EditTextViewContainer(getString(R.string.village_no),
                Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, "", getString(R.string.e_g_5),
                InputType.TYPE_CLASS_NUMBER));

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
                    generalQuestionSetData.setPurposeOfVisit(purposeOfVisit);
                    if (purposeOfVisit.equals(getString(R.string.cbr))) {
                        generalQuestionSetData.setCBRChecked(true);
                        generalQuestionSetData.setDCRChecked(false);
                        generalQuestionSetData.setDCRFUChecked(false);
                    } else if (purposeOfVisit.equals(getString(R.string.disability_centre_referral))) {
                        generalQuestionSetData.setDCRChecked(true);
                        generalQuestionSetData.setCBRChecked(false);
                        generalQuestionSetData.setDCRFUChecked(false);
                    } else {
                        generalQuestionSetData.setDCRFUChecked(true);
                        generalQuestionSetData.setCBRChecked(false);
                        generalQuestionSetData.setDCRChecked(false);
                    }
                }
                if (questionDataContainer instanceof CheckBoxViewContainer) {
                    String aspectSelected = ((CheckBoxViewContainer) questionDataContainer)
                            .getQuestionText();
                    boolean isChecked = ((CheckBoxViewContainer) questionDataContainer).isChecked();
                    if (aspectSelected.equals(getString(R.string.health))) {
                        generalQuestionSetData.setHealthChecked(isChecked);
                        if (!isChecked) {
                            healthQuestionSetData.resetData();
                        }
                        setPageActive(PAGES.HEALTH.ordinal(), isChecked);
                    } else if (aspectSelected.equals(getString(R.string.education))) {
                        generalQuestionSetData.setEducationChecked(isChecked);
                        if (!isChecked) {
                            educationQuestionSetData.resetData();
                        }
                        setPageActive(PAGES.EDUCATION.ordinal(), isChecked);
                    } else if (aspectSelected.equals(getString(R.string.social))) {
                        generalQuestionSetData.setSocialChecked(isChecked);
                        if (!isChecked) {
                            socialQuestionSetData.resetData();
                        }
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
        final ArrayList<QuestionDataContainer> healthPageViews = new ArrayList<>();
        healthPageViews.add(new HeaderViewContainer(getString(R.string.health)));

        healthPageViews.add(new SingleTextViewContainer(getString(R.string.for_health_what_was_provided),
                Constants.PRIMARY_QUESTION_TEXT_SIZE_SP));
        healthPageViews.add(new CheckBoxWithDescriptionViewContainer(
                getString(R.string.wheelchair), getString(R.string.description_max_100_characters),
                InputType.TYPE_CLASS_TEXT));
        healthPageViews.add(new CheckBoxWithDescriptionViewContainer(
                getString(R.string.prosthetic), getString(R.string.description_max_100_characters),
                InputType.TYPE_CLASS_TEXT));
        healthPageViews.add(new CheckBoxWithDescriptionViewContainer(
                getString(R.string.orthotic), getString(R.string.description_max_100_characters),
                InputType.TYPE_CLASS_TEXT));
        healthPageViews.add(new CheckBoxWithDescriptionViewContainer(
                getString(R.string.wheelchair_repairs), getString(R.string.description_max_100_characters),
                InputType.TYPE_CLASS_TEXT));
        healthPageViews.add(new CheckBoxWithDescriptionViewContainer(
                getString(R.string.referral_to_health_centre),
                getString(R.string.description_max_100_characters),
                InputType.TYPE_CLASS_TEXT));
        healthPageViews.add(new CheckBoxWithDescriptionViewContainer(
                getString(R.string.advice), getString(R.string.description_max_100_characters),
                InputType.TYPE_CLASS_TEXT));
        healthPageViews.add(new CheckBoxWithDescriptionViewContainer(
                getString(R.string.advocacy), getString(R.string.description_max_100_characters),
                InputType.TYPE_CLASS_TEXT));
        healthPageViews.add(new CheckBoxWithDescriptionViewContainer(
                getString(R.string.encouragement), getString(R.string.description_max_100_characters),
                InputType.TYPE_CLASS_TEXT));

        healthPageViews.add(new DividerViewContainer());

        healthPageViews.add(new DoubleTextViewContainer(getString(R.string.initial_goal),
                clientInfo.getSetGoalForHealth()));

        healthPageViews.add(new DividerViewContainer());

        List<RadioGroupViewContainer.RadioGroupListItem> radioGroupListItems = new ArrayList<>();
        radioGroupListItems.add(new RadioGroupViewContainer.RadioGroupListItem(
                getString(R.string.cancelled), false, View.generateViewId()));
        radioGroupListItems.add(new RadioGroupViewContainer.RadioGroupListItem(
                getString(R.string.ongoing), false, View.generateViewId()));
        radioGroupListItems.add(new RadioGroupViewContainer.RadioGroupListItem(
                getString(R.string.concluded), false, View.generateViewId()));
        healthPageViews.add(new RadioGroupViewContainer(getString(R.string.goal_met_9),
                true, radioGroupListItems));

        healthPageViews.add(new EditTextViewContainer(
                getString(R.string.if_concluded_what_was_the_outcome_10),
                Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, "",
                getString(R.string.description_max_100_characters_optional),
                InputType.TYPE_CLASS_TEXT));

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(
                healthPageViews,
                false,
                new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof CheckBoxWithDescriptionViewContainer) {
                    String checkBoxSelected = ((CheckBoxWithDescriptionViewContainer)
                            questionDataContainer).getCheckBoxText();
                    String description = ((CheckBoxWithDescriptionViewContainer)
                            questionDataContainer).getUserInput();
                    boolean isChecked = ((CheckBoxWithDescriptionViewContainer)
                            questionDataContainer).isChecked();


                    if (checkBoxSelected.equals(getString(R.string.wheelchair))) {
                        healthQuestionSetData.setWheelChairChecked(isChecked);
                        healthQuestionSetData.setWheelChairDesc(description);
                    } else if (checkBoxSelected.equals(getString(R.string.prosthetic))) {
                        healthQuestionSetData.setProstheticChecked(isChecked);
                        healthQuestionSetData.setProstheticDesc(description);
                    } else if (checkBoxSelected.equals(getString(R.string.orthotic))) {
                        healthQuestionSetData.setOrthoticChecked(isChecked);
                        healthQuestionSetData.setOrthoticDesc(description);
                    } else if (checkBoxSelected.equals(getString(R.string.wheelchair_repairs))) {
                        healthQuestionSetData.setWheelChairRepairChecked(isChecked);
                        healthQuestionSetData.setWheelChairRepairDesc(description);
                    } else if (checkBoxSelected.equals(getString(R.string.referral_to_health_centre))) {
                        healthQuestionSetData.setReferralToHCChecked(isChecked);
                        healthQuestionSetData.setReferralToHCDesc(description);
                    } else if (checkBoxSelected.equals(getString(R.string.advice))) {
                        healthQuestionSetData.setHealthAdviceChecked(isChecked);
                        healthQuestionSetData.setHealthAdviceDesc(description);
                    } else if (checkBoxSelected.equals(getString(R.string.advocacy))) {
                        healthQuestionSetData.setHealthAdvocacyChecked(isChecked);
                        healthQuestionSetData.setHealthAdvocacyDesc(description);
                    } else if (checkBoxSelected.equals(getString(R.string.encouragement))) {
                        healthQuestionSetData.setHealthEncouragementChecked(isChecked);
                        healthQuestionSetData.setHealthEncouragementDesc(description);
                    }
                }
                if (questionDataContainer instanceof RadioGroupViewContainer) {
                    String selectedItem = ((RadioGroupViewContainer) questionDataContainer)
                            .getCheckedItem().getDescription();
                    healthQuestionSetData.setHealthGoalStatus(selectedItem);
                    if (selectedItem.equalsIgnoreCase(getString(R.string.concluded))) {
                        healthQuestionSetData.setGoalConcluded(true);
                        healthQuestionSetData.setGoalCancelled(false);
                        healthQuestionSetData.setGoalOngoing(false);
                    } else if (selectedItem.equalsIgnoreCase(getString(R.string.cancelled))) {
                        healthQuestionSetData.setGoalCancelled(true);
                        healthQuestionSetData.setGoalConcluded(false);
                        healthQuestionSetData.setGoalOngoing(false);
                    } else {
                        healthQuestionSetData.setGoalOngoing(true);
                        healthQuestionSetData.setGoalConcluded(false);
                        healthQuestionSetData.setGoalCancelled(false);
                    }
                }
                if (questionDataContainer instanceof EditTextViewContainer) {
                    String description = ((EditTextViewContainer) questionDataContainer).getUserInput();
                    healthQuestionSetData.setHealthOutcomeDesc(description);
                }
            }
        }));
    }

    private void generateEducationPage() {
        ArrayList<QuestionDataContainer> educationPageViews = new ArrayList<>();
        educationPageViews.add(new HeaderViewContainer(getString(R.string.education)));

        educationPageViews.add(new SingleTextViewContainer(getString(R.string.for_education_what_was_provided),
                Constants.PRIMARY_QUESTION_TEXT_SIZE_SP));
        educationPageViews.add(new CheckBoxWithDescriptionViewContainer(getString(R.string.advice),
                getString(R.string.description_max_100_characters), InputType.TYPE_CLASS_TEXT));
        educationPageViews.add(new CheckBoxWithDescriptionViewContainer(getString(R.string.advocacy),
                getString(R.string.description_max_100_characters), InputType.TYPE_CLASS_TEXT));
        educationPageViews.add(new CheckBoxWithDescriptionViewContainer(getString(R.string.referral_to_other_org),
                getString(R.string.description_max_100_characters), InputType.TYPE_CLASS_TEXT));
        educationPageViews.add(new CheckBoxWithDescriptionViewContainer(getString(R.string.encouragement),
                getString(R.string.description_max_100_characters), InputType.TYPE_CLASS_TEXT));

        educationPageViews.add(new DividerViewContainer());

        educationPageViews.add(new DoubleTextViewContainer(getString(R.string.initial_goal),
                clientInfo.getSetGoalForEducation()));

        educationPageViews.add(new DividerViewContainer());

        List<RadioGroupViewContainer.RadioGroupListItem> radioGroupListItems = new ArrayList<>();
        radioGroupListItems.add(new RadioGroupViewContainer.RadioGroupListItem(
                getString(R.string.cancelled), false, View.generateViewId()));
        radioGroupListItems.add(new RadioGroupViewContainer.RadioGroupListItem(
                getString(R.string.ongoing), false, View.generateViewId()));
        radioGroupListItems.add(new RadioGroupViewContainer.RadioGroupListItem(
                getString(R.string.concluded), false, View.generateViewId()));
        educationPageViews.add(new RadioGroupViewContainer(getString(R.string.goal_met_12),
                true, radioGroupListItems));

        educationPageViews.add(new EditTextViewContainer(
                getString(R.string.if_concluded_what_was_the_outcome_13),
                Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, "",
                getString(R.string.description_max_100_characters_optional),
                InputType.TYPE_CLASS_TEXT));

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(
                educationPageViews,
                false,
                new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof CheckBoxWithDescriptionViewContainer) {
                    String checkBoxSelected = ((CheckBoxWithDescriptionViewContainer)
                            questionDataContainer).getCheckBoxText();
                    String description = ((CheckBoxWithDescriptionViewContainer)
                            questionDataContainer).getUserInput();
                    boolean isChecked = ((CheckBoxWithDescriptionViewContainer)
                            questionDataContainer).isChecked();
                    if (checkBoxSelected.equals(getString(R.string.advice))) {
                        educationQuestionSetData.setEducationAdviceChecked(isChecked);
                        educationQuestionSetData.setEducationAdviceDesc(description);
                    } else if (checkBoxSelected.equals(getString(R.string.advocacy))) {
                        educationQuestionSetData.setEducationAdvocacyChecked(isChecked);
                        educationQuestionSetData.setEducationAdvocacyDesc(description);
                    } else if (checkBoxSelected.equals(getString(R.string.referral_to_other_org))) {
                        educationQuestionSetData.setEducationReferralChecked(isChecked);
                        educationQuestionSetData.setEducationReferralDesc(description);
                    } else if (checkBoxSelected.equals(getString(R.string.encouragement))) {
                        educationQuestionSetData.setEducationEncouragementChecked(isChecked);
                        educationQuestionSetData.setEducationEncouragementDesc(description);
                    }
                }
                if (questionDataContainer instanceof RadioGroupViewContainer) {
                    String selectedItem = ((RadioGroupViewContainer) questionDataContainer)
                            .getCheckedItem().getDescription();
                    educationQuestionSetData.setEducationGoalStatus(selectedItem);
                    if (selectedItem.equalsIgnoreCase(getString(R.string.concluded))) {
                        educationQuestionSetData.setGoalConcluded(true);
                        educationQuestionSetData.setGoalCancelled(false);
                        educationQuestionSetData.setGoalOngoing(false);
                    } else if (selectedItem.equalsIgnoreCase(getString(R.string.cancelled))) {
                        educationQuestionSetData.setGoalCancelled(true);
                        educationQuestionSetData.setGoalConcluded(false);
                        educationQuestionSetData.setGoalOngoing(false);
                    } else {
                        educationQuestionSetData.setGoalOngoing(true);
                        educationQuestionSetData.setGoalConcluded(false);
                        educationQuestionSetData.setGoalCancelled(false);
                    }
                }
                if (questionDataContainer instanceof EditTextViewContainer) {
                    String description = ((EditTextViewContainer) questionDataContainer).getUserInput();
                    educationQuestionSetData.setEducationOutcomeDesc(description);
                }
            }
        }));
    }

    private void generateSocialPage() {
        ArrayList<QuestionDataContainer> socialPageViews = new ArrayList<>();
        socialPageViews.add(new HeaderViewContainer(getString(R.string.social)));

        socialPageViews.add(new SingleTextViewContainer(getString(R.string.for_social_what_was_provided),
                Constants.PRIMARY_QUESTION_TEXT_SIZE_SP));
        socialPageViews.add(new CheckBoxWithDescriptionViewContainer(getString(R.string.advice),
                getString(R.string.description_max_100_characters), InputType.TYPE_CLASS_TEXT));
        socialPageViews.add(new CheckBoxWithDescriptionViewContainer(getString(R.string.advocacy),
                getString(R.string.description_max_100_characters), InputType.TYPE_CLASS_TEXT));
        socialPageViews.add(new CheckBoxWithDescriptionViewContainer(getString(R.string.referral_to_other_org),
                getString(R.string.description_max_100_characters), InputType.TYPE_CLASS_TEXT));
        socialPageViews.add(new CheckBoxWithDescriptionViewContainer(getString(R.string.encouragement),
                getString(R.string.description_max_100_characters), InputType.TYPE_CLASS_TEXT));

        socialPageViews.add(new DividerViewContainer());

        socialPageViews.add(new DoubleTextViewContainer(getString(R.string.initial_goal),
                clientInfo.getSetGoalForSocialStatus()));

        socialPageViews.add(new DividerViewContainer());

        List<RadioGroupViewContainer.RadioGroupListItem> radioGroupListItems = new ArrayList<>();
        radioGroupListItems.add(new RadioGroupViewContainer.RadioGroupListItem(
                getString(R.string.cancelled), false, View.generateViewId()));
        radioGroupListItems.add(new RadioGroupViewContainer.RadioGroupListItem(
                getString(R.string.ongoing), false, View.generateViewId()));
        radioGroupListItems.add(new RadioGroupViewContainer.RadioGroupListItem(
                getString(R.string.concluded), false, View.generateViewId()));
        socialPageViews.add(new RadioGroupViewContainer(getString(R.string.goal_met_15),
                true, radioGroupListItems));

        socialPageViews.add(new EditTextViewContainer(
                getString(R.string.if_concluded_what_was_the_outcome_16),
                Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, "",
                getString(R.string.description_max_100_characters_optional),
                InputType.TYPE_CLASS_TEXT));

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(
                socialPageViews,
                false,
                new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof CheckBoxWithDescriptionViewContainer) {
                    String checkBoxSelected = ((CheckBoxWithDescriptionViewContainer)
                            questionDataContainer).getCheckBoxText();
                    String description = ((CheckBoxWithDescriptionViewContainer)
                            questionDataContainer).getUserInput();
                    boolean isChecked = ((CheckBoxWithDescriptionViewContainer)
                            questionDataContainer).isChecked();
                    if (checkBoxSelected.equals(getString(R.string.advice))) {
                        socialQuestionSetData.setSocialAdviceChecked(isChecked);
                        socialQuestionSetData.setSocialAdviceDesc(description);
                    } else if (checkBoxSelected.equals(getString(R.string.advocacy))) {
                        socialQuestionSetData.setSocialAdvocacyChecked(isChecked);
                        socialQuestionSetData.setSocialAdvocacyDesc(description);
                    } else if (checkBoxSelected.equals(getString(R.string.referral_to_other_org))) {
                        socialQuestionSetData.setSocialReferralChecked(isChecked);
                        socialQuestionSetData.setSocialReferralDesc(description);
                    } else if (checkBoxSelected.equals(getString(R.string.encouragement))) {
                        socialQuestionSetData.setSocialEncouragementChecked(isChecked);
                        socialQuestionSetData.setSocialEncouragementDesc(description);
                    }
                }
                if (questionDataContainer instanceof RadioGroupViewContainer) {
                    String selectedItem = ((RadioGroupViewContainer) questionDataContainer)
                            .getCheckedItem().getDescription();
                    socialQuestionSetData.setSocialGoalStatus(selectedItem);
                    if (selectedItem.equalsIgnoreCase(getString(R.string.concluded))) {
                        socialQuestionSetData.setGoalConcluded(true);
                        socialQuestionSetData.setGoalCancelled(false);
                        socialQuestionSetData.setGoalOngoing(false);
                    } else if (selectedItem.equalsIgnoreCase(getString(R.string.cancelled))) {
                        socialQuestionSetData.setGoalCancelled(true);
                        socialQuestionSetData.setGoalConcluded(false);
                        socialQuestionSetData.setGoalOngoing(false);
                    } else {
                        socialQuestionSetData.setGoalOngoing(true);
                        socialQuestionSetData.setGoalConcluded(false);
                        socialQuestionSetData.setGoalCancelled(false);
                    }
                }
                if (questionDataContainer instanceof EditTextViewContainer) {
                    String description = ((EditTextViewContainer) questionDataContainer).getUserInput();
                    socialQuestionSetData.setSocialOutcomeDesc(description);
                }
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
