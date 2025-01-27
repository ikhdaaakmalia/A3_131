package com.ikhdaamel.project_akhir.ui.view.pemasok

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikhdaamel.project_akhir.ui.customewidget.CustomeTopAppBar
import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.project_akhir.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.pemasok.UpdatePemasokViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.ui.Modifier

object DestinasiUpdatePemasok : DestinasiNavigasi {
    override val route = "update_pemasok"
    override val titleRes = "UPDATE DATA PEMASOK"
    const val idPemasok = "idPemasok"
    val routesWithArg = "$route/{$idPemasok}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePemasokView(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdatePemasokViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val updatePemasokUIState = viewModel.updatePemasokUIState

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomeTopAppBar(
                title = DestinasiUpdatePemasok.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack
            )
        }
    ) { padding ->
        InputPemasokBody(
            modifier = androidx.compose.ui.Modifier.padding(padding),
            insertPemasokUiState = updatePemasokUIState,
            onPemasokValueChange = viewModel::updatePemasokState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatePemasok()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            }
        )
    }
}
