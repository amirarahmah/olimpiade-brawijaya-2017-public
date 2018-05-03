package com.raioncommunity.android.ob2017.ui.nav_menu.petunjuk_penonton;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.view.CabangOlahraga;
import com.raioncommunity.android.ob2017.model.view.PetunjukPenonton;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.raioncommunity.android.ob2017.debug.Tag.ItemCaborAdapterTag;

/**
 * Created by arifinfirdaus on 9/10/17.
 */

public class PetunjukPenontonItemCaborAdapter extends RecyclerView.Adapter<PetunjukPenontonItemCaborAdapter.ViewHolder> {

    private PetunjukPenonton mPetunjukPenonton;
    private PetunjukPenonton[] mPetunjukPenontonList;
    private int[] mPetunjukPenontonItemCaborIdList;
    private Context mContext;
    private List<CabangOlahraga> mListCabor;

    public PetunjukPenontonItemCaborAdapter(PetunjukPenonton petunjukPenonton, Context context, List<CabangOlahraga> listCabor) {
        this.mPetunjukPenonton = petunjukPenonton;
        this.mContext = context;
        this.mPetunjukPenontonItemCaborIdList = petunjukPenonton.getListCaborId();
        this.mListCabor = listCabor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_petunjuk_penonton_item_cabor_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final int currentCaborIconRes = mListCabor.get(position).getCaborIconRes();
        Picasso.with(mContext)
                .load(currentCaborIconRes)
                .into(holder.mIvItemPetunjukPenontonCabor);

        holder.mIvItemPetunjukPenontonCabor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int curentCaborNameRes = mListCabor.get(position).getCaborNameRes();
                String currentCaborName = mContext.getResources().getString(curentCaborNameRes);
                Toast.makeText(mContext, currentCaborName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(ItemCaborAdapterTag, "getItemCount: " + mListCabor.size());
        return mListCabor.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIvItemPetunjukPenontonCabor;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIvItemPetunjukPenontonCabor = (ImageView) view.findViewById(R.id.iv_item_petunjuk_penonton_cabor);
        }


    }
}
