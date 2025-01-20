package com.ikhdaamel.project_akhir.ui.viewmodel.merk

import com.ikhdaamel.project_akhir.model.Merk

data class InsertMerkUiState(
    val insertMerkUiEvent: InsertMerkUiEvent = InsertMerkUiEvent()
)

data class InsertMerkUiEvent(
    val idMerk: String = "",
    val namaMerk: String = "",
    val deskMerk: String = ""
)

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