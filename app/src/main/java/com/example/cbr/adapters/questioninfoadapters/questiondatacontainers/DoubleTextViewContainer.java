package com.example.cbr.adapters.questioninfoadapters.questiondatacontainers;

public class DoubleTextViewContainer extends QuestionDataContainer {
    private final String firstText;
    private final String secondText;

    public DoubleTextViewContainer(String firstText, String secondText) {
        super(DOUBLE_TEXT_VIEW_TYPE);
        this.firstText = firstText;
        this.secondText = secondText;
    }

    public String getFirstText() {
        return firstText;
    }

    public String getSecondText() {
        return secondText;
    }
}
