package com.example.cbr.fragments.newclient;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
import com.example.cbr.models.ClientInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewClientFragment extends BaseFragment implements NewClientContract.View {

    private FragmentNewclientBinding binding;
    private NewClientContract.Presenter newClientPresenter;

    private QuestionsFragmentPagerAdapter newClientFragmentAdapter;
    private ArrayList<QuestionsFragmentPagerAdapter.ViewPagerContainer> viewPagerContainerList = new ArrayList<>();
    ClientInfo clientInfo = new ClientInfo();

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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new NewClientPresenter(this));
        binding = FragmentNewclientBinding.inflate(inflater, container, false);

        setupViewPager();
        setupButtons();

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
                String questionText = ((CheckBoxViewContainer) questionDataContainer).getQuestionText();
                boolean isChecked = ((CheckBoxViewContainer) questionDataContainer).isChecked();

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
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(mainPageList, true, onViewPagerChangedListener));
    }

    private void generateBasicInfo() {
        ArrayList<QuestionDataContainer> basicInfoList = new ArrayList<>();
        basicInfoList.add(new HeaderViewContainer(getString(R.string.basic_info)));
        basicInfoList.add(new EditTextViewContainer(getString(R.string.first_name), "First name", InputType.TYPE_CLASS_TEXT));
        basicInfoList.add(new EditTextViewContainer(getString(R.string.last_name), "Last name", InputType.TYPE_CLASS_TEXT));
        basicInfoList.add(new EditTextViewContainer(getString(R.string.age), "Age", InputType.TYPE_CLASS_NUMBER));
        List<RadioGroupViewContainer.RadioGroupListItem> genderOptions = new ArrayList<>();
        genderOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.male), false, View.generateViewId()));
        genderOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.female), false, View.generateViewId()));
        basicInfoList.add(new RadioGroupViewContainer(getString(R.string.gender), true, genderOptions));
        basicInfoList.add(new EditTextViewContainer(getString(R.string.contact_number), "Contact number", InputType.TYPE_CLASS_TEXT));
        basicInfoList.add(new EditTextViewContainer(getString(R.string.date), "Date", InputType.TYPE_CLASS_DATETIME));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(basicInfoList, false, onViewPagerChangedListener));
    }

    private void generateLocationInfo() {
        ArrayList<QuestionDataContainer> locationInfoList = new ArrayList<>();
        locationInfoList.add(new HeaderViewContainer(getString(R.string.location_info)));
        locationInfoList.add(new EditTextViewContainer(getString(R.string.gps_location), "GPS location", InputType.TYPE_CLASS_TEXT));
        List<String> zoneOptions = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.zone_locations_array)));
        locationInfoList.add(new SpinnerViewContainer(getString(R.string.zone_location), zoneOptions));
        locationInfoList.add(new EditTextViewContainer(getString(R.string.village_number), "Village number", InputType.TYPE_CLASS_NUMBER));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(locationInfoList, false, onViewPagerChangedListener));
    }

    private void generateCaregiverInfo() {
        ArrayList<QuestionDataContainer> caregiverInfoList = new ArrayList<>();
        caregiverInfoList.add(new HeaderViewContainer(getString(R.string.caregiver_info)));
        caregiverInfoList.add(new CheckBoxViewContainer(getString(R.string.caregiver_present_for_interview)));
        caregiverInfoList.add(new EditTextViewContainer(getString(R.string.caregiver_first_name), "Caregiver first name", InputType.TYPE_CLASS_TEXT));
        caregiverInfoList.add(new EditTextViewContainer(getString(R.string.caregiver_last_name), "Caregiver last name", InputType.TYPE_CLASS_TEXT));
        caregiverInfoList.add(new EditTextViewContainer(getString(R.string.caregiver_contact_number), "Caregiver contact number", InputType.TYPE_CLASS_NUMBER));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
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

    private void generateDisabilityType() {
        ArrayList<QuestionDataContainer> disablityTypeList = new ArrayList<>();
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
        disablityTypeList.add(new EditTextViewContainer(getString(R.string.other_option), getString(R.string.other), InputType.TYPE_CLASS_TEXT));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(disablityTypeList, false, onViewPagerChangedListener));
    }

    private void generateHealth() {
        ArrayList<QuestionDataContainer>  healthList = new ArrayList<>();
        healthList.add(new HeaderViewContainer(getString(R.string.health)));
        List<String> ratingOptions = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.client_ratings_array)));
        healthList.add(new SpinnerViewContainer(getString(R.string.please_rate_how_you_consider_the_client_s_health_to_be), ratingOptions));
        healthList.add(new EditTextViewContainer(getString(R.string.please_describe_what_they_require), getString(R.string.please_describe_what_they_require), InputType.TYPE_CLASS_TEXT));
        healthList.add(new EditTextViewContainer(getString(R.string.set_individual_goal), getString(R.string.set_individual_goal), InputType.TYPE_CLASS_TEXT));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(healthList, false, onViewPagerChangedListener));
    }

    private void generateEducation() {
        ArrayList<QuestionDataContainer> educationList = new ArrayList<>();
        educationList.add(new HeaderViewContainer(getString(R.string.education)));
        List<String> clientRatings = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.client_ratings_array)));
        educationList.add(new SpinnerViewContainer(getString(R.string.please_rate_how_you_consider_the_client_s_education_status_to_be), clientRatings));
        educationList.add(new EditTextViewContainer(getString(R.string.please_describe_what_they_require), getString(R.string.please_describe_what_they_require), InputType.TYPE_CLASS_TEXT));
        educationList.add(new EditTextViewContainer(getString(R.string.set_individual_goal), getString(R.string.set_individual_goal), InputType.TYPE_CLASS_TEXT));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(educationList, false, onViewPagerChangedListener));
    }

    private void generateSocialStatus() {
        ArrayList<QuestionDataContainer> socialStatusList = new ArrayList<>();
        socialStatusList.add(new HeaderViewContainer(getString(R.string.social_status)));
        List<String> clientRatings = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.client_ratings_array)));
        socialStatusList.add(new SpinnerViewContainer(getString(R.string.please_rate_how_you_consider_the_client_s_social_status_to_be), clientRatings));
        socialStatusList.add(new EditTextViewContainer(getString(R.string.please_describe_what_they_require), getString(R.string.please_describe_what_they_require), InputType.TYPE_CLASS_TEXT));
        socialStatusList.add(new EditTextViewContainer(getString(R.string.set_individual_goal), getString(R.string.set_individual_goal), InputType.TYPE_CLASS_TEXT));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
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
                    getActivity().getSupportFragmentManager().popBackStack();
                    // TODO: Make API call to database
                    // TODO: Call error checking functions
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
        newClientPresenter = presenter;
    }

    public static NewClientFragment newInstance() {
        return new NewClientFragment();
    }

    public static String getFragmentTag() {
        return NewClientFragment.class.getSimpleName();
    }
}
