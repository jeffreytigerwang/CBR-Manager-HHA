package com.example.cbr.models;

public class VisitGeneralQuestionSetData {

    private boolean isHealthChecked;
    private boolean isEducationChecked;
    private boolean isSocialChecked;

    private String purposeOfVisit;
    private String dateOfVisit;
    private String workerName;
    private String visitGpsLocation;
    private String villageNumber;
    private String visitZoneLocation;

    public VisitGeneralQuestionSetData() {
        this.purposeOfVisit = "";
        this.dateOfVisit = "";
        this.workerName = "";
        this.visitGpsLocation = "";
        this.villageNumber = "";
        this.visitZoneLocation = "";
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

    public String getVisitGpsLocation() {
        return visitGpsLocation;
    }

    public void setVisitGpsLocation(String visitGpsLocation) {
        this.visitGpsLocation = visitGpsLocation;
    }

    public String getVillageNumber() {
        return villageNumber;
    }

    public void setVillageNumber(String villageNumber) {
        this.villageNumber = villageNumber;
    }

    public String getVisitZoneLocation() {
        return visitZoneLocation;
    }

    public void setVisitZoneLocation(String visitZoneLocation) {
        this.visitZoneLocation = visitZoneLocation;
    }
}
