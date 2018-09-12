package com.komputerkit.ocafe.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.komputerkit.ocafe.DialogFragment.DDetailHistory;
import com.komputerkit.ocafe.Model.QOrder;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Config;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

/**
 * Created by KomputerKit on 23/02/2018.
 */

public class AdapterDetailHistory extends RecyclerView.Adapter<AdapterDetailHistory.MyViewHolder> {

    List<QOrder> data;
    DDetailHistory dDetailHistory ;

    public AdapterDetailHistory(DDetailHistory DDetailHistory,List<QOrder> data) { // Siswa Diganti menjadi Class List
        this.data = data;
        this.dDetailHistory = DDetailHistory ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        String uri = Config.getBaseUrl() + "assets/img/" + data.get(position).getImg() ;
        String jum = Utilitas.intToStr(Utilitas.strToInt(data.get(position).getJumlah()) * Utilitas.strToInt(data.get(position).getHarga_jual())) ;

        Glide.with(dDetailHistory.activity).load(uri).into(holder.gambarB) ;
        holder.tvBarang.setText(data.get(position).getBarang()) ;
        holder.tvHarga.setText("Rp "+ Utilitas.removeE(jum)) ;
        holder.tvKategori.setText(data.get(position).getJenis()) ;
        holder.tvDesk.setText("Jumlah : "+data.get(position).getJumlah() + "\n" +
                              "Faktur : " + data.get(position).getFaktur()) ;
        
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView gambarB;
        TextView tvBarang, tvKategori, tvHarga, tvDesk;
        CardView cvProduk;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvBarang = itemView.findViewById(R.id.tvBarang);
            tvHarga = itemView.findViewById(R.id.tvHarga);
            tvKategori = itemView.findViewById(R.id.tvKat);
            tvDesk = itemView.findViewById(R.id.tvDesk);
            gambarB = itemView.findViewById(R.id.imgProduk);
            cvProduk = itemView.findViewById(R.id.card1);

        }
    }
}
