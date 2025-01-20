package com.ikhdaamel.project_akhir.ui.viewmodel.merk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.ikhdaamel.project_akhir.model.Merk
import com.ikhdaamel.project_akhir.repository.repository.MerkRepository
import com.ikhdaamel.project_akhir.ui.view.merk.DestinasiDetailMerk
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailMerkUiState{
    data class Success(val merk: Merk) : DetailMerkUiState()
    object Error : DetailMerkUiState()
    object Loading : DetailMerkUiState()
}

class DetailMerkViewModel (
    savedStateHandle: SavedStateHandle,
    private val merk: MerkRepository
): ViewModel(){
    var merkDetailState : DetailMerkUiState by mutableStateOf(DetailMerkUiState.Loading)
        private set
    private val _idMerk : String = checkNotNull(savedStateHandle[DestinasiDetailMerk.idMerk])

    init {
        getMerkById()
    }

    fun getMerkById(){
        viewModelScope.launch {
            merkDetailState = DetailMerkUiState.Loading
            merkDetailState = try{
                val merk = merk.getMerkById(_idMerk)
                DetailMerkUiState.Success(merk)
            } catch (e: IOException) {
                DetailMerkUiState.Error
            } catch (e: HttpException) {
                DetailMerkUiState.Error
            }
        }
    }
}