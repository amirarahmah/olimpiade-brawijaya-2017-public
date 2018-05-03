package com.raioncommunity.android.ob2017.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.raioncommunity.android.ob2017.DummyScore;
import com.raioncommunity.android.ob2017.R;

import java.util.List;

/**
 * Created by amira on 7/7/2017.
 */

public class CardScoreAdapter extends RecyclerView.Adapter<CardScoreAdapter.MyViewHolder> {

    private List<DummyScore> scoreList;

    public CardScoreAdapter(List<DummyScore> scoreList) {
        this.scoreList = scoreList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_card_score, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        DummyScore score = scoreList.get(i);
        holder.jamMain.setText(score.getJamMain());
        holder.caborIcon.setImageResource(score.getCaborIcon());
        holder.caborName.setText(score.getCabang());
        if (score.isLive()) {
            holder.live.setText("LIVE");
        } else {
            holder.live.setVisibility(View.GONE);
        }
        holder.caborDet.setText(score.getCaborDet());
        if (score.isBagan()) {
            holder.cardScoreLangsung.setVisibility(View.GONE);
            holder.team1Res.setImageResource(score.getTeam1Resource());
            holder.team1Name.setText(score.getTeam1Nama());
            holder.team1Score.setText(score.getTeam1Score());
            holder.team2Res.setImageResource(score.getTeam2Resource());
            holder.team2Name.setText(score.getTeam2Nama());
            holder.team2Score.setText(score.getTeam2Score());
        } else {
            holder.cardScoreBagan.setVisibility(View.GONE);
            holder.juara1Nama.setText(score.getJuara1Name());
            holder.juara1Res.setImageResource(score.getJuara1Resource());
            holder.juara2Nama.setText(score.getJuara2Name());
            holder.juara2Res.setImageResource(score.getJuara2Resource());
            holder.juara3Nama.setText(score.getJuara3Name());
            holder.juara3Res.setImageResource(score.getJuara3Resource());
        }

        holder.tempat.setText(score.getTempat());

    }

    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView jamMain;
        ImageView caborIcon;
        public TextView caborName;
        public TextView live;
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
        public TextView tempat;

        public MyViewHolder(View view) {
            super(view);
            jamMain = (TextView) view.findViewById(R.id.waktu_main);
            caborIcon = (ImageView) view.findViewById(R.id.card_caborIcon);
            caborName = (TextView) view.findViewById(R.id.card_caborName);
            live = (TextView) view.findViewById(R.id.cardScore_live);
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
            tempat = (TextView) view.findViewById(R.id.lokasi_lomba);

        }

    }

}
