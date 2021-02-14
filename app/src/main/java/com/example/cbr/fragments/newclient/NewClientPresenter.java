package com.example.cbr.fragments.newclient;

import com.example.cbr.util.Constants;

import java.util.ArrayList;

public class NewClientPresenter implements NewClientContract.Presenter {
    private NewClientContract.View newClientView;

    public NewClientPresenter(NewClientContract.View newClientView) {
        this.newClientView = newClientView;
    }

    @Override
    public void onButtonClicked() {
        newClientView.displayString("Sample string, maybe get this from model");
    }

    @Override
    public ArrayList<String> getLocationArray() {
        return Constants.ZONES;
    }
}
