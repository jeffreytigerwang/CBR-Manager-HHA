package com.example.cbr.fragments.newreferral;

import com.example.cbr.fragments.base.BasePresenter;

public class NewReferralPresenter implements NewReferralContract.Presenter {

    private NewReferralContract.View newReferralView;

    public NewReferralPresenter(NewReferralContract.View newReferralView) {
        this.newReferralView = newReferralView;
    }
}
