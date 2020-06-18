package com.example.demoshop.utils;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

public class BindingAdapters {

    @SuppressLint("CheckResult")
    @BindingAdapter(value = {"image", "progressView", "placeholder", "error"}, requireAll = false)
    public static void loadImage(ImageView imageView,
                                 @Nullable String image,
                                 @IdRes int progressView,
                                 @DrawableRes int placeholder,
                                 @DrawableRes int error) {

        if (image == null) {
            if (placeholder == 0 && error == 0) {
                imageView.setImageDrawable(null);
            } else if (error != 0) {
                imageView.setImageResource(error);
            } else {
                imageView.setImageResource(placeholder);
            }

            return;
        }

        RequestBuilder<Drawable> request = Glide.with(imageView).load(image).thumbnail(0.5f);

        RequestOptions requestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop();

        if (placeholder != 0) {
            requestOptions.placeholder(placeholder);
        }

        if (error != 0) {
            requestOptions.error(error);
        }

        View progress = null;

        if (progressView != 0) {
            try {
                progress = ((View) imageView.getParent()).findViewById(progressView);
            } catch (Exception ignored) {
            }
        }

        if (progress != null) {
            final View finalProgress = progress;
            progress.setVisibility(View.VISIBLE);
            request.listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    finalProgress.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    finalProgress.setVisibility(View.GONE);
                    return false;
                }
            });
        }

        request.apply(requestOptions)
                .into(imageView);

    }

    @BindingAdapter("android:text")
    public static void setTextWithSom(TextView textView, int price) {
        String p = price + " сом";
        textView.setText(p);
    }

}
