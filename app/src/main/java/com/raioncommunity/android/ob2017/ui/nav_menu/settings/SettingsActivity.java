package com.raioncommunity.android.ob2017.ui.nav_menu.settings;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.dummy.DummyContent;
import com.raioncommunity.android.ob2017.model.view.CabangOlahraga;
import com.raioncommunity.android.ob2017.model.view.Fakultas;
import com.raioncommunity.android.ob2017.ui.nav_menu.settings.add_favorite_dialog.AddFavoriteDialogCallback;
import com.raioncommunity.android.ob2017.ui.nav_menu.settings.add_favorite_dialog.AddFavoriteDialogFragment;
import com.raioncommunity.android.ob2017.util.Constant;
import com.raioncommunity.android.ob2017.util.DataList;
import com.raioncommunity.android.ob2017.util.GSONConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.raioncommunity.android.ob2017.debug.Tag.GSON_CONVERTER_TAG;
import static com.raioncommunity.android.ob2017.util.Constant.SHARED_PREF_NAME;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener,
        AddFavoriteDialogCallback, FavoritSayaRecyclerViewCallback {


    // MARK : Properties

    private RecyclerView mRvFavoriteSaya;
    private RelativeLayout mRelativeLayoutAddFavorite;
    private Spinner mFakultasSpinner;

    private FavoritSayaRecyclerViewAdapter mFavoritSayaAdapter;
    private FakultasSpinnerAdapter mFakultasSpinnerAdapter;

    public List<CabangOlahraga> mCaborAvailableList;
    private List<CabangOlahraga> mSelectedCaborFavoritList;

    public Fakultas[] mFakultasList;
    Fakultas selectedFakultas;


    // MARK : Activity lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Set<String> defaultValueStringSet = new HashSet<>();
        Set<String> favoritStringSet = sharedPref.getStringSet(Constant.CABOR_FAVORIT_KEY, defaultValueStringSet);

        mCaborAvailableList = new ArrayList<>(Arrays.asList(DataList.cabangOlahragaList));

        mFakultasList = DataList.fakultasList;

        // update data list
        mSelectedCaborFavoritList = GSONConverter.fromSetStringToList(favoritStringSet, CabangOlahraga.class);
        Log.d(GSON_CONVERTER_TAG, "onCreate: mSelectedCaborFavoritList: " + mSelectedCaborFavoritList.toString());

        mCaborAvailableList.removeAll(mSelectedCaborFavoritList);

        mFavoritSayaAdapter = new FavoritSayaRecyclerViewAdapter(DummyContent.ITEMS, mSelectedCaborFavoritList, this);

        loadCurrentUserCaborList();
        setupViewSesiFavoriteSaya();

        setupViewsSesiFakultasSaya();
        loadSharedPrefFakultasSaya();
    }

    // MARK: - FavoritSayaRecyclerViewCallback
    @Override
    public void onAttemptRemoveItem(final CabangOlahraga cabangOlahraga, final int position) {
        showAlertDialog(cabangOlahraga, position);
    }

    private void showAlertDialog(final CabangOlahraga cabangOlahraga, final int position) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirm)
                .setPositiveButton(R.string.positiveButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        removeItem(cabangOlahraga, position); // handle delete item
                    }
                })
                .setNegativeButton(R.string.negativeButton, null);
        builder.create(); // Create the AlertDialog object and return it
        AlertDialog alertDialog = builder.show();
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    private void removeItem(CabangOlahraga co, int pos) {
        boolean isSuccessRemove = mSelectedCaborFavoritList.remove(co);

        if (isSuccessRemove) {
            mFavoritSayaAdapter.updateData(mSelectedCaborFavoritList);
            mFavoritSayaAdapter.notifyDataSetChanged();
        }
    }


    private void loadSharedPrefFakultasSaya() {

        mFakultasSpinner.post(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                String strSharedPrefFakultasFavorit = sharedPref.getString(
                        Constant.FAKULTAS_FAVORIT_KEY,
                        null
                );

                selectedFakultas = GSONConverter.fromStringToObject(strSharedPrefFakultasFavorit, Fakultas.class);
                int position = mFakultasSpinnerAdapter.getPosition(selectedFakultas);

                mFakultasSpinner.setSelection(position);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveSharedPrefCaborFavoritSaya();
        saveSharedPrefSelectedFakultasSpinnerItem();
    }

    private void saveSharedPrefCaborFavoritSaya() {
        writeToSharedPref(mSelectedCaborFavoritList);
    }

    private void saveSharedPrefSelectedFakultasSpinnerItem() {

        Object selectedItemFromSpinner = mFakultasSpinner.getSelectedItem();

        if (selectedItemFromSpinner instanceof Fakultas) {

            selectedFakultas = (Fakultas) selectedItemFromSpinner;

            // save ke sharedPref
            SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            String strSelectedFakultas = GSONConverter.fromObjectToString(selectedFakultas);

            editor.putString(
                    Constant.FAKULTAS_FAVORIT_KEY,
                    strSelectedFakultas
            );
            editor.commit();
        }
    }

    private void loadCurrentUserCaborList() {

//        if (mSelectedCaborFavoritList == null) {
//            mSelectedCaborFavoritList = new ArrayList<>();
//
//        } else {
//            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//            Set<String> defaultValueStringSet = new HashSet<>();
//            Set<String> favoritStringSet = sharedPref.getStringSet(Constant.CABOR_FAVORIT_KEY, defaultValueStringSet);
//
//            // update data list
//            mSelectedCaborFavoritList = GSONConverter.fromSetStringToList(favoritStringSet, CabangOlahraga.class);
//            mFavoritSayaAdapter.notifyDataSetChanged();
//        }

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Set<String> defaultValueStringSet = new HashSet<>();
        Set<String> favoritStringSet = sharedPref.getStringSet(Constant.CABOR_FAVORIT_KEY, defaultValueStringSet);

        // update data list
        mSelectedCaborFavoritList = GSONConverter.fromSetStringToList(favoritStringSet, CabangOlahraga.class);
        mFavoritSayaAdapter.updateData(mSelectedCaborFavoritList);
        mFavoritSayaAdapter.notifyDataSetChanged();

        Log.d(GSON_CONVERTER_TAG, "loadCurrentUserCaborList: mSelectedCaborFavoritList: " + mSelectedCaborFavoritList.toString());
    }

    private void setupViewSesiFavoriteSaya() {
        // setup recycler view
        mRvFavoriteSaya = (RecyclerView) findViewById(R.id.rv_favorite_pengaturan);
        setupRecyclerView(mRvFavoriteSaya);

        mCaborAvailableList = new ArrayList<>(Arrays.asList(DataList.cabangOlahragaList));


        mRelativeLayoutAddFavorite = (RelativeLayout) findViewById(R.id.relative_layout_add_favorite);
        mRelativeLayoutAddFavorite.setOnClickListener(this);
    }

    private void setupViewsSesiFakultasSaya() {
        mFakultasSpinner = (Spinner) findViewById(R.id.setting_fakultasSpinner);

        mFakultasSpinnerAdapter = new FakultasSpinnerAdapter(
                this,
                R.layout.item_spinner_dropdown_fakultas,
                DataList.fakultasList
        );
        mFakultasSpinner.setAdapter(mFakultasSpinnerAdapter);
    }


    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(mFavoritSayaAdapter);
    }

    // MARK: - View.OnClickListener
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.relative_layout_add_favorite) {
            handleAddFavorite();
        }
        if (id == R.id.setting_fakultasSpinner) {
        }
    }

    private void handleAddFavorite() {
        if (!mCaborAvailableList.isEmpty()) {

            updateFavoritSayaAdapter();

            ArrayList<String> param = new ArrayList<>();
            for (CabangOlahraga cabor : mCaborAvailableList) {
                String fromObjectToString = GSONConverter.fromObjectToString(cabor);
                param.add(fromObjectToString);
            }
            AddFavoriteDialogFragment.show(this, param, this, mFavoritSayaAdapter);
        } else {
            Toast.makeText(this, "Data Cabang Olahraga Kosong.", Toast.LENGTH_SHORT).show();
        }
    }

    // MARK : - AddFavoriteDialogCallback
    // TODO: 7/31/17 yang ini yang di pake buat save ke shared pref dan update recyclerview
    @Override
    public void onItemClick(CabangOlahraga item, Dialog currentDialog, FavoritSayaRecyclerViewAdapter favoritSayaRecyclerViewAdapter) {
        // refresh ui
        mSelectedCaborFavoritList.add(item);
        mFavoritSayaAdapter.updateData(mSelectedCaborFavoritList);
        mFavoritSayaAdapter.notifyDataSetChanged();

        updateFavoritSayaAdapter();
    }

    private void updateFavoritSayaAdapter() {
        // update data
        mCaborAvailableList = new ArrayList<>(Arrays.asList(DataList.cabangOlahragaList));
        mCaborAvailableList.removeAll(mSelectedCaborFavoritList);
        mFavoritSayaAdapter.notifyDataSetChanged();
    }


    // save ke shared pref : done
    private void writeToSharedPref(List<CabangOlahraga> selectedCaborFavoritList) {

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Set<String> cabangOlahragaSetString = GSONConverter.fromListToSetString(selectedCaborFavoritList);

        editor.putStringSet(Constant.CABOR_FAVORIT_KEY, cabangOlahragaSetString);
        editor.commit();
    }

    @Override
    public void updateUI(CabangOlahraga item) {
        // refresh ui
    }


    // MARK : - AppCompatActivity

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            Toast.makeText(this, "Data tersimpan.", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Data tersimpan.", Toast.LENGTH_SHORT).show();
    }
}
