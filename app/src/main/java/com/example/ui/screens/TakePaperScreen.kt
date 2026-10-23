package com.example.ui.screens

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
import com.example.data.model.CbsePaper
import com.example.ui.theme.*

@Composable
fun TakePaperScreen(
    paper: CbsePaper,
    answers: Map<String, Int>,
    isSubmitted: Boolean,
    score: Int,
    onSelectOption: (String, Int) -> Unit,
    onSubmit: (CbsePaper) -> Unit,
    onReset: () -> Unit
) {
    var selectedSectionIndex by remember { mutableIntStateOf(0) }
    val activeSection = paper.sections.getOrNull(selectedSectionIndex) ?: paper.sections.first()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkNavyBackground)
    ) {
        // Sticky Exam Header & Timer
        Surface(
            color = DeepNavySurface,
            shadowElevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = paper.title.take(28) + "...",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = TextPrimary
                    )
                    Text(
                        text = "${answers.size} answered • ${paper.totalMarks} Marks Total",
                        fontFamily = PlusJakartaSans,
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = "Timer",
                        tint = WarningAmber,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = if (isSubmitted) "COMPLETED" else "27:45",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = WarningAmber
                    )
                }
            }
        }

        // Section Tabs
        ScrollableTabRow(
            selectedTabIndex = selectedSectionIndex,
            containerColor = DeepNavyCard,
            contentColor = WarningAmber,
            edgePadding = 16.dp,
            divider = { HorizontalDivider(color = DeepNavyCardBorder) }
        ) {
            paper.sections.forEachIndexed { idx, sec ->
                Tab(
                    selected = selectedSectionIndex == idx,
                    onClick = { selectedSectionIndex = idx },
                    text = {
                        Text(
                            text = sec.name.take(14),
                            fontFamily = OutfitFont,
                            fontWeight = if (selectedSectionIndex == idx) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 12.sp,
                            color = if (selectedSectionIndex == idx) WarningAmber else TextMuted
                        )
                    },
                    modifier = Modifier.testTag("paper_section_tab_$idx")
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Score Banner if submitted
            if (isSubmitted) {
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, SuccessGreen, RoundedCornerShape(18.dp))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Exam Score Report",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = SuccessGreen
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "$score / ${paper.totalMarks} Marks",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 32.sp,
                                color = TextPrimary
                            )
                            Text(
                                text = "Great practice! Review step-by-step marking solutions below.",
                                fontFamily = PlusJakartaSans,
                                fontSize = 12.sp,
                                color = TextSecondary
                            )
                        }
                    }
                }
            }

            // Questions in active section
            items(activeSection.questions) { q ->
                val selectedOpt = answers[q.id]
                Card(
                    colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(16.dp))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Surface(
                                color = ElectricCyan.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "${q.type} • ${q.marks} Mark${if (q.marks > 1) "s" else ""}",
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = ElectricCyan,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = q.questionText,
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = TextPrimary,
                            lineHeight = 20.sp
                        )

                        if (q.options.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(12.dp))
                            q.options.forEachIndexed { optIdx, optStr ->
                                val isOptSelected = selectedOpt == optIdx
                                val bg = when {
                                    !isSubmitted && isOptSelected -> ElectricCyan.copy(alpha = 0.2f)
                                    isSubmitted && optIdx == q.correctOptionIndex -> SuccessGreen.copy(alpha = 0.2f)
                                    isSubmitted && isOptSelected && optIdx != q.correctOptionIndex -> ErrorRed.copy(alpha = 0.2f)
                                    else -> DeepNavySurface
                                }
                                val border = when {
                                    !isSubmitted && isOptSelected -> ElectricCyan
                                    isSubmitted && optIdx == q.correctOptionIndex -> SuccessGreen
                                    isSubmitted && isOptSelected && optIdx != q.correctOptionIndex -> ErrorRed
                                    else -> DeepNavyCardBorder
                                }

                                Surface(
                                    color = bg,
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .border(1.dp, border, RoundedCornerShape(10.dp))
                                        .clickable(enabled = !isSubmitted) {
                                            onSelectOption(q.id, optIdx)
                                        }
                                        .testTag("option_${q.id}_$optIdx")
                                ) {
                                    Row(
                                        modifier = Modifier.padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Text(
                                            text = "${('A' + optIdx)}. ",
                                            fontFamily = OutfitFont,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isOptSelected) ElectricCyan else TextSecondary
                                        )
                                        Text(
                                            text = optStr,
                                            fontFamily = PlusJakartaSans,
                                            fontSize = 12.sp,
                                            color = TextPrimary
                                        )
                                    }
                                }
                            }
                        } else {
                            // Non-MCQ question answer indicator
                            Spacer(modifier = Modifier.height(12.dp))
                            OutlinedButton(
                                onClick = {
                                    onSelectOption(q.id, 1) // Mark as completed
                                },
                                enabled = !isSubmitted,
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = if (selectedOpt != null) Icons.Default.CheckCircle else Icons.Default.EditNote,
                                    contentDescription = null,
                                    tint = if (selectedOpt != null) SuccessGreen else ElectricCyan,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (selectedOpt != null) "Answer Formulated ✓" else "Mark Answer Written on Sheet",
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 12.sp,
                                    color = if (selectedOpt != null) SuccessGreen else ElectricCyan
                                )
                            }
                        }

                        // Solution reveal if submitted
                        if (isSubmitted) {
                            Spacer(modifier = Modifier.height(12.dp))
                            HorizontalDivider(color = DeepNavyCardBorder)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "CBSE Marking Scheme Solution:",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = SuccessGreen
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = q.modelSolution,
                                fontFamily = PlusJakartaSans,
                                fontSize = 11.sp,
                                color = TextSecondary,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }

            // Bottom action
            item {
                if (!isSubmitted) {
                    Button(
                        onClick = { onSubmit(paper) },
                        colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("submit_paper_button")
                    ) {
                        Text(
                            text = "Submit Paper for Board Scoring",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                } else {
                    OutlinedButton(
                        onClick = onReset,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("retake_paper_button")
                    ) {
                        Text(
                            text = "Retake Examination",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            color = ElectricCyan
                        )
                    }
                }
            }
        }
    }
}
