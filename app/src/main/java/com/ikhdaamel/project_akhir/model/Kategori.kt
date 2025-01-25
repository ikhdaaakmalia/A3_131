package com.ikhdaamel.project_akhir.model

import kotlinx.serialization.Serializable

@Serializable
data class KategoriDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Kategori
)

@Serializable
data class KategoriResponse(
    val status: Boolean,
    val message: String,
    val data: List<Kategori>
)

@Serializable
data class Kategori(
    val idKategori: String,
    val namaKategori: String,
    val deskKategori: String
)