package com.example.cbr.models;

import java.util.ArrayList;
import java.util.List;

/*
* Data container for VisitFourthQuestionSetFragment
* */

public class VisitSocialQuestionSetData {

    private Integer clientId;
    private Integer visitId;

    private boolean isSocialAdviceChecked;
    private boolean isSocialAdvocacyChecked;
    private boolean isSocialReferralChecked;
    private boolean isSocialEncouragementChecked;
    private boolean isGoalCancelled;
    private boolean isGoalOngoing;
    private boolean isGoalConcluded;
    private String socialAdviceDesc;
    private String socialAdvocacyDesc;
    private String socialReferralDesc;
    private String socialEncouragementDesc;
    private String socialOutcomeDesc;
    private String socialGoalStatus;

    public VisitSocialQuestionSetData() {
        this.socialAdviceDesc = "";
        this.socialAdvocacyDesc = "";
        this.socialReferralDesc = "";
        this.socialEncouragementDesc = "";
        this.socialOutcomeDesc = "";
    }

    public List<String> getEmptyQuestions() {
        List<String> questionNumbers = new ArrayList<>();

        boolean isQuestionFourteenChecked = isSocialAdviceChecked
                || isSocialAdvocacyChecked
                || isSocialReferralChecked
                || isSocialEncouragementChecked;
        boolean isQuestionFifteenChecked = isGoalCancelled || isGoalConcluded || isGoalOngoing;

        boolean isSocialAdviceEmpty = socialAdviceDesc.isEmpty();
        boolean isSocialAdvocacyDescEmpty = socialAdvocacyDesc.isEmpty();
        boolean isSocialReferralDescEmpty = socialReferralDesc.isEmpty();
        boolean isSocialEncouragementDescEmpty = socialEncouragementDesc.isEmpty();

        boolean missingDesc = (isSocialAdviceChecked && isSocialAdviceEmpty)
                || (isSocialAdvocacyChecked && isSocialAdvocacyDescEmpty)
                || (isSocialReferralChecked && isSocialReferralDescEmpty)
                || (isSocialEncouragementChecked && isSocialEncouragementDescEmpty);

        if (!isQuestionFourteenChecked) {
            questionNumbers.add("14.");
        }
        if (missingDesc) {
            questionNumbers.add("14.Desc");
        }
        if (!isQuestionFifteenChecked) {
            questionNumbers.add("15.");
        }
        return questionNumbers;
    }

    public void resetData() {
        isSocialAdviceChecked = false;
        isSocialAdvocacyChecked = false;
        isSocialReferralChecked = false;
        isSocialEncouragementChecked = false;
        isGoalCancelled = false;
        isGoalOngoing = false;
        isGoalConcluded = false;
        socialAdviceDesc = "";
        socialAdvocacyDesc = "";
        socialReferralDesc = "";
        socialEncouragementDesc = "";
        socialOutcomeDesc = "";
        socialGoalStatus = "";
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

    public boolean isSocialReferralChecked() {
        return isSocialReferralChecked;
    }

    public void setSocialReferralChecked(boolean socialReferralChecked) {
        isSocialReferralChecked = socialReferralChecked;
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

    public String getSocialReferralDesc() {
        return socialReferralDesc;
    }

    public void setSocialReferralDesc(String socialReferralDesc) {
        this.socialReferralDesc = socialReferralDesc;
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

    public String getSocialGoalStatus() {
        return socialGoalStatus;
    }

    public void setSocialGoalStatus(String socialGoalStatus) {
        this.socialGoalStatus = socialGoalStatus;
    }
}
