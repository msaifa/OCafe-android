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

import com.komputerkit.ocafe.Fragment.Pelanggan.PPesanan;
import com.komputerkit.ocafe.Model.QBayar;
import com.komputerkit.ocafe.R;
import com.komputerkit.ocafe.Utilitas.Utilitas;

import java.util.List;

/**
 * Created by msaifa on 22/02/2018.
 */


public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.MyViewHolder>{

    List<QBayar> data;
    PPesanan pPesanan ;
    Utilitas utilitas ;

    public AdapterHistory(PPesanan pPesanan,List<QBayar> data){ // Siswa Diganti menjadi Class List
        this.data = data ;
        this.pPesanan = pPesanan ;

        utilitas = new Utilitas(pPesanan.getActivity()) ;
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
        holder.tvFaktur.setText("#"+data.get(position).getFaktur());
        holder.tvTotalBayar.setText("Rp "+Utilitas.removeE(data.get(position).getTotal_bayar()));

        if (data.get(position).getStatus().equals("1")){
            holder.tvStatus.setText("Sedang diproses");
            holder.wOpsi.setVisibility(View.GONE) ;
        } else if (data.get(position).getStatus().equals("2")){
            holder.tvStatus.setText("Sudah Dikirim");
            holder.wOpsi.setVisibility(View.GONE) ;
        } else if (data.get(position).getStatus().equals("0")){
            holder.tvStatus.setText("Tunggu Konfirmasi");
            holder.wOpsi.setVisibility(View.VISIBLE) ;
        } else if (data.get(position).getStatus().equals("4")){
            holder.tvStatus.setText("Sudah Diambil");
            holder.wOpsi.setVisibility(View.GONE) ;
        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pPesanan.pilih(data.get(position).getFaktur());
            }
        });

        holder.wOpsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilitas.showPopUp(holder.wOpsi,R.menu.item_cancel).setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        pPesanan.cancel(data.get(position).getFaktur());

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
            wOpsi = itemView.findViewById(R.id.wOpsi ) ;

        }
    }
}