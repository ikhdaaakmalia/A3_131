package com.ikhdaamel.project_akhir.ui.view.merk

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikhdaamel.project_akhir.model.Merk
import com.ikhdaamel.project_akhir.ui.customewidget.CustomeTopAppBar
import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.project_akhir.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.merk.DetailMerkUiState
import com.ikhdaamel.project_akhir.ui.viewmodel.merk.DetailMerkViewModel

object DestinasiDetailMerk: DestinasiNavigasi {
    override val route = "detail_merk"
    override val titleRes = "DETAIL DATA MERK"
    const val idMerk = "idMerk"
    val routesWithArg = "$route/{$idMerk}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMerkView(
    onBack: () -> Unit,
    onUpdateMerk: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailMerkViewModel = viewModel(factory = PenyediaViewModel.Factory)
){

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        topBar = {
            CustomeTopAppBar(
                title = DestinasiDetailMerk.titleRes,
                canNavigateBack = true,
                navigateUp = onBack,
                onRefresh = {
                    viewModel.getMerkById()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onUpdateMerk,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Merk"
                )
            }
        }
    ){ innerpadding ->
        DetailMerkStatus(
            modifier = Modifier.padding(innerpadding),
            detailMerkUiState = viewModel.merkDetailState,
            retryAction = {viewModel.getMerkById()}
        )
    }
}


@Composable
fun DetailMerkStatus(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailMerkUiState: DetailMerkUiState
){
    when (detailMerkUiState) {
        is DetailMerkUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is DetailMerkUiState.Success -> {
            if (detailMerkUiState.merk.idMerk.isEmpty()){
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text("Tidak Ada Data")
                }
            } else {
                ItemDetailMerk(
                    merk = detailMerkUiState.merk,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
        is DetailMerkUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ItemDetailMerk(
    modifier: Modifier = Modifier,
    merk: Merk
){
    Card(
        modifier = Modifier.padding(16.dp, top = 95.dp, end = 16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            ComponentDetailMerk(title = "Id Merk", isi = merk.idMerk)
            ComponentDetailMerk(title = "Nama Merk", isi = merk.namaMerk)
            ComponentDetailMerk(title = "Deskripsi Merk", isi = merk.deskMerk)
        }
    }
}

@Composable
fun ComponentDetailMerk(
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