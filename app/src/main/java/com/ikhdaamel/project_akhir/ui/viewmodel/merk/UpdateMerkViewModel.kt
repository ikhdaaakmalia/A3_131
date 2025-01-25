package com.ikhdaamel.project_akhir.ui.viewmodel.merk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.project_akhir.repository.repository.MerkRepository
import kotlinx.coroutines.launch

class UpdateMerkViewModel(
    savedStateHandle: SavedStateHandle,
    private val merkRepository: MerkRepository
): ViewModel(){
    var updateMerkUIState by mutableStateOf(InsertMerkUiState())
        private set
    private val _idMerk: String = checkNotNull(savedStateHandle["idMerk"])

    init {
        viewModelScope.launch {
            updateMerkUIState = merkRepository.getMerkById(_idMerk)
                .toUiStateMerk()
        }
    }
    fun updateMerkState(merkUiEvent: InsertMerkUiEvent){
        updateMerkUIState = InsertMerkUiState(
            insertMerkUiEvent = merkUiEvent
        )
    }
    suspend fun updateMerk(){
        viewModelScope.launch {
            try {
                merkRepository.updateMerk(_idMerk, updateMerkUIState.insertMerkUiEvent.toMerk())
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}