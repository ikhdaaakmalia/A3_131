package com.ikhdaamel.project_akhir.model

import kotlinx.serialization.Serializable

@Serializable
data class MerkDetailResponse(
    val status: Boolean,
    val message: String,
    val dataMerk: Merk
)

@Serializable
data class MerkResponse(
    val status: Boolean,
    val message: String,
    val dataMerl: List<Merk>
)

@Serializable
data class Merk(
    val idMerk: String,
    val namaMerk: String,
    val deskMerk: String
)