package com.komputerkit.ocafe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by msaifa on 15/02/2018.
 */

public class QBayar {

    @SerializedName("id_pelanggan")
    String id_pelanggan;

    @SerializedName("pelanggan")
    String pelanggan;

    @SerializedName("alamat")
    String alamat;

    @SerializedName("notelp")
    String notelp;

    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    @SerializedName("status_p")
    String status_p;

    @SerializedName("saldo")
    String saldo;

    @SerializedName("id_user")
    String id_user;

    @SerializedName("nama_user")
    String nama_user;

    @SerializedName("email_user")
    String email_user;

    @SerializedName("password_user")
    String password_user;

    @SerializedName("status")
    String status;

    @SerializedName("level")
    String level;

    @SerializedName("faktur")
    String faktur;

    @SerializedName("total_bayar")
    String total_bayar;

    @SerializedName("kembali")
    String kembali;

    @SerializedName("bayar")
    String bayar;

    @SerializedName("tgl_bayar")
    String tgl_bayar;

    @SerializedName("flag_bayar")
    String flag_bayar;

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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus_p() {
        return status_p;
    }

    public String getSaldo() {
        return saldo;
    }

    public String getId_user() {
        return id_user;
    }

    public String getNama_user() {
        return nama_user;
    }

    public String getEmail_user() {
        return email_user;
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

    public String getFaktur() {
        return faktur;
    }

    public String getTotal_bayar() {
        return total_bayar;
    }

    public String getKembali() {
        return kembali;
    }

    public String getBayar() {
        return bayar;
    }

    public String getTgl_bayar() {
        return tgl_bayar;
    }

    public String getFlag_bayar() {
        return flag_bayar;
    }
}
