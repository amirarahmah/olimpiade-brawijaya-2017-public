package com.raioncommunity.android.ob2017.ui.nav_menu.settings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.dummy.DummyContent;
import com.raioncommunity.android.ob2017.model.view.CabangOlahraga;

import java.util.List;

/**
 * Created by arifinfirdaus on 11/6/17.
 */

public class FavoritSayaRecyclerViewAdapter extends RecyclerView.Adapter<FavoritSayaRecyclerViewAdapter.ViewHolder> implements SettingsView {

    private final List<DummyContent.DummyItem> mValues;
    private List<CabangOlahraga> mSelectedCaborFavoritList;
    private FavoritSayaRecyclerViewCallback mFavoritSayaRecyclerViewCallback;


    public FavoritSayaRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
        this.mValues = items;
    }

    public FavoritSayaRecyclerViewAdapter(List<DummyContent.DummyItem> mValues, List<CabangOlahraga> caborList, FavoritSayaRecyclerViewCallback favoritSayaRecyclerViewCallback) {
        this.mValues = mValues;
        this.mSelectedCaborFavoritList = caborList;
        this.mFavoritSayaRecyclerViewCallback = favoritSayaRecyclerViewCallback;
    }

    @Override
    public FavoritSayaRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_setting_cabang_olahraga, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FavoritSayaRecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);

        final CabangOlahraga cabangOlahraga = mSelectedCaborFavoritList.get(position);

        int iconResCabangOlahraga = cabangOlahraga.getCaborIconRes();
        String namaCabangOlahraga = cabangOlahraga.getCaborName();

        holder.mIvThumbnailCabangOlahraga.setImageResource(iconResCabangOlahraga);
        holder.mTvNamaCabangOlahraga.setText(namaCabangOlahraga);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFavoritSayaRecyclerViewCallback.onAttemptRemoveItem(cabangOlahraga, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSelectedCaborFavoritList.size();
    }


    public void addItem(CabangOlahraga cabangOlahraga) {
        mSelectedCaborFavoritList.add(cabangOlahraga);
    }

    public void updateData(List<CabangOlahraga> cabangOlahragaList) {
        mSelectedCaborFavoritList.clear();
        mSelectedCaborFavoritList.addAll(cabangOlahragaList);
    }


    @Override
    public void updateUI(CabangOlahraga cabangOlahragaBaru) {
        mSelectedCaborFavoritList.add(cabangOlahragaBaru);
        notifyDataSetChanged();


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIvThumbnailCabangOlahraga;
        public final TextView mTvNamaCabangOlahraga;
        public DummyContent.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIvThumbnailCabangOlahraga = (ImageView) view.findViewById(R.id.iv_thumbnail_cabor_settings);
            mTvNamaCabangOlahraga = (TextView) view.findViewById(R.id.tv_nama_cabor_settings);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTvNamaCabangOlahraga.getText() + "'";
        }
    }
}
