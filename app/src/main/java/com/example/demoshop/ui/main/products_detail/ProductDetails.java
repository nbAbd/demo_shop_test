package com.example.demoshop.ui.main.products_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.demoshop.R;
import com.example.demoshop.data.model.Product;
import com.example.demoshop.ui.base.BaseActivity;

public class ProductDetails extends BaseActivity {

    public static Intent getStartIntent(Context context, Product product) {
        Intent i = new Intent(context, ProductDetails.class);
        i.putExtra("product", product);
        return i;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ImageView image = findViewById(R.id.product_image);
        TextView productName = findViewById(R.id.name);
        TextView productPrice = findViewById(R.id.price);
        TextView productCompany = findViewById(R.id.company);
        TextView productCategory = findViewById(R.id.category);
        TextView productDescription = findViewById(R.id.description);


        Product product = (Product) getIntent().getSerializableExtra("product");
        if (product != null) {
            Glide.with(this)
                    .load(product.getImg())
                    .placeholder(getResources().getDrawable(R.drawable.placeholder))
                    .error(getResources().getDrawable(R.drawable.placeholder))
                    .into(image);

            productName.setText(product.getName());
            String price = product.getPrice() + " сом";
            productPrice.setText(price);
            productCompany.setText(product.getCompany());
            productCategory.setText(product.getCategory());
            productDescription.setText(product.getDescription());
        }

        setUp();
    }

    public void setUp() {
        initToolbar(R.id.toolbar, "Детали");
        setHomeAsUp(true);
    }
}
