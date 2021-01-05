package com.example.common.picture.api;

import android.content.Context;
import android.widget.ImageView;

public interface IPicture {
    void defaultImage(Context context, Object url, ImageView iv);
    void circletImage(Context context, Object url, ImageView iv);
    void roundImage(Context context, Object url,int radius, ImageView iv);
}
