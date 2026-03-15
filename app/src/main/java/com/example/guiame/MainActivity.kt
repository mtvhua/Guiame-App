package com.example.guiame
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background // Faltaba
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement // Faltaba
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row // Faltaba
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth // Faltaba
import androidx.compose.foundation.layout.height // Faltaba
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size // Faltaba
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind // Faltaba
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color // Faltaba
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp // Faltaba
import com.example.guiame.ui.screens.AuthScreen
import com.example.guiame.ui.theme.GuiameTheme
import com.example.guiame.ui.screens.AutoexamenScreen
import com.example.guiame.ui.screens.InfoScreen
import com.example.guiame.ui.screens.SettingsScreen
import com.example.guiame.ui.screens.AutoexamenFlowScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuiameTheme {
                // 1. Interruptor principal
                var isLoggedIn by rememberSaveable { mutableStateOf(false) }

                // 2. Evaluamos el estado
                if (isLoggedIn) {
                    // Si entró, mostramos la app y le damos la función para "cerrar sesión"
                    GuiameApp(
                        onLogout = { isLoggedIn = false } // Al llamarla, apagará el interruptor
                    )
                } else {
                    // Si no, mostramos login
                    AuthScreen(
                        onLoginSuccess = { isLoggedIn = true }
                    )
                }
            }
        }
    }
}

// ----------------------------------------------------
// Modificamos GuiameApp para que reciba la acción onLogout
// ----------------------------------------------------
@Composable
fun GuiameApp(onLogout: () -> Unit) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.AUTOEXAMEN) }

    // Estados para las pantallas de cambiar correo/contraseña
    var showChangeEmail by rememberSaveable { mutableStateOf(false) }
    var showChangePassword by rememberSaveable { mutableStateOf(false) }
    var showQuiz by rememberSaveable { mutableStateOf(false) }

    // Colores de tu diseño
    val pinkHeaderBg = Color(0xFFFDE4E9)   // Rosa claro (fondo)
    val pinkStrong = Color(0xFFE94560)     // Rosa fuerte (activos y líneas)
    val pinkInactive = Color(0xFFEFA5B3)   // Rosa pálido (inactivos)

    if (showQuiz) {
        AutoexamenFlowScreen(
            onClose = { showQuiz = false } // Cuando le den a la X, cerramos el cuestionario
        )
    } else if (showChangeEmail) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Pantalla Cambiar Correo")
            androidx.compose.material3.Button(onClick = { showChangeEmail = false }) { Text("Volver") }
        }
    } else if (showChangePassword) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Pantalla Cambiar Contraseña")
            androidx.compose.material3.Button(onClick = { showChangePassword = false }) { Text("Volver") }
        }
    } else {
        // Usamos un Scaffold normal para poder personalizar la barra al 100%
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                // NUESTRA BARRA INFERIOR PERSONALIZADA
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(pinkHeaderBg) // 1. Fondo rosa claro
                        .drawBehind {
                            // 2. Línea superior rosa fuerte
                            drawLine(
                                color = pinkStrong,
                                start = Offset(0f, 0f),
                                end = Offset(size.width, 0f),
                                strokeWidth = 1.dp.toPx()
                            )
                        },
                    horizontalArrangement = Arrangement.SpaceEvenly, // Espacia los íconos igual
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Dibujamos cada botón de la barra
                    AppDestinations.entries.forEach { destination ->
                        // ¿Es esta la pantalla en la que estamos ahorita?
                        val isSelected = currentDestination == destination
                        // Si sí, rosa fuerte. Si no, rosa pálido.
                        val iconColor = if (isSelected) pinkStrong else pinkInactive

                        Icon(
                            imageVector = destination.icon,
                            contentDescription = destination.label,
                            tint = iconColor, // 3. Color dinámico del ícono
                            modifier = Modifier
                                .size(45.dp) // Íconos grandes como en el mockup
                                .clickable { currentDestination = destination }
                                .padding(4.dp)
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                when (currentDestination) {
                    AppDestinations.INFO -> InfoScreen()
                    AppDestinations.AUTOEXAMEN -> {
                        // 3. PASAMOS LA ACCIÓN PARA ABRIR EL CUESTIONARIO
                        AutoexamenScreen(
                            onStartQuiz = { showQuiz = true }
                        )
                    }
                    AppDestinations.SETTINGS -> {
                        SettingsScreen(
                            onLogoutClick = onLogout,
                            onChangeEmailClick = { showChangeEmail = true },
                            onChangePasswordClick = { showChangePassword = true }
                        )
                    }
                }
            }
        }
    }
}

enum class AppDestinations(val label: String, val icon: ImageVector) {
    INFO("Info", Icons.Default.Info),
    AUTOEXAMEN("Autoexamen", Icons.Default.Checklist),
    SETTINGS("Ajustes", Icons.Default.AccountCircle),
}