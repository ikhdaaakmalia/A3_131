package com.ikhdaamel.project_akhir.repository.repository

import com.ikhdaamel.project_akhir.model.Pemasok
import com.ikhdaamel.project_akhir.service.PemasokService
import java.io.IOException

interface PemasokRepository {
    suspend fun getPemasok(): List<Pemasok>
    suspend fun insertPemasok(pemasok: Pemasok)
    suspend fun updatePemasok(idPemasok: String, pemasok: Pemasok)
    suspend fun deletePemasok(idPemasok: String)
    suspend fun getPemasokById(idPemasok: String):Pemasok
}

class NetworkPemasokRepository(
    private val pemasokApiService: PemasokService
): PemasokRepository {
    override suspend fun insertPemasok(pemasok: Pemasok){
        pemasokApiService.insertPemasok(pemasok)
    }

    override suspend fun updatePemasok(idPemasok: String, pemasok: Pemasok) {
        pemasokApiService.updatePemasok(idPemasok, pemasok)
    }

    override suspend fun deletePemasok(idPemasok: String) {
        try {
            val response = pemasokApiService.deletePemasok(idPemasok)
            if(!response.isSuccessful){
                throw IOException("gagal menghapus data Pemasok. HTTP kode: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }

    override suspend fun getPemasok(): List<Pemasok> {
        return pemasokApiService.getPemasok().data                             //tambah .data karena struktur json sdh beda
    }
    override suspend fun getPemasokById(idPemasok: String): Pemasok {
        return pemasokApiService.getPemasokById(idPemasok).data                       //memanggil data untuk memasukkan value
    }
}