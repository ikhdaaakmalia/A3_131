package com.ikhdaamel.project_akhir.ui.viewmodel.kategori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.project_akhir.model.Kategori
import com.ikhdaamel.project_akhir.repository.repository.KategoriRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeKategoriUiState{
    data class Success(val kategori: List<Kategori>) : HomeKategoriUiState()
    object Error: HomeKategoriUiState()
    object Loading: HomeKategoriUiState()
}
class HomeKategoriViewModel(private val kategori: KategoriRepository): ViewModel(){

    private val _kategoriUIState = MutableStateFlow<HomeKategoriUiState>(HomeKategoriUiState.Loading)
    val kategoriUIState: StateFlow<HomeKategoriUiState> get() = _kategoriUIState

    init {
        getKategori()
    }
    fun getKategori(){
        viewModelScope.launch {
            _kategoriUIState.value = HomeKategoriUiState.Loading
            _kategoriUIState.value = try {
                val kategoriList = kategori.getKategori()
                HomeKategoriUiState.Success(kategoriList)
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