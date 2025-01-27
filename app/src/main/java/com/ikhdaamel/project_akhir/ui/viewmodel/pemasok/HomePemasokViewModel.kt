package com.ikhdaamel.project_akhir.ui.viewmodel.pemasok

import retrofit2.HttpException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.project_akhir.model.Pemasok
import com.ikhdaamel.project_akhir.repository.repository.PemasokRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomePemasokUiState{
    data class Success(val pemasok: List<Pemasok>) : HomePemasokUiState()
    object Error: HomePemasokUiState()
    object Loading: HomePemasokUiState()
}
class HomePemasokViewModel(private val pemasok: PemasokRepository): ViewModel(){

    private val _pemasokUIState = MutableStateFlow<HomePemasokUiState>(HomePemasokUiState.Loading)
    val pemasokUIState: StateFlow<HomePemasokUiState> get() = _pemasokUIState

    init {
        getPemasok()
    }
    fun getPemasok(){
        viewModelScope.launch {
            _pemasokUIState.value = HomePemasokUiState.Loading
            _pemasokUIState.value = try {
                val pemasokList = pemasok.getPemasok()
                HomePemasokUiState.Success(pemasokList)
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