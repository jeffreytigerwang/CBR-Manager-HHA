package com.example.cbr.models;

public class BaselineLivelihoodSurveyData {

    private Integer clientId;

    private boolean isWorking;
    private String occupation;
    private String employmentType;
    private boolean meetsFinancialNeeds;
    private boolean disabilityAffectsAbilityToWork;
    private boolean wantsToWork;

    public BaselineLivelihoodSurveyData() {
        this.isWorking = false;
        this.occupation = "";
        this.employmentType = "";
        this.meetsFinancialNeeds = false;
        this.disabilityAffectsAbilityToWork = false;
        this.wantsToWork = false;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public boolean isMeetsFinancialNeeds() {
        return meetsFinancialNeeds;
    }

    public void setMeetsFinancialNeeds(boolean meetsFinancialNeeds) {
        this.meetsFinancialNeeds = meetsFinancialNeeds;
    }

    public boolean isDisabilityAffectsAbilityToWork() {
        return disabilityAffectsAbilityToWork;
    }

    public void setDisabilityAffectsAbilityToWork(boolean disabilityAffectsAbilityToWork) {
        this.disabilityAffectsAbilityToWork = disabilityAffectsAbilityToWork;
    }

    public boolean isWantsToWork() {
        return wantsToWork;
    }

    public void setWantsToWork(boolean wantsToWork) {
        this.wantsToWork = wantsToWork;
    }
}
