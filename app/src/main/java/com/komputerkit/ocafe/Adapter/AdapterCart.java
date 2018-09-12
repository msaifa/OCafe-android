package com.komputerkit.ocafe.Adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.komputerkit.ocafe.DialogFragment.DCart;
import com.komputerkit.ocafe.Model.QBarang;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Config;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

/**
 * Created by msaifa on 22/02/2018.
 */


public class AdapterCart extends RecyclerView.Adapter<AdapterCart.MyViewHolder>{

    List<QBarang> data;
    DCart dCart ;

    public AdapterCart(DCart dCart, List<QBarang> data){ // Siswa Diganti menjadi Class List
        this.data = data ;
        this.dCart = dCart ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false );
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        String uri = Config.getBaseUrl() + "assets/img/" + data.get(position).getImg() ;
        String jum = Utilitas.intToStr(Utilitas.strToInt(data.get(position).getHarga_jual()) * Utilitas.strToInt(data.get(position).getJumlah())) ;

        Glide.with(holder.itemView).load(uri).into(holder.gambarB) ;
        holder.tvBarang.setText(data.get(position).getBarang());
        holder.tvHarga.setText("Rp "+Utilitas.removeE(jum));
        holder.tvJumlah.setText(data.get(position).getJumlah()) ;

        holder.imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dCart.plus(data.get(position).getId_barang());
            }
        });

        holder.imgMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dCart.min(data.get(position).getId_barang());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout imgPlus,imgMin ;
        ImageView gambarB ;
        TextView tvBarang,tvHarga, tvJumlah ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvBarang = itemView.findViewById(R.id.tvBarang) ;
            tvHarga = itemView.findViewById(R.id.tvHarga) ;
            tvJumlah = itemView.findViewById(R.id.wJumlah) ;
            gambarB = itemView.findViewById(R.id.imgProduk) ;
            imgPlus = itemView.findViewById(R.id.wPlus) ;
            imgMin = itemView.findViewById(R.id.wMin) ;

        }
    }
}
