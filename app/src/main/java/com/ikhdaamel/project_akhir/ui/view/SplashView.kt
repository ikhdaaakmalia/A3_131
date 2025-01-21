package com.ikhdaamel.project_akhir.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ikhdaamel.project_akhir.R
import com.ikhdaamel.project_akhir.ui.navigation.DestinasiNavigasi

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "HOME"
}
//@Preview(showBackground = true)
@Composable
fun SplashView(
    onProduk:() -> Unit,
    onPemasok:() -> Unit,
    onMerk:() -> Unit,
    onKategori:() -> Unit,
    onMulai:() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(
                    id = R.color.purple_200
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "A3 APLIKASI MANAJEMEN",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Image(
            painter = painterResource(
                id = R.drawable.gambar
            ),
            contentDescription = "",
            modifier = Modifier.size(350.dp)
        )
        Row {
            Button(
                onClick = { onProduk() },
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp),
            ) {
                Text(text = "PRODUK")
            }
            Button(
                onClick = { onPemasok() },
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp),
            ) {
                Text(text = "PEMASOK")
            }
        }
        Spacer(modifier = Modifier.padding(2.dp))
        Row {
            Button(
                onClick = { onMerk() },
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp),
            ) {
                Text(text = "MERK")
            }
            Button(
                onClick = { onKategori() },
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp),
            ) {
                Text(text = "KATEGORI")
            }
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Button(
            onClick = { onMulai() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
        ) {
            Text(text = "LIHAT SELENGKAPNYA")
        }
    }
}