package com.ikhdaamel.project_akhir.repository.repository

import com.ikhdaamel.project_akhir.model.Kategori
import com.ikhdaamel.project_akhir.model.Merk
import com.ikhdaamel.project_akhir.model.Pemasok
import com.ikhdaamel.project_akhir.model.Produk
import com.ikhdaamel.project_akhir.service.ProdukService
import java.io.IOException


interface ProdukRepository {
    suspend fun getProduk(): List<Produk>
    suspend fun insertProduk(produk: Produk)
    suspend fun updateProduk(idProduk: String, produk: Produk)
    suspend fun deleteProduk(idProduk: String)
    suspend fun getProdukById(idProduk: String):Produk
    suspend fun getKategori(): List<Kategori>
    suspend fun insertProdukInKategori(idKategori: String, produk: Produk)
    suspend fun getPemasok(): List<Pemasok>
    suspend fun getMerk(): List<Merk>
}

class NetworkProdukRepository(
    private val produkApiService: ProdukService
): ProdukRepository {
    override suspend fun insertProduk(produk: Produk){
        produkApiService.insertProduk(produk)
    }

    override suspend fun updateProduk(idProduk: String, produk: Produk) {
        produkApiService.updateProduk(idProduk, produk)
    }

    override suspend fun deleteProduk(Produk: String) {
        try {
            val response = produkApiService.deleteProduk(Produk)
            if(!response.isSuccessful){
                throw IOException("gagal menghapus data Produk. HTTP kode: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }

    override suspend fun getProduk(): List<Produk> {
        return produkApiService.getProduk().dataProduk                              //tambah .data karena struktur json sdh beda
    }
    override suspend fun getProdukById(idProduk: String): Produk {
        return produkApiService.getProdukById(idProduk).dataProduk                       //memanggil data untuk memasukkan value
    }
    override suspend fun getKategori(): List<Kategori> {
        return produkApiService.getKategori().dataKategori
    }
    override suspend fun insertProdukInKategori(idKategori: String, produk: Produk) {
        produkApiService.insertProdukInKategori(idKategori, produk)
    }
    override suspend fun getPemasok(): List<Pemasok> {
        return produkApiService.getPemasok().dataPemasok
    }
    override suspend fun getMerk(): List<Merk> {
        return produkApiService.getMerk().dataMerk
    }
}