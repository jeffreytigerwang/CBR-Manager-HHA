package com.example.cbr.models;

;

import java.io.Serializable;
import java.util.Date;

public class VisitRecord implements Serializable {

    private String purposeOfVisit;
    private boolean isHealthChecked;
    private boolean isEducationChecked;
    private boolean isSocialChecked;
    private Date dateOfVisit;
    private String nameOfCBRWorker;
    private String locationOfVisit;
    private String siteLocation;
    private int villageNumber;

    private boolean isWheelChairChecked;
    private boolean isProstheticChecked;
    private boolean isOrthoticChecked;
    private boolean isWRChecked;
    private boolean isReferralToHCChecked;
    private boolean isHealthAdviceChecked;
    private boolean isHealthAdvocacyChecked;
    private boolean isHealthEncouragementChecked;
    private boolean isHealthOutcomeChecked;
    private String wheelChairDescription;
    private String prostheticDescription;
    private String orthoticDescription;
    private String WRDescription;
    private String referralToHCDescription;
    private String healthAdviceDescription;
    private String healthAdvocacyDescription;
    private String healthEncouragementDescription;
    private String healthOutcomeDescription;
    private String healthGoalStatus;

    private boolean isEducationAdviceChecked;
    private boolean isEducationAdvocacyChecked;
    private boolean isEducationRefChecked;
    private boolean isEducationEncouragementChecked;
    private boolean isEducationOutcomeChecked;
    private String educationGoalStatus;

    private boolean isSocialAdviceChecked;
    private boolean isSocialAdvocacyChecked;
    private boolean isSocialRefChecked;
    private boolean isSocialEncouragementChecked;
    private boolean isSocialOutcomeChecked;
    private String SocialGoalStatus;

    public VisitRecord() {
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

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getNameOfCBRWorker() {
        return nameOfCBRWorker;
    }

    public void setNameOfCBRWorker(String nameOfCBRWorker) {
        this.nameOfCBRWorker = nameOfCBRWorker;
    }

    public String getLocationOfVisit() {
        return locationOfVisit;
    }

    public void setLocationOfVisit(String locationOfVisit) {
        this.locationOfVisit = locationOfVisit;
    }

    public String getSiteLocation() {
        return siteLocation;
    }

    public void setSiteLocation(String siteLocation) {
        this.siteLocation = siteLocation;
    }

    public int getVillageNumber() {
        return villageNumber;
    }

    public void setVillageNumber(int villageNumber) {
        this.villageNumber = villageNumber;
    }

    public boolean isWheelChairChecked() {
        return isWheelChairChecked;
    }

    public void setWheelChairChecked(boolean wheelChairChecked) {
        isWheelChairChecked = wheelChairChecked;
    }

    public boolean isProstheticChecked() {
        return isProstheticChecked;
    }

    public void setProstheticChecked(boolean prostheticChecked) {
        isProstheticChecked = prostheticChecked;
    }

    public boolean isOrthoticChecked() {
        return isOrthoticChecked;
    }

    public void setOrthoticChecked(boolean orthoticChecked) {
        isOrthoticChecked = orthoticChecked;
    }

    public boolean isWRChecked() {
        return isWRChecked;
    }

    public void setWRChecked(boolean WRChecked) {
        isWRChecked = WRChecked;
    }

    public boolean isReferralToHCChecked() {
        return isReferralToHCChecked;
    }

    public void setReferralToHCChecked(boolean referralToHCChecked) {
        isReferralToHCChecked = referralToHCChecked;
    }

    public boolean isHealthAdviceChecked() {
        return isHealthAdviceChecked;
    }

    public void setHealthAdviceChecked(boolean healthAdviceChecked) {
        isHealthAdviceChecked = healthAdviceChecked;
    }

    public boolean isHealthAdvocacyChecked() {
        return isHealthAdvocacyChecked;
    }

    public void setHealthAdvocacyChecked(boolean healthAdvocacyChecked) {
        isHealthAdvocacyChecked = healthAdvocacyChecked;
    }

    public boolean isHealthEncouragementChecked() {
        return isHealthEncouragementChecked;
    }

    public void setHealthEncouragementChecked(boolean healthEncouragementChecked) {
        isHealthEncouragementChecked = healthEncouragementChecked;
    }

    public boolean isHealthOutcomeChecked() {
        return isHealthOutcomeChecked;
    }

    public void setHealthOutcomeChecked(boolean healthOutcomeChecked) {
        isHealthOutcomeChecked = healthOutcomeChecked;
    }

    public String getWheelChairDescription() {
        return wheelChairDescription;
    }

    public void setWheelChairDescription(String wheelChairDescription) {
        this.wheelChairDescription = wheelChairDescription;
    }

    public String getProstheticDescription() {
        return prostheticDescription;
    }

    public void setProstheticDescription(String prostheticDescription) {
        this.prostheticDescription = prostheticDescription;
    }

    public String getWRDescription() {
        return WRDescription;
    }

    public void setWRDescription(String WRDescription) {
        this.WRDescription = WRDescription;
    }

    public String getReferralToHCDescription() {
        return referralToHCDescription;
    }

    public void setReferralToHCDescription(String referralToHCDescription) {
        this.referralToHCDescription = referralToHCDescription;
    }

    public String getHealthAdviceDescription() {
        return healthAdviceDescription;
    }

    public void setHealthAdviceDescription(String healthAdviceDescription) {
        this.healthAdviceDescription = healthAdviceDescription;
    }

    public String getHealthAdvocacyDescription() {
        return healthAdvocacyDescription;
    }

    public void setHealthAdvocacyDescription(String healthAdvocacyDescription) {
        this.healthAdvocacyDescription = healthAdvocacyDescription;
    }

    public String getHealthEncouragementDescription() {
        return healthEncouragementDescription;
    }

    public void setHealthEncouragementDescription(String healthEncouragementDescription) {
        this.healthEncouragementDescription = healthEncouragementDescription;
    }

    public String getHealthOutcomeDescription() {
        return healthOutcomeDescription;
    }

    public void setHealthOutcomeDescription(String healthOutcomeDescription) {
        this.healthOutcomeDescription = healthOutcomeDescription;
    }

    public String getOrthoticDescription() {
        return orthoticDescription;
    }

    public void setOrthoticDescription(String orthoticDescription) {
        this.orthoticDescription = orthoticDescription;
    }

    public String getHealthGoalStatus() {
        return healthGoalStatus;
    }

    public void setHealthGoalStatus(String healthGoalStatus) {
        this.healthGoalStatus = healthGoalStatus;
    }

    public boolean isEducationAdviceChecked() {
        return isEducationAdviceChecked;
    }

    public void setEducationAdviceChecked(boolean educationAdviceChecked) {
        isEducationAdviceChecked = educationAdviceChecked;
    }

    public boolean isEducationAdvocacyChecked() {
        return isEducationAdvocacyChecked;
    }

    public void setEducationAdvocacyChecked(boolean educationAdvocacyChecked) {
        isEducationAdvocacyChecked = educationAdvocacyChecked;
    }

    public boolean isEducationEncouragementChecked() {
        return isEducationEncouragementChecked;
    }

    public void setEducationEncouragementChecked(boolean educationEncouragementChecked) {
        isEducationEncouragementChecked = educationEncouragementChecked;
    }

    public boolean isEducationOutcomeChecked() {
        return isEducationOutcomeChecked;
    }

    public void setEducationOutcomeChecked(boolean educationOutcomeChecked) {
        isEducationOutcomeChecked = educationOutcomeChecked;
    }

    public String getEducationGoalStatus() {
        return educationGoalStatus;
    }

    public void setEducationGoalStatus(String educationGoalStatus) {
        this.educationGoalStatus = educationGoalStatus;
    }

    public boolean isEducationRefChecked() {
        return isEducationRefChecked;
    }

    public void setEducationRefChecked(boolean educationRefChecked) {
        isEducationRefChecked = educationRefChecked;
    }

    public boolean isSocialAdviceChecked() {
        return isSocialAdviceChecked;
    }

    public void setSocialAdviceChecked(boolean socialAdviceChecked) {
        isSocialAdviceChecked = socialAdviceChecked;
    }

    public boolean isSocialAdvocacyChecked() {
        return isSocialAdvocacyChecked;
    }

    public void setSocialAdvocacyChecked(boolean socialAdvocacyChecked) {
        isSocialAdvocacyChecked = socialAdvocacyChecked;
    }

    public boolean isSocialRefChecked() {
        return isSocialRefChecked;
    }

    public void setSocialRefChecked(boolean socialRefChecked) {
        isSocialRefChecked = socialRefChecked;
    }

    public boolean isSocialEncouragementChecked() {
        return isSocialEncouragementChecked;
    }

    public void setSocialEncouragementChecked(boolean socialEncouragementChecked) {
        isSocialEncouragementChecked = socialEncouragementChecked;
    }

    public boolean isSocialOutcomeChecked() {
        return isSocialOutcomeChecked;
    }

    public void setSocialOutcomeChecked(boolean socialOutcomeChecked) {
        isSocialOutcomeChecked = socialOutcomeChecked;
    }

    public String getSocialGoalStatus() {
        return SocialGoalStatus;
    }

    public void setSocialGoalStatus(String socialGoalStatus) {
        SocialGoalStatus = socialGoalStatus;
    }
}
