package com.example.cbr.models;

public class BaselineSocialSurveyData {

    private Integer clientId;

    private boolean feelsValued;
    private boolean feelsIndependent;
    private boolean ableToParticipateInEvents;
    private boolean disabilityAffectsAbilityToSocialize;
    private boolean hasExperiencedDiscrimination;

    public BaselineSocialSurveyData() {
        this.feelsValued = false;
        this.feelsIndependent = false;
        this.ableToParticipateInEvents = false;
        this.disabilityAffectsAbilityToSocialize = false;
        this.hasExperiencedDiscrimination = false;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public boolean isFeelsValued() {
        return feelsValued;
    }

    public void setFeelsValued(boolean feelsValued) {
        this.feelsValued = feelsValued;
    }

    public boolean isFeelsIndependent() {
        return feelsIndependent;
    }

    public void setFeelsIndependent(boolean feelsIndependent) {
        this.feelsIndependent = feelsIndependent;
    }

    public boolean isAbleToParticipateInEvents() {
        return ableToParticipateInEvents;
    }

    public void setAbleToParticipateInEvents(boolean ableToParticipateInEvents) {
        this.ableToParticipateInEvents = ableToParticipateInEvents;
    }

    public boolean isDisabilityAffectsAbilityToSocialize() {
        return disabilityAffectsAbilityToSocialize;
    }

    public void setDisabilityAffectsAbilityToSocialize(boolean disabilityAffectsAbilityToSocialize) {
        this.disabilityAffectsAbilityToSocialize = disabilityAffectsAbilityToSocialize;
    }

    public boolean isHasExperiencedDiscrimination() {
        return hasExperiencedDiscrimination;
    }

    public void setHasExperiencedDiscrimination(boolean hasExperiencedDiscrimination) {
        this.hasExperiencedDiscrimination = hasExperiencedDiscrimination;
    }
}
