package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.UserProfile
import com.example.ui.theme.*

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen(
    userProfile: UserProfile,
    onSaveProfile: (
        name: String,
        classLevel: String,
        age: Int,
        subjects: List<String>,
        isExamComing: Boolean,
        targetExam: String,
        daysUntilExam: Int,
        studyMasteryGoal: String,
        dailyGoalMinutes: Int,
        preferredLanguage: String
    ) -> Unit,
    onLogout: () -> Unit = {}
) {
    var name by remember { mutableStateOf(userProfile.name) }
    var classLevel by remember { mutableStateOf(userProfile.classLevel) }
    var age by remember { mutableIntStateOf(userProfile.age) }
    var subjects by remember { mutableStateOf(userProfile.subjects) }
    var isExamComing by remember { mutableStateOf(userProfile.isExamComing) }
    var targetExam by remember { mutableStateOf(userProfile.targetExam) }
    var daysUntilExam by remember { mutableIntStateOf(userProfile.daysUntilExam) }
    var studyMasteryGoal by remember { mutableStateOf(userProfile.studyMasteryGoal) }
    var dailyGoalMinutes by remember { mutableIntStateOf(userProfile.dailyGoalMinutes) }
    var preferredLanguage by remember { mutableStateOf(userProfile.preferredLanguage) }

    var savedSuccess by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }

    val educationTiers = listOf(
        "Primary (Classes 1-5)",
        "Middle (Classes 6-8)",
        "Secondary (Class 9-10)",
        "Higher Sec (Class 11-12)",
        "Bachelors / College",
        "Masters / Postgrad"
    )

    val commonClasses = listOf(
        "Class 5",
        "Class 8",
        "Class 10 CBSE",
        "Class 11 Science (PCM)",
        "Class 12 Science (PCM)",
        "Class 12 Commerce",
        "B.Tech Computer Science",
        "B.Sc. Mathematics",
        "MBA Finance",
        "M.Tech AI"
    )

    val sampleSubjectsPool = listOf(
        "Mathematics", "Science", "Physics", "Chemistry", "Biology",
        "Social Science", "English", "Computer Science", "Economics", "Data Structures"
    )

    val goalOptions = listOf(30, 45, 60, 90, 120)
    val languages = listOf("English", "Hindi", "Hinglish")
    val masteryOptions = listOf(
        "95%+ Board Ranker Mastery",
        "90%+ Distinction",
        "85%+ High Performer",
        "Solid Fundamentals & Passing"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkNavyBackground)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Google Account Header Card
        Card(
            colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(1.dp, DeepNavyCardBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(
                                    listOf(ElectricCyan, StudyPalPurple)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = userProfile.avatarInitials,
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = Color.Black
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = name,
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = TextPrimary
                        )
                        Text(
                            text = userProfile.email,
                            fontFamily = PlusJakartaSans,
                            fontSize = 12.sp,
                            color = TextSecondary
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = SuccessGreen,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "Google Account Synced",
                                fontFamily = PlusJakartaSans,
                                fontSize = 11.sp,
                                color = SuccessGreen
                            )
                        }
                    }
                }

                HorizontalDivider(color = DeepNavyCardBorder)

                // Log Out Action Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Authentication & Account",
                        fontFamily = PlusJakartaSans,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )

                    OutlinedButton(
                        onClick = { showLogoutDialog = true },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = ErrorRed),
                        border = BorderStroke(1.dp, ErrorRed.copy(alpha = 0.5f)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .height(36.dp)
                            .testTag("logout_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Log Out",
                            tint = ErrorRed,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Log Out",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        // 2. Study Profile Questionnaire Preferences Card
        Card(
            colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(1.dp, DeepNavyCardBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Study Profile & Academic Settings",
                    fontFamily = OutfitFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = ElectricCyan
                )

                // Name
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Student Name") },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = ElectricCyan,
                        unfocusedBorderColor = DeepNavyCardBorder,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("profile_name_field")
                )

                // Age
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Student Age: $age years",
                        fontFamily = PlusJakartaSans,
                        fontSize = 13.sp,
                        color = TextSecondary
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        listOf(12, 14, 16, 18, 20, 22).forEach { a ->
                            val isSel = age == a
                            Surface(
                                color = if (isSel) StudyPalPurple else DeepNavySurface,
                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, if (isSel) VividViolet else DeepNavyCardBorder),
                                modifier = Modifier
                                    .clickable { age = a }
                                    .padding(2.dp)
                            ) {
                                Text(
                                    text = "$a",
                                    fontFamily = OutfitFont,
                                    fontSize = 12.sp,
                                    color = if (isSel) Color.White else TextPrimary,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }
                }

                // Class / Degree (Class 1 to Master's Degree)
                Text(
                    text = "Current Class / Degree Level:",
                    fontFamily = PlusJakartaSans,
                    fontSize = 13.sp,
                    color = TextSecondary
                )
                OutlinedTextField(
                    value = classLevel,
                    onValueChange = { classLevel = it },
                    label = { Text("Class or Degree Program") },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = ElectricCyan,
                        unfocusedBorderColor = DeepNavyCardBorder,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                // Quick preset chips
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    commonClasses.forEach { cls ->
                        val isSel = classLevel == cls
                        Surface(
                            color = if (isSel) ElectricCyan else DeepNavySurface,
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, if (isSel) ElectricCyan else DeepNavyCardBorder),
                            modifier = Modifier.clickable { classLevel = cls }
                        ) {
                            Text(
                                text = cls,
                                fontFamily = OutfitFont,
                                fontSize = 11.sp,
                                fontWeight = if (isSel) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSel) Color.Black else TextPrimary,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                            )
                        }
                    }
                }

                // Subjects Read
                Text(
                    text = "Active Subjects (${subjects.size} selected):",
                    fontFamily = PlusJakartaSans,
                    fontSize = 13.sp,
                    color = TextSecondary
                )
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    sampleSubjectsPool.forEach { sub ->
                        val isSel = subjects.contains(sub)
                        Surface(
                            color = if (isSel) Color(0xFF132238) else DeepNavySurface,
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, if (isSel) ElectricCyan else DeepNavyCardBorder),
                            modifier = Modifier.clickable {
                                subjects = if (isSel) {
                                    if (subjects.size > 1) subjects - sub else subjects
                                } else {
                                    subjects + sub
                                }
                            }
                        ) {
                            Text(
                                text = if (isSel) "✓ $sub" else sub,
                                fontFamily = PlusJakartaSans,
                                fontSize = 12.sp,
                                color = if (isSel) ElectricCyan else TextSecondary,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                // Exam Status & Countdown
                Text(
                    text = "Exam Status:",
                    fontFamily = PlusJakartaSans,
                    fontSize = 13.sp,
                    color = TextSecondary
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Surface(
                        color = if (isExamComing) Color(0xFF2E1A08) else DeepNavySurface,
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, if (isExamComing) StreakFlame else DeepNavyCardBorder),
                        modifier = Modifier
                            .weight(1f)
                            .clickable { isExamComing = true }
                    ) {
                        Text(
                            text = "Exam Coming Up",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = if (isExamComing) StreakFlame else TextPrimary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }

                    Surface(
                        color = if (!isExamComing) Color(0xFF102820) else DeepNavySurface,
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, if (!isExamComing) SuccessGreen else DeepNavyCardBorder),
                        modifier = Modifier
                            .weight(1f)
                            .clickable { isExamComing = false }
                    ) {
                        Text(
                            text = "No Exam Soon",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = if (!isExamComing) SuccessGreen else TextPrimary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }
                }

                if (isExamComing) {
                    OutlinedTextField(
                        value = targetExam,
                        onValueChange = { targetExam = it },
                        label = { Text("Target Examination Name") },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ElectricCyan,
                            unfocusedBorderColor = DeepNavyCardBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = daysUntilExam.toString(),
                        onValueChange = { daysUntilExam = it.toIntOrNull() ?: 0 },
                        label = { Text("Days to Exam Countdown") },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = StreakFlame,
                            unfocusedBorderColor = DeepNavyCardBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Study Mastery Goal
                Text(
                    text = "Study Mastery Goal:",
                    fontFamily = PlusJakartaSans,
                    fontSize = 13.sp,
                    color = TextSecondary
                )
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    masteryOptions.forEach { mGoal ->
                        val isSel = studyMasteryGoal == mGoal
                        Surface(
                            color = if (isSel) Color(0xFF1B1838) else DeepNavySurface,
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, if (isSel) StudyPalPurple else DeepNavyCardBorder),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { studyMasteryGoal = mGoal }
                        ) {
                            Text(
                                text = if (isSel) "⭐ $mGoal" else mGoal,
                                fontFamily = OutfitFont,
                                fontWeight = if (isSel) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 13.sp,
                                color = if (isSel) VividViolet else TextPrimary,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
                            )
                        }
                    }
                }

                // Daily Study Goal Minutes
                Text(
                    text = "Daily Study Target Goal (Minutes):",
                    fontFamily = PlusJakartaSans,
                    fontSize = 13.sp,
                    color = TextSecondary
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    goalOptions.forEach { g ->
                        val isSel = dailyGoalMinutes == g
                        Surface(
                            color = if (isSel) StreakFlame else DeepNavySurface,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { dailyGoalMinutes = g }
                        ) {
                            Text(
                                text = "${g}m",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = if (isSel) Color.White else TextPrimary,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }

                // Preferred Language
                Text(
                    text = "AI Tutor Preferred Language:",
                    fontFamily = PlusJakartaSans,
                    fontSize = 13.sp,
                    color = TextSecondary
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    languages.forEach { l ->
                        val isSel = preferredLanguage == l
                        Surface(
                            color = if (isSel) NeonIndigo else DeepNavySurface,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { preferredLanguage = l }
                        ) {
                            Text(
                                text = l,
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        onSaveProfile(
                            name,
                            classLevel,
                            age,
                            subjects,
                            isExamComing,
                            targetExam,
                            daysUntilExam,
                            studyMasteryGoal,
                            dailyGoalMinutes,
                            preferredLanguage
                        )
                        savedSuccess = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("save_profile_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Save Profile & Mastery Goals",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                if (savedSuccess) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = SuccessGreen,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Profile and mastery goals updated successfully!",
                            fontFamily = PlusJakartaSans,
                            fontSize = 12.sp,
                            color = SuccessGreen
                        )
                    }
                }
            }
        }
    }

    // Logout Confirmation Dialog
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            containerColor = DeepNavySurface,
            titleContentColor = Color.White,
            title = {
                Text(
                    text = "Log Out of StudyPal?",
                    fontFamily = OutfitFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to sign out of your Google account (${userProfile.email})? You can sign back in anytime to continue your streak.",
                    fontFamily = PlusJakartaSans,
                    fontSize = 13.sp,
                    color = TextSecondary
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showLogoutDialog = false
                        onLogout()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ErrorRed),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.testTag("confirm_logout_button")
                ) {
                    Text("Log Out", color = Color.White, fontFamily = OutfitFont, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancel", color = TextSecondary)
                }
            }
        )
    }
}
