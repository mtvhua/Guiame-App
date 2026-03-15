package com.example.guiame.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight // Faltaba esto
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width // Faltaba esto
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ==========================================
// Paleta de Colores Extraída del Mockup
// ==========================================
val PinkHeaderBg = Color(0xFFFDE4E9) // Rosa muy claro (fondo del encabezado)
val PinkText = Color(0xFFE94560)     // Rosa fuerte (texto de títulos y botones)
val PinkButtonBg = Color(0xFFFADADD) // Rosa pastel (fondo de los botones)
val PinkNavBar = Color(0xFFFDE4E9)   // Fondo barra inferior
val PinkIconActive = Color(0xFFE94560) // Icono seleccionado
val PinkIconInactive = Color(0xFFEFA5B3) // Icono no seleccionado

@Composable
fun AutoexamenScreen(
    // Por ahora pasamos lambdas vacías para la navegación estática
    onNavigateToCancerInfo: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onStartQuiz: () -> Unit = {}
) {
    Scaffold(
        // En AutoexamenScreen.kt, InfoScreen.kt, etc.
// Reemplaza el bloque de 'topBar' por este:

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
                    text = "Autoexamen", // O "Cáncer de mama", etc.
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

            // Creamos los 4 botones usando nuestro Composable personalizado
            CustomPinkButton(text = "¿Qué es?")
            Spacer(modifier = Modifier.height(16.dp))

            CustomPinkButton(
                text = "Realizar",
                onClick = onStartQuiz // 2. LE ASIGNAMOS LA ACCIÓN AL BOTÓN
            )
            Spacer(modifier = Modifier.height(16.dp))

            CustomPinkButton(text = "Historial")
            Spacer(modifier = Modifier.height(16.dp))

            CustomPinkButton(text = "Notas")

            Spacer(modifier = Modifier.weight(1f)) // Empuja el contenido hacia arriba si es necesario
        }
    }
}

/**
 * Componente reutilizable para los botones rectangulares rosas de tu mockup.
 * Usamos una Card para darle esa pequeña sombra (elevación) que se ve en el diseño.
 */
@Composable
fun CustomPinkButton(
    text: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp) // Altura gruesa
            .clickable { onClick() },
        shape = RoundedCornerShape(4.dp), // Bordes ligeros
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Sombra
        colors = CardDefaults.cardColors(
            containerColor = PinkButtonBg // Fondo rosa pastel
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 1. PRIMERO LA LÍNEA (para que quede del lado izquierdo)
            Spacer(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(PinkText)
            )

            // 2. DESPUÉS EL TEXTO
            Text(
                text = text,
                color = PinkText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f).padding(horizontal = 16.dp)
            )
        }
    }
}