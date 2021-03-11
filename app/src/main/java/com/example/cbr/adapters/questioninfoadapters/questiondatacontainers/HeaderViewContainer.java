package com.example.cbr.adapters.questioninfoadapters.questiondatacontainers;

public class HeaderViewContainer extends QuestionDataContainer {
    private final String headerText;

    public HeaderViewContainer(String headerText) {
        super(HEADER_VIEW_TYPE);
        this.headerText = headerText;
    }

    public String getHeaderText() {
        return headerText;
    }
}
