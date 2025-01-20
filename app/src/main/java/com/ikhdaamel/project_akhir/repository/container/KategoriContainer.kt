package com.ikhdaamel.project_akhir.repository.container

import com.ikhdaamel.project_akhir.repository.repository.KategoriRepository
import com.ikhdaamel.project_akhir.repository.repository.MerkRepository
import com.ikhdaamel.project_akhir.repository.repository.NetworkKategoriRepository
import com.ikhdaamel.project_akhir.repository.repository.PemasokRepository
import com.ikhdaamel.project_akhir.repository.repository.ProdukRepository
import com.ikhdaamel.project_akhir.service.KategoriService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class KategoriContainer : AppContainer{
    private val baseUrl = "http://10.0.2.2:3000/api/kategori/"                 //based url
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val kategoriService: KategoriService by lazy { retrofit.create(KategoriService::class.java) }
    override val kategoriRepository: KategoriRepository by lazy { NetworkKategoriRepository(kategoriService) }
    override val produkRepository: ProdukRepository? = null                             //null agar tdk digunakan aja, karena appcontainernya general jd semua bisa akses
    override val merkRepository: MerkRepository? = null                                 //null agar tdk digunakan aja, karena appcontainernya general jd semua bisa akses
    override val pemasokRepository: PemasokRepository? = null                            //null agar tdk digunakan aja, karena appcontainernya general jd semua bisa akses
}