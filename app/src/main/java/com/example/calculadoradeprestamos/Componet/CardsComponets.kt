package com.example.calculadoradeprestamos.Componet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Infocard(title:String, info:Double, modifier: Modifier){
    Card(
        modifier = Modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray
        )
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = title, color = Color.Blue, fontSize = 20.sp)
            Text(text = "GTQ. $info", color = Color.Blue, fontSize = 20.sp)
        }
    }
}
@Composable
fun ShowInfoCards(titleInteres:String, montoInteres: Double, titleMonto: String, monto: Double ){
    Row (){
        Infocard(title = titleInteres, info = montoInteres, modifier = Modifier.padding(30.dp).weight(1f))
        SpaceV(10.dp)
        Infocard(title = titleMonto, info = monto, modifier = Modifier.padding(30.dp).weight(1f))
    }
}