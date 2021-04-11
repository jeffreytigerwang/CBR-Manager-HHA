package com.example.cbr.fragments.home;


public class HomePagePresenter implements HomePageContract.Presenter {
    private HomePageContract.View homePageView;

    public HomePagePresenter(HomePageContract.View homePageView) {
        this.homePageView = homePageView;
    }
}
