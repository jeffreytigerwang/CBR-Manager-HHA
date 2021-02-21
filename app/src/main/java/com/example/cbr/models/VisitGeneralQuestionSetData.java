package com.example.cbr.models;

import androidx.annotation.NonNull;

import com.example.cbr.util.Constants;

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
    private final Date dateOfVisit;
    private String workerName;
    private String visitGpsLocation;
    private String villageNumber;
    private String visitZoneLocation;

    public VisitGeneralQuestionSetData() {
        this.purposeOfVisit = "";
        this.dateOfVisit = new Date();
        this.workerName = "";
        this.visitGpsLocation = "";
        this.villageNumber = "";
        this.visitZoneLocation = "";
    }

    public List<String> getEmptyQuestions() {
        List<String> questionNumbers = new ArrayList<>();

        boolean isQuestionOneEmpty = purposeOfVisit.isEmpty();
        boolean isQuestionTwoChecked;
        if (purposeOfVisit.equalsIgnoreCase(Constants.CBR)) {
            isQuestionTwoChecked = isHealthChecked || isEducationChecked || isSocialChecked;
        } else {
            isQuestionTwoChecked = true;
        }
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
