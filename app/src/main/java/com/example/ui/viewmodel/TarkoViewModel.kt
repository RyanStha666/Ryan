package com.example.ui.viewmodel

import android.app.Application
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.*
import com.example.data.repository.TarkoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

sealed class Screen {
    data object GoogleLogin : Screen()
    data object Onboarding : Screen()
    data object Dashboard : Screen()
    data object NotesConverter : Screen()
    data class NoteDetail(val noteId: String) : Screen()
    data object NcertDirectory : Screen()
    data class NcertChapterDetail(val chapterId: String) : Screen()
    data class OralViva(val chapterId: String? = null) : Screen()
    data object CbsePapers : Screen()
    data class TakePaper(val paperId: String) : Screen()
    data object AiTutor : Screen()
    data class FlashcardsScreen(val chapterId: String? = null) : Screen()
    data object StreakTracker : Screen()
    data object Profile : Screen()
}

class TarkoViewModel(application: Application) : AndroidViewModel(application), TextToSpeech.OnInitListener {

    private val repository = TarkoRepository()

    val userProfile: StateFlow<UserProfile> = repository.userProfile
    val streakData: StateFlow<StreakData> = repository.streakData
    val notes: StateFlow<List<NoteDocument>> = repository.notes
    val tutorMessages: StateFlow<List<TutorMessage>> = repository.tutorMessages

    private val _currentScreen = MutableStateFlow<Screen>(
        if (!repository.userProfile.value.isLoggedIn) {
            Screen.GoogleLogin
        } else if (!repository.userProfile.value.isOnboardingCompleted) {
            Screen.Onboarding
        } else {
            Screen.Dashboard
        }
    )
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val screenBackStack = mutableListOf<Screen>()

    // Text To Speech
    private var tts: TextToSpeech? = null
    private val _isTtsReady = MutableStateFlow(false)
    val isTtsReady: StateFlow<Boolean> = _isTtsReady.asStateFlow()

    private val _isSpeaking = MutableStateFlow(false)
    val isSpeaking: StateFlow<Boolean> = _isSpeaking.asStateFlow()

    // Oral Viva State
    private val _activeOralItem = MutableStateFlow<OralTestItem?>(null)
    val activeOralItem: StateFlow<OralTestItem?> = _activeOralItem.asStateFlow()

    private val _oralEvaluation = MutableStateFlow<Pair<Int, String>?>(null)
    val oralEvaluation: StateFlow<Pair<Int, String>?> = _oralEvaluation.asStateFlow()

    // Active Note creation
    private val _isConvertingNote = MutableStateFlow(false)
    val isConvertingNote: StateFlow<Boolean> = _isConvertingNote.asStateFlow()

    // CBSE Paper State
    private val _activePaperAnswers = MutableStateFlow<Map<String, Int>>(emptyMap())
    val activePaperAnswers: StateFlow<Map<String, Int>> = _activePaperAnswers.asStateFlow()

    private val _paperSubmitted = MutableStateFlow(false)
    val paperSubmitted: StateFlow<Boolean> = _paperSubmitted.asStateFlow()

    private val _paperScore = MutableStateFlow(0)
    val paperScore: StateFlow<Int> = _paperScore.asStateFlow()

    init {
        try {
            tts = TextToSpeech(application.applicationContext, this)
        } catch (_: Exception) {}
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)
            if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                _isTtsReady.value = true
            }
        }
    }

    fun signInWithGoogle(email: String, name: String) {
        repository.loginWithGoogle(email, name)
        screenBackStack.clear()
        if (!userProfile.value.isOnboardingCompleted) {
            _currentScreen.value = Screen.Onboarding
        } else {
            _currentScreen.value = Screen.Dashboard
        }
    }

    fun signOut() {
        repository.logout()
        screenBackStack.clear()
        stopSpeaking()
        _currentScreen.value = Screen.GoogleLogin
    }

    fun submitOnboarding(
        name: String,
        classLevel: String,
        age: Int,
        subjects: List<String>,
        isExamComing: Boolean,
        targetExam: String,
        daysUntilExam: Int,
        studyMasteryGoal: String,
        dailyGoalMinutes: Int
    ) {
        repository.completeOnboarding(
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
        screenBackStack.clear()
        _currentScreen.value = Screen.Dashboard
    }

    fun speak(text: String) {
        if (_isTtsReady.value && tts != null) {
            _isSpeaking.value = true
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "STUDYPAL_TTS_ID")
        }
    }

    fun stopSpeaking() {
        tts?.stop()
        _isSpeaking.value = false
    }

    fun navigateTo(screen: Screen) {
        if (_currentScreen.value != screen) {
            screenBackStack.add(_currentScreen.value)
            _currentScreen.value = screen
        }
    }

    fun navigateBack(): Boolean {
        if (screenBackStack.isNotEmpty()) {
            _currentScreen.value = screenBackStack.removeAt(screenBackStack.size - 1)
            stopSpeaking()
            return true
        }
        return false
    }

    fun getNcertSubjects(): List<NcertSubject> = repository.getNcertSubjects()

    fun getNcertChapters(subjectId: String): List<NcertChapter> = repository.getNcertChapters(subjectId)

    fun getChapterById(chapterId: String): NcertChapter? {
        val allSubs = repository.getNcertSubjects()
        for (sub in allSubs) {
            val chs = repository.getNcertChapters(sub.id)
            val found = chs.find { it.id == chapterId }
            if (found != null) return found
        }
        return null
    }

    fun convertNotes(title: String, rawText: String) {
        viewModelScope.launch {
            _isConvertingNote.value = true
            val doc = repository.convertNotes(title, rawText)
            _isConvertingNote.value = false
            navigateTo(Screen.NoteDetail(doc.id))
        }
    }

    fun sendTutorMessage(text: String) {
        if (text.isNotBlank()) {
            repository.sendTutorMessage(text)
        }
    }

    fun setOralQuestion(item: OralTestItem) {
        _activeOralItem.value = item
        _oralEvaluation.value = null
        speak(item.promptQuestion)
    }

    fun evaluateOralResponse(item: OralTestItem, answerText: String) {
        val eval = repository.evaluateOralAnswer(item, answerText)
        _oralEvaluation.value = eval
        speak("Score: ${eval.first} percent. ${eval.second}")
    }

    fun getCbsePapers(): List<CbsePaper> = repository.getCbsePracticePapers()

    fun selectPaperOption(questionId: String, optionIndex: Int) {
        _activePaperAnswers.update {
            it + (questionId to optionIndex)
        }
    }

    fun submitPaper(paper: CbsePaper) {
        var score = 0
        val answers = _activePaperAnswers.value
        for (section in paper.sections) {
            for (q in section.questions) {
                if (q.type == "MCQ" && answers[q.id] == q.correctOptionIndex) {
                    score += q.marks
                } else if (q.type != "MCQ" && answers.containsKey(q.id)) {
                    score += q.marks
                }
            }
        }
        _paperScore.value = score
        _paperSubmitted.value = true
        repository.logStudyTime(paper.durationMinutes)
    }

    fun resetPaper() {
        _activePaperAnswers.value = emptyMap()
        _paperSubmitted.value = false
        _paperScore.value = 0
    }

    fun logStudyTime(minutes: Int) {
        repository.logStudyTime(minutes)
    }

    fun useStreakShield(): Boolean = repository.useStreakShield()

    fun updateProfile(
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
    ) {
        repository.updateProfile(
            name = name,
            classLevel = classLevel,
            age = age,
            subjects = subjects,
            isExamComing = isExamComing,
            targetExam = targetExam,
            daysUntilExam = daysUntilExam,
            studyMasteryGoal = studyMasteryGoal,
            dailyGoalMinutes = dailyGoalMinutes,
            preferredLanguage = preferredLanguage
        )
    }

    override fun onCleared() {
        super.onCleared()
        tts?.stop()
        tts?.shutdown()
    }
}
