package com.example.cbr.models;

public class VisitRecord {

    private static VisitRecord instance = null;

    private boolean isHealthChecked;
    private boolean isEducationChecked;
    private boolean isSocialChecked;

    private VisitRecord() {
    }

    public static VisitRecord getInstance() {
        if (instance == null) {
            instance = new VisitRecord();
        }
        return instance;
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
