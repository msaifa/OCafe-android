package com.komputerkit.ocafe.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.komputerkit.ocafe.Model.QBarang;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Config;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

/**
 * Created by msaifa on 22/02/2018.
 */

public class AdapterKonfirmasi extends RecyclerView.Adapter<AdapterKonfirmasi.MyViewHolder>{

    List<QBarang> data;

    public AdapterKonfirmasi(List<QBarang> data){ // Siswa Diganti menjadi Class List
        this.data = data ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_konfirmasi,parent,false );
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        String jum = Utilitas.intToStr(Utilitas.strToInt(data.get(position).getHarga_jual()) * Utilitas.strToInt(data.get(position).getJumlah())) ;

        holder.tvBarang.setText(data.get(position).getBarang());
        holder.tvHarga.setText(Utilitas.removeE(jum)) ;
        holder.tvKet.setText(data.get(position).getJumlah() +" x "+ Utilitas.removeE(data.get(position).getHarga_jual()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvBarang, tvHarga, tvKet;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvBarang = itemView.findViewById(R.id.tvProduk) ;
            tvHarga = itemView.findViewById(R.id.tvHarga) ;
            tvKet = itemView.findViewById(R.id.tvKet) ;
        }
    }
}