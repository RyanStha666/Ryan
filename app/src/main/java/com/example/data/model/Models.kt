package com.example.data.model

data class UserProfile(
    val isLoggedIn: Boolean = false,
    val email: String = "learner@studypal.internal",
    val name: String = "Learner",
    val classLevel: String = "Class 10 CBSE",
    val age: Int = 16,
    val subjects: List<String> = listOf("Mathematics", "Science", "Social Science", "English"),
    val isExamComing: Boolean = true,
    val targetExam: String = "CBSE 2027 Board Examination",
    val daysUntilExam: Int = 124,
    val studyMasteryGoal: String = "95%+ Board Ranker Mastery",
    val dailyGoalMinutes: Int = 45,
    val preferredLanguage: String = "Hinglish",
    val avatarInitials: String = "SP",
    val isOnboardingCompleted: Boolean = false
)

data class StreakData(
    val currentStreak: Int = 7,
    val longestStreak: Int = 14,
    val shieldsRemaining: Int = 2,
    val dailyGoalMinutes: Int = 45,
    val todayMinutesStudied: Int = 24,
    val weeklyHistory: List<DayActivity> = listOf(
        DayActivity("Mon", true, 35),
        DayActivity("Tue", true, 40),
        DayActivity("Wed", true, 25),
        DayActivity("Thu", true, 30),
        DayActivity("Fri", true, 45),
        DayActivity("Sat", true, 30),
        DayActivity("Sun", true, 24)
    )
)

data class DayActivity(
    val dayLabel: String,
    val completed: Boolean,
    val minutesStudied: Int
)

data class NcertSubject(
    val id: String,
    val name: String,
    val classLevel: String,
    val colorHex: Long,
    val iconType: String,
    val chaptersCount: Int,
    val pyqsCount: Int
)

data class NcertChapter(
    val id: String,
    val subjectId: String,
    val title: String,
    val chapterNumber: Int,
    val classLevel: String,
    val summary: String,
    val formulas: List<String>,
    val highYieldPoints: List<String>,
    val isCompleted: Boolean = false,
    val flashcards: List<Flashcard>,
    val oralQuestions: List<OralTestItem>,
    val pyqs: List<QuizQuestion>
)

data class NoteDocument(
    val id: String,
    val title: String,
    val originalText: String,
    val summaryBullets: List<String>,
    val formulas: List<String>,
    val mindMapNodes: List<MindMapNode>,
    val flashcards: List<Flashcard>,
    val quizQuestions: List<QuizQuestion>
)

data class MindMapNode(
    val concept: String,
    val subpoints: List<String>
)

data class Flashcard(
    val id: String,
    val front: String,
    val back: String,
    val hint: String,
    val subject: String,
    var isMastered: Boolean = false
)

data class QuizQuestion(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val marks: Int = 1
)

data class OralTestItem(
    val id: String,
    val promptQuestion: String,
    val expectedAnswer: String,
    val mandatoryKeywords: List<String>,
    var studentAnswer: String = "",
    var evaluationScore: Int? = null,
    var feedback: String? = null
)

data class CbsePaper(
    val id: String,
    val title: String,
    val subject: String,
    val classLevel: String,
    val durationMinutes: Int,
    val totalMarks: Int,
    val sections: List<PaperSection>
)

data class PaperSection(
    val name: String,
    val instructions: String,
    val questions: List<PaperQuestion>
)

data class PaperQuestion(
    val id: String,
    val questionText: String,
    val type: String, // "MCQ", "SHORT", "LONG", "CASE_BASED"
    val options: List<String> = emptyList(),
    val correctOptionIndex: Int = -1,
    val marks: Int,
    val modelSolution: String
)

data class TutorMessage(
    val id: String,
    val text: String,
    val isFromUser: Boolean,
    val timestamp: String,
    val suggestedPills: List<String> = emptyList()
)
