package com.raioncommunity.android.ob2017.model.view;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by bradhawk on 9/19/2016.
 */
public class NavigationItem {
    private int id;
    @DrawableRes
    private int navIconRes;
    @StringRes
    private int navNameRes;

    private String navName;

    public NavigationItem(int id, @DrawableRes int navIconRes, @StringRes int navNameRes) {
        this.id = id;
        this.navIconRes = navIconRes;
        this.navNameRes = navNameRes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @DrawableRes
    public int getNavIconRes() {
        return navIconRes;
    }

    public void setNavIconRes(@DrawableRes int navIconRes) {
        this.navIconRes = navIconRes;
    }

    @StringRes
    public int getNavNameRes() {
        return navNameRes;
    }

    public void setNavNameRes(@StringRes int navNameRes) {
        this.navNameRes = navNameRes;
    }

    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }
}
