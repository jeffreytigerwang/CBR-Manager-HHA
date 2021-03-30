package com.example.cbr.fragments.map;

import com.example.cbr.fragments.base.BasePresenter;
import com.example.cbr.fragments.base.BaseView;

public interface MapContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<MapContract.Presenter> {
    }
}
