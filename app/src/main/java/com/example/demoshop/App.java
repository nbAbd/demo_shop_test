package com.example.demoshop;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.example.demoshop.di.AppComponent;
import com.example.demoshop.di.DaggerAppComponent;
import com.example.demoshop.di.module.AppModule;
import com.example.demoshop.di.module.NetworkModule;

import timber.log.Timber;

@SuppressLint("Registered")
public class App extends Application {

    private AppComponent mComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = prepareAppComponent();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private AppComponent prepareAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("google.com"))
                .build();
    }

    public AppComponent getComponent() {
        return mComponent;
    }
}
