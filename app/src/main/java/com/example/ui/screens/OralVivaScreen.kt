package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.example.data.model.OralTestItem
import com.example.ui.theme.*

@Composable
fun OralVivaScreen(
    oralItems: List<OralTestItem>,
    isSpeaking: Boolean,
    onSpeak: (String) -> Unit,
    onStopSpeak: () -> Unit,
    onEvaluate: (OralTestItem, String) -> Unit,
    evaluation: Pair<Int, String>?
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val currentItem = oralItems.getOrNull(currentIndex) ?: OralTestItem(
        id = "oral_default",
        promptQuestion = "State Ohm's Law and explain the relation between potential difference and current.",
        expectedAnswer = "Current flowing through a conductor is directly proportional to potential difference across its ends provided temperature remains constant: V = I * R.",
        mandatoryKeywords = listOf("directly proportional", "potential difference", "temperature constant", "V = IR")
    )

    var studentAnswer by remember { mutableStateOf("") }

    LaunchedEffect(currentIndex) {
        studentAnswer = ""
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkNavyBackground)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        color = VividViolet.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "VOICE VIVA VOCE",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = VividViolet,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                    Text(
                        text = "Question ${currentIndex + 1} of ${oralItems.size.coerceAtLeast(1)}",
                        fontFamily = PlusJakartaSans,
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "AI Oral Examiner",
                    fontFamily = OutfitFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = TextPrimary
                )
                Text(
                    text = "The AI asks questions aloud. Speak or type your answer to simulate a CBSE board practical viva.",
                    fontFamily = PlusJakartaSans,
                    fontSize = 13.sp,
                    color = TextSecondary
                )
            }
        }

        // Examiner Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.5.dp,
                        Brush.verticalGradient(listOf(VividViolet, DeepNavyCardBorder)),
                        RoundedCornerShape(20.dp)
                    )
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(
                                        Brush.radialGradient(listOf(VividViolet, NeonIndigo))
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.RecordVoiceOver,
                                    contentDescription = "AI Examiner",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Column {
                                Text(
                                    text = "Dr. Tarko (AI Examiner)",
                                    fontFamily = OutfitFont,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = TextPrimary
                                )
                                Text(
                                    text = "CBSE Board Evaluator",
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 11.sp,
                                    color = VividViolet
                                )
                            }
                        }

                        // Audio speak trigger
                        IconButton(
                            onClick = {
                                if (isSpeaking) onStopSpeak() else onSpeak(currentItem.promptQuestion)
                            },
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(if (isSpeaking) StreakFlame else DeepNavySurface)
                                .border(1.dp, if (isSpeaking) StreakFlame else VividViolet, CircleShape)
                                .testTag("oral_speak_question_button")
                        ) {
                            Icon(
                                imageVector = if (isSpeaking) Icons.Default.VolumeOff else Icons.Default.VolumeUp,
                                contentDescription = "Speak Question",
                                tint = if (isSpeaking) Color.White else VividViolet
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "\"${currentItem.promptQuestion}\"",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = TextPrimary,
                        lineHeight = 24.sp
                    )
                }
            }
        }

        // Student Answer Input Area
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(18.dp))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Your Spoken / Written Response",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = TextPrimary
                    )

                    OutlinedTextField(
                        value = studentAnswer,
                        onValueChange = { studentAnswer = it },
                        placeholder = { Text("Speak or type your answer clearly...") },
                        minLines = 3,
                        maxLines = 6,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = VividViolet,
                            unfocusedBorderColor = DeepNavyCardBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("student_oral_input")
                    )

                    // Quick spoken answer templates for rapid testing
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SuggestionChip(
                            onClick = {
                                studentAnswer = currentItem.expectedAnswer
                            },
                            label = { Text("Voice Sample (Perfect)", fontSize = 11.sp) },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = DeepNavySurface,
                                labelColor = SuccessGreen
                            )
                        )
                        SuggestionChip(
                            onClick = {
                                studentAnswer = "It decomposes in sunlight into silver and gas."
                            },
                            label = { Text("Voice Sample (Partial)", fontSize = 11.sp) },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = DeepNavySurface,
                                labelColor = WarningAmber
                            )
                        )
                    }

                    Button(
                        onClick = {
                            if (studentAnswer.isNotBlank()) {
                                onEvaluate(currentItem, studentAnswer)
                            }
                        },
                        enabled = studentAnswer.isNotBlank(),
                        colors = ButtonDefaults.buttonColors(containerColor = VividViolet),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("evaluate_oral_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Analytics,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Evaluate Viva Response",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }

        // Evaluation Result
        if (evaluation != null) {
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.dp,
                            if (evaluation.first >= 75) SuccessGreen else WarningAmber,
                            RoundedCornerShape(18.dp)
                        )
                        .testTag("oral_evaluation_card")
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Viva Voce Evaluation",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = TextPrimary
                            )
                            Surface(
                                color = if (evaluation.first >= 75) SuccessGreen.copy(alpha = 0.2f) else WarningAmber.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "${evaluation.first}% Concept Accuracy",
                                    fontFamily = OutfitFont,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = if (evaluation.first >= 75) SuccessGreen else WarningAmber,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = evaluation.second,
                            fontFamily = PlusJakartaSans,
                            fontSize = 13.sp,
                            color = TextPrimary,
                            lineHeight = 18.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Ideal CBSE Board Answer:",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = ElectricCyan
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = currentItem.expectedAnswer,
                            fontFamily = PlusJakartaSans,
                            fontSize = 12.sp,
                            color = TextSecondary,
                            lineHeight = 17.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        if (currentIndex < oralItems.size - 1) {
                            Button(
                                onClick = {
                                    currentIndex++
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("next_oral_question_button")
                            ) {
                                Text(
                                    text = "Next Viva Question",
                                    fontFamily = OutfitFont,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
