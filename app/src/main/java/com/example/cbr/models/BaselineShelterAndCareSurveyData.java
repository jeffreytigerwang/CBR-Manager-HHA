package com.example.cbr.models;

public class BaselineShelterAndCareSurveyData {

    private Integer clientId;

    private boolean hasAdequateShelter;
    private boolean hasAccessToEssentialItems;

    public BaselineShelterAndCareSurveyData() {
        this.hasAdequateShelter = false;
        this.hasAccessToEssentialItems = false;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public boolean isHasAdequateShelter() {
        return hasAdequateShelter;
    }

    public void setHasAdequateShelter(boolean hasAdequateShelter) {
        this.hasAdequateShelter = hasAdequateShelter;
    }

    public boolean isHasAccessToEssentialItems() {
        return hasAccessToEssentialItems;
    }

    public void setHasAccessToEssentialItems(boolean hasAccessToEssentialItems) {
        this.hasAccessToEssentialItems = hasAccessToEssentialItems;
    }
}
