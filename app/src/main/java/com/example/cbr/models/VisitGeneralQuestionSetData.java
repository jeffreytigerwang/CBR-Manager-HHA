package com.example.cbr.models;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* Data container for VisitFirstQuestionSetFragment
* */

public class VisitGeneralQuestionSetData implements Serializable {

    private Integer clientId;
    private Integer visitId;

    private boolean isHealthChecked;
    private boolean isEducationChecked;
    private boolean isSocialChecked;

    private String purposeOfVisit;
    private Date dateOfVisit;
    private String workerName;
    private String visitGpsLocation;
    private String villageNumber;
    private String visitZoneLocation;

    public VisitGeneralQuestionSetData() {
        this.purposeOfVisit = "";
        this.dateOfVisit = null;
        this.workerName = "";
        this.visitGpsLocation = "";
        this.villageNumber = "";
        this.visitZoneLocation = "";
    }

    public VisitGeneralQuestionSetData(Integer clientId,
                                       Integer visitId,
                                       boolean isHealthChecked,
                                       boolean isEducationChecked,
                                       boolean isSocialChecked,
                                       String purposeOfVisit,
                                       Date dateOfVisit,
                                       String workerName,
                                       String visitGpsLocation,
                                       String villageNumber,
                                       String visitZoneLocation) {
        this.clientId = clientId;
        this.visitId = visitId;
        this.isHealthChecked = isHealthChecked;
        this.isEducationChecked = isEducationChecked;
        this.isSocialChecked = isSocialChecked;
        this.purposeOfVisit = purposeOfVisit;
        this.dateOfVisit = dateOfVisit;
        this.workerName = workerName;
        this.visitGpsLocation = visitGpsLocation;
        this.villageNumber = villageNumber;
        this.visitZoneLocation = visitZoneLocation;
    }

    public List<String> getEmptyQuestions() {
        List<String> questionNumbers = new ArrayList<>();

        boolean isQuestionOneEmpty = purposeOfVisit.isEmpty();
        boolean isQuestionTwoChecked = isHealthChecked || isEducationChecked || isSocialChecked;
        boolean isQuestionThreeNull = dateOfVisit == null;
        boolean isQuestionFourEmpty = workerName.isEmpty();
        boolean isQuestionFiveEmpty = visitGpsLocation.isEmpty();
        boolean isQuestionSixEmpty = visitZoneLocation.isEmpty();
        boolean isQuestionSevenEmpty = villageNumber.isEmpty();

        if (isQuestionOneEmpty) {
            questionNumbers.add("1.");
        }
        if (!isQuestionTwoChecked) {
            questionNumbers.add("2.");
        }
        if (isQuestionThreeNull) {
            questionNumbers.add("3.");
        }
        if (isQuestionFourEmpty) {
            questionNumbers.add("4.");
        }
        if (isQuestionFiveEmpty) {
            questionNumbers.add("5.");
        }
        if (isQuestionSixEmpty) {
            questionNumbers.add("6.");
        }
        if (isQuestionSevenEmpty) {
            questionNumbers.add("7.");
        }
        return questionNumbers;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getVisitId() {
        return visitId;
    }

    public void setVisitId(Integer visitId) {
        this.visitId = visitId;
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

    public void setDateOfVisit(Date date) {
        this.dateOfVisit = date;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
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

    @NonNull
    @Override
    public String toString() {
        return "VisitGeneralQuestionSetData{" +
                "isHealthChecked=" + isHealthChecked +
                ", isEducationChecked=" + isEducationChecked +
                ", isSocialChecked=" + isSocialChecked +
                ", purposeOfVisit='" + purposeOfVisit + '\'' +
                ", dateOfVisit='" + dateOfVisit + '\'' +
                ", workerName='" + workerName + '\'' +
                ", visitGPSLocation='" + visitGpsLocation + '\'' +
                ", villageNumber='" + villageNumber + '\'' +
                ", visitZoneLocation='" + visitZoneLocation + '\'' +
                '}';
    }
}
