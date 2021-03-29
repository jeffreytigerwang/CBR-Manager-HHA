package com.example.cbr.fragments.notification;

import com.example.cbr.fragments.base.BasePresenter;
import com.example.cbr.fragments.base.BaseView;

public interface NotificationContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<NotificationContract.Presenter> {
    }
}
