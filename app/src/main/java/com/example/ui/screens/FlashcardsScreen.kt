package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Flashcard
import com.example.ui.theme.*

@Composable
fun FlashcardsScreen(
    flashcards: List<Flashcard>,
    onSpeakWord: (String) -> Unit
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    var isFlipped by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        label = "cardFlip"
    )

    val currentCard = flashcards.getOrNull(currentIndex) ?: Flashcard(
        id = "fc_def",
        front = "What is the formula of Slaked Lime?",
        back = "Ca(OH)₂ [Calcium Hydroxide], formed by CaO + H₂O with evolution of heat.",
        hint = "Formed from quicklime",
        subject = "Chemistry"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkNavyBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Active Recall Flashcards",
                    fontFamily = OutfitFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = TextPrimary
                )
                Text(
                    text = "Card ${currentIndex + 1} of ${flashcards.size.coerceAtLeast(1)} • ${currentCard.subject}",
                    fontFamily = PlusJakartaSans,
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }

            IconButton(
                onClick = { onSpeakWord(if (isFlipped) currentCard.back else currentCard.front) },
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(DeepNavyCard)
            ) {
                Icon(
                    imageVector = Icons.Default.VolumeUp,
                    contentDescription = "Pronounce",
                    tint = ElectricCyan
                )
            }
        }

        // Center 3D Flip Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 24.dp)
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 12f * density
                }
                .clip(RoundedCornerShape(24.dp))
                .background(
                    if (rotation > 90f) Color(0xFF192238) else DeepNavyCard
                )
                .border(
                    1.5.dp,
                    if (rotation > 90f) VividViolet else ElectricCyan,
                    RoundedCornerShape(24.dp)
                )
                .clickable { isFlipped = !isFlipped }
                .padding(24.dp)
                .testTag("flashcard_flip_box"),
            contentAlignment = Alignment.Center
        ) {
            if (rotation <= 90f) {
                // Front
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "QUESTION",
                        fontFamily = PlusJakartaSans,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ElectricCyan,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Text(
                        text = currentCard.front,
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = TextPrimary,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        lineHeight = 28.sp
                    )
                    if (currentCard.hint.isNotBlank()) {
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = "💡 ${currentCard.hint}",
                            fontFamily = PlusJakartaSans,
                            fontSize = 12.sp,
                            color = TextMuted
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Tap to Flip",
                        fontFamily = PlusJakartaSans,
                        fontSize = 11.sp,
                        color = ElectricCyan
                    )
                }
            } else {
                // Back (Mirrored for correct reading)
                Column(
                    modifier = Modifier.graphicsLayer { rotationY = 180f },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "ANSWER",
                        fontFamily = PlusJakartaSans,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = VividViolet,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Text(
                        text = currentCard.back,
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = TextPrimary,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        lineHeight = 26.sp
                    )
                }
            }
        }

        // Bottom Rating & Navigation Bar
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        isFlipped = false
                        if (currentIndex < flashcards.size - 1) currentIndex++ else currentIndex = 0
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ErrorRed.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Hard", color = ErrorRed, fontFamily = OutfitFont, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = {
                        isFlipped = false
                        if (currentIndex < flashcards.size - 1) currentIndex++ else currentIndex = 0
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Good", color = SuccessGreen, fontFamily = OutfitFont, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = {
                        isFlipped = false
                        if (currentIndex < flashcards.size - 1) currentIndex++ else currentIndex = 0
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Easy", color = ElectricCyan, fontFamily = OutfitFont, fontWeight = FontWeight.Bold)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = {
                        isFlipped = false
                        if (currentIndex > 0) currentIndex--
                    },
                    enabled = currentIndex > 0,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Previous")
                }

                OutlinedButton(
                    onClick = {
                        isFlipped = false
                        if (currentIndex < flashcards.size - 1) currentIndex++
                    },
                    enabled = currentIndex < flashcards.size - 1,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Next Card")
                }
            }
        }
    }
}
