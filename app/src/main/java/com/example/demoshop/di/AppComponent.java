package com.example.demoshop.di;

import com.example.demoshop.ui.adapter.AdaptersModule;
import com.example.demoshop.ui.main.products.MainProductsActivity;
import com.example.demoshop.di.module.ApiModule;
import com.example.demoshop.di.module.AppModule;
import com.example.demoshop.di.module.NetworkModule;
import com.example.demoshop.ui.main.MainModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApiModule.class,
        AppModule.class,
        NetworkModule.class,
        MainModule.class,
        AdaptersModule.class
})

public interface AppComponent {

    void inject(MainProductsActivity activity);

}
