package com.example.cbr.models;

public class ReferralInfo {

    private boolean requirePhysiotherapy;
    private boolean requireProsthetic;
    private boolean requireOrthotic;
    private boolean requireWheelchair;
    private boolean requireOther;
    private String otherDescription;
    private boolean amputeeDisability;
    private boolean polioDisability;
    private boolean spinalCordInjuryDisability;
    private boolean cerebralPalsyDisability;
    private boolean spinaBifidaDisability;
    private boolean hydrocephalusDisability;
    private boolean visualImpairmentDisability;
    private boolean hearingImpairmentDisability;
    private boolean otherDisability;
    private boolean isInjuryAboveKnee;
    private boolean isInjuryBelowKnee;
    private boolean isIntermediateWheelchairUser;
    private Integer hipWidth;
    private boolean hasExistingWheelchair;
    private boolean canRepairWheelchair;
    private String outcome;

    public ReferralInfo() {

    }

    public ReferralInfo(boolean requirePhysiotherapy,
                        boolean requireProsthetic,
                        boolean requireOrthotic,
                        boolean requireWheelchair,
                        boolean requireOther,
                        String otherDescription,
                        boolean amputeeDisability,
                        boolean polioDisability,
                        boolean spinalCordInjuryDisability,
                        boolean cerebralPalsyDisability,
                        boolean spinaBifidaDisability,
                        boolean hydrocephalusDisability,
                        boolean visualImpairmentDisability,
                        boolean hearingImpairmentDisability,
                        boolean otherDisability,
                        boolean isInjuryAboveKnee,
                        boolean isInjuryBelowKnee,
                        boolean isIntermediateWheelchairUser,
                        Integer hipWidth,
                        boolean hasExistingWheelchair,
                        boolean canRepairWheelchair,
                        String outcome) {
        this.requirePhysiotherapy = requirePhysiotherapy;
        this.requireProsthetic = requireProsthetic;
        this.requireOrthotic = requireOrthotic;
        this.requireWheelchair = requireWheelchair;
        this.requireOther = requireOther;
        this.otherDescription = otherDescription;
        this.amputeeDisability = amputeeDisability;
        this.polioDisability = polioDisability;
        this.spinalCordInjuryDisability = spinalCordInjuryDisability;
        this.cerebralPalsyDisability = cerebralPalsyDisability;
        this.spinaBifidaDisability = spinaBifidaDisability;
        this.hydrocephalusDisability = hydrocephalusDisability;
        this.visualImpairmentDisability = visualImpairmentDisability;
        this.hearingImpairmentDisability = hearingImpairmentDisability;
        this.otherDisability = otherDisability;
        this.isInjuryAboveKnee = isInjuryAboveKnee;
        this.isInjuryBelowKnee = isInjuryBelowKnee;
        this.isIntermediateWheelchairUser = isIntermediateWheelchairUser;
        this.hipWidth = hipWidth;
        this.hasExistingWheelchair = hasExistingWheelchair;
        this.canRepairWheelchair = canRepairWheelchair;
        this.outcome = outcome;
    }

    public boolean isRequirePhysiotherapy() {
        return requirePhysiotherapy;
    }

    public void setRequirePhysiotherapy(boolean requirePhysiotherapy) {
        this.requirePhysiotherapy = requirePhysiotherapy;
    }

    public boolean isRequireProsthetic() {
        return requireProsthetic;
    }

    public void setRequireProsthetic(boolean requireProsthetic) {
        this.requireProsthetic = requireProsthetic;
    }

    public boolean isRequireOrthotic() {
        return requireOrthotic;
    }

    public void setRequireOrthotic(boolean requireOrthotic) {
        this.requireOrthotic = requireOrthotic;
    }

    public boolean isRequireWheelchair() {
        return requireWheelchair;
    }

    public void setRequireWheelchair(boolean requireWheelchair) {
        this.requireWheelchair = requireWheelchair;
    }

    public boolean isRequireOther() {
        return requireOther;
    }

    public void setRequireOther(boolean requireOther) {
        this.requireOther = requireOther;
    }

    public String getOtherDescription() {
        return otherDescription;
    }

    public void setOtherDescription(String otherDescription) {
        this.otherDescription = otherDescription;
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

    public boolean isOtherDisability() {
        return otherDisability;
    }

    public void setOtherDisability(boolean otherDisability) {
        this.otherDisability = otherDisability;
    }

    public boolean isInjuryAboveKnee() {
        return isInjuryAboveKnee;
    }

    public void setInjuryAboveKnee(boolean injuryAboveKnee) {
        isInjuryAboveKnee = injuryAboveKnee;
    }

    public boolean isInjuryBelowKnee() {
        return isInjuryBelowKnee;
    }

    public void setInjuryBelowKnee(boolean injuryBelowKnee) {
        isInjuryBelowKnee = injuryBelowKnee;
    }

    public boolean isIntermediateWheelchairUser() {
        return isIntermediateWheelchairUser;
    }

    public void setIntermediateWheelchairUser(boolean intermediateWheelchairUser) {
        isIntermediateWheelchairUser = intermediateWheelchairUser;
    }

    public Integer getHipWidth() {
        return hipWidth;
    }

    public void setHipWidth(Integer hipWidth) {
        this.hipWidth = hipWidth;
    }

    public boolean isHasExistingWheelchair() {
        return hasExistingWheelchair;
    }

    public void setHasExistingWheelchair(boolean hasExistingWheelchair) {
        this.hasExistingWheelchair = hasExistingWheelchair;
    }

    public boolean isCanRepairWheelchair() {
        return canRepairWheelchair;
    }

    public void setCanRepairWheelchair(boolean canRepairWheelchair) {
        this.canRepairWheelchair = canRepairWheelchair;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
}
