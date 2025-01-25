package com.ikhdaamel.project_akhir.ui.viewmodel.pemasok

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.project_akhir.repository.repository.PemasokRepository
import com.ikhdaamel.project_akhir.ui.view.pemasok.DestinasiUpdatePemasok
import kotlinx.coroutines.launch

class UpdatePemasokViewModel(
    savedStateHandle: SavedStateHandle,
    private val pemasokRepository: PemasokRepository
): ViewModel() {
    var updatePemasokUIState by mutableStateOf(InsertPemasokUiState())
        private set
    private val _idPemasok: String = checkNotNull(savedStateHandle["idPemasok"])

    init {
        viewModelScope.launch {
            updatePemasokUIState = pemasokRepository.getPemasokById(_idPemasok)
                .toUiStatePemasok()
        }
    }
    fun updatePemasokState(pemasokUiEvent: InsertPemasokUiEvent) {
        updatePemasokUIState = InsertPemasokUiState(
            insertPemasokUiEvent = pemasokUiEvent
        )
    }
    suspend fun updatePemasok() {
        viewModelScope.launch {
            try {
                pemasokRepository.updatePemasok(_idPemasok, updatePemasokUIState.insertPemasokUiEvent.toPemasok())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
