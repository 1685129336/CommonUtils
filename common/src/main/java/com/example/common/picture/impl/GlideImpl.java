package com.example.common.picture.impl;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.common.picture.api.IPicture;

public class GlideImpl implements IPicture {
    @Override
    public void defaultImage(Context context, Object url, ImageView iv) {
        Glide.with(context).load(url).into(iv);
    }

    @Override
    public void circletImage(Context context, Object url, ImageView iv) {
        Glide.with(context).load(url).transform(new CircleCrop()).into(iv);
    }

    @Override
    public void roundImage(Context context, Object url, int radius, ImageView iv) {
        Glide.with(context).load(url).transform(new RoundedCorners(radius)).into(iv);
    }
}
