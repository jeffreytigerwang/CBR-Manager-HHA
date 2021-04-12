package com.example.cbr.models;

public class BaselineFoodAndNutritionSurveyData {

    private Integer clientId;

    private String  rateFoodSecurity;
    private boolean hasEnoughFoodEveryMonth;
    private String childNutrition;

    public BaselineFoodAndNutritionSurveyData() {
        this.rateFoodSecurity = "";
        this.hasEnoughFoodEveryMonth = false;
        this.childNutrition = "";
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getRateFoodSecurity() {
        return rateFoodSecurity;
    }

    public void setRateFoodSecurity(String rateFoodSecurity) {
        this.rateFoodSecurity = rateFoodSecurity;
    }

    public boolean isHasEnoughFoodEveryMonth() {
        return hasEnoughFoodEveryMonth;
    }

    public void setHasEnoughFoodEveryMonth(boolean hasEnoughFoodEveryMonth) {
        this.hasEnoughFoodEveryMonth = hasEnoughFoodEveryMonth;
    }

    public String getChildNutrition() {
        return childNutrition;
    }

    public void setChildNutrition(String childNutrition) {
        this.childNutrition = childNutrition;
    }
}
