package com.example.xyzreader.extras;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

public class ImageLoader {

    private static RequestOptions _requestOptions;

    @SuppressLint("CheckResult")
    public static void load(Context context, String url, ImageView img) {

        if (_requestOptions == null) {
            _requestOptions = new RequestOptions();
            _requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            _requestOptions.skipMemoryCache(true);
        }

        Glide.with(context)
                .load(url)
                .apply(_requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img);
    }
}
