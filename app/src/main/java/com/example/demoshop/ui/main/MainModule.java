package com.example.demoshop.ui.main;

import com.example.demoshop.ui.main.products.MainProductsViewModel;
import com.example.demoshop.utils.DataHelper;
import com.example.demoshop.utils.rx.SchedulerProvider;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    MainProductsViewModel provideMainProductsViewModel(SchedulerProvider schedulerProvider, Gson gson, DataHelper dataHelper) {
        return new MainProductsViewModel(schedulerProvider, gson, dataHelper);
    }
}
