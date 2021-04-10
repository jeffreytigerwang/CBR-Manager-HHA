package com.example.cbr.fragments.discussion;

import com.example.cbr.fragments.base.BasePresenter;
import com.example.cbr.fragments.base.BaseView;

public interface DiscussionContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
    }
}
