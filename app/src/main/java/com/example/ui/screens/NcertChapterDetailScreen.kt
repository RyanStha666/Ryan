package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.NcertChapter
import com.example.ui.theme.*

@Composable
fun NcertChapterDetailScreen(
    chapter: NcertChapter,
    onStartViva: () -> Unit,
    onStartFlashcards: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkNavyBackground)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Chapter Header Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(20.dp))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Surface(
                        color = ElectricCyan.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "${chapter.classLevel} • CHAPTER ${chapter.chapterNumber}",
                            fontFamily = PlusJakartaSans,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = ElectricCyan,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = chapter.title,
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = chapter.summary,
                        fontFamily = PlusJakartaSans,
                        fontSize = 13.sp,
                        color = TextSecondary,
                        lineHeight = 19.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = onStartViva,
                            colors = ButtonDefaults.buttonColors(containerColor = VividViolet),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("chapter_viva_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.RecordVoiceOver,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Oral Viva (${chapter.oralQuestions.size})",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Color.White
                            )
                        }

                        Button(
                            onClick = onStartFlashcards,
                            colors = ButtonDefaults.buttonColors(containerColor = DeepNavySurface),
                            shape = RoundedCornerShape(12.dp),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = androidx.compose.ui.graphics.SolidColor(ElectricCyan)
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("chapter_flashcards_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.FlipCameraAndroid,
                                contentDescription = null,
                                tint = ElectricCyan,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Flashcards (${chapter.flashcards.size})",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = ElectricCyan
                            )
                        }
                    }
                }
            }
        }

        // High-Yield Points
        item {
            Text(
                text = "CBSE High-Yield Board Points",
                fontFamily = OutfitFont,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = TextPrimary
            )
        }

        items(chapter.highYieldPoints) { pt ->
            Card(
                colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(12.dp))
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Stars,
                        contentDescription = null,
                        tint = StreakFlame,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = pt,
                        fontFamily = PlusJakartaSans,
                        fontSize = 13.sp,
                        color = TextPrimary,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // Formulas
        if (chapter.formulas.isNotEmpty()) {
            item {
                Text(
                    text = "Key Formulas & Reactions",
                    fontFamily = OutfitFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = ElectricCyan
                )
            }

            items(chapter.formulas) { form ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, ElectricCyan.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                ) {
                    Text(
                        text = form,
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = ElectricCyan,
                        modifier = Modifier.padding(14.dp)
                    )
                }
            }
        }

        // PYQs
        if (chapter.pyqs.isNotEmpty()) {
            item {
                Text(
                    text = "Previous Year Board Questions (PYQs)",
                    fontFamily = OutfitFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = TextPrimary
                )
            }

            items(chapter.pyqs) { q ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(16.dp))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = q.question,
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = TextPrimary
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        q.options.forEachIndexed { i, opt ->
                            Text(
                                text = "${('A' + i)}. $opt",
                                fontFamily = PlusJakartaSans,
                                fontSize = 12.sp,
                                color = if (i == q.correctIndex) SuccessGreen else TextSecondary,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Answer: ${q.explanation}",
                            fontFamily = PlusJakartaSans,
                            fontSize = 11.sp,
                            color = SuccessGreen
                        )
                    }
                }
            }
        }
    }
}
