package com.ikhdaamel.project_akhir

import android.app.Application
import com.ikhdaamel.project_akhir.repository.container.AppContainer
import com.ikhdaamel.project_akhir.repository.container.KategoriContainer
import com.ikhdaamel.project_akhir.repository.container.MerkContainer
import com.ikhdaamel.project_akhir.repository.container.PemasokContainer
import com.ikhdaamel.project_akhir.repository.container.ProdukContainer

class ManageApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = object : AppContainer {
            override val produkRepository = ProdukContainer().produkRepository
            override val pemasokRepository = PemasokContainer().pemasokRepository
            override val merkRepository = MerkContainer().merkRepository
            override val kategoriRepository = KategoriContainer().kategoriRepository
        }
    }
}