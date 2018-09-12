package com.komputerkit.ocafe.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.komputerkit.ocafe.Model.TblPegawai;
import com.komputerkit.ocafe.R;

import java.util.List;

/**
 * Created by msaifa on 15/02/2018.
 */

public class AdapterLapPegawai extends RecyclerView.Adapter<AdapterLapPegawai.MyViewHolder>{

    List<TblPegawai> data;

    public AdapterLapPegawai(List<TblPegawai> data){ // Siswa Diganti menjadi Class List
        this.data = data ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pegawai,parent,false );
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tvJenisB.setText(data.get(position).getNamaUser());
        holder.tvKeteranganB.setText(data.get(position).getEmailUser());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvJenisB,tvKeteranganB ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvJenisB = itemView.findViewById(R.id.tvPegawai) ;
            tvKeteranganB = itemView.findViewById(R.id.etEmail) ;
        }
    }
}

