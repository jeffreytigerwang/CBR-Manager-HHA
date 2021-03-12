package com.example.cbr.fragments.home;

import com.example.cbr.fragments.base.BasePresenter;
import com.example.cbr.fragments.base.BaseView;

public interface HomePageContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter>{
    }
}



