package com.example.cbr.fragments;

import com.example.cbr.fragments.base.BasePresenter;
import com.example.cbr.fragments.base.BaseView;
import com.example.cbr.models.ClientInfo;

import java.io.IOException;
import java.util.List;

public interface DashboardPageContract {

    interface Presenter extends BasePresenter {
        List<ClientInfo> getTopPriority() throws IOException;
    }

    interface View extends BaseView<Presenter> {

    }
}
