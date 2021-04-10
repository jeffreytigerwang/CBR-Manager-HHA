package com.example.cbr.fragments.newvisit;

import com.example.cbr.fragments.base.BasePresenter;
import com.example.cbr.fragments.base.BaseView;
import com.example.cbr.models.VisitEducationQuestionSetData;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.models.VisitHealthQuestionSetData;
import com.example.cbr.models.VisitSocialQuestionSetData;

public interface NewVisitContract {
    interface Presenter extends BasePresenter {
        void createVisitGeneralQuestionSetData(
                VisitGeneralQuestionSetData visitGeneralQuestionSetData);
        void createVisitHealthQuestionSetData(
                VisitHealthQuestionSetData visitHealthQuestionSetData);
        void createVisitEducationQuestionSetData(
                VisitEducationQuestionSetData visitEducationQuestionSetData);
        void createVisitSocialQuestionSetData(
                VisitSocialQuestionSetData visitSocialQuestionSetData);
    }

    interface View extends BaseView<Presenter> {
    }
}
