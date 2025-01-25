package com.ikhdaamel.project_akhir.model

import kotlinx.serialization.Serializable

@Serializable
data class PemasokDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Pemasok
)

@Serializable
data class PemasokResponse(
    val status: Boolean,
    val message: String,
    val data: List<Pemasok>
)

@Serializable
data class Pemasok(
    val idPemasok: String,
    val namaPemasok: String,
    val alamatPemasok: String,
    val telpPemasok: String
)