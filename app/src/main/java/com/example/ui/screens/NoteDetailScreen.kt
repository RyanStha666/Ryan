package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.example.data.model.NoteDocument
import com.example.ui.theme.*

@Composable
fun NoteDetailScreen(
    note: NoteDocument,
    onBack: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabTitles = listOf("Summary", "Formulas", "Mind Map", "Flashcards", "Quiz")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkNavyBackground)
    ) {
        // Tab row
        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            containerColor = DeepNavySurface,
            contentColor = ElectricCyan,
            edgePadding = 16.dp,
            divider = { HorizontalDivider(color = DeepNavyCardBorder) }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontFamily = OutfitFont,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 13.sp,
                            color = if (selectedTab == index) ElectricCyan else TextMuted
                        )
                    },
                    modifier = Modifier.testTag("note_tab_$index")
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Note Title Header
            item {
                Text(
                    text = note.title,
                    fontFamily = OutfitFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = TextPrimary
                )
            }

            when (selectedTab) {
                0 -> {
                    // Summary bullets
                    item {
                        Text(
                            text = "Executive Concept Summary",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = ElectricCyan
                        )
                    }
                    items(note.summaryBullets) { bullet ->
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
                                    imageVector = Icons.Default.CheckCircleOutline,
                                    contentDescription = null,
                                    tint = ElectricCyan,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = bullet,
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 13.sp,
                                    color = TextPrimary,
                                    lineHeight = 19.sp
                                )
                            }
                        }
                    }
                }
                1 -> {
                    // Formulas
                    item {
                        Text(
                            text = "Essential CBSE Formulas & Equations",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = ElectricCyan
                        )
                    }
                    items(note.formulas) { formula ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, ElectricCyan.copy(alpha = 0.3f), RoundedCornerShape(14.dp))
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = formula,
                                    fontFamily = OutfitFont,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = ElectricCyan
                                )
                            }
                        }
                    }
                }
                2 -> {
                    // Mind Map
                    item {
                        Text(
                            text = "Concept Mind Map Structure",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = NeonIndigo
                        )
                    }
                    items(note.mindMapNodes) { node ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(14.dp))
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AccountTree,
                                        contentDescription = null,
                                        tint = NeonIndigo,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(
                                        text = node.concept,
                                        fontFamily = OutfitFont,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = TextPrimary
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                node.subpoints.forEach { sub ->
                                    Row(
                                        modifier = Modifier.padding(vertical = 3.dp),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Text(text = "•", color = NeonIndigo, fontWeight = FontWeight.Bold)
                                        Text(
                                            text = sub,
                                            fontFamily = PlusJakartaSans,
                                            fontSize = 12.sp,
                                            color = TextSecondary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                3 -> {
                    // Flashcards
                    item {
                        Text(
                            text = "Tap Card to Flip & Reveal Answer",
                            fontFamily = PlusJakartaSans,
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                    }
                    items(note.flashcards) { card ->
                        var isFlipped by remember { mutableStateOf(false) }
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = if (isFlipped) Color(0xFF1B233D) else DeepNavyCard
                            ),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    1.dp,
                                    if (isFlipped) VividViolet else DeepNavyCardBorder,
                                    RoundedCornerShape(16.dp)
                                )
                                .clickable { isFlipped = !isFlipped }
                                .testTag("flashcard_${card.id}")
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(18.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = if (isFlipped) "ANSWER (REVEALED)" else "QUESTION",
                                        fontFamily = PlusJakartaSans,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isFlipped) VividViolet else ElectricCyan,
                                        letterSpacing = 1.sp
                                    )
                                    Icon(
                                        imageVector = Icons.Default.FlipCameraAndroid,
                                        contentDescription = "Flip",
                                        tint = TextMuted,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = if (isFlipped) card.back else card.front,
                                    fontFamily = OutfitFont,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    color = TextPrimary,
                                    lineHeight = 22.sp
                                )

                                if (!isFlipped && card.hint.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "💡 Hint: ${card.hint}",
                                        fontFamily = PlusJakartaSans,
                                        fontSize = 11.sp,
                                        color = TextMuted
                                    )
                                }
                            }
                        }
                    }
                }
                4 -> {
                    // Practice Quiz
                    items(note.quizQuestions) { q ->
                        var selectedOption by remember { mutableIntStateOf(-1) }
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
                                    fontSize = 15.sp,
                                    color = TextPrimary
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                q.options.forEachIndexed { optIndex, optionText ->
                                    val isSelected = selectedOption == optIndex
                                    val isCorrect = optIndex == q.correctIndex
                                    val buttonBg = when {
                                        selectedOption == -1 -> DeepNavySurface
                                        isSelected && isCorrect -> SuccessGreen.copy(alpha = 0.2f)
                                        isSelected && !isCorrect -> ErrorRed.copy(alpha = 0.2f)
                                        isCorrect && selectedOption != -1 -> SuccessGreen.copy(alpha = 0.2f)
                                        else -> DeepNavySurface
                                    }
                                    val borderColor = when {
                                        selectedOption == -1 -> DeepNavyCardBorder
                                        isSelected && isCorrect -> SuccessGreen
                                        isSelected && !isCorrect -> ErrorRed
                                        isCorrect && selectedOption != -1 -> SuccessGreen
                                        else -> DeepNavyCardBorder
                                    }

                                    Surface(
                                        color = buttonBg,
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp)
                                            .border(1.dp, borderColor, RoundedCornerShape(10.dp))
                                            .clickable { selectedOption = optIndex }
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(12.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            Text(
                                                text = "${('A' + optIndex)}. ",
                                                fontFamily = OutfitFont,
                                                fontWeight = FontWeight.Bold,
                                                color = ElectricCyan
                                            )
                                            Text(
                                                text = optionText,
                                                fontFamily = PlusJakartaSans,
                                                fontSize = 12.sp,
                                                color = TextPrimary
                                            )
                                        }
                                    }
                                }

                                if (selectedOption != -1) {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = "Explanation: ${q.explanation}",
                                        fontFamily = PlusJakartaSans,
                                        fontSize = 11.sp,
                                        color = if (selectedOption == q.correctIndex) SuccessGreen else TextSecondary
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
