package com.example.cbr.fragments.visitpage;

import com.example.cbr.fragments.base.BasePresenter;
import com.example.cbr.fragments.base.BaseView;
import com.example.cbr.models.VisitGeneralQuestionSetData;

import java.io.IOException;
import java.util.List;

public interface VisitPageContract {

    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
    }
}
