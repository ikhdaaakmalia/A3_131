package com.ikhdaamel.project_akhir.ui.view.kategori

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikhdaamel.project_akhir.ui.customewidget.CustomeTopAppBar
import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.project_akhir.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.InsertKategoriUiEvent
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.InsertKategoriUiState
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.InsertKategoriViewModel
import kotlinx.coroutines.launch

object DestinasiInsertKategori: DestinasiNavigasi {
    override val route = "input_kategori"
    override val titleRes = "INSERT KATEGORI"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputKategoriView(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color(0xFF005BAC),
        topBar = {
            CustomeTopAppBar(
                title = DestinasiInsertKategori.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack
            )
        }
    ){ innerpadding ->
        InputKategoriBody(
            insertKategoriUiState = viewModel.kategoriUiState,
            onKategoriValueChange = viewModel::updateInsertKategoriState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertKategori()
                    onBack()
                }
            },
            modifier = Modifier
                .padding(innerpadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun InputKategoriBody(
    insertKategoriUiState: InsertKategoriUiState,
    onKategoriValueChange:(InsertKategoriUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputKategori(
            insertKategoriUiEvent = insertKategoriUiState.insertKategoriUiEvent,
            onValueChange = onKategoriValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "SIMPAN", color = Color.White)
        }
    }
}

@Composable
fun FormInputKategori(
    insertKategoriUiEvent: InsertKategoriUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertKategoriUiEvent) -> Unit ={},
    enabled: Boolean = true
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(text = "Masukkan Data Kategori",
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp,
            color = Color.White
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = insertKategoriUiEvent.idKategori,
            onValueChange = {onValueChange(insertKategoriUiEvent.copy(idKategori = it))},
            label = { Text("Id Kategori", color = Color.White) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "",
                    tint = Color.White
                )
            },
            enabled = enabled,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(50.dp)
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = insertKategoriUiEvent.namaKategori,
            onValueChange = {onValueChange(insertKategoriUiEvent.copy(namaKategori = it))},
            label = { Text("Nama Kategori", color = Color.White) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "",
                    tint = Color.White
                )
            },
            enabled = enabled,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(50.dp)
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = insertKategoriUiEvent.deskKategori,
            onValueChange = {onValueChange(insertKategoriUiEvent.copy(deskKategori = it))},
            label = { Text("Deskripsi Kategori", color = Color.White) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "",
                    tint = Color.White
                )
            },
            enabled = enabled,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(50.dp)
        )
        //peringatan jika ada yg belum diisi
        if (enabled) {
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp),
                color = Color.White
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}
