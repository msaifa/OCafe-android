package com.komputerkit.ocafe.Fragment.Pelanggan;

import android.app.Fragment;
import android.app.FragmentManager;
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
import com.komputerkit.ocafe.ActivityLogin;
import com.komputerkit.ocafe.ActivityUtamaPelanggan;
import com.komputerkit.ocafe.Adapter.AdapterKategori;
import com.komputerkit.ocafe.DialogFragment.DProdukKategori;
import com.komputerkit.ocafe.Model.TblJenisBarang;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by msaifa on 21/02/2018.
 */

public class PKategori extends Fragment {
    View v ;
    ImageView btnBack ;
    Utilitas utilitas ;

    @Override
    public void onStart() {
        super.onStart();
        utilitas = new Utilitas(getActivity()) ;
        load();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pelanggan_kategori,container, false) ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view ;
        init() ;
        event() ;
    }

    public void init(){
        btnBack = v.findViewById(R.id.btnBack) ;
    }

    public void event(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityUtamaPelanggan)getActivity()).open() ;
            }
        });
    }

    public void load(){
        ApiInterface apiInterface = ApiClient.retrofitGet().create(ApiInterface.class) ;
        Call<List<TblJenisBarang>> call = apiInterface.getJenisBarang(utilitas.getToken("username"),"") ;
        call.enqueue(new Callback<List<TblJenisBarang>>() {
            @Override
            public void onResponse(Call<List<TblJenisBarang>> call, Response<List<TblJenisBarang>> response) {
                RecyclerView recyclerView = v.findViewById(R.id.recKategori) ;
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                AdapterKategori adapter = new AdapterKategori(PKategori.this,response.body()) ;
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<TblJenisBarang>> call, Throwable t) {
                utilitas.toast("Failed get Data");
            }
        });
    }

    public void pilih(TblJenisBarang tblJenisBarang) {
        DProdukKategori dProdukKategori = new DProdukKategori(((ActivityUtamaPelanggan)getActivity()),tblJenisBarang) ;
        FragmentManager fm = getFragmentManager() ;
        dProdukKategori.show(fm,"adsf") ;
    }
}
