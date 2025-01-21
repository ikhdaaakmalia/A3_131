package com.ikhdaamel.project_akhir.ui.viewmodel.produk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.project_akhir.repository.repository.ProdukRepository
import com.ikhdaamel.project_akhir.ui.view.produk.DestinasiUpdateProduk
import kotlinx.coroutines.launch

class UpdateProdukkViewModel(
    savedStateHandle: SavedStateHandle,
    private val produkRepository: ProdukRepository
): ViewModel(){
    var updateProdukUIState by mutableStateOf(InsertProdukUiState())
        private set
    private val _idProduk: String = checkNotNull(savedStateHandle[DestinasiUpdateProduk.idProduk])

    init {
        viewModelScope.launch {
            updateProdukUIState = produkRepository.getProdukById(_idProduk)
                .toUiStateProduk()
        }
    }
    fun UpdateInsertProdukState(insertProdukUiEvent: InsertProdukUiEvent){
        updateProdukUIState = InsertProdukUiState(
            insertProdukUiEvent = InsertProdukUiEvent()
        )
    }
    suspend fun updateProduk(){
        viewModelScope.launch {
            try {
                produkRepository.updateProduk(_idProduk, updateProdukUIState.insertProdukUiEvent.toProduk())
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}