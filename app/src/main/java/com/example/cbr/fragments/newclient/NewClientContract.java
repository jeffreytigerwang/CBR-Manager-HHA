package com.example.cbr.fragments.newclient;

import com.example.cbr.fragments.base.BasePresenter;
import com.example.cbr.fragments.base.BaseView;

import java.util.ArrayList;

public interface NewClientContract {
    interface Presenter extends BasePresenter {
        void onButtonClicked();
        ArrayList<String> getLocationArray();
    }

    interface View extends BaseView<Presenter> {


    }
}