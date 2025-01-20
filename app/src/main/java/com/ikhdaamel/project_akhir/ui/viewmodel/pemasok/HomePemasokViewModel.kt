package com.ikhdaamel.project_akhir.ui.viewmodel.pemasok

import com.ikhdaamel.project_akhir.model.Pemasok

sealed class HomePemasokUiState{
    data class Success(val pemasok: List<Pemasok>) : HomePemasokUiState()
    object Error: HomePemasokUiState()
    object Loading: HomePemasokUiState()
}