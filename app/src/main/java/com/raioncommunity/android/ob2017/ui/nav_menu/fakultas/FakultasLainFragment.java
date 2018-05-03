package com.raioncommunity.android.ob2017.ui.nav_menu.fakultas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.view.Fakultas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amira on 7/5/2017.
 */

public class FakultasLainFragment extends Fragment implements FakultasAdapter.ClickListener {

    public static FakultasLainFragment newInstance() {

        Bundle args = new Bundle();
        FakultasLainFragment fragment = new FakultasLainFragment();
        fragment.setArguments(args);
        return fragment;

    }

    public RecyclerView recyclerView;
    public List<Fakultas> fakultasList;
    public FakultasAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fakultas_lain, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.fakultasLain_recycler);

        fakultasList = new ArrayList<>();

        fakultasList.add(new Fakultas(1, R.drawable.ic_fh, R.string.fakultasFH));
        fakultasList.add(new Fakultas(2, R.drawable.ic_feb, R.string.fakultasFEB));
        fakultasList.add(new Fakultas(3, R.drawable.ic_fia, R.string.fakultasFIA));
        fakultasList.add(new Fakultas(4, R.drawable.ic_fp, R.string.fakultasFP));
        fakultasList.add(new Fakultas(5, R.drawable.ic_fapet, R.string.fakultasFapet));
        fakultasList.add(new Fakultas(6, R.drawable.ic_tekniik, R.string.fakultasFT));
        fakultasList.add(new Fakultas(7, R.drawable.ic_fk, R.string.fakultasFK));
        fakultasList.add(new Fakultas(8, R.drawable.ic_fpik, R.string.fakultasFPIK));
        fakultasList.add(new Fakultas(9, R.drawable.ic_fmipa, R.string.fakultasFMIPA));
        fakultasList.add(new Fakultas(10, R.drawable.ic_ftp, R.string.fakultasFTP));
        fakultasList.add(new Fakultas(11, R.drawable.ic_fisip, R.string.fakultasFISIP));
        fakultasList.add(new Fakultas(12, R.drawable.ic_fib, R.string.fakultasFIB));
        fakultasList.add(new Fakultas(13, R.drawable.ic_fkh, R.string.fakultasFKH));
        fakultasList.add(new Fakultas(14, R.drawable.ic_fkg, R.string.fakultasFKG));
        fakultasList.add(new Fakultas(15, R.drawable.ic_filkom3, R.string.fakultasFILKOM));
        fakultasList.add(new Fakultas(16, R.drawable.ic_vokasi2, R.string.fakultasVokasi));
        fakultasList.add(new Fakultas(17, R.drawable.ic_ub_kediri, R.string.fakultasKediri));

        adapter = new FakultasAdapter(getContext(), fakultasList);
        adapter.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void itemClicked(View view, int position) {
        Fakultas fakultas = fakultasList.get(position);
        Fragment fragment = FakultasLainContainerFragment.newInstance(fakultas.getId());

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        ft.replace(R.id.content_frame, fragment);
        ft.addToBackStack("backstack");
        ft.commit();
    }
}
