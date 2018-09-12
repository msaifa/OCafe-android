package com.komputerkit.ocafe.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.komputerkit.ocafe.Model.QBayar;
import com.komputerkit.ocafe.Model.TblPelanggan;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

/**
 * Created by msaifa on 15/02/2018.
 */

public class AdapterLapPembayaran extends RecyclerView.Adapter<AdapterLapPembayaran.MyViewHolder>{

    List<QBayar> data;

    public AdapterLapPembayaran(List<QBayar> data){ // Siswa Diganti menjadi Class List
        this.data = data ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pembayaran,parent,false );
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tvPelanggan.setText(data.get(position).getPelanggan());
        holder.tvFaktur.setText(data.get(position).getNama_user());
        holder.tvTotalBayar.setText(data.get(position).getFaktur());
        holder.tvKeterangan.setText(Utilitas.removeE(data.get(position).getTotal_bayar()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvPelanggan,tvFaktur,tvTotalBayar,tvKeterangan;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvPelanggan = itemView.findViewById(R.id.tvPelangganBay) ;
            tvFaktur = itemView.findViewById(R.id.tvFakturBay) ;
            tvTotalBayar = itemView.findViewById(R.id.tvTotalBayarBay) ;
            tvKeterangan= itemView.findViewById(R.id.tvKeteranganBay) ;

        }
    }
}