package com.example.cbr.fragments.map;


public class MapPresenter implements MapContract.Presenter{
    private MapContract.View MapView;

    public MapPresenter(MapContract.View MapView) {
        this.MapView = MapView;
    }
}
