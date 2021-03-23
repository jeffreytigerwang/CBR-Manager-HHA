package com.example.cbr.adapters.questioninfoadapters.questiondatacontainers;

/**
 * A class that contains information on {@code recyclerview_unchangeable_edit_text.xml}.
 * <p>The {@code EditText} is set to be disabled; meaning the user will not be able to
 * interact with it. However, The text can be set progammically using
 * {@link UnchangeableEditTextViewContainer#textEditText textEditText}.
 *
 * @see com.example.cbr.adapters.questioninfoadapters.BaseInfoAdapter BaseInfoAdapter
 * */

public class UnchangeableEditTextViewContainer extends QuestionDataContainer {
    private final String questionText;
    private final int questionTextSizeSp;
    private final String textEditText;

    public UnchangeableEditTextViewContainer(String questionText, int questionTextSizeSp,
                                             String textEditText) {
        super(UNCHANGEABLE_EDIT_TEXT_VIEW_TYPE);
        this.questionText = questionText;
        this.questionTextSizeSp = questionTextSizeSp;
        this.textEditText = textEditText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getQuestionTextSizeSp() {
        return questionTextSizeSp;
    }

    public String getTextEditText() {
        return textEditText;
    }
}
