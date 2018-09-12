package com.komputerkit.ocafe.Fragment.Login;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.komputerkit.ocafe.API.ApiClient;
import com.komputerkit.ocafe.API.ApiInterface;
import com.komputerkit.ocafe.ActivityLogin;
import com.komputerkit.ocafe.Model.TblPegawai;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by msaifa on 14/02/2018.
 */

public class LoginAdmin extends Fragment {

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
        Call<List<TblPegawai>> call = apiInterface.loginAdmin(utilitas.getToken("admin"),email,password) ;
        call.enqueue(new Callback<List<TblPegawai>>() {
            @Override
            public void onResponse(Call<List<TblPegawai>> call, Response<List<TblPegawai>> response) {
                List<TblPegawai> list = response.body() ;
                if (list.size() == 1){
                    if (response.body().get(0).getLevel().equals("3")){
                        utilitas.setSession("isLogin","3") ;
                        ((ActivityLogin)getActivity()).pindah("bAdmin") ;
                    } else if (response.body().get(0).getLevel().equals("0")){
                        utilitas.setSession("isLogin","1") ;
                        ((ActivityLogin)getActivity()).pindah("bPelayan") ;
                    }
                } else {
                    utilitas.toast("Username atau password anda salah.");
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<TblPegawai>> call, Throwable t) {
                utilitas.toast("Failed Load data.\n" + t.toString());
                progressDialog.dismiss();
            }
        });
    }

}
