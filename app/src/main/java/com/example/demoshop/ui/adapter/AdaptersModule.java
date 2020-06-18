package com.example.demoshop.ui.adapter;

import dagger.Module;
import dagger.Provides;

@Module
public class AdaptersModule {

    @Provides
    ProductAdapter provideProductAdapter() {
        return new ProductAdapter();
    }
}
