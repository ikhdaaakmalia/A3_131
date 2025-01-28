package com.ikhdaamel.project_akhir.ui.view.pemasok

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
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
import com.ikhdaamel.project_akhir.ui.customewidget.CustomTopAppBar
import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.project_akhir.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.pemasok.InsertPemasokUiEvent
import com.ikhdaamel.project_akhir.ui.viewmodel.pemasok.InsertPemasokUiState
import com.ikhdaamel.project_akhir.ui.viewmodel.pemasok.InsertPemasokViewModel
import kotlinx.coroutines.launch

object DestinasiInsertPemasok: DestinasiNavigasi {
    override val route = "input_pemasok"
    override val titleRes = "INSERT PEMASOK"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputPemasokView(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertPemasokViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color(0xFF005BAC),
        topBar = {
            CustomTopAppBar(
                title = DestinasiInsertPemasok.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack
            )
        }
    ){ innerpadding ->
        InputPemasokBody(
            insertPemasokUiState = viewModel.pemasokUiState,
            onPemasokValueChange = viewModel::updateInsertPemasokState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertPemasok()
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
fun InputPemasokBody(
    insertPemasokUiState: InsertPemasokUiState,
    onPemasokValueChange:(InsertPemasokUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputPemasok(
            insertPemasokUiEvent = insertPemasokUiState.insertPemasokUiEvent,
            onValueChange = onPemasokValueChange,
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
fun FormInputPemasok(
    insertPemasokUiEvent: InsertPemasokUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertPemasokUiEvent) -> Unit ={},
    enabled: Boolean = true
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Masukkan Data Pemasok",
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp,
            color = Color.White
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = insertPemasokUiEvent.idPemasok,
            onValueChange = {onValueChange(insertPemasokUiEvent.copy(idPemasok = it))},
            label = { Text("Id Pemasok", color = Color.White) },
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
            value = insertPemasokUiEvent.namaPemasok,
            onValueChange = {onValueChange(insertPemasokUiEvent.copy(namaPemasok = it))},
            label = { Text("Nama Pemasok", color = Color.White) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
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
            value = insertPemasokUiEvent.alamatPemasok,
            onValueChange = {onValueChange(insertPemasokUiEvent.copy(alamatPemasok = it))},
            label = { Text("Alamat Pemasok", color = Color.White) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Home,
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
            value = insertPemasokUiEvent.telpPemasok,
            onValueChange = {onValueChange(insertPemasokUiEvent.copy(telpPemasok = it))},
            label = { Text("No Telepon Pemasok", color = Color.White) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Phone,
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