package com.example.cbr.fragments.newreferral;

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
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.RecordPhotoViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.SingleTextViewContainer;
import com.example.cbr.databinding.FragmentQuestionspageBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ReferralInfo;
import com.example.cbr.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class NewReferralFragment extends BaseFragment implements NewReferralContract.View {

    private FragmentQuestionspageBinding binding;
    private NewReferralContract.Presenter clientListPresenter;

    private ClientInfo clientInfo;
    private QuestionsFragmentPagerAdapter questionsFragmentPagerAdapter;
    private ArrayList<QuestionsFragmentPagerAdapter.ViewPagerContainer> viewPagerContainerList = new ArrayList<>();
    ReferralInfo referralInfo = new ReferralInfo();

    private enum PAGES {
        MAIN,
        PHYSIOTHERAPY,
        PROSTHETIC,
        ORTHOTIC,
        WHEELCHAIR
    }

    private static final String NEW_REFERRAL_PAGE_BUNDLE = "newReferralPageBundle";

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new NewReferralPresenter(this));
        binding = FragmentQuestionspageBinding.inflate(inflater, container, false);

        clientInfo = (ClientInfo) getArguments().getSerializable(NEW_REFERRAL_PAGE_BUNDLE);

        setupViewPager();
        setupButtons();

        // referral need clientId to identify which client a referral belong to
        referralInfo.setClientId(clientInfo.getClientId());

        return binding.getRoot();
    }

    private void setupViewPager() {
        generateViewPagerList();
        final ViewPager2 viewPager2 = binding.questionsPageViewPager;
        questionsFragmentPagerAdapter = new QuestionsFragmentPagerAdapter(this, viewPagerContainerList);
        viewPager2.setAdapter(questionsFragmentPagerAdapter);
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

    private void updateDisplayInfo(int currentPage) {
        int numTotalPage = questionsFragmentPagerAdapter.getItemCount();
        binding.questionsPagePageNumberText.setText(getString(R.string.viewpager_page_number, currentPage + 1, numTotalPage));

        if (currentPage == PAGES.MAIN.ordinal()) {
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
                    clientListPresenter.createReferralInfo(referralInfo);
                }
                binding.questionsPageViewPager.setCurrentItem(binding.questionsPageViewPager.getCurrentItem() + 1);
            }
        });

        binding.questionsPageNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.questionsPageViewPager.getCurrentItem() == PAGES.MAIN.ordinal()) {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
                binding.questionsPageViewPager.setCurrentItem(binding.questionsPageViewPager.getCurrentItem() - 1);
            }
        });
    }

    private void generateViewPagerList() {
        generateMainPage();
        generatePhysiotherapy();
        generateProsthetic();
        generateOrthotic();
        generateWheelchair();
    }

    private void generateMainPage() {
        final ArrayList<QuestionDataContainer> mainPageList = new ArrayList<>();
        mainPageList.add(new SingleTextViewContainer(getString(R.string.service_requirements_question), 20));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.physiotherapy)));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.prosthetic)));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.orthotic)));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.wheelchair)));
        mainPageList.add(new CheckBoxViewContainer(getString(R.string.other)));
        mainPageList.add(new EditTextViewContainer(getString(R.string.other_option), Constants.PRIMARY_TEXT_SIZE_SP, "", getString(R.string.other), InputType.TYPE_CLASS_TEXT));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof CheckBoxViewContainer) {
                    String serviceRequired = ((CheckBoxViewContainer) questionDataContainer).getQuestionText();
                    boolean isChecked = ((CheckBoxViewContainer) questionDataContainer).isChecked();

                    if (serviceRequired.equals(getString(R.string.physiotherapy))) {
                        referralInfo.setRequirePhysiotherapy(isChecked);
                        setPageActive(PAGES.PHYSIOTHERAPY.ordinal(), isChecked);
                    } else if (serviceRequired.equals(getString(R.string.prosthetic))) {
                        referralInfo.setRequireProsthetic(isChecked);
                        setPageActive(PAGES.PROSTHETIC.ordinal(), isChecked);
                    } else if (serviceRequired.equals(getString(R.string.orthotic))) {
                        referralInfo.setRequireOrthotic(isChecked);
                        setPageActive(PAGES.ORTHOTIC.ordinal(), isChecked);
                    } else if (serviceRequired.equals(getString(R.string.wheelchair))) {
                        referralInfo.setRequireWheelchair(isChecked);
                        setPageActive(PAGES.WHEELCHAIR.ordinal(), isChecked);
                    } else if (serviceRequired.equals(getString(R.string.other))) {
                        referralInfo.setRequireOther(isChecked);
                    }
                }

                if (questionDataContainer instanceof EditTextViewContainer) {
                    String userInput = ((EditTextViewContainer) mainPageList.get(positionChanged)).getUserInput();
                    String option = ((EditTextViewContainer) mainPageList.get(positionChanged)).getQuestionText();

                    if (option.equals(getString(R.string.other_option))) {
                        referralInfo.setOtherDescription(userInput);
                    }
                }
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(mainPageList, true, onViewPagerChangedListener));
    }

    private void generatePhysiotherapy() {
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
        physioTherapyList.add(new EditTextViewContainer(getString(R.string.other_option), Constants.PRIMARY_TEXT_SIZE_SP, "", getString(R.string.other), InputType.TYPE_CLASS_TEXT));
        physioTherapyList.add(new RecordPhotoViewContainer(getString(R.string.new_referral_picture)));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof CheckBoxViewContainer) {
                    String disability = ((CheckBoxViewContainer) questionDataContainer).getQuestionText();
                    boolean isChecked = ((CheckBoxViewContainer) questionDataContainer).isChecked();

                    if (disability.equals(getString(R.string.amputee))) {
                        referralInfo.setAmputeeDisability(isChecked);
                    } else if (disability.equals(getString(R.string.polio))) {
                        referralInfo.setPolioDisability(isChecked);
                    } else if (disability.equals(getString(R.string.spinal_cord_injury))) {
                        referralInfo.setSpinalCordInjuryDisability(isChecked);
                    } else if (disability.equals(getString(R.string.cerebral_palsy))) {
                        referralInfo.setCerebralPalsyDisability(isChecked);
                    } else if (disability.equals(getString(R.string.spina_bifida))) {
                        referralInfo.setSpinaBifidaDisability(isChecked);
                    } else if (disability.equals(getString(R.string.hydrocephalus))) {
                        referralInfo.setHydrocephalusDisability(isChecked);
                    } else if (disability.equals(getString(R.string.visual_impairment))) {
                        referralInfo.setVisualImpairmentDisability(isChecked);
                    } else if (disability.equals(getString(R.string.hearing_impairment))) {
                        referralInfo.setHearingImpairmentDisability(isChecked);
                    } else if (disability.equals(getString(R.string.other))) {
                        referralInfo.setOtherDisability(isChecked);
                    }
                }

                //TODO add photo to database once it is working
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(physioTherapyList, false, onViewPagerChangedListener));
    }

    private void generateProsthetic() {
        ArrayList<QuestionDataContainer> prostheticList = new ArrayList<>();

        prostheticList.add(new HeaderViewContainer(getString(R.string.prosthetic)));
        List<RadioGroupViewContainer.RadioGroupListItem> prostheticOptions = new ArrayList<>();
        prostheticOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.above_knee_question), false, View.generateViewId()));
        prostheticOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.below_knee_question), false, View.generateViewId()));
        prostheticList.add(new RadioGroupViewContainer(getString(R.string.prosthetic_question), true, prostheticOptions));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof RadioGroupViewContainer) {
                   String description = ((RadioGroupViewContainer) questionDataContainer).getCheckedItem().getDescription();
                   boolean isChecked = ((RadioGroupViewContainer) questionDataContainer).getCheckedItem().isChecked();

                   if (description.equals(getString(R.string.above_knee_question))) {
                       referralInfo.setInjuryAboveKnee(isChecked);
                       referralInfo.setInjuryBelowKnee(!isChecked);
                   } else if (description.equals(getString(R.string.below_knee_question))) {
                       referralInfo.setInjuryBelowElbow(isChecked);
                       referralInfo.setInjuryAboveElbow(!isChecked);
                   }
                }
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(prostheticList, false, onViewPagerChangedListener));
    }

    private void generateOrthotic() {
        ArrayList<QuestionDataContainer> orthoticList = new ArrayList<>();

        orthoticList.add(new HeaderViewContainer(getString(R.string.orthotic)));
        List<RadioGroupViewContainer.RadioGroupListItem> orthoticOptions = new ArrayList<>();
        orthoticOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.above_elbow_question), false, View.generateViewId()));
        orthoticOptions.add(new RadioGroupViewContainer.RadioGroupListItem(getString(R.string.below_elbow_question), false, View.generateViewId()));
        orthoticList.add(new RadioGroupViewContainer(getString(R.string.orthotic_question), true, orthoticOptions));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof RadioGroupViewContainer) {
                    String description = ((RadioGroupViewContainer) questionDataContainer).getCheckedItem().getDescription();
                    boolean isChecked = ((RadioGroupViewContainer) questionDataContainer).getCheckedItem().isChecked();

                    if (description.equals(getString(R.string.above_elbow_question))) {
                        referralInfo.setInjuryAboveElbow(isChecked);
                        referralInfo.setInjuryBelowElbow(!isChecked);
                    } else if (description.equals(getString(R.string.below_elbow_question))) {
                        referralInfo.setInjuryBelowElbow(isChecked);
                        referralInfo.setInjuryAboveElbow(!isChecked);
                    }
                }
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

        wheelchair.add(new EditTextViewContainer("What is the client's hip width in inches?", Constants.PRIMARY_QUESTION_TEXT_SIZE_SP, "", "", InputType.TYPE_CLASS_NUMBER));

        QuestionsFragmentPagerAdapter.OnViewPagerChangedListener onViewPagerChangedListener = new QuestionsFragmentPagerAdapter.OnViewPagerChangedListener() {
            @Override
            public void onChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
                if (questionDataContainer instanceof RadioGroupViewContainer) {
                    String question = ((RadioGroupViewContainer) questionDataContainer).getQuestionText();
                    String description = ((RadioGroupViewContainer) questionDataContainer).getCheckedItem().getDescription();

                    if (question.equals(getString(R.string.wheelchair_expertise_question))) {
                        if (description.equals(getString(R.string.basic_user))) {
                            referralInfo.setIntermediateWheelchairUser(false);
                        } else if (description.equals(getString(R.string.intermediate_user))) {
                            referralInfo.setIntermediateWheelchairUser(true);
                        }
                    }

                    if (question.equals(getString(R.string.existing_wheelchair_question))) {
                        if (description.equals(getString(R.string.yes))) {
                            referralInfo.setHasExistingWheelchair(true);
                        } else if (description.equals(getString(R.string.no))) {
                            referralInfo.setHasExistingWheelchair(false);
                        }
                    }

                    if (question.equals(getString(R.string.wheelchair_repair_question))) {
                        if (description.equals(getString(R.string.yes))) {
                            referralInfo.setCanRepairWheelchair(true);
                        } else if (description.equals(getString(R.string.no))) {
                            referralInfo.setCanRepairWheelchair(false);
                        }
                    }
                }

                if (questionDataContainer instanceof EditTextViewContainer) {
                    Integer hipWidth = Integer.valueOf(((EditTextViewContainer) questionDataContainer).getUserInput());
                    referralInfo.setHipWidth(hipWidth);
                }
            }
        };

        viewPagerContainerList.add(new QuestionsFragmentPagerAdapter.ViewPagerContainer(wheelchair, false, onViewPagerChangedListener));
    }

    @Override
    public void setPresenter(NewReferralContract.Presenter presenter) {
        clientListPresenter = presenter;
    }


    public static NewReferralFragment newInstance(ClientInfo clientInfo) {
        NewReferralFragment newReferralFragment = new NewReferralFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(NEW_REFERRAL_PAGE_BUNDLE, clientInfo);
        newReferralFragment.setArguments(bundle);

        return newReferralFragment;
    }

    public static String getFragmentTag() {
        return NewReferralFragment.class.getSimpleName();
    }
}
