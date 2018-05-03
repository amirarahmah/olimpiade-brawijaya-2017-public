package com.raioncommunity.android.ob2017.ui.nav_menu.berita;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.raioncommunity.android.ob2017.Const;
import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.dummy.DummyContent;
import com.raioncommunity.android.ob2017.model.json.News;
import com.raioncommunity.android.ob2017.ui.berita_detail.BeritaDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.raioncommunity.android.ob2017.debug.Tag.BERITA_DETAIL_TAG;
import static com.raioncommunity.android.ob2017.util.DataList.context;

/**
 * Created by arifinfirdaus on 7/6/17.
 */

public class BeritaRecyclerViewAdapter extends RecyclerView.Adapter<BeritaRecyclerViewAdapter.ViewHolder> {

    private List<DummyContent.DummyItem> mValues;
    private ObservableList<News> mNewsList;
    private Context mContext;


    public BeritaRecyclerViewAdapter(ObservableList<News> newsList, Context context) {
        this.mNewsList = newsList;
        this.mContext = context;
    }

    @Override
    public BeritaRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BeritaRecyclerViewAdapter.ViewHolder holder, final int position) {

//        holder.mTvHeadlineBerita.setText("Berita ke " + mValues.get(position).id);
//        holder.mTvIsiBerita.setText("Detail berita ke " + mValues.get(position).content);


        final News news = mNewsList.get(position);
        holder.mTvHeadlineBerita.setText(news.getJudul());
        holder.mTvIsiBerita.setText(news.getIsi());
        Picasso.with(mContext)
                .load(news.getGambar())
                .resize(800, 800)
                .into(holder.iv_item_news);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, BeritaDetailActivity.class);
                Log.d(BERITA_DETAIL_TAG, "onClick: " + news.getId());
                intent.putExtra(Const.KEY.BERITA.BERITA_ID, news.getId());
                context.startActivity(intent);
            }
        });

        holder.mBtnBacaSelengkapnyaBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, BeritaDetailActivity.class);
                intent.putExtra(Const.KEY.BERITA.BERITA_ID, news.getId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        Log.d("news", "getItemCount: " + mNewsList.size());
        return mNewsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTvHeadlineBerita;
        public final TextView mTvIsiBerita;
        public final Button mBtnBacaSelengkapnyaBerita;
        public final ImageView iv_item_news;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTvHeadlineBerita = (TextView) view.findViewById(R.id.tv_headline_berita);
            mTvIsiBerita = (TextView) view.findViewById(R.id.tv_isi_berita);
            mBtnBacaSelengkapnyaBerita = (Button) view.findViewById(R.id.btn_baca_selengkapnya_berita);
            iv_item_news = (ImageView) view.findViewById(R.id.iv_item_news);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTvIsiBerita.getText() + "'";
        }
    }
}
