package com.example.cbr.adapters.questioninfoadapters.questiondatacontainers;

/**
 * A class that contains information on {@code recyclerview_checkbox_with_description.xml},
 * which are used to customize the views or retrieve user input.
 * <p>The {@code EditText} will only be <strong>VISIBLE</strong>
 * to the user when the {@code CheckBox} attached
 * is checked, <strong>GONE</strong> when not.
 * */

public class CheckBoxWithDescriptionViewContainer extends QuestionDataContainer {
    private final String checkBoxText;
    private final String editTextHint;
    private final int inputType;

    private boolean isChecked = false;
    private String userInput = "";

    public CheckBoxWithDescriptionViewContainer(String checkBoxText,
                                                String editTextHint, int inputType) {
        super(CHECK_BOX_WITH_DESCRIPTION_VIEW_TYPE);
        this.checkBoxText = checkBoxText;
        this.editTextHint = editTextHint;
        this.inputType = inputType;
    }

    public String getCheckBoxText() {
        return checkBoxText;
    }

    public String getEditTextHint() {
        return editTextHint;
    }

    public int getInputType() {
        return inputType;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}
