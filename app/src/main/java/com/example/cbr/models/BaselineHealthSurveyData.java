package com.example.cbr.models;

public class BaselineHealthSurveyData {

    private Integer clientId;

    private String  rateGeneralHealth;
    private boolean hasAccessToRehabilitationServices;
    private boolean needsAccessToRehabilitationServices;
    private boolean hasAssistiveDevice;
    private boolean isAssistiveDeviceWorkingWell;
    private boolean needsAssistiveDevice;
    private String  assistiveDeviceNeeded;
    private String  healthServiceSatisfaction;

    public BaselineHealthSurveyData() {
        this.rateGeneralHealth = "";
        this.hasAccessToRehabilitationServices = false;
        this.needsAccessToRehabilitationServices = false;
        this.hasAssistiveDevice = false;
        this.isAssistiveDeviceWorkingWell = false;
        this.needsAssistiveDevice = false;
        this.assistiveDeviceNeeded = "";
        this.healthServiceSatisfaction = "";
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getRateGeneralHealth() {
        return rateGeneralHealth;
    }

    public void setRateGeneralHealth(String rateGeneralHealth) {
        this.rateGeneralHealth = rateGeneralHealth;
    }

    public boolean isHasAccessToRehabilitationServices() {
        return hasAccessToRehabilitationServices;
    }

    public void setHasAccessToRehabilitationServices(boolean hasAccessToRehabilitationServices) {
        this.hasAccessToRehabilitationServices = hasAccessToRehabilitationServices;
    }

    public boolean isNeedsAccessToRehabilitationServices() {
        return needsAccessToRehabilitationServices;
    }

    public void setNeedsAccessToRehabilitationServices(boolean needsAccessToRehabilitationServices) {
        this.needsAccessToRehabilitationServices = needsAccessToRehabilitationServices;
    }

    public boolean isHasAssistiveDevice() {
        return hasAssistiveDevice;
    }

    public void setHasAssistiveDevice(boolean hasAssistiveDevice) {
        this.hasAssistiveDevice = hasAssistiveDevice;
    }

    public boolean isAssistiveDeviceWorkingWell() {
        return isAssistiveDeviceWorkingWell;
    }

    public void setAssistiveDeviceWorkingWell(boolean assistiveDeviceWorkingWell) {
        isAssistiveDeviceWorkingWell = assistiveDeviceWorkingWell;
    }

    public boolean isNeedsAssistiveDevice() {
        return needsAssistiveDevice;
    }

    public void setNeedsAssistiveDevice(boolean needsAssistiveDevice) {
        this.needsAssistiveDevice = needsAssistiveDevice;
    }

    public String getAssistiveDeviceNeeded() {
        return assistiveDeviceNeeded;
    }

    public void setAssistiveDeviceNeeded(String assistiveDeviceNeeded) {
        this.assistiveDeviceNeeded = assistiveDeviceNeeded;
    }

    public String getHealthServiceSatisfaction() {
        return healthServiceSatisfaction;
    }

    public void setHealthServiceSatisfaction(String healthServiceSatisfaction) {
        this.healthServiceSatisfaction = healthServiceSatisfaction;
    }
}
