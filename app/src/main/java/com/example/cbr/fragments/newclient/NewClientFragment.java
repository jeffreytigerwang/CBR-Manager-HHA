package com.example.cbr.fragments.newclient;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.example.cbr.databinding.FragmentNewclientBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.models.ClientDisability;
import com.example.cbr.models.ClientEducationAspect;
import com.example.cbr.models.ClientHealthAspect;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientSocialAspect;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;
import com.example.cbr.util.StringsUtil;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewClientFragment extends BaseFragment implements NewClientContract.View {
    private FragmentNewclientBinding binding;
    private NewClientContract.Presenter presenter;

    private QuestionsFragmentPagerAdapter newClientFragmentAdapter;
    private ArrayList<QuestionsFragmentPagerAdapter.ViewPagerContainer> viewPagerContainerList = new ArrayList<>();

    private enum PAGES {
        MAIN,
        BASIC_INFO,
        LOCATION_INFO,
        CAREGIVER_INFO,
        PHOTO,
        DISABILITY_TYPE,
        HEALTH,
        EDUCATION,
        SOCIAL_STATUS
    }

    private static final String NEW_CLIENT_PAGE_BUNDLE = "newClientPageBundle";

    ClientInfo clientInfo = new ClientInfo();
    private int clientId;
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new NewClientPresenter(this, getContext()));
        binding = FragmentNewclientBinding.inflate(inflater, container, false);

        setupViewPager();
        setupButtons();

        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        return binding.getRoot();
    }

    private void setupViewPager() {
        generateViewPagerList();
        final ViewPager2 viewPager2 = binding.newClientPageViewPager;
        newClientFragmentAdapter = new QuestionsFragmentPagerAdapter(getActivity(), viewPagerContainerList);
        viewPager2.setAdapter(newClientFragmentAdapter);
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
        generateBasicInfo();
        generateLocationInfo();
        generateCaregiverInfo();
        generatePhoto();
        generateDisabilityType();
        generateHealth();
        generateEducation();
        generateSocialStatus();
    }

    private void generateMainPage() {
        final ArrayList<QuestionDataContainer> mainPageList = new ArrayList<>();
        mainPageList.add(new SingleTextViewContainer(getString(R.string.interview_consent), 20));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.consent_to_interview)));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof CheckBoxViewContainer) {
                    String questionText = ((CheckBoxViewContainer) questionDataContainer).getQuestionText();
                    boolean isChecked = ((CheckBoxViewContainer) questionDataContainer).isChecked();

                    clientInfo.setConsentToInterview(isChecked);

                    if (questionText.equals(getString(R.string.consent_to_interview))) {
                        setPageActive(PAGES.BASIC_INFO.ordinal(), isChecked);
                        setPageActive(PAGES.LOCATION_INFO.ordinal(), isChecked);
                        setPageActive(PAGES.CAREGIVER_INFO.ordinal(), isChecked);
                        setPageActive(PAGES.PHOTO.ordinal(), isChecked);
                        setPageActive(PAGES.DISABILITY_TYPE.ordinal(), isChecked);
                        setPageActive(PAGES.HEALTH.ordinal(), isChecked);
                        setPageActive(PAGES.EDUCATION.ordinal(), isChecked);
                        setPageActive(PAGES.SOCIAL_STATUS.ordinal(), isChecked);
                    }
                }
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(mainPageList, true, onViewPagerChangedListener));
    }

    private void generateBasicInfo() {
        final ArrayList<QuestionDataContainer> basicInfoList = new ArrayList<>();
        basicInfoList.add(new HeaderViewContainer(getString(R.string.basic_info)));
        basicInfoList.add(new EditTextViewContainer(getString(R.string.first_name), getString(R.string.first_name), InputType.TYPE_CLASS_TEXT));
        basicInfoList.add(new EditTextViewContainer(getString(R.string.last_name), getString(R.string.last_name), InputType.TYPE_CLASS_TEXT));
        basicInfoList.add(new EditTextViewContainer(getString(R.string.age), getString(R.string.age), InputType.TYPE_CLASS_NUMBER));
        List<RadioGroupViewContainer.RadioGroupListItem> genderOptions = new ArrayList<>();
        genderOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.male), false, View.generateViewId()));
        genderOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.female), false, View.generateViewId()));
        basicInfoList.add(new RadioGroupViewContainer(getString(R.string.gender), true, genderOptions));
        basicInfoList.add(new EditTextViewContainer(getString(R.string.contact_number), getString(R.string.contact_number), InputType.TYPE_CLASS_NUMBER));
        basicInfoList.add(new EditTextViewContainer(getString(R.string.date), getString(R.string.date), InputType.TYPE_CLASS_DATETIME));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof EditTextViewContainer) {
                    String questionText = ((EditTextViewContainer) basicInfoList.get(positionChanged)).getQuestionText();
                    String userInput = ((EditTextViewContainer) basicInfoList.get(positionChanged)).getUserInput();

                    if (questionText.equals(getString(R.string.first_name))) {
                        clientInfo.setFirstName(userInput);
                    } else if (questionText.equals(getString(R.string.last_name))) {
                        clientInfo.setLastName(userInput);
                    } else if (questionText.equals(getString(R.string.age))) {
                        // If the user enters a number then deletes it, the app will crash
                        if (userInput != null) {
                            if (userInput.equals("")) {
                                clientInfo.setAge(-1);
                            } else {
                                clientInfo.setAge(Integer.parseInt(userInput));
                            }
                        }
                    } else if (questionText.equals(getString(R.string.contact_number))) {
                        clientInfo.setContactNumber(userInput);
                    }
                }

                if (questionDataContainer instanceof RadioGroupViewContainer) {
                    String gender = ((RadioGroupViewContainer) questionDataContainer).getCheckedItem().getDescription();
                    clientInfo.setGender(gender);
                }

            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(basicInfoList, false, onViewPagerChangedListener));
    }

    private void generateLocationInfo() {
        final ArrayList<QuestionDataContainer> locationInfoList = new ArrayList<>();
        locationInfoList.add(new HeaderViewContainer(getString(R.string.location_info)));
        locationInfoList.add(new SingleTextViewContainer(getString(R.string.gps_location), 20));
        locationInfoList.add(new EditTextViewContainer(getString(R.string.latitude), getString(R.string.latitude), InputType.TYPE_CLASS_TEXT));
        locationInfoList.add(new EditTextViewContainer(getString(R.string.longitude), getString(R.string.longitude), InputType.TYPE_CLASS_TEXT));
        List<String> zoneOptions = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.zone_locations_array)));
        locationInfoList.add(new SpinnerViewContainer(getString(R.string.zone_location), zoneOptions));
        locationInfoList.add(new EditTextViewContainer(getString(R.string.village_number), getString(R.string.village_number), InputType.TYPE_CLASS_NUMBER));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof EditTextViewContainer) {
                    String questionText = ((EditTextViewContainer) locationInfoList.get(positionChanged)).getQuestionText();
                    String userInput = ((EditTextViewContainer) locationInfoList.get(positionChanged)).getUserInput();

                    if (questionText.equals(getString(R.string.latitude))) {
                        clientInfo.setGpsLatitude(userInput);
                    } else if (questionText.equals(getString(R.string.longitude))) {
                        clientInfo.setGpsLongitude((userInput));
                    } else {
                        if (userInput != null) {
                            if (userInput.equals("")) {
                                clientInfo.setVillageNumber(-1);
                            } else {
                                clientInfo.setVillageNumber(Integer.parseInt(userInput));
                            }
                        }
                    }

                }

                if (questionDataContainer instanceof SpinnerViewContainer) {
                    String selectedZone = ((SpinnerViewContainer) questionDataContainer).getSelectedItem();
                    clientInfo.setZoneLocation(selectedZone);
                }
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(locationInfoList, false, onViewPagerChangedListener));
    }

    private void generateCaregiverInfo() {
        final ArrayList<QuestionDataContainer> caregiverInfoList = new ArrayList<>();
        caregiverInfoList.add(new HeaderViewContainer(getString(R.string.caregiver_info)));
        caregiverInfoList.add(new CheckBoxViewContainer(getString(R.string.caregiver_present_for_interview)));
        caregiverInfoList.add(new EditTextViewContainer(getString(R.string.caregiver_first_name), getString(R.string.caregiver_first_name), InputType.TYPE_CLASS_TEXT));
        caregiverInfoList.add(new EditTextViewContainer(getString(R.string.caregiver_last_name), getString(R.string.caregiver_last_name), InputType.TYPE_CLASS_TEXT));
        caregiverInfoList.add(new EditTextViewContainer(getString(R.string.caregiver_contact_number), getString(R.string.caregiver_contact_number), InputType.TYPE_CLASS_NUMBER));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof EditTextViewContainer) {
                    String questionText = ((EditTextViewContainer) caregiverInfoList.get(positionChanged)).getQuestionText();
                    String userInput = ((EditTextViewContainer) caregiverInfoList.get(positionChanged)).getUserInput();

                    if (questionText.equals(getString(R.string.caregiver_first_name))) {
                        clientInfo.setCaregiverFirstName(userInput);
                    } else if (questionText.equals(getString(R.string.caregiver_last_name))) {
                        clientInfo.setCaregiverLastName(userInput);
                    } else if (questionText.equals(getString(R.string.caregiver_contact_number))) {
                        clientInfo.setCaregiverContactNumber(userInput);
                    }
                }

                if (questionDataContainer instanceof CheckBoxViewContainer) {
                    boolean caregiverPresent = ((CheckBoxViewContainer) questionDataContainer).isChecked();
                    clientInfo.setCaregiverPresentForInterview(caregiverPresent);
                }

            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(caregiverInfoList, false, onViewPagerChangedListener));
    }

    private void generatePhoto() {
        ArrayList<QuestionDataContainer> photoList = new ArrayList<>();
        photoList.add(new HeaderViewContainer(getString(R.string.photo)));
        photoList.add(new SingleTextViewContainer(getString(R.string.you_must_allow_this_app_to_use_your_camera_to_record_a_photo), 20));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(photoList, false, onViewPagerChangedListener));
    }

    private final void generateDisabilityType() {
        final ArrayList<QuestionDataContainer> disablityTypeList = new ArrayList<>();
        disablityTypeList.add(new HeaderViewContainer(getString(R.string.type_of_disability)));
        disablityTypeList.add(new CheckBoxViewContainer(getString(R.string.amputee)));
        disablityTypeList.add(new CheckBoxViewContainer(getString(R.string.polio)));
        disablityTypeList.add(new CheckBoxViewContainer(getString(R.string.spinal_cord_injury)));
        disablityTypeList.add(new CheckBoxViewContainer(getString(R.string.cerebral_palsy)));
        disablityTypeList.add(new CheckBoxViewContainer(getString(R.string.spina_bifida)));
        disablityTypeList.add(new CheckBoxViewContainer(getString(R.string.hydrocephalus)));
        disablityTypeList.add(new CheckBoxViewContainer(getString(R.string.visual_impairment)));
        disablityTypeList.add(new CheckBoxViewContainer(getString(R.string.hearing_impairment)));
        disablityTypeList.add(new CheckBoxViewContainer(getString(R.string.don_t_know)));
        disablityTypeList.add(new CheckBoxViewContainer(getString(R.string.other)));
        disablityTypeList.add(new EditTextViewContainer(getString(R.string.other_option), getString(R.string.other), InputType.TYPE_CLASS_TEXT));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof CheckBoxViewContainer) {
                    String questionText = ((CheckBoxViewContainer) questionDataContainer).getQuestionText();
                    boolean isChecked = ((CheckBoxViewContainer) questionDataContainer).isChecked();

                    if (questionText.equals(getString(R.string.amputee))) {
                        clientInfo.setAmputeeDisability(isChecked);
                    } else if (questionText.equals(getString(R.string.polio))) {
                        clientInfo.setPolioDisability(isChecked);
                    } else if (questionText.equals(getString(R.string.spinal_cord_injury))) {
                        clientInfo.setSpinalCordInjuryDisability(isChecked);
                    } else if (questionText.equals(getString(R.string.cerebral_palsy))) {
                        clientInfo.setCerebralPalsyDisability(isChecked);
                    } else if (questionText.equals(getString(R.string.spina_bifida))) {
                        clientInfo.setSpinaBifidaDisability(isChecked);
                    } else if (questionText.equals(getString(R.string.hydrocephalus))) {
                        clientInfo.setHydrocephalusDisability(isChecked);
                    } else if (questionText.equals(getString(R.string.visual_impairment))) {
                        clientInfo.setVisualImpairmentDisability(isChecked);
                    } else if (questionText.equals(getString(R.string.hearing_impairment))) {
                        clientInfo.setHearingImpairmentDisability(isChecked);
                    } else if (questionText.equals(getString(R.string.don_t_know))) {
                        clientInfo.setDoNotKnowDisability(isChecked);
                    } else if (questionText.equals(getString(R.string.other))) {
                        clientInfo.setOtherDisability(isChecked);
                    }
                }

                if (questionDataContainer instanceof EditTextViewContainer) {
                    String questionText = ((EditTextViewContainer) disablityTypeList.get(positionChanged)).getQuestionText();
                    String userInput = ((EditTextViewContainer) disablityTypeList.get(positionChanged)).getUserInput();

                    if (questionText.equals(getString(R.string.other_option))) {
                        clientInfo.setDescribeOtherDisability(userInput);
                    }
                }
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(disablityTypeList, false, onViewPagerChangedListener));
    }

    private void generateHealth() {
        final ArrayList<QuestionDataContainer>  healthList = new ArrayList<>();
        healthList.add(new HeaderViewContainer(getString(R.string.health)));
        List<String> ratingOptions = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.client_ratings_array)));
        healthList.add(new SpinnerViewContainer(getString(R.string.please_rate_how_you_consider_the_client_s_health_to_be), ratingOptions));
        healthList.add(new EditTextViewContainer(getString(R.string.please_describe_what_they_require), getString(R.string.please_describe_what_they_require), InputType.TYPE_CLASS_TEXT));
        healthList.add(new EditTextViewContainer(getString(R.string.set_individual_goal), getString(R.string.set_individual_goal), InputType.TYPE_CLASS_TEXT));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof SpinnerViewContainer) {
                    String healthRating = ((SpinnerViewContainer) questionDataContainer).getSelectedItem();
                    clientInfo.setRateHealth(healthRating);
                }

                if (questionDataContainer instanceof EditTextViewContainer) {
                    String questionText = ((EditTextViewContainer) healthList.get(positionChanged)).getQuestionText();
                    String userInput = ((EditTextViewContainer) healthList.get(positionChanged)).getUserInput();

                    if (questionText.equals(getString(R.string.please_describe_what_they_require))) {
                        clientInfo.setDescribeHealth(userInput);
                    } else if (questionText.equals(getString(R.string.set_individual_goal))) {
                        clientInfo.setSetGoalForHealth(userInput);
                    }
                }
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(healthList, false, onViewPagerChangedListener));
    }

    private void generateEducation() {
        final ArrayList<QuestionDataContainer> educationList = new ArrayList<>();
        educationList.add(new HeaderViewContainer(getString(R.string.education)));
        List<String> clientRatings = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.client_ratings_array)));
        educationList.add(new SpinnerViewContainer(getString(R.string.please_rate_how_you_consider_the_client_s_education_status_to_be), clientRatings));
        educationList.add(new EditTextViewContainer(getString(R.string.please_describe_what_they_require), getString(R.string.please_describe_what_they_require), InputType.TYPE_CLASS_TEXT));
        educationList.add(new EditTextViewContainer(getString(R.string.set_individual_goal), getString(R.string.set_individual_goal), InputType.TYPE_CLASS_TEXT));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof SpinnerViewContainer) {
                    String educationRating = ((SpinnerViewContainer) questionDataContainer).getSelectedItem();
                    clientInfo.setRateEducation(educationRating);
                }

                if (questionDataContainer instanceof EditTextViewContainer) {
                    String questionText = ((EditTextViewContainer) educationList.get(positionChanged)).getQuestionText();
                    String userInput = ((EditTextViewContainer) educationList.get(positionChanged)).getUserInput();

                    if (questionText.equals(getString(R.string.please_describe_what_they_require))) {
                        clientInfo.setDescribeEducation(userInput);
                    } else if (questionText.equals(getString(R.string.set_individual_goal))) {
                        clientInfo.setSetGoalForEducation(userInput);
                    }
                }
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(educationList, false, onViewPagerChangedListener));
    }

    private void generateSocialStatus() {
        final ArrayList<QuestionDataContainer> socialStatusList = new ArrayList<>();
        socialStatusList.add(new HeaderViewContainer(getString(R.string.social_status)));
        List<String> clientRatings = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.client_ratings_array)));
        socialStatusList.add(new SpinnerViewContainer(getString(R.string.please_rate_how_you_consider_the_client_s_social_status_to_be), clientRatings));
        socialStatusList.add(new EditTextViewContainer(getString(R.string.please_describe_what_they_require), getString(R.string.please_describe_what_they_require), InputType.TYPE_CLASS_TEXT));
        socialStatusList.add(new EditTextViewContainer(getString(R.string.set_individual_goal), getString(R.string.set_individual_goal), InputType.TYPE_CLASS_TEXT));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof SpinnerViewContainer) {
                    String socialStatusRating = ((SpinnerViewContainer) questionDataContainer).getSelectedItem();
                    clientInfo.setRateSocialStatus(socialStatusRating);
                }

                if (questionDataContainer instanceof EditTextViewContainer) {
                    String questionText = ((EditTextViewContainer) socialStatusList.get(positionChanged)).getQuestionText();
                    String userInput = ((EditTextViewContainer) socialStatusList.get(positionChanged)).getUserInput();

                    if (questionText.equals(getString(R.string.please_describe_what_they_require))) {
                        clientInfo.setDescribeSocialStatus(userInput);
                    } else if (questionText.equals(getString(R.string.set_individual_goal))) {
                        clientInfo.setSetGoalForSocialStatus(userInput);
                    }
                }
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(socialStatusList, false, onViewPagerChangedListener));
    }

    private void setPageActive(int page, boolean isActive) {
        newClientFragmentAdapter.setPageActive(page, isActive);
        updateDisplayInfo(binding.newClientPageViewPager.getCurrentItem());
    }

    private void setupButtons() {
        binding.newClientPagePositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.newClientPageViewPager.getCurrentItem() == newClientFragmentAdapter.getItemCount() - 1) {
                    boolean isAllFilled = isAllRequiredQuestionsFilled();

                    if (isAllFilled) {
                        recordAndFinish();
                        Toast.makeText(getContext(), R.string.client_record_success, Toast.LENGTH_SHORT).show();
//                         getActivity().getSupportFragmentManager().popBackStack();
                    }
                }
                binding.newClientPageViewPager.setCurrentItem(binding.newClientPageViewPager.getCurrentItem() + 1);
            }
        });

        binding.newClientPageNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.newClientPageViewPager.getCurrentItem() == PAGES.MAIN.ordinal()) {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
                binding.newClientPageViewPager.setCurrentItem(binding.newClientPageViewPager.getCurrentItem() - 1);
            }
        });
    }

    private void recordAndFinish() {
        clientId = ThreadLocalRandom.current().nextInt(100000000, 999999999);
        clientInfo.setClientId(clientId);

        ClientDisability clientDisability = new ClientDisability(
            clientId,
            clientInfo.getAmputeeDisability(),
            clientInfo.getPolioDisability(),
            clientInfo.getSpinalCordInjuryDisability(),
            clientInfo.getCerebralPalsyDisability(),
            clientInfo.getSpinaBifidaDisability(),
            clientInfo.getHydrocephalusDisability(),
            clientInfo.getVisualImpairmentDisability(),
            clientInfo.getHearingImpairmentDisability(),
            clientInfo.getDoNotKnowDisability(),
            clientInfo.getOtherDisability(),
            clientInfo.getDescribeOtherDisability()
        );

        ClientHealthAspect clientHealthAspect = new ClientHealthAspect(
                clientId,
                clientInfo.getRateHealth(),
                clientInfo.getDescribeHealth(),
                clientInfo.getSetGoalForHealth()
        );

        ClientEducationAspect clientEducationAspect = new ClientEducationAspect(
                clientId,
                clientInfo.getRateEducation(),
                clientInfo.getDescribeEducation(),
                clientInfo.getSetGoalForEducation()
        );

        ClientSocialAspect clientSocialAspect = new ClientSocialAspect(
                clientId,
                clientInfo.getRateSocialStatus(),
                clientInfo.getDescribeSocialStatus(),
                clientInfo.getSetGoalForSocialStatus()
        );

        presenter.createClientInfo(clientInfo);
        presenter.createClientDisability(clientDisability);
        presenter.createClientHealthAspect(clientHealthAspect);
        presenter.createClientEducationAspect(clientEducationAspect);
        presenter.createClientSocialAspect(clientSocialAspect);
    }

    private boolean isAllRequiredQuestionsFilled() {
        final boolean isConsentChecked = clientInfo.getConsentToInterview();
        final String firstName = clientInfo.getFirstName();
        final String lastName = clientInfo.getLastName();
        final Integer age = clientInfo.getAge();
        final String gender = clientInfo.getGender();
        final String gpsLatitude = clientInfo.getGpsLatitude();
        final String gpsLongitude = clientInfo.getGpsLongitude();
        final Integer villageNumber = clientInfo.getVillageNumber();
        final boolean isCaregiverPresent = clientInfo.getCaregiverPresentForInterview();
        final String caregiverFirstName = clientInfo.getCaregiverFirstName();
        final String caregiverLastName = clientInfo.getCaregiverLastName();
        final boolean isOtherDisabilityChecked = clientInfo.getOtherDisability();
        final String describeOtherDisability = clientInfo.getDescribeOtherDisability();
        final String describeHealth = clientInfo.getDescribeHealth();
        final String healthGoal = clientInfo.getSetGoalForHealth();
        final String describeEducation = clientInfo.getDescribeEducation();
        final String educationGoal = clientInfo.getSetGoalForEducation();
        final String describeSocialStatus = clientInfo.getDescribeSocialStatus();
        final String socialStatusGoal = clientInfo.getSetGoalForSocialStatus();

        // Error checking for the consent page
        if (!isConsentChecked) {
            showOkDialog(getString(R.string.missing_fields), getString(R.string.must_consent_to_interview), null);
            return false;
        }

        // Error checking for the Basic Info page
        if (isStringFieldNull(firstName, getString(R.string.first_name_cannot_be_empty)) ||
            isStringFieldNull(lastName, getString(R.string.last_name_cannot_be_empty)))
        {
            return false;
        }

        if (age == -1) {
            showOkDialog(getString(R.string.missing_fields), getString(R.string.age_cannot_be_empty), null);
            return false;
        }

        if (isStringFieldNull(gender, getString(R.string.gender_cannot_be_empty))) {
            return false;
        }

        // Error checking for the Location Info page
        if (gpsLatitude != null || gpsLongitude != null) {
            if (isStringFieldNull(gpsLatitude, getString(R.string.latitude_cannot_be_empty)) ||
                isStringFieldNull(gpsLongitude, getString(R.string.longitude_cannot_be_empty)) ||
                isInvalidGpsCoordinate(gpsLatitude, getString(R.string.latitude_not_entered_properly)) ||
                isInvalidGpsCoordinate(gpsLongitude, getString(R.string.longitude_not_entered_properly)))
            {
                return false;
            }

            clientInfo.setGpsLocation(gpsLatitude + "," + gpsLongitude);
            System.out.println(clientInfo.getGpsLocation());
            return true;
        }

        if (villageNumber == null) {
            showOkDialog(getString(R.string.missing_fields), getString(R.string.village_number_cannot_be_empty), null);
            return false;
        }

        // Error checking for the Caregiver Info page
        if (isCaregiverPresent) {
            if (isStringFieldNull(caregiverFirstName, getString(R.string.caregiver_first_name_cannot_be_empty)) ||
                isStringFieldNull(caregiverLastName, getString(R.string.caregiver_last_name_cannot_be_empty)))
            {
                return false;
            }
        }

        // Error checking for the Type of Disability page
        if (isOtherDisabilityChecked) {
            if (isStringFieldNull(describeOtherDisability, getString(R.string.other_disability_description_cannot_be_empty))) {
                return false;
            }
        }

        // Error checking for the Health page
        if (isStringFieldNull(describeHealth, getString(R.string.health_description_cannot_be_empty)) ||
            isStringFieldNull(healthGoal, getString(R.string.health_goal_cannot_be_empty)))
        {
            return false;
        }

        // Error checking for the Education page
        if (isStringFieldNull(describeEducation, getString(R.string.education_description_cannot_be_empty)) ||
            isStringFieldNull(educationGoal, getString(R.string.education_goal_cannot_be_empty)))
        {
            return false;
        }

        // Error checking for the Social Status page
        if (isStringFieldNull(describeSocialStatus, getString(R.string.social_status_description_cannot_be_empty)) ||
            isStringFieldNull(socialStatusGoal, getString(R.string.social_status_goal_cannot_be_empty)))
        {
            return false;
        }

        return true;
    }

    private boolean isStringFieldNull(String field, String message) {
        if (field == null) {
            showOkDialog(getString(R.string.missing_fields), message, null);
            return true;
        }
        return false;
    }

    // Use Regex to see if the Gps coordinates entered by the user are valid
    // Example of valid Gps coordinates: 80.0123, 40, -34.034
    private boolean isInvalidGpsCoordinate(String coordinate, String message) {
        String gpsPattern = "^-?[0-9]{1,3}(?:\\.[0-9]{1,10})?$";
        Pattern p = Pattern.compile(gpsPattern);
        Matcher m = p.matcher(coordinate);

        if (m.find()) {
            return false;
        } else {
            showOkDialog(getString(R.string.invalid_gps_coordinate), message, null);
            return true;
        }
    }

    private void updateDisplayInfo(int currentPage) {
        int numTotalPage = newClientFragmentAdapter.getItemCount();
        binding.newClientPagePageNumberText.setText(getString(R.string.viewpager_page_number, currentPage + 1, numTotalPage));

        if (currentPage == PAGES.MAIN.ordinal()) {
            binding.newClientPageNegativeButton.setText(R.string.cancel);
        } else {
            binding.newClientPageNegativeButton.setText(R.string.back);
        }

        if (currentPage == numTotalPage - 1) {
            binding.newClientPagePositiveButton.setText(R.string.record);
        } else {
            binding.newClientPagePositiveButton.setText(R.string.next);
        }
    }

    @Override
    public void setPresenter(NewClientContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public static NewClientFragment newInstance() {
        return new NewClientFragment();
    }

    public static String getFragmentTag() {
        return NewClientFragment.class.getSimpleName();
    }
}
