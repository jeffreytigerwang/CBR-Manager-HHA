package com.example.cbr.fragments.clientlist;

import com.example.cbr.fragments.base.BasePresenter;
import com.example.cbr.fragments.base.BaseView;

public interface ClientListContract {

    // methods communicating from view to presenter
    interface Presenter extends BasePresenter {
        void onButtonClicked();
    }

    // methods communicating from presenter to view
    interface View extends BaseView<Presenter> {
        void displayString(String string);
    }
}
