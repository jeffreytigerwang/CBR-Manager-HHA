package com.example.cbr.model;

public class ClientInfo {
    private boolean consentToInterview;

    private String gpsLocation;
    private String zoneLocation;
    private String villageNumber;
    private String dateJoined;
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
                          String zoneLocation,
                          String villageNumber,
                          String dateJoined,
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
            this.zoneLocation = zoneLocation;
            this.villageNumber = villageNumber;
            this.dateJoined = dateJoined;
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

}