package com.ikhdaamel.project_akhir.ui.view.pemasok

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikhdaamel.project_akhir.R
import com.ikhdaamel.project_akhir.model.Pemasok
import com.ikhdaamel.project_akhir.ui.customewidget.CustomTopAppBar
import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.project_akhir.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.pemasok.HomePemasokUiState
import com.ikhdaamel.project_akhir.ui.viewmodel.pemasok.HomePemasokViewModel
import kotlinx.coroutines.flow.StateFlow

object DestinasiHomePemasok : DestinasiNavigasi {
    override val route = "home_pemasok"
    override val titleRes = "DATA PEMASOK"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePemasokView(
    onBack: () -> Unit,
    onInsertPemasok: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailPemasok: (String) -> Unit = {},
    viewModel: HomePemasokViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color(0xFF005BAC),
        topBar = {
            CustomTopAppBar(
                title = DestinasiHomePemasok.titleRes,
                canNavigateBack = true,
                navigateUp = onBack,
                scrollBehavior = scrollBehavior,
                onRefresh = { viewModel.getPemasok() }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color(0xFFFFCC00),
                onClick = onInsertPemasok,
                contentColor = Color.White,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Pemasok")
            }
        },
    ) { innerPadding ->
        HomePemasokStatus(
            homePemasokUiState = viewModel.pemasokUIState,
            retryAction = { viewModel.getPemasok() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailPemasok,
            onDeleteClick = {
                viewModel.deletePemasok(it.idPemasok)
                viewModel.getPemasok()
            }
        )
    }
}

@Composable
fun HomePemasokStatus(
    homePemasokUiState: StateFlow<HomePemasokUiState>,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pemasok) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (val state = homePemasokUiState.collectAsState().value) {
        is HomePemasokUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomePemasokUiState.Success ->
            if (state.pemasok.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak Ada Data Pemasok")
                }
            } else {
                PemasokLayout(
                    pemasok = state.pemasok,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = onDetailClick,
                    onDeleteClick = { onDeleteClick(it) }
                )
            }
        is HomePemasokUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.eror), contentDescription = "")
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun PemasokLayout(
    pemasok: List<Pemasok>,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit,
    onDeleteClick: (Pemasok) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pemasok) { pemasok ->
            PemasokCard(
                pemasok = pemasok,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(pemasok.idPemasok) },
                onDeleteClick = { onDeleteClick(pemasok) }
            )
        }
    }
}

@Composable
fun PemasokCard(
    pemasok: Pemasok,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pemasok) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pemasok.namaPemasok,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(pemasok) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
            Text(text = "Alamat: ${pemasok.alamatPemasok}", color = Color(0xFFFFCC00))
            Text(text = "No Telp: ${pemasok.telpPemasok}", color = Color(0xFF005BAC))

        }
    }
}
