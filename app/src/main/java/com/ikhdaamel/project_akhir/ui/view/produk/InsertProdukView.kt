package com.ikhdaamel.project_akhir.ui.view.produk

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikhdaamel.project_akhir.ui.customewidget.Choice.kategoriChoice
import com.ikhdaamel.project_akhir.ui.customewidget.Choice.merkChoice
import com.ikhdaamel.project_akhir.ui.customewidget.Choice.pemasokChoice
import com.ikhdaamel.project_akhir.ui.customewidget.CustomTopAppBar
import com.ikhdaamel.project_akhir.ui.customewidget.DropdownInsertWithId
import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.project_akhir.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.HomeKategoriViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.merk.HomeMerkViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.pemasok.HomePemasokViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.produk.InsertProdukUiEvent
import com.ikhdaamel.project_akhir.ui.viewmodel.produk.InsertProdukUiState
import com.ikhdaamel.project_akhir.ui.viewmodel.produk.InsertProdukViewModel
import kotlinx.coroutines.launch

object DestinasiInsertProduk : DestinasiNavigasi {
    override val route = "input_produk"
    override val titleRes = "INSERT PRODUK"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputProdukView(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertProdukViewModel = viewModel(factory = PenyediaViewModel.Factory),
    kategoriViewModel : HomeKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory),
    merkViewModel : HomeMerkViewModel = viewModel(factory = PenyediaViewModel.Factory),
    pemasokViewModel : HomePemasokViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val kategoriList = kategoriChoice(kategoriViewModel)
    val merkList = merkChoice(merkViewModel)
    val pemasokList = pemasokChoice(pemasokViewModel)

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color(0xFF005BAC),
        topBar = {
            CustomTopAppBar(
                title = DestinasiInsertProduk.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack
            )
        }
    ) { innerPadding ->
        InputProdukBody(
            insertProdukUiState = viewModel.produkUiState,
            pemasokList = pemasokList,
            kategoriList = kategoriList,
            merkList = merkList,
            onProdukValueChange = viewModel::updateInsertProdukState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertProduk()
                    onBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun InputProdukBody(
    insertProdukUiState: InsertProdukUiState,
    kategoriList: List<String>,
    pemasokList: List<String>,
    merkList: List<String>,
    onProdukValueChange: (InsertProdukUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputProduk(
            insertProdukUiEvent = insertProdukUiState.insertProdukUiEvent,
            onValueChange = onProdukValueChange,
            modifier = Modifier.fillMaxWidth(),
            pemasokList = pemasokList,
            kategoriList = kategoriList,
            merkList = merkList
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
fun FormInputProduk(
    insertProdukUiEvent: InsertProdukUiEvent = InsertProdukUiEvent(),
    modifier: Modifier = Modifier,
    kategoriList: List<String>,
    pemasokList: List<String>,
    merkList: List<String>,
    onValueChange: InsertProdukUiEvent.() -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = insertProdukUiEvent.idProduk,
            onValueChange = { onValueChange(insertProdukUiEvent.copy(idProduk = it)) },
            label = { Text("Id Produk", color = Color.White) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = null,
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
            value = insertProdukUiEvent.namaProduk,
            onValueChange = { onValueChange(insertProdukUiEvent.copy(namaProduk = it)) },
            label = { Text("Nama Produk", color = Color.White) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = null,
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
            value = insertProdukUiEvent.deskProduk,
            onValueChange = { onValueChange(insertProdukUiEvent.copy(deskProduk = it)) },
            label = { Text("Deskripsi Produk", color = Color.White) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null,
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
            value = insertProdukUiEvent.harga,
            onValueChange = { onValueChange(insertProdukUiEvent.copy(harga = it)) },
            label = { Text("Harga Produk", color = Color.White) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
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
            value = insertProdukUiEvent.stok,
            onValueChange = { onValueChange(insertProdukUiEvent.copy(stok = it)) },
            label = { Text("Jumlah Stok Produk", color = Color.White) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = null,
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
        DropdownInsertWithId(
            label = "Pilih Pemasok",
            options = pemasokList,
            selectedOptionId = insertProdukUiEvent.idPemasok,
            onOptionSelected = { selectedId ->
                onValueChange(insertProdukUiEvent.copy(idPemasok = selectedId))
            }
        )
        DropdownInsertWithId(
            label = "Pilih Kategori",
            options = kategoriList,
            selectedOptionId = insertProdukUiEvent.idKategori,
            onOptionSelected = { selectedId ->
                onValueChange(insertProdukUiEvent.copy(idKategori = selectedId))
            }
        )
        DropdownInsertWithId(
            label = "Pilih Merk",
            options = merkList,
            selectedOptionId = insertProdukUiEvent.idMerk,
            onOptionSelected = { selectedId ->
                onValueChange(insertProdukUiEvent.copy(idMerk = selectedId))
            }
        )
    }
}
