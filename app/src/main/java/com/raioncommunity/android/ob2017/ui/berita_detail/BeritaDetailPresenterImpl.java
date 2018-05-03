package com.raioncommunity.android.ob2017.ui.berita_detail;

/**
 * Created by arifinfirdaus on 7/10/17.
 */

class BeritaDetailPresenterImpl implements BeritaDetailPresenter {

    private BeritaDetailView mBeritaDetailView;


    public BeritaDetailPresenterImpl(BeritaDetailView beritaDetailView) {
        this.mBeritaDetailView = beritaDetailView;
    }

    @Override
    public void attemptGetDataFromPreviousActivity() {
        mBeritaDetailView.updateUI();
    }
}
