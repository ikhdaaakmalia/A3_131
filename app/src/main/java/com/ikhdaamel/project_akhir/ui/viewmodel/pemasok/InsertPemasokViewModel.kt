package com.ikhdaamel.project_akhir.ui.viewmodel.pemasok

import com.ikhdaamel.project_akhir.model.Pemasok

fun Pemasok.toInsertProdukUiEvent(): InsertProdukUiEvent = InsertProdukUiEvent(
    idPemasok = idPemasok,
    namaPemasok = namaPemasok,
    alamatPemasok = alamatPemasok,
    telpPemasok = telpPemasok
)