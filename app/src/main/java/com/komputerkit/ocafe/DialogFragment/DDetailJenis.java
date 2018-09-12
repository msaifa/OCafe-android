package com.komputerkit.ocafe.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.komputerkit.ocafe.API.ApiClient;
import com.komputerkit.ocafe.API.ApiInterface;
import com.komputerkit.ocafe.Adapter.AdapterLapBarang;
import com.komputerkit.ocafe.Fragment.Laporan.LBarang;
import com.komputerkit.ocafe.Fragment.Laporan.LJenisBarang;
import com.komputerkit.ocafe.Model.QBarang;
import com.komputerkit.ocafe.Model.TblJenisBarang;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by msaifa on 24/02/2018.
 */

@SuppressLint("ValidFragment")
public class DDetailJenis extends DialogFragment {

    View v;
    Utilitas utilitas;
    Context context;
    ImageView btnEdit;
    TblJenisBarang tblJenisBarang ;
    LJenisBarang lJenisBarang ;
    ProgressBar progressBar ;

    @SuppressLint("ValidFragment")

    public DDetailJenis(LJenisBarang lJenisBarang, TblJenisBarang tblJenisBarang) {
        this.tblJenisBarang = tblJenisBarang ;
        this.lJenisBarang = lJenisBarang ;
    }

    @Override
    public void onStart() {
        super.onStart();

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        utilitas = new Utilitas(lJenisBarang.getActivity());
        load() ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_detail_history, container, false);
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
        progressBar = v.findViewById(R.id.progressBar) ;
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
        getActivity().getFragmentManager().beginTransaction().remove(DDetailJenis.this).commit();
    }

    public void load(){
        ApiInterface apiInterface = ApiClient.retrofitGet().create(ApiInterface.class);
        Call<List<QBarang>> call = apiInterface.getDetailBarang(utilitas.getToken("admin"),tblJenisBarang.getIdJenis()) ;

        call.enqueue(new Callback<List<QBarang>>() {
            @Override
            public void onResponse(Call<List<QBarang>> call, Response<List<QBarang>> response) {
                try{
                    if (response.body().size() > 0){
                        AdapterLapBarang adapter = new AdapterLapBarang(response.body()) ;
                        RecyclerView recyclerView = v.findViewById(R.id.recProduk) ;
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(lJenisBarang.getActivity()));

                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                    } else {
                        utilitas.toast("Tida ada data");
                        progressBar.setVisibility(View.GONE);
                    }
                } catch (Exception e){
                    utilitas.toast("Failed get Data");
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<QBarang>> call, Throwable t) {
                utilitas.toast("Failed get Data \n" + t.toString());
                progressBar.setVisibility(View.GONE);
            }
        });

        utilitas.setText(v,R.id.titleBar,tblJenisBarang.getJenis()) ;

    }

}
