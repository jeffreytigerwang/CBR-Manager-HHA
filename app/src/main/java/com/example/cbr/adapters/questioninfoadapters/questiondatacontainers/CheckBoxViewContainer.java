package com.example.cbr.adapters.questioninfoadapters.questiondatacontainers;

public class CheckBoxViewContainer extends QuestionDataContainer {
    private final String questionText;

    private boolean isChecked = false;

    public CheckBoxViewContainer(String questionText) {
        super(CHECK_BOX_VIEW_TYPE);
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
