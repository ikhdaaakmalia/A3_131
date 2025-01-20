package com.ikhdaamel.project_akhir.ui.view.kategori

import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi

object DestinasiDetailKategori: DestinasiNavigasi {
    override val route = "detail kategori"
    override val titleRes = "DETAIL DATA KATEGORI"
    const val idKategori = "idKategori"
    val routesWithArg = "$route/[$idKategori]"
}