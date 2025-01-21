package com.ikhdaamel.project_akhir.ui.view.produk

import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi

object DestinasiUpdateProduk : DestinasiNavigasi {
    override val route = "update_produk"
    override val titleRes = "UPDATE DATA PRODUK"
    const val idProduk = "idProduk"
    val routesWithArg = "$route/[$idProduk]"
}