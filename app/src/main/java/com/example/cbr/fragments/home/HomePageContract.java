package com.example.cbr.fragments.home;

import androidx.annotation.NonNull;

import com.example.cbr.fragments.base.BasePresenter;
import com.example.cbr.fragments.base.BaseView;
import com.example.cbr.models.ClientDisability;
import com.example.cbr.models.ClientEducationAspect;
import com.example.cbr.models.ClientHealthAspect;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientSocialAspect;
import com.example.cbr.models.ReferralInfo;
import com.example.cbr.models.VisitEducationQuestionSetData;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.models.VisitHealthQuestionSetData;
import com.example.cbr.models.VisitSocialQuestionSetData;

import java.io.IOException;
import java.util.List;

public interface HomePageContract {
    interface Presenter extends BasePresenter {
        List<ClientInfo> getAllClients() throws IOException;
        List<ClientDisability> getAllClientDisabilities() throws IOException;
        List<ClientEducationAspect> getAllClientEducationAspect() throws IOException;
        List<ClientHealthAspect> getAllClientHealthAspect() throws IOException;
        List<ClientSocialAspect> getAllClientSocialAspect() throws IOException;

        List<ReferralInfo> getAllReferrals() throws IOException;
        List<VisitEducationQuestionSetData> getAllVisitEducationQuestions() throws IOException;
        List<VisitGeneralQuestionSetData> getAllVisitGeneralQuestions() throws IOException;
        List<VisitHealthQuestionSetData> getAllVisitHealthQuestions() throws IOException;
        List<VisitSocialQuestionSetData> getAllVisitSocialQuestions() throws IOException;

         void createClientInfo(@NonNull ClientInfo clientInfo);
         void createVisitGeneralQuestionSetData(@NonNull VisitGeneralQuestionSetData visitGeneralQuestionSetData);
         void createVisitHealthQuestionSetData(@NonNull VisitHealthQuestionSetData visitHealthQuestionSetData);
         void createVisitEducationQuestionSetData(@NonNull VisitEducationQuestionSetData visitEducationQuestionSetData);
         void createVisitSocialQuestionSetData(@NonNull VisitSocialQuestionSetData visitSocialQuestionSetData);
         void createClientDisability(@NonNull ClientDisability clientDisability);
         void createClientHealthAspect(@NonNull ClientHealthAspect clientHealthAspect);
         void createClientEducationAspect(@NonNull ClientEducationAspect clientEducationAspect);
         void createClientSocialAspect(@NonNull ClientSocialAspect clientSocialAspect);
         void createReferralInfo(@NonNull ReferralInfo referralInfo);

    }

    interface View extends BaseView<Presenter>{
    }
}



