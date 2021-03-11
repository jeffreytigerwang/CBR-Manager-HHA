package com.example.cbr.adapters.questioninfoadapters;

import java.io.Serializable;
import java.util.List;

public class QuestionContainerHelper {

    public static final int SINGLE_TEXT_VIEW_TYPE = 0;
    public static final int DOUBLE_TEXT_VIEW_TYPE = 1;
    public static final int HEADER_VIEW_TYPE = 2;
    public static final int CLICKABLE_VIEW_TYPE = 3;
    public static final int DIVIDER_VIEW_TYPE = 4;
    public static final int EDIT_TEXT_VIEW_TYPE = 5;
    public static final int RADIO_GROUP_VIEW_TYPE = 6;
    public static final int SPINNER_VIEW_TYPE = 7;

    public static class QuestionDataContainer implements Serializable {
        private final int viewType;

        QuestionDataContainer(int viewType) {
            this.viewType = viewType;
        }

        public int getViewType() {
            return viewType;
        }
    }

    static class SingleTextViewContainer extends QuestionDataContainer {
        private final String text;

        SingleTextViewContainer(String text) {
            super(SINGLE_TEXT_VIEW_TYPE);
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    static class DoubleTextViewContainer extends QuestionDataContainer {
        private final String firstText;
        private final String secondText;

        DoubleTextViewContainer(String firstText, String secondText) {
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

    static class HeaderViewContainer extends QuestionDataContainer {
        private final String headerText;

        HeaderViewContainer(String headerText) {
            super(HEADER_VIEW_TYPE);
            this.headerText = headerText;
        }

        public String getHeaderText() {
            return headerText;
        }
    }

    static class ClickableViewContainer extends QuestionDataContainer {
        private final String clickableText;
        private final ClickableViewHolderBehavior clickableViewHolderBehavior;

        ClickableViewContainer(String clickableText, ClickableViewHolderBehavior clickableViewHolderBehavior) {
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
    }

    interface ClickableViewHolderBehavior {
        void onClick();
    }

    class DividerViewContainer extends QuestionDataContainer {
        DividerViewContainer() {
            super(DIVIDER_VIEW_TYPE);
        }
    }

    static class EditTextViewContainer extends QuestionDataContainer {
        private final String questionText;
        private final String hintText;
        private final int inputType;

        private String userInput = "";

        EditTextViewContainer(String questionText, String hintText, int inputType) {
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

    static class RadioGroupViewContainer extends QuestionDataContainer {
        private final String questionText;
        private final boolean isVertical;
        private final List<RadioGroupListItem> descriptionList;
        private int checkedIndex;

        RadioGroupViewContainer(String questionText, boolean isVertical, List<RadioGroupListItem> descriptionList) {
            super(RADIO_GROUP_VIEW_TYPE);
            this.questionText = questionText;
            this.isVertical = isVertical;
            this.descriptionList = descriptionList;
        }

        public String getQuestionText() {
            return questionText;
        }

        public boolean isVertical() {
            return isVertical;
        }

        public List<RadioGroupListItem> getDescriptionList() {
            return descriptionList;
        }

        public int getCheckedIndex() {
            return checkedIndex;
        }

        public void setCheckedIndex(int checkedIndex) {
            this.checkedIndex = checkedIndex;
        }
    }

    static class RadioGroupListItem {
        private final String description;
        private boolean isChecked;
        private final int id;

        public RadioGroupListItem(String description, boolean isChecked, int id) {
            this.description = description;
            this.isChecked = isChecked;
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public int getId() {
            return id;
        }
    }

    static class SpinnerViewContainer extends QuestionDataContainer {
        private final String questionText;
        private final List<String> optionsList;
        private String selectedItem;

        SpinnerViewContainer(String questionText, List<String> optionsList) {
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
}
