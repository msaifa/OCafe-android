package com.komputerkit.ocafe.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.komputerkit.ocafe.API.ApiClient;
import com.komputerkit.ocafe.API.ApiInterface;
import com.komputerkit.ocafe.ActivityUtamaPelanggan;
import com.komputerkit.ocafe.Adapter.AdapterDetailHistory;
import com.komputerkit.ocafe.Adapter.AdapterDetailPesanan;
import com.komputerkit.ocafe.Fragment.Pelayan.YBeranda;
import com.komputerkit.ocafe.Model.QBayar;
import com.komputerkit.ocafe.Model.QOrder;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by msaifa on 24/02/2018.
 */

@SuppressLint("ValidFragment")
public class DDetailPesanan extends DialogFragment {

    View v;
    Utilitas utilitas ;
    public YBeranda yBeranda ; ;
    ProgressBar progressBar ;
    ImageView btnBack ;
    RecyclerView recyclerView ;
    String faktur ;
    Button btnKonfirm ;

    @SuppressLint("ValidFragment")
    public DDetailPesanan(YBeranda yBeranda, String faktur) {
        this.yBeranda = yBeranda  ;
        this.faktur = faktur ;
    }

    @Override
    public void onStart() {
        super.onStart();

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        utilitas = new Utilitas(yBeranda.getActivity()) ;

        load() ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_detail_pesanan, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v = view;

        init();
        event();
    }

    public void init() {
        progressBar = v.findViewById(R.id.progressBar) ;
        btnBack = v.findViewById(R.id.btnBack) ;
        btnKonfirm = v.findViewById(R.id.btnKonfirm) ;
        recyclerView = v.findViewById(R.id.recProduk) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(yBeranda.getActivity()));
    }

    public void event() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keluar();
            }
        });

        btnKonfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilih() ;
            }
        });
    }

    public void keluar() {
        getActivity().getFragmentManager().beginTransaction().remove(DDetailPesanan.this).commit();
    }

    public void load(){
        ApiInterface apiInterface = ApiClient.retrofitGet().create(ApiInterface.class) ;
        Call<List<QOrder>> call = apiInterface.getDetailPesanan(utilitas.getToken("admin"),faktur) ;
        call.enqueue(new Callback<List<QOrder>>() {
            @Override
            public void onResponse(Call<List<QOrder>> call, Response<List<QOrder>> response) {
                try{
                    if (response.body().size() > 0){

                        AdapterDetailPesanan adapter = new AdapterDetailPesanan(DDetailPesanan.this,response.body()) ;
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);

                    } else {
                        utilitas.toast("Tidak ada data");
                        progressBar.setVisibility(View.GONE);
                    }
                } catch (Exception e){
                    utilitas.toast("Failed get Data");
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<QOrder>> call, Throwable t) {
                utilitas.toast("Failed get Data \n" + t.toString());
            }
        });
    }

    public void pilih(){
        ApiInterface apiInterface = ApiClient.retrofitGet().create(ApiInterface.class) ;
        Call<List<com.komputerkit.ocafe.Model.Response>> call = apiInterface.konfirmPesanan(utilitas.getToken("admin"),faktur) ;

        call.enqueue(new Callback<List<com.komputerkit.ocafe.Model.Response>>() {
            @Override
            public void onResponse(Call<List<com.komputerkit.ocafe.Model.Response>> call, Response<List<com.komputerkit.ocafe.Model.Response>> response) {
                try {
                    if (response.body().size() > 0){

                        yBeranda.load();
                        utilitas.toast("Konfirmasi berhasil");
                        keluar();

                    } else {
                        utilitas.toast("tidak ada data");
                    }
                }catch (Exception e){
                    utilitas.toast("gagal konfirmasi pesanan");
                }
            }

            @Override
            public void onFailure(Call<List<com.komputerkit.ocafe.Model.Response>> call, Throwable t) {
                utilitas.toast("Gagal konfirmasi pesanan");
            }
        });
    }

}
