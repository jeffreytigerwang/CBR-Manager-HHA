package com.example.cbr.fragments.clientpage;

public class ClientPagePresenter implements ClientPageContract.Presenter {

    private ClientPageContract.View clientPageView;

    public ClientPagePresenter(ClientPageContract.View clientPageView) {
        this.clientPageView = clientPageView;
    }
}
