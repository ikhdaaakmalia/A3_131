package com.ikhdaamel.project_akhir.ui.viewmodel.produk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.project_akhir.model.Produk
import com.ikhdaamel.project_akhir.repository.repository.ProdukRepository
import kotlinx.coroutines.launch

class InsertProdukViewModel (private val produkRepository: ProdukRepository) : ViewModel(){
    var produkUiState by mutableStateOf(InsertProdukUiState())
        private set
    fun updateInsertProdukState(insertProdukUiEvent: InsertProdukUiEvent){
        produkUiState = InsertProdukUiState(insertProdukUiEvent = insertProdukUiEvent)
    }
    suspend fun insertProduk(){
        viewModelScope.launch{
            try {
                produkRepository.insertProduk(produkUiState.insertProdukUiEvent.toProduk())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertProdukUiState(
    val insertProdukUiEvent: InsertProdukUiEvent = InsertProdukUiEvent()
)

data class InsertProdukUiEvent(
    val idProduk: String= "",
    val namaProduk: String= "",
    val deskProduk: String= "",
    val harga: String= "",
    val stok: String= "",
    val idKategori: String= "",
    val idPemasok: String= "",
    val idMerk: String= ""
)

fun InsertProdukUiEvent.toProduk(): Produk = Produk(
    idProduk = idProduk,
    namaProduk = namaProduk,
    deskProduk = deskProduk,
    harga = harga,
    stok = stok,
    idKategori = idKategori,
    idPemasok = idPemasok,
    idMerk = idMerk
)

fun Produk.toUiStateProduk(): InsertProdukUiState = InsertProdukUiState(
    insertProdukUiEvent = toInsertProdukUiEvent()
)

fun Produk.toInsertProdukUiEvent(): InsertProdukUiEvent = InsertProdukUiEvent(
    idProduk = idProduk,
    namaProduk = namaProduk,
    deskProduk = deskProduk,
    harga = harga,
    stok = stok,
    idKategori = idKategori,
    idPemasok = idPemasok,
    idMerk = idMerk
)