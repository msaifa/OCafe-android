package com.komputerkit.ocafe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by msaifa on 16/02/2018.
 */

public class QBarang {

    @SerializedName("id_barang")
    String id_barang ;

    @SerializedName("id_jenis")
    String id_jenis ;

    @SerializedName("jenis")
    String jenis ;

    @SerializedName("ket_jenis")
    String ket_jenis ;

    @SerializedName("barang")
    String barang ;

    @SerializedName("harga_beli")
    String harga_beli ;

    @SerializedName("harga_jual")
    String harga_jual ;

    @SerializedName("ket")
    String ket ;

    @SerializedName("stok")
    String stok ;

    @SerializedName("img")
    String img ;

    @SerializedName("deskripsi")
    String deskripsi ;

    String jumlah = "0";
    String defPosition = "" ;

    public String getId_barang() {
        return id_barang;
    }

    public String getId_jenis() {
        return id_jenis;
    }

    public String getJenis() {
        return jenis;
    }

    public String getKet_jenis() {
        return ket_jenis;
    }

    public String getBarang() {
        return barang;
    }

    public String getHarga_beli() {
        return harga_beli;
    }

    public String getHarga_jual() {
        return harga_jual;
    }

    public String getKet() {
        return ket;
    }

    public String getStok() {
        return stok;
    }

    public String getImg() {
        return img;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getDefPosition() {
        return defPosition;
    }

    public void setDefPosition(String defPosition) {
        this.defPosition = defPosition;
    }
}
