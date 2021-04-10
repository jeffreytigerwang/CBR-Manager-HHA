package com.example.cbr.adapters.questioninfoadapters.questiondatacontainers;

import java.util.List;

public class RadioGroupViewContainer extends QuestionDataContainer {
    private final String questionText;
    private final boolean isVertical;
    private final List<RadioGroupListItem> descriptionList;
    private int checkedIndex;

    public RadioGroupViewContainer(String questionText, boolean isVertical, List<RadioGroupListItem> descriptionList) {
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

    /**
     * Gets the checked item at {@link RadioGroupViewContainer#checkedIndex}
     * for any checked items in {@link RadioGroupViewContainer#descriptionList}.
     * */
    public RadioGroupListItem getCheckedItem() {
        return descriptionList.get(checkedIndex);
    }

    public void setCheckedIndex(int checkedIndex) {
        this.checkedIndex = checkedIndex;
    }

    /**
     * Gets the checked item at {@link RadioGroupViewContainer#checkedIndex}
     * for any checked items in {@link RadioGroupViewContainer#descriptionList}.
     * */
    public RadioGroupListItem getCheckedItem() {
        return descriptionList.get(checkedIndex);
    }

    public static class RadioGroupListItem {
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

        public void setChecked(boolean isChecked) {
            this.isChecked = isChecked;
        }

        public int getId() {
            return id;
        }
    }

}
