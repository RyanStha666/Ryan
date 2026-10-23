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
import com.example.data.model.NcertSubject
import com.example.ui.theme.*

@Composable
fun NcertDirectoryScreen(
    subjects: List<NcertSubject>,
    onSelectSubject: (String) -> Unit,
    getChaptersForSubject: (String) -> List<NcertChapter>,
    onSelectChapter: (String) -> Unit
) {
    var selectedSubjectId by remember { mutableStateOf(subjects.firstOrNull()?.id ?: "") }
    val activeChapters = remember(selectedSubjectId) {
        if (selectedSubjectId.isNotEmpty()) getChaptersForSubject(selectedSubjectId) else emptyList()
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
                Text(
                    text = "NCERT Chapter Syllabus",
                    fontFamily = OutfitFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = TextPrimary
                )
                Text(
                    text = "Pick a CBSE NCERT chapter to review formulas, definitions, PYQs, and take voice oral tests.",
                    fontFamily = PlusJakartaSans,
                    fontSize = 13.sp,
                    color = TextSecondary
                )
            }
        }

        // Subject selector pills
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                subjects.forEach { sub ->
                    val isSelected = sub.id == selectedSubjectId
                    Surface(
                        color = if (isSelected) ElectricCyan else DeepNavyCard,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .border(
                                1.dp,
                                if (isSelected) ElectricCyan else DeepNavyCardBorder,
                                RoundedCornerShape(12.dp)
                            )
                            .clickable { selectedSubjectId = sub.id }
                            .testTag("subject_pill_${sub.id}")
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = sub.name,
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = if (isSelected) Color.Black else TextPrimary,
                                maxLines = 1
                            )
                            Text(
                                text = sub.classLevel,
                                fontFamily = PlusJakartaSans,
                                fontSize = 10.sp,
                                color = if (isSelected) Color.Black.copy(alpha = 0.7f) else TextSecondary
                            )
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = "Chapters (${activeChapters.size})",
                fontFamily = OutfitFont,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = TextPrimary
            )
        }

        items(activeChapters) { chapter ->
            Card(
                colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(16.dp))
                    .clickable { onSelectChapter(chapter.id) }
                    .testTag("ncert_chapter_${chapter.id}")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(
                                    if (chapter.isCompleted) SuccessGreen.copy(alpha = 0.15f) else ElectricCyan.copy(alpha = 0.15f)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${chapter.chapterNumber}",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = if (chapter.isCompleted) SuccessGreen else ElectricCyan
                            )
                        }

                        Column {
                            Text(
                                text = chapter.title,
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = TextPrimary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "${chapter.formulas.size} Formulas • ${chapter.pyqs.size} PYQs • ${chapter.oralQuestions.size} Oral Viva",
                                fontFamily = PlusJakartaSans,
                                fontSize = 11.sp,
                                color = TextSecondary
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "View",
                        tint = ElectricCyan
                    )
                }
            }
        }
    }
}
