package com.raioncommunity.android.ob2017.ui.nav_menu.cabang_olahraga;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.view.CabangOlahraga;
import com.raioncommunity.android.ob2017.util.GSONConverter;

import java.util.List;

import static com.raioncommunity.android.ob2017.debug.Tag.CABOR_FRAGMENT_TAG;

public class CaborAdapter extends RecyclerView.Adapter<CaborAdapter.MyViewHolder> {

    private List<CabangOlahraga> caborList;
    private ClickListener clicklistener;


    public CaborAdapter(List<CabangOlahraga> caborList) {
        this.caborList = caborList;
    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }

    public void setClickListener(ClickListener clicklistener) {
        this.clicklistener = clicklistener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cabang_olahraga, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        CabangOlahraga cabor = caborList.get(i);

        holder.indicatorFavorit.setVisibility(
                cabor.isSelected() ? View.VISIBLE : View.INVISIBLE
        );
        holder.caborIcon.setImageResource(cabor.getCaborIconRes());
        holder.caborName.setText(cabor.getCaborNameRes());

    }

    @Override
    public int getItemCount() {
        return caborList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView caborIcon;
        TextView caborName;
        ImageView indicatorFavorit;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            caborIcon = (ImageView) itemView.findViewById(R.id.cabor_icon);
            caborName = (TextView) itemView.findViewById(R.id.cabor_name);
            indicatorFavorit = (ImageView) itemView.findViewById(R.id.cabor_favorite);
        }

        @Override
        public void onClick(View v) {
            if (clicklistener != null) {
                clicklistener.itemClicked(v, getAdapterPosition());
            }
        }
    }

}

