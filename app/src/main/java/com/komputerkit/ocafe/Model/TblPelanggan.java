package com.komputerkit.ocafe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by msaifa on 15/02/2018.
 */

public class TblPelanggan {

    @SerializedName("id_pelanggan")
    String id_pelanggan ;

    @SerializedName("pelanggan")
    String pelanggan ;

    @SerializedName("alamat")
    String alamat ;

    @SerializedName("notelp")
    String notelp ;

    @SerializedName("email")
    String email ;

    @SerializedName("password")
    String password ;

    @SerializedName("status_p")
    String status_p ;

    @SerializedName("saldo")
    String saldo ;

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

    public void setId_pelanggan(String id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public void setPelanggan(String pelanggan) {
        this.pelanggan = pelanggan;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus_p(String status_p) {
        this.status_p = status_p;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
}
