package com.ikhdaamel.project_akhir.model

import kotlinx.serialization.Serializable

@Serializable
data class Pemasok(
    val idPemasok: String,
    val namaPemasok: String,
    val alamatPemasok: String,
    val teleponPemasok: String
)