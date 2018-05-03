package com.raioncommunity.android.ob2017.ui.nav_menu.settings

import com.raioncommunity.android.ob2017.model.view.CabangOlahraga

/**
 * Created by arifinfirdaus on 7/31/17.
 */
interface FavoritSayaRecyclerViewCallback {

    fun onAttemptRemoveItem(cabangOlahraga: CabangOlahraga, position: Int)

}