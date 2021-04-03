package com.example.cbr.models;

import java.util.ArrayList;
import java.util.List;

/*
* Data container for VisitSecondQuestionSetFragment
* */

public class VisitHealthQuestionSetData {

    private Integer clientId;
    private Integer visitId;

    private boolean isWheelChairChecked;
    private boolean isProstheticChecked;
    private boolean isOrthoticChecked;
    private boolean isWheelChairRepairChecked;
    private boolean isReferralToHCChecked;
    private boolean isHealthAdviceChecked;
    private boolean isHealthAdvocacyChecked;
    private boolean isHealthEncouragementChecked;
    private boolean isGoalCancelled;
    private boolean isGoalOngoing;
    private boolean isGoalConcluded;
    private String wheelChairDesc;
    private String prostheticDesc;
    private String orthoticDesc;
    private String wheelChairRepairDesc;
    private String referralToHCDesc;
    private String healthAdviceDesc;
    private String healthAdvocacyDesc;
    private String healthEncouragementDesc;
    private String healthOutcomeDesc;
    private String healthGoalStatus;

    public VisitHealthQuestionSetData() {
        this.wheelChairDesc = "";
        this.prostheticDesc = "";
        this.orthoticDesc = "";
        this.wheelChairRepairDesc = "";
        this.referralToHCDesc = "";
        this.healthAdviceDesc = "";
        this.healthAdvocacyDesc = "";
        this.healthEncouragementDesc = "";
        this.healthOutcomeDesc = "";
    }

    public List<String> getEmptyQuestions() {
        List<String> questionNumbers = new ArrayList<>();

        boolean isQuestionEightChecked = isWheelChairChecked
                || isProstheticChecked
                || isOrthoticChecked
                || isWheelChairRepairChecked
                || isReferralToHCChecked
                || isHealthAdviceChecked
                || isHealthAdvocacyChecked
                || isHealthEncouragementChecked;
        boolean isQuestionNineChecked = isGoalCancelled || isGoalConcluded || isGoalOngoing;

        boolean isWheelChairDescEmpty = wheelChairDesc.isEmpty();
        boolean isProstheticDescEmpty = prostheticDesc.isEmpty();
        boolean isOrthoticDescEmpty = orthoticDesc.isEmpty();
        boolean isWheelChairRepairDescEmpty = wheelChairRepairDesc.isEmpty();
        boolean isReferralToHCDescEmpty = referralToHCDesc.isEmpty();
        boolean isHealthAdviceDescEmpty = healthAdviceDesc.isEmpty();
        boolean isHealthAdvocacyDescEmpty = healthAdvocacyDesc.isEmpty();
        boolean isHealthEncouragementDescEmpty = healthEncouragementDesc.isEmpty();

        boolean missingDesc = (isWheelChairChecked && isWheelChairDescEmpty)
                || (isProstheticChecked && isProstheticDescEmpty)
                || (isOrthoticChecked && isOrthoticDescEmpty)
                || (isWheelChairRepairChecked && isWheelChairRepairDescEmpty)
                || (isReferralToHCChecked && isReferralToHCDescEmpty)
                || (isHealthAdviceChecked && isHealthAdviceDescEmpty)
                || (isHealthAdvocacyChecked && isHealthAdvocacyDescEmpty)
                || (isHealthEncouragementChecked && isHealthEncouragementDescEmpty);

        if (!isQuestionEightChecked) {
            questionNumbers.add("8.");
        } else if (missingDesc) {
            questionNumbers.add("8.");
        }
        if (!isQuestionNineChecked) {
            questionNumbers.add("9.");
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

    public boolean isWheelChairChecked() {
        return isWheelChairChecked;
    }

    public void setWheelChairChecked(boolean wheelChairChecked) {
        isWheelChairChecked = wheelChairChecked;
    }

    public boolean isProstheticChecked() {
        return isProstheticChecked;
    }

    public void setProstheticChecked(boolean prostheticChecked) {
        isProstheticChecked = prostheticChecked;
    }

    public boolean isOrthoticChecked() {
        return isOrthoticChecked;
    }

    public void setOrthoticChecked(boolean orthoticChecked) {
        isOrthoticChecked = orthoticChecked;
    }

    public boolean isWheelChairRepairChecked() {
        return isWheelChairRepairChecked;
    }

    public void setWheelChairRepairChecked(boolean wheelChairRepairChecked) {
        isWheelChairRepairChecked = wheelChairRepairChecked;
    }

    public boolean isReferralToHCChecked() {
        return isReferralToHCChecked;
    }

    public void setReferralToHCChecked(boolean referralToHCChecked) {
        isReferralToHCChecked = referralToHCChecked;
    }

    public boolean isHealthAdviceChecked() {
        return isHealthAdviceChecked;
    }

    public void setHealthAdviceChecked(boolean healthAdviceChecked) {
        isHealthAdviceChecked = healthAdviceChecked;
    }

    public boolean isHealthAdvocacyChecked() {
        return isHealthAdvocacyChecked;
    }

    public void setHealthAdvocacyChecked(boolean healthAdvocacyChecked) {
        isHealthAdvocacyChecked = healthAdvocacyChecked;
    }

    public boolean isHealthEncouragementChecked() {
        return isHealthEncouragementChecked;
    }

    public void setHealthEncouragementChecked(boolean healthEncouragementChecked) {
        isHealthEncouragementChecked = healthEncouragementChecked;
    }

    public String getWheelChairDesc() {
        return wheelChairDesc;
    }

    public void setWheelChairDesc(String wheelChairDesc) {
        this.wheelChairDesc = wheelChairDesc;
    }

    public String getProstheticDesc() {
        return prostheticDesc;
    }

    public void setProstheticDesc(String prostheticDesc) {
        this.prostheticDesc = prostheticDesc;
    }

    public String getOrthoticDesc() {
        return orthoticDesc;
    }

    public void setOrthoticDesc(String orthoticDesc) {
        this.orthoticDesc = orthoticDesc;
    }

    public String getWheelChairRepairDesc() {
        return wheelChairRepairDesc;
    }

    public void setWheelChairRepairDesc(String wheelChairRepairDesc) {
        this.wheelChairRepairDesc = wheelChairRepairDesc;
    }

    public String getReferralToHCDesc() {
        return referralToHCDesc;
    }

    public void setReferralToHCDesc(String referralToHCDesc) {
        this.referralToHCDesc = referralToHCDesc;
    }

    public String getHealthAdviceDesc() {
        return healthAdviceDesc;
    }

    public void setHealthAdviceDesc(String healthAdviceDesc) {
        this.healthAdviceDesc = healthAdviceDesc;
    }

    public String getHealthAdvocacyDesc() {
        return healthAdvocacyDesc;
    }

    public void setHealthAdvocacyDesc(String healthAdvocacyDesc) {
        this.healthAdvocacyDesc = healthAdvocacyDesc;
    }

    public String getHealthEncouragementDesc() {
        return healthEncouragementDesc;
    }

    public void setHealthEncouragementDesc(String healthEncouragementDesc) {
        this.healthEncouragementDesc = healthEncouragementDesc;
    }

    public String getHealthOutcomeDesc() {
        return healthOutcomeDesc;
    }

    public void setHealthOutcomeDesc(String healthOutcomeDesc) {
        this.healthOutcomeDesc = healthOutcomeDesc;
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

    public String getHealthGoalStatus() {
        return healthGoalStatus;
    }

    public void setHealthGoalStatus(String healthGoalStatus) {
        this.healthGoalStatus = healthGoalStatus;
    }
}