package com.example.cbr.fragments.clientlist;

import com.example.cbr.fragments.base.BasePresenter;
import com.example.cbr.fragments.base.BaseView;

public interface ClientListContract {
    interface Presenter extends BasePresenter {
        void onButtonClicked();
    }

    interface View extends BaseView<Presenter> {
        void displayString(String string);
    }
}
