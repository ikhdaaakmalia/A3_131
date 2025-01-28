package com.ikhdaamel.project_akhir.ui.viewmodel.produk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.ikhdaamel.project_akhir.model.Produk
import com.ikhdaamel.project_akhir.repository.repository.ProdukRepository
import com.ikhdaamel.project_akhir.ui.view.produk.DestinasiDetailProduk
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailProdukUiState{
    data class Success(val produk: Produk) : DetailProdukUiState()
    object Error : DetailProdukUiState()
    object Loading : DetailProdukUiState()
}
class DetailProdukViewModel (
    savedStateHandle: SavedStateHandle,
    private val produk: ProdukRepository
): ViewModel(){
    var produkDetailState : DetailProdukUiState by mutableStateOf(DetailProdukUiState.Loading)
        private set
    private val _idProduk : String = checkNotNull(savedStateHandle[DestinasiDetailProduk.idProduk])

    init {
        getProdukById()
    }

    fun getProdukById(){
        viewModelScope.launch {
            produkDetailState = DetailProdukUiState.Loading
            produkDetailState = try{
                val produk = produk.getProdukById(_idProduk)
                DetailProdukUiState.Success(produk)
            } catch (e: IOException) {
                DetailProdukUiState.Error
            } catch (e: HttpException) {
                DetailProdukUiState.Error
            }
        }
    }
}