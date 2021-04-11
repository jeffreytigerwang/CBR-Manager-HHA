package com.example.cbr.models;

import com.example.cbr.util.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClientInfo implements Serializable, Comparable<ClientInfo>{
    private static final double nullCoordinateHandler = 300;
    private Integer clientId;
    private double latitude;
    private double longitude;
    private String dateJoined;
    private boolean consentToInterview;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private String contactNumber;
    private String gpsLocation;
    private String zoneLocation;
    private Integer villageNumber;
    private boolean caregiverPresentForInterview;
    private String caregiverFirstName;
    private String caregiverLastName;
    private String caregiverContactNumber;
    private byte[] photo;
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
    private String describeOtherDisability;
    private String rateHealth;
    private String describeHealth;
    private String setGoalForHealth;
    private String rateEducation;
    private String describeEducation;
    private String setGoalForEducation;
    private String rateSocialStatus;
    private String describeSocialStatus;
    private String setGoalForSocialStatus;
    private double overallRisk;
    
    public ClientInfo() {

    }

    public ClientInfo(Integer clientId,
                      boolean consentToInterview,
                      String firstName,
                      String lastName,
                      Integer age,
                      String gender,
                      String contactNumber,
                      String dateJoined,
                      String gpsLocation,
                      String zoneLocation,
                      Integer villageNumber,
                      boolean caregiverPresentForInterview,
                      String caregiverFirstName,
                      String caregiverLastName,
                      String caregiverContactNumber,
                      byte[] photo,
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
                      String describeOtherDisability,
                      String rateHealth,
                      String describeHealth,
                      String setGoalForHealth,
                      String rateEducation,
                      String describeEducation,
                      String setGoalForEducation,
                      String rateSocialStatus,
                      String describeSocialStatus,
                      String setGoalForSocialStatus) {
        this.clientId = clientId;
        this.consentToInterview = consentToInterview;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.dateJoined = dateJoined;
        this.gpsLocation = gpsLocation;
        this.zoneLocation = zoneLocation;
        this.villageNumber = villageNumber;
        this.caregiverPresentForInterview = caregiverPresentForInterview;
        this.caregiverFirstName = caregiverFirstName;
        this.caregiverLastName = caregiverLastName;
        this.caregiverContactNumber = caregiverContactNumber;
        this.photo = photo;
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
        this.describeOtherDisability = describeOtherDisability;
        this.rateHealth = rateHealth;
        this.describeHealth = describeHealth;
        this.setGoalForHealth = setGoalForHealth;
        this.rateEducation = rateEducation;
        this.describeEducation = describeEducation;
        this.setGoalForEducation = setGoalForEducation;
        this.rateSocialStatus = rateSocialStatus;
        this.describeSocialStatus = describeSocialStatus;
        this.setGoalForSocialStatus = setGoalForSocialStatus;

        this.latitude = getClientLatitude();
        this.longitude = getClientLongitude();
        overallRisk = 0;
    }

    public double getClientLatitude() {
        if (getGpsLocation()!= null && !getGpsLocation().trim().isEmpty()){
            String[] coordinates = getGpsLocation().split("[\\s,]+");
            return Double.parseDouble(coordinates[0]);
        }

        return nullCoordinateHandler;
    }

    public double getClientLongitude() {
        if (getGpsLocation()!= null && !getGpsLocation().trim().isEmpty()){
            String[] coordinates = getGpsLocation().split("[\\s,]+");
            return Double.parseDouble(coordinates[1]);
        }

        return nullCoordinateHandler;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setOverallRisk(double overallRisk){
        this.overallRisk = overallRisk;
    }

    public double getOverallRisk(){
        return overallRisk;
    }

    public String getGpsLocation() {
        return gpsLocation;
    }

    public String getZoneLocation() {
        return zoneLocation;
    }

    public Integer getVillageNumber() {
        return villageNumber;
    }

    public String getDateJoined() {
        return dateJoined;
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

    public String getCaregiverContactNumber() {
        return caregiverContactNumber;
    }

    public List<String> getDisabilityList() {
        List<String> disabilityList = new ArrayList<>();
        if (getDoNotKnowDisability()) {
            disabilityList.add(Constants.UNKNOWN_DISABILITY);
            return disabilityList;
        }

        if (getAmputeeDisability()) disabilityList.add(Constants.AMPUTEE_DISABILITY);
        if (getPolioDisability()) disabilityList.add(Constants.POLIO_DISABILITY);
        if (getSpinalCordInjuryDisability()) disabilityList.add(Constants.SPINAL_CORD_INJURY_DISABILITY);
        if (getCerebralPalsyDisability()) disabilityList.add(Constants.CEREBRAL_PALSY_DISABILITY);
        if (getSpinaBifidaDisability()) disabilityList.add(Constants.SPINA_BIFIDA_DISABILITY);
        if (getHydrocephalusDisability()) disabilityList.add(Constants.HYDROCEPHALUS_DISABILITY);
        if (getVisualImpairmentDisability()) disabilityList.add(Constants.VISUAL_IMPAIRMENT_DISABILITY);
        if (getHearingImpairmentDisability()) disabilityList.add(Constants.HEARING_IMPAIRMENT_DISABILITY);
        if (getOtherDisability()) disabilityList.add(Constants.OTHER_DISABILITY);

        return disabilityList;
    }

    public String getDisabilityListFormatted() {
        return getDisabilityList().toString().replace("[", "").replace("]", "");
    }

    public boolean getAmputeeDisability() {
        return amputeeDisability;
    }

    public boolean getPolioDisability() {
        return polioDisability;
    }

    public boolean getSpinalCordInjuryDisability() {
        return spinalCordInjuryDisability;
    }

    public boolean getCerebralPalsyDisability() {
        return cerebralPalsyDisability;
    }

    public boolean getSpinaBifidaDisability() {
        return spinaBifidaDisability;
    }

    public boolean getHydrocephalusDisability() {
        return hydrocephalusDisability;
    }

    public boolean getVisualImpairmentDisability() {
        return visualImpairmentDisability;
    }

    public boolean getHearingImpairmentDisability() {
        return hearingImpairmentDisability;
    }

    public boolean getDoNotKnowDisability() {
        return doNotKnowDisability;
    }

    public boolean getOtherDisability() {
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

    public void setZoneLocation(String zoneLocation) {
        this.zoneLocation = zoneLocation;
    }

    public void setVillageNumber(Integer villageNumber) {
        this.villageNumber = villageNumber;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean getConsentToInterview() {
        return consentToInterview;
    }

    public Boolean getCaregiverPresentForInterview() {
        return caregiverPresentForInterview;
    }

    public String getCaregiverFirstName() {
        return caregiverFirstName;
    }

    public void setCaregiverFirstName(String caregiverFirstName) {
        this.caregiverFirstName = caregiverFirstName;
    }

    public void setCaregiverLastName(String caregiverLastName) {
        this.caregiverLastName = caregiverLastName;
    }

    public String getDescribeOtherDisability() {
        return describeOtherDisability;
    }

    public void setDescribeOtherDisability(String describeOtherDisability) {
        this.describeOtherDisability = describeOtherDisability;
    }

    public String getCaregiverLastName() {
        return caregiverLastName;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public int compareTo(ClientInfo clientInfo) {
        return (int) (this.getOverallRisk() - clientInfo.getOverallRisk());
    }

    public static final Comparator<ClientInfo> BY_NAME_ASCENDING = new Comparator<ClientInfo>() {
        @Override
        public int compare(ClientInfo clientInfo, ClientInfo t1) {
            return clientInfo.getFullName().compareTo(t1.getFullName());
        }
    };

    public static final Comparator<ClientInfo> BY_NAME_DESCENDING = new Comparator<ClientInfo>() {
        @Override
        public int compare(ClientInfo clientInfo, ClientInfo t1) {
            return t1.getFullName().compareTo(clientInfo.getFullName());
        }
    };

    public static final Comparator<ClientInfo> BY_ID_ASCENDING = new Comparator<ClientInfo>() {
        @Override
        public int compare(ClientInfo clientInfo, ClientInfo t1) {
            return clientInfo.getClientId().compareTo(t1.getClientId());
        }
    };

    public static final Comparator<ClientInfo> BY_ID_DESCENDING = new Comparator<ClientInfo>() {
        @Override
        public int compare(ClientInfo clientInfo, ClientInfo t1) {
            return t1.getClientId().compareTo(clientInfo.getClientId());
        }
    };

}
