package com.example.demoshop.ui.main.products;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.demoshop.App;
import com.example.demoshop.R;
import com.example.demoshop.data.model.Product;
import com.example.demoshop.databinding.ActivityMainBinding;
import com.example.demoshop.ui.adapter.PaginationListener;
import com.example.demoshop.ui.adapter.ProductAdapter;
import com.example.demoshop.ui.base.BaseMVVMActivity;
import com.example.demoshop.ui.main.products_detail.ProductDetails;

import java.util.List;

import javax.inject.Inject;

public class MainProductsActivity extends BaseMVVMActivity<ActivityMainBinding, MainProductsViewModel> implements MainProductsNavigator {

    @Inject
    MainProductsViewModel mViewModel;

    @Inject
    ProductAdapter mAdapter;

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel.setNavigator(this);
        mBinding = getViewDataBinding();

        setUp();
    }

    public void setUp() {
        initToolbar("Товары");
        setUpProductAdapter();
    }


    public void setUpProductAdapter() {
        RecyclerView recyclerView = findViewById(R.id.product_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        mAdapter.setListener(new ProductAdapter.OnProductClickListener() {
            @Override
            public void onProductClick(Product product) {
                startActivity(ProductDetails.getStartIntent(getApplicationContext(), product));
            }
        });

        recyclerView.setAdapter(mAdapter);
        mViewModel.fetchProducts();


    }


    @Override
    public void performDependencyInjection() {
        App.get(this).getComponent().inject(this);
    }

    @Override
    public MainProductsViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public int getBindingVariable() {
        return com.example.demoshop.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void populateProducts(List<Product> products) {
        mAdapter.setItems(products);

        if (mAdapter.getItemCount() == 0) {
            mBinding.productRv.setVisibility(View.GONE);
        } else {
            mBinding.productRv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void handleNetworkError(Throwable throwable) {

    }

    @Override
    public void showProgress() {
        mAdapter.setLoading(true);
    }

    @Override
    public void hideProgress() {
        mAdapter.setLoading(false);

        super.hideProgress();
    }
}
