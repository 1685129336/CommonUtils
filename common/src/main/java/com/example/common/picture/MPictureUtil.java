package com.example.common.picture;

import android.content.Context;
import android.widget.ImageView;

import com.example.common.picture.api.IPicture;
import com.example.common.picture.impl.GlideImpl;

public class MPictureUtil {
    private static volatile MPictureUtil util;
    public static MPictureUtil getInstance(){
        if(util==null){
            synchronized (MPictureUtil.class){
                if(util==null){
                    util = new MPictureUtil();
                }
            }
        }
        return util;
    }
    public static void init(IPicture pictureImpl){
        picture = pictureImpl;
    }

    public MPictureUtil() {
    }

    private static IPicture picture = new GlideImpl();

    public void defaultImage(Context context, Object url, ImageView iv){
        picture.defaultImage(context, url, iv);
    }
    public void circletImage(Context context, Object url, ImageView iv){
        picture.circletImage(context, url, iv);
    }
    public void roundImage(Context context, Object url,int radius, ImageView iv){
        picture.roundImage(context, url, radius, iv);
    }
}
