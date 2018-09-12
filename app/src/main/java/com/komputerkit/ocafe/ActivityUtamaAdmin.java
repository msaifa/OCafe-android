package com.komputerkit.ocafe;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.komputerkit.ocafe.Fragment.Laporan.LBarang;
import com.komputerkit.ocafe.Fragment.Laporan.LJenisBarang;
import com.komputerkit.ocafe.Fragment.Laporan.LPegawai;
import com.komputerkit.ocafe.Fragment.Laporan.LPelanggan;
import com.komputerkit.ocafe.Fragment.Laporan.LPembayaran;
import com.komputerkit.ocafe.Fragment.Laporan.LPenjualan;
import com.komputerkit.ocafe.Utilitas.Utilitas;

public class ActivityUtamaAdmin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    boolean posisi ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (posisi){
            posisi = false ;
            FrameLayout wFragment = findViewById(R.id.wFragment) ;
            wFragment.setVisibility(View.GONE);

            View v = this.findViewById(android.R.id.content) ;
            Utilitas.setText(v,R.id.tvTitle,"Beranda") ;
        } else {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                Utilitas utilitas = new Utilitas(this) ;
                utilitas.showDialog("Apakah anda yakin untuk keluar ?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            finishAffinity();
                        } else {
                            finish();
                        }
                    }
                });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_utama, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        posisi = true ;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction() ;
        View v = this.findViewById(android.R.id.content) ;
        FrameLayout wFragment = findViewById(R.id.wFragment) ;
        wFragment.setVisibility(View.VISIBLE);

        if (id == R.id.nav_jenis_barang) {

            LJenisBarang lJenisBarang = new LJenisBarang() ;
            fragmentTransaction.replace(R.id.wFragment,lJenisBarang).commit() ;
            Utilitas.setText(v,R.id.tvTitle,"Laporan Jenis Barang") ;

        } else if (id == R.id.nav_barang) {

            LBarang lJenisBarang = new LBarang() ;
            fragmentTransaction.replace(R.id.wFragment,lJenisBarang).commit() ;
            Utilitas.setText(v,R.id.tvTitle,"Laporan Barang") ;

        } else if (id == R.id.nav_pegawai) {

            LPegawai lJenisBarang = new LPegawai() ;
            fragmentTransaction.replace(R.id.wFragment,lJenisBarang).commit() ;
            Utilitas.setText(v,R.id.tvTitle,"Laporan Pegawai") ;

        } else if (id == R.id.nav_pelanggan) {

            LPelanggan lJenisBarang = new LPelanggan() ;
            fragmentTransaction.replace(R.id.wFragment,lJenisBarang).commit() ;
            Utilitas.setText(v,R.id.tvTitle,"Laporan Pelanggan") ;

        } else if (id == R.id.nav_pembayaran) {

            LPembayaran lJenisBarang = new LPembayaran() ;
            fragmentTransaction.replace(R.id.wFragment,lJenisBarang).commit() ;
            Utilitas.setText(v,R.id.tvTitle,"Laporan Pembayaran") ;

        } else if (id == R.id.nav_transaksi) {

            LPenjualan lJenisBarang = new LPenjualan() ;
            fragmentTransaction.replace(R.id.wFragment,lJenisBarang).commit() ;
            Utilitas.setText(v,R.id.tvTitle,"Laporan Penjualan") ;

        } else if (id == R.id.nav_pengaturan) {

        } else if (id == R.id.nav_keluar) {
            Utilitas utilitas = new Utilitas(this) ;
            utilitas.setSession("isLogin","");
            startActivity(new Intent(this,ActivityLogin.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openDrawer(View v){
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);
    }

    public void lapBayar(View v){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        MenuItem item = navigationView.getMenu().findItem(R.id.nav_pembayaran) ;
        onNavigationItemSelected(item) ;
    }

    public void lapJenis(View v){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        MenuItem item = navigationView.getMenu().findItem(R.id.nav_jenis_barang) ;
        onNavigationItemSelected(item) ;
    }

    public void lapTransaksi(View v){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        MenuItem item = navigationView.getMenu().findItem(R.id.nav_transaksi) ;
        onNavigationItemSelected(item) ;
    }

    public void lapBarang(View v){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        MenuItem item = navigationView.getMenu().findItem(R.id.nav_barang) ;
        onNavigationItemSelected(item) ;
    }

    public void lapPelanggan(View v){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        MenuItem item = navigationView.getMenu().findItem(R.id.nav_pelanggan) ;
        onNavigationItemSelected(item) ;
    }

    public void lapPegawai(View v){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        MenuItem item = navigationView.getMenu().findItem(R.id.nav_pegawai) ;
        onNavigationItemSelected(item) ;
    }
}
