package com.example.cbr.models;

import android.util.Log;

import com.example.cbr.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class VisitGeneralQuestionSetData {

    private boolean isHealthChecked;
    private boolean isEducationChecked;
    private boolean isSocialChecked;

    private String purposeOfVisit;
    private String dateOfVisit;
    private String workerName;
    private String visitGPSLocation;
    private String villageNumber;
    private String visitZoneLocation;

    public VisitGeneralQuestionSetData() {
        this.purposeOfVisit = "";
        this.dateOfVisit = "";
        this.workerName = "";
        this.visitGPSLocation = "";
        this.villageNumber = "";
        this.visitZoneLocation = "";
    }

//    public boolean isRequiredFieldEmpty() {
//        boolean isQuestionOneFilled = purposeOfVisit.isEmpty();
//        boolean isQuestionTwoFilled;
//        if (purposeOfVisit.equalsIgnoreCase(Constants.CBR)) {
//            isQuestionTwoFilled = isHealthChecked || isEducationChecked || isSocialChecked;
//        } else {
//            isQuestionTwoFilled = true;
//        }
//        boolean isQuestionThreeFilled = dateOfVisit.isEmpty();
//        boolean isQuestionFourFilled = workerName.isEmpty();
//        boolean isQuestionFiveFilled = visitGPSLocation.isEmpty();
//        boolean isQuestionSixFilled = visitZoneLocation.isEmpty();
//        boolean isQuestionSevenFilled = villageNumber.isEmpty();
//        return isQuestionOneFilled
//                && isQuestionTwoFilled
//                && isQuestionThreeFilled
//                && isQuestionFourFilled
//                && isQuestionFiveFilled
//                && isQuestionSixFilled
//                && isQuestionSevenFilled;
//    }

    public List<String> getEmptyQuestions() {
        List<String> questionNumbers = new ArrayList<>();

        boolean isQuestionOneEmpty = purposeOfVisit.isEmpty();
        boolean isQuestionTwoChecked;
        if (purposeOfVisit.equalsIgnoreCase(Constants.CBR)) {
            isQuestionTwoChecked = isHealthChecked || isEducationChecked || isSocialChecked;
        } else {
            isQuestionTwoChecked = true;
        }
        boolean isQuestionThreeEmpty = dateOfVisit.isEmpty();
        boolean isQuestionFourEmpty = workerName.isEmpty();
        boolean isQuestionFiveEmpty = visitGPSLocation.isEmpty();
        boolean isQuestionSixEmpty = visitZoneLocation.isEmpty();
        boolean isQuestionSevenEmpty = villageNumber.isEmpty();

        if (isQuestionOneEmpty) {
            questionNumbers.add("1.");
        }
        if (!isQuestionTwoChecked) {
            questionNumbers.add("2.");
        }
        if (isQuestionThreeEmpty) {
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

    public String getVisitGPSLocation() {
        return visitGPSLocation;
    }

    public void setVisitGPSLocation(String visitGPSLocation) {
        this.visitGPSLocation = visitGPSLocation;
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

    @Override
    public String toString() {
        return "VisitGeneralQuestionSetData{" +
                "isHealthChecked=" + isHealthChecked +
                ", isEducationChecked=" + isEducationChecked +
                ", isSocialChecked=" + isSocialChecked +
                ", purposeOfVisit='" + purposeOfVisit + '\'' +
                ", dateOfVisit='" + dateOfVisit + '\'' +
                ", workerName='" + workerName + '\'' +
                ", visitGPSLocation='" + visitGPSLocation + '\'' +
                ", villageNumber='" + villageNumber + '\'' +
                ", visitZoneLocation='" + visitZoneLocation + '\'' +
                '}';
    }
}
