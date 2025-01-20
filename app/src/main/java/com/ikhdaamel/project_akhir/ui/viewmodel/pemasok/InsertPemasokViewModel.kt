package com.ikhdaamel.project_akhir.ui.viewmodel.pemasok

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.project_akhir.model.Pemasok
import com.ikhdaamel.project_akhir.repository.repository.PemasokRepository
import kotlinx.coroutines.launch

class InsertPemasokViewModel (private val pemasokRepository: PemasokRepository) : ViewModel(){
    var pemasokUiState by mutableStateOf(InsertPemasokUiState())
        private set
    fun updateInsertPemasokState(insertPemasokUiEvent: InsertPemasokUiEvent){
        pemasokUiState = InsertPemasokUiState(insertPemasokUiEvent = insertPemasokUiEvent)
    }
    suspend fun insertPemasok(){
        viewModelScope.launch{
            try {
                pemasokRepository.insertPemasok(pemasokUiState.insertPemasokUiEvent.toPemasok())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertPemasokUiState(
    val insertPemasokUiEvent : InsertPemasokUiEvent = InsertPemasokUiEvent()
)

data class InsertPemasokUiEvent(
    val idPemasok : String = "",
    val namaPemasok : String = "",
    val alamatPemasok : String = "",
    val telpPemasok : String = ""
)

fun InsertPemasokUiEvent.toPemasok(): Pemasok = Pemasok(
    idPemasok = idPemasok,
    namaPemasok = namaPemasok,
    alamatPemasok = alamatPemasok,
    telpPemasok = telpPemasok
)

fun Pemasok.toUiStatePemasok(): InsertPemasokUiState = InsertPemasokUiState(
    insertPemasokUiEvent = toInsertPemasokUiEvent()
)

fun Pemasok.toInsertPemasokUiEvent(): InsertPemasokUiEvent = InsertPemasokUiEvent(
    idPemasok = idPemasok,
    namaPemasok = namaPemasok,
    alamatPemasok = alamatPemasok,
    telpPemasok = telpPemasok
)