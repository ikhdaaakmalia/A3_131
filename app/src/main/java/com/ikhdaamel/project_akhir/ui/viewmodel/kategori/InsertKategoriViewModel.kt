package com.ikhdaamel.project_akhir.ui.viewmodel.kategori

import com.ikhdaamel.project_akhir.model.Kategori

data class InsertKategoriUiState(
    val insertKategoriUiEvent: InsertKategoriUiEvent = InsertKategoriUiEvent()
)

data class InsertKategoriUiEvent(
    val idKategori: String = "",
    val namaKategori: String = "",
    val deskKategori: String = ""
)

fun InsertKategoriUiEvent.toKategori() : Kategori = Kategori(
    idKategori = idKategori,
    namaKategori = namaKategori,
    deskKategori = deskKategori
)

fun Kategori.UiStateKategori(): InsertKategoriUiState = InsertKategoriUiState(
    insertKategoriUiEvent = toInsertKategoriUiEvent()
)

fun Kategori.toInsertKategoriUiEvent(): InsertKategoriUiEvent = InsertKategoriUiEvent(
    idKategori = idKategori,
    namaKategori = namaKategori,
    deskKategori = deskKategori
)