package com.example.demoshop.ui.adapter;

import android.icu.text.Normalizer2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoshop.R;
import com.example.demoshop.data.model.Product;
import com.example.demoshop.databinding.ListItemProductBinding;
import com.example.demoshop.ui.main.ProductItemViewModel;

import java.util.ArrayList;
import java.util.List;

import io.supercharge.shimmerlayout.ShimmerLayout;
import timber.log.Timber;

public class ProductAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int ITEM_TYPE_LOADING = 0;
    private static final int ITEM_TYPE_NORMAL = 1;

    private static final int LOADING_ITEM_COUNT = 2;

    private boolean mIsLoading;

    private List<Product> products = new ArrayList<>();
    private OnProductClickListener mListener;


    private final List<ShimmerLayout> mShimmerLayouts = new ArrayList<>(LOADING_ITEM_COUNT);


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_loading_products, parent, false);
            return new LoadingViewHolder(view);
        } else if (viewType == ITEM_TYPE_NORMAL) {
            ListItemProductBinding binding = ListItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ProductViewHolder(binding);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mIsLoading) {
            return position == products.size() - 1 ? ITEM_TYPE_LOADING : ITEM_TYPE_NORMAL;
        } else {
            return ITEM_TYPE_NORMAL;
        }
    }

    public void setListener(OnProductClickListener mListener) {
        this.mListener = mListener;
    }

    public void setItems(List<Product> items) {
        this.products.clear();
        if (items != null) {
            this.products.addAll(items);
        }
        this.products = items;
        notifyDataSetChanged();
    }

    public void addItems(List<Product> items) {
        this.products.addAll(items);
        notifyItemInserted(products.size() - 1);
    }

    public void setLoading(boolean loading) {
        if (mIsLoading != loading)
            mIsLoading = loading;
        if (!mIsLoading) {
            for (ShimmerLayout shimmerLayout : mShimmerLayouts) {
                shimmerLayout.stopShimmerAnimation();
            }
            mShimmerLayouts.clear();
            notifyItemRangeRemoved(products.size(), LOADING_ITEM_COUNT);
        }
    }

    public class ProductViewHolder extends BaseViewHolder {
        ListItemProductBinding mBinding;

        ProductViewHolder(ListItemProductBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(final int position) {

            ProductItemViewModel viewModel = new ProductItemViewModel(products.get(position));
            mBinding.setViewModel(viewModel);

            Timber.e(products.get(position).getDescription());

            mBinding.executePendingBindings();

            mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onProductClick(products.get(position));
                    }
                }
            });
        }
    }

    private class LoadingViewHolder extends BaseViewHolder {
        private final ShimmerLayout mShimmerLayout;

        LoadingViewHolder(View itemView) {
            super(itemView);
            mShimmerLayout = (ShimmerLayout) itemView;
        }

        @Override
        public void onBind(int position) {
            mShimmerLayouts.add(mShimmerLayout);
            mShimmerLayout.startShimmerAnimation();
        }
    }

    public interface OnProductClickListener {
        void onProductClick(Product product);
    }
}
