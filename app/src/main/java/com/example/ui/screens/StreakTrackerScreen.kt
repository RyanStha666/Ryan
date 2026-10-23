package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.example.data.model.StreakData
import com.example.ui.theme.*

@Composable
fun StreakTrackerScreen(
    streakData: StreakData,
    onLogMinutes: (Int) -> Unit,
    onUseShield: () -> Unit,
    onStartPractice: () -> Unit
) {
    var selectedGoalMinutes by remember { mutableIntStateOf(streakData.dailyGoalMinutes) }
    var shieldMessage by remember { mutableStateOf<String?>(null) }

    val todayProgress = (streakData.todayMinutesStudied.toFloat() / streakData.dailyGoalMinutes).coerceIn(0f, 1f)
    val animatedProgress by animateFloatAsState(targetValue = todayProgress, label = "progress")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkNavyBackground)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Streak Flame Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.5.dp,
                        brush = Brush.verticalGradient(
                            listOf(StreakFlame, DeepNavyCardBorder)
                        ),
                        shape = RoundedCornerShape(24.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    listOf(StreakFlame, Color(0xFF6B2200))
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocalFireDepartment,
                            contentDescription = "Streak Flame",
                            tint = Color.White,
                            modifier = Modifier.size(46.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "${streakData.currentStreak} Day Study Streak",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = StreakFlame
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (todayProgress >= 1f)
                            "🎉 Daily goal achieved! Your streak is safely extended!"
                        else
                            "Study ${streakData.dailyGoalMinutes - streakData.todayMinutesStudied} more mins today to keep your flame burning!",
                        fontFamily = PlusJakartaSans,
                        fontSize = 13.sp,
                        color = TextSecondary,
                        modifier = Modifier.padding(horizontal = 12.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Progress circle / bar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Today: ${streakData.todayMinutesStudied}m / ${streakData.dailyGoalMinutes}m",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = TextPrimary
                        )
                        Text(
                            text = "${(todayProgress * 100).toInt()}% Done",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = if (todayProgress >= 1f) SuccessGreen else ElectricCyan
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { animatedProgress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        color = if (todayProgress >= 1f) SuccessGreen else StreakFlame,
                        trackColor = DeepNavySurface
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = { onLogMinutes(10) },
                            colors = ButtonDefaults.buttonColors(containerColor = StreakFlame),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("log_10m_button")
                        ) {
                            Text(
                                text = "+10 Mins Study",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = Color.White
                            )
                        }
                        Button(
                            onClick = onStartPractice,
                            colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("start_quiz_streak_button")
                        ) {
                            Text(
                                text = "Practice Now",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }

        // Weekly Heatmap Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(18.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "This Week's Activity Heatmap",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        streakData.weeklyHistory.forEach { day ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(38.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (day.completed)
                                                Brush.radialGradient(listOf(StreakFlame, Color(0xFF6B2200)))
                                            else
                                                Brush.radialGradient(listOf(DeepNavySurface, DeepNavyCardBorder))
                                        )
                                        .border(
                                            1.dp,
                                            if (day.completed) StreakFlame else Color.Transparent,
                                            CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (day.completed) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = "Completed",
                                            tint = Color.White,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    } else {
                                        Text(
                                            text = "${day.minutesStudied}m",
                                            fontFamily = PlusJakartaSans,
                                            fontSize = 10.sp,
                                            color = TextMuted
                                        )
                                    }
                                }

                                Text(
                                    text = day.dayLabel,
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = if (day.completed) StreakFlame else TextSecondary
                                )
                            }
                        }
                    }
                }
            }
        }

        // Streak Shield & Protection
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
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(ElectricCyan.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Shield,
                                    contentDescription = "Streak Shield",
                                    tint = ElectricCyan,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Column {
                                Text(
                                    text = "Streak Freeze Shields",
                                    fontFamily = OutfitFont,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = TextPrimary
                                )
                                Text(
                                    text = "${streakData.shieldsRemaining} Shields Available",
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 12.sp,
                                    color = ElectricCyan
                                )
                            }
                        }

                        Button(
                            onClick = {
                                if (streakData.shieldsRemaining > 0) {
                                    onUseShield()
                                    shieldMessage = "Shield activated for today! Your streak is protected."
                                } else {
                                    shieldMessage = "No shields remaining! Complete a 7-day streak to earn another."
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = DeepNavySurface),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = Brush.linearGradient(listOf(ElectricCyan, NeonIndigo))
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.testTag("use_streak_shield_button")
                        ) {
                            Text(
                                text = "Use Shield",
                                fontFamily = OutfitFont,
                                fontSize = 12.sp,
                                color = ElectricCyan
                            )
                        }
                    }

                    if (shieldMessage != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = shieldMessage!!,
                            fontFamily = PlusJakartaSans,
                            fontSize = 12.sp,
                            color = SuccessGreen
                        )
                    }
                }
            }
        }

        // Streak Milestones & Badges
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DeepNavyCardBorder, RoundedCornerShape(18.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "CBSE Streak Milestones",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    MilestoneRow("3-Day Spark", "Completed", true, Icons.Default.EmojiEvents, StreakFlame)
                    MilestoneRow("7-Day Flame Scholar", "Achieved today!", true, Icons.Default.LocalFireDepartment, StreakFlame)
                    MilestoneRow("14-Day Board Exam Master", "7 days to go", false, Icons.Default.MilitaryTech, NeonIndigo)
                    MilestoneRow("30-Day CBSE Legend", "23 days to go", false, Icons.Default.WorkspacePremium, VividViolet)
                }
            }
        }
    }
}

@Composable
fun MilestoneRow(
    title: String,
    subtitle: String,
    isUnlocked: Boolean,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    accentColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(if (isUnlocked) accentColor.copy(alpha = 0.2f) else DeepNavySurface),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = if (isUnlocked) accentColor else TextMuted,
                    modifier = Modifier.size(20.dp)
                )
            }
            Column {
                Text(
                    text = title,
                    fontFamily = OutfitFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = if (isUnlocked) TextPrimary else TextSecondary
                )
                Text(
                    text = subtitle,
                    fontFamily = PlusJakartaSans,
                    fontSize = 11.sp,
                    color = if (isUnlocked) accentColor else TextMuted
                )
            }
        }

        if (isUnlocked) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Unlocked",
                tint = SuccessGreen,
                modifier = Modifier.size(20.dp)
            )
        } else {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Locked",
                tint = TextMuted,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
