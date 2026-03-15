package com.example.guiame.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Colores del flujo (Añadimos los verdes para el reporte)
private val PinkStrong = Color(0xFFE94560)
private val PinkPale = Color(0xFFFDE4E9)
private val PinkCardBg = Color(0xFFFADADD)
private val GreenReportBg = Color(0xFFE8F5E9)
private val GreenText = Color(0xFF2E7D32)

@Composable
fun AutoexamenFlowScreen(onClose: () -> Unit) {
    // 0 = Bienvenida, 1 al 7 = Preguntas, 8 = Reporte Final
    var currentStep by remember { mutableIntStateOf(0) }

    // Guardamos las respuestas (Paso -> ¿Dijo Sí o No?) True = Sí, False = No
    val answers = remember { mutableStateMapOf<Int, Boolean>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // TOP BAR DINÁMICA
        QuizTopBar(
            step = currentStep,
            onBack = { if (currentStep > 0) currentStep-- },
            onClose = onClose
        )

        // CONTENIDO PRINCIPAL SEGÚN EL PASO
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            when (currentStep) {
                0 -> WelcomeStep(onStart = { currentStep = 1 })
                in 1..7 -> QuestionStep(
                    stepNumber = currentStep,
                    selectedAnswer = answers[currentStep],
                    onAnswerSelected = { isYes -> answers[currentStep] = isYes },
                    onContinue = { currentStep++ }
                )
                8 -> ReportStep() // La IA lo llenará después
            }
        }
    }
}

@Composable
fun QuizTopBar(step: Int, onBack: () -> Unit, onClose: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(PinkPale)
            .statusBarsPadding() // <-- ¡ESTO BAJA LA BARRA ABAJO DE LA CÁMARA!
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Flecha de atrás (solo en preguntas)
        if (step in 1..7) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Atrás",
                tint = PinkStrong,
                modifier = Modifier.clickable { onBack() }
            )
        } else {
            Spacer(modifier = Modifier.width(24.dp)) // Espacio vacío para balancear
        }

        // Título o Barra de Progreso
        if (step == 0) {
            Text("Evaluación", color = PinkStrong, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        } else if (step in 1..7) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                LinearProgressIndicator(
                    progress = { step / 7f },
                    modifier = Modifier.fillMaxWidth(0.6f).height(8.dp).clip(RoundedCornerShape(4.dp)),
                    color = PinkStrong,
                    trackColor = Color.White
                )
                Text("$step de 7", color = PinkStrong, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
            }
        } else {
            Text("Tu Reporte", color = PinkStrong, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        // Botón de Cerrar
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Cerrar",
            tint = PinkStrong,
            modifier = Modifier.size(28.dp).clickable { onClose() } // Lo hice un poquito más grande para que sea fácil de tocar
        )
    }
}

@Composable
fun WelcomeStep(onStart: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Círculo gigante con check (puedes poner tu propia imagen luego)
        Box(
            modifier = Modifier.size(120.dp).background(PinkStrong, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("✓", color = Color.White, fontSize = 60.sp)
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text("Bienvenida al\nAutoexamen", color = PinkStrong, fontSize = 32.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Te guiaremos paso a paso en el proceso del autoexamen de mama. Tómate tu tiempo y sigue las instrucciones cuidadosamente.",
            textAlign = TextAlign.Center, color = Color.Gray, fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onStart,
            modifier = Modifier.fillMaxWidth().height(55.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PinkStrong)
        ) {
            Text("Empezar", fontSize = 20.sp)
        }
    }
}

@Composable
fun QuestionStep(
    stepNumber: Int,
    selectedAnswer: Boolean?,
    onAnswerSelected: (Boolean) -> Unit,
    onContinue: () -> Unit
) {
    // Textos simulados basados en el paso (Puedes rellenar los demás luego)
    val title = when(stepNumber) {
        1 -> "Inspección Visual"
        2 -> "Brazos Levantados"
        else -> "Paso $stepNumber"
    }
    val instructions = when(stepNumber) {
        1 -> "Párate frente a un espejo con los brazos a los lados.\n\nObserva tus senos en busca de:\n• Cambios en el tamaño o forma\n• Cambios en la piel (hoyuelos, arrugas)"
        2 -> "Levanta los brazos por encima de la cabeza.\n\nObserva si hay:\n• Cambios en la forma\n• Bultos visibles"
        else -> "Sigue las instrucciones para este paso..."
    }
    val question = "¿Observaste algún cambio visible en tus senos?"

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Círculo con el número
        Box(
            modifier = Modifier.size(60.dp).border(2.dp, PinkStrong, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(stepNumber.toString(), color = PinkStrong, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(title, color = PinkStrong, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        // Tarjeta de instrucciones
        Card(
            colors = CardDefaults.cardColors(containerColor = PinkCardBg),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(instructions, color = PinkStrong, modifier = Modifier.padding(16.dp), fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(32.dp))

        // Pregunta
        Text(question, color = PinkStrong, fontSize = 18.sp, fontWeight = FontWeight.Medium, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(24.dp))

        // Botones Sí / No
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SelectionButton(text = "No", isSelected = selectedAnswer == false, onClick = { onAnswerSelected(false) }, modifier = Modifier.weight(1f))
            SelectionButton(text = "Sí", isSelected = selectedAnswer == true, onClick = { onAnswerSelected(true) }, modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(24.dp))

        // Botón Continuar (Solo se activa si ya seleccionó Sí o No)
        Button(
            onClick = onContinue,
            enabled = selectedAnswer != null,
            modifier = Modifier.fillMaxWidth().height(55.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PinkStrong, disabledContainerColor = PinkPale)
        ) {
            Text("Continuar", fontSize = 18.sp, color = if (selectedAnswer != null) Color.White else PinkStrong)
        }
    }
}

@Composable
fun SelectionButton(text: String, isSelected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier.height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) PinkStrong else PinkCardBg,
            contentColor = if (isSelected) Color.White else PinkStrong
        )
    ) {
        Text(text, fontSize = 18.sp)
    }
}

@Composable
fun ReportStep() {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tarjeta Verde de "Todo excelente"
        Card(
            colors = CardDefaults.cardColors(containerColor = GreenReportBg),
            border = androidx.compose.foundation.BorderStroke(1.dp, GreenText),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("✨", fontSize = 40.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("¡Excelente! No se detectaron aspectos preocupantes", color = GreenText, fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Tu autoexamen no mostró hallazgos que requieran atención inmediata.", color = GreenText, textAlign = TextAlign.Center)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Tarjetas rosas de resumen (Para llenar después con IA)
        Card(colors = CardDefaults.cardColors(containerColor = PinkCardBg), modifier = Modifier.fillMaxWidth()) {
            Text("📋 Resumen del Autoexamen\n\n(Aquí irá el texto generado por IA en el futuro...)", color = PinkStrong, modifier = Modifier.padding(16.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(colors = CardDefaults.cardColors(containerColor = PinkCardBg), modifier = Modifier.fillMaxWidth()) {
            Text("Recomendaciones:\n\n✓ Realiza autoexámenes mensuales...\n✓ Mantén tus chequeos...", color = PinkStrong, modifier = Modifier.padding(16.dp))
        }
    }
}