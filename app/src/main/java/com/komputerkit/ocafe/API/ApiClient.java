package com.komputerkit.ocafe.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.komputerkit.ocafe.Utilitas.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SMENDA on 06/09/2017.
 */

public class ApiClient {
    public static String BASE_URL = Config.getBaseUrl(); // BASE URL
    public static Retrofit retrofit = null ;

    public static Retrofit retrofitGet(){
        if (retrofit == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create() ;

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build() ;
        }

        return retrofit ;
    }



    public static void setBaseUrl(String base){
        BASE_URL = base;
    }
}
