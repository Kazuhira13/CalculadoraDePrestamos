package com.example.calculadoradeprestamos.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calculadoradeprestamos.Componet.Alert
import com.example.calculadoradeprestamos.Componet.MainButton
import com.example.calculadoradeprestamos.Componet.MainTextField
import com.example.calculadoradeprestamos.Componet.ShowInfoCards
import com.example.calculadoradeprestamos.Componet.SpaceH
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homeview(){
    Scaffold (topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "calculadora de cuotas")},
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            ))
    }){
        ContentHomeView(it)
    }
}


@Composable
fun ContentHomeView(paddingValues: PaddingValues){
    var montoPrestamo by remember { mutableStateOf("") }
    var cantCuotas by remember { mutableStateOf("") }
    var tasa by remember { mutableStateOf("") }
    var montoIntereses by remember { mutableStateOf(0.0) }
    var montoCuota by remember { mutableStateOf(0.0) }

    var showAlert by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .padding(paddingValues)
        .padding(10.dp)
        .fillMaxSize(),
        horizontalAlignment =  Alignment.CenterHorizontally) {
        ShowInfoCards(
            titleInteres = "Intereses",
            montoInteres = montoIntereses,
            titleMonto = "Monto",
            monto = montoCuota
        )

        MainTextField(value = montoPrestamo, onValueChange = {montoPrestamo = it},   label = "prestamo")
        SpaceH()
        MainTextField(value = cantCuotas, onValueChange = {cantCuotas = it}, label = "cuotas")
        SpaceH(10.dp)
        MainTextField(value = tasa, onValueChange = {tasa = it}, label = "tasa")
        SpaceH(20.dp)
        MainButton(text = "Calcular"){
            if (montoPrestamo != "" && cantCuotas != ""){
                montoIntereses = calcularTotal(montoPrestamo.toDouble(),cantCuotas.toInt(),tasa.toDouble())
                montoCuota = CalcularCuota(montoPrestamo.toDouble(),cantCuotas.toInt(),tasa.toDouble())
            }else{
                showAlert = true
            }
        }
        SpaceH()
        MainButton(text = "borrar", color = Color.Red) {
            montoPrestamo = ""
            cantCuotas = ""
            tasa = ""
            montoIntereses = 0.0
            montoCuota = 0.0
        }
        if (showAlert){
            Alert(
                title = "alerta",
                message = "porfavor ingrese los datos",
                confirmeText = "aceptar",
                onConfirmClick = {
                    showAlert = false
                }
            ) { }
        }
    }
}

fun calcularTotal(monto: Double, cuotas: Int,tasa: Double):Double {
    val res = cuotas * CalcularCuota(monto,cuotas,tasa)
    return BigDecimal(res).setScale(2,BigDecimal.ROUND_UP).toDouble()
}

fun CalcularCuota(monto: Double, cuotas: Int, tasa: Double):Double{
    val tasaInteresMensual = tasa/12/100
    val cuotas = monto * tasaInteresMensual * Math.pow(1+tasaInteresMensual, cuotas.toDouble())/
            (Math.pow(1+tasaInteresMensual, cuotas.toDouble())-1)

    val cuotaRedondeada = BigDecimal(cuotas).setScale(2,BigDecimal.ROUND_UP).toDouble()
    return cuotaRedondeada
    return cuotas
}