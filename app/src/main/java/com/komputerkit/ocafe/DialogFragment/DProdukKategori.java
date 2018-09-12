package com.komputerkit.ocafe.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.komputerkit.ocafe.API.ApiClient;
import com.komputerkit.ocafe.API.ApiInterface;
import com.komputerkit.ocafe.ActivityUtamaPelanggan;
import com.komputerkit.ocafe.Adapter.AdapterProdukKategori;
import com.komputerkit.ocafe.Fragment.Pelanggan.PAkun;
import com.komputerkit.ocafe.Model.QBarang;
import com.komputerkit.ocafe.Model.Response;
import com.komputerkit.ocafe.Model.TblJenisBarang;
import com.komputerkit.ocafe.Model.TblPelanggan;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by msaifa on 22/02/2018.
 */

@SuppressLint("ValidFragment")
public class DProdukKategori extends DialogFragment {

    View v;
    Utilitas utilitas;
    Context context;
    ImageView btnEdit;
    public ActivityUtamaPelanggan activity ;
    TblJenisBarang tblJenisBarang ;

    @SuppressLint("ValidFragment")
    public DProdukKategori(ActivityUtamaPelanggan activity, TblJenisBarang tblJenisBarang) {
        this.activity = activity ;
        this.tblJenisBarang = tblJenisBarang ;
    }

    @Override
    public void onStart() {
        super.onStart();

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        utilitas = new Utilitas(activity);
        load() ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_produk_kategori, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v = view;

        init();
        event();
    }

    public void init() {
        btnEdit = v.findViewById(R.id.btnBack) ;

        utilitas.setText(v,R.id.tvKat,tblJenisBarang.getJenis()) ;
        utilitas.setText(v,R.id.tvKet,tblJenisBarang.getKetJenis()) ;
    }

    public void event() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keluar();
            }
        });
    }

    public void keluar() {
        getActivity().getFragmentManager().beginTransaction().remove(DProdukKategori.this).commit();
    }

    public void load(){
        List<QBarang> list = activity.list ;
        List<QBarang> temp = new ArrayList<>() ;

        for(int i = 0 ; i < list.size() ; i++) {
            if (list.get(i).getId_jenis().equals(tblJenisBarang.getIdJenis())) {
                temp.add(list.get(i));
            }
        }

        AdapterProdukKategori adapter = new AdapterProdukKategori(this,temp) ;
        RecyclerView recyclerView = v.findViewById(R.id.recProduk) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void pilih(String idBarang){
        List<QBarang> list = activity.list ;
        int position = 0;
        for(int i = 0 ; i < list.size() ; i++){
            if (list.get(i).getId_barang().equals(idBarang)){
                position = i ;
            }
        }

        DDetailBarang dDetailBarang = new DDetailBarang(activity,list,position) ;
        FragmentManager FM = getFragmentManager() ;
        dDetailBarang.show(FM,"adsf") ;
    }

}
