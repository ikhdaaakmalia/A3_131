package com.ikhdaamel.project_akhir.ui.viewmodel.pemasok

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.ikhdaamel.project_akhir.model.Pemasok
import com.ikhdaamel.project_akhir.repository.repository.PemasokRepository
import com.ikhdaamel.project_akhir.ui.view.pemasok.DestinasiDetailPemasok
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailPemasokUiState{
    data class Success(val pemasok: Pemasok) : DetailPemasokUiState()
    object Error : DetailPemasokUiState()
    object Loading : DetailPemasokUiState()
}

class DetailPemasokViewModel (
    savedStateHandle: SavedStateHandle,
    private val pemasok: PemasokRepository
): ViewModel(){
    var pemasokDetailState : DetailPemasokUiState by mutableStateOf(DetailPemasokUiState.Loading)
        private set
    private val _idPemasok : String = checkNotNull(savedStateHandle[DestinasiDetailPemasok.idPemasok])

    init {
        getPemasokById()
    }

    fun getPemasokById(){
        viewModelScope.launch {
            pemasokDetailState = DetailPemasokUiState.Loading
            pemasokDetailState = try{
                val pemasok = pemasok.getPemasokById(_idPemasok)
                DetailPemasokUiState.Success(pemasok)
            } catch (e: IOException) {
                DetailPemasokUiState.Error
            } catch (e: HttpException) {
                DetailPemasokUiState.Error
            }
        }
    }
}