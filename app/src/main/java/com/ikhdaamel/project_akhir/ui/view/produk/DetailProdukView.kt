package com.ikhdaamel.project_akhir.ui.view.produk

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikhdaamel.project_akhir.model.Produk
import com.ikhdaamel.project_akhir.ui.customewidget.CustomeTopAppBar
import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.project_akhir.ui.view.pemasok.OnError
import com.ikhdaamel.project_akhir.ui.view.pemasok.OnLoading
import com.ikhdaamel.project_akhir.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.produk.DetailProdukUiState
import com.ikhdaamel.project_akhir.ui.viewmodel.produk.DetailProdukViewModel

object DestinasiDetailProduk: DestinasiNavigasi {
    override val route = "detail_poduk"
    override val titleRes = "DETAIL DATA PRODUK"
    const val idProduk = "idProduk"
    val routesWithArg = "$route/{$idProduk}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProdukView(
    onBack: () -> Unit,
    onKategori: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailProdukViewModel = viewModel(factory = PenyediaViewModel.Factory)
){

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color(0xFF005BAC),
        topBar = {
            CustomeTopAppBar(
                title = DestinasiDetailProduk.titleRes,
                canNavigateBack = true,
                navigateUp = onBack,
                onRefresh = {
                    viewModel.getProdukById()
                }
            )
        },
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                horizontalArrangement = Arrangement.Center
            ){
                FloatingActionButton(
                    containerColor = Color(0xFFFFCC00),
                    onClick = onKategori,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.padding(18.dp)
                ) {
                    Row{
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Kategori"
                        )
                        Text(
                            text = "DATA KATEGORI",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    ){ innerpadding ->
        DetailProdukStatus(
            modifier = Modifier.padding(innerpadding),
            detailProdukUiState = viewModel.produkDetailState,
            retryAction = {viewModel.getProdukById()}
        )
    }
}

@Composable
fun DetailProdukStatus(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailProdukUiState: DetailProdukUiState
){
    when (detailProdukUiState) {
        is DetailProdukUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is DetailProdukUiState.Success -> {
            if (detailProdukUiState.produk.idProduk.isEmpty()){
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text("Tidak Ada Data")
                }
            } else {
                com.ikhdaamel.project_akhir.ui.view.produk.ItemDetailProduk(
                    produk = detailProdukUiState.produk,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
        is DetailProdukUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}


@Composable
fun ItemDetailProduk(
    modifier: Modifier = Modifier,
    produk: Produk
){
    Card(
        modifier = Modifier.padding(16.dp, top = 95.dp, end = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            ComponentDetailProduk(title = "Id Produk", isi = produk.idProduk)
            ComponentDetailProduk(title = "Nama Produk", isi = produk.namaProduk)
            ComponentDetailProduk(title = "Deskripsi Produk", isi = produk.deskProduk)
            ComponentDetailProduk(title = "Harga Produk", isi = produk.harga)
            ComponentDetailProduk(title = "Stok Produk", isi = produk.stok)
            ComponentDetailProduk(title = "Id Kategori", isi = produk.idKategori)
            ComponentDetailProduk(title = "Id Pemasok", isi = produk.idPemasok)
            ComponentDetailProduk(title = "Id Merk", isi = produk.idMerk)
        }
    }
}

@Composable
fun ComponentDetailProduk(
    modifier: Modifier = Modifier,
    title : String,
    isi : String
){
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "$title",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.width(150.dp)
        )
        Text(
            text = ":",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = isi,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}