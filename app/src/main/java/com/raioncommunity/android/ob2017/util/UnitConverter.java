package com.raioncommunity.android.ob2017.util;

import android.content.Context;

/**
 * Created by arifinfirdaus on 9/11/17.
 */

public class UnitConverter {

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
