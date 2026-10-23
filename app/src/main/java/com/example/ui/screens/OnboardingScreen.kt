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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

// Data structure for grouped education levels from Class 1 to Master's Degree
data class EducationCategory(
    val id: String,
    val title: String,
    val iconEmoji: String,
    val levels: List<String>,
    val defaultAge: Int,
    val sampleSubjects: List<String>
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OnboardingScreen(
    initialName: String = "Learner",
    initialClass: String = "Class 10 CBSE",
    onComplete: (
        name: String,
        classLevel: String,
        age: Int,
        subjects: List<String>,
        isExamComing: Boolean,
        targetExam: String,
        daysUntilExam: Int,
        studyMasteryGoal: String,
        dailyGoalMinutes: Int
    ) -> Unit
) {
    var currentStep by remember { mutableIntStateOf(0) }

    val educationCategories = remember {
        listOf(
            EducationCategory(
                id = "primary",
                title = "Primary (Classes 1 - 5)",
                iconEmoji = "🎒",
                levels = listOf("Class 1", "Class 2", "Class 3", "Class 4", "Class 5"),
                defaultAge = 9,
                sampleSubjects = listOf("Mathematics", "Environmental Studies (EVS)", "General Science", "English Reading & Grammar", "Hindi", "Social Studies")
            ),
            EducationCategory(
                id = "middle",
                title = "Middle School (Classes 6 - 8)",
                iconEmoji = "📚",
                levels = listOf("Class 6", "Class 7", "Class 8"),
                defaultAge = 13,
                sampleSubjects = listOf("Mathematics", "Science", "History & Civics", "Geography", "English", "Hindi", "Computer Basics")
            ),
            EducationCategory(
                id = "secondary",
                title = "Secondary (Classes 9 - 10)",
                iconEmoji = "🎓",
                levels = listOf("Class 9", "Class 10 CBSE", "Class 10 ICSE", "Class 10 State Board"),
                defaultAge = 15,
                sampleSubjects = listOf("Mathematics", "Science (Physics/Chem/Bio)", "Social Science", "English Language & Lit", "Information Technology", "Hindi Course A/B")
            ),
            EducationCategory(
                id = "higher_sec",
                title = "Higher Secondary (Classes 11 - 12)",
                iconEmoji = "⚡",
                levels = listOf(
                    "Class 11 Science (PCM)",
                    "Class 11 Science (PCB)",
                    "Class 11 Commerce",
                    "Class 11 Humanities",
                    "Class 12 Science (PCM)",
                    "Class 12 Science (PCB)",
                    "Class 12 Commerce",
                    "Class 12 Humanities",
                    "JEE / NEET Aspirant"
                ),
                defaultAge = 17,
                sampleSubjects = listOf("Physics", "Chemistry", "Mathematics", "Biology", "Computer Science", "Accountancy", "Economics", "Business Studies", "English Core")
            ),
            EducationCategory(
                id = "undergrad",
                title = "College / Bachelors Degree",
                iconEmoji = "🏛️",
                levels = listOf(
                    "B.Tech / B.E. (Engineering & CS)",
                    "B.Sc. (Physical & Life Sciences)",
                    "B.Com / BBA (Commerce & Finance)",
                    "B.A. (Humanities & Social Sciences)",
                    "MBBS / Medical & Pharma",
                    "LLB (Law & Legal Studies)"
                ),
                defaultAge = 20,
                sampleSubjects = listOf("Data Structures & Algorithms", "Operating Systems", "Engineering Mathematics", "Database Systems", "Financial Accounting", "Microeconomics", "Constitutional Law", "Pharmacology")
            ),
            EducationCategory(
                id = "postgrad",
                title = "Postgraduate / Masters Degree",
                iconEmoji = "🔬",
                levels = listOf(
                    "M.Tech / M.E. (AI & Advanced Eng)",
                    "M.Sc. (Physics, Chemistry, Math, Data)",
                    "MBA (Management & Finance)",
                    "M.Com / M.A.",
                    "Ph.D. / Research Fellow"
                ),
                defaultAge = 23,
                sampleSubjects = listOf("Machine Learning & AI", "Advanced Algorithms", "Quantitative Finance", "Strategic Management", "Research Methodology", "Advanced Econometrics", "Deep Learning")
            )
        )
    }

    var selectedCategoryIndex by remember { mutableIntStateOf(2) } // Default to Secondary (9-10)
    var studentName by remember { mutableStateOf(initialName) }
    var selectedClass by remember { mutableStateOf(initialClass) }
    var selectedAge by remember { mutableIntStateOf(16) }
    var selectedSubjects by remember {
        mutableStateOf(listOf("Mathematics", "Science (Physics/Chem/Bio)", "Social Science", "English Language & Lit"))
    }
    var isExamComing by remember { mutableStateOf(true) }
    var targetExamTitle by remember { mutableStateOf("CBSE 2027 Board Examination") }
    var daysUntilExam by remember { mutableIntStateOf(124) }
    var studyMasteryGoal by remember { mutableStateOf("95%+ Board Ranker Mastery") }
    var dailyStudyMinutes by remember { mutableIntStateOf(45) }

    val totalSteps = 6

    val masteryGoalOptions = listOf(
        "95%+ Board Ranker Mastery" to "Aiming for top 1% percentile & full marks in theory",
        "90%+ Distinction" to "Consistent A1 grades & strong conceptual clarity",
        "85%+ High Performer" to "High confidence in numericals & syllabus PYQs",
        "Solid Fundamentals & Passing" to "Clear doubts and master core textbook concepts"
    )

    val dailyMinutesOptions = listOf(30, 45, 60, 90, 120)

    Scaffold(
        containerColor = DarkNavyBackground,
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DeepNavySurface)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (currentStep > 0) {
                        IconButton(
                            onClick = { currentStep-- },
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(DeepNavyCard)
                                .testTag("onboarding_back_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = ElectricCyan,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.size(36.dp))
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.studypal_logo),
                            contentDescription = "StudyPal Mascot Logo",
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                        )
                        Text(
                            text = "StudyPal Setup",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = Color.White
                        )
                    }

                    Text(
                        text = "${currentStep + 1}/$totalSteps",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = ElectricCyan
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                LinearProgressIndicator(
                    progress = { (currentStep + 1).toFloat() / totalSteps.toFloat() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = ElectricCyan,
                    trackColor = DeepNavyCardBorder,
                )
            }
        },
        bottomBar = {
            Surface(
                color = DeepNavySurface,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (currentStep > 0) {
                        OutlinedButton(
                            onClick = { currentStep-- },
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, DeepNavyCardBorder),
                            modifier = Modifier.height(48.dp)
                        ) {
                            Text("Previous", color = TextSecondary)
                        }
                    } else {
                        Spacer(modifier = Modifier.width(10.dp))
                    }

                    Button(
                        onClick = {
                            if (currentStep < totalSteps - 1) {
                                currentStep++
                            } else {
                                onComplete(
                                    studentName,
                                    selectedClass,
                                    selectedAge,
                                    selectedSubjects,
                                    isExamComing,
                                    targetExamTitle,
                                    daysUntilExam,
                                    studyMasteryGoal,
                                    dailyStudyMinutes
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .height(48.dp)
                            .testTag("onboarding_next_button")
                    ) {
                        Text(
                            text = if (currentStep == totalSteps - 1) "Start Learning with StudyPal 🚀" else "Continue",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = if (currentStep == totalSteps - 1) Icons.Default.Check else Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            when (currentStep) {
                // STEP 1: NAME
                0 -> {
                    QuestionHeader(
                        stepTitle = "What is your name?",
                        subtitle = "StudyPal will personalize your greetings, daily study plans, and oral viva tests."
                    )

                    Card(
                        colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                        shape = RoundedCornerShape(18.dp),
                        border = BorderStroke(1.dp, DeepNavyCardBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            OutlinedTextField(
                                value = studentName,
                                onValueChange = { studentName = it },
                                label = { Text("Your Full Name") },
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = ElectricCyan,
                                    unfocusedBorderColor = DeepNavyCardBorder,
                                    focusedTextColor = TextPrimary,
                                    unfocusedTextColor = TextPrimary
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("onboarding_name_input")
                            )

                            Text(
                                text = "Welcome $studentName! StudyPal is ready to be your 24/7 AI study copilot.",
                                fontFamily = PlusJakartaSans,
                                fontSize = 13.sp,
                                color = ElectricCyan
                            )
                        }
                    }
                }

                // STEP 2: CLASS (Class 1 to Master's Degree) & AGE
                1 -> {
                    QuestionHeader(
                        stepTitle = "What class or degree are you studying?",
                        subtitle = "From Class 1 all the way to Master's degree, we tailor the curriculum to your level."
                    )

                    // 1. Education Tier Selector
                    Text(
                        text = "1. Select Education Category:",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        educationCategories.forEachIndexed { idx, category ->
                            val isSelectedCategory = selectedCategoryIndex == idx
                            Surface(
                                color = if (isSelectedCategory) Color(0xFF132238) else DeepNavyCard,
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(
                                    1.dp,
                                    if (isSelectedCategory) ElectricCyan else DeepNavyCardBorder
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedCategoryIndex = idx
                                        selectedClass = category.levels.first()
                                        selectedAge = category.defaultAge
                                        selectedSubjects = category.sampleSubjects.take(4)
                                    }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        Text(text = category.iconEmoji, fontSize = 18.sp)
                                        Text(
                                            text = category.title,
                                            fontFamily = PlusJakartaSans,
                                            fontWeight = if (isSelectedCategory) FontWeight.Bold else FontWeight.Medium,
                                            fontSize = 13.sp,
                                            color = if (isSelectedCategory) ElectricCyan else TextPrimary
                                        )
                                    }
                                    if (isSelectedCategory) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = null,
                                            tint = ElectricCyan,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // 2. Specific Class or Degree within chosen Category
                    val currentCategory = educationCategories[selectedCategoryIndex]
                    Text(
                        text = "2. Choose Your Specific Class / Program:",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        currentCategory.levels.forEach { levelName ->
                            val isSel = selectedClass == levelName
                            Surface(
                                color = if (isSel) ElectricCyan else DeepNavyCard,
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(
                                    1.dp,
                                    if (isSel) ElectricCyan else DeepNavyCardBorder
                                ),
                                modifier = Modifier
                                    .clickable { selectedClass = levelName }
                                    .testTag("class_level_${levelName.take(8).replace(" ", "_")}")
                            ) {
                                Text(
                                    text = levelName,
                                    fontFamily = OutfitFont,
                                    fontWeight = if (isSel) FontWeight.Bold else FontWeight.Medium,
                                    fontSize = 12.sp,
                                    color = if (isSel) Color.Black else TextPrimary,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // 3. Student Age
                    Text(
                        text = "3. Student Age: $selectedAge years old",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )

                    // Age slider / quick chips
                    val ageRange = when (selectedCategoryIndex) {
                        0 -> 6..11
                        1 -> 11..14
                        2 -> 14..17
                        3 -> 16..19
                        4 -> 18..24
                        else -> 21..28
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        ageRange.forEach { age ->
                            val isSel = selectedAge == age
                            Surface(
                                color = if (isSel) StudyPalPurple else DeepNavyCard,
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(
                                    1.dp,
                                    if (isSel) VividViolet else DeepNavyCardBorder
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { selectedAge = age }
                                    .testTag("age_option_$age")
                            ) {
                                Text(
                                    text = "$age",
                                    fontFamily = OutfitFont,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = if (isSel) Color.White else TextPrimary,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(vertical = 10.dp)
                                )
                            }
                        }
                    }
                }

                // STEP 3: SUBJECTS THEY READ
                2 -> {
                    val currentCategory = educationCategories[selectedCategoryIndex]
                    QuestionHeader(
                        stepTitle = "What subjects do you read / study?",
                        subtitle = "Select all subjects currently in your syllabus for $selectedClass."
                    )

                    Card(
                        colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                        shape = RoundedCornerShape(18.dp),
                        border = BorderStroke(1.dp, DeepNavyCardBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = "${selectedSubjects.size} Subjects Selected for $selectedClass",
                                fontFamily = OutfitFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = ElectricCyan
                            )

                            currentCategory.sampleSubjects.forEach { sub ->
                                val isSelected = selectedSubjects.contains(sub)
                                Surface(
                                    color = if (isSelected) Color(0xFF132238) else DeepNavySurface,
                                    shape = RoundedCornerShape(12.dp),
                                    border = BorderStroke(
                                        1.dp,
                                        if (isSelected) ElectricCyan else DeepNavyCardBorder
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            selectedSubjects = if (isSelected) {
                                                if (selectedSubjects.size > 1) selectedSubjects - sub else selectedSubjects
                                            } else {
                                                selectedSubjects + sub
                                            }
                                        }
                                        .testTag("subject_toggle_${sub.take(8).replace(" ", "_")}")
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .clip(CircleShape)
                                                    .background(if (isSelected) ElectricCyan else TextMuted)
                                            )
                                            Text(
                                                text = sub,
                                                fontFamily = PlusJakartaSans,
                                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                                fontSize = 13.sp,
                                                color = if (isSelected) Color.White else TextSecondary
                                            )
                                        }

                                        Checkbox(
                                            checked = isSelected,
                                            onCheckedChange = null,
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = ElectricCyan,
                                                checkmarkColor = Color.Black,
                                                uncheckedColor = DeepNavyCardBorder
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // STEP 4: EXAM COMING OR NOT
                3 -> {
                    QuestionHeader(
                        stepTitle = "Is your exam coming up soon?",
                        subtitle = "We'll build a live countdown timer and focused revision sprint."
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            color = if (isExamComing) Color(0xFF2E1A08) else DeepNavyCard,
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(
                                1.5.dp,
                                if (isExamComing) StreakFlame else DeepNavyCardBorder
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { isExamComing = true }
                                .testTag("exam_coming_yes_card")
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Event,
                                    contentDescription = null,
                                    tint = StreakFlame,
                                    modifier = Modifier.size(32.dp)
                                )
                                Text(
                                    text = "Yes, Exam Soon",
                                    fontFamily = OutfitFont,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = if (isExamComing) StreakFlame else TextPrimary
                                )
                                Text(
                                    text = "Boards, Mid-Terms, or Semester Finals",
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 11.sp,
                                    color = TextSecondary,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        Surface(
                            color = if (!isExamComing) Color(0xFF102820) else DeepNavyCard,
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(
                                1.5.dp,
                                if (!isExamComing) SuccessGreen else DeepNavyCardBorder
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { isExamComing = false }
                                .testTag("exam_coming_no_card")
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MenuBook,
                                    contentDescription = null,
                                    tint = SuccessGreen,
                                    modifier = Modifier.size(32.dp)
                                )
                                Text(
                                    text = "No Major Exam",
                                    fontFamily = OutfitFont,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = if (!isExamComing) SuccessGreen else TextPrimary
                                )
                                Text(
                                    text = "Regular syllabus & concept mastery",
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 11.sp,
                                    color = TextSecondary,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    if (isExamComing) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(1.dp, StreakFlame.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp),
                                verticalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                OutlinedTextField(
                                    value = targetExamTitle,
                                    onValueChange = { targetExamTitle = it },
                                    label = { Text("Target Examination Name") },
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = StreakFlame,
                                        unfocusedBorderColor = DeepNavyCardBorder,
                                        focusedTextColor = TextPrimary,
                                        unfocusedTextColor = TextPrimary
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Text(
                                    text = "Days until examination countdown:",
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 13.sp,
                                    color = TextSecondary
                                )

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Slider(
                                        value = daysUntilExam.toFloat(),
                                        onValueChange = { daysUntilExam = it.toInt() },
                                        valueRange = 1f..365f,
                                        colors = SliderDefaults.colors(
                                            thumbColor = StreakFlame,
                                            activeTrackColor = StreakFlame,
                                            inactiveTrackColor = DeepNavyCardBorder
                                        ),
                                        modifier = Modifier.weight(1f)
                                    )

                                    Surface(
                                        color = DeepNavySurface,
                                        shape = RoundedCornerShape(10.dp),
                                        border = BorderStroke(1.dp, StreakFlame)
                                    ) {
                                        Text(
                                            text = "$daysUntilExam days",
                                            fontFamily = OutfitFont,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            color = StreakFlame,
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // STEP 5: STUDY MASTERY GOAL
                4 -> {
                    QuestionHeader(
                        stepTitle = "What is your Study Mastery Goal?",
                        subtitle = "Select your target score benchmark and daily study minutes habit."
                    )

                    Text(
                        text = "Target Academic Mastery Level:",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        masteryGoalOptions.forEach { (goalTitle, goalDesc) ->
                            val isSel = studyMasteryGoal == goalTitle
                            Surface(
                                color = if (isSel) Color(0xFF1B1838) else DeepNavyCard,
                                shape = RoundedCornerShape(14.dp),
                                border = BorderStroke(
                                    1.dp,
                                    if (isSel) StudyPalPurple else DeepNavyCardBorder
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { studyMasteryGoal = goalTitle }
                                    .testTag("mastery_goal_${goalTitle.take(8).replace(" ", "_")}")
                            ) {
                                Row(
                                    modifier = Modifier.padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isSel) Icons.Default.Stars else Icons.Default.StarOutline,
                                        contentDescription = null,
                                        tint = if (isSel) VividViolet else TextSecondary,
                                        modifier = Modifier.size(24.dp)
                                    )

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = goalTitle,
                                            fontFamily = OutfitFont,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            color = if (isSel) Color.White else TextPrimary
                                        )
                                        Text(
                                            text = goalDesc,
                                            fontFamily = PlusJakartaSans,
                                            fontSize = 12.sp,
                                            color = TextSecondary
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Daily Study Target Time (Minutes / Day):",
                        fontFamily = OutfitFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        dailyMinutesOptions.forEach { mins ->
                            val isSel = dailyStudyMinutes == mins
                            Surface(
                                color = if (isSel) ElectricCyan else DeepNavyCard,
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(
                                    1.dp,
                                    if (isSel) ElectricCyan else DeepNavyCardBorder
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { dailyStudyMinutes = mins }
                                    .testTag("daily_mins_$mins")
                            ) {
                                Column(
                                    modifier = Modifier.padding(vertical = 10.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "${mins}m",
                                        fontFamily = OutfitFont,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = if (isSel) Color.Black else TextPrimary
                                    )
                                    Text(
                                        text = "Daily",
                                        fontFamily = PlusJakartaSans,
                                        fontSize = 10.sp,
                                        color = if (isSel) Color.Black.copy(alpha = 0.7f) else TextSecondary
                                    )
                                }
                            }
                        }
                    }
                }

                // STEP 6: SUMMARY & LAUNCH
                5 -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(24.dp))
                                .background(
                                    Brush.radialGradient(
                                        listOf(Color(0xFF1E284A), DarkNavyBackground)
                                    )
                                )
                                .border(
                                    2.dp,
                                    Brush.linearGradient(listOf(ElectricCyan, StudyPalPurple)),
                                    RoundedCornerShape(24.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.studypal_logo),
                                contentDescription = "StudyPal Mascot Logo",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(24.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Text(
                            text = "You're All Set, $studentName!",
                            fontFamily = OutfitFont,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "Your customized StudyPal learning plan for $selectedClass is active.",
                            fontFamily = PlusJakartaSans,
                            fontSize = 13.sp,
                            color = TextSecondary,
                            textAlign = TextAlign.Center
                        )

                        Card(
                            colors = CardDefaults.cardColors(containerColor = DeepNavyCard),
                            shape = RoundedCornerShape(18.dp),
                            border = BorderStroke(1.dp, ElectricCyan.copy(alpha = 0.4f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                SummaryItem("Student Name", studentName)
                                SummaryItem("Class / Degree", "$selectedClass • $selectedAge yrs")
                                SummaryItem("Target Examination", if (isExamComing) "$targetExamTitle ($daysUntilExam days left)" else "Concept Mastery Mode")
                                SummaryItem("Subjects (${selectedSubjects.size})", selectedSubjects.joinToString(", "))
                                SummaryItem("Mastery Goal", studyMasteryGoal)
                                SummaryItem("Daily Study Target", "$dailyStudyMinutes minutes per day")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuestionHeader(
    stepTitle: String,
    subtitle: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stepTitle,
            fontFamily = OutfitFont,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            color = Color.White
        )
        Text(
            text = subtitle,
            fontFamily = PlusJakartaSans,
            fontSize = 13.sp,
            color = TextSecondary,
            lineHeight = 18.sp
        )
    }
}

@Composable
fun SummaryItem(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = label,
            fontFamily = PlusJakartaSans,
            fontSize = 12.sp,
            color = TextSecondary,
            modifier = Modifier.weight(0.4f)
        )
        Text(
            text = value,
            fontFamily = OutfitFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = TextPrimary,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(0.6f)
        )
    }
}
