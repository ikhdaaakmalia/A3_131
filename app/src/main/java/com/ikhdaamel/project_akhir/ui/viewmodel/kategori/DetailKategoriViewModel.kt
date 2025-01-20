package com.ikhdaamel.project_akhir.ui.viewmodel.kategori

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.ikhdaamel.project_akhir.model.Kategori
import com.ikhdaamel.project_akhir.repository.repository.KategoriRepository
import com.ikhdaamel.project_akhir.ui.view.kategori.DestinasiDetailKategori
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailKategoriUiState{
    data class Success(val kategori: Kategori) : DetailKategoriUiState()
    object Error : DetailKategoriUiState()
    object Loading : DetailKategoriUiState()
}

class DetailKategoriViewModel (
    savedStateHandle: SavedStateHandle,
    private val kategori: KategoriRepository
): ViewModel(){
    var kategoriDetailState : DetailKategoriUiState by mutableStateOf(DetailKategoriUiState.Loading)
        private set
    private val _idKategori : String = checkNotNull(savedStateHandle[DestinasiDetailKategori.idKategori])

    init {
        getKategoriById()
    }

    fun getKategoriById(){
        viewModelScope.launch {
            kategoriDetailState = DetailKategoriUiState.Loading
            kategoriDetailState = try{
                val kategori = kategori.getKategoriById(_idKategori)
                DetailKategoriUiState.Success(kategori)
            } catch (e: IOException) {
                DetailKategoriUiState.Error
            } catch (e: HttpException) {
                DetailKategoriUiState.Error
            }
        }
    }
}