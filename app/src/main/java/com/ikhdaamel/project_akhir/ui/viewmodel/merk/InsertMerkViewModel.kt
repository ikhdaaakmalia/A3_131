package com.ikhdaamel.project_akhir.ui.viewmodel.merk

import com.ikhdaamel.project_akhir.model.Merk

fun InsertMerkUiEvent.toMerk(): Merk = Merk(
    idMerk = idMerk,
    namaMerk = namaMerk,
    deskMerk = deskMerk
)

fun Merk.toUiStateMerk(): InsertMerkUiState = InsertMerkUiState(
    insertMerkUiEvent = toInsertMerkUiEvent()
)

fun Merk.toInsertMerkUiEvent(): InsertMerkUiEvent = InsertMerkUiEvent(
    idMerk = idMerk,
    namaMerk = namaMerk,
    deskMerk = deskMerk
)