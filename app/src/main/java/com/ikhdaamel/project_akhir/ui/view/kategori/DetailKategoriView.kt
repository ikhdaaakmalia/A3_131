package com.ikhdaamel.project_akhir.ui.view.kategori

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikhdaamel.project_akhir.model.Kategori
import com.ikhdaamel.project_akhir.ui.customewidget.CustomeTopAppBar
import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.project_akhir.ui.view.pemasok.OnError
import com.ikhdaamel.project_akhir.ui.view.pemasok.OnLoading
import com.ikhdaamel.project_akhir.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.DetailKategoriUiState
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.DetailKategoriViewModel

object DestinasiDetailKategori: DestinasiNavigasi {
    override val route = "detail_kategori"
    override val titleRes = "DETAIL DATA KATEGORI"
    const val idKategori = "idKategori"
    val routesWithArg = "$route/[$idKategori]"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailKategoriView(
    navigateBack: () -> Unit,
    navigateToUpdate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory)
){

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        topBar = {
            CustomeTopAppBar(
                title = DestinasiDetailKategori.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = {
                    viewModel.getKategoriById()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToUpdate,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Kategori"
                )
            }
        }
    ){ innerpadding ->
        DetailKategoriStatus(
            modifier = Modifier.padding(innerpadding),
            detailKategoriUiState = viewModel.kategoriDetailState,
            retryAction = {viewModel.getKategoriById()}
        )
    }
}


@Composable
fun DetailKategoriStatus(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailKategoriUiState: DetailKategoriUiState
){
    when (detailKategoriUiState) {
        is DetailKategoriUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is DetailKategoriUiState.Success -> {
            if (detailKategoriUiState.kategori.idKategori.isEmpty()){
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text("Tidak Ada Data")
                }
            } else {
                ItemDetailKategori(
                    kategori = detailKategoriUiState.kategori,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
        is DetailKategoriUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ItemDetailKategori(
    modifier: Modifier = Modifier,
    kategori: Kategori
){
    Card(
        modifier = Modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            ComponentDetailKategori(title = "Id Kategori", isi = kategori.idKategori)
            ComponentDetailKategori(title = "Nama Kategori", isi = kategori.namaKategori)
            ComponentDetailKategori(title = "Deskripsi Kategori", isi = kategori.deskKategori)
        }
    }
}

@Composable
fun ComponentDetailKategori(
    modifier: Modifier = Modifier,
    title : String,
    isi : String
){
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "$title :",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = isi,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}