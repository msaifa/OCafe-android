package com.komputerkit.ocafe.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.komputerkit.ocafe.Fragment.Laporan.LJenisBarang;
import com.komputerkit.ocafe.Model.TblJenisBarang;
import com.komputerkit.ocafe.R;

import java.util.List;

/**
 * Created by msaifa on 14/02/2018.
 */

public class AdapterLapJenis extends RecyclerView.Adapter<AdapterLapJenis.MyViewHolder>{

    List<TblJenisBarang> tblJenisBarang;
    LJenisBarang lJenisBarang ;

    public AdapterLapJenis(LJenisBarang lJenisBarang, List<TblJenisBarang> qJanji){ // Siswa Diganti menjadi Class List
        this.tblJenisBarang = qJanji;
        this.lJenisBarang = lJenisBarang ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jenis_barang,parent,false );
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tvJenisB.setText(tblJenisBarang.get(position).getJenis());
        holder.tvKeteranganB.setText(tblJenisBarang.get(position).getKetJenis());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lJenisBarang.pilih(tblJenisBarang.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return tblJenisBarang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvJenisB,tvKeteranganB ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvJenisB = itemView.findViewById(R.id.tvJenisJB) ;
            tvKeteranganB = itemView.findViewById(R.id.tvKeteranganJB) ;
        }
    }
}
