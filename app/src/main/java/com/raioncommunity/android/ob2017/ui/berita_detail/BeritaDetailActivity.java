package com.raioncommunity.android.ob2017.ui.berita_detail;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.raioncommunity.android.ob2017.Const;
import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.api.RestClient;
import com.raioncommunity.android.ob2017.model.json.News;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.raioncommunity.android.ob2017.debug.Tag.BERITA_DETAIL_TAG;

public class BeritaDetailActivity extends AppCompatActivity implements BeritaDetailView {

    private TextView mTvHeadlineBeritaDetail;
    private TextView mTvDetailBeritaIsiBerita;
    private TextView mTvDetailBeritaTanggal;
    private ImageView mIvDetailBerita;
    private BeritaDetailPresenter mBeritaDetailPresenter;

    private ActionBar mActionBar;

    private SimpleDateFormat df;
    private SimpleDateFormat sdf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        // init views
        mTvHeadlineBeritaDetail = (TextView) findViewById(R.id.tv_headline_berita_detail);
        mTvDetailBeritaIsiBerita = (TextView) findViewById(R.id.detailBerita_isiBerita);
        mTvDetailBeritaTanggal = (TextView) findViewById(R.id.detailBerita_tanggal);
        mIvDetailBerita = (ImageView) findViewById(R.id.iv_headline_berita_detail);

        // init presenter
        mBeritaDetailPresenter = new BeritaDetailPresenterImpl(this);
        mBeritaDetailPresenter.attemptGetDataFromPreviousActivity();

        // init dateFormatter
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateUI() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int idBeritaExtras = extras.getInt(Const.KEY.BERITA.BERITA_ID);
            fetchData(idBeritaExtras);
        }
    }

    private void fetchData(int idBerita) {
        RestClient.beritaService.getNews(idBerita).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful()) {

                    // ambil current news
                    News news = response.body();

                    // ambil info yg dibutuhkan
                    String judulBerita = news.getJudul();
                    String tanggalBerita = news.getTanggal();
                    String isiBerita = news.getIsi();
                    String gambarBerita = news.getGambar();

                    // set toolbar title
                    if (mActionBar != null) {
                        mActionBar.setTitle(judulBerita);
                    }

                    // update komponen ui
                    mTvHeadlineBeritaDetail.setText(judulBerita);
                    mTvDetailBeritaTanggal.setText(tanggalBerita);
                    mTvDetailBeritaIsiBerita.setText(isiBerita);
                    Picasso.with(getApplicationContext())
                            .load(gambarBerita)
                            .resize(800, 800)
                            .into(mIvDetailBerita);

                    // convert date format
                    try {
                        Date date = df.parse(tanggalBerita);
                        mTvDetailBeritaTanggal.setText(sdf.format(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(BeritaDetailActivity.this, "Terjadi kesalahan network.", Toast.LENGTH_SHORT).show();
                Log.d(BERITA_DETAIL_TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }


}
