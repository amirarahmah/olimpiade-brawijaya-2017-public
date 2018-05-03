package com.raioncommunity.android.ob2017.model.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.raioncommunity.android.ob2017.R;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;


public class FakultasWelcome extends AbstractItem<FakultasWelcome, FakultasWelcome.ViewHolder> {

    private int fakultasName;
    private int fakultasIcon;

    public FakultasWelcome(int fakultasName) {
        this.fakultasName = fakultasName;
//        this.fakultasIcon = fakultasIcon;
    }

    public int getFakultasName() {
        return fakultasName;
    }

    public int getFakultasIcon() {
        return fakultasIcon;
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.fakultas_welcome;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_welcome_fakultas;
    }

    @Override
    public void bindView(ViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);
//        holder.fakultasIcon.setImageResource(getFakultasIcon());
        holder.fakultasName.setText(getFakultasName());

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView fakultasName;
        ImageView fakultasIcon;

        public ViewHolder(View itemView) {
            super(itemView);
//            fakultasIcon = (ImageView) itemView.findViewById(R.id.fakultas_icon);
            fakultasName = (TextView) itemView.findViewById(R.id.fakultas_name);

        }
    }
}
