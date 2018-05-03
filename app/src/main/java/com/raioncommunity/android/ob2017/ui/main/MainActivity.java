package com.raioncommunity.android.ob2017.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.view.DataModel;
import com.raioncommunity.android.ob2017.ui.nav_menu.beranda.BerandaFragment;
import com.raioncommunity.android.ob2017.ui.nav_menu.berita.BeritaFragment;
import com.raioncommunity.android.ob2017.ui.nav_menu.cabang_olahraga.CabangOlahragaContainerFragment;
import com.raioncommunity.android.ob2017.ui.nav_menu.cabang_olahraga.CaborFragment;
import com.raioncommunity.android.ob2017.ui.nav_menu.fakultas.FakultasLainContainerFragment;
import com.raioncommunity.android.ob2017.ui.nav_menu.fakultas.FakultasLainFragment;
import com.raioncommunity.android.ob2017.ui.nav_menu.fakultas.FakultasSayaFragment;
import com.raioncommunity.android.ob2017.ui.nav_menu.jadwal.ActualJadwalFragment;
import com.raioncommunity.android.ob2017.ui.nav_menu.klasmen.KlasmenFragment;
import com.raioncommunity.android.ob2017.ui.nav_menu.settings.SettingsActivity;
import com.raioncommunity.android.ob2017.ui.nav_menu.petunjuk_penonton.PetunjukPenontonFragment;
import com.raioncommunity.android.ob2017.util.UnitConverter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DrawerItemCustomAdapter.ClickListener,
        BeritaFragment.OnFragmentInteractionListener, PetunjukPenontonFragment.OnFragmentInteractionListener {

    private ActionBar actionBar;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawerList;
    Toolbar toolbar;
    ActionBarDrawerToggle mDrawerToggle;
    List<DataModel> drawerItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RecyclerView) findViewById(R.id.left_drawer);

        setupToolbar();

        if (getSupportActionBar() != null) actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.navDrawerBeranda);
        updateFragment(BerandaFragment.newInstance());

        drawerItem = new ArrayList<>();

        drawerItem.add(new DataModel(R.drawable.ic_home, R.string.navDrawerBeranda));
        drawerItem.add(new DataModel(R.drawable.ic_action_jadwal, R.string.navDrawerJadwal));
        drawerItem.add(new DataModel(R.drawable.ic_action_tmedals_copy, R.string.navDrawerKlasemen));
        drawerItem.add(new DataModel(R.drawable.ic_action_fakultas_saya, R.string.navDrawerFakSaya));
        drawerItem.add(new DataModel(R.drawable.ic_action_fakultas_lain, R.string.navDrawerFakLain));
        drawerItem.add(new DataModel(R.drawable.ic_action_cabang_olim, R.string.navDrawerCabor));
        drawerItem.add(new DataModel(R.drawable.ic_action_news, R.string.navDrawerNews));
        drawerItem.add(new DataModel(R.drawable.ic_location_pointer, R.string.navDrawerGuide));
        drawerItem.add(new DataModel(R.drawable.ic_action_setting, R.string.navDrawerSetting));
//        drawerItem.add(new DataModel(R.drawable.ic_action_spectator_guide, R.string.navDrawerAboutUs));

        ArrayList<Drawable> drawableArrayList = new ArrayList<>();
        for (int i = 0; i < drawerItem.size(); i++) {
            Drawable drawable = ContextCompat.getDrawable(this, this.drawerItem.get(i).icon);
            drawable.setColorFilter(Color.parseColor("#673AB7"), PorterDuff.Mode.SRC_IN);
            drawableArrayList.add(drawable);
        }

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, drawerItem);
        adapter.setClickListener(this);
        mDrawerList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mDrawerList.setAdapter(adapter);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setupDrawerToggle();

    }

    @Override
    public void itemClicked(View view, int position) {

        switch (position) {
            case 0:
                actionBar.setTitle(R.string.navDrawerBeranda);
                updateFragment(BerandaFragment.newInstance());
                break;
            case 1:
                actionBar.setTitle(R.string.navDrawerJadwal);
                updateFragment(ActualJadwalFragment.newInstance());
                break;
            case 2:
                actionBar.setTitle(R.string.navDrawerKlasemen);
                updateFragment(KlasmenFragment.newInstance());
                break;
            case 3:
                actionBar.setTitle(R.string.navDrawerFakSaya);
                updateFragment(FakultasSayaFragment.newInstance());
                break;
            case 4:
                actionBar.setTitle(R.string.navDrawerFakLain);
                updateFragment(FakultasLainFragment.newInstance());
                break;
            case 5:
                actionBar.setTitle(R.string.navDrawerCabor);
                updateFragment(CaborFragment.newInstance());
                break;
            case 6:
                actionBar.setTitle(R.string.navDrawerNews);
                updateFragment(BeritaFragment.newInstance("", ""));
                break;
            case 7:
                actionBar.setTitle("Petunjuk Penonton");
                updateFragment(PetunjukPenontonFragment.newInstance("", ""));
                break;
            case 8:
                navigateToSettingsActivity();
                break;
        }
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    // MARK : - Navigation
    private void navigateToSettingsActivity() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void updateFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.content_frame, fragment, "FRAGMENT");
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("FRAGMENT");
        if (fragment != null && fragment instanceof CabangOlahragaContainerFragment) {
            updateFragment(CaborFragment.newInstance());
        } else if (fragment != null && fragment instanceof FakultasLainContainerFragment) {
            updateFragment(FakultasLainFragment.newInstance());
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
