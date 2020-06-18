package com.example.demoshop.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoshop.data.model.Product;
import com.example.demoshop.databinding.ListItemProductBinding;
import com.example.demoshop.ui.main.ProductItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class SecondProductAdapter extends RecyclerView.Adapter<SecondProductAdapter.MyViewHolder> {

    private List<Product> mItems = new ArrayList<>();
    private OnProductItemClickListener productItemClickListener;

    private List<Product> getItems() {
        return mItems;
    }

    public void setItems(List<Product> items) {
        this.mItems.clear();

        if (getItems().size() > 0) {
            mItems.addAll(items);
        }
        this.mItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListItemProductBinding itemProductBinding = ListItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(itemProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    public void setClickListener(OnProductItemClickListener listener) {
        this.productItemClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ListItemProductBinding binding;


        MyViewHolder(@NonNull ListItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(final Product product) {
            ProductItemViewModel viewModel = new ProductItemViewModel(product);
            binding.setViewModel(viewModel);
            binding.executePendingBindings();

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (productItemClickListener != null) {
                        productItemClickListener.onItemClick(product);
                    }
                }
            });
        }
    }

    public interface OnProductItemClickListener {
        void onItemClick(Product product);
    }
}
