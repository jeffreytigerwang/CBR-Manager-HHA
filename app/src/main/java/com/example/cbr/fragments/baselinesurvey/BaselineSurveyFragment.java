package com.example.cbr.fragments.baselinesurvey;

import com.example.cbr.databinding.FragmentBaselinesurveyBinding;
import com.example.cbr.fragments.base.BaseFragment;

public class BaselineSurveyFragment extends BaseFragment implements BaselineSurveyContract.View {
    private FragmentBaselinesurveyBinding binding;
    private BaselineSurveyContract.Presenter presenter;

    @Override
    public void setPresenter(BaselineSurveyContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public static String getFragmentTag() {
        return BaselineSurveyFragment.class.getSimpleName();
    }
}
