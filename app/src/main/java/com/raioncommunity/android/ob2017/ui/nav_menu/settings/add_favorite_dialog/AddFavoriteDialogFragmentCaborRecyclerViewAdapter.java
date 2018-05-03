package com.raioncommunity.android.ob2017.ui.nav_menu.settings.add_favorite_dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.view.CabangOlahraga;
import com.raioncommunity.android.ob2017.ui.nav_menu.settings.FavoritSayaRecyclerViewAdapter;

import java.util.List;

import static com.raioncommunity.android.ob2017.debug.Tag.ADD_FAVORITE_DIALOG_TAG;

/**
 * Created by arifinfirdaus on 7/11/17.
 */


class AddFavoriteDialogFragmentCaborRecyclerViewAdapter extends RecyclerView.Adapter<AddFavoriteDialogFragmentCaborRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<CabangOlahraga> mCaborList;
    private AddFavoriteDialogCallback mCallback;
    private Dialog mDialog;
    private FavoritSayaRecyclerViewAdapter mFavoritSayaRecyclerViewAdapter;


    public AddFavoriteDialogFragmentCaborRecyclerViewAdapter(Context mContext, List<CabangOlahraga> caborList, AddFavoriteDialogCallback callback, Dialog dialog, FavoritSayaRecyclerViewAdapter favoritSayaRecyclerViewAdapter) {
        this.mContext = mContext;
        this.mCaborList = caborList;
        this.mCallback = callback;
        this.mDialog = dialog;
        this.mFavoritSayaRecyclerViewAdapter = favoritSayaRecyclerViewAdapter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(mContext)
                .inflate(
                        R.layout.item_add_favorite_cabang_olahraga,
                        parent,
                        false
                );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CabangOlahraga currentItem = mCaborList.get(position);
        holder.mTvNamaCabangOlahraga.setText(currentItem.getCaborName());
        Log.d(ADD_FAVORITE_DIALOG_TAG, "onBindViewHolder: currentItemCaborName : " + currentItem.getCaborName()); // null namenya
        holder.mIvCabangOlahraga.setImageResource(currentItem.getCaborIconRes());

        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CabangOlahraga cabangOlahragaItem = mCaborList.get(position);
                mCallback.onItemClick(cabangOlahragaItem, mDialog, mFavoritSayaRecyclerViewAdapter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCaborList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout mLayout;
        public ImageView mIvCabangOlahraga;
        public TextView mTvNamaCabangOlahraga;

        public ViewHolder(View itemView) {
            super(itemView);

            mLayout = (RelativeLayout) itemView.findViewById(R.id.addFavoriteItem_parent);
            mIvCabangOlahraga = (ImageView) itemView.findViewById(R.id.addFavoriteItem_icon);
            mTvNamaCabangOlahraga = (TextView) itemView.findViewById(R.id.addFavoriteItem_name);
        }
    }
}