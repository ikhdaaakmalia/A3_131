package com.ikhdaamel.project_akhir.ui.view.merk

import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi

object DestinasiDetailMerk: DestinasiNavigasi {
    override val route = "detail_merk"
    override val titleRes = "DETAIL DATA MERK"
    const val idMerk = "idMerk"
    val routesWithArg = "$route/[$idMerk]"
}