package com.example.cbr.models;

import com.example.cbr.util.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientInfo implements Serializable {
    private Boolean consentToInterview;

    private String gpsLocation;
    private String location;
    private String villageNumber;
    private String date;
    private String firstName;
    private String lastName;
    private String id;
    private Integer age;
    private String contactNumber;

    private Boolean caregiverPresentForInterview;
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


    public ClientInfo() {}

    public ClientInfo(boolean consentToInterview,
                      String gpsLocation,
                      String location,
                      String villageNumber,
                      String date,
                      String firstName,
                      String lastName,//String id,
                      Integer age,
                      String contactNumber,
                      Boolean caregiverPresentForInterview,
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

    public Boolean isConsentToInterview() {
        return consentToInterview;
    }

    public String getGpsLocation() {
        return gpsLocation;
    }

    public String getLocation() {
        return location;
    }

    public String getVillageNumber() {
        return villageNumber;
    }

    public String getDate() {
        return date;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        if (age == null) {
            return -1;
        }
        return age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Boolean isCaregiverPresentForInterview() {
        return caregiverPresentForInterview;
    }

    public String getCaregiverContactNumber() {
        return caregiverContactNumber;
    }

    public List<String> getDisabilityList() {
        List<String> disabilityList = new ArrayList<>();
        if (isDoNotKnowDisability()) {
            disabilityList.add(Constants.UNKNOWN_DISABILITY);
            return disabilityList;
        }

        if (isAmputeeDisability()) disabilityList.add(Constants.AMPUTEE_DISABILITY);
        if (isPolioDisability()) disabilityList.add(Constants.POLIO_DISABILITY);
        if (isSpinalCordInjuryDisability()) disabilityList.add(Constants.SPINAL_CORD_INJURY_DISABILITY);
        if (isCerebralPalsyDisability()) disabilityList.add(Constants.CEREBRAL_PALSY_DISABILITY);
        if (isSpinaBifidaDisability()) disabilityList.add(Constants.SPINA_BIFIDA_DISABILITY);
        if (isHydrocephalusDisability()) disabilityList.add(Constants.HYDROCEPHALUS_DISABILITY);
        if (isVisualImpairmentDisability()) disabilityList.add(Constants.VISUAL_IMPAIRMENT_DISABILITY);
        if (isHearingImpairmentDisability()) disabilityList.add(Constants.HEARING_IMPAIRMENT_DISABILITY);
        if (isOtherDisability()) disabilityList.add(Constants.OTHER_DISABILITY);

        return disabilityList;
    }

    public boolean isAmputeeDisability() {
        return amputeeDisability;
    }

    public boolean isPolioDisability() {
        return polioDisability;
    }

    public boolean isSpinalCordInjuryDisability() {
        return spinalCordInjuryDisability;
    }

    public boolean isCerebralPalsyDisability() {
        return cerebralPalsyDisability;
    }

    public boolean isSpinaBifidaDisability() {
        return spinaBifidaDisability;
    }

    public boolean isHydrocephalusDisability() {
        return hydrocephalusDisability;
    }

    public boolean isVisualImpairmentDisability() {
        return visualImpairmentDisability;
    }

    public boolean isHearingImpairmentDisability() {
        return hearingImpairmentDisability;
    }

    public boolean isDoNotKnowDisability() {
        return doNotKnowDisability;
    }

    public boolean isOtherDisability() {
        return otherDisability;
    }

    public String getRateHealth() {
        return rateHealth;
    }

    public String getDescribeHealth() {
        return describeHealth;
    }

    public String getSetGoalForHealth() {
        return setGoalForHealth;
    }

    public String getRateEducation() {
        return rateEducation;
    }

    public String getDescribeEducation() {
        return describeEducation;
    }

    public String getSetGoalForEducation() {
        return setGoalForEducation;
    }

    public String getRateSocialStatus() {
        return rateSocialStatus;
    }

    public String getDescribeSocialStatus() {
        return describeSocialStatus;
    }

    public String getSetGoalForSocialStatus() {
        return setGoalForSocialStatus;
    }

    public void setConsentToInterview(boolean consentToInterview) {
        this.consentToInterview = consentToInterview;
    }

    public void setGpsLocation(String gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setVillageNumber(String villageNumber) {
        this.villageNumber = villageNumber;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;//this.id = id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setCaregiverPresentForInterview(boolean caregiverPresentForInterview) {
        this.caregiverPresentForInterview = caregiverPresentForInterview;
    }

    public void setCaregiverContactNumber(String caregiverContactNumber) {
        this.caregiverContactNumber = caregiverContactNumber;
    }

    public void setAmputeeDisability(boolean amputeeDisability) {
        this.amputeeDisability = amputeeDisability;
    }

    public void setPolioDisability(boolean polioDisability) {
        this.polioDisability = polioDisability;
    }

    public void setSpinalCordInjuryDisability(boolean spinalCordInjuryDisability) {
        this.spinalCordInjuryDisability = spinalCordInjuryDisability;
    }

    public void setCerebralPalsyDisability(boolean cerebralPalsyDisability) {
        this.cerebralPalsyDisability = cerebralPalsyDisability;
    }

    public void setSpinaBifidaDisability(boolean spinaBifidaDisability) {
        this.spinaBifidaDisability = spinaBifidaDisability;
    }

    public void setHydrocephalusDisability(boolean hydrocephalusDisability) {
        this.hydrocephalusDisability = hydrocephalusDisability;
    }

    public void setVisualImpairmentDisability(boolean visualImpairmentDisability) {
        this.visualImpairmentDisability = visualImpairmentDisability;
    }

    public void setHearingImpairmentDisability(boolean hearingImpairmentDisability) {
        this.hearingImpairmentDisability = hearingImpairmentDisability;
    }

    public void setDoNotKnowDisability(boolean doNotKnowDisability) {
        this.doNotKnowDisability = doNotKnowDisability;
    }

    public void setOtherDisability(boolean otherDisability) {
        this.otherDisability = otherDisability;
    }

    public void setRateHealth(String rateHealth) {
        this.rateHealth = rateHealth;
    }

    public void setDescribeHealth(String describeHealth) {
        this.describeHealth = describeHealth;
    }

    public void setSetGoalForHealth(String setGoalForHealth) {
        this.setGoalForHealth = setGoalForHealth;
    }

    public void setRateEducation(String rateEducation) {
        this.rateEducation = rateEducation;
    }

    public void setDescribeEducation(String describeEducation) {
        this.describeEducation = describeEducation;
    }

    public void setSetGoalForEducation(String setGoalForEducation) {
        this.setGoalForEducation = setGoalForEducation;
    }

    public void setRateSocialStatus(String rateSocialStatus) {
        this.rateSocialStatus = rateSocialStatus;
    }

    public void setDescribeSocialStatus(String describeSocialStatus) {
        this.describeSocialStatus = describeSocialStatus;
    }

    public void setSetGoalForSocialStatus(String setGoalForSocialStatus) {
        this.setGoalForSocialStatus = setGoalForSocialStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}