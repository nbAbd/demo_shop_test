package com.example.demoshop.ui.main.products;

import com.example.demoshop.data.model.Product;
import com.example.demoshop.ui.base.BaseNavigator;

import java.util.List;

public interface MainProductsNavigator extends BaseNavigator {

    void populateProducts(List<Product> products);
}
