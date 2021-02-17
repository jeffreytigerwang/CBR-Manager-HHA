package com.example.cbr.models;

public class VisitGeneralQuestionSetData {

    private boolean isHealthChecked;
    private boolean isEducationChecked;
    private boolean isSocialChecked;

    private String purposeOfVisit;
    private String dateOfVisit;
    private String workerName;
    private String locationOfVisit;
    private String villageNumber;
    private String siteLocation;

    public VisitGeneralQuestionSetData() {
        this.purposeOfVisit = "";
        this.dateOfVisit = "";
        this.workerName = "";
        this.locationOfVisit = "";
        this.villageNumber = "";
        this.siteLocation = "";
    }

    public boolean isHealthChecked() {
        return isHealthChecked;
    }

    public void setHealthChecked(boolean healthChecked) {
        isHealthChecked = healthChecked;
    }

    public boolean isEducationChecked() {
        return isEducationChecked;
    }

    public void setEducationChecked(boolean educationChecked) {
        isEducationChecked = educationChecked;
    }

    public boolean isSocialChecked() {
        return isSocialChecked;
    }

    public void setSocialChecked(boolean socialChecked) {
        isSocialChecked = socialChecked;
    }

    public String getPurposeOfVisit() {
        return purposeOfVisit;
    }

    public void setPurposeOfVisit(String purposeOfVisit) {
        this.purposeOfVisit = purposeOfVisit;
    }

    public String getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(String dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getLocationOfVisit() {
        return locationOfVisit;
    }

    public void setLocationOfVisit(String locationOfVisit) {
        this.locationOfVisit = locationOfVisit;
    }

    public String getVillageNumber() {
        return villageNumber;
    }

    public void setVillageNumber(String villageNumber) {
        this.villageNumber = villageNumber;
    }

    public String getSiteLocation() {
        return siteLocation;
    }

    public void setSiteLocation(String siteLocation) {
        this.siteLocation = siteLocation;
    }
}
