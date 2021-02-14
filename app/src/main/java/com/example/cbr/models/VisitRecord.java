package com.example.cbr.models;

import java.io.Serializable;

public class VisitRecord implements Serializable {

    private boolean isHealthChecked;
    private boolean isEducationChecked;
    private boolean isSocialChecked;

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
}
