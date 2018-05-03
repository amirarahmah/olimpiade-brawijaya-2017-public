package com.raioncommunity.android.ob2017.model.view;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by arifinfirdaus on 7/20/17.
 */

public class OtherRenderableDrawable {

    @DrawableRes
    private int drawableIconRes;

    public OtherRenderableDrawable(int drawableIconRes) {
        this.drawableIconRes = drawableIconRes;
    }

    public int getDrawableIconRes() {
        return drawableIconRes;
    }

    public void setDrawableIconRes(int drawableIconRes) {
        this.drawableIconRes = drawableIconRes;
    }
}
