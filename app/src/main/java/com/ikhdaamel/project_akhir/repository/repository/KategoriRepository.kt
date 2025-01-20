package com.ikhdaamel.project_akhir.repository.repository

import com.ikhdaamel.project_akhir.model.Kategori
import com.ikhdaamel.project_akhir.service.KategoriService
import java.io.IOException

interface KategoriRepository {
    suspend fun getKategori(): List<Kategori>
    suspend fun insertKategori(kategori: Kategori)
    suspend fun updateKategori(idKategori: String, kategori: Kategori)
    suspend fun deleteKategori(idKategori: String)
    suspend fun getKategoriById(idKategori: String): Kategori
}

class NetworkKategoriRepository(
    private val kategoriApiService: KategoriService
): KategoriRepository {
    override suspend fun insertKategori(kategori: Kategori){
        kategoriApiService.insertKategori(kategori)
    }

    override suspend fun updateKategori(idKategori: String, kategori: Kategori) {
        kategoriApiService.updateKategori(idKategori, kategori)
    }

    override suspend fun deleteKategori(idKategori: String) {
        try {
            val response = kategoriApiService.deleteKategori(idKategori)
            if(!response.isSuccessful){
                throw IOException("gagal menghapus data Kategori. HTTP kode: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }

    override suspend fun getKategori(): List<Kategori> {
        return kategoriApiService.getKategori().dataKategori                             //tambah .data karena struktur json sdh beda
    }
    override suspend fun getKategoriById(idKategori: String): Kategori {
        return kategoriApiService.getKategoriById(idKategori).dataKategori                     //memanggil data untuk memasukkan value
    }
}