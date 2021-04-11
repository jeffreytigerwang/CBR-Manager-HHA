package com.example.cbr.fragments.clientpage;

import com.example.cbr.fragments.base.BasePresenter;
import com.example.cbr.fragments.base.BaseView;
import com.example.cbr.models.ReferralInfo;
import com.example.cbr.models.VisitGeneralQuestionSetData;

import java.io.IOException;
import java.util.List;

public interface ClientPageContract {

    interface Presenter extends BasePresenter {
        List<VisitGeneralQuestionSetData> getVisits() throws IOException;
        List<ReferralInfo> getReferrals() throws IOException;
    }

    interface View extends BaseView<Presenter> {
    }
}
