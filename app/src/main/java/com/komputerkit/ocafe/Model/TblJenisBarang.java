package com.komputerkit.ocafe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by msaifa on 14/02/2018.
 */

public class TblJenisBarang {

    @SerializedName("id_jenis")
    String idJenis;

    @SerializedName("jenis")
    String jenis;

    @SerializedName("ket_jenis")
    String ketJenis;

    public String getIdJenis() {
        return idJenis;
    }

    public String getJenis() {
        return jenis;
    }

    public String getKetJenis() {
        return ketJenis;
    }
}
