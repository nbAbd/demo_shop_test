package com.example.demoshop.ui.base;

public interface BaseNavigator {

    void handleNetworkError(Throwable throwable);

    void showProgress();

    void hideProgress();
}
