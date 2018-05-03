package com.raioncommunity.android.ob2017.ui.nav_menu.settings.add_favorite_dialog;

import android.app.Dialog;

import com.raioncommunity.android.ob2017.model.view.CabangOlahraga;
import com.raioncommunity.android.ob2017.ui.nav_menu.settings.FavoritSayaRecyclerViewAdapter;

/**
 * Created by arifinfirdaus on 7/19/17.
 */

public interface AddFavoriteDialogCallback {

    void onItemClick(CabangOlahraga item, Dialog currentDialog, FavoritSayaRecyclerViewAdapter favoritSayaRecyclerViewAdapter);

    void updateUI(CabangOlahraga item);
}
