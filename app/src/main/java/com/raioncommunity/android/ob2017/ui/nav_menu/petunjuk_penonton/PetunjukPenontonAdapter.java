package com.raioncommunity.android.ob2017.ui.nav_menu.petunjuk_penonton;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.dummy.DummyContent;
import com.raioncommunity.android.ob2017.model.view.PetunjukPenonton;
import com.raioncommunity.android.ob2017.util.ItemOffsetDecoration;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.raioncommunity.android.ob2017.debug.Tag.ItemCaborAdapterTag;

/**
 * Created by arifinfirdaus on 7/6/17.
 */

public class PetunjukPenontonAdapter extends RecyclerView.Adapter<PetunjukPenontonAdapter.ViewHolder> {


    private List<PetunjukPenonton> mPetunjukPenontonList;
    private Context mContext;
    Resources mRes;


    public PetunjukPenontonAdapter(List<PetunjukPenonton> mPetunjukPenontonList, Context context) {
        this.mPetunjukPenontonList = mPetunjukPenontonList;
        this.mContext = context;
    }

    @Override
    public PetunjukPenontonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_petunjuk_penonton_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PetunjukPenontonAdapter.ViewHolder holder, final int position) {

        mRes = mContext.getResources();
        final PetunjukPenonton petunjukPenonton = mPetunjukPenontonList.get(position);

        holder.mTvVenueName.setText(mRes.getString(petunjukPenonton.getVenueNameRes()));
        Picasso.with(mContext)
                .load(petunjukPenonton.getGambar())
                .resize(800, 800)
                .centerCrop()
                .into(holder.iv_tempat_petunjuk_penonton);

        holder.mImgBtnDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToGoogleMapApps(petunjukPenonton, v, position, mRes);
            }
        });

        setupItemCaborAdapter(holder, petunjukPenonton, position);

    }

    private void setupItemCaborAdapter(PetunjukPenontonAdapter.ViewHolder holder, PetunjukPenonton pp, int position) {
        Log.d(ItemCaborAdapterTag, "setupItemCaborAdapter: ");
        PetunjukPenontonItemCaborAdapter itemCaborAdapter = new PetunjukPenontonItemCaborAdapter(
                pp,
                mContext,
                pp.getListCabor()
        );
        holder.mRvVenueCabor.setAdapter(itemCaborAdapter);
        holder.mRvVenueCabor.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        ItemOffsetDecoration decoration = new ItemOffsetDecoration(mContext, R.dimen.itemOffset);
        holder.mRvVenueCabor.addItemDecoration(decoration);
    }

    private void navigateToGoogleMapApps(PetunjukPenonton petunjukPenonton, View v, int position, Resources res) {
        Context context = v.getContext();
        petunjukPenonton.onClick(v, res.getString(petunjukPenonton.getVenueNameRes()));
    }


    @Override
    public int getItemCount() {
        return mPetunjukPenontonList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTvVenueName;
        public final ImageButton mImgBtnDirection;
        public final RecyclerView mRvVenueCabor;
        public final ImageView iv_tempat_petunjuk_penonton;
        public DummyContent.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTvVenueName = (TextView) view.findViewById(R.id.tv_venue_name_petunjuk_penonton);
            mImgBtnDirection = (ImageButton) view.findViewById(R.id.imgbtn_direction_petunjuk_penonton);
            mRvVenueCabor = (RecyclerView) view.findViewById(R.id.venueCabor_recyclerView);
            iv_tempat_petunjuk_penonton = (ImageView) view.findViewById(R.id.iv_tempat_petunjuk_penonton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTvVenueName.getText() + "'";
        }
    }
}