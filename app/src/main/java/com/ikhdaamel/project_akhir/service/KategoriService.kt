package com.ikhdaamel.project_akhir.service

import com.ikhdaamel.project_akhir.model.Kategori
import com.ikhdaamel.project_akhir.model.KategoriDetailResponse
import com.ikhdaamel.project_akhir.model.KategoriResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface KategoriService {
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
    )
    @GET(".")                                                                                       //ganti jadi titik krn disesuaikan utk endpoint di postman
    suspend fun getKategori(): KategoriResponse                                                         //diganti ngikutin yg terbaru

    @GET("{idKategori}")                                                                              //endpoint
    suspend fun getKategoriById(@Path("idKategori")idKategori:String): KategoriDetailResponse               //jd path karena merujuk ke alamat url

    @POST("store")
    suspend fun insertKategori(@Body kategori: Kategori)

    @PUT("{idKategori}")
    suspend fun updateKategori(@Path("idKategori")idKategori: String, @Body kategori: Kategori)

    @DELETE("{idKategori}")
    suspend fun deleteKategori(@Path("idKategori")idKategori: String): Response<Void>
}