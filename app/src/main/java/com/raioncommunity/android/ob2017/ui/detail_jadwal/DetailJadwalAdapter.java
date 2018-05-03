package com.raioncommunity.android.ob2017.ui.detail_jadwal;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.raioncommunity.android.ob2017.DummyScore;
import com.raioncommunity.android.ob2017.R;

import java.util.List;

/**
 * Created by amira on 7/12/2017.
 */

public class DetailJadwalAdapter extends RecyclerView.Adapter<DetailJadwalAdapter.MyViewHolder> {

    private List<DummyScore> jadwalList;
    private Context mContext;

    public DetailJadwalAdapter(List<DummyScore> jadwalList, Context context){
        this.jadwalList = jadwalList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_detail_jadwal, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {

        DummyScore object = jadwalList.get(i);
        if(i % 2 == 0){
            holder.container.setBackgroundColor(Color.parseColor("#f0f0f0"));
        }else{
            holder.container.setBackgroundColor(Color.TRANSPARENT);
        }
        holder.cabor.setText(object.getCabang());
        holder.tempat.setText(object.getTempat());
        holder.jamMain.setText(object.getJamMain());

    }

    @Override
    public int getItemCount() {
        return jadwalList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout container;
        private TextView cabor;
        private TextView tempat;
        private TextView jamMain;

        public MyViewHolder(View itemView) {
            super(itemView);
            container = (LinearLayout) itemView.findViewById(R.id.detailJadwal_item);
            cabor = (TextView) itemView.findViewById(R.id.detailJadwal_cabor);
            tempat = (TextView) itemView.findViewById(R.id.detailJadwal_lokasi);
            jamMain = (TextView) itemView.findViewById(R.id.detailJadwal_jamMain);

        }

    }

}
