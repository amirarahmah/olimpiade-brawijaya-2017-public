package com.raioncommunity.android.ob2017.adapter;

import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.raioncommunity.android.ob2017.DummyScore;
import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.json.Jadwal;

import java.util.List;

import static com.raioncommunity.android.ob2017.debug.Tag.FAK_RESULT_TAG;

/**
 * Created by amira on 7/14/2017.
 */

public class CardScoreDoneAdapter extends RecyclerView.Adapter<CardScoreDoneAdapter.MyViewHolder> {

    List<DummyScore> scoreList;
    public ObservableList<Jadwal> mJadwalList;


    public CardScoreDoneAdapter(ObservableList<Jadwal> mJadwalList) {
        this.mJadwalList = mJadwalList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_card_score_done, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {

        Jadwal jadwal = mJadwalList.get(i);

        holder.caborIcon.setImageResource(jadwal.getCaborIcon());
        holder.caborName.setText(jadwal.getCabang());
        holder.caborDet.setText(jadwal.getCabordet());
        holder.tv_tempat_card_score_done.setText(jadwal.getTempat());

        Log.d(FAK_RESULT_TAG, "onBindViewHolder: jadwal.getLomba(): " + jadwal.getLomba());
        Log.d(FAK_RESULT_TAG, "onBindViewHolder: jadwal.getIsBagan(): " + jadwal.getIsBagan());

        if (jadwal.getIsBagan()) {
            holder.cardScoreLangsung.setVisibility(View.GONE);
            holder.team1Res.setImageResource(jadwal.getTeam1Resource());
            holder.team1Name.setText(jadwal.getTeam1Nama());
            holder.team1Score.setText(jadwal.getTeam1Score());
            holder.team2Res.setImageResource(jadwal.getTeam2Resource());
            holder.team2Name.setText(jadwal.getTeam2Nama());
            holder.team2Score.setText(jadwal.getTeam2Score());
        } else {
            holder.cardScoreBagan.setVisibility(View.GONE);
            holder.juara1Nama.setText(jadwal.getJuara1Name());
            holder.juara1Res.setImageResource(jadwal.getJuara1Resource());
            holder.juara2Nama.setText(jadwal.getJuara2Name());
            holder.juara2Res.setImageResource(jadwal.getJuara2Resource());
            holder.juara3Nama.setText(jadwal.getJuara3Name());
            holder.juara3Res.setImageResource(jadwal.getJuara3Resource());
        }

    }

    @Override
    public int getItemCount() {
        return mJadwalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView caborIcon;
        public TextView caborName;
        public TextView caborDet;
        public RelativeLayout cardScoreBagan;
        public ImageView team1Res;
        public TextView team1Name;
        public TextView team1Score;
        public ImageView team2Res;
        public TextView team2Name;
        public TextView team2Score;
        public RelativeLayout cardScoreLangsung;
        public ImageView juara1Res;
        public TextView juara1Nama;
        public ImageView juara2Res;
        public TextView juara2Nama;
        public ImageView juara3Res;
        public TextView juara3Nama;

        public TextView jenis;
        public TextView cardScore_team1Score;
        public TextView cardScore_team2Score;
        public TextView card_versus;
        public TextView tv_tempat_card_score_done;


        public MyViewHolder(View view) {
            super(view);

            caborIcon = (ImageView) view.findViewById(R.id.card_caborIcon);
            caborName = (TextView) view.findViewById(R.id.card_caborName);
            caborDet = (TextView) view.findViewById(R.id.jenis);
            cardScoreBagan = (RelativeLayout) view.findViewById(R.id.cardScore_bagan);
            team1Res = (ImageView) view.findViewById(R.id.cardScore_team1Res);
            team1Name = (TextView) view.findViewById(R.id.cardScore_team1Nama);
            team1Score = (TextView) view.findViewById(R.id.cardScore_team1Score);
            team2Res = (ImageView) view.findViewById(R.id.cardScore_team2Res);
            team2Name = (TextView) view.findViewById(R.id.cardScore_team2Nama);
            team2Score = (TextView) view.findViewById(R.id.cardScore_team2Score);
            cardScoreLangsung = (RelativeLayout) view.findViewById(R.id.cardScore_langsung);
            juara1Res = (ImageView) view.findViewById(R.id.cardScore_juara1Res);
            juara1Nama = (TextView) view.findViewById(R.id.cardScore_juara1Text);
            juara2Res = (ImageView) view.findViewById(R.id.cardScore_juara2Res);
            juara2Nama = (TextView) view.findViewById(R.id.cardScore_juara2Text);
            juara3Res = (ImageView) view.findViewById(R.id.cardScore_juara3Res);
            juara3Nama = (TextView) view.findViewById(R.id.cardScore_juara3Text);

            jenis = (TextView) view.findViewById(R.id.jenis);
            cardScore_team1Score = (TextView) view.findViewById(R.id.cardScore_team1Score);
            cardScore_team2Score = (TextView) view.findViewById(R.id.cardScore_team2Score);
            card_versus = (TextView) view.findViewById(R.id.card_versus);
            tv_tempat_card_score_done = (TextView) view.findViewById(R.id.tv_tempat_card_score_done);


        }

    }

}
