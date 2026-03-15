package com.example.guiame.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoScreen() {
    var showSintomas by remember { mutableStateOf(false) }

    if (showSintomas) {
        // Si el interruptor está encendido, mostramos la pantalla nueva
        SintomasScreen(
            onBack = { showSintomas = false } // Al darle atrás, lo apagamos
        )
    } else {
        Scaffold(
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(PinkHeaderBg) // Fondo rosa claro
                        // AÑADE ESTO: Un borde inferior fino y rosa fuerte
                        .drawBehind {
                            drawLine(
                                color = PinkText,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = 1.dp.toPx()
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Cáncer de mama",
                        color = PinkText,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        ) { paddingValues ->
            // Cuerpo de la pantalla
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 32.dp), // Margen a los lados para los botones
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center // Centra los botones verticalmente
            ) {

                Spacer(modifier = Modifier.height(32.dp))

                // Creamos los 5 botones usando nuestro Composable personalizado
                CustomPinkButton(text = "¿Qué es?")
                Spacer(modifier = Modifier.height(16.dp))

                CustomPinkButton(text = "Tipos")
                Spacer(modifier = Modifier.height(16.dp))

                CustomPinkButton(text = "Diagnostico")
                Spacer(modifier = Modifier.height(16.dp))

                CustomPinkButton(
                        text = "Síntomas",
                        onClick = { showSintomas = true } // Encendemos el interruptor
                    )
                Spacer(modifier = Modifier.height(16.dp))

                CustomPinkButton(text = "Tratamientos")

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}