package com.raioncommunity.android.ob2017.ui.nav_menu.klasmen;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.json.Medali;

import java.util.List;

/**
 * Created by amira on 7/3/2017.
 */

public class MedaliAdapter extends RecyclerView.Adapter<MedaliAdapter.MyViewHolder> {

    private List<Medali> klasmenList;
    private Context mContext;

    public MedaliAdapter(List<Medali> klasmenList, Context context) {
        this.klasmenList = klasmenList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_klasmen, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {

        Medali object = klasmenList.get(i);
        if (i % 2 == 0) {
            holder.itemRow.setBackgroundColor(Color.parseColor("#F0F0F0"));
        } else {
            holder.itemRow.setBackgroundColor(Color.TRANSPARENT);
        }
        holder.fakultasName.setText(object.getFakultas());
        holder.medaliGold.setText(object.getEmas());
        holder.medaliSilver.setText(object.getPerak());
        holder.medaliBronze.setText(object.getPerunggu());
        holder.totalMedali.setText(object.getTotalMedali());

    }

    @Override
    public int getItemCount() {
        return klasmenList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (klasmenList != null) {
            if (position % 2 == 0) {
                return 0;
            } else {
                return 1;
            }
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout itemRow;
        private TextView fakultasName;
        private TextView medaliGold;
        private TextView medaliSilver;
        private TextView medaliBronze;
        private TextView totalMedali;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemRow = (LinearLayout) itemView.findViewById(R.id.item_klasemen);
            fakultasName = (TextView) itemView.findViewById(R.id.itemKlasmen_nama);
            medaliGold = (TextView) itemView.findViewById(R.id.itemKlasmen_gold);
            medaliSilver = (TextView) itemView.findViewById(R.id.itemKlasmen_silver);
            medaliBronze = (TextView) itemView.findViewById(R.id.itemKlasmen_bronze);
            totalMedali = (TextView) itemView.findViewById(R.id.itemKlasmen_total);
        }

    }

}
