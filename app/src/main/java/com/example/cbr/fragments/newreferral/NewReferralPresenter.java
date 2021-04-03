package com.example.cbr.fragments.newreferral;

public class NewReferralPresenter implements NewReferralContract.Presenter {

    private NewReferralContract.View newReferralView;

    public NewReferralPresenter(NewReferralContract.View newReferralView) {
        this.newReferralView = newReferralView;
    }
}
