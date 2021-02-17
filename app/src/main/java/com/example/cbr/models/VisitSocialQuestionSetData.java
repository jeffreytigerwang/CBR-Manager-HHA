package com.example.cbr.models;

public class VisitSocialQuestionSetData {

    private boolean isSocialAdviceChecked;
    private boolean isSocialAdvocacyChecked;
    private boolean isSocialRefChecked;
    private boolean isSocialEncouragementChecked;
    private String socialAdviceDesc;
    private String socialAdvocacyDesc;
    private String socialRefDesc;
    private String socialEncouragementDesc;
    private String socialOutcomeDesc;
    private String socialGoalStatus;

    public VisitSocialQuestionSetData() {
        this.socialAdviceDesc = "";
        this.socialAdvocacyDesc = "";
        this.socialRefDesc = "";
        this.socialEncouragementDesc = "";
        this.socialOutcomeDesc = "";
        this.socialGoalStatus = "";
    }

    public boolean isSocialAdviceChecked() {
        return isSocialAdviceChecked;
    }

    public void setSocialAdviceChecked(boolean socialAdviceChecked) {
        isSocialAdviceChecked = socialAdviceChecked;
    }

    public boolean isSocialAdvocacyChecked() {
        return isSocialAdvocacyChecked;
    }

    public void setSocialAdvocacyChecked(boolean socialAdvocacyChecked) {
        isSocialAdvocacyChecked = socialAdvocacyChecked;
    }

    public boolean isSocialRefChecked() {
        return isSocialRefChecked;
    }

    public void setSocialRefChecked(boolean socialRefChecked) {
        isSocialRefChecked = socialRefChecked;
    }

    public boolean isSocialEncouragementChecked() {
        return isSocialEncouragementChecked;
    }

    public void setSocialEncouragementChecked(boolean socialEncouragementChecked) {
        isSocialEncouragementChecked = socialEncouragementChecked;
    }

    public String getSocialAdviceDesc() {
        return socialAdviceDesc;
    }

    public void setSocialAdviceDesc(String socialAdviceDesc) {
        this.socialAdviceDesc = socialAdviceDesc;
    }

    public String getSocialAdvocacyDesc() {
        return socialAdvocacyDesc;
    }

    public void setSocialAdvocacyDesc(String socialAdvocacyDesc) {
        this.socialAdvocacyDesc = socialAdvocacyDesc;
    }

    public String getSocialRefDesc() {
        return socialRefDesc;
    }

    public void setSocialRefDesc(String socialRefDesc) {
        this.socialRefDesc = socialRefDesc;
    }

    public String getSocialEncouragementDesc() {
        return socialEncouragementDesc;
    }

    public void setSocialEncouragementDesc(String socialEncouragementDesc) {
        this.socialEncouragementDesc = socialEncouragementDesc;
    }

    public String getSocialOutcomeDesc() {
        return socialOutcomeDesc;
    }

    public void setSocialOutcomeDesc(String socialOutcomeDesc) {
        this.socialOutcomeDesc = socialOutcomeDesc;
    }

    public String getSocialGoalStatus() {
        return socialGoalStatus;
    }

    public void setSocialGoalStatus(String socialGoalStatus) {
        this.socialGoalStatus = socialGoalStatus;
    }
}
