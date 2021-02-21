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

        addTextViewType(context.getString(R.string.client_id), visitGeneralQuestionSetData.getClientId().toString());
        addTextViewType(context.getString(R.string.visit_id), visitGeneralQuestionSetData.getVisitId().toString());

        addDividerViewType();
        addTextViewType(context.getString(R.string.visit_purpose), visitGeneralQuestionSetData.getPurposeOfVisit());
        addTextViewType(context.getString(R.string.visit_date), visitGeneralQuestionSetData.getDateOfVisit().toString());
        addTextViewType(context.getString(R.string.worker_name), visitGeneralQuestionSetData.getWorkerName());
        addTextViewType(context.getString(R.string.gps_location), visitGeneralQuestionSetData.getVisitGpsLocation());
        addTextViewType(context.getString(R.string.village_number), visitGeneralQuestionSetData.getVillageNumber());
        addTextViewType(context.getString(R.string.zone_location), visitGeneralQuestionSetData.getVisitZoneLocation());
    }
}
