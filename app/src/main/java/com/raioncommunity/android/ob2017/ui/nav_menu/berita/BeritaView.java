package com.raioncommunity.android.ob2017.ui.nav_menu.berita;

/**
 * Created by arifinfirdaus on 7/6/17.
 */

public interface BeritaView {

    void showToastMessage(String message);

    void toBeritaDetailActivity();

    void showProgressDialog();

    void hideProgressDialog();
}
