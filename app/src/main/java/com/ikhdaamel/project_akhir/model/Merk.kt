package com.ikhdaamel.project_akhir.model

import kotlinx.serialization.Serializable

@Serializable
data class Merk(
    val idMerk: String,
    val namaMerk: String,
    val deskMerk: String
)