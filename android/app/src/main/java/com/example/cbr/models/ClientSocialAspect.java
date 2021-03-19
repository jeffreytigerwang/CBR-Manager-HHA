package com.example.cbr.models;

public class ClientSocialAspect {
    private Integer clientId;
    private String rateSocialStatus;
    private String describeSocialStatus;
    private String setGoalForSocialStatus;

    public ClientSocialAspect(Integer clientId, String rateSocialStatus, String describeSocialStatus, String setGoalForSocialStatus) {
        this.clientId = clientId;
        this.rateSocialStatus = rateSocialStatus;
        this.describeSocialStatus = describeSocialStatus;
        this.setGoalForSocialStatus = setGoalForSocialStatus;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getRateSocialStatus() {
        return rateSocialStatus;
    }

    public void setRateSocialStatus(String rateSocialStatus) {
        this.rateSocialStatus = rateSocialStatus;
    }

    public String getDescribeSocialStatus() {
        return describeSocialStatus;
    }

    public void setDescribeSocialStatus(String describeSocialStatus) {
        this.describeSocialStatus = describeSocialStatus;
    }

    public String getSetGoalForSocialStatus() {
        return setGoalForSocialStatus;
    }

    public void setSetGoalForSocialStatus(String setGoalForSocialStatus) {
        this.setGoalForSocialStatus = setGoalForSocialStatus;
    }
}
