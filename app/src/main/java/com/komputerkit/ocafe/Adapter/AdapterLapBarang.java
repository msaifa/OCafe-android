package com.komputerkit.ocafe.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.komputerkit.ocafe.Fragment.Laporan.LBarang;
import com.komputerkit.ocafe.Model.QBarang;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Config;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

/**
 * Created by msaifa on 16/02/2018.
 */

public class AdapterLapBarang extends RecyclerView.Adapter<AdapterLapBarang.MyViewHolder>{

    List<QBarang> data;

    public AdapterLapBarang(List<QBarang> data){ // Siswa Diganti menjadi Class List
        this.data = data ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lap_barang,parent,false );
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String uri = Config.getBaseUrl() + "assets/img/" + data.get(position).getImg() ;

        Glide.with(holder.itemView).load(uri).into(holder.gambarB) ;
        holder.tvBarang.setText(data.get(position).getBarang());
        holder.tvStok.setText(data.get(position).getStok());
        holder.tvHarga.setText(Utilitas.removeE(data.get(position).getHarga_jual()));
        holder.tvKeteranganB.setText(data.get(position).getKet());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView gambarB ;
        TextView tvBarang,tvKeteranganB,tvStok,tvHarga ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvBarang = itemView.findViewById(R.id.tvBarangB) ;
            tvStok = itemView.findViewById(R.id.tvStokB) ;
            tvHarga = itemView.findViewById(R.id.tvHargaB) ;
            tvKeteranganB = itemView.findViewById(R.id.tvKeteranganB) ;
            gambarB = itemView.findViewById(R.id.GambarB) ;

        }
    }
}
