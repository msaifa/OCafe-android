package com.komputerkit.ocafe.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.Toast;

import com.komputerkit.ocafe.API.ApiClient;
import com.komputerkit.ocafe.API.ApiInterface;
import com.komputerkit.ocafe.ActivityUtamaPelanggan;
import com.komputerkit.ocafe.Adapter.AdapterKonfirmasi;
import com.komputerkit.ocafe.Model.QBarang;
import com.komputerkit.ocafe.Model.Response;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by msaifa on 22/02/2018.
 */

@SuppressLint("ValidFragment")
public class DKonfirmasi extends DialogFragment {

    View v;
    Utilitas utilitas ;
    ActivityUtamaPelanggan context ;
    RecyclerView recyclerView ;
    AdapterKonfirmasi adapter ;
    List<QBarang> temp = new ArrayList<>() ;
    ImageView btnBack ;
    Button btnOrder;
    double total = 0 ;
    int gagal = 0;
    ProgressDialog progressDialog ;

    @SuppressLint("ValidFragment")
    public DKonfirmasi(ActivityUtamaPelanggan activity) {
        this.context = activity ;
    }

    @Override
    public void onStart() {
        super.onStart();

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        utilitas = new Utilitas(context) ;
        load() ;
        progressDialog = new ProgressDialog(context) ;
        progressDialog.setMessage("Loading...");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_konfirmasi, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v = view;

        init();
        event();
    }

    public void init() {
        recyclerView = v.findViewById(R.id.recKonfirmasi) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new AdapterKonfirmasi(temp) ;
        recyclerView.setAdapter(adapter);
        btnBack = v.findViewById(R.id.btnBack) ;
        btnOrder = v.findViewById(R.id.btnOrder) ;
    }

    public void event() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keluar();
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (utilitas.strToInt(context.tblPelanggan.getSaldo()) >= total){
                    progressDialog.show();
                    bayar() ;
                } else {
                    utilitas.toast("Saldo Anda kurang. Harap topupp saldo anda");
                }

            }
        });
    }

    public void keluar() {
        getActivity().getFragmentManager().beginTransaction().remove(DKonfirmasi.this).commit();
    }

    public void load(){
        temp.clear();
        for(int i = 0 ; i < context.list.size() ; i++){
            if (utilitas.strToInt(context.list.get(i).getJumlah()) > 0){
                temp.add(context.list.get(i)) ;
                total += utilitas.strToDouble(context.list.get(i).getJumlah()) * utilitas.strToDouble(context.list.get(i).getHarga_jual()) ;
            }
        }
        adapter.notifyDataSetChanged();

        double saldo = utilitas.strToDouble(context.tblPelanggan.getSaldo()) ;
        double sisa = saldo - total ;
        utilitas.setText(v,R.id.tvTanggal,utilitas.removeE(total)) ;
        utilitas.setText(v,R.id.tvStatus,utilitas.removeE(saldo)) ;
        utilitas.setText(v,R.id.tvSisaSaldo,utilitas.removeE(sisa)) ;
        utilitas.setText(v,R.id.etNama,context.tblPelanggan.getPelanggan()) ;
        utilitas.setText(v,R.id.etAlamat,context.tblPelanggan.getAlamat()) ;
        utilitas.setText(v,R.id.etTelp,context.tblPelanggan.getNotelp()) ;
    }

    public void bayar(){
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.CEILING);
        String totalBayar = df.format(total) ;

        String id_pelanggan = context.tblPelanggan.getId_pelanggan() ;
        String tgl_bayar = utilitas.getDate("yyyy-MM-dd") ;

        ApiInterface apiInterface = ApiClient.retrofitGet().create(ApiInterface.class) ;

        Call<List<Response>> call = apiInterface.bayar(utilitas.getToken(id_pelanggan),totalBayar,id_pelanggan,totalBayar,"0",tgl_bayar) ;
        call.enqueue(new Callback<List<Response>>() {
            @Override
            public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {
                try{
                    if (response.body().size() == 1){
                        if (response.body().get(0).getResponse().equals("berhasil")){
                            order(response.body().get(0).getFaktur());
                        } else {
                            utilitas.toast("Failed Transaction");
                        }
                    } else {
                        utilitas.toast("Failed Transaction");
                        progressDialog.dismiss();
                    }
                }catch (Exception e){
                    utilitas.toast("Failed Transaction");
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Response>> call, Throwable t) {
                utilitas.toast("Failed Transaction\n" + t.toString());
                progressDialog.dismiss();
            }
        });
    }

    public void order(String faktur){
        String tgl = utilitas.getDate("yyyy-MM-dd") ;
        String jam = utilitas.getDate("HH:mm") ;
        ApiInterface apiInterface = ApiClient.retrofitGet().create(ApiInterface.class) ;


        int i = 0 ;
        while (i < context.list.size()){
            if (utilitas.strToInt(context.list.get(i).getJumlah()) > 0){
                Call<List<Response>> call = apiInterface.order(utilitas.getToken("asd"),
                        context.list.get(i).getId_barang(),tgl,jam,
                        context.list.get(i).getJumlah(),context.list.get(i).getHarga_jual(),faktur) ;
                call.enqueue(new Callback<List<Response>>() {
                    @Override
                    public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {
                        try{
                            if (response.body().get(0).getResponse().equals("berhasil")){
                            } else {
                                gagal = 1  ;
                                utilitas.toast("Error3");
                            }
                        }catch (Exception e){
                            gagal = 1 ;
                            utilitas.toast("Error2" + e.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Response>> call, Throwable t) {
                        gagal = 1;
                        utilitas.toast("Error1" + t.toString());
                    }
                });
            }
            i++ ;
        }

        if (gagal == 0){

            DSukses dSukses = new DSukses(context) ;
            FragmentManager fm = getFragmentManager() ;
            dSukses.show(fm,"asdf") ;

        } else {
            utilitas.toast("Terdapat transaksi error !");
            progressDialog.dismiss();
        }
    }
}
