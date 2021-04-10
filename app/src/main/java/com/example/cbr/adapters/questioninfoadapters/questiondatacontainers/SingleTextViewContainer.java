package com.example.cbr.adapters.questioninfoadapters.questiondatacontainers;

public class SingleTextViewContainer extends QuestionDataContainer {
    private final String text;
    private final int textSizeSp;

    public SingleTextViewContainer(String text, int textSizeSp) {
        super(SINGLE_TEXT_VIEW_TYPE);
        this.text = text;
        this.textSizeSp = textSizeSp;
    }

    public String getText() {
        return text;
    }

    public int getTextSizeSp() {
        return textSizeSp;
    }
}
