package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.ui.components.TarkoBottomNav
import com.example.ui.components.TarkoTopBar
import com.example.ui.screens.*
import com.example.ui.theme.DarkNavyBackground
import com.example.ui.theme.TarkoAITheme
import com.example.ui.viewmodel.Screen
import com.example.ui.viewmodel.TarkoViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: TarkoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TarkoAITheme {
                MainAppContent(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainAppContent(viewModel: TarkoViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val userProfile by viewModel.userProfile.collectAsState()
    val streakData by viewModel.streakData.collectAsState()
    val notes by viewModel.notes.collectAsState()
    val tutorMessages by viewModel.tutorMessages.collectAsState()
    val isSpeaking by viewModel.isSpeaking.collectAsState()
    val oralEval by viewModel.oralEvaluation.collectAsState()
    val isConvertingNote by viewModel.isConvertingNote.collectAsState()
    val paperAnswers by viewModel.activePaperAnswers.collectAsState()
    val paperSubmitted by viewModel.paperSubmitted.collectAsState()
    val paperScore by viewModel.paperScore.collectAsState()

    // 1. Google Sign-In Flow
    if (currentScreen is Screen.GoogleLogin) {
        GoogleLoginScreen(
            userEmailDefault = userProfile.email,
            userNameDefault = userProfile.name,
            onGoogleSignIn = { email, name ->
                viewModel.signInWithGoogle(email, name)
            },
            onGuestLogin = {
                viewModel.signInWithGoogle(userProfile.email, userProfile.name)
            }
        )
        return
    }

    // 2. Student Questionnaire / Onboarding Flow
    if (currentScreen is Screen.Onboarding) {
        OnboardingScreen(
            initialName = userProfile.name,
            initialClass = userProfile.classLevel,
            onComplete = { name, classLevel, age, subjects, isExamComing, targetExam, daysUntilExam, studyMasteryGoal, dailyGoalMinutes ->
                viewModel.submitOnboarding(
                    name = name,
                    classLevel = classLevel,
                    age = age,
                    subjects = subjects,
                    isExamComing = isExamComing,
                    targetExam = targetExam,
                    daysUntilExam = daysUntilExam,
                    studyMasteryGoal = studyMasteryGoal,
                    dailyGoalMinutes = dailyGoalMinutes
                )
            }
        )
        return
    }

    // 3. Main StudyPal App Experience
    val showBackButton = currentScreen !is Screen.Dashboard
    BackHandler(enabled = showBackButton) {
        viewModel.navigateBack()
    }

    val topBarTitle = when (currentScreen) {
        is Screen.Dashboard -> "StudyPal"
        is Screen.NotesConverter -> "Notes to Tools"
        is Screen.NoteDetail -> "Study Pack"
        is Screen.NcertDirectory -> "NCERT Syllabus"
        is Screen.NcertChapterDetail -> "Chapter Detail"
        is Screen.OralViva -> "Viva Voce"
        is Screen.CbsePapers -> "CBSE Papers"
        is Screen.TakePaper -> "Timed Exam"
        is Screen.AiTutor -> "AI Tutor"
        is Screen.FlashcardsScreen -> "Flashcards"
        is Screen.StreakTracker -> "Study Streak"
        is Screen.Profile -> "Student Profile"
        else -> "StudyPal"
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkNavyBackground),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TarkoTopBar(
                title = topBarTitle,
                userProfile = userProfile,
                streakData = streakData,
                showBackButton = showBackButton,
                onBackClick = { viewModel.navigateBack() },
                onStreakClick = { viewModel.navigateTo(Screen.StreakTracker) },
                onProfileClick = { viewModel.navigateTo(Screen.Profile) }
            )
        },
        bottomBar = {
            TarkoBottomNav(
                currentScreen = currentScreen,
                onNavigate = { screen -> viewModel.navigateTo(screen) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(DarkNavyBackground)
        ) {
            when (val screen = currentScreen) {
                is Screen.Dashboard -> {
                    DashboardScreen(
                        userProfile = userProfile,
                        streakData = streakData,
                        onNavigateNotes = { viewModel.navigateTo(Screen.NotesConverter) },
                        onNavigateNcert = { viewModel.navigateTo(Screen.NcertDirectory) },
                        onNavigateViva = { viewModel.navigateTo(Screen.OralViva()) },
                        onNavigatePapers = { viewModel.navigateTo(Screen.CbsePapers) },
                        onNavigateTutor = { viewModel.navigateTo(Screen.AiTutor) },
                        onNavigateStreak = { viewModel.navigateTo(Screen.StreakTracker) },
                        onNavigateFlashcards = { viewModel.navigateTo(Screen.FlashcardsScreen()) },
                        onLogStudyMinutes = { mins -> viewModel.logStudyTime(mins) }
                    )
                }

                is Screen.NotesConverter -> {
                    NotesConverterScreen(
                        notes = notes,
                        isConverting = isConvertingNote,
                        onConvert = { title, text -> viewModel.convertNotes(title, text) },
                        onSelectNote = { id -> viewModel.navigateTo(Screen.NoteDetail(id)) }
                    )
                }

                is Screen.NoteDetail -> {
                    val noteDoc = notes.find { it.id == screen.noteId } ?: notes.firstOrNull()
                    if (noteDoc != null) {
                        NoteDetailScreen(
                            note = noteDoc,
                            onBack = { viewModel.navigateBack() }
                        )
                    } else {
                        viewModel.navigateBack()
                    }
                }

                is Screen.NcertDirectory -> {
                    NcertDirectoryScreen(
                        subjects = viewModel.getNcertSubjects(),
                        onSelectSubject = {},
                        getChaptersForSubject = { subId -> viewModel.getNcertChapters(subId) },
                        onSelectChapter = { chId -> viewModel.navigateTo(Screen.NcertChapterDetail(chId)) }
                    )
                }

                is Screen.NcertChapterDetail -> {
                    val ch = viewModel.getChapterById(screen.chapterId)
                    if (ch != null) {
                        NcertChapterDetailScreen(
                            chapter = ch,
                            onStartViva = { viewModel.navigateTo(Screen.OralViva(ch.id)) },
                            onStartFlashcards = { viewModel.navigateTo(Screen.FlashcardsScreen(ch.id)) }
                        )
                    } else {
                        viewModel.navigateBack()
                    }
                }

                is Screen.OralViva -> {
                    val items = if (screen.chapterId != null) {
                        viewModel.getChapterById(screen.chapterId)?.oralQuestions ?: emptyList()
                    } else {
                        viewModel.getChapterById("ch_sci_1")?.oralQuestions ?: emptyList()
                    }
                    OralVivaScreen(
                        oralItems = items,
                        isSpeaking = isSpeaking,
                        onSpeak = { text -> viewModel.speak(text) },
                        onStopSpeak = { viewModel.stopSpeaking() },
                        onEvaluate = { item, answer -> viewModel.evaluateOralResponse(item, answer) },
                        evaluation = oralEval
                    )
                }

                is Screen.CbsePapers -> {
                    CbsePapersScreen(
                        papers = viewModel.getCbsePapers(),
                        onSelectPaper = { id ->
                            viewModel.resetPaper()
                            viewModel.navigateTo(Screen.TakePaper(id))
                        }
                    )
                }

                is Screen.TakePaper -> {
                    val paper = viewModel.getCbsePapers().find { it.id == screen.paperId } ?: viewModel.getCbsePapers().first()
                    TakePaperScreen(
                        paper = paper,
                        answers = paperAnswers,
                        isSubmitted = paperSubmitted,
                        score = paperScore,
                        onSelectOption = { qId, opt -> viewModel.selectPaperOption(qId, opt) },
                        onSubmit = { p -> viewModel.submitPaper(p) },
                        onReset = { viewModel.resetPaper() }
                    )
                }

                is Screen.AiTutor -> {
                    AiTutorScreen(
                        messages = tutorMessages,
                        onSendMessage = { txt -> viewModel.sendTutorMessage(txt) }
                    )
                }

                is Screen.FlashcardsScreen -> {
                    val cards = if (screen.chapterId != null) {
                        viewModel.getChapterById(screen.chapterId)?.flashcards ?: emptyList()
                    } else {
                        notes.firstOrNull()?.flashcards ?: emptyList()
                    }
                    FlashcardsScreen(
                        flashcards = cards,
                        onSpeakWord = { word -> viewModel.speak(word) }
                    )
                }

                is Screen.StreakTracker -> {
                    StreakTrackerScreen(
                        streakData = streakData,
                        onLogMinutes = { mins -> viewModel.logStudyTime(mins) },
                        onUseShield = { viewModel.useStreakShield() },
                        onStartPractice = { viewModel.navigateTo(Screen.OralViva()) }
                    )
                }

                is Screen.Profile -> {
                    ProfileScreen(
                        userProfile = userProfile,
                        onSaveProfile = { name, cls, age, subjects, isExam, exam, days, mastery, goal, lang ->
                            viewModel.updateProfile(name, cls, age, subjects, isExam, exam, days, mastery, goal, lang)
                        },
                        onLogout = {
                            viewModel.signOut()
                        }
                    )
                }

                else -> {}
            }
        }
    }
}
