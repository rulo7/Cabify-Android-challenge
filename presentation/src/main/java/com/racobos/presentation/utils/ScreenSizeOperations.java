package com.racobos.presentation.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by rulo7 on 10/10/2016.
 */

public class ScreenSizeOperations {
    public static int getPxFromDps(Context context, int dps) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps, context.getResources().getDisplayMetrics());
    }
}
