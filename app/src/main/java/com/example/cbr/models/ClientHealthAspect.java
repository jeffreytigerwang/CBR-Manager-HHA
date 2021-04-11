package com.example.cbr.models;

public class ClientHealthAspect {
    private Integer clientId;
    private String rateHealth;
    private String describeHealth;
    private String setGoalForHealth;

    public ClientHealthAspect(Integer clientId, String rateHealth, String describeHealth, String setGoalForHealth) {
        this.clientId = clientId;
        this.rateHealth = rateHealth;
        this.describeHealth = describeHealth;
        this.setGoalForHealth = setGoalForHealth;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getRateHealth() {
        return rateHealth;
    }

    public void setRateHealth(String rateHealth) {
        this.rateHealth = rateHealth;
    }

    public String getDescribeHealth() {
        return describeHealth;
    }

    public void setDescribeHealth(String describeHealth) {
        this.describeHealth = describeHealth;
    }

    public String getSetGoalForHealth() {
        return setGoalForHealth;
    }

    public void setSetGoalForHealth(String setGoalForHealth) {
        this.setGoalForHealth = setGoalForHealth;
    }
}
