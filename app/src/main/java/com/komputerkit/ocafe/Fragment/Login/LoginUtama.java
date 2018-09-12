package com.komputerkit.ocafe.Fragment.Login;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.komputerkit.ocafe.ActivityLogin;
import com.komputerkit.ocafe.R;

/**
 * Created by msaifa on 14/02/2018.
 */

public class LoginUtama extends Fragment{

    View v ;
    Button btnPengguna,btnPembeli ;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_utama,container, false) ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view ;
        init() ;
        event() ;
    }

    public void init(){
        btnPengguna = v.findViewById(R.id.BtnPengguna) ;
        btnPembeli = v.findViewById(R.id.BtnPembeli) ;
    }

    public void event(){
        btnPengguna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityLogin)getActivity()).pindah("admin");
            }
        });

        btnPembeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityLogin)getActivity()).pindah("pelanggan");
            }
        });
    }

}
