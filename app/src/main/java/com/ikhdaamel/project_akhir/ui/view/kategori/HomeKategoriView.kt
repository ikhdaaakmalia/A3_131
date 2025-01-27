package com.ikhdaamel.project_akhir.ui.view.kategori

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikhdaamel.project_akhir.R
import com.ikhdaamel.project_akhir.model.Kategori
import com.ikhdaamel.project_akhir.ui.customewidget.CustomeTopAppBar
import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.project_akhir.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.HomeKategoriUiState
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.HomeKategoriViewModel
import kotlinx.coroutines.flow.StateFlow

object DestinasiHomeKategori : DestinasiNavigasi {
    override val route = "home_kategori"
    override val titleRes = "HOME KATEGORI"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeKategoriView(
    onBack: () -> Unit,
    onInsertKategori: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailKategori: (String) -> Unit = {},
    viewModel: HomeKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomeTopAppBar(
                title = DestinasiHomeKategori.titleRes,
                canNavigateBack = true,
                navigateUp = onBack,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getKategori()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onInsertKategori,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Kategori")
            }
        },
    ){innerPadding ->
        HomeKategoriStatus(
            homeKategoriUiState = viewModel.kategoriUIState,
            retryAction = {viewModel.getKategori()},
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailKategori, onDeleteClick = {
                viewModel.deleteKategori(it.idKategori)
                viewModel.getKategori()
            }
        )
    }
}

@Composable
fun HomeKategoriStatus(
    homeKategoriUiState: StateFlow<HomeKategoriUiState>,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Kategori) -> Unit = {},
    onDetailClick: (String) -> Unit
){
    when(val state = homeKategoriUiState.collectAsState().value){
        is HomeKategoriUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomeKategoriUiState.Success ->
            if(state.kategori.isEmpty()){
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(text = "Tidak Ada Data Kategori")
                }
            } else {
                KategoriLayout(
                    kategori = state.kategori, modifier = modifier.fillMaxWidth(),
                    onDetailClick = onDetailClick,
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeKategoriUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id = R.drawable.eror), contentDescription = "")
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun KategoriLayout(
    kategori: List<Kategori>,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit,
    onDeleteClick: (Kategori) -> Unit = {}
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(kategori) { kategori ->
            KategoriCard(
                kategori = kategori,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {onDetailClick(kategori.idKategori)},
                onDeleteClick = {
                    onDeleteClick(kategori)
                }
            )
        }
    }
}

@Composable
fun KategoriCard(
    kategori: Kategori,
    modifier: Modifier = Modifier,
    onDeleteClick: (Kategori) -> Unit = {}
){
    Card (
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = kategori.namaKategori,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Green
                )
                Spacer(Modifier.weight(1f))
                IconButton(
                    onClick ={onDeleteClick(kategori)},

                ){
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
                Text(
                    text = kategori.idKategori,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Text(
                text = kategori.deskKategori,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}