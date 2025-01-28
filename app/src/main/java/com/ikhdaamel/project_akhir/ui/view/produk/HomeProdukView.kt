package com.ikhdaamel.project_akhir.ui.view.produk

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
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
import com.ikhdaamel.project_akhir.model.Produk
import com.ikhdaamel.project_akhir.ui.customewidget.CustomeTopAppBar
import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.project_akhir.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.produk.HomeProdukUiState
import com.ikhdaamel.project_akhir.ui.viewmodel.produk.HomeProdukViewModel
import kotlinx.coroutines.flow.StateFlow

object DestinasiHomeProduk : DestinasiNavigasi {
    override val route = "home_produk"
    override val titleRes = ""
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeProdukView(
    onInsertProduk: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailProduk: (String) -> Unit = {},
    onPemasok: () -> Unit,
    onUpdateProduk: (String) -> Unit,
    onMerk: () -> Unit,
    viewModel: HomeProdukViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color(0xFF005BAC),
        topBar = {
            CustomeTopAppBar(
                title = DestinasiHomeProduk.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = { viewModel.getProduk() },
                modifier = Modifier,
                navigateUp = {},
                showLogo = true
            )
        },
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                FloatingActionButton(
                    onClick = onPemasok,
                    containerColor = Color(0xFFFFCC00), // Kuning khas Indomaret
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "Pemasok")
                }
                FloatingActionButton(
                    onClick = onMerk,
                    containerColor = Color(0xFFFFCC00),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Merk")
                }
                FloatingActionButton(
                    onClick = onInsertProduk,
                    containerColor = Color(0xFFFFCC00),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Produk")
                }
            }
        },
    ) { innerPadding ->
        HomeProdukStatus(
            homeProdukUiState = viewModel.produkUIState,
            retryAction = { viewModel.getProduk() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailProduk,
            onDeleteClick = {
                viewModel.deleteProduk(it.idProduk)
                viewModel.getProduk()
            },
            onUpdateProduk = onUpdateProduk
        )
    }
}

@Composable
fun HomeProdukStatus(
    homeProdukUiState: StateFlow<HomeProdukUiState>,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Produk) -> Unit = {},
    onDetailClick: (String) -> Unit,
    onUpdateProduk: (String) -> Unit = {}
) {
    when (val state = homeProdukUiState.collectAsState().value) {
        is HomeProdukUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomeProdukUiState.Success ->
            if (state.produk.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak Ada Data Produk")
                }
            } else {
                LazyColumn(
                    modifier = modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.produk) { produk ->
                        ProdukCard(
                            produk = produk,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onDetailClick(produk.idProduk) },
                            onDeleteClick = { onDeleteClick(produk) },
                            onUpdateProduk = { onUpdateProduk(produk.idProduk) }
                        )
                    }
                }
            }
        is HomeProdukUiState.Error -> OnError(
            retryAction = retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun ProdukCard(
    produk: Produk,
    modifier: Modifier = Modifier,
    onDeleteClick: (Produk) -> Unit = {},
    onUpdateProduk: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.White),
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
                    text = produk.namaProduk,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF005BAC)
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = onUpdateProduk) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = Color(0xFF005BAC)
                    )
                }
                IconButton(onClick = { onDeleteClick(produk) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
            Text(text = produk.deskProduk, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Harga: Rp ${produk.harga}", color = Color(0xFFFFCC00))
            Text(text = "Stok: ${produk.stok}", color = Color(0xFF005BAC))
        }
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
        Image(painter = painterResource(id = R.drawable.eror), contentDescription = null)
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}
