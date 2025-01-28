package com.ikhdaamel.project_akhir.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ikhdaamel.project_akhir.ui.view.DestinasiHome
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
//import com.ikhdaamel.project_akhir.ui.view.SplashView
import com.ikhdaamel.project_akhir.ui.view.kategori.DestinasiDetailKategori
import com.ikhdaamel.project_akhir.ui.view.kategori.DestinasiHomeKategori
import com.ikhdaamel.project_akhir.ui.view.kategori.DestinasiInsertKategori
import com.ikhdaamel.project_akhir.ui.view.kategori.DestinasiUpdateKategori
import com.ikhdaamel.project_akhir.ui.view.kategori.DetailKategoriView
import com.ikhdaamel.project_akhir.ui.view.kategori.HomeKategoriView
import com.ikhdaamel.project_akhir.ui.view.kategori.InputKategoriView
import com.ikhdaamel.project_akhir.ui.view.kategori.UpdateKategoriView
import com.ikhdaamel.project_akhir.ui.view.merk.DestinasiDetailMerk
import com.ikhdaamel.project_akhir.ui.view.merk.DestinasiHomeMerk
import com.ikhdaamel.project_akhir.ui.view.merk.DestinasiInsertMerk
import com.ikhdaamel.project_akhir.ui.view.merk.DestinasiUpdateMerk
import com.ikhdaamel.project_akhir.ui.view.merk.DetailMerkView
import com.ikhdaamel.project_akhir.ui.view.merk.HomeMerkView
import com.ikhdaamel.project_akhir.ui.view.merk.InputMerkView
import com.ikhdaamel.project_akhir.ui.view.merk.UpdateMerkView
import com.ikhdaamel.project_akhir.ui.view.pemasok.DestinasiDetailPemasok
import com.ikhdaamel.project_akhir.ui.view.pemasok.DestinasiDetailPemasok.idPemasok
import com.ikhdaamel.project_akhir.ui.view.pemasok.DestinasiHomePemasok
import com.ikhdaamel.project_akhir.ui.view.pemasok.DestinasiInsertPemasok
import com.ikhdaamel.project_akhir.ui.view.pemasok.DestinasiUpdatePemasok
import com.ikhdaamel.project_akhir.ui.view.pemasok.DetailPemasokView
import com.ikhdaamel.project_akhir.ui.view.pemasok.HomePemasokView
import com.ikhdaamel.project_akhir.ui.view.pemasok.InputPemasokView
import com.ikhdaamel.project_akhir.ui.view.pemasok.UpdatePemasokView
import com.ikhdaamel.project_akhir.ui.view.produk.DestinasiDetailProduk
import com.ikhdaamel.project_akhir.ui.view.produk.DestinasiHomeProduk
import com.ikhdaamel.project_akhir.ui.view.produk.DestinasiInsertProduk
import com.ikhdaamel.project_akhir.ui.view.produk.DestinasiUpdateProduk
import com.ikhdaamel.project_akhir.ui.view.produk.DetailProdukView
import com.ikhdaamel.project_akhir.ui.view.produk.HomeProdukView
import com.ikhdaamel.project_akhir.ui.view.produk.InputProdukView
import com.ikhdaamel.project_akhir.ui.view.produk.UpdateProdukView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeProduk.route,
        modifier = Modifier
    ) {
//        composable(DestinasiHome.route) {
//            SplashView(
//                onProduk = {navController.navigate(DestinasiHomeProduk.route)},
//                onPemasok = { navController.navigate(DestinasiHomePemasok.route) },
//                onMerk = { navController.navigate(DestinasiHomeMerk.route) },
//                onMulai = {},
//                onKategori = { navController.navigate(DestinasiHomeKategori.route) }
//            )
//        }
        //produk
        composable(DestinasiHomeProduk.route) {
            HomeProdukView(
                onInsertProduk = { navController.navigate(DestinasiInsertProduk.route) },
                onPemasok = { navController.navigate(DestinasiHomePemasok.route) },
                onMerk = { navController.navigate(DestinasiHomeMerk.route) },
                onDetailProduk = { idProduk ->
                    navController.navigate("${DestinasiDetailProduk.route}/$idProduk") },
                onUpdateProduk = { idProduk ->
                    navController.navigate("${DestinasiUpdateProduk.route}/$idProduk")
                }
            )
        }
        composable(DestinasiInsertProduk.route){
            InputProdukView(
                onBack = {navController.navigate(DestinasiHomeProduk.route)}
            )
        }
        composable(DestinasiDetailProduk.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailProduk.idProduk){
                    type = NavType.StringType
                }
            )
        ){
            val idProduk = it.arguments?.getString(DestinasiDetailProduk.idProduk)
            idProduk?.let { idProduk ->
                DetailProdukView(
                    onKategori = { navController.navigate(DestinasiHomeKategori.route) },
                    onBack = {
                        navController.navigate(DestinasiHomeProduk.route){
                            popUpTo(DestinasiHomeProduk.route){
                                inclusive = true }
                        }
                    }
                )
            }
        }
        composable(
            DestinasiUpdateProduk.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateProduk.idProduk){
                    type = NavType.StringType
                }
            )
        ){
            UpdateProdukView(
                onBack = {navController.popBackStack()},
                onNavigate = {navController.popBackStack()}
            )
        }
        //pemasok
        composable(DestinasiHomePemasok.route) {
            HomePemasokView(
                onBack = {navController.popBackStack()},
                onInsertPemasok = { navController.navigate(DestinasiInsertPemasok.route) },
                onDetailPemasok = { idPemasok ->
                    navController.navigate("${DestinasiDetailPemasok.route}/$idPemasok") }
            )
        }
        composable(DestinasiInsertPemasok.route){
            InputPemasokView(
                onBack = {navController.navigate(DestinasiHomePemasok.route)}
            )
        }
        composable(DestinasiDetailPemasok.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailPemasok.idPemasok){
                    type = NavType.StringType
                }
            )
        ){
            val idPemasok = it.arguments?.getString(DestinasiDetailPemasok.idPemasok)
            idPemasok?.let { idPemasok ->
                DetailPemasokView(
                    onUpdatePemasok ={ navController.navigate("${DestinasiUpdatePemasok.route}/$idPemasok") },
                    onBack = {
                        navController.navigate(DestinasiHomePemasok.route){
                            popUpTo(DestinasiHomePemasok.route){
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
        composable(DestinasiUpdatePemasok.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdatePemasok.idPemasok){
                    type = NavType.StringType
                }
            )
        ){
            UpdatePemasokView(
                onBack = {navController.popBackStack()},
                onNavigate = {navController.popBackStack()}
            )
        }

        //merk
        composable(DestinasiHomeMerk.route) {
            HomeMerkView(
                onBack = {navController.popBackStack()},
                onInsertMerk = { navController.navigate(DestinasiInsertMerk.route) },
                onDetailMerk = { idMerk ->
                    navController.navigate("${DestinasiDetailMerk.route}/$idMerk")  }
            )
        }
        composable(DestinasiInsertMerk.route){
            InputMerkView(
                onBack = {navController.navigate(DestinasiHomeMerk.route)}
            )
        }
        composable(DestinasiDetailMerk.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailMerk.idMerk){
                    type = NavType.StringType
                }
            )
        ){
            val idMerk = it.arguments?.getString(DestinasiDetailMerk.idMerk)
            idMerk?.let { idMerk ->
                DetailMerkView(
                    onUpdateMerk ={ navController.navigate("${DestinasiUpdateMerk.route}/$idMerk") },
                    onBack = {
                        navController.navigate(DestinasiHomeMerk.route){
                            popUpTo(DestinasiHomeMerk.route){
                                inclusive = true }
                        }
                    }
                )
            }
        }
        composable(DestinasiUpdateMerk.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateMerk.idMerk){
                    type = NavType.StringType
                }
            )
        ){
            UpdateMerkView(
                onBack = {navController.popBackStack()},
                onNavigate = {navController.popBackStack()}
            )
        }
        //kategori
        composable(DestinasiHomeKategori.route) {
            HomeKategoriView(
                onInsertKategori = { navController.navigate(DestinasiInsertKategori.route) },
                onBack = { navController.popBackStack() },
                onDetailKategori = {idKategori -> navController.navigate("${DestinasiDetailKategori.route}/$idKategori") }
            )
        }
        composable(DestinasiInsertKategori.route){
            InputKategoriView(
                onBack = {navController.navigate(DestinasiHomeKategori.route)}
            )
        }
        composable(DestinasiDetailKategori.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailKategori.idKategori){
                    type = NavType.StringType
                }
            )
        ){
            val idKategori = it.arguments?.getString(DestinasiDetailKategori.idKategori)
            idKategori?.let { idKategori ->
                DetailKategoriView(
                    onUpdateKategori ={ navController.navigate("${DestinasiUpdateKategori.route}/$idKategori") },
                    onBack = {
                        navController.navigate(DestinasiHomeKategori.route){
                            popUpTo(DestinasiHomeKategori.route){
                                inclusive = true }
                        }
                    }
                )
            }
        }
        composable(
            DestinasiUpdateKategori.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateKategori.idKategori){
                    type = NavType.StringType
                }
            )
        ){
            UpdateKategoriView(
                onBack = {navController.popBackStack()},
                onNavigate = {navController.popBackStack()}
            )
        }
    }
}