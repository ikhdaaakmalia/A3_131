package com.ikhdaamel.project_akhir.model

import kotlinx.serialization.Serializable

@Serializable
data class Kategori(
    val idKategori: String,
    val namaKategori: String,
    val deskKategori: String
)