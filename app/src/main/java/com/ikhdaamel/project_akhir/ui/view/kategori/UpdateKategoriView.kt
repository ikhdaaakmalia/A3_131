package com.ikhdaamel.project_akhir.ui.view.kategori

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikhdaamel.project_akhir.ui.customewidget.CustomeTopAppBar
import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.project_akhir.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.UpdateKategoriViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateKategori : DestinasiNavigasi {
    override val route = "update_kategori"
    override val titleRes = "UPDATE DATA KATEGORI"
    const val idKategori = "idKategori"
    val routesWithArg = "$route/{$idKategori}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateKategoriView(
    onBack:() -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdateKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val updateKategoriUIState = viewModel.updateKategoriUIState

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomeTopAppBar(
                title = DestinasiUpdateKategori.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack
            )
        }
    ){ padding ->
        InputKategoriBody(
            modifier = androidx.compose.ui.Modifier.padding(padding),
            insertKategoriUiState = viewModel.updateKategoriUIState,
            onKategoriValueChange = viewModel::updateInsertKategoriState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateKategori()
                    delay(600)
                    withContext(Dispatchers.Main){
                        onNavigate()
                    }
                }
            }
        )
    }
}