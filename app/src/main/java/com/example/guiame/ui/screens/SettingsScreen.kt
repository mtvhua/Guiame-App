package com.example.guiame.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset

@Composable
fun SettingsScreen(
    onLogoutClick: () -> Unit = {},
    onChangeEmailClick: () -> Unit = {},    // Acción para ir a cambiar correo
    onChangePasswordClick: () -> Unit = {}, // Acción para ir a cambiar contraseña
    onPreferencesClick: () -> Unit = {}     // Acción para ir a preferencias
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // HEADER
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFFFDE4E9)) // Usamos el rosa claro directo (PinkHeaderBg)
                .drawBehind {
                    drawLine(
                        color = PinkTitle, // Usamos el rosa fuerte para la línea
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Ajustes",
                tint = PinkTitle,
                modifier = Modifier.size(60.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // LISTA DE AJUSTES

        // 1. Nombre (Se edita aquí mismo)
        EditableSettingPill(
            initialText = "Tu Nombre",
            icon = Icons.Default.Person
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 2. Correo (Navega a otra pantalla)
        SettingPill(
            text = "Correo",
            icon = Icons.Default.Email,
            showEditIcon = true,
            onClick = onChangeEmailClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 3. Contraseña (Navega a otra pantalla)
        SettingPill(
            text = "Contraseña",
            icon = Icons.Default.Lock,
            showEditIcon = true,
            onClick = onChangePasswordClick
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 4. Preferencias (Navega a otra pantalla)
        SettingPill(
            text = "Preferencias",
            icon = Icons.Default.Notifications,
            showEditIcon = true,
            onClick = onPreferencesClick
        )

        Spacer(modifier = Modifier.weight(1f))

        // BOTÓN CERRAR SESIÓN
        Row(
            modifier = Modifier
                .padding(bottom = 32.dp)
                .clickable { onLogoutClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null, tint = PinkTitle)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Cerrar sesión", color = PinkTitle, fontWeight = FontWeight.Bold)
        }
    }
}

// ==========================================
// Píldora Editable (Mágia para el Nombre)
// ==========================================
@Composable
fun EditableSettingPill(
    initialText: String,
    icon: ImageVector
) {
    var text by remember { mutableStateOf(initialText) }
    var isEditing by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .height(56.dp)
            .clip(RoundedCornerShape(50))
            .background(PinkInputBg)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = PinkIcon, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))

        if (isEditing) {
            // Modo edición: Un campo de texto limpio
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                textStyle = TextStyle(color = PinkIcon, fontSize = 16.sp),
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            // Cambiamos el lapicito por una palomita para guardar
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Guardar",
                tint = PinkTitle,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { isEditing = false } // Al hacer clic, salimos de modo edición
            )
        } else {
            // Modo normal: Solo texto
            Text(text = text, color = PinkIcon, fontSize = 16.sp, modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar",
                tint = PinkTitle,
                modifier = Modifier
                    .size(20.dp)
                    .clickable { isEditing = true } // Al hacer clic, entramos a modo edición
            )
        }
    }
}

// ==========================================
// Píldora Normal (Botones)
// ==========================================
@Composable
fun SettingPill(
    text: String,
    icon: ImageVector,
    showEditIcon: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .height(56.dp)
            .clip(RoundedCornerShape(50))
            .background(PinkInputBg)
            .clickable { onClick() }
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = PinkIcon, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, color = PinkIcon, fontSize = 16.sp, modifier = Modifier.weight(1f))

        if (showEditIcon) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar",
                tint = PinkTitle,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}