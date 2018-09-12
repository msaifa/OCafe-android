package com.komputerkit.ocafe.Fragment.Pelanggan;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
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
import com.komputerkit.ocafe.Adapter.AdapterHistory;
import com.komputerkit.ocafe.DialogFragment.DDetailHistory;
import com.komputerkit.ocafe.Model.QBayar;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by msaifa on 21/02/2018.
 */

@SuppressLint("ValidFragment")
public class PPesanan extends Fragment {

    View v ;
    Button btnPengguna,btnPembeli ;
    ProgressDialog progressDialog ;
    AdapterHistory adapter ;
    RecyclerView recyclerView ;
    ActivityUtamaPelanggan acativity ;
    ImageView btnBack ;
    Utilitas utilitas ;

    @SuppressLint("ValidFragment")
    public PPesanan(ActivityUtamaPelanggan acativity) {
        this.acativity = acativity;
    }

    @Override
    public void onStart() {
        super.onStart();
        progressDialog = new ProgressDialog(getActivity()) ;
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        utilitas = new Utilitas(getActivity()) ;
        load();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history,container, false) ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view ;
        init() ;
        event() ;
    }

    public void init(){
        recyclerView = v.findViewById(R.id.recLaporan) ;
        btnBack = v.findViewById(R.id.btnBack) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void event(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityUtamaPelanggan)getActivity()).open();
            }
        });
    }

    public void load(){

        ApiInterface apiInterface = ApiClient.retrofitGet().create(ApiInterface.class) ;
        Call<List<QBayar>> call = apiInterface.getHistory(Utilitas.getToken("user"),acativity.tblPelanggan.getId_pelanggan()) ;
        call.enqueue(new Callback<List<QBayar>>() {
            @Override
            public void onResponse(Call<List<QBayar>> call, Response<List<QBayar>> response) {
                adapter = new AdapterHistory(PPesanan.this,response.body()) ;
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<QBayar>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed Get Data\n" + t.toString(), Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        });
    }

    public void pilih(String faktur){

        DDetailHistory dDetailHistory = new DDetailHistory(acativity,faktur) ;
        FragmentManager fm = getFragmentManager() ;
        dDetailHistory.show(fm,"adsf") ;

    }

    public void cancel(String id_order){

        ApiInterface apiInterface = ApiClient.retrofitGet().create(ApiInterface.class) ;
        Call<List<com.komputerkit.ocafe.Model.Response>> call = apiInterface.cancelPesanan(utilitas.getToken("admin"),id_order) ;

        call.enqueue(new Callback<List<com.komputerkit.ocafe.Model.Response>>() {
            @Override
            public void onResponse(Call<List<com.komputerkit.ocafe.Model.Response>> call, Response<List<com.komputerkit.ocafe.Model.Response>> response) {
                try{

                    if (response.body().size() > 0){

                        if (response.body().get(0).getResponse().equals("berhasil")){
                            load();
                            utilitas.toast("Berhasil cancel pesanan");
                        } else {
                            utilitas.toast("gagal cancel pesanan");
                        }

                    } else {
                        utilitas.toast("Gagal");
                    }

                }catch (Exception e){
                    utilitas.toast("Failed get Data");
                }
            }

            @Override
            public void onFailure(Call<List<com.komputerkit.ocafe.Model.Response>> call, Throwable t) {
                utilitas.toast("Failed get Data");
            }
        });

    }

}
