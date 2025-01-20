package com.ikhdaamel.project_akhir.ui.viewmodel.kategori

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.project_akhir.model.Kategori
import com.ikhdaamel.project_akhir.repository.repository.KategoriRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeKategoriUiState{
    data class Success(val kategori: List<Kategori>) : HomeKategoriUiState()
    object Error: HomeKategoriUiState()
    object Loading: HomeKategoriUiState()
}
class HomeKategoriViewModel(private val kategori: KategoriRepository): ViewModel(){
    var kategoriUIState: HomeKategoriUiState by mutableStateOf(HomeKategoriUiState.Loading)
        private set
    init {
        getKategori()
    }
    fun getKategori(){
        viewModelScope.launch {
            kategoriUIState = HomeKategoriUiState.Loading
            kategoriUIState = try {
                HomeKategoriUiState.Success(kategori.getKategori())
            } catch (e: IOException) {
                HomeKategoriUiState.Error
            } catch (e: HttpException) {
                HomeKategoriUiState.Error
            }
        }
    }
    fun deleteKategori(idKategori: String){
        viewModelScope.launch {
            try {
                kategori.deleteKategori(idKategori)
            } catch (e: IOException) {
                HomeKategoriUiState.Error
            } catch (e: HttpException) {
                HomeKategoriUiState.Error
            }
        }
    }
}