package com.ikhdaamel.project_akhir.ui.viewmodel.kategori

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.project_akhir.repository.repository.KategoriRepository
import com.ikhdaamel.project_akhir.ui.view.kategori.DestinasiUpdateKategori

import kotlinx.coroutines.launch

class UpdateKategoriViewModel(
    savedStateHandle: SavedStateHandle,
    private val kategoriRepository: KategoriRepository
): ViewModel(){
    var updateKategoriUIState by mutableStateOf(InsertKategoriUiState())
        private set
    private val _idKategori: String = checkNotNull(savedStateHandle[DestinasiUpdateKategori.idKategori])

    init {
        viewModelScope.launch {
            updateKategoriUIState = kategoriRepository.getKategoriById(_idKategori)
                .toUiStateKategori()
        }
    }
    fun UpdateInsertKategoriState(insertKategoriUiEvent: InsertKategoriUiEvent){
        updateKategoriUIState = InsertKategoriUiState(
            insertKategoriUiEvent = InsertKategoriUiEvent()
        )
    }
    suspend fun updateKategori(){
        viewModelScope.launch {
            try {
                kategoriRepository.updateKategori(_idKategori, updateKategoriUIState.insertKategoriUiEvent.toKategori())
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}