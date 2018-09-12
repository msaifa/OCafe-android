package com.komputerkit.ocafe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by msaifa on 15/02/2018.
 */

public class QOrder {

    @SerializedName("id_user")
    String id_user;

    @SerializedName("nama_user")
    String nama_user;


    @SerializedName("total_bayar")
    String total_bayar;

    @SerializedName("bayar")
    String bayar;

    @SerializedName("kembali")
    String kembali;

    @SerializedName("tgl_bayar")
    String tgl_bayar;

    @SerializedName("flag_bayar")
    String flag_bayar;

    @SerializedName("password_user")
    String password_user;

    @SerializedName("status")
    String status;

    @SerializedName("level")
    String level;

    @SerializedName("id_order")
    String id_order;

    @SerializedName("jam_order")
    String jam_order;

    @SerializedName("jumlah")
    String jumlah;

    @SerializedName("faktur")
    String faktur;

    @SerializedName("harga_order")
    String harga_order;

    @SerializedName("status_order")
    String status_order;

    @SerializedName("id_barang")
    String id_barang;

    @SerializedName("id_jenis")
    String id_jenis;

    @SerializedName("jenis")
    String jenis;

    @SerializedName("ket_jenis")
    String ket_jenis;

    @SerializedName("barang")
    String barang;

    @SerializedName("harga_beli")
    String harga_beli;

    @SerializedName("harga_jual")
    String harga_jual;

    @SerializedName("ket")
    String ket;

    @SerializedName("stok")
    String stok;

    @SerializedName("img")
    String img;

    @SerializedName("id_pelanggan")
    String id_pelanggan;

    @SerializedName("pelanggan")
    String pelanggan;

    @SerializedName("alamat")
    String alamat;

    @SerializedName("notelp")
    String notelp;

    @SerializedName("password")
    String password;

    @SerializedName("email")
    String email;

    @SerializedName("saldo")
    String saldo;

    public String getId_user() {
        return id_user;
    }

    public String getNama_user() {
        return nama_user;
    }

    public String getTotal_bayar() {
        return total_bayar;
    }

    public String getBayar() {
        return bayar;
    }

    public String getKembali() {
        return kembali;
    }

    public String getTgl_bayar() {
        return tgl_bayar;
    }

    public String getFlag_bayar() {
        return flag_bayar;
    }

    public String getPassword_user() {
        return password_user;
    }

    public String getStatus() {
        return status;
    }

    public String getLevel() {
        return level;
    }

    public String getId_order() {
        return id_order;
    }

    public String getJam_order() {
        return jam_order;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getFaktur() {
        return faktur;
    }

    public String getHarga_order() {
        return harga_order;
    }

    public String getStatus_order() {
        return status_order;
    }

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

    public String getId_pelanggan() {
        return id_pelanggan;
    }

    public String getPelanggan() {
        return pelanggan;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNotelp() {
        return notelp;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getSaldo() {
        return saldo;
    }
}
