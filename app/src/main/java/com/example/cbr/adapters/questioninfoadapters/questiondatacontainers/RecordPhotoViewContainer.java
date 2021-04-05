package com.example.cbr.adapters.questioninfoadapters.questiondatacontainers;

import android.graphics.Bitmap;

public class RecordPhotoViewContainer extends QuestionDataContainer {
    private final String questionText;
    private Bitmap image;

    public RecordPhotoViewContainer(String questionText) {
        super(PHOTO_VIEW_TYPE);
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
