package com.komputerkit.ocafe.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.komputerkit.ocafe.ActivityUtamaPelanggan;
import com.komputerkit.ocafe.Adapter.AdapterCart;
import com.komputerkit.ocafe.Model.QBarang;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msaifa on 22/02/2018.
 */

@SuppressLint("ValidFragment")
public class DCart extends DialogFragment {

    View v;
    Utilitas utilitas ;
    ActivityUtamaPelanggan context ;
    RecyclerView recyclerView ;
    AdapterCart adapter ;
    List<QBarang> temp = new ArrayList<>() ;
    Button btnCekout,btnKembali ;
    ImageView btnBack;
    ConstraintLayout wKosong,wJumlah ;

    @SuppressLint("ValidFragment")
    public DCart(ActivityUtamaPelanggan activity) {
        this.context = activity ;
    }

    @Override
    public void onStart() {
        super.onStart();

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        utilitas = new Utilitas(context) ;
        load() ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_cart, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v = view;

        init();
        event();
    }

    public void init() {
        recyclerView = v.findViewById(R.id.recCart) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new AdapterCart(this,temp) ;
        recyclerView.setAdapter(adapter);
        btnCekout = v.findViewById(R.id.btnCekout) ;
        btnKembali = v.findViewById(R.id.btnKembali) ;
        btnBack= v.findViewById(R.id.btnBack) ;
        wKosong = v.findViewById(R.id.wKosong) ;
        wJumlah = v.findViewById(R.id.wJumlah) ;
    }

    public void event() {
        btnCekout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindah();
            }
        });
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keluar() ;
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keluar();
            }
        });
    }

    public void keluar() {
        getActivity().getFragmentManager().beginTransaction().remove(DCart.this).commit();
    }

    public void load(){
        temp.clear();
        int jum = 0 ;
        for(int i = 0 ; i < context.list.size() ; i++){
            if (utilitas.strToInt(context.list.get(i).getJumlah()) > 0){
                temp.add(context.list.get(i)) ;
                jum += Utilitas.strToInt(context.list.get(i).getHarga_jual()) * Utilitas.strToInt(context.list.get(i).getJumlah()) ;
            }
        }

        if (temp.size() == 0){
            wKosong.setVisibility(View.VISIBLE);
            btnCekout.setVisibility(View.GONE);
            wJumlah.setVisibility(View.GONE);
        }
        String jumlah = utilitas.intToStr(jum) ;
        utilitas.setText(v,R.id.tvJumlah,"Rp. " + utilitas.removeE(jumlah)) ;
        adapter.notifyDataSetChanged();
    }

    public void plus(String id){

        for (int i = 0 ; i < context.list.size() ; i++){
            if (context.list.get(i).getId_barang().equals(id)){

                if (utilitas.strToInt(context.list.get(i).getJumlah()) < utilitas.strToInt(context.list.get(i).getStok())){
                    String jumlah = context.list.get(i).getJumlah() ;
                    int jum = utilitas.strToInt(jumlah) + 1 ;
                    context.list.get(i).setJumlah(utilitas.intToStr(jum));
                } else {
                    utilitas.toast("Stok Tidak mencukupi");
                }
            }
        }

        load() ;
    }

    public void min(String id){

        for (int i = 0 ; i < context.list.size() ; i++){
            if (context.list.get(i).getId_barang().equals(id)){
                String jumlah = context.list.get(i).getJumlah() ;
                int jum = utilitas.strToInt(jumlah) - 1 ;
                context.list.get(i).setJumlah(utilitas.intToStr(jum));
            }
        }

        load() ;
    }

    public void pindah(){
        DKonfirmasi dKonfirmasi = new DKonfirmasi(context) ;
        FragmentManager fm = getFragmentManager() ;
        dKonfirmasi.show(fm,"adsf") ;
    }
}
