package com.komputerkit.ocafe.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.komputerkit.ocafe.Fragment.Pelanggan.PKategori;
import com.komputerkit.ocafe.Model.TblJenisBarang;
import com.komputerkit.ocafe.R;

import java.util.List;

/**
 * Created by msaifa on 22/02/2018.
 */

public class AdapterKategori extends RecyclerView.Adapter<AdapterKategori.MyViewHolder>{

    List<TblJenisBarang> data;
    PKategori pKategori ;

    public AdapterKategori(PKategori pKategori, List<TblJenisBarang> data){ // Siswa Diganti menjadi Class List
        this.data = data ;
        this.pKategori = pKategori ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kategori,parent,false );
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tvBarang.setText(data.get(position).getJenis());
        holder.tvKeteranganB.setText(data.get(position).getKetJenis());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pKategori.pilih(data.get(position)) ;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvBarang,tvKeteranganB ;
        CardView card ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvBarang = itemView.findViewById(R.id.tvKat) ;
            tvKeteranganB = itemView.findViewById(R.id.tvKet) ;

            card = itemView.findViewById(R.id.card1) ;
        }
    }
}