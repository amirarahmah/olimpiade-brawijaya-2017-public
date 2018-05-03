package com.raioncommunity.android.ob2017.ui.nav_menu.settings.add_favorite_dialog;

/**
 * Created by arifinfirdaus on 7/9/17.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.view.CabangOlahraga;
import com.raioncommunity.android.ob2017.ui.nav_menu.settings.FavoritSayaRecyclerViewAdapter;
import com.raioncommunity.android.ob2017.ui.nav_menu.settings.SettingsView;
import com.raioncommunity.android.ob2017.util.Constant;
import com.raioncommunity.android.ob2017.util.DataList;
import com.raioncommunity.android.ob2017.util.GSONConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.alexrs.prefs.lib.Prefs;

import static com.raioncommunity.android.ob2017.Const.LOG.FAKULTAS_FAVORIT_SP_TAG;
import static com.raioncommunity.android.ob2017.debug.Tag.ADD_FAVORITE_DIALOG_TAG;
import static com.raioncommunity.android.ob2017.debug.Tag.ARRAY_LIST_TAG;
import static com.raioncommunity.android.ob2017.debug.Tag.GSON_CONVERTER_TAG;

/**
 * Created by bradhawk on 9/26/2016.
 */

public class AddFavoriteDialogFragment extends DialogFragment implements AddFavoriteDialogCallback, SettingsView {

    // MARK : Properties


    private AddFavoriteDialogCallback mCallback;

    private List<CabangOlahraga> mCabangOlahragaList;
    public List<CabangOlahraga> mCaborAvailableList;
    private List<CabangOlahraga> mSelectedCaborFavoritList;

    private RecyclerView mRecyclerViewAddFavorite;
    private Object mSelectedItem;
    static FavoritSayaRecyclerViewAdapter mFavoritSayaRecyclerViewAdapter;


    // MARK : Fragment Lifecycle

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.view_add_favorite, null);

        mCabangOlahragaList = new ArrayList<>();

        mCaborAvailableList = new ArrayList<>(Arrays.asList(DataList.cabangOlahragaList));
        setupPrefOnFirst();
        setupCabangOlahragaDataList();
        builder.setView(view);
        setupRecyclerView(view);

        return builder.create();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();

        // save pref favorit saya
        if (mSelectedCaborFavoritList == null) {
            Log.d(FAKULTAS_FAVORIT_SP_TAG, "onPause: onPause: mSelectedCaborFavoritList.size : " + mSelectedCaborFavoritList.size());
        } else {
            Log.d(FAKULTAS_FAVORIT_SP_TAG, "onPause: mSelectedCaborFavoritList.size : " + mSelectedCaborFavoritList.size());
        }
    }

    // MARK : AddFavoriteDialogCallback

    @Override
    public void onItemClick(CabangOlahraga item, Dialog currentDialog, FavoritSayaRecyclerViewAdapter favoritSayaRecyclerViewAdapter) {
        if (mCallback != null) {
            mCallback.onItemClick(item, getDialog(), favoritSayaRecyclerViewAdapter); // ke settings activity
        } else {
            Log.d(FAKULTAS_FAVORIT_SP_TAG, "onItemClick: mCallback is null");
        }
        getDialog().dismiss();
    }

    // unused
    private void writeToSharedPref(List<CabangOlahraga> selectedCaborFavoritList) {
        // save ke sharedPref
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Set<String> cabangOlahragaSetString = GSONConverter.fromListToSetString(selectedCaborFavoritList);
        editor.putStringSet(Constant.CABOR_FAVORIT_KEY, cabangOlahragaSetString);
        editor.commit();
    }

    // unsued
    @Override
    public void updateUI(CabangOlahraga item) {
        // mSelectedCaborFavoritList.add(item);
        // mCallback.onItemClick(item, getDialog());
    }


    private void setupCabangOlahragaDataList() {
        Bundle args = getArguments();
        if (args != null) {
            List<String> arrayListBundle = args.getStringArrayList(ARRAY_LIST_TAG);
            if (arrayListBundle != null) {
                for (String value : arrayListBundle) {
                    CabangOlahraga fromStringToObject = GSONConverter.fromStringToObject(value,
                            CabangOlahraga.class);
                    mCabangOlahragaList.add(fromStringToObject);
                }
            }
        }
    }

    private void setupPrefOnFirst() {
        // setup pref on start
        Set<String> stringSet = Prefs.with(getContext()).getStringSet(
                Constant.CABOR_FAVORIT_KEY,
                null
        );

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        Set<String> defaultValueStringSet = new HashSet<>();
        Set<String> favoritStringSet = sharedPref.getStringSet(
                Constant.CABOR_FAVORIT_KEY,
                defaultValueStringSet
        );

        Log.d("STRING_SET_TAG", "setupPrefOnFirst: stringSet: " + stringSet);

        mSelectedCaborFavoritList = GSONConverter.fromSetStringToList(
                favoritStringSet,
                CabangOlahraga.class
        );
        mCaborAvailableList.removeAll(mSelectedCaborFavoritList);
    }


    private void setupRecyclerView(View view) {
        AddFavoriteDialogFragmentCaborRecyclerViewAdapter adapter = new
                AddFavoriteDialogFragmentCaborRecyclerViewAdapter(
                getContext(),
                mCabangOlahragaList,
                this,
                getDialog(),
                mFavoritSayaRecyclerViewAdapter
        );

        mRecyclerViewAddFavorite = (RecyclerView) view.findViewById(R.id.addFavorite_recyclerView);
        mRecyclerViewAddFavorite.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewAddFavorite.setAdapter(adapter);
    }

    public static void show(FragmentActivity activity, ArrayList<String> lists,
                            AddFavoriteDialogCallback callback,
                            FavoritSayaRecyclerViewAdapter favoritSayaRecyclerViewAdapter) {
        AddFavoriteDialogFragment dialog = new AddFavoriteDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ARRAY_LIST_TAG, lists);
        dialog.setArguments(bundle);
        dialog.mCallback = callback;
        dialog.show(activity.getSupportFragmentManager(), ADD_FAVORITE_DIALOG_TAG);
        for (String l : lists) {
            Log.d(GSON_CONVERTER_TAG, "AddFavoriteDialogFragment: l : " + l);
        }
        mFavoritSayaRecyclerViewAdapter = favoritSayaRecyclerViewAdapter;
    }


    // TODO: 7/15/17 ketika item di klik, dismiss, update data dkk dst

}
