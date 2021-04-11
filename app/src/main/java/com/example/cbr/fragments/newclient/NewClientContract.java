package com.example.cbr.fragments.newclient;

import com.example.cbr.fragments.base.BasePresenter;
import com.example.cbr.fragments.base.BaseView;
import com.example.cbr.models.ClientDisability;
import com.example.cbr.models.ClientEducationAspect;
import com.example.cbr.models.ClientHealthAspect;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientSocialAspect;

public interface NewClientContract {
    interface Presenter extends BasePresenter {
        void createClientInfo(ClientInfo clientInfo);
        void createClientDisability(ClientDisability clientDisability);
        void createClientHealthAspect(ClientHealthAspect clientHealthAspect);
        void createClientEducationAspect(ClientEducationAspect clientEducationAspect);
        void createClientSocialAspect(ClientSocialAspect clientSocialAspect);
    }

    interface View extends BaseView<Presenter> {

    }
}