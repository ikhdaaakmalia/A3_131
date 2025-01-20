package com.ikhdaamel.project_akhir.ui.viewmodel.pemasok

import retrofit2.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.project_akhir.model.Pemasok
import com.ikhdaamel.project_akhir.repository.repository.PemasokRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomePemasokUiState{
    data class Success(val pemasok: List<Pemasok>) : HomePemasokUiState()
    object Error: HomePemasokUiState()
    object Loading: HomePemasokUiState()
}
class HomePemasokViewModel(private val pemasok: PemasokRepository): ViewModel(){
    var pemasokUIState: HomePemasokUiState by mutableStateOf(HomePemasokUiState.Loading)
        private set
    init {
        getPemasok()
    }
    fun getPemasok(){
        viewModelScope.launch {
            pemasokUIState = HomePemasokUiState.Loading
            pemasokUIState = try {
                HomePemasokUiState.Success(pemasok.getPemasok())
            } catch (e: IOException) {
                HomePemasokUiState.Error
            } catch (e: HttpException) {
                HomePemasokUiState.Error
            }
        }
    }
    fun deletePemasok(idPemasok: String){
        viewModelScope.launch {
            try {
                pemasok.deletePemasok(idPemasok)
            } catch (e: IOException) {
                HomePemasokUiState.Error
            } catch (e: HttpException) {
                HomePemasokUiState.Error
            }
        }
    }
}