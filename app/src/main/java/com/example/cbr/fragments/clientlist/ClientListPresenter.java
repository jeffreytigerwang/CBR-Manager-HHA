package com.example.cbr.fragments.clientlist;

public class ClientListPresenter implements ClientListContract.Presenter {

    private ClientListContract.View clientListView;

    public ClientListPresenter(ClientListContract.View clientListView) {
        this.clientListView = clientListView;
    }

    @Override
    public void onButtonClicked() {
        clientListView.displayString("Sample string, maybe get this from model");
    }
}
