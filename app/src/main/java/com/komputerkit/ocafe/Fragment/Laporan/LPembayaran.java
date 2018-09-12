package com.komputerkit.ocafe.Fragment.Laporan;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.komputerkit.ocafe.API.ApiClient;
import com.komputerkit.ocafe.API.ApiInterface;
import com.komputerkit.ocafe.Adapter.AdapterLapPembayaran;
import com.komputerkit.ocafe.Model.QBayar;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by msaifa on 14/02/2018.
 */

public class LPembayaran extends Fragment {

    View v ;
    EditText etCari,etDari,etSampai ;
    AdapterLapPembayaran adapter ;
    RecyclerView recyclerView ;
    ProgressBar progressBar ;
    Utilitas utilitas ;
    ConstraintLayout lDari,lSampai ;
    List<QBayar> data ;

    @Override
    public void onStart() {
        super.onStart();

        utilitas = new Utilitas(getActivity()) ;
        utilitas.setText(v,R.id.etDari,utilitas.getDate("dd-MM-yyyy")) ;
        utilitas.setText(v,R.id.etSampai,utilitas.getDate("dd-MM-yyyy")) ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lap_transaksi,container, false) ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view ;
        init() ;
        event() ;
    }

    public void init(){
        recyclerView = v.findViewById(R.id.recTransaksi) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        etCari = v.findViewById(R.id.etCari) ;
        etDari = v.findViewById(R.id.etDari) ;
        etSampai = v.findViewById(R.id.etSampai) ;
        progressBar = v.findViewById(R.id.progressBar) ;
        lDari = v.findViewById(R.id.lDari) ;
        lSampai = v.findViewById(R.id.lSampai) ;
    }

    public void event(){
        etCari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                load();
            }
        });

        etDari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                load();
            }
        });

        etSampai.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                load();
            }
        });

        lDari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utilitas.showDateDialog(v,R.id.etDari);
            }
        });

        lSampai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilitas.showDateDialog(etSampai);
            }
        });
    }

    public void load(){
        progressBar.setVisibility(View.VISIBLE);
        String cari = etCari.getText().toString() ;
        String dari = etDari.getText().toString() ;
        String sampai = etSampai.getText().toString() ;

        final String tglDari = Utilitas.getYear(dari) +"-"+ Utilitas.getMonth(dari) +"-"+ Utilitas.getDay(dari) ;
        final String tglSampai = Utilitas.getYear(sampai) +"-"+ Utilitas.getMonth(sampai) +"-"+ Utilitas.getDay(sampai) ;

        ApiInterface apiInterface = ApiClient.retrofitGet().create(ApiInterface.class) ;
        Call<List<QBayar>> call = apiInterface.getPembayaran(Utilitas.getToken("user"),cari,tglDari,tglSampai) ;
        call.enqueue(new Callback<List<QBayar>>() {
            @Override
            public void onResponse(Call<List<QBayar>> call, Response<List<QBayar>> response) {
                adapter = new AdapterLapPembayaran(response.body()) ;
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                data = response.body() ;

                jum();
            }

            @Override
            public void onFailure(Call<List<QBayar>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed Get Data\n" + t.toString(), Toast.LENGTH_SHORT).show();

                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void jum(){
        int total = 0 ;
        for(int i = 0 ; i < data.size() ; i++){
            total += Utilitas.strToInt(data.get(i).getTotal_bayar()) ;
        }
        String jum = utilitas.intToStr(total) ;
        utilitas.setText(v,R.id.tvJumlahBay,"Rp. " + utilitas.removeE(jum)) ;

        progressBar.setVisibility(View.GONE);
    }

}
