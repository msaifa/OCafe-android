package com.komputerkit.ocafe.Fragment.Laporan;

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
import com.komputerkit.ocafe.ActivityLogin;
import com.komputerkit.ocafe.Adapter.AdapterLapJenis;
import com.komputerkit.ocafe.DialogFragment.DDetailJenis;
import com.komputerkit.ocafe.Model.TblJenisBarang;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by msaifa on 14/02/2018.
 */

public class LJenisBarang extends Fragment {

    View v ;
    AdapterLapJenis adapterLapJenis ;
    RecyclerView recyclerView ;
    EditText etCari ;
    ProgressBar progressBar ;

    @Override
    public void onStart() {
        super.onStart();

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
        final Utilitas utilitas = new Utilitas(getActivity()) ;
        ApiInterface apiInterface = ApiClient.retrofitGet().create(ApiInterface.class) ;
        Call<List<TblJenisBarang>> call = apiInterface.getJenisBarang(Utilitas.getToken("Username"),etCari.getText().toString()) ;
        call.enqueue(new Callback<List<TblJenisBarang>>() {
            @Override
            public void onResponse(Call<List<TblJenisBarang>> call, Response<List<TblJenisBarang>> response) {

                adapterLapJenis = new AdapterLapJenis(LJenisBarang.this,response.body()) ;
                recyclerView.setAdapter(adapterLapJenis) ;
                adapterLapJenis.notifyDataSetChanged();

                utilitas.setText(v,R.id.tvJumlahB,utilitas.intToStr(response.body().size()) + " Data") ;
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<TblJenisBarang>> call, Throwable t) {
                utilitas.toast("error Load Data\n" + t.toString());

                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void pilih(TblJenisBarang tblJenisBarang){

        DDetailJenis detail = new DDetailJenis(this,tblJenisBarang) ;
        FragmentManager fm = getFragmentManager() ;
        detail.show(fm,"asd") ;
    }

}
