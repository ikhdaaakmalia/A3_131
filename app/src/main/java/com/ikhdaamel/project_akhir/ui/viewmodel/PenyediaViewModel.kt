package com.ikhdaamel.project_akhir.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ikhdaamel.project_akhir.ManageApplication
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.DetailKategoriViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.HomeKategoriViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.UpdateKategoriViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.InsertKategoriViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.pemasok.DetailPemasokViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.pemasok.HomePemasokViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.pemasok.InsertPemasokViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.pemasok.UpdatePemasokViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.produk.DetailProdukViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.produk.InsertProdukViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.produk.UpdateProdukViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.produk.HomeProdukViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.merk.HomeMerkViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.merk.InsertMerkViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.merk.UpdateMerkViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.merk.DetailMerkViewModel


object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeProdukViewModel(ManageApp().container.produkRepository!!) }
        initializer { InsertProdukViewModel(ManageApp().container.produkRepository!!) }
        initializer { UpdateProdukViewModel(createSavedStateHandle(), ManageApp().container.produkRepository!!) }
        initializer { DetailProdukViewModel(createSavedStateHandle(), ManageApp().container.produkRepository!!) }
        initializer { HomePemasokViewModel(ManageApp().container.pemasokRepository!!) }
        initializer { InsertPemasokViewModel(ManageApp().container.pemasokRepository!!) }
        initializer { UpdatePemasokViewModel(createSavedStateHandle(), ManageApp().container.pemasokRepository!!) }
        initializer { DetailPemasokViewModel(createSavedStateHandle(), ManageApp().container.pemasokRepository!!) }
        initializer { HomeMerkViewModel(ManageApp().container.merkRepository!!) }
        initializer { InsertMerkViewModel(ManageApp().container.merkRepository!!) }
        initializer { UpdateMerkViewModel(createSavedStateHandle(), ManageApp().container.merkRepository!!) }
        initializer { DetailMerkViewModel(createSavedStateHandle(), ManageApp().container.merkRepository!!) }
        initializer { HomeKategoriViewModel(ManageApp().container.kategoriRepository!!) }
        initializer { InsertKategoriViewModel(ManageApp().container.kategoriRepository!!) }
        initializer { UpdateKategoriViewModel(createSavedStateHandle(), ManageApp().container.kategoriRepository!!) }
        initializer { DetailKategoriViewModel(createSavedStateHandle(), ManageApp().container.kategoriRepository!!) }
    }
        //memakai tanda !! karena di AppContainer ada tanda ? nya dan disini akan ditegaskan bahwa !! artinya tdk akan null
    fun CreationExtras.ManageApp(): ManageApplication =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ManageApplication)
}