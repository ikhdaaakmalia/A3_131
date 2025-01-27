package com.ikhdaamel.project_akhir.ui.viewmodel.merk

import retrofit2.HttpException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.project_akhir.model.Merk
import com.ikhdaamel.project_akhir.repository.repository.MerkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeMerkUiState{
    data class Success(val merk: List<Merk>) : HomeMerkUiState()
    object Error: HomeMerkUiState()
    object Loading: HomeMerkUiState()
}
class HomeMerkViewModel(private val merk: MerkRepository): ViewModel(){

    private val _merkUIState = MutableStateFlow<HomeMerkUiState>(HomeMerkUiState.Loading)
    val merkUIState: StateFlow<HomeMerkUiState> get() = _merkUIState

    init {
        getMerk()
    }
    fun getMerk(){
        viewModelScope.launch {
            _merkUIState.value = HomeMerkUiState.Loading
            _merkUIState.value = try {
                val merkList = merk.getMerk()
                HomeMerkUiState.Success(merkList)
            } catch (e: IOException) {
                HomeMerkUiState.Error
            } catch (e: HttpException) {
                HomeMerkUiState.Error
            }
        }
    }
    fun deleteMerk(idMerk: String){
        viewModelScope.launch {
            try {
                merk.deleteMerk(idMerk)
            } catch (e: IOException) {
                HomeMerkUiState.Error
            } catch (e: HttpException) {
                HomeMerkUiState.Error
            }
        }
    }
}