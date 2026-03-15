package com.example.guiame.ui.screens // Asegúrate de usar tu paquete

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ==========================================
// Colores del Mockup
// ==========================================
val PinkTitle = Color(0xFFD81B60)      // Rosa oscuro para títulos y botones principales
val PinkInputBg = Color(0xFFF8D7DA)    // Rosa pálido para los textfields y botón secundario
val PinkIcon = Color(0xFFB06A7B)       // Rosa medio para iconos y texto de los inputs

// Estados posibles de la pantalla
enum class AuthMode { LOGIN, REGISTER, FORGOT_PASSWORD }

@Composable
fun AuthScreen(
    onLoginSuccess: () -> Unit = {} // Lo llamaremos cuando inicie sesión para ir al Autoexamen
) {
    // Controla qué vista estamos mostrando
    var currentMode by remember { mutableStateOf(AuthMode.LOGIN) }

    // Variables para guardar lo que el usuario escribe (por ahora solo UI)
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        // Flecha de retroceso (solo aparece si no estamos en Login)
        if (currentMode != AuthMode.LOGIN) {
            IconButton(
                onClick = { currentMode = AuthMode.LOGIN },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = PinkTitle,
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        // Contenido central
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // TÍTULOS CON FUENTE CURSIVA
            val titleText = when (currentMode) {
                AuthMode.LOGIN -> "¡Bienvenida\nde vuelta!"
                AuthMode.FORGOT_PASSWORD -> "Cambiar\ncontraseña"
                AuthMode.REGISTER -> "¡Empecemos!"
            }

            Text(
                text = titleText,
                color = PinkTitle,
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive, // Le da el toque elegante de tu diseño
                fontWeight = FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 45.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            // CAMPOS DE TEXTO SEGÚN EL MODO
            if (currentMode == AuthMode.REGISTER) {
                PillTextField(
                    value = name,
                    onValueChange = { name = it },
                    hint = "Nombre",
                    icon = Icons.Default.Person
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            PillTextField(
                value = email,
                onValueChange = { email = it },
                hint = "Correo",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (currentMode != AuthMode.FORGOT_PASSWORD) {
                PillTextField(
                    value = password,
                    onValueChange = { password = it },
                    hint = "Contraseña",
                    icon = Icons.Default.Lock,
                    isPassword = true
                )
            }

            // LINK OLVIDASTE CONTRASEÑA (Solo en Login)
            if (currentMode == AuthMode.LOGIN) {
                Text(
                    text = "¿Olvidaste la contraseña?",
                    color = PinkTitle,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .clickable { currentMode = AuthMode.FORGOT_PASSWORD }
                )
            } else {
                Spacer(modifier = Modifier.height(32.dp))
            }

            // BOTONES DE ACCIÓN
            when (currentMode) {
                AuthMode.LOGIN -> {
                    PillButton(text = "Iniciar", isPrimary = true) {
                        onLoginSuccess() // Simula el inicio de sesión
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    PillButton(text = "Registrarme", isPrimary = false) {
                        currentMode = AuthMode.REGISTER
                    }
                }
                AuthMode.REGISTER -> {
                    PillButton(text = "Registrarme", isPrimary = true) {
                        currentMode = AuthMode.LOGIN // Vuelve al login al registrar
                    }
                }
                AuthMode.FORGOT_PASSWORD -> {
                    PillButton(text = "Resetear", isPrimary = true) {
                        currentMode = AuthMode.LOGIN // Vuelve al login al resetear
                    }
                }
            }
        }
    }
}

// ==========================================
// Componentes Personalizados para el Mockup
// ==========================================

@Composable
fun PillTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = hint, color = PinkIcon) },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null, tint = PinkIcon)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = true,
        shape = RoundedCornerShape(50), // Forma de píldora
        modifier = Modifier.fillMaxWidth(0.85f), // No ocupa todo el ancho
        colors = TextFieldDefaults.colors(
            focusedContainerColor = PinkInputBg,
            unfocusedContainerColor = PinkInputBg,
            focusedIndicatorColor = Color.Transparent,   // Quita la línea de abajo
            unfocusedIndicatorColor = Color.Transparent, // Quita la línea de abajo
            cursorColor = PinkTitle
        )
    )
}

@Composable
fun PillButton(
    text: String,
    isPrimary: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.6f) // Más corto que los campos de texto
            .height(50.dp),
        shape = RoundedCornerShape(50), // Forma de píldora
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isPrimary) PinkTitle else PinkInputBg,
            contentColor = if (isPrimary) Color.White else PinkTitle
        )
    ) {
        Text(text = text, fontSize = 18.sp)
    }
}