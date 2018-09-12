package com.komputerkit.ocafe.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.komputerkit.ocafe.API.ApiClient;
import com.komputerkit.ocafe.API.ApiInterface;
import com.komputerkit.ocafe.ActivityUtamaPelanggan;
import com.komputerkit.ocafe.Fragment.Pelanggan.PAkun;
import com.komputerkit.ocafe.Model.QBarang;
import com.komputerkit.ocafe.Model.Response;
import com.komputerkit.ocafe.Model.TblPelanggan;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Config;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by msaifa on 22/02/2018.
 */

@SuppressLint("ValidFragment")
public class DDetailBarang extends DialogFragment{

    View v;
    Button btnEdit;
    List<QBarang> list ;
    Integer position ;
    QBarang qBarang ;
    Utilitas utilitas ;
    ActivityUtamaPelanggan activity ;
    ImageView imgBack ;

    @SuppressLint("ValidFragment")
    public DDetailBarang(ActivityUtamaPelanggan activity, List<QBarang> list, Integer position) {
        this.list = list ;
        this.position = position ;
        this.activity = activity ;
        qBarang = list.get(position) ;
    }

    @Override
    public void onStart() {
        super.onStart();

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        utilitas = new Utilitas(activity) ;
        load();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_detail_barang, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v = view;

        init();
        event();
    }

    public void init() {
        btnEdit = v.findViewById(R.id.btnPesan) ;
        imgBack = v.findViewById(R.id.btnBack) ;
    }

    public void event() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keluar();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String jumlah = activity.list.get(position).getJumlah() ;
                int jum = utilitas.strToInt(jumlah) +  1 ;
                activity.list.get(position).setJumlah(utilitas.intToStr(jum));

                FragmentManager fn = getFragmentManager() ;
                DCart dCart = new DCart(activity) ;
                dCart.show(fn,"asdf") ;
            }
        });
    }

    public void keluar() {
        getActivity().getFragmentManager().beginTransaction().remove(DDetailBarang.this).commit();
    }

    public void load(){
        utilitas.setText(v,R.id.titleBar,qBarang.getBarang()) ;
        utilitas.setText(v,R.id.tvBarang,qBarang.getBarang()) ;
        utilitas.setText(v,R.id.tvDeskripsi,"\t\t" +qBarang.getDeskripsi()) ;
        utilitas.setText(v,R.id.tvHarga,"Rp. " + utilitas.removeE(qBarang.getHarga_jual())) ;
        String uri = Config.getBaseUrl() + "assets/img/" + qBarang.getImg() ;

        ImageView imageView = v.findViewById(R.id.imgProduk) ;
        Glide.with(getActivity()).load(uri).into(imageView) ;
    }

}
