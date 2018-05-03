package com.raioncommunity.android.ob2017.ui.nav_menu.berita

import android.app.Activity

/**
 * Created by arifinfirdaus on 7/6/17.
 */
// MARK: sample template
interface BeritaInteractor {

    interface OnFetchBeritaFinishedListener {

        fun onError()

        fun onSuccess()

    }

    fun fetchData(listener: OnFetchBeritaFinishedListener, activity: Activity)

}