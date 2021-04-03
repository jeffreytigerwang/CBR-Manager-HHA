package com.example.cbr.models;

import java.util.ArrayList;
import java.util.List;

/*
* Data container for VisitThirdQuestionSetFragment
* */

public class VisitEducationQuestionSetData {

    private Integer clientId;
    private Integer visitId;

    private boolean isEducationAdviceChecked;
    private boolean isEducationAdvocacyChecked;
    private boolean isEducationReferralChecked;
    private boolean isEducationEncouragementChecked;
    private boolean isGoalCancelled;
    private boolean isGoalOngoing;
    private boolean isGoalConcluded;
    private String educationAdviceDesc;
    private String educationAdvocacyDesc;
    private String educationReferralDesc;
    private String educationEncouragementDesc;
    private String educationOutcomeDesc;
    private String educationGoalStatus;

    public VisitEducationQuestionSetData() {
        this.educationAdviceDesc = "";
        this.educationAdvocacyDesc = "";
        this.educationReferralDesc = "";
        this.educationEncouragementDesc = "";
        this.educationOutcomeDesc = "";
    }

    public List<String> getEmptyQuestions() {
        List<String> questionNumbers = new ArrayList<>();

        boolean isQuestionElevenChecked = isEducationAdviceChecked
                || isEducationAdvocacyChecked
                || isEducationReferralChecked
                || isEducationEncouragementChecked;
        boolean isQuestionTwelveChecked = isGoalCancelled || isGoalConcluded || isGoalOngoing;

        boolean isEducationAdviceEmpty = educationAdviceDesc.isEmpty();
        boolean isEducationAdvocacyDescEmpty = educationAdvocacyDesc.isEmpty();
        boolean isEducationReferralDescEmpty = educationReferralDesc.isEmpty();
        boolean isEducationEncouragementDescEmpty = educationEncouragementDesc.isEmpty();

        boolean missingDesc = (isEducationAdviceChecked && isEducationAdviceEmpty)
                || (isEducationAdvocacyChecked && isEducationAdvocacyDescEmpty)
                || (isEducationReferralChecked && isEducationReferralDescEmpty)
                || (isEducationEncouragementChecked && isEducationEncouragementDescEmpty);

        if (!isQuestionElevenChecked) {
            questionNumbers.add("11.");
        } else if (missingDesc) {
            questionNumbers.add("11.");
        }
        if (!isQuestionTwelveChecked) {
            questionNumbers.add("12.");
        }
        return questionNumbers;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getVisitId() {
        return visitId;
    }

    public void setVisitId(Integer visitId) {
        this.visitId = visitId;
    }

    public boolean isEducationAdviceChecked() {
        return isEducationAdviceChecked;
    }

    public void setEducationAdviceChecked(boolean educationAdviceChecked) {
        isEducationAdviceChecked = educationAdviceChecked;
    }

    public boolean isEducationAdvocacyChecked() {
        return isEducationAdvocacyChecked;
    }

    public void setEducationAdvocacyChecked(boolean educationAdvocacyChecked) {
        isEducationAdvocacyChecked = educationAdvocacyChecked;
    }

    public boolean isEducationReferralChecked() {
        return isEducationReferralChecked;
    }

    public void setEducationReferralChecked(boolean educationReferralChecked) {
        isEducationReferralChecked = educationReferralChecked;
    }

    public boolean isEducationEncouragementChecked() {
        return isEducationEncouragementChecked;
    }

    public void setEducationEncouragementChecked(boolean educationEncouragementChecked) {
        isEducationEncouragementChecked = educationEncouragementChecked;
    }

    public String getEducationAdviceDesc() {
        return educationAdviceDesc;
    }

    public void setEducationAdviceDesc(String educationAdviceDesc) {
        this.educationAdviceDesc = educationAdviceDesc;
    }

    public String getEducationAdvocacyDesc() {
        return educationAdvocacyDesc;
    }

    public void setEducationAdvocacyDesc(String educationAdvocacyDesc) {
        this.educationAdvocacyDesc = educationAdvocacyDesc;
    }

    public String getEducationReferralDesc() {
        return educationReferralDesc;
    }

    public void setEducationReferralDesc(String educationReferralDesc) {
        this.educationReferralDesc = educationReferralDesc;
    }

    public String getEducationEncouragementDesc() {
        return educationEncouragementDesc;
    }

    public void setEducationEncouragementDesc(String educationEncouragementDesc) {
        this.educationEncouragementDesc = educationEncouragementDesc;
    }

    public String getEducationOutcomeDesc() {
        return educationOutcomeDesc;
    }

    public void setEducationOutcomeDesc(String educationOutcomeDesc) {
        this.educationOutcomeDesc = educationOutcomeDesc;
    }

    public boolean isGoalCancelled() {
        return isGoalCancelled;
    }

    public void setGoalCancelled(boolean goalCancelled) {
        isGoalCancelled = goalCancelled;
    }

    public boolean isGoalOngoing() {
        return isGoalOngoing;
    }

    public void setGoalOngoing(boolean goalOngoing) {
        isGoalOngoing = goalOngoing;
    }

    public boolean isGoalConcluded() {
        return isGoalConcluded;
    }

    public void setGoalConcluded(boolean goalConcluded) {
        isGoalConcluded = goalConcluded;
    }

    public String getEducationGoalStatus() {
        return educationGoalStatus;
    }

    public void setEducationGoalStatus(String educationGoalStatus) {
        this.educationGoalStatus = educationGoalStatus;
    }
}
