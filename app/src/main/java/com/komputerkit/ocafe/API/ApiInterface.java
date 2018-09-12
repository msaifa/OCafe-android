package com.komputerkit.ocafe.API;

import com.komputerkit.ocafe.Model.QBarang;
import com.komputerkit.ocafe.Model.QBayar;
import com.komputerkit.ocafe.Model.QOrder;
import com.komputerkit.ocafe.Model.Response;
import com.komputerkit.ocafe.Model.TblJenisBarang;
import com.komputerkit.ocafe.Model.TblPegawai;
import com.komputerkit.ocafe.Model.TblPelanggan;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by SMENDA on 06/09/2017.
 */

public interface ApiInterface {

     @FormUrlEncoded
     @POST("CAdmin/getJenis")
     Call<List<TblJenisBarang>> getJenisBarang(@Field("token") String token,@Field("cari") String pencarian) ;

     @FormUrlEncoded
     @POST("CAdmin/getBarang")
     Call<List<QBarang>> getBarang(@Field("token") String token, @Field("cari") String pencarian) ;

     @FormUrlEncoded
     @POST("CAdmin/getPengguna")
     Call<List<TblPegawai>> getPegawai(@Field("token") String token, @Field("cari") String pencarian) ;

     @FormUrlEncoded
     @POST("CAdmin/getPelanggan")
     Call<List<TblPelanggan>> getPelanggan(@Field("token") String token, @Field("cari") String pencarian) ;

     @FormUrlEncoded
     @POST("CAdmin/getPembayaran")
     Call<List<QBayar>> getPembayaran(@Field("token") String token, @Field("cari") String pencarian,@Field("dari") String dari,@Field("sampai") String sampai) ;

     @FormUrlEncoded
     @POST("CAdmin/getPenjualan")
     Call<List<QOrder>> getPenjualan(@Field("token") String token, @Field("cari") String pencarian, @Field("dari") String dari, @Field("sampai") String sampai) ;

     @FormUrlEncoded
     @POST("CAdmin/login")
     Call<List<TblPegawai>> loginAdmin(@Field("token") String token, @Field("email") String email, @Field("password") String password) ;

     @FormUrlEncoded
     @POST("CPelanggan/login")
     Call<List<TblPelanggan>> loginPelanggan(@Field("token") String token, @Field("email") String email, @Field("password") String password) ;

     @FormUrlEncoded
     @POST("CPelanggan/getAkun")
     Call<List<TblPelanggan>> getAkun(@Field("token") String token, @Field("id_pelanggan") String idpelanggan) ;

     @FormUrlEncoded
     @POST("CPelanggan/ubahProfil")
     Call<List<Response>> ubahProfile(@Field("token") String token,
                                      @Field("id_pelanggan") String idpelanggan,
                                      @Field("pelanggan") String pelanggan,
                                      @Field("alamat") String alamat,
                                      @Field("notelp") String notelp,
                                      @Field("email") String email
                                          ) ;

     @FormUrlEncoded
     @POST("CPelanggan/ubahPassword")
     Call<List<Response>> ubahPassword(@Field("token") String token,@Field("passbaru") String passbaru,@Field("passlama") String passlama,@Field("id_pelanggan") String idpelanggan) ;

     @FormUrlEncoded
     @POST("CPelanggan/bayar")
     Call<List<Response>> bayar(
                         @Field("token") String token,
                         @Field("total_bayar") String totalBayar,
                         @Field("id_pelanggan") String idpelanggan,
                         @Field("bayar") String bayar,
                         @Field("kembali") String kembali,
                         @Field("tgl_bayar") String tgl_bayar
                         ) ;

     @FormUrlEncoded
     @POST("CPelanggan/order")
     Call<List<Response>> order(
             @Field("token") String token,
             @Field("idbarang") String idbarang,
             @Field("tglorder") String tglorder,
             @Field("jamorder") String jamorder,
             @Field("jumlah") String jumlah,
             @Field("hargaorder") String hargaorder,
             @Field("faktur") String faktur
     ) ;

     @FormUrlEncoded
     @POST("CPelanggan/getHistory")
     Call<List<QBayar>> getHistory(@Field("token") String token,@Field("id_pelanggan") String idPelanggan) ;

     @FormUrlEncoded
     @POST("CPelanggan/getDetailHistory")
     Call<List<QOrder>> getDetailHistory(@Field("token") String token,@Field("faktur") String faktur) ;

     @FormUrlEncoded
     @POST("CPelanggan/getDetailBarang")
     Call<List<QBarang>> getDetailBarang(@Field("token") String token,@Field("idjenis") String idjenis) ;



     // PELAYANAN
     @FormUrlEncoded
     @POST("CPelayan/cancel")
     Call<List<Response>> cancelPesanan(@Field("token") String token,@Field("faktur") String id_order) ;

     @FormUrlEncoded
     @POST("CPelayan/konfirm")
     Call<List<Response>> konfirmPesanan(@Field("token") String token,@Field("faktur") String faktur) ;

     @FormUrlEncoded
     @POST("CPelayan/getPesanan")
     Call<List<QBayar>> getPesanan(@Field("token") String token,@Field("cari") String cari) ;

     @FormUrlEncoded
     @POST("CPelayan/getDetail")
     Call<List<QOrder>> getDetailPesanan(@Field("token") String token,@Field("faktur") String faktur) ;
}

