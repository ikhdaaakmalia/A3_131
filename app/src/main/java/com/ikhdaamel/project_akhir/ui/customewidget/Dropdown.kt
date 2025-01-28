package com.ikhdaamel.project_akhir.ui.customewidget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.HomeKategoriUiState
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.HomeKategoriViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.merk.HomeMerkUiState
import com.ikhdaamel.project_akhir.ui.viewmodel.merk.HomeMerkViewModel
import com.ikhdaamel.project_akhir.ui.viewmodel.pemasok.HomePemasokUiState
import com.ikhdaamel.project_akhir.ui.viewmodel.pemasok.HomePemasokViewModel

object Choice {
    @Composable
    fun pemasokChoice(
        pemasokViewModel : HomePemasokViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String> {
        val dataPemasok by pemasokViewModel.pemasokUIState.collectAsState()

        return if (dataPemasok is HomePemasokUiState.Success) {
            val pemasokList = (dataPemasok as HomePemasokUiState.Success).pemasok
            pemasokList.map { it.namaPemasok + " | " + it.idPemasok }
        } else {
            emptyList()
        }
    }

    @Composable
    fun merkChoice(
        merkViewModel : HomeMerkViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String> {
        val dataMerk by merkViewModel.merkUIState.collectAsState()

        return if (dataMerk is HomeMerkUiState.Success) {
            val merkList = (dataMerk as HomeMerkUiState.Success).merk
            merkList.map { it.namaMerk + " | " + it.idMerk }
        } else {
            emptyList()
        }
    }

    @Composable
    fun kategoriChoice(
        kategoriViewModel : HomeKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String> {
        val dataKategori by kategoriViewModel.kategoriUIState.collectAsState()

        return if (dataKategori is HomeKategoriUiState.Success) {
            val kategoriList = (dataKategori as HomeKategoriUiState.Success).kategori
            kategoriList.map { it.namaKategori + " | " + it.idKategori }
        } else {
            emptyList()
        }
    }
}

@Composable
fun DropdownInsertWithId(
    label: String,
    options: List<String>,
    selectedOptionId: String?,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedOptionName = options.find { it.contains(selectedOptionId ?: "") }?.split(" | ")?.first() ?: ""

    Box {
        OutlinedTextField(
            value = selectedOptionName,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                val (name, id) = option.split(" | ")
                DropdownMenuItem(
                    text = { Text(name) },
                    onClick = {
                        onOptionSelected(id)
                        expanded = false
                    }
                )
            }
        }
    }
}
