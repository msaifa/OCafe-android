package com.komputerkit.ocafe;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.komputerkit.ocafe.API.ApiClient;
import com.komputerkit.ocafe.API.ApiInterface;
import com.komputerkit.ocafe.Fragment.Login.LoginAdmin;
import com.komputerkit.ocafe.Fragment.Login.LoginPelanggan;
import com.komputerkit.ocafe.Fragment.Login.LoginUtama;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity {

    boolean posisi ;
    Utilitas utilitas ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        init() ;
    }

    public void init(){
        utilitas = new Utilitas(this) ;
        String level = utilitas.getSession("isLogin","") ;

        if (level.equals("")){
            pindah("utama") ;
        } else {
            if (level.equals("1")){
                pindah("bPelayan");
            } else if (level.equals("3")){
                pindah("bAdmin");
            } else if (level.equals("0")){
                pindah("bPelanggan");
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (posisi){
            pindah("utama");
            posisi = false ;
        } else {
            keluar();
        }
    }

    public void keluar(){
        utilitas.showDialog("Apakah Anda yakin untuk keluar ?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    finishAffinity();
                } else {
                    finish() ;
                }
            }
        });
    }

    public void pindah(String tujuan){
        posisi  = true ;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction() ;

        if (tujuan.equals("admin")){
            LoginAdmin loginAdmin = new LoginAdmin() ;
            fragmentTransaction.replace(R.id.wFragment,loginAdmin).commit() ;
        } else if (tujuan.equals("utama")){
            LoginUtama loginUtama = new LoginUtama() ;
            fragmentTransaction.replace(R.id.wFragment,loginUtama).commit();
        } else if (tujuan.equals("bAdmin")){
            Intent i = new Intent(this,ActivityUtamaAdmin.class) ;
            startActivity(i);
        } else if (tujuan.equals("bPelanggan")){
            Intent i = new Intent(this,ActivityUtamaPelanggan.class) ;
            startActivity(i);
        } else if (tujuan.equals("pelanggan")){
            LoginPelanggan loginUtama = new LoginPelanggan() ;
            fragmentTransaction.replace(R.id.wFragment,loginUtama).commit();
        } else if (tujuan.equals("bPelayan")){
            Intent i = new Intent(this,ActivityUtamaPelayan.class) ;
            startActivity(i);
        }
    }
}
