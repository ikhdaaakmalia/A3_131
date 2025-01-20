package com.ikhdaamel.project_akhir.ui.viewmodel.kategori

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.project_akhir.model.Kategori
import com.ikhdaamel.project_akhir.repository.repository.KategoriRepository
import kotlinx.coroutines.launch

class InsertKategoriViewModel (private val kategoriRepository: KategoriRepository) : ViewModel(){
    var kategorikUiState by mutableStateOf(InsertKategoriUiState())
        private set
    fun updateInsertKategoriState(insertKategoriUiEvent: InsertKategoriUiEvent){
        kategorikUiState = InsertKategoriUiState(insertKategoriUiEvent = insertKategoriUiEvent)
    }
    suspend fun insertKategori(){
        viewModelScope.launch{
            try {
                kategoriRepository.insertKategori(kategorikUiState.insertKategoriUiEvent.toKategori())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}


data class InsertKategoriUiState(
    val insertKategoriUiEvent: InsertKategoriUiEvent = InsertKategoriUiEvent()
)

data class InsertKategoriUiEvent(
    val idKategori: String = "",
    val namaKategori: String = "",
    val deskKategori: String = ""
)

fun InsertKategoriUiEvent.toKategori() : Kategori = Kategori(
    idKategori = idKategori,
    namaKategori = namaKategori,
    deskKategori = deskKategori
)

fun Kategori.toUiStateKategori(): InsertKategoriUiState = InsertKategoriUiState(
    insertKategoriUiEvent = toInsertKategoriUiEvent()
)

fun Kategori.toInsertKategoriUiEvent(): InsertKategoriUiEvent = InsertKategoriUiEvent(
    idKategori = idKategori,
    namaKategori = namaKategori,
    deskKategori = deskKategori
)