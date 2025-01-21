package com.ikhdaamel.project_akhir.ui.view.merk

import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi

object DestinasiUpdateMerk : DestinasiNavigasi {
    override val route = "update_merk"
    override val titleRes = "UPDATE DATA MERK"
    const val idMerk = "idMerk"
    val routesWithArg = "$route/[$idMerk]"
}