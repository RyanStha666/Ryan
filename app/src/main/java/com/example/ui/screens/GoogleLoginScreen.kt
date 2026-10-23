package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.*

@Composable
fun GoogleLoginScreen(
    userEmailDefault: String = "learner@studypal.internal",
    userNameDefault: String = "Learner",
    onGoogleSignIn: (email: String, name: String) -> Unit = { _, _ -> },
    onGuestLogin: () -> Unit = {}
) {
    var showAccountChooser by remember { mutableStateOf(false) }
    var customEmail by remember { mutableStateOf(userEmailDefault) }
    var customName by remember { mutableStateOf(userNameDefault) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkNavyBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // StudyPal Robot Mascot Logo Card
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(
                        Brush.radialGradient(
                            listOf(Color(0xFF1E284A), DarkNavyBackground)
                        )
                    )
                    .border(
                        2.dp,
                        Brush.linearGradient(listOf(ElectricCyan, StudyPalPurple)),
                        RoundedCornerShape(32.dp)
                    )
                    .shadow(16.dp, RoundedCornerShape(32.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.studypal_logo),
                    contentDescription = "StudyPal Mascot Logo",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(32.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // App Name with Gradient Accent
            Text(
                text = "StudyPal",
                fontFamily = OutfitFont,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 38.sp,
                color = Color.White,
                letterSpacing = 0.5.sp
            )

            Text(
                text = "AI Study Companion & Exam Mastery",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = ElectricCyan,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Tailored study routines, oral viva voce tests, notes summarizer & CBSE board preparation.",
                fontFamily = PlusJakartaSans,
                fontSize = 13.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Feature Highlights
            Card(
                colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                shape = RoundedCornerShape(20.dp),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(DeepNavyCardBorder, Color(0xFF2E3E66)))),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    LoginFeatureRow(
                        icon = Icons.Default.School,
                        iconTint = ElectricCyan,
                        title = "Personalized Study Curriculum",
                        subtitle = "Targeted for your class, subjects & upcoming board exams"
                    )
                    LoginFeatureRow(
                        icon = Icons.Default.AutoAwesome,
                        iconTint = StudyPalPurple,
                        title = "AI Tutor & Oral Viva Tests",
                        subtitle = "Speak your answers & get instant examiner feedback"
                    )
                    LoginFeatureRow(
                        icon = Icons.Default.CheckCircle,
                        iconTint = SuccessGreen,
                        title = "Daily Study Streak & Goals",
                        subtitle = "Stay accountable with flame streaks, shields & countdowns"
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Google Sign In Button
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(16.dp),
                shadowElevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .clickable {
                        showAccountChooser = true
                    }
                    .testTag("google_sign_in_button")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    GoogleGLogo()

                    Spacer(modifier = Modifier.width(14.dp))

                    Text(
                        text = "Continue with Google",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF1F2937)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Quick Guest Preview Option
            TextButton(
                onClick = onGuestLogin,
                modifier = Modifier.testTag("guest_login_button")
            ) {
                Text(
                    text = "Explore as Guest Demo",
                    fontFamily = PlusJakartaSans,
                    fontSize = 13.sp,
                    color = TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "By signing in, your study progress and notes are securely synced.",
                fontFamily = PlusJakartaSans,
                fontSize = 11.sp,
                color = TextMuted,
                textAlign = TextAlign.Center
            )
        }

        // Google Account Chooser Modal Dialog
        if (showAccountChooser) {
            AlertDialog(
                onDismissRequest = { showAccountChooser = false },
                containerColor = DeepNavySurface,
                titleContentColor = Color.White,
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        GoogleGLogo()
                        Text(
                            text = "Choose Google Account",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                },
                text = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Select an account to sign in to StudyPal:",
                            fontFamily = PlusJakartaSans,
                            fontSize = 13.sp,
                            color = TextSecondary
                        )

                        // Google Account Item (Detected Account)
                        Surface(
                            color = DeepNavyCard,
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, ElectricCyan.copy(alpha = 0.5f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    showAccountChooser = false
                                    onGoogleSignIn(customEmail, customName)
                                }
                                .testTag("select_google_account_card")
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(42.dp)
                                        .clip(CircleShape)
                                        .background(
                                            Brush.linearGradient(
                                                listOf(ElectricCyan, StudyPalPurple)
                                            )
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = customName.split(" ").mapNotNull { it.firstOrNull()?.toString() }.take(2).joinToString("").uppercase(),
                                        fontFamily = OutfitFont,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = Color.Black
                                    )
                                }

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = customName,
                                        fontFamily = OutfitFont,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp,
                                        color = TextPrimary
                                    )
                                    Text(
                                        text = customEmail,
                                        fontFamily = PlusJakartaSans,
                                        fontSize = 12.sp,
                                        color = TextSecondary
                                    )
                                }

                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Active",
                                    tint = ElectricCyan,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                        OutlinedTextField(
                            value = customName,
                            onValueChange = { customName = it },
                            label = { Text("Display Name") },
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
                            value = customEmail,
                            onValueChange = { customEmail = it },
                            label = { Text("Google Email") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = ElectricCyan,
                                unfocusedBorderColor = DeepNavyCardBorder,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showAccountChooser = false
                            onGoogleSignIn(customEmail, customName)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.testTag("confirm_google_sign_in_button")
                    ) {
                        Text(
                            text = "Sign In as $customName",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAccountChooser = false }) {
                        Text("Cancel", color = TextSecondary)
                    }
                }
            )
        }
    }
}

@Composable
fun GoogleGLogo() {
    Surface(
        modifier = Modifier.size(24.dp),
        shape = CircleShape,
        color = Color.Transparent
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "G",
                fontFamily = OutfitFont,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = Color(0xFF4285F4)
            )
        }
    }
}

@Composable
fun LoginFeatureRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    title: String,
    subtitle: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(iconTint.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontFamily = OutfitFont,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = TextPrimary
            )
            Text(
                text = subtitle,
                fontFamily = PlusJakartaSans,
                fontSize = 12.sp,
                color = TextSecondary,
                lineHeight = 16.sp
            )
        }
    }
}
