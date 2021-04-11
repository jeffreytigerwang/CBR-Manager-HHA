package com.example.cbr.fragments.newreferral;

import com.example.cbr.fragments.base.BasePresenter;
import com.example.cbr.fragments.base.BaseView;
import com.example.cbr.models.ReferralInfo;

public interface NewReferralContract {
    interface Presenter extends BasePresenter {
        void createReferralInfo(ReferralInfo referralInfo);
    }

    interface View extends BaseView<Presenter> {

    }
}
