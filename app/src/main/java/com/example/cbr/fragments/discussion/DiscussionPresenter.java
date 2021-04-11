package com.example.cbr.fragments.discussion;


public class DiscussionPresenter implements DiscussionContract.Presenter{
    private DiscussionContract.View discussionView;

    public DiscussionPresenter(DiscussionContract.View discussionView) {
        this.discussionView = discussionView;
    }
}
