package com.racobos.presentation.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by rulo7 on 13/10/2016.
 */

public class Renderer {
    public static void print(String src, ImageView view, @DrawableRes int errorPlaceHolder, int width, int height, boolean dpsSize) {
        Context context = view.getContext();
        if (dpsSize) {
            width = ScreenSizeOperations.getPxFromDps(context, width);
            height = ScreenSizeOperations.getPxFromDps(context, height);
        }
        Glide.with(context).load(src).override(width, height).error(errorPlaceHolder).into(view);
    }
}
