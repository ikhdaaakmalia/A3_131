package com.ikhdaamel.project_akhir.service

import com.ikhdaamel.project_akhir.model.Produk
import com.ikhdaamel.project_akhir.model.ProdukDetailResponse
import com.ikhdaamel.project_akhir.model.ProdukResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProdukService {
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
    )
    @GET(".")                                                                                       //ganti jadi titik krn disesuaikan utk endpoint di postman
    suspend fun getProduk(): ProdukResponse                                                         //diganti ngikutin yg terbaru

    @GET("{idProduk}")                                                                              //endpoint
    suspend fun getProdukById(@Path("idProduk")idProduk:String): ProdukDetailResponse               //jd path karena merujuk ke alamat url

    @POST("store")
    suspend fun insertProduk(@Body produk: Produk)

    @PUT("{idProduk}")
    suspend fun updateProduk(@Path("idProduk")idProduk: String, @Body produk: Produk)

    @DELETE("{idProduk}")
    suspend fun deleteProduk(@Path("idProduk")idProduk: String): Response<Void>
}