package com.ikhdaamel.project_akhir.ui.view.merk

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
import com.ikhdaamel.project_akhir.ui.customewidget.CustomTopAppBar
import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.project_akhir.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.merk.UpdateMerkViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateMerk : DestinasiNavigasi {
    override val route = "update_merk"
    override val titleRes = "UPDATE DATA MERK"
    const val idMerk = "idMerk"
    val routesWithArg = "$route/{$idMerk}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateMerkView(
    onBack:() -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdateMerkViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val updateMerkUIState = viewModel.updateMerkUIState

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color(0xFF005BAC),
        topBar = {
            CustomTopAppBar(
                title = DestinasiUpdateMerk.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack
            )
        }
    ){ padding ->
        InputMerkBody(
            modifier = androidx.compose.ui.Modifier.padding(padding),
            insertMerkUiState = viewModel.updateMerkUIState,
            onMerkValueChange = viewModel::updateMerkState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateMerk()
                    delay(600)
                    withContext(Dispatchers.Main){
                        onNavigate()
                    }
                }
            }
        )
    }
}