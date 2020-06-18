package com.example.demoshop.di.module;

import android.app.Application;
import android.content.Context;

import com.example.demoshop.utils.DataHelper;
import com.example.demoshop.utils.rx.AppSchedulerProvider;
import com.example.demoshop.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application mApplication; // it must be initialized


    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApplication;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Singleton
    @Provides
    public DataHelper provideDataHelper() {
        return new DataHelper(mApplication.getApplicationContext());
    }

}
