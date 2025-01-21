package com.ikhdaamel.project_akhir.ui.view.pemasok

import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi

object DestinasiDetailPemasok: DestinasiNavigasi {
    override val route = "detail_pemasok"
    override val titleRes = "DETAIL DATA PEMASOK"
    const val idPemasok = "idPemasok"
    val routesWithArg = "$route/[$idPemasok]"
}