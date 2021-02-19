package com.example.cbr.models;

public class VisitEducationQuestionSetData {

    private Integer clientId;
    private Integer visitId;

    private boolean isEducationAdviceChecked;
    private boolean isEducationAdvocacyChecked;
    private boolean isEducationRefChecked;
    private boolean isEducationEncouragementChecked;
    private String educationAdviceDesc;
    private String educationAdvocacyDesc;
    private String educationRefDesc;
    private String educationEncouragementDesc;
    private String educationOutcomeDesc;
    private String educationGoalStatus;

    public VisitEducationQuestionSetData() {
        this.educationAdviceDesc = "";
        this.educationAdvocacyDesc = "";
        this.educationRefDesc = "";
        this.educationEncouragementDesc = "";
        this.educationOutcomeDesc = "";
        this.educationGoalStatus = "";
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

    public boolean isEducationRefChecked() {
        return isEducationRefChecked;
    }

    public void setEducationRefChecked(boolean educationRefChecked) {
        isEducationRefChecked = educationRefChecked;
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

    public String getEducationRefDesc() {
        return educationRefDesc;
    }

    public void setEducationRefDesc(String educationRefDesc) {
        this.educationRefDesc = educationRefDesc;
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
