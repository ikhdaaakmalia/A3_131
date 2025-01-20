package com.ikhdaamel.project_akhir.ui.viewmodel.pemasok

import com.ikhdaamel.project_akhir.model.Pemasok

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