package com.ikhdaamel.project_akhir.ui.viewmodel.merk

import com.ikhdaamel.project_akhir.model.Merk
import com.ikhdaamel.project_akhir.model.Pemasok

sealed class HomeMerkUiState{
    data class Success(val merk: List<Merk>) : HomeMerkUiState()
    object Error: HomeMerkUiState()
    object Loading: HomeMerkUiState()
}