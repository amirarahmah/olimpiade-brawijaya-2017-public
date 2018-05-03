package com.raioncommunity.android.ob2017.ui.nav_menu.berita

import android.app.Activity
import android.content.Context

/**
 * Created by arifinfirdaus on 7/6/17.
 */


class BeritaPresenterImpl : BeritaPresenter, BeritaInteractor.OnFetchBeritaFinishedListener {


    private var mBeritaView: BeritaView? = null
    private var mBeritaInteractor: BeritaInteractor? = null
    private var mContext: Context? = null


    constructor(beritaView: BeritaView, context: Context) {
        this.mBeritaView = beritaView
        this.mBeritaInteractor = BeritaInteractorImpl()
        this.mContext = context
    }


    override fun attemptFetchBerita() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        mBeritaInteractor?.fetchData(this, mContext as Activity)
    }

    override fun attemptNavigateToDetailBeritaActivity() {
        mBeritaView?.toBeritaDetailActivity()
    }

    override fun onError() {
        mBeritaView?.showToastMessage("Error fetch data")
    }

    override fun onSuccess() {
        mBeritaView?.showToastMessage("Sukes fetch data")
    }
}
