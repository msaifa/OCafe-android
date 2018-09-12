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
 * Created by WahyuFS on 2018-02-23.
 */

public class PAbout extends Fragment {

    View v ;

    @Override
    public void onStart() {
        super.onStart();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about,container, false) ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view ;

        Button btnBack = v.findViewById(R.id.btnBack) ;
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityUtamaPelanggan)getActivity()).onBackPressed();
            }
        });
    }

}