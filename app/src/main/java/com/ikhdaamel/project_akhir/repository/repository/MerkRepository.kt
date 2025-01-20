package com.ikhdaamel.project_akhir.repository.repository

import com.ikhdaamel.project_akhir.model.Merk
import com.ikhdaamel.project_akhir.service.MerkService
import java.io.IOException

interface MerkRepository {
    suspend fun getMerk(): List<Merk>
    suspend fun insertMerk(merk: Merk)
    suspend fun updateMerk(idMerk: String, merk: Merk)
    suspend fun deleteMerk(idMerk: String)
    suspend fun getMerkById(idMerk: String): Merk
}

class NetworkMerkRepository(
    private val merkApiService: MerkService
): MerkRepository {
    override suspend fun insertMerk(merk: Merk){
        merkApiService.insertMerk(merk)
    }

    override suspend fun updateMerk(idMerk: String, merk: Merk) {
        merkApiService.updateMerk(idMerk, merk)
    }

    override suspend fun deleteMerk(idMerk: String) {
        try {
            val response = merkApiService.deleteMerk(idMerk)
            if(!response.isSuccessful){
                throw IOException("gagal menghapus data Merk. HTTP kode: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }

    override suspend fun getMerk(): List<Merk> {
        return merkApiService.getMerk().dataMerk                             //tambah .data karena struktur json sdh beda
    }
    override suspend fun getMerkById(idMerk: String): Merk {
        return merkApiService.getMerkById(idMerk).dataMerk                       //memanggil data untuk memasukkan value
    }
}