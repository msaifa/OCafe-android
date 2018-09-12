package com.komputerkit.ocafe.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.komputerkit.ocafe.Model.QOrder;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

import okhttp3.internal.Util;

/**
 * Created by msaifa on 16/02/2018.
 */

public class AdapterLapPenjualan extends RecyclerView.Adapter<AdapterLapPenjualan.MyViewHolder>{

    List<QOrder> data;

    public AdapterLapPenjualan(List<QOrder> data){ // Siswa Diganti menjadi Class List
        this.data = data ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lap_transaksi,parent,false );
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        double jumlah = Utilitas.strToDouble(data.get(position).getJumlah()) * Utilitas.strToDouble(data.get(position).getHarga_order()) ;

        holder.tvBarang.setText(data.get(position).getBarang());
        holder.tvFaktur.setText(data.get(position).getFaktur());
        holder.tvJumlah.setText(data.get(position).getJumlah() + " x " + data.get(position).getHarga_order());
        holder.tvKeterangan.setText(Utilitas.removeE(jumlah));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvBarang,tvFaktur,tvJumlah,tvKeterangan;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvBarang = itemView.findViewById(R.id.tvBarangTra) ;
            tvFaktur = itemView.findViewById(R.id.tvFakturTra) ;
            tvJumlah = itemView.findViewById(R.id.tvJumlahBarangTra) ;
            tvKeterangan= itemView.findViewById(R.id.tvKeteranganTra) ;

        }
    }
}