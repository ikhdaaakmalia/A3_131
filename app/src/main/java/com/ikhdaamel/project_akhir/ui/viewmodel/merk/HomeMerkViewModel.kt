package com.ikhdaamel.project_akhir.ui.viewmodel.merk

import retrofit2.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.project_akhir.model.Merk
import com.ikhdaamel.project_akhir.repository.repository.MerkRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeMerkUiState{
    data class Success(val merk: List<Merk>) : HomeMerkUiState()
    object Error: HomeMerkUiState()
    object Loading: HomeMerkUiState()
}
class HomeMerkViewModel(private val merk: MerkRepository): ViewModel(){
    var merkUIState: HomeMerkUiState by mutableStateOf(HomeMerkUiState.Loading)
        private set
    init {
        getMerk()
    }
    fun getMerk(){
        viewModelScope.launch {
            merkUIState = HomeMerkUiState.Loading
            merkUIState = try {
                HomeMerkUiState.Success(merk.getMerk())
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