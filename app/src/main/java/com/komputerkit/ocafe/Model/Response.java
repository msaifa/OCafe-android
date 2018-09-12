package com.komputerkit.ocafe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by msaifa on 22/02/2018.
 */

public class Response {
    @SerializedName("response")
    String response ;

    @SerializedName("faktur")
    String faktur ;

    public Response(String response, String faktur) {
        this.response = response;
        this.faktur = faktur;
    }

    public String getResponse() {
        return response;
    }

    public String getFaktur() {
        return faktur;
    }
}
