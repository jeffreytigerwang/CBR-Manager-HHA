package com.example.cbr.models;

public class ClientDisability {
    private Integer clientId;

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

    public ClientDisability(int id, boolean amputeeDisability, boolean polioDisability, boolean spinalCordInjuryDisability, boolean cerebralPalsyDisability, boolean spinaBifidaDisability,
                            boolean hydrocephalusDisability, boolean visualImpairmentDisability, boolean hearingImpairmentDisability, boolean doNotKnowDisability, boolean otherDisability,
                            String describeOtherDisability) {
        this.clientId = id;
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
    }

    public Integer getId() {
        return clientId;
    }

    public void setId(Integer id) {
        this.clientId = id;
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
}
