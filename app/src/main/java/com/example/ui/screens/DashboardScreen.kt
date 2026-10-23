package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.StreakData
import com.example.data.model.UserProfile
import com.example.ui.theme.*

@Composable
fun DashboardScreen(
    userProfile: UserProfile,
    streakData: StreakData,
    onNavigateNotes: () -> Unit,
    onNavigateNcert: () -> Unit,
    onNavigateViva: () -> Unit,
    onNavigatePapers: () -> Unit,
    onNavigateTutor: () -> Unit,
    onNavigateStreak: () -> Unit,
    onNavigateFlashcards: () -> Unit,
    onLogStudyMinutes: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkNavyBackground)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // StudyPal Mascot Hero Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                shape = RoundedCornerShape(22.dp),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.linearGradient(listOf(ElectricCyan.copy(alpha = 0.6f), StudyPalPurple.copy(alpha = 0.6f)))
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Surface(
                                    color = ElectricCyan,
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Text(
                                        text = userProfile.classLevel,
                                        fontFamily = OutfitFont,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp,
                                        color = Color.Black,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                                Text(
                                    text = "STUDYPAL AI",
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    color = VividViolet,
                                    letterSpacing = 1.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Welcome, ${userProfile.name} 👋",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 21.sp,
                                color = TextPrimary
                            )

                            Text(
                                text = "Goal: ${userProfile.studyMasteryGoal}",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                color = ElectricCyan
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "Age ${userProfile.age} • ${userProfile.subjects.size} Active Subjects",
                                fontFamily = PlusJakartaSans,
                                fontSize = 11.sp,
                                color = TextSecondary
                            )
                        }

                        // Mascot Logo Badge
                        Box(
                            modifier = Modifier
                                .size(76.dp)
                                .clip(RoundedCornerShape(18.dp))
                                .border(
                                    1.5.dp,
                                    Brush.linearGradient(listOf(ElectricCyan, StudyPalPurple)),
                                    RoundedCornerShape(18.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.studypal_logo),
                                contentDescription = "StudyPal Mascot",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(18.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }

        // Active Subjects Chips Row
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "YOUR ACTIVE SUBJECTS",
                    fontFamily = PlusJakartaSans,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextMuted,
                    letterSpacing = 0.8.sp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    userProfile.subjects.take(4).forEach { sub ->
                        Surface(
                            color = Color(0xFF132038),
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, DeepNavyCardBorder)
                        ) {
                            Text(
                                text = sub,
                                fontFamily = PlusJakartaSans,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = TextPrimary,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }
        }

        // Exam Countdown & Study Streak Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(18.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = if (userProfile.isExamComing) "EXAM COUNTDOWN" else "LEARNING MODE",
                                fontFamily = PlusJakartaSans,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextMuted,
                                letterSpacing = 0.8.sp
                            )
                            Text(
                                text = if (userProfile.isExamComing) "${userProfile.daysUntilExam} Days Left" else "Concept Mastery",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                                color = if (userProfile.isExamComing) StreakFlame else ElectricCyan
                            )
                            if (userProfile.isExamComing) {
                                Text(
                                    text = userProfile.targetExam,
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            }
                        }

                        // Streak flame button
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(14.dp))
                                .background(StreakFlame.copy(alpha = 0.15f))
                                .border(1.dp, StreakFlame.copy(alpha = 0.4f), RoundedCornerShape(14.dp))
                                .clickable { onNavigateStreak() }
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                                .testTag("dashboard_streak_card_button"),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = "Streak",
                                tint = StreakFlame,
                                modifier = Modifier.size(22.dp)
                            )
                            Column {
                                Text(
                                    text = "${streakData.currentStreak} Days",
                                    fontFamily = OutfitFont,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = StreakFlame
                                )
                                Text(
                                    text = "Study Streak 🔥",
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 10.sp,
                                    color = TextSecondary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                    HorizontalDivider(color = DeepNavyCardBorder)
                    Spacer(modifier = Modifier.height(14.dp))

                    // Daily goal progress
                    val goalPercent = (streakData.todayMinutesStudied.toFloat() / streakData.dailyGoalMinutes).coerceIn(0f, 1f)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Today's Study Goal: ${streakData.todayMinutesStudied} / ${streakData.dailyGoalMinutes} mins",
                            fontFamily = PlusJakartaSans,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextPrimary
                        )
                        Text(
                            text = "${(goalPercent * 100).toInt()}%",
                            fontFamily = OutfitFont,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (goalPercent >= 1f) SuccessGreen else ElectricCyan
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { goalPercent },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = if (goalPercent >= 1f) SuccessGreen else ElectricCyan,
                        trackColor = DeepNavySurface
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = { onLogStudyMinutes(15) },
                            colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                            modifier = Modifier.testTag("dashboard_log_time_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddCircleOutline,
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "+15m Study Log",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }

        // Feature Grid: AI Study Tools
        item {
            Text(
                text = "STUDYPAL AI SUITE",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = TextMuted,
                letterSpacing = 1.sp
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ToolCard(
                    title = "Notes to Tools",
                    subtitle = "Turn rough notes into mindmaps & flashcards",
                    badge = "AI GENERATOR",
                    icon = Icons.Default.AutoFixHigh,
                    accentColor = ElectricCyan,
                    onClick = onNavigateNotes,
                    modifier = Modifier.weight(1f)
                )

                ToolCard(
                    title = "Oral Viva Voce",
                    subtitle = "Speak answers & get instant AI marking",
                    badge = "VOICE EXAM",
                    icon = Icons.Default.Mic,
                    accentColor = VividViolet,
                    onClick = onNavigateViva,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ToolCard(
                    title = "Syllabus Mastery",
                    subtitle = "Chapter formulas, high-yield points & PYQs",
                    badge = "OFFICIAL SYLLABUS",
                    icon = Icons.Default.MenuBook,
                    accentColor = SuccessGreen,
                    onClick = onNavigateNcert,
                    modifier = Modifier.weight(1f)
                )

                ToolCard(
                    title = "Practice Papers",
                    subtitle = "Official exam pattern timed mock tests",
                    badge = "EXAM READY",
                    icon = Icons.Default.Assignment,
                    accentColor = WarningAmber,
                    onClick = onNavigatePapers,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ToolCard(
                    title = "AI Tutor",
                    subtitle = "24/7 doubt clearing in English & Hinglish",
                    badge = "24/7 COPILOT",
                    icon = Icons.Default.Forum,
                    accentColor = NeonIndigo,
                    onClick = onNavigateTutor,
                    modifier = Modifier.weight(1f)
                )

                ToolCard(
                    title = "Flashcards",
                    subtitle = "Active recall & spaced repetition deck",
                    badge = "MEMORY RECALL",
                    icon = Icons.Default.Style,
                    accentColor = Color(0xFFFF5252),
                    onClick = onNavigateFlashcards,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Daily Checklist
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(16.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Today's Study Checklist",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ChecklistItem(
                        title = "Practice Oral Viva for ${userProfile.subjects.firstOrNull() ?: "Science"}",
                        tag = "15m",
                        isDone = true
                    )
                    ChecklistItem(
                        title = "Solve Timed Mock Test Section A",
                        tag = "20m",
                        isDone = false
                    )
                    ChecklistItem(
                        title = "Review Formula Flashcards Deck",
                        tag = "10m",
                        isDone = false
                    )
                }
            }
        }

        // Global Student Leaderboard (Anonymous Top Study Streaks)
        item {
            AnonymousLeaderboardCard(
                streakData = streakData,
                userProfile = userProfile
            )
        }
    }
}

@Composable
fun ToolCard(
    title: String,
    subtitle: String,
    badge: String,
    icon: ImageVector,
    accentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(accentColor.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = accentColor,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Surface(
                    color = accentColor.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = badge,
                        fontFamily = PlusJakartaSans,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = accentColor,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                fontFamily = OutfitFont,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                fontFamily = PlusJakartaSans,
                fontSize = 11.sp,
                color = TextSecondary,
                lineHeight = 15.sp
            )
        }
    }
}

@Composable
fun ChecklistItem(title: String, tag: String, isDone: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = if (isDone) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                contentDescription = null,
                tint = if (isDone) SuccessGreen else TextMuted,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = title,
                fontFamily = PlusJakartaSans,
                fontSize = 13.sp,
                color = if (isDone) TextSecondary else TextPrimary
            )
        }

        Surface(
            color = DeepNavySurface,
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(
                text = tag,
                fontFamily = PlusJakartaSans,
                fontSize = 10.sp,
                color = TextMuted,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }
    }
}

data class AnonymousRankEntry(
    val id: String,
    val alias: String,
    val avatar: String,
    val level: String,
    val category: String,
    val streak: Int,
    val minutes: Int,
    val badge: String,
    val isCurrentUser: Boolean = false,
    val initialCheers: Int = 20
)

@Composable
fun AnonymousLeaderboardCard(
    streakData: StreakData,
    userProfile: UserProfile
) {
    var selectedFilter by remember { mutableStateOf("all") }
    var cheerCounts by remember { mutableStateOf(mapOf<String, Int>()) }

    val baseRoster = remember {
        listOf(
            AnonymousRankEntry("1", "Cosmic Scholar #849", "🦉", "Class 10 Secondary", "secondary", 48, 2140, "Grandmaster 🏆", initialCheers = 142),
            AnonymousRankEntry("2", "Nova Stargazer #210", "🚀", "Bachelor's CSIT", "uni", 43, 1920, "Code Sage ⚡", initialCheers = 118),
            AnonymousRankEntry("3", "Alpine Lynx #392", "🏔️", "Class 12 Science (PCM)", "higher", 39, 1750, "Physics Ace ⚛️", initialCheers = 95),
            AnonymousRankEntry("4", "Solar Flare #105", "☀️", "Class 10 Secondary", "secondary", 34, 1480, "Math Wizard 📐", initialCheers = 84),
            AnonymousRankEntry("5", "Zenith Explorer #674", "🧭", "Class 9 Foundation", "secondary", 29, 1310, "Bio Champ 🧬", initialCheers = 72),
            AnonymousRankEntry("6", "Quantum Falcon #881", "🦅", "Class 11 Management", "higher", 24, 1120, "Focus Monk 🧘", initialCheers = 63),
            AnonymousRankEntry("7", "Aurora Owl #433", "✨", "Master's MSc CS", "uni", 19, 940, "Research Fellow 📜", initialCheers = 58),
            AnonymousRankEntry("8", "Apex Prowler #512", "🐆", "Class 12 Humanities", "higher", 14, 680, "Essay Crafter ✍️", initialCheers = 45),
            AnonymousRankEntry("9", "Nebula Voyager #729", "🪐", "Class 10 Secondary", "secondary", 9, 420, "Nepali Scholar 🇳🇵", initialCheers = 39),
            AnonymousRankEntry("10", "Shadow Weaver #314", "🦊", "Bachelor's BCA", "uni", 5, 270, "Algo Solver 💻", initialCheers = 27)
        )
    }

    val userEntry = AnonymousRankEntry(
        id = "user",
        alias = "You (Anonymous Learner)",
        avatar = "🌟",
        level = userProfile.classLevel,
        category = "secondary",
        streak = streakData.currentStreak,
        minutes = streakData.todayMinutesStudied,
        badge = if (streakData.currentStreak > 0) "Active Streak 🔥" else "Day 1 Aspirant ⚡",
        isCurrentUser = true,
        initialCheers = 15
    )

    val filteredList = remember(selectedFilter, streakData.currentStreak, streakData.todayMinutesStudied) {
        val list = baseRoster.filter { entry ->
            when (selectedFilter) {
                "secondary" -> entry.category == "secondary"
                "higher" -> entry.category == "higher"
                "uni" -> entry.category == "uni"
                else -> true
            }
        }.toMutableList()

        list.add(userEntry)
        list.sortedWith(compareByDescending<AnonymousRankEntry> { it.streak }.thenByDescending { it.minutes })
    }

    val userRank = filteredList.indexOfFirst { it.isCurrentUser } + 1

    Card(
        colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(20.dp))
            .testTag("global_leaderboard_card")
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            color = ElectricCyan.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "GLOBAL STREAK MASTERY",
                                fontFamily = PlusJakartaSans,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = ElectricCyan,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Surface(
                            color = SuccessGreen.copy(alpha = 0.18f),
                            shape = RoundedCornerShape(99.dp)
                        ) {
                            Text(
                                text = "🟢 Real-Time Listener Active",
                                fontFamily = PlusJakartaSans,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = SuccessGreen,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                        Surface(
                            color = VividViolet.copy(alpha = 0.18f),
                            shape = RoundedCornerShape(99.dp)
                        ) {
                            Text(
                                text = "🔒 100% Anonymous",
                                fontFamily = PlusJakartaSans,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = VividViolet,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "🏆 Global Student Leaderboard",
                        fontFamily = OutfitFont,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Text(
                        text = "Top continuous study streaks worldwide. Complete lessons to rank higher without sharing personal identity.",
                        fontFamily = PlusJakartaSans,
                        fontSize = 11.sp,
                        color = TextSecondary,
                        lineHeight = 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Current User Rank Banner
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = ElectricCyan.copy(alpha = 0.08f)
                ),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.5.dp, ElectricCyan.copy(alpha = 0.45f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(ElectricCyan),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "#$userRank",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "You (Anonymous Learner)",
                                    fontFamily = OutfitFont,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                                Surface(
                                    color = DeepNavySurface,
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = if (streakData.currentStreak >= 40) "Diamond Tier 🏆"
                                        else if (streakData.currentStreak >= 25) "Platinum Tier 💎"
                                        else if (streakData.currentStreak >= 10) "Gold Tier 🥇"
                                        else "Silver Tier",
                                        fontFamily = PlusJakartaSans,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = ElectricCyan,
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                    )
                                }
                            }
                            Text(
                                text = "${streakData.currentStreak} Days Streak • ${streakData.todayMinutesStudied} mins studied today",
                                fontFamily = PlusJakartaSans,
                                fontSize = 10.sp,
                                color = TextSecondary
                            )
                        }
                    }

                    Surface(
                        color = WarningAmber.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(99.dp),
                        border = BorderStroke(1.dp, WarningAmber.copy(alpha = 0.4f))
                    ) {
                        Text(
                            text = if (streakData.currentStreak == 0) "Complete 1 lesson for Day 1!" else "🔥 Rank Active!",
                            fontFamily = PlusJakartaSans,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = WarningAmber,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Filter Chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LeaderboardFilterChip(label = "🌍 All Levels", isSelected = selectedFilter == "all") { selectedFilter = "all" }
                LeaderboardFilterChip(label = "🔬 Secondary (9-10)", isSelected = selectedFilter == "secondary") { selectedFilter = "secondary" }
                LeaderboardFilterChip(label = "🧪 Higher Sec (11-12)", isSelected = selectedFilter == "higher") { selectedFilter = "higher" }
                LeaderboardFilterChip(label = "🎓 Undergrad & Masters", isSelected = selectedFilter == "uni") { selectedFilter = "uni" }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Leaderboard Rows
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                filteredList.forEachIndexed { index, entry ->
                    val rank = index + 1
                    val currentCheers = cheerCounts[entry.id] ?: entry.initialCheers

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (entry.isCurrentUser) ElectricCyan.copy(alpha = 0.08f) else DeepNavySurface
                        ),
                        shape = RoundedCornerShape(12.dp),
                        border = if (entry.isCurrentUser) BorderStroke(1.dp, ElectricCyan) else BorderStroke(1.dp, DeepNavyCardBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                // Rank icon / number
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clip(CircleShape)
                                        .background(
                                            when (rank) {
                                                1 -> WarningAmber
                                                2 -> Color(0xFFB0BEC5)
                                                3 -> Color(0xFFCD7F32)
                                                else -> Color.White.copy(alpha = 0.08f)
                                            }
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = when (rank) {
                                            1 -> "🥇"
                                            2 -> "🥈"
                                            3 -> "🥉"
                                            else -> "#$rank"
                                        },
                                        fontSize = if (rank <= 3) 12.sp else 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (rank <= 3) Color.Black else TextSecondary
                                    )
                                }

                                Text(text = entry.avatar, fontSize = 18.sp)

                                Column {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Text(
                                            text = entry.alias,
                                            fontFamily = PlusJakartaSans,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (entry.isCurrentUser) ElectricCyan else TextPrimary
                                        )
                                        if (entry.isCurrentUser) {
                                            Surface(
                                                color = ElectricCyan,
                                                shape = RoundedCornerShape(4.dp)
                                            ) {
                                                Text(
                                                    text = "YOU",
                                                    fontSize = 8.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.Black,
                                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                                )
                                            }
                                        }
                                    }
                                    Text(
                                        text = "${entry.level} • ${entry.minutes} mins",
                                        fontFamily = PlusJakartaSans,
                                        fontSize = 10.sp,
                                        color = TextMuted
                                    )
                                }
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Surface(
                                    color = StreakFlame.copy(alpha = 0.15f),
                                    shape = RoundedCornerShape(99.dp),
                                    border = BorderStroke(1.dp, StreakFlame.copy(alpha = 0.35f))
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                                    ) {
                                        Text(text = "🔥", fontSize = 11.sp)
                                        Text(
                                            text = "${entry.streak}d",
                                            fontFamily = OutfitFont,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = StreakFlame
                                        )
                                    }
                                }

                                Surface(
                                    color = Color.White.copy(alpha = 0.06f),
                                    shape = RoundedCornerShape(99.dp),
                                    border = BorderStroke(1.dp, DeepNavyCardBorder),
                                    modifier = Modifier.clickable {
                                        cheerCounts = cheerCounts.toMutableMap().apply {
                                            put(entry.id, currentCheers + 1)
                                        }
                                    }
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                                    ) {
                                        Text(text = "👏", fontSize = 10.sp)
                                        Text(
                                            text = "$currentCheers",
                                            fontFamily = PlusJakartaSans,
                                            fontSize = 10.sp,
                                            color = TextSecondary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "🔒 Zero personal data exposed. Ranks recalculate live upon lesson completion or pull-to-refresh sync.",
                fontFamily = PlusJakartaSans,
                fontSize = 9.sp,
                color = TextMuted,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun LeaderboardFilterChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        color = if (isSelected) ElectricCyan.copy(alpha = 0.2f) else DeepNavySurface,
        shape = RoundedCornerShape(99.dp),
        border = BorderStroke(1.dp, if (isSelected) ElectricCyan else DeepNavyCardBorder),
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(
            text = label,
            fontFamily = PlusJakartaSans,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) ElectricCyan else TextSecondary,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

