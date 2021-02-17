package com.example.cbr.models;

;

import java.io.Serializable;
import java.util.Date;

public class VisitCheckContainer implements Serializable {

    private String purposeOfVisit;
    private boolean isHealthChecked;
    private boolean isEducationChecked;
    private boolean isSocialChecked;
    private String siteLocation;
    private int siteLocationSpinnerSelectedPosition;

    private boolean isWheelChairChecked;
    private boolean isProstheticChecked;
    private boolean isOrthoticChecked;
    private boolean isWRChecked;
    private boolean isReferralToHCChecked;
    private boolean isHealthAdviceChecked;
    private boolean isHealthAdvocacyChecked;
    private boolean isHealthEncouragementChecked;
    private String healthGoalStatus;

    private boolean isEducationAdviceChecked;
    private boolean isEducationAdvocacyChecked;
    private boolean isEducationRefChecked;
    private boolean isEducationEncouragementChecked;

    private String educationGoalStatus;

    private boolean isSocialAdviceChecked;
    private boolean isSocialAdvocacyChecked;
    private boolean isSocialRefChecked;
    private boolean isSocialEncouragementChecked;
    private String SocialGoalStatus;

    public VisitCheckContainer() {
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

    public String getSiteLocation() {
        return siteLocation;
    }

    public void setSiteLocation(String siteLocation) {
        this.siteLocation = siteLocation;
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

    public String getSocialGoalStatus() {
        return SocialGoalStatus;
    }

    public void setSocialGoalStatus(String socialGoalStatus) {
        SocialGoalStatus = socialGoalStatus;
    }

    public int getSiteLocationSpinnerSelectedPosition() {
        return siteLocationSpinnerSelectedPosition;
    }

    public void setSiteLocationSpinnerSelectedPosition(int siteLocationSpinnerSelectedPosition) {
        this.siteLocationSpinnerSelectedPosition = siteLocationSpinnerSelectedPosition;
    }
}
