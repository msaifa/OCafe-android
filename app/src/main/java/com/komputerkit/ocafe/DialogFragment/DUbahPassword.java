package com.komputerkit.ocafe.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.komputerkit.ocafe.API.ApiClient;
import com.komputerkit.ocafe.API.ApiInterface;
import com.komputerkit.ocafe.ActivityLogin;
import com.komputerkit.ocafe.Fragment.Pelanggan.PAkun;
import com.komputerkit.ocafe.Model.Response;
import com.komputerkit.ocafe.Model.TblPelanggan;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

// MANUAL
// import + (PACKAGE CLASS) + . + (NAME CLASS)

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by msaifa on 22/02/2018.
 */

@SuppressLint("ValidFragment")
public class DUbahPassword extends DialogFragment {

    View v;
    Utilitas utilitas;
    Context context;
    PAkun pAkun;
    Button btnEdit;
    TblPelanggan tblPelanggan ;
    ProgressDialog progressDialog ;

    @SuppressLint("ValidFragment")
    public DUbahPassword(PAkun pAkun) {
        this.pAkun = pAkun;
        tblPelanggan = pAkun.tblPelanggan ;
    }

    @Override
    public void onStart() {
        super.onStart();

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        utilitas = new Utilitas(pAkun.getActivity());
        progressDialog = new ProgressDialog(pAkun.getActivity()) ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_ubah_password, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v = view;

        init();
        event();
    }

    public void init() {
        btnEdit = v.findViewById(R.id.btnUbah) ;
    }

    public void event() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (utilitas.getText(v,R.id.pasLama).equals(utilitas.getText(v,R.id.pasKonfirm))){
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                    ubah() ;
                } else {
                    utilitas.toast("Password Baru dan password konfirmasi tidak sama");
                }
            }
        });
    }

    public void keluar() {
        getActivity().getFragmentManager().beginTransaction().remove(DUbahPassword.this).commit();
    }

    public void ubah(){
        String id = utilitas.getText(v,R.id.pasBaru) ;
        String pelanggan = utilitas.getText(v,R.id.pasLama) ;


        ApiInterface apiInterface = ApiClient.retrofitGet().create(ApiInterface.class) ;
        Call<List<Response>> call = apiInterface.ubahPassword(utilitas.getToken(),id,pelanggan,tblPelanggan.getId_pelanggan()) ;
        call.enqueue(new Callback<List<Response>>() {
            @Override
            public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {
                try{
                    if (response.body().size() == 0){
                        utilitas.toast("Gagal Ubah Profil");
                        progressDialog.dismiss();
                    } else {
                        if (response.body().get(0).getResponse().equals("berhasil")){
                            utilitas.setSession("isLogin","");
                            utilitas.toast("Ubah Password Berhasil. Silahkan Login Kembali") ;
                            startActivity(new Intent(pAkun.getActivity(), ActivityLogin.class));
                            keluar();
                            progressDialog.dismiss();
                        } else {
                            utilitas.toast("Gagal Ubah Profil");
                            progressDialog.dismiss();
                        }
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<List<Response>> call, Throwable t) {
                utilitas.toast("Failed Ubah Profile" + t.toString());
                progressDialog.dismiss();
            }
        });
    }

}
