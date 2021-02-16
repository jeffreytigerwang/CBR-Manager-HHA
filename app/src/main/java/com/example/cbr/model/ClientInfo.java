package com.example.cbr.model;

public class ClientInfo {
    private boolean consentToInterview;

    private String gpsLocation;
    private String location;
    private String villageNumber;
    private String date;
    private String firstName;
    private String lastName;
    private Integer age;
    private String contactNumber;

    private boolean caregiverPresentForInterview;
    private String caregiverContactNumber;

    private boolean amputeeDisability;
    private boolean polioDisability;
    private boolean spinalCordInjuryDisability;
    private boolean cerebralPalsyDisability;
    private boolean spinaBifidaDisability;
    private boolean hydrocephalusDisability;
    private boolean visualImpairmentDisability;
    private boolean hearingImpairmentDisability;
    private boolean doNotKnowDisability;
    private boolean otherDisability;

    private String rateHealth;
    private String describeHealth;
    private String setGoalForHealth;

    private String rateEducation;
    private String describeEducation;
    private String setGoalForEducation;

    private String rateSocialStatus;
    private String describeSocialStatus;
    private String setGoalForSocialStatus;

        public ClientInfo(Boolean consentToInterview,
                          String gpsLocation,
                          String location,
                          String villageNumber,
                          String date,
                          String firstName,
                          String lastName,
                          Integer age,
                          String contactNumber,
                          boolean caregiverPresentForInterview,
                          String caregiverContactNumber,
                          boolean amputeeDisability,
                          boolean polioDisability,
                          boolean spinalCordInjuryDisability,
                          boolean cerebralPalsyDisability,
                          boolean spinaBifidaDisability,
                          boolean hydrocephalusDisability,
                          boolean visualImpairmentDisability,
                          boolean hearingImpairmentDisability,
                          boolean doNotKnowDisability,
                          boolean otherDisability,
                          String rateHealth,
                          String describeHealth,
                          String setGoalForHealth,
                          String rateEducation,
                          String describeEducation,
                          String setGoalForEducation,
                          String rateSocialStatus,
                          String describeSocialStatus,
                          String setGoalForSocialStatus) {
            this.consentToInterview = consentToInterview;
            this.gpsLocation = gpsLocation;
            this.location = location;
            this.villageNumber = villageNumber;
            this.date = date;
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.contactNumber = contactNumber;
            this.caregiverPresentForInterview = caregiverPresentForInterview;
            this.caregiverContactNumber = caregiverContactNumber;
            this.amputeeDisability = amputeeDisability;
            this.polioDisability = polioDisability;
            this.spinalCordInjuryDisability = spinalCordInjuryDisability;
            this.cerebralPalsyDisability = cerebralPalsyDisability;
            this.spinaBifidaDisability = spinaBifidaDisability;
            this.hydrocephalusDisability = hydrocephalusDisability;
            this.visualImpairmentDisability = visualImpairmentDisability;
            this.hearingImpairmentDisability = hearingImpairmentDisability;
            this.doNotKnowDisability = doNotKnowDisability;
            this.otherDisability = otherDisability;
            this.rateHealth = rateHealth;
            this.describeHealth = describeHealth;
            this.setGoalForHealth = setGoalForHealth;
            this.rateEducation = rateEducation;
            this.describeEducation = describeEducation;
            this.setGoalForEducation = setGoalForEducation;
            this.rateSocialStatus = rateSocialStatus;
            this.describeSocialStatus = describeSocialStatus;
            this.setGoalForSocialStatus = setGoalForSocialStatus;
        }

    public ClientInfo() {

    }


    public boolean isConsentToInterview() {
        return consentToInterview;
    }

    public void setConsentToInterview(boolean consentToInterview) {
        this.consentToInterview = consentToInterview;
    }

    public String getGpsLocation() {
        return gpsLocation;
    }

    public void setGpsLocation(String gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVillageNumber() {
        return villageNumber;
    }

    public void setVillageNumber(String villageNumber) {
        this.villageNumber = villageNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public boolean isCaregiverPresentForInterview() {
        return caregiverPresentForInterview;
    }

    public void setCaregiverPresentForInterview(boolean caregiverPresentForInterview) {
        this.caregiverPresentForInterview = caregiverPresentForInterview;
    }

    public String getCaregiverContactNumber() {
        return caregiverContactNumber;
    }

    public void setCaregiverContactNumber(String caregiverContactNumber) {
        this.caregiverContactNumber = caregiverContactNumber;
    }

    public boolean isAmputeeDisability() {
        return amputeeDisability;
    }

    public void setAmputeeDisability(boolean amputeeDisability) {
        this.amputeeDisability = amputeeDisability;
    }

    public boolean isPolioDisability() {
        return polioDisability;
    }

    public void setPolioDisability(boolean polioDisability) {
        this.polioDisability = polioDisability;
    }

    public boolean isSpinalCordInjuryDisability() {
        return spinalCordInjuryDisability;
    }

    public void setSpinalCordInjuryDisability(boolean spinalCordInjuryDisability) {
        this.spinalCordInjuryDisability = spinalCordInjuryDisability;
    }

    public boolean isCerebralPalsyDisability() {
        return cerebralPalsyDisability;
    }

    public void setCerebralPalsyDisability(boolean cerebralPalsyDisability) {
        this.cerebralPalsyDisability = cerebralPalsyDisability;
    }

    public boolean isSpinaBifidaDisability() {
        return spinaBifidaDisability;
    }

    public void setSpinaBifidaDisability(boolean spinaBifidaDisability) {
        this.spinaBifidaDisability = spinaBifidaDisability;
    }

    public boolean isHydrocephalusDisability() {
        return hydrocephalusDisability;
    }

    public void setHydrocephalusDisability(boolean hydrocephalusDisability) {
        this.hydrocephalusDisability = hydrocephalusDisability;
    }

    public boolean isVisualImpairmentDisability() {
        return visualImpairmentDisability;
    }

    public void setVisualImpairmentDisability(boolean visualImpairmentDisability) {
        this.visualImpairmentDisability = visualImpairmentDisability;
    }

    public boolean isHearingImpairmentDisability() {
        return hearingImpairmentDisability;
    }

    public void setHearingImpairmentDisability(boolean hearingImpairmentDisability) {
        this.hearingImpairmentDisability = hearingImpairmentDisability;
    }

    public boolean isDoNotKnowDisability() {
        return doNotKnowDisability;
    }

    public void setDoNotKnowDisability(boolean doNotKnowDisability) {
        this.doNotKnowDisability = doNotKnowDisability;
    }

    public boolean isOtherDisability() {
        return otherDisability;
    }

    public void setOtherDisability(boolean otherDisability) {
        this.otherDisability = otherDisability;
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

    public String getRateSocialStatus() {
        return rateSocialStatus;
    }

    public void setRateSocialStatus(String rateSocialStatus) {
        this.rateSocialStatus = rateSocialStatus;
    }

    public String getDescribeSocialStatus() {
        return describeSocialStatus;
    }

    public void setDescribeSocialStatus(String describeSocialStatus) {
        this.describeSocialStatus = describeSocialStatus;
    }

    public String getSetGoalForSocialStatus() {
        return setGoalForSocialStatus;
    }

    public void setSetGoalForSocialStatus(String setGoalForSocialStatus) {
        this.setGoalForSocialStatus = setGoalForSocialStatus;
    }
}