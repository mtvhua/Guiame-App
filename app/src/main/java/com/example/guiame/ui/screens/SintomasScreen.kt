package com.example.guiame.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
private val PinkImagePlaceholder = Color(0xFFFADADD)
@Composable
fun SintomasScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // TOP BAR CON BOTÓN DE REGRESAR
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(PinkHeaderBg)
                .statusBarsPadding() // <- Evita que lo tape la cámara
                .drawBehind {
                    // Línea fuerte abajo del encabezado
                    drawLine(
                        color = PinkText,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 2.dp.toPx()
                    )
                }
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Regresar",
                tint = PinkText,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onBack() }
            )

            Text(
                text = "Síntomas",
                color = PinkText,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 28.dp), // Centra el texto compensando el ícono
                textAlign = TextAlign.Center
            )
        }

        // CONTENIDO CON SCROLL
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            // Párrafo 1
            Text(
                text = "Se debe considerar que el cáncer de mama precoz por lo general no causa síntomas, por esto es importante el apoyo con los estudios de imagen e histopatología para diagnóstico y tamizaje. Los pacientes, en caso de presentar síntomas, pueden referir:",
                fontSize = 16.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de síntomas
            val listaSintomas = listOf(
                "Descamación de la areola y piel",
                "Formación de costras",
                "Rubor",
                "Edema de la mama",
                "Formación de hoyuelos cutáneos (piel de naranja)",
                "Mastalgia",
                "Retracción de los pezones",
                "Dolor óseo",
                "Úlceras cutáneas",
                "Adenopatías",
                "Pérdida de peso",
                "Secreción sanguinolenta"
            )

            // Dibujamos las viñetas automáticamente
            listaSintomas.forEach { sintoma ->
                Row(modifier = Modifier.padding(bottom = 6.dp)) {
                    Text("• ", fontSize = 16.sp, color = Color.DarkGray, fontWeight = FontWeight.Bold)
                    Text(sintoma, fontSize = 16.sp, color = Color.DarkGray)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Párrafo 2
            Text(
                text = "Sin embargo, el síntoma más temprano y frecuente es la aparición de una masa a nivel mamario, no dolorosa, firme y de bordes irregulares.",
                fontSize = 16.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ESPACIO RESERVADO PARA LAS IMÁGENES
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(PinkImagePlaceholder, shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🖼️\n(Espacio para imágenes)",
                    color = PinkText,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botón "Más información"
            Button(
                onClick = { /* Lógica futura */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PinkText)
            ) {
                Text("Más información", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Referencias
            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Referencias",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "1. Palmero Picazo, J., Lassard Rosenthal, J., Juárez Aguilar, L. A., & Medina Núñez, C. A. (2021). Cáncer de mama: una visión general. Acta Médica Grupo Ángeles, 19(3), 354–360. https://doi.org/10.35366/101727",
                fontSize = 12.sp,
                color = Color.Gray,
                lineHeight = 16.sp
            )

            // Espacio final para que no tope con la barra de navegación al hacer scroll
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}