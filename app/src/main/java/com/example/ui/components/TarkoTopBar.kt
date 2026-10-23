package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarkoTopBar(
    title: String = "StudyPal",
    userProfile: UserProfile,
    streakData: StreakData,
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    onStreakClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    Surface(
        color = DeepNavySurface,
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (showBackButton) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(DeepNavyCard)
                            .testTag("top_bar_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = ElectricCyan
                        )
                    }
                } else {
                    // StudyPal Mascot Avatar Icon in TopBar
                    Image(
                        painter = painterResource(id = R.drawable.studypal_logo),
                        contentDescription = "StudyPal Mascot Logo",
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.dp, ElectricCyan.copy(alpha = 0.5f), RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Column {
                    Text(
                        text = title,
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp,
                        color = Color.White,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = "${userProfile.classLevel} • ${userProfile.name}",
                        fontFamily = PlusJakartaSans,
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Streak Flame Badge (Clickable to Streak Tracker)
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            Brush.horizontalGradient(
                                listOf(Color(0xFF331600), Color(0xFF471F00))
                            )
                        )
                        .border(1.dp, StreakFlame.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
                        .clickable { onStreakClick() }
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                        .testTag("streak_badge_button"),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = "Study Streak",
                        tint = StreakFlame,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "${streakData.currentStreak}d",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = StreakFlame
                    )
                }

                // Profile Avatar Button (Clickable to Profile / Logout)
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                listOf(ElectricCyan, StudyPalPurple)
                            )
                        )
                        .clickable { onProfileClick() }
                        .testTag("profile_avatar_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = userProfile.avatarInitials,
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
