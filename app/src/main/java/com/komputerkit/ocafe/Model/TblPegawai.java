package com.komputerkit.ocafe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by msaifa on 15/02/2018.
 */

public class TblPegawai {

    @SerializedName("id_user")
    String idUser ;

    @SerializedName("nama_user")
    String namaUser ;

    @SerializedName("email_user")
    String emailUser ;

    @SerializedName("password_user")
    String passwordUser ;

    @SerializedName("status")
    String status ;

    @SerializedName("level")
    String level ;

    public String getIdUser() {
        return idUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public String getStatus() {
        return status;
    }

    public String getLevel() {
        return level;
    }
}
