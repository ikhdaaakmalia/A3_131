package com.ikhdaamel.project_akhir.ui.viewmodel.pemasok

import com.ikhdaamel.project_akhir.model.Pemasok

data class InsertPemasokUiState(
    val insertPemasokUiEvent : InsertPemasokUiEvent = InsertPemasokUiEvent()
)

data class InsertPemasokUiEvent(
    val idPemasok : String = "",
    val namaPemasok : String = "",
    val alamatPemasok : String = "",
    val telpPemasok : String = ""
)

fun InsertPemasokUiEvent.toPemasok(): Pemasok = Pemasok(
    idPemasok = idPemasok,
    namaPemasok = namaPemasok,
    alamatPemasok = alamatPemasok,
    telpPemasok = telpPemasok
)

fun Pemasok.toUiStatePemasok(): InsertPemasokUiState = InsertPemasokUiState(
    insertPemasokUiEvent = toInsertPemasokUiEvent()
)

fun Pemasok.toInsertPemasokUiEvent(): InsertPemasokUiEvent = InsertPemasokUiEvent(
    idPemasok = idPemasok,
    namaPemasok = namaPemasok,
    alamatPemasok = alamatPemasok,
    telpPemasok = telpPemasok
)