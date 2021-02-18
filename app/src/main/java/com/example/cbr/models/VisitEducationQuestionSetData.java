package com.example.cbr.models;

public class VisitEducationQuestionSetData {

    private boolean isEducationAdviceChecked;
    private boolean isEducationAdvocacyChecked;
    private boolean isEducationReferralChecked;
    private boolean isEducationEncouragementChecked;
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
        this.educationGoalStatus = "";
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

    public String getEducationGoalStatus() {
        return educationGoalStatus;
    }

    public void setEducationGoalStatus(String educationGoalStatus) {
        this.educationGoalStatus = educationGoalStatus;
    }
}
