package com.komputerkit.ocafe.Fragment.Login;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.komputerkit.ocafe.API.ApiClient;
import com.komputerkit.ocafe.API.ApiInterface;
import com.komputerkit.ocafe.ActivityLogin;
import com.komputerkit.ocafe.Model.TblPelanggan;
import com.komputerkit.ocafe.Model.TblPelanggan;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by msaifa on 14/02/2018.
 */

public class LoginPelanggan extends Fragment {

    View v ;
    Button btnLoginAdminOk ;
    Utilitas utilitas ;
    ProgressDialog progressDialog ;

    @Override
    public void onStart() {
        super.onStart();
        utilitas = new Utilitas(getActivity()) ;
        progressDialog = new ProgressDialog(getActivity()) ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_admin,container, false) ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view ;
        init() ;
        event() ;
    }

    public void init(){
        btnLoginAdminOk = v.findViewById(R.id.btnLog) ;
    }

    public void event(){
        btnLoginAdminOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                login();
            }
        });
    }

    public void login(){
        String email = utilitas.getText(v,R.id.etEmail) ;
        String password = utilitas.getText(v,R.id.etPassword) ;

        ApiInterface apiInterface = ApiClient.retrofitGet().create(ApiInterface.class) ;
        Call<List<TblPelanggan>> call = apiInterface.loginPelanggan(utilitas.getToken("admin"),email,password) ;
        call.enqueue(new Callback<List<TblPelanggan>>() {
            @Override
            public void onResponse(Call<List<TblPelanggan>> call, Response<List<TblPelanggan>> response) {
                List<TblPelanggan> list = response.body() ;
                if (list.size() == 1){
                    String id_pelanggan = list.get(0).getId_pelanggan() ;
                    utilitas.setSession("id_pelanggan",id_pelanggan);
                    utilitas.setSession("isLogin","0");

                    ((ActivityLogin)getActivity()).pindah("bPelanggan");
                } else {
                    utilitas.toast("Username atau password anda salah.");
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<TblPelanggan>> call, Throwable t) {
                utilitas.toast("Failed Load data.\n" + t.toString());
                progressDialog.dismiss();
            }
        });
    }
}
