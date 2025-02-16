package com.ikhdaamel.project_akhir.ui.view.merk

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
import com.ikhdaamel.project_akhir.model.Merk
import com.ikhdaamel.project_akhir.ui.customewidget.CustomTopAppBar
import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.project_akhir.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.merk.HomeMerkUiState
import com.ikhdaamel.project_akhir.ui.viewmodel.merk.HomeMerkViewModel
import kotlinx.coroutines.flow.StateFlow

object DestinasiHomeMerk : DestinasiNavigasi {
    override val route = "home_merk"
    override val titleRes = "DATA MERK"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMerkView(
    onBack: () -> Unit,
    onInsertMerk: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailMerk: (String) -> Unit = {},
    viewModel: HomeMerkViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color(0xFF005BAC),
        topBar = {
            CustomTopAppBar(
                title = DestinasiHomeMerk.titleRes,
                canNavigateBack = true,
                navigateUp = onBack,
                scrollBehavior = scrollBehavior,
                onRefresh = { viewModel.getMerk() }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onInsertMerk,
                containerColor = Color(0xFFFFCC00),
                contentColor = Color.White,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Merk")
            }
        },
    ) { innerPadding ->
        HomeMerkStatus(
            homeMerkUiState = viewModel.merkUIState,
            retryAction = { viewModel.getMerk() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailMerk,
            onDeleteClick = {
                viewModel.deleteMerk(it.idMerk)
                viewModel.getMerk()
            }
        )
    }
}

@Composable
fun HomeMerkStatus(
    homeMerkUiState: StateFlow<HomeMerkUiState>,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Merk) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (val state = homeMerkUiState.collectAsState().value) {
        is HomeMerkUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomeMerkUiState.Success ->
            if (state.merk.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak Ada Data Merk")
                }
            } else {
                MerkLayout(
                    merk = state.merk,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = onDetailClick,
                    onDeleteClick = { onDeleteClick(it) }
                )
            }
        is HomeMerkUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
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
fun MerkLayout(
    merk: List<Merk>,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit,
    onDeleteClick: (Merk) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(merk) { merk ->
            MerkCard(
                merk = merk,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(merk.idMerk) },
                onDeleteClick = { onDeleteClick(merk) }
            )
        }
    }
}

@Composable
fun MerkCard(
    merk: Merk,
    modifier: Modifier = Modifier,
    onDeleteClick: (Merk) -> Unit = {}
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
                    text = merk.namaMerk,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(merk) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
            Text(
                text = merk.deskMerk,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
