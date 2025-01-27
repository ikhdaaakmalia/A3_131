package com.ikhdaamel.project_akhir.ui.view.merk

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
import com.ikhdaamel.project_akhir.ui.viewmodel.merk.InsertMerkUiEvent
import com.ikhdaamel.project_akhir.ui.viewmodel.merk.InsertMerkUiState
import com.ikhdaamel.project_akhir.ui.viewmodel.merk.InsertMerkViewModel
import kotlinx.coroutines.launch

object DestinasiInsertMerk: DestinasiNavigasi {
    override val route = "input_merk"
    override val titleRes = "INSERT MERK"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputMerkView(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertMerkViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomeTopAppBar(
                title = DestinasiInsertMerk.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack
            )
        }
    ){ innerpadding ->
        InputMerkBody(
            insertMerkUiState = viewModel.merkUiState,
            onMerkValueChange = viewModel::updateInsertMerkState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertMerk()
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
fun InputMerkBody(
    insertMerkUiState: InsertMerkUiState,
    onMerkValueChange:(InsertMerkUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputMerk(
            insertMerkUiEvent = insertMerkUiState.insertMerkUiEvent,
            onValueChange = onMerkValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "SIMPAN")
        }
    }
}

@Composable
fun FormInputMerk(
    insertMerkUiEvent: InsertMerkUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertMerkUiEvent) -> Unit ={},
    enabled: Boolean = true
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Masukkan Data Merk",
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = insertMerkUiEvent.idMerk,
            onValueChange = {onValueChange(insertMerkUiEvent.copy(idMerk = it))},
            label = { Text("Id Merk") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = ""
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
            value = insertMerkUiEvent.namaMerk,
            onValueChange = {onValueChange(insertMerkUiEvent.copy(namaMerk = it))},
            label = { Text("Nama Merk") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = ""
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
            value = insertMerkUiEvent.deskMerk,
            onValueChange = {onValueChange(insertMerkUiEvent.copy(deskMerk = it))},
            label = { Text("Deskripsi Merk") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = ""
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
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}