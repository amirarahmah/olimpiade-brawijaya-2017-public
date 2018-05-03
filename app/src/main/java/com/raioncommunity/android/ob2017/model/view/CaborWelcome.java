package com.raioncommunity.android.ob2017.model.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.raioncommunity.android.ob2017.R;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;


/**
 * Modified by arifinfirdaus
 */


public class CaborWelcome extends AbstractItem<CaborWelcome, CaborWelcome.ViewHolder> {

    /**
     * @param caborImageRes
     * @param caborName
     * @param isSelected : untuk multiple select di Welcome2Fragment.java
     */


    private int caborImageRes;
    private int caborName;
    private boolean isSelected;


    public CaborWelcome(int caborImageRes, int caborName) {
        this.caborImageRes = caborImageRes;
        this.caborName = caborName;
    }

    public CaborWelcome(int caborImageRes, int caborName, boolean isSelected) {
        this.caborImageRes = caborImageRes;
        this.caborName = caborName;
        this.isSelected = isSelected;
    }

    public int getCaborImageRes() {
        return caborImageRes;
    }

    public int getCaborName() {
        return caborName;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.cabor_welcome;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_cabor_welcome;
    }

    @Override
    public void bindView(ViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);
        holder.caborImageRes.setImageResource(getCaborImageRes());
        holder.caborName.setText(getCaborName());

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView caborImageRes;
        TextView caborName;

        public ViewHolder(View itemView) {
            super(itemView);
            caborImageRes = (ImageView) itemView.findViewById(R.id.welcome_cabor_image);
            caborName = (TextView) itemView.findViewById(R.id.welcome_cabor_name);

        }
    }

}
