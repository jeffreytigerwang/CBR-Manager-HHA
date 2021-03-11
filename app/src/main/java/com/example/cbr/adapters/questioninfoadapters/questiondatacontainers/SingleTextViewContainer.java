package com.example.cbr.adapters.questioninfoadapters.questiondatacontainers;

public class SingleTextViewContainer extends QuestionDataContainer {
    private final String text;

    public SingleTextViewContainer(String text) {
        super(SINGLE_TEXT_VIEW_TYPE);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
