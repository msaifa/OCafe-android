package com.komputerkit.ocafe.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.komputerkit.ocafe.Model.TblPelanggan;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

/**
 * Created by msaifa on 15/02/2018.
 */

public class AdapterLapPelanggan extends RecyclerView.Adapter<AdapterLapPelanggan.MyViewHolder>{

    List<TblPelanggan> data;

    public AdapterLapPelanggan(List<TblPelanggan> data){ // Siswa Diganti menjadi Class List
        this.data = data ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelanggan,parent,false );
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tvPelanggan.setText(data.get(position).getPelanggan());
        holder.tvEmail.setText(data.get(position).getEmail());
        holder.tvAlamat.setText(data.get(position).getAlamat());
        holder.tvTelp.setText(data.get(position).getNotelp());
        holder.tvSaldo.setText("Rp. " + Utilitas.removeE(data.get(position).getSaldo()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvPelanggan,tvEmail,tvAlamat,tvTelp,tvSaldo ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvPelanggan = itemView.findViewById(R.id.tvPelangganPel) ;
            tvEmail = itemView.findViewById(R.id.tvEmailPel) ;
            tvAlamat = itemView.findViewById(R.id.tvAlamatPel) ;
            tvTelp = itemView.findViewById(R.id.tvTelpPel) ;
            tvSaldo = itemView.findViewById(R.id.tvSaldoPel) ;

        }
    }
}

