package com.komputerkit.ocafe.Fragment.Pelayan;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.komputerkit.ocafe.Adapter.AdapterLapBarang;
import com.komputerkit.ocafe.Adapter.AdapterPesanan;
import com.komputerkit.ocafe.DialogFragment.DDetailPesanan;
import com.komputerkit.ocafe.Model.QBarang;
import com.komputerkit.ocafe.Model.QBayar;
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

public class YBeranda extends Fragment {

    View v ;
    AdapterPesanan adapter ;
    RecyclerView recyclerView ;
    EditText etCari ;
    ProgressBar progressBar ;
    public List<QBayar> list = new ArrayList<>() ;
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
        return inflater.inflate(R.layout.fragment_lap_master,container, false) ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view ;
        init() ;
        event() ;
    }

    public void init(){
        recyclerView = v.findViewById(R.id.recLapMaster) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        etCari = v.findViewById(R.id.etCari) ;
        progressBar = v.findViewById(R.id.progressBar) ;
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
    }

    public void load(){
        progressBar.setVisibility(View.VISIBLE);
        list.clear();
        final Utilitas utilitas = new Utilitas(getActivity()) ;

        ApiInterface apiInterface = ApiClient.retrofitGet().create(ApiInterface.class) ;
        Call<List<QBayar>> call = apiInterface.getPesanan(utilitas.getToken("admin"),etCari.getText().toString()) ;

        call.enqueue(new Callback<List<QBayar>>() {
            @Override
            public void onResponse(Call<List<QBayar>> call, Response<List<QBayar>> response) {
                 try{
                     if (response.body().size() > 0){

                         list = response.body() ;
                         adapter = new AdapterPesanan(YBeranda.this,list) ;
                         recyclerView.setAdapter(adapter) ;

                         utilitas.setText(v,R.id.tvJumlahB,utilitas.intToStr(response.body().size()) + " Data") ;
                     } else {
                         utilitas.toast("Tidak ada data");
                     }
                     adapter.notifyDataSetChanged();
                 }catch (Exception e){
                     utilitas.toast("Failed get Data" + e.toString());
                 }
                 progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<QBayar>> call, Throwable t) {
                utilitas.toast("Failed Get Data \n" + t.toString());
            }
        });

    }

    public void pilih(int position){

        DDetailPesanan d = new DDetailPesanan(this,list.get(position).getFaktur()) ;
        FragmentManager fm = getFragmentManager() ;
        d.show(fm,"adsf") ;

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
