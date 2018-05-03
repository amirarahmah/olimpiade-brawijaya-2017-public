package com.raioncommunity.android.ob2017.ui.nav_menu.jadwal;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.view.DateToView;

import java.util.List;

/**
 * Created by amira on 7/2/2017.
 */

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.MyViewHolder>{

    Context context;
    List<DateToView> dateList;
    ClickListener clicklistener;

    public DateAdapter(Context context, List<DateToView> dateList){
        this.context = context;
        this.dateList = dateList;
    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }

    public void setClickListener(ClickListener clicklistener) {
        this.clicklistener = clicklistener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_date, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        DateToView dateToView = dateList.get(i);
        if(i % 2 == 0){
            holder.container.setBackgroundColor(Color.parseColor("#F0F0F0"));
        }else{
            holder.container.setBackgroundColor(Color.TRANSPARENT);
        }
        holder.date.setText(dateToView.getPrintedDate());
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView date;
        FrameLayout container;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            container = (FrameLayout) itemView.findViewById(R.id.container_date);
            date = (TextView) itemView.findViewById(R.id.tanggal);
        }

        @Override
        public void onClick(View v) {
            if (clicklistener != null) {
                clicklistener.itemClicked(v, getAdapterPosition());
            }
        }

    }

}
