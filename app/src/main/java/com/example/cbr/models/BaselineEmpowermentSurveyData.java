package com.example.cbr.models;

public class BaselineEmpowermentSurveyData {

    private Integer clientId;

    private boolean isMemberOfAnyOrganization;
    private String organizationName;
    private boolean isAwareOfRights;
    private boolean feelsAbleToInfluencePeople;

    public BaselineEmpowermentSurveyData() {
        this.isMemberOfAnyOrganization = false;
        this.organizationName = "";
        this.isAwareOfRights = false;
        this.feelsAbleToInfluencePeople = false;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public boolean isMemberOfAnyOrganization() {
        return isMemberOfAnyOrganization;
    }

    public void setMemberOfAnyOrganization(boolean memberOfAnyOrganization) {
        isMemberOfAnyOrganization = memberOfAnyOrganization;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public boolean isAwareOfRights() {
        return isAwareOfRights;
    }

    public void setAwareOfRights(boolean awareOfRights) {
        isAwareOfRights = awareOfRights;
    }

    public boolean isFeelsAbleToInfluencePeople() {
        return feelsAbleToInfluencePeople;
    }

    public void setFeelsAbleToInfluencePeople(boolean feelsAbleToInfluencePeople) {
        this.feelsAbleToInfluencePeople = feelsAbleToInfluencePeople;
    }
}
