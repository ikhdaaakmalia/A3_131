package com.ikhdaamel.project_akhir.ui.view.produk

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikhdaamel.project_akhir.ui.customewidget.Choice.kategoriChoice
import com.ikhdaamel.project_akhir.ui.customewidget.Choice.merkChoice
import com.ikhdaamel.project_akhir.ui.customewidget.Choice.pemasokChoice
import com.ikhdaamel.project_akhir.ui.customewidget.CustomeTopAppBar
import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.project_akhir.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.HomeKategoriViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.merk.HomeMerkViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.pemasok.HomePemasokViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.produk.UpdateProdukViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateProduk : DestinasiNavigasi {
    override val route = "update_produk"
    override val titleRes = "UPDATE DATA PRODUK"
    const val idProduk = "idProduk"
    val routesWithArg = "$route/{$idProduk}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProdukView(
    onBack:() -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdateProdukViewModel = viewModel(factory = PenyediaViewModel.Factory),
    kategoriViewModel : HomeKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory),
    merkViewModel : HomeMerkViewModel = viewModel(factory = PenyediaViewModel.Factory),
    pemasokViewModel : HomePemasokViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val updateProdukUIState = viewModel.updateProdukUIState

    val kategoriList = kategoriChoice(kategoriViewModel)
    val merkList = merkChoice(merkViewModel)
    val pemasokList = pemasokChoice(pemasokViewModel)

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color(0xFF005BAC),
        topBar = {
            CustomeTopAppBar(
                title = DestinasiUpdateProduk.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack
            )
        }
    ){ padding ->
        InputProdukBody(
            modifier = androidx.compose.ui.Modifier.padding(padding),
            insertProdukUiState = updateProdukUIState,
            kategoriList = kategoriList,
            merkList = merkList,
            pemasokList = pemasokList,
            onProdukValueChange = viewModel::updateProdukState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateProduk()
                    delay(600)
                    withContext(Dispatchers.Main){
                        onNavigate()
                    }
                }
            }
        )
    }
}