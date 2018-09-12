package com.komputerkit.ocafe.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.komputerkit.ocafe.API.ApiClient;
import com.komputerkit.ocafe.API.ApiInterface;
import com.komputerkit.ocafe.Fragment.Pelanggan.PAkun;
import com.komputerkit.ocafe.Model.Response;
import com.komputerkit.ocafe.Model.TblPelanggan;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by msaifa on 22/02/2018.
 */

@SuppressLint("ValidFragment")
public class DEditProfile extends DialogFragment {

    View v;
    Utilitas utilitas;
    Context context;
    PAkun pAkun;
    Button btnEdit;
    TblPelanggan tblPelanggan ;

    @SuppressLint("ValidFragment")
    public DEditProfile(PAkun pAkun) {
        this.pAkun = pAkun;
        tblPelanggan = pAkun.tblPelanggan ;
    }

    @Override
    public void onStart() {
        super.onStart();

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        utilitas = new Utilitas(pAkun.getActivity());

        load();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_edit_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v = view;

        init();
        event();
    }

    public void init() {
        btnEdit = v.findViewById(R.id.btnEdit) ;
    }

    public void event() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubah() ;
            }
        });
    }

    public void keluar() {
        getActivity().getFragmentManager().beginTransaction().remove(DEditProfile.this).commit();
    }

    public void ubah(){
        String id = tblPelanggan.getId_pelanggan() ;
        String pelanggan = utilitas.getText(v,R.id.etNama) ;
        String email = utilitas.getText(v,R.id.etEmail) ;
        String notelp = utilitas.getText(v,R.id.etNotelp) ;
        String alamat = utilitas.getText(v,R.id.etAlamat) ;


        ApiInterface apiInterface = ApiClient.retrofitGet().create(ApiInterface.class) ;
        Call<List<Response>> call = apiInterface.ubahProfile(utilitas.getToken(),id,pelanggan,alamat,notelp,email) ;
        call.enqueue(new Callback<List<Response>>() {
            @Override
            public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {
                try{
                    if (response.body().size() == 0){
                        utilitas.toast("Gagal Ubah Profil");
                    } else {
                        if (response.body().get(0).getResponse().equals("berhasil")){
                            berhasil() ;
                            pAkun.loadData();
                            utilitas.toast("Ubah Profil Berhasil");
                            keluar();
                        } else {
                            utilitas.toast("Gagal Ubah Profil");
                        }
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<List<Response>> call, Throwable t) {
                utilitas.toast("Failed Ubah Profile" + t.toString());
            }
        });
    }

    public void load(){
        utilitas.setText(v,R.id.etNama,tblPelanggan.getPelanggan()) ;
        utilitas.setText(v,R.id.etEmail,tblPelanggan.getEmail()) ;
        utilitas.setText(v,R.id.etEmail,tblPelanggan.getEmail()) ;
        utilitas.setText(v,R.id.etNotelp,tblPelanggan.getNotelp()) ;
        utilitas.setText(v,R.id.etAlamat,tblPelanggan.getAlamat()) ;
    }

    public void berhasil(){
        String pelanggan = utilitas.getText(v,R.id.etNama) ;
        String email = utilitas.getText(v,R.id.etEmail) ;
        String notelp = utilitas.getText(v,R.id.etNotelp) ;
        String alamat = utilitas.getText(v,R.id.etAlamat) ;

        tblPelanggan.setPelanggan(pelanggan);
        tblPelanggan.setEmail(email);
        tblPelanggan.setNotelp(notelp);
        tblPelanggan.setAlamat(alamat);
    }

}