package com.komputerkit.ocafe.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.komputerkit.ocafe.ActivityUtamaPelanggan;
import com.komputerkit.ocafe.Model.QBarang;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Config;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

/**
 * Created by msaifa on 22/02/2018.
 */

public class AdapterProdukFrontPage extends RecyclerView.Adapter<AdapterProdukFrontPage.MyViewHolder>{

    List<QBarang> data;
    ActivityUtamaPelanggan activityUtamaPelanggan ;

    public AdapterProdukFrontPage(ActivityUtamaPelanggan activity, List<QBarang> data){ // Siswa Diganti menjadi Class List
        this.data = data ;
        activityUtamaPelanggan = activity ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk_fronpage,parent,false );
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String uri = Config.getBaseUrl() + "assets/img/" + data.get(position).getImg() ;

        Glide.with(holder.itemView).load(uri).into(holder.gambarB) ;
        holder.tvBarang.setText(data.get(position).getBarang());
        holder.tvHarga.setText("Rp "+Utilitas.removeE(data.get(position).getHarga_jual()));
        holder.tvKategori.setText(data.get(position).getJenis());


        holder.cvProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityUtamaPelanggan.pilih(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView gambarB,imgCart ;
        TextView tvBarang,tvKategori,tvHarga;
        CardView cvProduk ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvBarang = itemView.findViewById(R.id.tvProduk) ;
            tvHarga = itemView.findViewById(R.id.tvHarga) ;
            tvKategori = itemView.findViewById(R.id.tvKategori) ;
            gambarB = itemView.findViewById(R.id.imgGambar) ;
            imgCart = itemView.findViewById(R.id.imgCart) ;
            cvProduk = itemView.findViewById(R.id.cvProduk) ;

        }
    }
}