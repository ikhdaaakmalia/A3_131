package com.ikhdaamel.project_akhir.model

import kotlinx.serialization.Serializable

@Serializable
data class ProdukDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Produk
)

@Serializable
data class ProdukResponse(
    val status: Boolean,
    val message: String,
    val data: List<Produk>
)

@Serializable
data class Produk (
    val idProduk: String,
    val namaProduk: String,
    val deskProduk: String,
    val harga: String,
    val stok: String,
    val idKategori: String,
    val idPemasok: String,
    val idMerk: String
)