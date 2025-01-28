package com.ikhdaamel.project_akhir.ui.view.pemasok

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
import com.ikhdaamel.project_akhir.model.Pemasok
import com.ikhdaamel.project_akhir.ui.customewidget.CustomeTopAppBar
import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.project_akhir.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.pemasok.DetailPemasokUiState
import com.ikhdaamel.project_akhir.ui.viewmodel.pemasok.DetailPemasokViewModel

object DestinasiDetailPemasok: DestinasiNavigasi {
    override val route = "detail_pemasok"
    override val titleRes = "DETAIL DATA PEMASOK"
    const val idPemasok = "idPemasok"
    val routesWithArg = "$route/{$idPemasok}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPemasokView(
    onBack: () -> Unit,
    onUpdatePemasok: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailPemasokViewModel = viewModel(factory = PenyediaViewModel.Factory)
){

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color(0xFF005BAC),
        topBar = {
            CustomeTopAppBar(
                title = DestinasiDetailPemasok.titleRes,
                canNavigateBack = true,
                navigateUp = onBack,
                onRefresh = {
                    viewModel.getPemasokById()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onUpdatePemasok,
                containerColor = Color(0xFFFFCC00),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Pemasok"
                )
            }
        }
    ){ innerpadding ->
        DetailPemasokStatus(
            modifier = Modifier.padding(innerpadding),
            detailPemasokUiState = viewModel.pemasokDetailState,
            retryAction = {viewModel.getPemasokById()}
        )
    }
}


@Composable
fun DetailPemasokStatus(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailPemasokUiState: DetailPemasokUiState
){
    when (detailPemasokUiState) {
        is DetailPemasokUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is DetailPemasokUiState.Success -> {
            if (detailPemasokUiState.pemasok.idPemasok.isEmpty()){
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text("Tidak Ada Data")
                }
            } else {
                ItemDetailPemasok(
                    pemasok = detailPemasokUiState.pemasok,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
        is DetailPemasokUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ItemDetailPemasok(
    modifier: Modifier = Modifier,
    pemasok: Pemasok
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
            ComponentDetailPemasok(title = "Id Pemasok", isi = pemasok.idPemasok)
            Spacer(modifier = Modifier.width(16.dp))
            ComponentDetailPemasok(title = "Nama Pemasok", isi = pemasok.namaPemasok)
            Spacer(modifier = Modifier.width(16.dp))
            ComponentDetailPemasok(title = "Alamat Pemasok", isi = pemasok.alamatPemasok)
            Spacer(modifier = Modifier.width(16.dp))
            ComponentDetailPemasok(title = "No Telepon Pemasok", isi = pemasok.telpPemasok)
        }
    }
}

@Composable
fun ComponentDetailPemasok(
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
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = isi,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}