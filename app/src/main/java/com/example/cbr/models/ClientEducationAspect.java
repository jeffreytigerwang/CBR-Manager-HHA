package com.example.cbr.models;

public class ClientEducationAspect {
    private Integer clientId;
    private String rateEducation;
    private String describeEducation;
    private String setGoalForEducation;

    public ClientEducationAspect(Integer clientId, String rateEducation, String describeEducation, String setGoalForEducation) {
        this.clientId = clientId;
        this.rateEducation = rateEducation;
        this.describeEducation = describeEducation;
        this.setGoalForEducation = setGoalForEducation;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getRateEducation() {
        return rateEducation;
    }

    public void setRateEducation(String rateEducation) {
        this.rateEducation = rateEducation;
    }

    public String getDescribeEducation() {
        return describeEducation;
    }

    public void setDescribeEducation(String describeEducation) {
        this.describeEducation = describeEducation;
    }

    public String getSetGoalForEducation() {
        return setGoalForEducation;
    }

    public void setSetGoalForEducation(String setGoalForEducation) {
        this.setGoalForEducation = setGoalForEducation;
    }
}
