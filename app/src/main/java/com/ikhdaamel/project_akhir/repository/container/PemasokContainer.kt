package com.ikhdaamel.project_akhir.repository.container

import com.ikhdaamel.project_akhir.repository.repository.KategoriRepository
import com.ikhdaamel.project_akhir.repository.repository.MerkRepository
import com.ikhdaamel.project_akhir.repository.repository.NetworkPemasokRepository
import com.ikhdaamel.project_akhir.repository.repository.PemasokRepository
import com.ikhdaamel.project_akhir.repository.repository.ProdukRepository
import com.ikhdaamel.project_akhir.service.PemasokService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class PemasokContainer : AppContainer{
    private val baseUrl = "http://10.0.2.2:3000/api/pemasok/"                 //based url
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val pemasokService: PemasokService by lazy { retrofit.create(PemasokService::class.java) }
    override val pemasokRepository: PemasokRepository by lazy { NetworkPemasokRepository(pemasokService) }
    override val produkRepository: ProdukRepository? = null                             //null agar tdk digunakan aja, karena appcontainernya general jd semua bisa akses
    override val merkRepository: MerkRepository? = null                                 //null agar tdk digunakan aja, karena appcontainernya general jd semua bisa akses
    override val kategoriRepository: KategoriRepository? = null                         //null agar tdk digunakan aja, karena appcontainernya general jd semua bisa akses
}