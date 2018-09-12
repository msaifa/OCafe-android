package com.komputerkit.ocafe.Fragment.Pelanggan;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.komputerkit.ocafe.ActivityUtamaPelanggan;
import com.komputerkit.ocafe.DialogFragment.DEditProfile;
import com.komputerkit.ocafe.DialogFragment.DUbahPassword;
import com.komputerkit.ocafe.Model.TblPelanggan;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

/**
 * Created by msaifa on 21/02/2018.
 */

public class PAkun extends Fragment {

    View v ;
    Button btnPengguna,btnPembeli ;
    ImageView btnBack ;
    Button btnEdit,btnUbah  ;
    public TblPelanggan tblPelanggan ;
    Utilitas utilitas ;

    @Override
    public void onStart() {
        super.onStart();
        utilitas = new Utilitas(getActivity()) ;
        tblPelanggan = ((ActivityUtamaPelanggan)getActivity()).tblPelanggan ;

        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pelanggan_profile,container, false) ;
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
        btnEdit = v.findViewById(R.id.btnEdit) ;
        btnUbah = v.findViewById(R.id.btnUbah) ;
    }

    public void event(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityUtamaPelanggan)getActivity()).open() ;
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager() ;
                DEditProfile dEditProfile = new DEditProfile(PAkun.this) ;
                dEditProfile.show(fm,"ubahpr");
            }
        });

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager() ;
                DUbahPassword dEditProfile = new DUbahPassword(PAkun.this) ;
                dEditProfile.show(fm,"ubahpr");
            }
        });
    }

    public void loadData(){
        utilitas.setText(v,R.id.tvNama,tblPelanggan.getPelanggan()) ;
        utilitas.setText(v,R.id.tvEmail,tblPelanggan.getEmail()) ;
        utilitas.setText(v,R.id.tvEmail2,tblPelanggan.getEmail()) ;
        utilitas.setText(v,R.id.tvStatus,"Rp. " + utilitas.removeE(tblPelanggan.getSaldo())) ;
        utilitas.setText(v,R.id.tvTelp,tblPelanggan.getNotelp()) ;
        utilitas.setText(v,R.id.tvEmail,tblPelanggan.getEmail()) ;
        utilitas.setText(v,R.id.tvAlamat,tblPelanggan.getAlamat()) ;
    }

}
