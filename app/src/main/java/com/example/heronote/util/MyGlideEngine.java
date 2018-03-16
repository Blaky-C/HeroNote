package com.example.heronote.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.zhihu.matisse.engine.impl.GlideEngine;

/**
 * Created by SQS on 2018/3/17.
 */

public class MyGlideEngine extends GlideEngine {

    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
//        GlideApp.with(context)
//                .asBitmap()  // some .jpeg files are actually gif
//                .load(uri)
//                .placeholder(placeholder)
//                .override(resize, resize)
//                .centerCrop()
//                .into(imageView);
        Glide.with(context)
                .asBitmap()  // some .jpeg files are actually gif
                .load(uri)
                .apply(new RequestOptions()
                        .placeholder(placeholder)
                        .override(resize, resize)
                        .centerCrop())
                .into(imageView);
    }

    @Override
    public void loadAnimatedGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView,
                                         Uri uri) {
//        GlideApp.with(context)
//                .asBitmap()
//                .load(uri)
//                .placeholder(placeholder)
//                .override(resize, resize)
//                .centerCrop()
//                .into(imageView);
        Glide.with(context)
                .asBitmap()
                .load(uri)
                .apply(new RequestOptions()
                        .placeholder(placeholder)
                        .override(resize, resize)
                        .centerCrop())
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
//        GlideApp.with(context)
//                .load(uri)
//                .override(resizeX, resizeY)
//                .priority(Priority.HIGH)
//                .into(imageView);
        Glide.with(context)
                .load(uri)
                .apply(new RequestOptions()
                        .override(resizeX, resizeY)
                        .priority(Priority.HIGH))
                .into(imageView);
    }

    @Override
    public void loadAnimatedGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
//        GlideApp.with(context)
//                .asGif()
//                .load(uri)
//                .override(resizeX, resizeY)
//                .priority(Priority.HIGH)
//                .into(imageView);
        Glide.with(context)
                .asGif()
                .load(uri)
                .apply(new RequestOptions()
                        .override(resizeX, resizeY)
                        .priority(Priority.HIGH))
                .into(imageView);
    }

}
