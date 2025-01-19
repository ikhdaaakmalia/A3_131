package com.ikhdaamel.project_akhir.service

import com.ikhdaamel.project_akhir.model.Pemasok
import com.ikhdaamel.project_akhir.model.PemasokDetailResponse
import com.ikhdaamel.project_akhir.model.PemasokResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PemasokService {
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
    )
    @GET(".")                                                               //ganti jadi titik krn disesuaikan utk endpoint di postman
    suspend fun getPemasok(): PemasokResponse                            //diganti ngikutin yg terbaru

    @GET("{idPemasok}")                                                                               //endpoint
    suspend fun getPemasokById(@Path("idPemasok")idPemasok:String): PemasokDetailResponse                //jd path karena merujuk ke alamat url

    @POST("store")
    suspend fun insertPemasok(@Body pemasok: Pemasok)

    @PUT("{idPemasok}")
    suspend fun updatePemasok(@Path("idPemasok")idPemasok: String, @Body pemasok: Pemasok)

    @DELETE("{idPemasok}")
    suspend fun deletePemasok(@Path("idPemasok")idPemasok: String): Response<Void>
}