package com.example.cbr.fragments.clientpage;

public class ClientPagePresenter implements ClientPageContract.Presenter {

    private ClientPageContract.View clientListView;

    public ClientPagePresenter(ClientPageContract.View clientListView) {
        this.clientListView = clientListView;
    }
}
