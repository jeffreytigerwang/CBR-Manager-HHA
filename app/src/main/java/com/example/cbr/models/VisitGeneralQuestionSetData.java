package com.example.cbr.models;

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

    private boolean isCBRChecked;
    private boolean isDCRChecked;
    private boolean isDCRFUChecked;
    private boolean isHealthChecked;
    private boolean isEducationChecked;
    private boolean isSocialChecked;

    private String purposeOfVisit;

    private final Date dateOfVisit;

    private Date dateOfVisit;

    private String workerName;
    private String visitGpsLocation;
    private int villageNumber;
    private String visitZoneLocation;
    public VisitGeneralQuestionSetData() {
        this.dateOfVisit = new Date();
        this.workerName = "";
        this.visitGpsLocation = "";
        this.villageNumber = -1;
        this.visitZoneLocation = "";
    }

    public VisitGeneralQuestionSetData(Integer clientId,
                                       Integer visitId,
                                       boolean isCBRChecked,
                                       boolean isDCRChecked,
                                       boolean isDCRFUChecked,
                                       boolean isHealthChecked,
                                       boolean isEducationChecked,
                                       boolean isSocialChecked,
                                       Date dateOfVisit,
                                       String workerName,
                                       String visitGpsLocation,
                                       String villageNumber,
                                       String visitZoneLocation) {
        this.clientId = clientId;
        this.visitId = visitId;
        this.isCBRChecked = isCBRChecked;
        this.isDCRChecked = isDCRChecked;
        this.isDCRFUChecked = isDCRFUChecked;
        this.isHealthChecked = isHealthChecked;
        this.isEducationChecked = isEducationChecked;
        this.isSocialChecked = isSocialChecked;
        this.dateOfVisit = dateOfVisit;
        this.workerName = workerName;
        this.visitGpsLocation = visitGpsLocation;
        this.villageNumber = villageNumber;
        this.visitZoneLocation = visitZoneLocation;
    }

    public List<String> getEmptyQuestions() {
        List<String> questionNumbers = new ArrayList<>();

        boolean isQuestionOneChecked = isCBRChecked || isDCRChecked || isDCRFUChecked;
        boolean isQuestionTwoChecked = isHealthChecked || isEducationChecked || isSocialChecked;
        boolean isQuestionThreeNull = dateOfVisit == null;
        boolean isQuestionFourEmpty = workerName.isEmpty();
        boolean isQuestionFiveEmpty = visitGpsLocation.isEmpty();
        boolean isQuestionSixEmpty = visitZoneLocation.isEmpty();
        boolean isQuestionSevenEmpty = villageNumber == -1;

        if (!isQuestionOneChecked) {
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

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
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

    public int getVillageNumber() {
        return villageNumber;
    }

    public void setVillageNumber(int villageNumber) {
        this.villageNumber = villageNumber;
    }

    public String getVisitZoneLocation() {
        return visitZoneLocation;
    }

    public void setVisitZoneLocation(String visitZoneLocation) {
        this.visitZoneLocation = visitZoneLocation;
    }

    public boolean isCBRChecked() {
        return isCBRChecked;
    }

    public void setCBRChecked(boolean CBRChecked) {
        isCBRChecked = CBRChecked;
    }

    public boolean isDCRChecked() {
        return isDCRChecked;
    }

    public void setDCRChecked(boolean DCRChecked) {
        isDCRChecked = DCRChecked;
    }

    public boolean isDCRFUChecked() {
        return isDCRFUChecked;
    }

    public void setDCRFUChecked(boolean DCRFUChecked) {
        isDCRFUChecked = DCRFUChecked;
    }

    public String getPurposeOfVisit() {
        return purposeOfVisit;
    }

    public void setPurposeOfVisit(String purposeOfVisit) {
        this.purposeOfVisit = purposeOfVisit;
    }
}
