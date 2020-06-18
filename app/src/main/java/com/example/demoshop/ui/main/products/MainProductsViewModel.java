package com.example.demoshop.ui.main.products;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.demoshop.data.model.Product;
import com.example.demoshop.ui.base.BaseViewModel;
import com.example.demoshop.utils.DataHelper;
import com.example.demoshop.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import timber.log.Timber;

public class MainProductsViewModel extends BaseViewModel<MainProductsNavigator> {


    private DataHelper mDataHelper;
    private Gson mGson;

    public MainProductsViewModel(SchedulerProvider schedulerProvider, Gson gson, DataHelper dataHelper) {
        super(schedulerProvider);
        mGson = gson;
        mDataHelper = dataHelper;
    }

    public List<Product> getProducts() {
        String jsonFileString = mDataHelper.getJsonFromAssets("products.json");

        TypeToken<List<Product>> token = new TypeToken<List<Product>>() {
        };

        List<Product> products = mGson.fromJson(jsonFileString, token.getType());

        Timber.d(products.get(0).getDescription());

        return products;
    }

    void fetchProducts() {
        getNavigator().populateProducts(getProducts());
    }
}
