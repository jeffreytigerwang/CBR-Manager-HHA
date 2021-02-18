package com.example.cbr.models;

public class VisitHealthQuestionSetData {

    private boolean isWheelChairChecked;
    private boolean isProstheticChecked;
    private boolean isOrthoticChecked;
    private boolean isWheelChairRepairChecked;
    private boolean isReferralToHCChecked;
    private boolean isHealthAdviceChecked;
    private boolean isHealthAdvocacyChecked;
    private boolean isHealthEncouragementChecked;
    private String wheelChairDesc;
    private String prostheticDesc;
    private String orthoticDesc;
    private String wheelchairRepairDesc;
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
        this.wheelchairRepairDesc = "";
        this.referralToHCDesc = "";
        this.healthAdviceDesc = "";
        this.healthAdvocacyDesc = "";
        this.healthEncouragementDesc = "";
        this.healthOutcomeDesc = "";
        this.healthGoalStatus = "";
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

    public String getWheelchairRepairDesc() {
        return wheelchairRepairDesc;
    }

    public void setWheelchairRepairDesc(String wheelchairRepairDesc) {
        this.wheelchairRepairDesc = wheelchairRepairDesc;
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

    public String getHealthGoalStatus() {
        return healthGoalStatus;
    }

    public void setHealthGoalStatus(String healthGoalStatus) {
        this.healthGoalStatus = healthGoalStatus;
    }
}