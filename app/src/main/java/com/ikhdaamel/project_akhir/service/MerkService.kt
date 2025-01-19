package com.ikhdaamel.project_akhir.service

import com.ikhdaamel.project_akhir.model.Merk
import com.ikhdaamel.project_akhir.model.MerkDetailResponse
import com.ikhdaamel.project_akhir.model.MerkResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MerkService {
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
    )
    @GET(".")                                                                                       //ganti jadi titik krn disesuaikan utk endpoint di postman
    suspend fun getMerk(): MerkResponse                                                             //diganti ngikutin yg terbaru

    @GET("{idMerk}")                                                                                //endpoint
    suspend fun getMerkById(@Path("idMerk")idMerk:String): MerkDetailResponse                       //jd path karena merujuk ke alamat url

    @POST("store")
    suspend fun insertMerk(@Body merk: Merk)

    @PUT("{idMerk}")
    suspend fun updateMerk(@Path("idMerk")idMerk: String, @Body merk: Merk)

    @DELETE("{idMerk}")
    suspend fun deleteMerk(@Path("idMerk")idMerk: String): Response<Void>
}