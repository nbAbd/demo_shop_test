package com.example.demoshop.ui.main;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.demoshop.data.model.Product;

public class ProductItemViewModel extends ViewModel {

    public ObservableField<Product> product = new ObservableField<>();

    public ProductItemViewModel(Product product) {
        setProduct(product);
    }

    private void setProduct(Product product) {
        this.product.set(product);
    }
}
