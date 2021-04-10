package com.example.cbr.fragments.visitpage;

public class VisitPagePresenter implements VisitPageContract.Presenter {
    private VisitPageContract.View visitPageView;

    public VisitPagePresenter(VisitPageContract.View visitPageView) {
        this.visitPageView = visitPageView;

    }
}
