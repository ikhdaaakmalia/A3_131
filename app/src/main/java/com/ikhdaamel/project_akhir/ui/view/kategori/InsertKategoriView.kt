package com.ikhdaamel.project_akhir.ui.view.kategori

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ikhdaamel.project_akhir.ui.viewmodel.kategori.InsertKategoriUiEvent

@Preview(showBackground = true)
@Composable
fun FormInputKategori(
    insertKategoriUiEvent: InsertKategoriUiEvent = InsertKategoriUiEvent(),
    modifier: Modifier = Modifier,
    onValueChange: (InsertKategorikUiEvent) -> Unit ={},
    enabled: Boolean = true
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Masukkan Data Kategori",
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = insertKategoriUiEvent.idKategori,
            onValueChange = {onValueChange(insertKategoriUiEvent.copy(idKategori = it))},
            label = { Text("Id Kategori") },
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
            value = insertKategoriUiEvent.namaKategori,
            onValueChange = {onValueChange(insertKategoriUiEvent.copy(namaKategori = it))},
            label = { Text("Nama Kategori") },
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
            value = insertKategoriUiEvent.deskKategori,
            onValueChange = {onValueChange(insertKategoriUiEvent.copy(deskKategori = it))},
            label = { Text("Deskripsi Kategori") },
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