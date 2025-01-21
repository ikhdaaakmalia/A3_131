package com.ikhdaamel.project_akhir.ui.view.kategori

import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi

object DestinasiUpdateKategori : DestinasiNavigasi {
    override val route = "update_kategori"
    override val titleRes = "UPDATE DATA KATEGORI"
    const val idKategori = "idKategori"
    val routesWithArg = "$route/[$idKategori]"
}