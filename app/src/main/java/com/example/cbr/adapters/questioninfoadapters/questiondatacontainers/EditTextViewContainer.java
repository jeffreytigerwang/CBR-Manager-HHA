package com.example.cbr.adapters.questioninfoadapters.questiondatacontainers;

public class EditTextViewContainer extends QuestionDataContainer {
    private final String questionText;
    private final String hintText;
    private final int inputType;

    private String userInput = "";

    public EditTextViewContainer(String questionText, String hintText, int inputType) {
        super(EDIT_TEXT_VIEW_TYPE);
        this.questionText = questionText;
        this.hintText = hintText;
        this.inputType = inputType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getHintText() {
        return hintText;
    }

    public int getInputType() {
        return inputType;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}