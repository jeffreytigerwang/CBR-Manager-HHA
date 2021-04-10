package com.example.cbr.adapters.questioninfoadapters.questiondatacontainers;

public class ClickableViewContainer extends QuestionDataContainer {
    private final String clickableText;
    private final ClickableViewHolderBehavior clickableViewHolderBehavior;

    public ClickableViewContainer(String clickableText, ClickableViewHolderBehavior clickableViewHolderBehavior) {
        super(CLICKABLE_VIEW_TYPE);
        this.clickableText = clickableText;
        this.clickableViewHolderBehavior = clickableViewHolderBehavior;
    }

    public String getClickableText() {
        return clickableText;
    }

    public ClickableViewHolderBehavior getClickableViewHolderBehavior() {
        return clickableViewHolderBehavior;
    }

    public interface ClickableViewHolderBehavior {
        void onClick();
    }
}
