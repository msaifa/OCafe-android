package com.komputerkit.ocafe;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.komputerkit.ocafe.API.ApiClient;
import com.komputerkit.ocafe.API.ApiInterface;
import com.komputerkit.ocafe.Adapter.AdapterProdukFrontPage;
import com.komputerkit.ocafe.DialogFragment.DCart;
import com.komputerkit.ocafe.DialogFragment.DDetailBarang;
import com.komputerkit.ocafe.DialogFragment.DEditProfile;
import com.komputerkit.ocafe.Fragment.Pelanggan.PAbout;
import com.komputerkit.ocafe.Fragment.Pelanggan.PAkun;
import com.komputerkit.ocafe.Fragment.Pelanggan.PKategori;
import com.komputerkit.ocafe.Fragment.Pelanggan.PPesanan;
import com.komputerkit.ocafe.Model.QBarang;
import com.komputerkit.ocafe.Model.TblPelanggan;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityUtamaPelanggan extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Typeface typeface1,typeface2;
    ImageButton btn1;
    ImageView cart2,arrow;
    TextView titleBar,titleBar2;
    Utilitas utilitas ;
    boolean posisi ;
    MenuItem menu ;
    ProgressBar proses;
    public TblPelanggan tblPelanggan ;
    ApiInterface apiInterface ;
    ProgressDialog progressDialog ;
    public List<QBarang> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama_pelanggan);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        proses = findViewById(R.id.proses);


        typeface1 = Typeface.createFromAsset(getAssets(), "fonts/comforta.ttf");
        typeface2 = Typeface.createFromAsset(getAssets(), "fonts/quesha.ttf");



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Collpase Title Dissappear

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.CollpasingBar);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                btn1 = findViewById(R.id.imageButton);
                cart2 = findViewById(R.id.cart2);
                arrow = findViewById(R.id.arrow);
                titleBar = findViewById(R.id.titleBar);
                titleBar2 = findViewById(R.id.titleBar2);


                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    arrow.setVisibility(View.GONE);

                    cart2.setVisibility(View.VISIBLE);
                    btn1.setVisibility(View.VISIBLE);
                    titleBar.setVisibility(View.VISIBLE);
                    isShow = true;
                } else if(isShow) {
                    arrow.setVisibility(View.VISIBLE);

                    cart2.setVisibility(View.GONE);
                    btn1.setVisibility(View.GONE);
                    titleBar.setVisibility(View.GONE);

                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

        utilitas = new Utilitas(this) ;
        apiInterface = ApiClient.retrofitGet().create(ApiInterface.class) ;
        progressDialog = new ProgressDialog(this) ;
        progressDialog.setMessage("Loading...");

        loadAkun() ;
        loadData() ;
    }

    public void setNav(){
        NavigationView nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);
        View header=nv.getHeaderView(0);
/*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        utilitas.setText(header,R.id.tvStatus,"Rp "+utilitas.removeE(tblPelanggan.getSaldo())) ;
        utilitas.setText(header,R.id.tvNama,tblPelanggan.getPelanggan()) ;
        utilitas.setText(header,R.id.tvEmail,tblPelanggan.getEmail()) ;

    }

    public void loadData(){
        Call<List<QBarang>> call = apiInterface.getBarang(utilitas.getToken("cobasek"),"") ;
        call.enqueue(new Callback<List<QBarang>>() {
            @Override
            public void onResponse(Call<List<QBarang>> call, Response<List<QBarang>> response) {
                try{

                    if (response.body().size() == 0){
                        utilitas.toast("Tidak ada data");
                        proses.setVisibility(View.GONE);
                    } else {
                        list = response.body() ;
                        RecyclerView recyclerView = findViewById(R.id.recProduk) ;
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new GridLayoutManager(ActivityUtamaPelanggan.this,2));

                        AdapterProdukFrontPage adapter = new AdapterProdukFrontPage(ActivityUtamaPelanggan.this,list) ;
                        recyclerView.setAdapter(adapter);
                        proses.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                    }

                }catch (Exception e){
                    proses.setVisibility(View.GONE);
                    utilitas.toast("Failed Get Data");
                }
            }

            @Override
            public void onFailure(Call<List<QBarang>> call, Throwable t) {
                proses.setVisibility(View.GONE);
                utilitas.toast("Failed Get Data" + t.toString());
            }
        });
    }

    public void loadAkun(){
        String id = utilitas.getSession("id_pelanggan","") ;
        

        Call<List<TblPelanggan>> call = apiInterface.getAkun(utilitas.getToken(id),id) ;
        call.enqueue(new Callback<List<TblPelanggan>>() {
            @Override
            public void onResponse(Call<List<TblPelanggan>> call, Response<List<TblPelanggan>> response) {
                try{
                    if (response.body().size() == 1){
                        tblPelanggan = response.body().get(0) ;
                        setNav();
                    } else {
                        utilitas.toast("Failed Get Data");
                    }
                }catch (Exception e){
                    utilitas.toast("Failed Get Data");
                }
            }

            @Override
            public void onFailure(Call<List<TblPelanggan>> call, Throwable t) {
                utilitas.toast("Failed Get Data" + t.toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START) ;
        } else {
            if (posisi){
                contentBeranda(true);
                FrameLayout frameLayout = findViewById(R.id.wFragment) ;
                frameLayout.setVisibility(View.GONE);
                posisi = false ;
            } else {
                keluar() ;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        posisi = true ;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction() ;
        FrameLayout frameLayout = findViewById(R.id.wFragment) ;
        frameLayout.setVisibility(View.VISIBLE);

        if (id == R.id.beranda) {

            frameLayout.setVisibility(View.GONE);
            posisi = false ;
            contentBeranda(true);

        } else if (id == R.id.kategori) {

            contentBeranda(false) ;
            PKategori pKategori = new PKategori() ;
            fragmentTransaction.replace(R.id.wFragment,pKategori,"Mencoba").commit() ;

        } else if (id == R.id.pesanan) {

            contentBeranda(false) ;
            PPesanan pKategori = new PPesanan(this) ;
            fragmentTransaction.replace(R.id.wFragment,pKategori,"Mencoba").commit() ;

        } else if (id == R.id.akun) {

            contentBeranda(false) ;
            PAkun pKategori = new PAkun() ;
            fragmentTransaction.replace(R.id.wFragment,pKategori,"Mencoba").commit() ;

        } else if (id == R.id.tentang) {

            contentBeranda(false) ;
            PAbout pKategori = new PAbout() ;
            fragmentTransaction.replace(R.id.wFragment,pKategori,"Mencoba").commit() ;

        }else if (id == R.id.keluar) {
            utilitas.setSession("isLogin","");
            startActivity(new Intent(this,ActivityLogin.class));

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    public void openDrawer(View v){
        open();
    }

    public void open(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);
    }

    public void contentBeranda(boolean status){
        loadAkun();
        AppBarLayout appBarLayout = findViewById(R.id.appbar) ;
        NestedScrollView nestedScrollView = findViewById(R.id.nested) ;
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (status){
            appBarLayout.setVisibility(View.VISIBLE);
            navigationView.getMenu().getItem(0).setChecked(true) ;
        } else {
            appBarLayout.setVisibility(View.GONE);
        }
    }

    public void pilih(Integer position){
        FragmentManager fm = getFragmentManager() ;
        DDetailBarang dDetailBarang = new DDetailBarang(this,list,position) ;
        dDetailBarang.show(fm,"detail");
    }

    public void cart(View v){
        DCart dCart = new DCart(this) ;
        FragmentManager fm = getFragmentManager() ;
        dCart.show(fm,"KNTL") ;
    }
public void kategori(View v){
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    MenuItem item = navigationView.getMenu().findItem(R.id.kategori) ;
    onNavigationItemSelected(item) ;
}
}

