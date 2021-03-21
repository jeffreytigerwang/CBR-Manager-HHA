package com.example.cbr.adapters.questioninfoadapters.questiondatacontainers;

import java.util.List;

public class SpinnerViewContainer extends QuestionDataContainer {
    private final String questionText;
    private final List<String> optionsList;
    private String selectedItem;

    public SpinnerViewContainer(String questionText, List<String> optionsList) {
        super(SPINNER_VIEW_TYPE);
        this.questionText = questionText;
        this.optionsList = optionsList;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptionsList() {
        return optionsList;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }
}
