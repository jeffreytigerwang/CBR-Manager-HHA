package com.example.cbr.adapters;

import android.content.Context;

import com.example.cbr.R;
import com.example.cbr.models.VisitGeneralQuestionSetData;

public class VisitInfoAdapter extends BaseInfoAdapter {

    VisitGeneralQuestionSetData visitGeneralQuestionSetData;

    public VisitInfoAdapter(Context context, VisitGeneralQuestionSetData visitGeneralQuestionSetData) {
        super(context);
        this.visitGeneralQuestionSetData = visitGeneralQuestionSetData;

        generateList();
    }

    @Override
    void generateList() {
        addHeaderViewType(context.getString(R.string.visit_details));

        addDoubleTextViewType(context.getString(R.string.client_id), visitGeneralQuestionSetData.getClientId().toString());
        addDoubleTextViewType(context.getString(R.string.visit_id), visitGeneralQuestionSetData.getVisitId().toString());

        addDividerViewType();
        addDoubleTextViewType(context.getString(R.string.visit_purpose), visitGeneralQuestionSetData.getPurposeOfVisit());
        addDoubleTextViewType(context.getString(R.string.visit_date), visitGeneralQuestionSetData.getDateOfVisit().toString());
        addDoubleTextViewType(context.getString(R.string.worker_name), visitGeneralQuestionSetData.getWorkerName());
        addDoubleTextViewType(context.getString(R.string.gps_location), visitGeneralQuestionSetData.getVisitGpsLocation());
        addDoubleTextViewType(context.getString(R.string.village_number), visitGeneralQuestionSetData.getVillageNumber());
        addDoubleTextViewType(context.getString(R.string.zone_location), visitGeneralQuestionSetData.getVisitZoneLocation());
    }
}
