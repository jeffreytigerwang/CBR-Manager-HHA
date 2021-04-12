package com.example.cbr.models;

public class BaselineEducationSurveyData {

    private Integer clientId;

    private boolean attendingSchool;
    private Integer grade;
    private String reasonForNotAttendingSchool;
    private boolean hasBeenToSchool;
    private boolean wantsToAttendSchool;

    public BaselineEducationSurveyData() {
        this.attendingSchool = false;
        this.grade = -1;
        this.reasonForNotAttendingSchool = "";
        this.hasBeenToSchool = false;
        this.wantsToAttendSchool = false;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public boolean isAttendingSchool() {
        return attendingSchool;
    }

    public void setAttendingSchool(boolean attendingSchool) {
        this.attendingSchool = attendingSchool;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getReasonForNotAttendingSchool() {
        return reasonForNotAttendingSchool;
    }

    public void setReasonForNotAttendingSchool(String reasonForNotAttendingSchool) {
        this.reasonForNotAttendingSchool = reasonForNotAttendingSchool;
    }

    public boolean isHasBeenToSchool() {
        return hasBeenToSchool;
    }

    public void setHasBeenToSchool(boolean hasBeenToSchool) {
        this.hasBeenToSchool = hasBeenToSchool;
    }

    public boolean isWantsToAttendSchool() {
        return wantsToAttendSchool;
    }

    public void setWantsToAttendSchool(boolean wantsToAttendSchool) {
        this.wantsToAttendSchool = wantsToAttendSchool;
    }
}
