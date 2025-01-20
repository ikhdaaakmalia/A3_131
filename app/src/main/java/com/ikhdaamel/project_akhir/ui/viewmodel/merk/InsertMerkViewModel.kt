package com.ikhdaamel.project_akhir.ui.viewmodel.merk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.project_akhir.model.Merk
import com.ikhdaamel.project_akhir.repository.repository.MerkRepository
import kotlinx.coroutines.launch

class InsertMerkViewModel (private val merkRepository: MerkRepository) : ViewModel(){
    var merkUiState by mutableStateOf(InsertMerkUiState())
        private set
    fun updateInsertMerkState(insertMerkUiEvent: InsertMerkUiEvent){
        merkUiState = InsertMerkUiState(insertMerkUiEvent = insertMerkUiEvent)
    }
    suspend fun insertMerk(){
        viewModelScope.launch{
            try {
                merkRepository.insertMerk(merkUiState.insertMerkUiEvent.toMerk())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertMerkUiState(
    val insertMerkUiEvent: InsertMerkUiEvent = InsertMerkUiEvent()
)

data class InsertMerkUiEvent(
    val idMerk: String = "",
    val namaMerk: String = "",
    val deskMerk: String = ""
)

fun InsertMerkUiEvent.toMerk(): Merk = Merk(
    idMerk = idMerk,
    namaMerk = namaMerk,
    deskMerk = deskMerk
)

fun Merk.toUiStateMerk(): InsertMerkUiState = InsertMerkUiState(
    insertMerkUiEvent = toInsertMerkUiEvent()
)

fun Merk.toInsertMerkUiEvent(): InsertMerkUiEvent = InsertMerkUiEvent(
    idMerk = idMerk,
    namaMerk = namaMerk,
    deskMerk = deskMerk
)