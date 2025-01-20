package com.ikhdaamel.project_akhir.ui.view

import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi

object DestinasiUpdatePemasok : DestinasiNavigasi {
    override val route = "update pemasok"
    override val titleRes = "UPDATE DATA PEMASOK"
    const val idPemasok = "idPemasok"
    val routesWithArg = "$route/[$idPemasok]"
}