package com.example.cbr.fragments.clientpage;

import com.example.cbr.fragments.base.BasePresenter;
import com.example.cbr.fragments.base.BaseView;

public interface ClientPageContract {

    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
    }
}
