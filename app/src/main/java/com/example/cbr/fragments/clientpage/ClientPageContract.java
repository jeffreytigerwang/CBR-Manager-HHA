package com.example.cbr.fragments.clientpage;

import com.example.cbr.fragments.base.BasePresenter;
import com.example.cbr.fragments.base.BaseView;

public interface ClientPageContract {

    // methods communicating from view to presenter
    interface Presenter extends BasePresenter {
    }

    // methods communicating from presenter to view
    interface View extends BaseView<Presenter> {
    }
}
