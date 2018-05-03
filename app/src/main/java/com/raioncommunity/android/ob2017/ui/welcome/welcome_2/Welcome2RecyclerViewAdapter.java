package com.raioncommunity.android.ob2017.ui.welcome.welcome_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.dummy.DummyContent;
import com.raioncommunity.android.ob2017.model.view.CabangOlahraga;
import com.raioncommunity.android.ob2017.model.view.CaborWelcome;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

import static com.raioncommunity.android.ob2017.debug.Tag.Welcome2IndicatorTag;

/**
 * Created by arifinfirdaus on 7/22/17.
 */

// selected recyclerview mAdapter
// https://stackoverflow.com/questions/36369913/how-to-implement-multi-select-in-recyclerview

public class Welcome2RecyclerViewAdapter extends RecyclerView.Adapter<Welcome2RecyclerViewAdapter.ViewHolder> {


    private final List<DummyContent.DummyItem> mValues;
    private final List<CaborWelcome> mCaborListOld;
    private final Context mContext;
    private List<CaborWelcome> mSelectedCaborFavoritList;
    private Welcome2FragmentCallback mCallback;

    private List<CabangOlahraga> mCaborList;


    public Welcome2RecyclerViewAdapter(List<DummyContent.DummyItem> mValues, List<CaborWelcome> mCaborList, Context context) {
        this.mValues = mValues;
        this.mCaborListOld = mCaborList;
        this.mContext = context;
        this.mSelectedCaborFavoritList = new ArrayList<>();
    }

    public Welcome2RecyclerViewAdapter(List<DummyContent.DummyItem> mValues, List<CaborWelcome> mCaborList, Context context, Welcome2FragmentCallback callback, List<CabangOlahraga> cabangOlahragaList) {
        this.mValues = mValues;
        this.mCaborListOld = mCaborList;
        this.mContext = context;
        this.mSelectedCaborFavoritList = new ArrayList<>();
        this.mCallback = callback;
        this.mCaborList = cabangOlahragaList;
    }

    @Override
    public Welcome2RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cabang_olahraga_favorit_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Welcome2RecyclerViewAdapter.ViewHolder holder, final int position) {
        // holder.mItem = mValues.get(position);


        // cek selected
        // final CaborWelcome currentCaborWelcome = mCaborListOld.get(position);

        final CabangOlahraga currentCabangOlahraga = mCaborList.get(position);

//        holder.mIvIndicatorSelected.setVisibility(
//                currentCaborWelcome.isSelected() ? View.VISIBLE : View.INVISIBLE
//        );

        holder.mIvIndicatorSelected.setVisibility(
                currentCabangOlahraga.isSelected() ? View.VISIBLE : View.INVISIBLE
        );

        Log.d(
                Welcome2IndicatorTag,
                "onBindViewHolder:  currentCabangOlahraga.isSelected(): " + currentCabangOlahraga.isSelected()
        );

        if (currentCabangOlahraga.isSelected()) {

            // TODO: 8/31/17 show indikator di current holder

        }

        int currentHolderPosition = holder.getAdapterPosition();


        holder.mIvIndicatorSelected.setVisibility(
                currentCabangOlahraga.isSelected() ? View.VISIBLE : View.INVISIBLE
        );

//        holder.mIvThumbnailCaborFavorit.setImageResource(mCaborListOld.get(position).getCaborImageRes());
//        holder.mTvNamaCaborFavorit.setText(mCaborListOld.get(position).getCaborName());

        holder.mIvThumbnailCaborFavorit.setImageResource(mCaborList.get(position).getCaborIconRes());
        holder.mTvNamaCaborFavorit.setText(mCaborList.get(position).getCaborName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // currentCaborWelcome.setSelected(!currentCaborWelcome.isSelected());
                currentCabangOlahraga.setSelected(!currentCabangOlahraga.isSelected());

                // Toast.makeText(v.getContext(), "pilihan Cabor posisi : " + position + " Selected : " + currentCaborWelcome.isSelected(), Toast.LENGTH_SHORT).show();

//                holder.mIvIndicatorSelected.setVisibility(
//                        currentCaborWelcome.isSelected() ? View.VISIBLE : View.INVISIBLE
//                );

                holder.mIvIndicatorSelected.setVisibility(
                        currentCabangOlahraga.isSelected() ? View.VISIBLE : View.INVISIBLE
                );

                // TODO: 8/31/17 ganti ke cabor welcome
                mCallback.onItemClick(currentCabangOlahraga, position);
            }
        });


    }

    @Override
    public int getItemCount() {

        if (mCaborList.isEmpty() || mCaborList == null) {
            return DummyContent.ITEMS.size();
        } else {
            return mCaborList.size();
        }
    }

    public void updateData(List<CabangOlahraga> cabangOlahragaList) {
        mSelectedCaborFavoritList.clear();
        mSelectedCaborFavoritList.addAll(mCaborListOld);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final FrameLayout flContainerCaborFavorit;
        public final ImageView mIvThumbnailCaborFavorit;
        public final TextView mTvNamaCaborFavorit;
        public final ImageView mIvIndicatorSelected;
        public DummyContent.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            flContainerCaborFavorit = (FrameLayout) view.findViewById(R.id.fl_container_cabor_favorit_welcome_2);
            mIvThumbnailCaborFavorit = (ImageView) view.findViewById(R.id.iv_thumbnail_cabor_favorit_welcome_2);
            mTvNamaCaborFavorit = (TextView) view.findViewById(R.id.tv_nama_cabor_favorit_welcome_2);
            mIvIndicatorSelected = (ImageView) view.findViewById(R.id.iv_indicator_selected_welcome_2);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + "" + "'";
        }
    }
}
