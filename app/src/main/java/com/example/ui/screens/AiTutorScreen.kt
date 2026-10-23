package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.TutorMessage
import com.example.ui.theme.*

@Composable
fun AiTutorScreen(
    messages: List<TutorMessage>,
    onSendMessage: (String) -> Unit
) {
    var inputText by remember { mutableStateOf("") }
    var activeLanguage by remember { mutableStateOf("Hinglish") }
    val languages = listOf("English", "Hindi", "Hinglish")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkNavyBackground)
    ) {
        // Tutor Sub-header with Language selector
        Surface(
            color = DeepNavySurface,
            modifier = Modifier.fillMaxWidth(),
            shadowElevation = 2.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(ElectricCyan.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = ElectricCyan,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "Tarko AI Doubt Solver",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = TextPrimary
                        )
                        Text(
                            text = "Science & Maths 24x7",
                            fontFamily = PlusJakartaSans,
                            fontSize = 10.sp,
                            color = SuccessGreen
                        )
                    }
                }

                // Language switcher
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    languages.forEach { lang ->
                        val isLang = activeLanguage == lang
                        Surface(
                            color = if (isLang) ElectricCyan else DeepNavyCard,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .clickable { activeLanguage = lang }
                                .padding(2.dp)
                        ) {
                            Text(
                                text = lang,
                                fontFamily = PlusJakartaSans,
                                fontSize = 10.sp,
                                fontWeight = if (isLang) FontWeight.Bold else FontWeight.Normal,
                                color = if (isLang) Color.Black else TextSecondary,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                            )
                        }
                    }
                }
            }
        }

        // Chat message list
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(messages) { msg ->
                if (msg.isFromUser) {
                    // User Message
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Box(
                            modifier = Modifier
                                .widthIn(max = 280.dp)
                                .clip(RoundedCornerShape(16.dp, 16.dp, 2.dp, 16.dp))
                                .background(NeonIndigo)
                                .padding(12.dp)
                        ) {
                            Text(
                                text = msg.text,
                                fontFamily = PlusJakartaSans,
                                fontSize = 13.sp,
                                color = Color.White
                            )
                        }
                    }
                } else {
                    // AI Tutor Message
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp, 16.dp, 16.dp, 2.dp))
                                .background(DeepNavyCard)
                                .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(16.dp, 16.dp, 16.dp, 2.dp))
                                .padding(14.dp)
                        ) {
                            Column {
                                Text(
                                    text = msg.text,
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 13.sp,
                                    color = TextPrimary,
                                    lineHeight = 19.sp
                                )

                                if (msg.suggestedPills.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(
                                        text = "Suggested Questions:",
                                        fontFamily = OutfitFont,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp,
                                        color = ElectricCyan
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    msg.suggestedPills.forEach { pill ->
                                        Surface(
                                            color = DeepNavySurface,
                                            shape = RoundedCornerShape(8.dp),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 3.dp)
                                                .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(8.dp))
                                                .clickable { onSendMessage(pill) }
                                        ) {
                                            Text(
                                                text = "💬 $pill",
                                                fontFamily = PlusJakartaSans,
                                                fontSize = 11.sp,
                                                color = TextPrimary,
                                                modifier = Modifier.padding(8.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Input bar
        Surface(
            color = DeepNavySurface,
            shadowElevation = 8.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    placeholder = { Text("Ask doubt in $activeLanguage...") },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = ElectricCyan,
                        unfocusedBorderColor = DeepNavyCardBorder,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("tutor_input_field")
                )

                IconButton(
                    onClick = {
                        if (inputText.isNotBlank()) {
                            onSendMessage(inputText)
                            inputText = ""
                        }
                    },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(ElectricCyan)
                        .testTag("tutor_send_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}
