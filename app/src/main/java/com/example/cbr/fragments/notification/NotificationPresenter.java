package com.example.cbr.fragments.notification;


public class NotificationPresenter implements NotificationContract.Presenter {
    private NotificationContract.View NotificationView;

    public NotificationPresenter(NotificationContract.View NotificationView) {
        this.NotificationView = NotificationView;
    }
}
