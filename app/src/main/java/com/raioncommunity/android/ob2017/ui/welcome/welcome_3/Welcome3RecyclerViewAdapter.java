package com.raioncommunity.android.ob2017.ui.welcome.welcome_3;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
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
import com.raioncommunity.android.ob2017.model.view.Fakultas;
import com.raioncommunity.android.ob2017.model.view.FakultasWelcome;
import com.raioncommunity.android.ob2017.ui.welcome.welcome_2.Welcome2FragmentCallback;
import com.raioncommunity.android.ob2017.util.GSONConverter;

import java.util.ArrayList;
import java.util.List;

import static com.raioncommunity.android.ob2017.debug.Tag.WELCOME_3_TAG;

/**
 * Created by arifinfirdaus on 7/22/17.
 */

public class Welcome3RecyclerViewAdapter extends RecyclerView.Adapter<Welcome3RecyclerViewAdapter.ViewHolder> {


    private final List<DummyContent.DummyItem> mValues;
    private final List<FakultasWelcome> mFakultasList;
    private int lastCheckedPosition = -1;
    private Context mContext;
    private ArrayList<Fakultas> mFakultasArrayList;
    private Welcome3FragmentCallback mCallback;


    public Welcome3RecyclerViewAdapter(List<DummyContent.DummyItem> mValues, List<FakultasWelcome> fakultasList) {
        this.mValues = mValues;
        this.mFakultasList = fakultasList;
    }

    public Welcome3RecyclerViewAdapter(Context context, List<DummyContent.DummyItem> mValues, List<FakultasWelcome> mFakultasList, ArrayList<Fakultas> mFakultasArrayList, Welcome3FragmentCallback callback) {
        this.mContext = context;
        this.mValues = mValues;
        this.mFakultasList = mFakultasList;
        this.mFakultasArrayList = mFakultasArrayList;
        this.mCallback = callback;
        // Log.d(WELCOME_3_TAG, "constructor: " + GSONConverter.fromListToSetString(mFakultasArrayList));

    }

    @Override
    public Welcome3RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fakultas_list_content_welcome_3, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Welcome3RecyclerViewAdapter.ViewHolder holder, final int position) {
        // holder.mItem = mValues.get(position);
        final Fakultas fakultas = mFakultasArrayList.get(position);
        holder.mIvThumbnailFakultas.setImageResource(mFakultasArrayList.get(position).getFakultasIconRes());
        holder.mTvNamaFakultas.setText(mContext.getResources().getString(mFakultasList.get(position).getFakultasName()));

        // Log.d(WELCOME_3_TAG, "onBindViewHolder: " + fakultas.getFakultasName());
        // Log.d(WELCOME_3_TAG, "onBindViewHolder: " + GSONConverter.fromObjectToString(fakultas));

        if (fakultas.getId() == lastCheckedPosition) {
            holder.mContainer.setBackgroundColor(ContextCompat.getColor(mContext, R.color.material_blue_grey_100));
        } else {
            holder.mContainer.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastCheckedPosition = fakultas.getId();
                notifyItemRangeChanged(0, mFakultasArrayList.size());
                mCallback.onItemClick(fakultas, position);

            }
        });

    }

    public Fakultas getSelectedItem() {
        Fakultas fakultas = mFakultasArrayList.get(lastCheckedPosition);
        return fakultas;
    }

    public int selectedPosition() {
        return lastCheckedPosition;
    }


    @Override
    public int getItemCount() {

        if (mFakultasArrayList.isEmpty() || mFakultasArrayList == null) {
            return DummyContent.ITEMS.size();
        } else {
            return mFakultasList.size();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIvThumbnailFakultas;
        public final TextView mTvNamaFakultas;
        public FrameLayout mContainer;
        public DummyContent.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mContainer = (FrameLayout) view.findViewById(R.id.container_fakultas_welcome);
            mIvThumbnailFakultas = (ImageView) view.findViewById(R.id.iv_thumbnail_fakultas_welcome_3);
            mTvNamaFakultas = (TextView) view.findViewById(R.id.tv_nama_fakultas_welcome_3);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + "" + "'";
        }
    }
}
