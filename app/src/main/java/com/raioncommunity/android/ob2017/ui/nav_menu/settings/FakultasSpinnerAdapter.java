package com.raioncommunity.android.ob2017.ui.nav_menu.settings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.view.Fakultas;

/**
 * Created by arifinfirdaus on 7/9/17.
 */

public class FakultasSpinnerAdapter extends ArrayAdapter<Fakultas> {


    private Context mContext;
    private Fakultas[] mFakultasList;
    private int mResource;


    public FakultasSpinnerAdapter(Context context, int resource, Fakultas[] fakultasList) {
        super(context, resource, fakultasList);
        this.mContext = context;
        this.mFakultasList = fakultasList;
        this.mResource = resource;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View dropDownView = convertView;
        if (dropDownView == null) {
            dropDownView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        }

        Fakultas fakultas = mFakultasList[position];
        if (fakultas != null) {
            TextView tvSpinnerItem = (TextView) dropDownView.findViewById(R.id.tv_spinner_item);
            tvSpinnerItem.setText(fakultas.getFakultasName());
        } 
        
        if (fakultas == null) {
            Toast.makeText(mContext, "fakultas == null", Toast.LENGTH_SHORT).show();
        }
        return dropDownView;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;

        if (item == null) {
            item = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        }

        Fakultas fakultas = mFakultasList[position];
        if (fakultas != null) {
            TextView tvSpinnerItem = (TextView) item.findViewById(R.id.tv_spinner_item);
            tvSpinnerItem.setText(fakultas.getFakultasName());
        }
        return item;
    }
}
