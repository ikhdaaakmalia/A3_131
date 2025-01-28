package com.ikhdaamel.project_akhir.ui.viewmodel.produk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.project_akhir.model.Produk
import com.ikhdaamel.project_akhir.repository.repository.ProdukRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeProdukUiState{
    data class Success(val produk: List<Produk>) : HomeProdukUiState()
    object Error: HomeProdukUiState()
    object Loading: HomeProdukUiState()
}
class HomeProdukViewModel(private val produk: ProdukRepository): ViewModel(){
    private val _produkUIState = MutableStateFlow<HomeProdukUiState>(HomeProdukUiState.Loading)
    val produkUIState: StateFlow<HomeProdukUiState> get() = _produkUIState

    init {
        getProduk()
    }
    fun getProduk(){
        viewModelScope.launch {
            _produkUIState.value = HomeProdukUiState.Loading
            _produkUIState.value = try {
                val produkList = produk.getProduk()
                HomeProdukUiState.Success(produkList)
            } catch (e: IOException) {
                HomeProdukUiState.Error
            } catch (e: HttpException) {
                HomeProdukUiState.Error
            }
        }
    }
    fun deleteProduk(idProduk: String){
        viewModelScope.launch {
            try {
                produk.deleteProduk(idProduk)
            } catch (e: IOException) {
                HomeProdukUiState.Error
            } catch (e: HttpException) {
                HomeProdukUiState.Error
            }
        }
    }
}