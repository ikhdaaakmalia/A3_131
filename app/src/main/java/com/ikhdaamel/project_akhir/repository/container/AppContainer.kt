package com.ikhdaamel.project_akhir.repository.container

import com.ikhdaamel.project_akhir.repository.repository.KategoriRepository
import com.ikhdaamel.project_akhir.repository.repository.MerkRepository
import com.ikhdaamel.project_akhir.repository.repository.PemasokRepository
import com.ikhdaamel.project_akhir.repository.repository.ProdukRepository

interface AppContainer {
    val produkRepository: ProdukRepository?
    val pemasokRepository: PemasokRepository?
    val merkRepository: MerkRepository?
    val kategoriRepository: KategoriRepository?
}
