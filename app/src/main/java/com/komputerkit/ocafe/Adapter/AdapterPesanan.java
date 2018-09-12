package com.komputerkit.ocafe.Adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.komputerkit.ocafe.ActivityUtamaPelayan;
import com.komputerkit.ocafe.Fragment.Pelayan.YBeranda;
import com.komputerkit.ocafe.Model.QBayar;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

/**
 * Created by msaifa on 24/02/2018.
 */

public class AdapterPesanan extends RecyclerView.Adapter<AdapterPesanan.MyViewHolder>{

    List<QBayar> data;
    YBeranda yBeranda ;
    Utilitas utilitas ;

    public AdapterPesanan(YBeranda yBeranda, List<QBayar> data){ // Siswa Diganti menjadi Class List
        this.data = data ;
        this.yBeranda = yBeranda ;

        utilitas = new Utilitas(yBeranda.getActivity()) ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent,false );
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tvTanggal.setText(data.get(position).getTgl_bayar());
        holder.tvFaktur.setText(data.get(position).getPelanggan());
        holder.tvTotalBayar.setText("Rp "+ Utilitas.removeE(data.get(position).getTotal_bayar()));

        if (data.get(position).getStatus().equals("1")){
            holder.tvStatus.setText("Sedang diproses");
        } else if (data.get(position).getStatus().equals("2")){
            holder.tvStatus.setText("Sudah Dikirim");
        } else if (data.get(position).getStatus().equals("0")){
            holder.tvStatus.setText("Tunggu Konfirmasi");
        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yBeranda.pilih(position);
            }
        });

        holder.wOpsi.setVisibility(View.VISIBLE);
        holder.wOpsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilitas.showPopUp(holder.wOpsi,R.menu.item_cancel).setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        yBeranda.cancel(data.get(position).getFaktur());

                        return false;
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvFaktur,tvTotalBayar,tvTanggal,tvStatus;
        CardView card ;
        ConstraintLayout wOpsi ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvFaktur = itemView.findViewById(R.id.tvFaktur) ;
            tvTotalBayar = itemView.findViewById(R.id.tvTotal) ;
            tvTanggal = itemView.findViewById(R.id.tvTanggal) ;
            tvStatus  = itemView.findViewById(R.id.tvStatus) ;
            card  = itemView.findViewById(R.id.card) ;
            wOpsi = itemView.findViewById(R.id.wOpsi) ;

        }
    }
}
